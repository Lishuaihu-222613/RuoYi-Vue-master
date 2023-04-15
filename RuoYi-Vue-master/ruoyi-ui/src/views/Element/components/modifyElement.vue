<template>
  <!-- 添加或修改产品配置对话框 -->
  <el-dialog :visible.sync="dialogFormVisible" :title="windowTitle" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen">
    <el-form ref="form" :model="element" :rules="rules" label-width="80px">
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
          <el-form-item  label="元素密度" prop="elementDensity">
            <el-input-number v-model="element.elementDensity" placeholder="请输入元素密度" :precision="2" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item  label="元素质量" prop="elementMass">
            <el-input-number v-model="element.elementMass" placeholder="请输入元素质量" :precision="2"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item  label="元素体积" prop="elementVolume">
            <el-input v-model="element.elementVolume" placeholder="请输入元素体积" :precision="2"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item  label="元素表面积" prop="elementWetArea">
            <el-input v-model="element.elementWetArea" placeholder="请输入元素表面积" :precision="2"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
          <el-form-item  label="元素包容盒" prop="elementBoundingBox">
            <el-input v-model="element.elementBoundingBox" placeholder="请输入元素包容盒" maxlength="30" />
          </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="元素来源">
            <el-radio-group v-model="element.elementSource">
              <el-radio :label="'自制'">自 制</el-radio>
              <el-radio :label="'外源'">外 源</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否包含子元素">
            <el-switch
              v-model="element.hasSubElements"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="元素类别">
            <el-radio-group v-model="elementType">
              <el-radio :label="'AssemblyProduct'">产 品</el-radio>
              <el-radio :label="'AssemblyComponent'">组 件</el-radio>
              <el-radio :label="'AssemblyPart'">零 件</el-radio>
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
export default {
  name: 'modifyStructure',
  components: { Treeselect },
  props:{
    selectElement: {
      type: Object,
      default: {}
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
      parentId:undefined,
      elementOptions:[],
    }
  },
  methods:{
    handleOpen(){
      treeManagement.getTreeManagement(22223).then(result =>{
        this.labelOptions = result.data;
      })
      if(this.element.dynamicLabels.includes('AssemblyProduct')){
        this.elementType = 'AssemblyProduct';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyProduct')
      } else if(this.element.dynamicLabels.includes('AssemblyComponent')){
        this.elementType = 'AssemblyComponent';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyComponent')
      } else if(this.element.dynamicLabels.includes('AssemblyPart')){
        this.elementType = 'AssemblyPart';
        this.dynamicLabels  = this.element.dynamicLabels.filter(item => item !== 'AssemblyComponent')
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
        id: node.leafId,
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
    submitForm(){
      if(this.windowTitle === "创建元素" && this.elementType === 'AssemblyProduct'){
        let newLabels = this.dynamicLabels.concat();
        newLabels.unshift(this.elementType);
        this.element.dynamicLabels = newLabels;
        elementManagement.createElement(this.element).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else if(this.windowTitle === "创建元素" && this.elementType !== 'AssemblyProduct'){
        let newLabels = this.dynamicLabels.concat();
        newLabels.unshift(this.elementType);
        this.element.dynamicLabels = newLabels;
        let data = {
          parentId:this.parentId,
          element:this.element,
        }
        elementManagement.createElementForParent(this.data).then(result =>{
          if(result.code === 200){
            this.element = result.data;
            this.$modal.msgSuccess("创建成功！")
          }
        })
      } else{
        elementManagement.updateElement(this.element).then(result =>{
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
