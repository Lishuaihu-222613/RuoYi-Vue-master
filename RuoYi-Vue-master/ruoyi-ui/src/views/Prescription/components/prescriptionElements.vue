<template>
  <el-dialog :visible.sync="dialogFormVisible" title="配方组分编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="elements" ref="form">
      <el-form-item v-for="(item,index) in elements"
                    :label-width="formLabelWidth"
                    :label="'组分'+index+1"
      >
        <el-row>
          <el-col>
            <el-select v-model="item.material.materialId" filterable placeholder="请选择材料">
              <el-option
                v-for="option in materialOptions"
                :key="option.materialId"
                :label="option.materialName"
                :value="option.materialId">
              </el-option>
            </el-select>
          </el-col>
          <el-col>
            <el-slider
              v-model="item.percentage"
              show-input>
            </el-slider>
          </el-col>
          <el-col>
            <el-button @click="removeElement(item)">删 除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addElement">增 加</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as materialManagement from '@/api/system/materialManagement'
import * as treeManagement from '@/api/system/treeManagement'

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
      prescriptionId:0,
      windowTitle:"创建组分",
      elements:[],
      dialogFormVisible: false,
      formLabelWidth: '120px',
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

    handleOpen() {
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.elements = result.data
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
      this.elements = [{}]
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addElement() {
      this.elements.push({});
    },
    removeElement(item) {
      let index = this.elements.indexOf(item)
      if (index !== -1) {
        this.elements.splice(index, 1)
      }
    },

    onSubmit(){
      let data = {
        prescriptionId:this.prescriptionId,
        elements:this.elements,
      }
      prescriptionManagement.modifyElements(data).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("配方组分修改成功！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
