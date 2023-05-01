<template>
  <el-dialog :visible.sync="dialogFormVisible" title="质量问题编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="problem" :rules="rules" ref="form">
      <el-form-item :label-width="formLabelWidth" label="质量问题名称">
        <el-input v-model="problem.problemName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="质量问题内容">
        <el-input v-model="problem.problemDescription"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="质量问题标签">
        <el-row class="row-bg" justify="space-around" type="flex">
          <treeselect v-model="problem.dynamicLabels"
                      :multiple="true"
                      :clearable="true"
                      :searchable="true"
                      :normalizer="normalizer"
                      :options="options"
                      placeholder="请选择标签"
          />
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="外观表现" :prop="'problem.appearances'">
        <el-row>
          <el-col :offset="18" :span="6">
            <el-button type="primary" @click="addAppearance" >添加外观</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in problem.appearances"
                :key="index"
                :gutter="20"
        >
          <el-col :span="2">
            <el-tag type="primary">
              外观表现{{ index+1 }}
            </el-tag>
          </el-col>
          <el-col :span="6">
            <el-input v-model="problem.appearances[index].appearanceName" placeholder="名称" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="problem.appearances[index].appearanceDescription" placeholder="内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeAppearance(item)">删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="产生原因">
        <el-row>
          <el-col :offset="18" :span="6">
            <el-button type="primary" @click="addReason" >添加原因</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in problem.reasons"
                :key="index"
                :gutter="20"
        >
          <el-col :span="2">
            <el-tag type="primary">
              产生原因{{ index+1 }}
            </el-tag>
          </el-col>
          <el-col :span="6">
            <el-input v-model="problem.reasons[index].reasonName" placeholder="名称" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="problem.reasons[index].reasonDescription" placeholder="内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeReason(item)">删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="解决方法">
        <el-row>
          <el-col :offset="18" :span="6">
            <el-button type="primary" @click="addSolution">添加方法</el-button>
          </el-col>
        </el-row>

        <el-row v-for="(item, index) in problem.solutions"
                :key="index"
                :gutter="20"
        >
          <el-col :span="2">
            <el-tag type="primary">
              解决方法{{ index+1 }}
            </el-tag>
          </el-col>
          <el-col :span="6">
            <el-input v-model="problem.solutions[index].solutionName" placeholder="名称" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="problem.solutions[index].solutionDescription" placeholder="内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeSolution(item)">删除</el-button>
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
import * as problemManagement from'@/api/system/qualityProblemManagement'
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'


export default {
  name: 'modifyProblem',
  components: { Treeselect },
  props: {
    selectProblemId: {
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
    selectProblemId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.problemId = newVal
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
      windowTitle:"创建问题",
      problem: {
        problemId: 0,
        problemName: '',
        dynamicLabels: [],
        problemDescription: '',
        appearances: [],
        reasons: [],
        solutions:[]
      },
      problemId:0,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      // 表单校验
      rules: {
        problemName: [
          { required: true, message: "问题名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '原则名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        problemDescription: [
          { required: true, message: "问题内容不能为空", trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    /** 查询标签列表 */
    getLabelList() {
      this.loading = true
      this.options = []
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
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleOpen() {
      this.getLabelList();
      if(this.problemId === 0){
        this.problem = {
          problemId: 0,
          problemName: '',
          dynamicLabels: [],
          problemDescription: '',
          appearances: [],
          reasons: [],
          solutions:[]
        }
      }else {
        problemManagement.getQualityProblemById(this.problemId).then(result =>{
          if(result.code === 200){
            this.problem = result.data
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
      this.problem = {
        problemId: 0,
        problemName: '',
        dynamicLabels: [],
        problemDescription: '',
        appearances: [],
        reasons: [],
        solutions:[]
      }
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addAppearance() {
      this.problem.appearances.push({
        appearanceId:0,
        appearanceName:'',
        appearanceDescription:''
      })
    },
    removeAppearance(item) {
      let index = this.problem.appearances.indexOf(item)
      if (index !== -1) {
        this.problem.appearances.splice(index, 1)
      }
    },
    addReason() {
      this.problem.reasons.push({
        reasonId:0,
        reasonName:'',
        reasonDescription:''
      })
    },
    removeReason(item) {
      let index = this.problem.reasons.indexOf(item)
      if (index !== -1) {
        this.problem.reasons.splice(index, 1)
      }
    },
    addSolution() {
      this.problem.solutions.push({
        solutionId:0,
        solutionName:'',
        solutionDescription:''
      })
    },
    removeSolution(item) {
      let index = this.problem.solutions.indexOf(item)
      if (index !== -1) {
        this.problem.solutions.splice(index, 1)
      }
    },
    onSubmit(){
      if(this.windowTitle === "创建问题"){
        problemManagement.createQualityProblem(this.problem).then(result =>{
          if(result.code === 200){
            this.problem = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else {
        problemManagement.updateQualityProblem(this.problem).then(result =>{
          if(result.code === 200){
            this.problem = result.data;
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
