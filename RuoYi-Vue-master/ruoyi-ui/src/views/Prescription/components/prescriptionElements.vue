<template>
  <el-dialog :visible.sync="dialogFormVisible" title="配方组分编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="配方组分">
        <el-form ref="form">
          <el-form-item v-for="(item,index) in elements"
                        :label-width="formLabelWidth"
          >
            <template #label>
              {{'组分'+(index+1)}}
            </template>
            <el-row>
              <el-col :span="8">
                <el-select v-model="item.materialId" disabled  placeholder="请选择材料">
                  <el-option
                    v-for="option in materialOptions"
                    :key="option.materialId"
                    :label="option.materialName"
                    :value="option.materialId">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">-----</el-col>
              <el-col :span="8">
                <el-input-number v-model="item.percentage" controls-position="right" :precision="2"></el-input-number>
              </el-col>
              <el-col :span="4">
                <el-button @click="removeMaterialElement(item)">删 除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="新增组分">
            <el-row>
              <el-col :span="6">
                <el-select v-model="materialLabel" filterable placeholder="请选择分类"
                           @change="changeMaterialLabel"
                >
                  <el-option
                    v-for="option in materialLabelOptions"
                    :key="option.leafId"
                    :label="option.leafName"
                    :value="option.leafName">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="2">-</el-col>
              <el-col :span="6">
                <el-select v-model="newMaterialId" filterable placeholder="请选择材料">
                  <el-option
                    v-for="option in filterMaterialOptions"
                    :key="option.materialId"
                    :label="option.materialName"
                    :value="option.materialId">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">----</el-col>
              <el-col :span="6">
                <el-input-number v-model="newMaterialPercentage" controls-position="right" :precision="2"></el-input-number>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addMaterialElement">增 加</el-button>
          <el-button type="primary" @click="onSubmitMaterial">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="关联产品">
        <el-form ref="productForm">
          <el-form-item v-for="(item,index) in products"
                        :label-width="formLabelWidth"
          >
            <template #label>
              {{'产品'+(index+1)}}
            </template>
            <el-row>
              <el-col :span="20">
                <el-select v-model="item.elementId" disabled placeholder="请选择产品">
                  <el-option
                    v-for="option in productOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.elementId">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click="removeProductElement(item)">删 除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="新增产品">
            <el-row>
              <el-col :span="10">
                <el-select v-model="productLabel" filterable placeholder="请选择分类"
                           @change="changeProductLabel"
                >
                  <el-option
                    v-for="option in productLabelOptions"
                    :key="option.leafId"
                    :label="option.leafName"
                    :value="option.leafName">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">-----</el-col>
              <el-col :span="10">
                <el-select v-model="newProductId" filterable placeholder="请选择产品">
                  <el-option
                    v-for="option in filterProductOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.elementId">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addProduct">增 加</el-button>
          <el-button type="primary" @click="onSubmitProduct">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="关联工艺">
        <el-form ref="processForm">
          <el-form-item v-for="(item,index) in processes"
                        :label-width="formLabelWidth"
          >
            <template #label>
              {{'工艺'+(index+1)}}
            </template>
            <el-row>
              <el-col :span="20">
                <el-select v-model="item.elementId" disabled  placeholder="请选择工艺">
                  <el-option
                    v-for="option in processOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.elementId">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click="removeProcessElement(item)">删 除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="新增工艺">
            <el-row>
              <el-col :span="10">
                <el-select v-model="processLabel" filterable placeholder="请选择分类"
                           @change="changeProcessLabel"
                >
                  <el-option
                    v-for="option in processLabelOptions"
                    :key="option.leafId"
                    :label="option.leafName"
                    :value="option.leafName">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">-----</el-col>
              <el-col :span="10">
                <el-select v-model="newProcessId" filterable placeholder="请选择工艺">
                  <el-option
                    v-for="option in filterProcessOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.elementId">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addProcess">增 加</el-button>
          <el-button type="primary" @click="onSubmitProcess">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as materialManagement from '@/api/system/materialManagement'
import * as structureManagement from '@/api/system/elementManagement'
import * as processManagement from '@/api/system/processManagement'
import { getAllProducts } from '@/api/system/elementManagement'
import { getAllProcess } from '@/api/system/processManagement'


export default {
  name: 'prescriptionElements',
  props: {
    selectPrescriptionId: {
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
    selectPrescriptionId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.prescriptionId = newVal;
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
    this.getMaterialOptions();
  },

  data() {
    return {
      // 遮罩层
      loading: true,
      // 知识树管理树选项
      options: [],
      materialOptions:[],
      productOptions:[],
      processOptions:[],
      tabPosition:"left",
      prescriptionId:0,
      materialLabel:'',
      productLabel:'',
      processLabel:'',
      materialLabelOptions:[],
      productLabelOptions:[],
      processLabelOptions:[],
      newMaterialId:undefined,
      newProductId:undefined,
      newProcessId:undefined,
      filterMaterialOptions:[],
      filterProductOptions:[],
      filterProcessOptions:[],
      newMaterialPercentage:0.0,
      windowTitle:"创建组分",
      elements:[{
        percentage:0.0,
        materialId:0,
      }],
      products:[],
      processes:[],
      dialogFormVisible: false,
      formLabelWidth: '100px',
      inputVisible: false,
      inputValue: '',
      selectLabel:''
    }
  },
  methods: {

    getMaterialOptions(){
      materialManagement.getAllMaterialOptions().then(result =>{
        if(result.code === 200){
          this.materialOptions = result.data
        }
      })
    },

    getProductOptions(){
      structureManagement.getAllProducts().then(result =>{
        if(result.code === 200){
          this.productOptions = result.data
        }
      })
    },

    getProcessOptions(){
      processManagement.getAllProcess().then(result =>{
        if(result.code === 200){
          this.processOptions = result.data
        }
      })
    },
    changeMaterialLabel(value){
      materialManagement.getMaterialOptionsByLabel(value).then(result =>{
        if(result.code === 200){
          this.filterMaterialOptions = []
          this.filterMaterialOptions.push(result.data)
        }
      })
    },
    changeProductLabel(value){
      structureManagement.getProductOptionsByLabel(value).then(result =>{
        if(result.code === 200){
          this.filterProductOptions = []
          this.filterProductOptions.push(result.data)
        }
      })
    },
    changeProcessLabel(value){
      processManagement.getProcessOptionsByLabel(value).then(result =>{
        if(result.code === 200){
          this.filterProcessOptions = []
          this.filterProcessOptions.push(result.data)
        }
      })
    },
    handleOpen() {
      this.getMaterialOptions();
      this.getProductOptions();
      this.getProcessOptions();

      console.log(this.prescriptionId)
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.elements = []
          this.elements.push(result.data)
        }
      })
      prescriptionManagement.getProductsByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.products = []
          this.products.push(result.data)
        }
      })
      prescriptionManagement.getProcessByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.processes = []
          this.processes.push(result.data)
        }
      })
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },

    // 表单重置
    reset() {
      this.elements = []
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addMaterialElement() {
      this.elements.push({
        materialId: this.newMaterialId,
        percentage: this.newMaterialPercentage
      });
      this.newMaterialId = undefined
      this.newMaterialPercentage = 0.0
    },
    removeMaterialElement(item) {
      let index = this.elements.indexOf(item)
      if (index !== -1) {
        this.elements.splice(index, 1)
      }
    },
    addProduct() {
      this.products.push(this.newProductId);
      this.newProductId = undefined
    },
    removeProductElement(item) {
      let index = this.products.indexOf(item)
      if (index !== -1) {
        this.products.splice(index, 1)
      }
    },
    addProcess() {
      this.processes.push(this.newProcessId);
      this.newProcessId = undefined
    },
    removeProcessElement(item) {
      let index = this.processes.indexOf(item)
      if (index !== -1) {
        this.processes.splice(index, 1)
      }
    },
    onSubmitMaterial(){
      let data = {
        prescriptionId:this.prescriptionId,
        elements:this.elements,
      }
      console.log(data)
      prescriptionManagement.modifyElements(data).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("配方组分修改成功！")
        }
      })
    },
    onSubmitProduct(){
      let data = {
        prescriptionId:this.prescriptionId,
        products:this.products,
      }
      console.log(data)
      prescriptionManagement.modifyProducts(data).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("关联产品修改成功！")
        }
      })
    },
    onSubmitProcess(){
      let data = {
        prescriptionId:this.prescriptionId,
        processes:this.processes,
      }
      console.log(data)
      prescriptionManagement.modifyProcesses(data).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("关联工艺修改成功！")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
