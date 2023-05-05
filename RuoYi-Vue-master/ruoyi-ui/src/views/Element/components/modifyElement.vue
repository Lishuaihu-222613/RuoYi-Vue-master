<template>
  <!-- 添加或修改产品配置对话框 -->
  <el-dialog :visible.sync="dialogFormVisible" :title="windowTitle" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen">
    <el-form ref="form" :model="element" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="元素名称" prop="elementName">
            <el-input v-model="element.elementName" placeholder="请输入元素名称" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="元素数量" prop="elementQuantity">
            <el-input-number v-model="element.elementQuantity" controls-position="right" :min="1" :max="10"></el-input-number>
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
          <el-form-item  label="密度" prop="elementDensity">
            <el-input-number v-model="element.elementDensity" placeholder="请输入元素密度" :precision="2" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item  label="质量" prop="elementMass">
            <el-input-number v-model="element.elementMass" placeholder="请输入元素质量" :precision="2"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item  label="体积" prop="elementVolume">
            <el-input-number v-model="element.elementVolume" placeholder="请输入元素体积" :precision="2"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item  label="表面积" prop="elementWetArea">
            <el-input-number v-model="element.elementWetArea" placeholder="请输入元素表面积" :precision="2"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
          <el-form-item  label="包容盒" prop="elementBoundingBox">
            <el-input v-model="element.elementBoundingBox" placeholder="请输入元素包容盒" maxlength="30" />
          </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="来源">
            <el-radio-group v-model="element.elementSource">
              <el-radio :label="'自制'">自制</el-radio>
              <el-radio :label="'外源'">外源</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="包含子元素">
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
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="类别">
            <el-radio-group v-model="elementType">
              <el-radio :label="'AssemblyProduct'">产 品</el-radio>
              <el-radio :label="'AssemblyComponent'">组 件</el-radio>
              <el-radio :label="'AssemblyPart'">零 件</el-radio>
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
              >
                <div slot="value-label" slot-scope="{ node }">{{ node.raw.leafValue}}</div>
              </treeselect>
            </el-row>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="!(elementType === 'AssemblyProduct')">
          <el-form-item label="父元素">
            <treeselect v-model="parentId"
                        :clearable="true"
                        :searchable="true"
                        :normalizer="elementNormalizer"
                        :options="elementOptions"
                        placeholder="请选择父元素"
            />
          </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="模型文件">
            <el-select v-model="modelFileId" placeholder="请选择模型文件">
              <el-option
                v-for="item in modelOptions"
                :key="item.fileId"
                :label="item.fileName"
                :value="item.fileId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联文件">
            <el-select v-model="associatedFileId" filterable placeholder="请选择关联文件" multiple>
              <el-option
                v-for="item in fileOptions"
                :key="item.fileId"
                :label="item.fileName"
                :value="item.fileId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
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
import * as elementManagement from '@/api/system/elementManagement'
import * as treeManagement from '@/api/system/treeManagement'
import * as fileManagement from '@/api/system/fileManagement'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

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
    proId:{
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
    proId:{
      handler(newVal, oldVal) {
        this.productId = newVal
      }
    },
    title:{
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },
  data(){
    return{
      dialogFormVisible:false,
      windowTitle:'创建元素',
      element:{
        elementId:0,
        elementName:'',
        elementDescription:'',
        elementQuantity:0,
        elementDensity:0,
        elementMass:0,
        elementVolume:0,
        elementWetArea:0,
        elementBoundingBox:'',
        elementSource:'自制',
        hasSubElements:false,
        dynamicLabels:[],
      },
      elementType:'',
      dynamicLabels:[],
      labelOptions:[],
      parentId: 0,
      productId:0,
      modelFileId:undefined,
      modelOptions:[],
      associatedFileId:[],
      fileOptions:[],
      elementOptions:[],
      elementId:0,
      isTypical:false,
    }
  },
  methods:{
    handleOpen(){
      treeManagement.getTreeManagement(25500).then(result =>{
        this.labelOptions = []
        this.labelOptions.push(result.data);
      })
      if(this.windowTitle === "创建元素"){
        if(this.parentId !== 0){
          this.elementType = 'AssemblyComponent'
          elementManagement.getProductById(this.productId).then(result =>{
            this.elementOptions = [];
            this.elementOptions.push(result.data)
          })
        } else {
          this.elementType = 'AssemblyProduct'
        }
      }
      else {
        elementManagement.getElementById(this.elementId).then(result =>{
          if(result.code === 200){
            console.log("455555")
            this.element = result.data;
            console.log(this.element)
          }
        })
        elementManagement.getProductById(this.productId).then(result =>{
          this.elementOptions = [];
          this.elementOptions.push(result.data)
        })
        if(this.element.dynamicLabels.includes('TypicalElement')){
          this.isTypical = true
        }
        if(this.element.dynamicLabels.includes('AssemblyProduct')){
          this.elementType = 'AssemblyProduct';
          this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyProduct'||'TypicalElement')
        } else if(this.element.dynamicLabels.includes('AssemblyComponent')){
          this.elementType = 'AssemblyComponent';
          elementManagement.getParentElement(this.element.elementId).then(result =>{
            this.parentId = result.data.elementId
          } )
          this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyComponent'||'TypicalElement')
        } else if(this.element.dynamicLabels.includes('AssemblyPart')){
          this.elementType = 'AssemblyPart';
          elementManagement.getParentElement(this.element.elementId).then(result =>{
            this.parentId = result.data.elementId
          } )
          this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyComponent'||'TypicalElement')
        }
      }
      // fileManagement.getModelList().then(result =>{
      //   if(result.code === 200){
      //     this.modelOptions = result.data
      //   }
      // })
      // fileManagement.getFileList().then(result =>{
      //   if(result.code === 200){
      //     this.fileOptions = result.data
      //   }
      // })
      // fileManagement.getModelFileByStructure(this.element.elementId).then(result =>{
      //   if(result.code === 200){
      //     this.modelFileId = result.data.fileId
      //   }
      // })
      // fileManagement.getFilesByStructure().then(result =>{
      //   if(result.code === 200){
      //     this.associatedFileId = result.data.map(item =>{
      //       return item.fileId
      //     })
      //   }
      // })
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
        id: node.leafId,
        label: node.leafName,
        value:node.leafValue,
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
    submitForm(){
      if(this.isTypical === true){
        this.element.dynamicLabels.push("TypicalElement")
      }
      if(this.windowTitle === "创建元素" ){
        this.element.dynamicLabels.push(this.elementType)
        let data = {
          parentId:this.parentId,
          originElement:this.element,
        }
        elementManagement.createElement(data).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else{
        let data = {
          parentId:this.parentId,
          originElement:this.element,
        }
        elementManagement.updateElement(data).then(result =>{
          let newLabels = this.dynamicLabels.concat();
          newLabels.unshift(this.elementType);
          this.element.dynamicLabels = newLabels;
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("修改成功！")
          }
        })
      }
      let files = {
        elementId : this.element.elementId,
        modelFileId: this.modelFileId,
        associatedFiles: this.associatedFileId
      }
      elementManagement.modifyFiles(files).then(result =>{
        if(result.code === 200){
          this.loading = false
        }
      })
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
