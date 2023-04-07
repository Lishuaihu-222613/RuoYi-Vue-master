<template>
  <el-dialog :visible.sync="dialogFormVisible" title="工步信息" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <ModifyStep
      ref="ModifyStep"
      :dialog="modifyStepShow"
      :sId="sequenceId"
      :selectId="selectStepId"
      :available="availableResources"
      @closeDialog="() =>{ this.modifyStepShow = false }"
    ></ModifyStep>
    <el-row>
      <el-button style="display: inline-block;float: right;" @click="addStep">添加工步</el-button>
    </el-row>
    <el-table id="Steps" :data="Steps" :tree-props="treeProps" border row-key="stepId">
      <el-table-column label="工步号" prop="stepNumber" ></el-table-column>
      <el-table-column label="工步名" prop="stepName" ></el-table-column>
      <el-table-column label="工步内容" prop="stepDescription" ></el-table-column>
      <el-table-column label="技术要求" prop="stepRequirement" ></el-table-column>
      <el-table-column label="检验要求" prop="inspectRequirement" ></el-table-column>
      <el-table-column label="使用工具" >
        <template v-slot="scope">
          <el-button size="small" type="text" @click="getResource(scope.row.stepId)">查看工具</el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
      >
        <template v-slot="scope">
          <el-button size="small" type="text" @click="deleteStep(scope.row.stepId)">移除工步</el-button>
          <el-button size="small" type="text" @click="modifyStep(scope.row.stepId)">编辑工步</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script>

import ModifyStep from '@/views/Process/components/ModifyStep.vue'
import * as processManagement from '@/api/system/processManagement'

export default {
  name: 'StepTable',

  components: {
    ModifyStep
  },

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
          this.sequenceId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    }
  },
  data() {
    return {
      sequenceId: 0,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      treeProps: {
        children: 'subStep'
      },
      Steps: [],
      selectStepId: 0,
      modifyStepShow: false,
    }
  },
  methods: {

    handleOpen() {
      processManagement.getStepBySequence(this.sequenceId).then(result => {
        if (result.code == 200) {
          this.Steps = result.data
        }
      })
      let subs = [];
      this.Steps.forEach(item =>{
        if(item.subStep.length !== 0){
          item.subStep.forEach(n =>{
            subs.push(n)
          })
        }
      })
      console.log(subs)
      subs.forEach(item =>{
        let index = this.Steps.indexOf(item)
        if (index !== -1){
          this.Steps.splice(index, 1)
        }
      })
    },

    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },

    init() {
      // this.generateData()
    },
    addStep() {
      let newStep = {
        stepId: 0,
        stepName: '待编辑',
        stepNumber: '待编辑',
        stepDescription: '待编辑',
        stepRequirement: '待编辑',
        inspectRequirement: '待编辑',
        dynamicLabels:[],
      }
      let sAnds = {
        sequenceId: this.sequenceId,
        step: newStep
      }
      processManagement.addStepForSequence(sAnds).then(result => {
        if (result.code == 200) {
          let s = result.data
          console.log(s)
          this.steps.push(s)
          this.$modal.msgSuccess("添加工步成功")
        }
      })
    },

    deleteStep(stepId) {
      processManagement.deleteStepById(stepId).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess(result.data)
        }
      })
    },
    modifyStep(stepId) {
      console.log(this.sequenceId)
      this.modifyStepShow = true
      this.selectStepId = stepId
    },
    getResource(stepId) {

    }
  }
}
</script>

<style scoped>

</style>
