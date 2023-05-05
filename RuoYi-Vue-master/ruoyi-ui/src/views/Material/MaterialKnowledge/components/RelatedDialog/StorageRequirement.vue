<template>
  <el-dialog :visible.sync="dialogFormVisible" title="存储要求" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-table :data="storageRequirements">
      <el-table-column label="存储要求"
                       prop="requirementName"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.requirementName" placeholder="存储要求"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="要求描述"
                       prop="requirementDescription"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.requirementDescription" placeholder="要求描述" type="textarea"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            circle icon="el-icon-circle-plus-outline" type="primary"
            @click="addRequirement(scope.row)"
          ></el-button>
          <el-button
            circle icon="el-icon-delete" type="danger"
            @click="removeRequirement(scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

import * as materialManagement from '@/api/system/materialManagement'
export default {
  name: 'StorageRequirement',

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

  mounted() {

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
    storageRequirements: [
      {
        requirementId: 0,
        requirementName: '',
        requirementDescription: ''
      }
    ]
  }),
  methods: {
    handleOpen() {
      materialManagement.getStorageRequirementsByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          this.storageRequirements = result.data
        }
      })
    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    addRequirement() {
      let item = {
        requirementId: 0,
        requirementName: '',
        requirementDescription: ''
      }
      materialManagement.createStorageRequirement(this.materialId).then(result => {
        if (result.code != 200) {
          this.$modal.alertError('创建失败')
        }
        item.requirementId = result.data.requirementId
      })
      this.storageRequirements.push(item)
    },
    removeRequirement(item) {
      let index = this.storageRequirements.indexOf(item)
      if (index !== -1) {
        this.storageRequirements.splice(index, 1)
        materialManagement.deleteStorageRequirementById(item.requirementId).then(result => {
          if (result.code != 200) {
            this.$modal.alertError('删除失败')
          }
        })
      }
    },
    onSubmit() {
      this.dangers.forEach((requirement, index, array) => {
        materialManagement.updateStorageRequirement(requirement).then(result => {
          if (result.code == 200) {
            requirement = result.data
          }
        })
      })
      this.$modal.msgSuccess('修改成功！')
    }
  }
}
</script>

<style scoped>

</style>
