<template>
  <el-dialog :visible.sync="dialogFormVisible" title="检测方法编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="基础信息">
        <el-form :model="method" :rules="rules" ref="form">
          <el-form-item :label-width="formLabelWidth" label="检测方法名称">
            <el-input v-model="method.methodName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检测方法内容">
            <el-input v-model="method.methodDescription"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检测方法原则">
            <el-input v-model="method.methodPrinciple"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="未来效果">
            <el-input v-model="method.futureExpansion"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检测方法标签">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="method.dynamicLabels"
                          :multiple="true"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="options"
                          placeholder="请选择标签"
              />
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检验条件" :prop="'method.methodConditions'">
            <el-button type="primary" @click="addConditions" style="float: right">添加</el-button>
            <el-row v-for="(item, index) in method.methodConditions"
                    :key="index"
                    :gutter="20"
            >
              <el-col :span="2">
                <el-tag type="primary">
                  检验条件{{ index }}
                </el-tag>
              </el-col>
              <el-col :span="6">
                <el-input v-model="item.conditionName" placeholder="名称" style="width:100%"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col :span="6">
                <el-input v-model="item.conditionDescription" placeholder="内容" style="width:100%"></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click.prevent="removeCondition(item)">删除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="影响因素" :prop="'method.methodFactors'">
            <el-button type="primary" @click="addFactor" style="float: right">添加</el-button>
            <el-row v-for="(item, index) in method.methodFactors"
                    :key="index"
                    :gutter="20"
            >
              <el-col :span="2">
                <el-tag type="primary">
                  影响因素{{ index }}
                </el-tag>
              </el-col>
              <el-col :span="6">
                <el-input v-model="item.factorName" placeholder="名称" style="width:100%"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col :span="6">
                <el-input v-model="item.factorDescription" placeholder="内容" style="width:100%"></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click.prevent="removeFactor(item)">删除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检测模式" :prop="'method.methodModes'">
            <el-button type="primary" @click="addMode" style="float: right">添加</el-button>
            <el-row v-for="(item, index) in method.methodModes"
                    :key="index"
                    :gutter="20"
            >
              <el-col :span="2">
                <el-tag type="primary">
                  检测模式{{ index }}
                </el-tag>
              </el-col>
              <el-col :span="6">
                <el-input v-model="item.modeName" placeholder="名称" style="width:100%"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col :span="6">
                <el-input v-model="item.modeDescription" placeholder="内容" style="width:100%"></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click.prevent="removeMode(item)">删除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmit">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="关联信息">
        <el-form :model="method" :rules="rules" ref="relatedForm">
          <el-form-item :label-width="formLabelWidth" label="关联工艺实例">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="processLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="processLabelOptions"
                          placeholder="请选择工艺标签"
              />
              <el-select v-model="processIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in processOptions"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="关联质量问题">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="problemLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="problemLabelOptions"
                          placeholder="请选择问题标签"
              />
              <el-select v-model="problemIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in problemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用设备">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="resourceLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="resourceIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in resourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="fileLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="fileLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="fileIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in fileOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="changeRelation">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'
import * as methodManagement from '@/api/system/qualityProblemManagement'

export default {
  name: 'modifyMethod',
  components: { Treeselect },
  props: {
    selectProblem: {
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
    selectProblem: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.method = newVal
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
    },
    precessLabel:{
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
      tabPosition:"left",
      windowTitle:"创建问题",
      method: {
        methodId: 0,
        methodName: '',
        dynamicLabels: [],
        methodDescription: '',
        methodPrinciple:'',
        futureExpansion:'',
        methodConditions: [],
        methodFactors: [],
        methodModes:[]
      },
      processLabel:'',
      processLabelOptions: [],
      processOptions: [],
      processIds: [],
      problemLabel:'',
      problemLabelOptions: [],
      problemOptions: [],
      problemIds:[],
      resourceLabel:'',
      resourceLabelOptions: [],
      resourceOptions: [],
      resourceIds:[],
      fileLabel:'',
      fileLabelOptions: [],
      fileOptions:[],
      fileIds:[],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      // 表单校验
      rules: {
        methodName: [
          { required: true, message: "问题名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '原则名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        methodDescription: [
          { required: true, message: "问题内容不能为空", trigger: "blur" }
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
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.processLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.problemLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.resourceLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.fileLabelOptions.push(response.data)
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
      this.method = {
        methodId: 0,
        methodName: '',
        dynamicLabels: [],
        methodDescription: '',
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
      this.method.appearances.push({
        appearanceId:0,
        appearanceName:'',
        appearanceDescription:''
      })
    },
    removeAppearance(item) {
      let index = this.method.appearances.indexOf(item)
      if (index !== -1) {
        this.method.appearances.splice(index, 1)
      }
    },
    addReason() {
      this.method.reasons.push({
        reasonId:0,
        reasonName:'',
        reasonDescription:''
      })
    },
    removeReason(item) {
      let index = this.method.reasons.indexOf(item)
      if (index !== -1) {
        this.method.reasons.splice(index, 1)
      }
    },
    addSolution() {
      this.method.reasons.push({
        solutionId:0,
        solutionName:'',
        solutionDescription:''
      })
    },
    removeSolution(item) {
      let index = this.method.reasons.indexOf(item)
      if (index !== -1) {
        this.method.reasons.splice(index, 1)
      }
    },
    onSubmit(){
      if(this.windowTitle === "创建问题"){
        methodManagement.createQualityProblem(this.method).then(result =>{
          if(result.code === 200){
            this.method = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else {
        methodManagement.updateQualityProblem(this.method).then(result =>{
          if(result.code === 200){
            this.method = result.data;
            this.$modal.msgSuccess("修改成功！")
          }
        })
      }
    },
    changeRelation(){
      let relations = {
        processIds:this.processIds,
        problemIds:this.problemIds,
        resourceIds:this.resourceIds,
        fileIds:this.fileIds,
      }
      methodManagement.changeRelations(relations).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("更改成功")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
