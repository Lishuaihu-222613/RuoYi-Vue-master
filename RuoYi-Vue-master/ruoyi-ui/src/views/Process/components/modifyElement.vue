<template>
  <!-- 添加或修改工艺元素配置对话框 -->
  <el-dialog :visible.sync="dialogFormVisible" :title="windowTitle" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen">
    <el-form ref="form" :model="element" :rules="rules" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="元素序号" prop="elementNumber">
            <el-input v-model="element.elementNumber" placeholder="请输入元素序号" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="元素名称" prop="elementName">
            <el-input v-model="element.elementName" placeholder="请输入元素名称" maxlength="30"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="元素描述" prop="elementDescription">
          <el-input v-model="element.elementDescription" placeholder="请输入元素描述" type="textarea" autosize />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="元素类别">
            <el-radio-group v-model="elementType">
              <el-radio :label="'Process'">工 艺</el-radio>
              <el-radio :label="'Sequence'">工 序</el-radio>
              <el-radio :label="'Step'">工 步</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="元素标签">
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
        <el-col :span="12">
          <el-form-item label="是否包含子元素">
            <el-switch
              v-model="element.hasSubElements"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="12" v-if="!(elementType === 'AssemblyProduct')">
          <el-form-item label="父元素">
            <treeselect v-model="parentId"
                        :clearable="true"
                        :searchable="true"
                        :normalizer="elementNormalizer"
                        :options="elementOptions"
                        placeholder="请选择父元素"
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
export default {
  name: 'modifyElement',
  components: { Treeselect },
  props:{
    selectElementId: {
      type: Number,
      default: 0
    },
    selectElement:{
      type:Object,
      default:{}
    },
    dialog: {
      type: Boolean,
      default: false
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
    selectElement: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.element = newVal
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
      dynamicLabels:[],
      labelOptions:[],
      parentId:undefined,
      elementOptions:[],
      newRequirementName:'',
      newRequirementValue:'',
      newPropertyName:'',
      newPropertyValue:'',
    }
  },
  methods:{
    handleOpen(){
      treeManagement.getTreeManagement(22223).then(result =>{
        this.labelOptions = result.data;
      })
      processManagement.getElementById(this.elementId).then(result =>{
        if(result.code === 200){
          this.element = result.data
        }
      })
      if(this.element.dynamicLabels.includes('Process')){
        this.elementType = 'Process';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'Process')
      } else if(this.element.dynamicLabels.includes('Sequence')){
        this.elementType = 'Sequence';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'Sequence')
      } else if(this.element.dynamicLabels.includes('Step')){
        this.elementType = 'Step';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'Step')
      }
    },
    handleClose(){
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    labelNormalizer(node){
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    elementNormalizer(node){
      if (node.children && !node.children.length) {
        delete node.children;
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
      if(this.windowTitle === "创建元素" && this.elementType === 'Process'){
        let newLabels = this.dynamicLabels.concat();
        newLabels.unshift(this.elementType);
        this.element.dynamicLabels = newLabels;
        processManagement.createElement(this.element).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      }
      else if(this.windowTitle === "创建元素" && this.elementType !== 'Process'){
        let newLabels = this.dynamicLabels.concat();
        newLabels.unshift(this.elementType);
        this.element.dynamicLabels = newLabels;
        let data = {
          parentId:this.parentId,
          element:this.element,
        }
        process.createElementForParent(this.data).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      }
      else if(this.windowTitle === "推荐信息修改"){
        this.$emit('changeElement', this.element);
      }
      else{
        processManagement.updateElement(this.element).then(result =>{
          let newLabels = this.dynamicLabels.concat();
          newLabels.unshift(this.elementType);
          this.element.dynamicLabels = newLabels;
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("修改成功！")
          }
        })
      }
    },
    cancel(){
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.element = {}
      this.resetForm('form')
    },
  },
}
</script>

<style scoped>

</style>

