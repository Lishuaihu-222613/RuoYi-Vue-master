<template>
  <el-dialog :visible.sync="dialogFormVisible" title="设计原则编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="principle" :rules="rules" ref="form">
      <el-form-item :label-width="formLabelWidth" label="设计原则名称">
        <el-input v-model="principle.principleName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="设计原则内容">
        <el-input v-model="principle.principleDescription"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="设计原则标签">
        <el-row class="row-bg" justify="space-around" type="flex">
          <treeselect v-model="principle.dynamicLabels"
                      :multiple="true"
                      :clearable="true"
                      :searchable="true"
                      @select=""
                      :normalizer="normalizer"
                      :options="options"
                      placeholder="请选择标签"
          />
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="应用条件">
        <el-row>
          <el-col :offset="18" :span="6">
            <el-button type="primary" @click.prevent="addCondition" >添加条件</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item,index) in principle.principleConditions" :key="index">
          <el-col :span="18">
            <el-input v-model="principle.principleConditions[index]"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button
              type="text"
              @click="removeCondition(principle.principleConditions[index])"
            > 删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="应用效果">
        <el-row>
          <el-col :offset="18" :span="6">
            <el-button type="primary" @click.prevent="addResult" >添加用途</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item,index) in principle.principleResults" :key="index">
          <el-col :span="18">
            <el-input v-model="principle.principleResults[index]"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button
              type="text"
              @click="removeResult(principle.principleResults[index])"
            > 删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSubmit">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>

import * as principleManagement from '@/api/system/principleManagement'
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'modifyPrinciple',

  components:{
    Treeselect
  },


  props: {
    selectPrincipleId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title:{
      type: String,
      default: ""
    }
  },
  watch: {
    selectPrincipleId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.principleId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    title:{
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },

  created() {
  },

  data() {
    return {
      // 遮罩层
      loading: true,
      // 知识树管理树选项
      options: [],
      windowTitle:"创建原则",
      principle: {
        principleId: 0,
        principleName: '',
        dynamicLabels: [],
        principleDescription: '',
        principleConditions: [""],
        principleResults: [""]
      },
      principleId:0,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      // 表单校验
      rules: {
        principleName: [
          { required: true, message: "原则名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '原则名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        principleDescription: [
          { required: true, message: "原则内容不能为空", trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    /** 查询标签列表 */
    getLabelList() {
      this.loading = true
      this.options = []
      treeManagement.getTreeManagement(25656).then(response => {
        console.log(response.data)
        this.options.push(response.data)
      })
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleOpen() {
      this.getLabelList();
      if(this.principleId === 0){
        this.principle = {
          principleId: 0,
          principleName: '',
          dynamicLabels: [],
          principleDescription: '',
          principleConditions: [""],
          principleResults: [""]
        }
      }else {
        principleManagement.getPrincipleById(this.principleId).then(result =>{
          if(result.code === 200){
            this.principle = result.data
          }
        })
      }

    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.principle = {
        principleId: 0,
        principleName: '',
        dynamicLabels: [],
        principleDescription: '',
        principleConditions: [],
        principleResults: []
      }
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addCondition() {
      console.log(1)
      this.principle.principleConditions.push(" ")
    },
    removeCondition(item) {
      let index = this.principle.principleConditions.indexOf(item)
      if (index !== -1) {
        this.principle.principleConditions.splice(index, 1)
      }
    },
    addResult() {
      this.principle.principleResults.push(" ")
    },
    removeResult(item) {
      let index = this.principle.principleResults.indexOf(item)
      if (index !== -1) {
        this.principle.principleResults.splice(index, 1)
      }
    },
    onSubmit(){
      if(this.windowTitle === "创建原则"){
        principleManagement.createPrinciple(this.principle).then(result =>{
          if(result.code === 200){
            this.principle = result.data;
            this.$modal.msgSuccess("创建原则成功！")
          }
        })
      } else {
        principleManagement.updatePrinciple(this.principle).then(result =>{
          if(result.code === 200){
            this.principle = result.data;
            this.$modal.msgSuccess("修改原则成功！")
          }
        })
      }
    }
  }

}
</script>

<style scoped>

</style>
