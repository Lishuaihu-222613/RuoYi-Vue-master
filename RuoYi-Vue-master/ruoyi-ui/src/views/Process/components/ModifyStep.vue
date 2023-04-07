<template>
  <el-dialog :visible.sync="dialogFormVisible" title="工步编辑" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs type="border-card">
      <el-tab-pane label="工步基本信息编辑">
        <el-form :model="step">
          <el-form-item :label-width="formLabelWidth" label="工步号">
            <el-input v-model="step.stepNumber"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="工步名称">
            <el-input v-model="step.stepName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="工步内容">
            <el-input v-model="step.stepDescription" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="技术要求">
            <el-input v-model="step.stepRequirement" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="检验要求">
            <el-input v-model="step.inspectRequirement" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用工具">
            <el-select v-model="step.associatedResources" multiple filterable placeholder="请选择">
              <el-option
                v-for="item in resources"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.resourceId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-row type="flex" class="row-bg" justify="space-around">
              <el-col :span="6" :offset="18">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="工步关系编辑">
        <el-row>
          <p style="text-align: center; margin: 0 0 20px">工步关系选择</p>
          <el-select v-model="relation" clearable placeholder="请选择工步关系" style="right :0px"
                     @change="selectRelationKind"
          >
            <el-option
              v-for="item in relations"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-row>
        <div style="text-align: center">
          <el-transfer
            v-model="selectedRelations"
            :button-texts="['移除', '添加']"
            :data="steps"
            :format="{noChecked: '${total}',hasChecked: '${checked}/${total}'}"
            :titles="['待选工步', '已选工步']"
            filterable
            :filter-method="filterMethod"
            style="text-align: left; display: inline-block"
          >
            <span slot-scope="{ option }">{{ option.number }}-{{ option.label }}</span>
            <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">操作</el-button>
          </el-transfer>
        </div>

      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>
import * as processManagement from '@/api/system/processManagement'

export default {
  name: 'ModifyStep',

  watch: {
    selectId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.stepId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    }
  },

  props: {
    selectId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    sId: {
      type: Number,
      default: 0
    }
  },

  data(){
    return {
      dialogFormVisible:false,
      formLabelWidth:'120px',
      stepId:0,
      sequenceId:0,
      step:{},
      steps: [],
      beforeSteps:[],
      afterSteps:[],
      andeSteps:[],
      orSteps:[],
      subSteps:[],
      selectedRelations: [],
      relations: [{
        value: 'before',
        label: '前道工步关系'
      }, {
        value: 'after',
        label: '后道工步关系'
      }, {
        value: 'and',
        label: '工步与关系'
      }, {
        value: 'or',
        label: '工步或关系'
      },{
        value: 'sub',
        label: '子工步关系'
      }],
      relation: '',
      availableResources: {
        useEquipment: [],
        useMouldTool: [],
        useMeasuringTool: [],
        useSpecialTool: [],
        useSpreaderTool: []
      }
    }
  },
  methods: {
    handleOpen() {
      //初始化工步信息
      processManagement.getStepById(this.stepId).then(result => {
        if (result.code == 200) {
          this.step = result.data
          console.log(this.step)
          this.beforeSteps =  this.step.beforeStep.map(item=>{
            return item.stepId;
          })
          console.log(this.beforeSteps)
          this.afterSteps =  this.step.afterStep.map(item=>{
            return item.stepId;
          })
          this.andSteps =  this.step.andStep.map(item=>{
            return item.stepId;
          })
          this.orSteps =  this.step.orStep.map(item=>{
            return item.stepId;
          })
          this.subSteps =  this.step.subStep.map(item=>{
            return item.stepId;
          })
        }
      });
      //得到备选关系
      let oriSteps;
      this.sequenceId = this.sId
      processManagement.getStepBySequence(this.sequenceId).then(result => {
        if(result.code === 200){
          oriSteps = result.data
          console.log(oriSteps)
          this.steps = oriSteps.map(item=>{
            return Object.assign({},{'key':item.stepId,
              'label':item.stepName,'number':item.stepNumber
            })
          })
          this.removeItemSelf(this.steps)
          console.log(this.steps)
        }
      });
      //得到备选资源（从工序资源中选择）
      processManagement.getSequenceById(this.sequenceId).then(result => {
        if(result.code === 200){
          let seq = result.data
          this.availableResources.useEquipment = seq.useEquipment
        }
      })
    },

    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },


    modifyStep() {
      processManagement.updateStep(this.step).then(result => {
        if (result.code === 200) {
          this.step = result.data
        }
      })
    },

    removeItemSelf(arr){
      let index = NaN;
      for(let i in arr){
        if(arr[i]['key'] === this.stepId){
          index=i;
          break;
        }
      }
      if (!isNaN(index)) {
        arr.splice(index,1);
      }
    },
    selectRelationKind(rel) {

      if (rel === 'before') {
        this.selectedRelations = this.beforeSteps
        console.log(this.beforeSteps)
      } else if (rel === 'after') {
        this.selectedRelations = this.afterSteps
      } else if (rel === 'and') {
        this.selectedRelations = this.andSteps
      } else if (rel === 'or') {
        this.selectedRelations = this.orSteps
      } else if(rel ==='sub') {
        this.selectedRelations = this.subSteps
      }

      console.log(this.selectedRelations)
    },

    filterMethod(query, item) {
      return item.label.indexOf(query) > -1
    },

    modifyRelation() {
      let ss = {
        stepId:this.stepId,
        stepIds:this.selectedRelations
      }
      if(this.relation == "before"){
        processManagement.modifyBeforeStep(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工步关系修改成功！")
          }
        })
      } else if(this.relation == "after"){
        processManagement.modifyAfterStep(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工步关系修改成功！")
          }
        })
      } else if(this.relation =="and"){
        processManagement.modifyAndStep(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工步关系修改成功！")
          }
        })
      } else if(this.relation == "or"){
        processManagement.modifyOrStep(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工步关系修改成功！")
          }
        })
      } else if(this.relation == "sub"){
        // 子工步关系更改（可换）
        // ss.stepIds.forEach(item =>{
        //   processManagement.removeStepForSequence(this.sequenceId,item)
        // })
        processManagement.modifySubStep(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工步关系修改成功！")
          }
        })
      }
    },

    onSubmit() {
      processManagement.updateStep(this.step).then(result => {
        if(result.code ==200){
          this.step = result.data
          this.$modal.msgSuccess("工步修改成功！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
