<template>
  <el-dialog :visible.sync="dialogFormVisible" title="材料生产方法" top="50vh" width="75%" @closed="handleClose"
             @open="handleOpen"
  >
    <el-tabs v-model="currentProject" editable type="card" @edit="handleTabsEdit">
      <el-tab-pane
        v-for="(item, index) in produceMethods"
        :key="item.methodId"
        :label="item.methodName"
        :name="item.methodId.toString()"
      >
        <el-form :model="item">
          <el-form-item :label-width="formLabelWidth" label="生产方法Id">
            <el-input v-model="item.methodId" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="生产方法名称">
            <el-input v-model="item.methodName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="生产方法描述">
            <el-input v-model="item.methodDescription" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item v-for="(value, stepIndex) in item.methodSteps" :key="stepIndex"
                        :label-width="formLabelWidth"
                        label="生产步骤"
          >
            <el-row>
              <el-input v-model="item.methodSteps[stepIndex]" type="textarea"></el-input>
              <el-button circle icon="el-icon-remove" @click.prevent="deleteStep(item,stepIndex)"></el-button>
              <el-button circle icon="el-icon-circle-plus" @click.prevent="addStep(item)"></el-button>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="关联文件">
            <el-button @click.prevent="getAssociatedFiles(item.methodId)">查看</el-button>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关工艺">
            <el-button @click.prevent="getAssociatedProcesses(item.methodId)">查看</el-button>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关质量问题">
            <el-button @click.prevent="getAssociatedProblems(item.methodId)">查看</el-button>
          </el-form-item>
          <el-form-item >
            <el-row>
              <el-col :span="4" :offset="20">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>

</template>

<script>
import * as materialManagement from '@/api/system/materialManagement'

export default {

  name: 'ProduceMethod',

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
    currentProject: '',
    tabIndex: 0,
    produceMethods: [{
      methodId: 0,
      methodName: '',
      methodDescription: '',
      methodSteps: []
    }]
  }),

  methods: {
    handleOpen() {
      materialManagement.getProduceMethodsByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          this.produceMethods = result.data
        }
      })
      this.currentProject = this.produceMethods[0].methodId.toString()
      this.tabIndex = this.produceMethods.length - 1
    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    handleTabsEdit(targetName, action) {
      let newTabIndex = ++this.tabIndex
      if (action === 'add') {
        this.produceMethods.push({
          methodId: 0,
          methodName: '',
          methodDescription: '',
          methodSteps: []
        })
        this.currentProject = this.produceMethods[newTabIndex].methodId
        materialManagement.createProduceMethod(this.materialId).then(result => {
          if (result.code == 200) {
            this.$modal.msgSuccess('创建成功')
          }
        })
      }
      if (action === 'remove') {
        let tabs = this.produceMethods
        let activeName = this.currentProject
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.methodId === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1]
              if (nextTab) {
                activeName = nextTab.methodId
              }
            }
          })
        }
        this.currentProject = activeName
        materialManagement.deleteProduceMethodById(targetName).then(result => {
          if (result.code == 200) {
            this.$modal.msgSuccess('删除成功')
          }
        })
        this.produceMethods = tabs.filter(tab => tab.methodId !== targetName)
      }
    },
    deleteStep(item, stepIndex) {
      let index = this.produceMethods.indexOf(item)
      if (index !== -1) {
        this.produceMethods[index].methodSteps.splice(stepIndex, 1)
        materialManagement.updateProduceMethod(item).then(result => {
          if (result.code == 200) {
            this.$modal.msgSuccess('删除成功')
          }
        })
      }
    },
    addStep(index) {
      this.produceMethods[index].methodSteps.push('')
    },
    getAssociatedFiles(methodId) {
      this.$router.push({ path: '/system/user', query: { methodId: methodId } })
    },
    getAssociatedProcesses(methodId) {
      this.$router.push({ path: '/system/user', query: { methodId: methodId } })
    },
    getAssociatedProblems(methodId) {
      this.$router.push({ path: '/system/user', query: { methodId: methodId } })
    },
    onSubmit() {
      this.produceMethods.forEach((item) => {
        materialManagement.updateProduceMethod(item).then(result => {
          if (result.code != 200) {
            this.$modal.alertError('更改失败')
          }
          this.$modal.msgSuccess('更改成功')
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
