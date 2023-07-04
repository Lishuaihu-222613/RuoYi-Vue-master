<template>
  <!-- 添加或修改工艺元素配置对话框 -->
  <el-dialog :visible.sync="dialogFormVisible" :title="windowTitle" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen">
    <el-form ref="form" :model="element"  label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="序号" prop="elementNumber">
            <el-input v-model="element.elementNumber" placeholder="请输入序号" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="名称" prop="elementName">
            <el-input v-model="element.elementName" placeholder="请输入名称" maxlength="30"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="描述" prop="elementDescription">
          <el-input v-model="element.elementDescription" placeholder="请输入描述" type="textarea" autosize />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="类别">
            <el-radio-group v-model="elementType">
              <el-radio :label="'Process'">工 艺</el-radio>
              <el-radio :label="'Sequence'">工 序</el-radio>
              <el-radio :label="'Step'">工 步</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="标签">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="dynamicLabels"
                          :multiple="true"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="labelNormalizer"
                          :options="labelOptions"
                          placeholder="请选择元素标签"
              />
            </el-row>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="包含子级">
            <el-switch
              v-model="element.hasSubElements"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="典型">
            <el-switch
              v-model="isTypical"
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8" v-if="!(elementType === 'Process')">
          <el-form-item label="父级">
            <treeselect v-model="parentId"
                        :clearable="true"
                        :searchable="true"
                        :normalizer="elementNormalizer"
                        :options="elementOptions"
                        placeholder="请选择父级"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item  label="备注" prop="elementRemark">
          <el-row>
            <el-col :span="6" :offset="18">
              <el-button type="primary" @click="addElementRemark" >添加备注</el-button>
            </el-col>
          </el-row>
          <el-row v-for="(item,index) in element.elementRemark" :key="index">
            <el-col :span="20">
              <el-input v-model="element.elementRemark[index]"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button
                type="text"
                @click="removeElementRemark(item)"
              >删除</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item  label="要求" prop="elementRequirements">
          <el-row>
            <el-col :span="8">
              <el-input v-model="newRequirementName" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line">--</el-col>
            <el-col :span="8">
              <el-input v-model="newRequirementValue" placeholder="内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="addRequirement">添加</el-button>
            </el-col>
          </el-row>
          <el-row v-for="(value,name,index) in element.elementRequirements"
                  :key="name"
                  :gutter="20"
          >
            <el-col :span="6">
              <el-input v-model="name" placeholder="名称" :disabled="true"></el-input>
            </el-col>
            <el-col :span="2" class="line">----</el-col>
            <el-col :span="6">
              <el-input v-model="element.elementRequirements[name]" placeholder="内容" ></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="text" @click.prevent="removeRequirement(name)">删除</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item  label="其他属性" prop="elementOtherProperties">
          <el-row>
            <el-col :span="8">
              <el-input v-model="newPropertyName" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line">--</el-col>
            <el-col :span="8">
              <el-input v-model="newPropertyValue" placeholder="内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="addProperty">添加</el-button>
            </el-col>
          </el-row>
          <el-row v-for="(value,name,index) in element.elementOtherProperties"
                  :key="name"
                  :gutter="20"
          >
            <el-col :span="6">
              <el-input v-model="name" placeholder="名称" :disabled="true"></el-input>
            </el-col>
            <el-col :span="2" class="line">----</el-col>
            <el-col :span="6">
              <el-input v-model="element.elementOtherProperties[name]" placeholder="内容" ></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="text" @click.prevent="removeProperty(name)">删除</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>

</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as processManagement from '@/api/system/processManagement'
import * as treeManagement from '@/api/system/treeManagement'
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import * as elementManagement from '@/api/system/elementManagement'
import { getParentElementById, getSingleElementById } from '@/api/system/processManagement'
export default {
  name: 'modifyElement',
  components: { Treeselect },
  props:{
    selectElementId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    proId: {
      type: Number,
      default: 0
    },
    pId:{
      type: Number,
      default: 0
    },
    title:{
      type: String,
      default: ""
    }
  },
  watch: {
    selectElementId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.elementId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    pId:{
      handler(newVal, oldVal) {
        this.parentId = newVal
      }
    },
    proId: {
      handler(newVal, oldVal) {
        this.processId = newVal
      }
    },
    title: {
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },
  data(){
    return{
      dialogFormVisible:false,
      windowTitle:'',
      elementId:0,
      element:{
        elementId:undefined,
        elementName:'',
        elementDescription:'',
        elementNumber:'',
        elementRemark:[],
        elementRequirements:{},
        elementOtherProperties:{},
        hasSubElements:false,
        dynamicLabels:[],
      },
      elementType:'',
      isTypical: false,
      dynamicLabels:[],
      labelOptions:[],
      parentId:0,
      processId:0,
      elementOptions:[],
      newRequirementName:'',
      newRequirementValue:'',
      newPropertyName:'',
      newPropertyValue:'',
    }
  },
  methods:{
    handleOpen(){
      treeManagement.getTreeManagement(25500).then(result =>{
        this.labelOptions = []
        this.labelOptions.push(result.data) ;
      })

      if (this.windowTitle === '创建元素'){
        if (this.parentId !== 0 && this.parentId !== undefined) {
          this.elementType = 'Sequence'
          processManagement.getElementById(this.processId).then(result =>{
            this.elementOptions = []
            this.elementOptions.push(result.data)
          })
        } else {
          this.elementType = 'Process'
        }
      }
      else {
        // processManagement.getSingleElementById(this.elementId).then(result =>{
        //   if(result.code === 200){
        //     this.element = result.data
        //   }
        // })
        if (this.processId !== 0 ||this.processId !== undefined) {
          processManagement.getElementById(this.processId).then(result => {
            if (result.code === 200) {
              this.elementOptions = []
              this.elementOptions.push(result.data)
            }
          })
        }
        processManagement.getSingleElementById(this.elementId).then(result =>{
          if (result.code === 200) {
            this.element = result.data
            this.dynamicLabels = this.element.dynamicLabels
            console.log(this.element)
            if (this.dynamicLabels.includes('TypicalElement')) {
              this.isTypical = true
              this.dynamicLabels = this.element.dynamicLabels.filter(item => item !== 'TypicalElement')
            }
            if (this.dynamicLabels.includes('Process')) {
              this.elementType = 'Process'
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Process')
            } else if (this.dynamicLabels.includes('Sequence')) {
              this.elementType = 'Sequence'
              processManagement.getParentElementById(this.element.elementId).then(result => {
                this.parentId = result.data.elementId
              })
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Sequence')
            } else if (this.dynamicLabels.includes('Step')) {
              this.elementType = 'Step'
              processManagement.getParentElementById(this.element.elementId).then(result => {
                this.parentId = result.data.elementId
              })
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Step')
            }
          }
        })

      }
    },
    handleClose(){
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    labelNormalizer(node){
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs;
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    elementNormalizer(node){
      if (node.subElements && !node.subElements.length) {
        delete node.subElements;
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    addElementRemark(){
      this.element.elementRemark.push('')
    },
    removeElementRemark(item){
      let index = this.element.elementRemark.indexOf(item)
      if (index !== -1) {
        this.element.elementRemark.splice(index, 1)
      }
    },
    addRequirement(){
      this.$set(this.element.elementRequirements,this.newRequirementName,this.newRequirementValue)
    },
    removeRequirement(name){
      this.$delete(this.element.elementRequirements,name)
    },
    addProperty(){
      this.$set(this.element.elementOtherProperties,this.newPropertyName,this.newPropertyValue)
    },
    removeProperty(name){
      this.$delete(this.element.elementOtherProperties,name)
    },
    submitForm(){
      if (this.isTypical === true) {
        this.element.dynamicLabels.push('TypicalElement')
      }
      this.element.dynamicLabels.push(this.elementType)
      this.element.dynamicLabels = this.element.dynamicLabels.concat(this.dynamicLabels)
      if(this.windowTitle === "创建元素" ){
        let data = {
          parentId: this.parentId,
          originElement: this.element
        }
        processManagement.createElement(data).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else {
        let data = {
          parentId: this.parentId,
          originElement: this.element
        }
        processManagement.updateElement(data).then(result => {
          if (result.code === 200) {
            this.element = result.data
            this.$modal.msgSuccess('修改成功！')
          }
        })
      }
      this.loading = false
    },
    cancel(){
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.element = {
        elementId:undefined,
        elementName:'',
        elementDescription:'',
        elementNumber:'',
        elementRemark:[],
        elementRequirements:{},
        elementOtherProperties:{},
        hasSubElements:false,
        dynamicLabels:[],
      }
      this.elementType = ''
      this.parentId = 0
      this.elementId = 0
      this.files = []
      this.model = {}
      this.isTypical = false
      this.dynamicLabels = []
      this.resetForm('form')
    },
  },
}
</script>

<style scoped>

</style>

