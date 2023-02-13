<template>
  <el-dialog :visible.sync="dialogFormVisible" title="危险与注意事项" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-table :data="dangers">
      <el-table-column label="危险事项"
                       prop="dangerName"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.dangerName" placeholder="危险事项"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="危险描述"
                       prop="dangerDescription"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.dangerDescription" placeholder="危险事项" type="textarea"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            circle icon="el-icon-circle-plus-outline" type="primary"
            @click="addDanger(scope.row)"
          ></el-button>
          <el-button
            circle icon="el-icon-delete" type="danger"
            @click="removeDanger(scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-divider direction="vertical"></el-divider>
    <el-table :data="protections">
      <el-table-column label="注意事项"
                       prop="protectionName"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.protectionName" placeholder="注意事项"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="具体描述"
                       prop="protectionDescription"
      >
        <template v-slot="scope">
          <el-input v-model="scope.row.protectionDescription" placeholder="具体描述" type="textarea"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            circle icon="el-icon-circle-plus-outline" type="primary"
            @click="addProtection(scope.row)"
          ></el-button>
          <el-button
            circle icon="el-icon-delete" type="danger"
            @click="removeProtection(scope.row)"
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
  name: 'DangerAndProtection',

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
    dangers: [
      {
        dangerId: 0,
        dangerName: '',
        dangerDescription: ''
      }
    ],
    protections: [
      {
        protectionId: 0,
        protectionName: '',
        protectionDescription: ''
      }
    ]
  }),
  methods: {
    handleOpen() {
      materialManagement.getDangersByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          this.dangers = result.data
        }
      })
      materialManagement.getProtectionsByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          this.protections = result.data
        }
      })
    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    addDanger() {
      let item = {
        dangerId: 0,
        dangerName: '',
        dangerDescription: ''
      }
      materialManagement.createDanger(this.materialId).then(result => {
        if (result.code != 200) {
          this.$modal.alertError('创建失败')
        }
        item.dangerId = result.data.dangerId
      })
      this.dangers.push(item)
    },
    removeDanger(item) {
      let index = this.dangers.indexOf(item)
      if (index !== -1) {
        this.dangers.splice(index, 1)
        console.log(item.dangerId)
        materialManagement.deleteDangerById(item.dangerId).then(result => {
          if (result.code != 200) {
            this.$modal.alertError('删除失败')
          }
        })
      }
    },
    addProtection() {
      let item = {
        protectionId: 0,
        protectionName: '',
        protectionDescription: ''
      }
      this.protections.push(item)
      materialManagement.createProtection(this.materialId).then(result => {
        if (result.code != 200) {
          this.$modal.msgSuccess('创建失败')
        }
        item.protectionId = result.data.dangerId
      })
      this.protections.push(item)
    },
    removeProtection(item) {
      let index = this.protections.indexOf(item)
      if (index !== -1) {
        this.protections.splice(index, 1)
        materialManagement.deleteProtectionById(item.protectionId).then(result => {
          if (result.code == 200) {
            this.$modal.msgSuccess('删除成功')
          }
        })
      }
    },
    onSubmit() {
      this.dangers.forEach((danger, index, array) => {
        materialManagement.updateDanger(danger).then(result => {
          if (result.code == 200) {
            danger = result.data
          }
        })
      })
      this.protections.forEach((protection, index, array) => {
        materialManagement.updateProtection(protection).then(result => {
          if (result.code == 200) {
            protection = result.data
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
