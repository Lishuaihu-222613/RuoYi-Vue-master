<template>
  <el-dialog :visible.sync="dialogFormVisible" title="推进剂配方编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="prescription" :rules="rules" ref="form">
      <el-form-item :label-width="formLabelWidth" label="推进剂配方名称">
        <el-input v-model="prescription.prescriptionName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="推进剂配方描述">
        <el-input v-model="prescription.prescriptionDescription"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="推进剂配方密度">
        <el-input v-model="prescription.density"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="推进剂配方危险等级">
        <el-input v-model="prescription.dangerLevel"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="推进剂配方标签">
        <el-row class="row-bg" justify="space-around" type="flex">
          <treeselect v-model="prescription.dynamicLabels"
                      :multiple="true"
                      :clearable="true"
                      :searchable="true"
                      :normalizer="normalizer"
                      :options="options"
                      placeholder="请选择分类标签"
          />
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="固化时间" :prop="'prescription.curingTime'">
        <el-button type="primary" @click="addCuringTime" style="float: right">添加</el-button>
        <el-row v-for="(value, key) in prescription.curingTime"
                :key="key"
                :gutter="20"
        >
          <el-col :span="6">
            <el-input v-model="key" placeholder="名称" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="value" placeholder="内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeCuringTime(key)">删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="其他属性">
        <el-button type="primary" @click="addOtherProperty" style="float: right">添加</el-button>
        <el-row v-for="(value, key) in prescription.otherProperty"
                :key="key"
                :gutter="20"
        >
          <el-col :span="6">
            <el-input v-model="key" placeholder="名称" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="value" placeholder="内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeOtherProperty(key)">删除</el-button>
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
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'
import * as prescriptionManagement from '@/api/system/prescriptionManagement'

export default {
  name: 'modifyPrescription',
  components:{Treeselect},

  props: {
    selectPrescription: {
      type: Object,
      default: {}
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
    selectPrescription: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.Prescription = newVal
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
      windowTitle:"创建配方",
      prescription: {
        prescriptionId: 0,
        prescriptionName: '',
        prescriptionLabels: [],
        prescriptionDescription: '',
        density:'',
        dangerLevel:'',
        curingTime: new Map(),
        otherProperty: new Map(),
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      // 表单校验
      rules: {
        prescriptionName: [
          { required: true, message: "配方名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '配方名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        prescriptionDescription: [
          { required: true, message: "配方内容不能为空", trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    /** 查询标签列表 */
    getLabelList() {
      this.loading = true
      treeManagement.getTreeManagement(25500).then(response => {
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
        id: node.leafId,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleOpen() {
      this.getLabelList();
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.prescription = {
        prescriptionId: 0,
        prescriptionName: '',
        prescriptionLabels: [],
        prescriptionDescription: '',
        density:'',
        dangerLevel:'',
        curingTime: new Map(),
        otherProperty: new Map(),
      },
        this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addCuringTime() {
      this.prescription.curingTime.push("","")
    },
    removeCuringTime(key) {
      this.prescription.curingTime.delete(key)
    },
    addOtherProperty() {
      this.prescription.otherProperty.push("","")
    },
    removeOtherProperty(key) {
      this.prescription.otherProperty.delete(key)
    },
    onSubmit(){
      if(this.windowTitle === "创建配方"){
        prescriptionManagement.createPrescription(this.prescription).then(result =>{
          if(result.code === 200){
            this.prescription = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else {
        prescriptionManagement.updatePrescription(this.prescription).then(result =>{
          if(result.code === 200){
            this.prescription = result.data;
            this.$modal.msgSuccess("修改成功！")
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
