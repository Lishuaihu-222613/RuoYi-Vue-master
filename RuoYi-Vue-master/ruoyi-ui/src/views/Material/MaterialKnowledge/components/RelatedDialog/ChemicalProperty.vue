<template>
  <el-dialog :visible.sync="dialogFormVisible" title="材料化学性质" @closed="handleClose" @open="handleOpen">
    <el-form :model="chemicalProperty">
      <el-form-item :label-width="formLabelWidth" label="节点Id">
        <el-input v-model="chemicalProperty.propertyId" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="节点名称">
        <el-input v-model="chemicalProperty.propertyName"></el-input>
      </el-form-item>
      <el-form-item v-for="(value, key) in chemicalProperty.propertyAttributes" :label-width="formLabelWidth" :key="key">
        <el-row>
          <el-input style="display: inline-block;float: right;" v-model="key"></el-input>
          <el-input type="textarea" v-model="chemicalProperty.propertyAttributes[key]"></el-input>
          <el-button @click.prevent="deleteAttr(key)">删 除</el-button>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click.prevent="addAttr">新增性质</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import * as materialManagement from '@/api/system/materialManagement'

export default {
  name: 'ChemicalProperty',
  props: {
    selectId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    selectId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.materialId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    }
  },
  data: () => ({
    materialId: 0,
    dialogFormVisible: false,
    formLabelWidth: '120px',
    chemicalProperty: {
      propertyId: '',
      propertyName: '',
      propertyAttributes: new Map()
    },
  }),
  methods: {
    handleOpen() {
      console.log(this.materialId)
      materialManagement.getChemicalPropertyByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          console.log(result.data)
          this.chemicalProperty = result.data
        }
      })
    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    addAttr(){
      this.chemicalProperty.propertyAttributes.push("","")
    },
    deleteAttr(key){
      this.chemicalProperty.propertyAttributes.delete(key);
    },
    onSubmit() {
      if (this.chemicalProperty.propertyName !== '') {
        materialManagement.updateChemicalProperty(this.chemicalProperty).then(result => {
          if (result.code == 200) {
            alert('创建成功！')
          } else {
            alert('创建失败！')
          }
        })
      }
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>

</style>
