<template>
  <el-dialog :visible.sync="dialogFormVisible" title="工序编辑" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs type="border-card">
      <el-tab-pane label="工序基本信息编辑">
        <el-form :model="sequence">
          <el-form-item :label-width="formLabelWidth" label="工序号">
            <el-input v-model="sequence.sequenceNumber"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="工序名称">
            <el-input v-model="sequence.sequenceName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="准结工时">
            <el-input-number v-model="sequence.QuasiClosingHours" :max="100" :precision="2" :step="0.1"
            ></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="单件工时">
            <el-input-number v-model="sequence.TaktTime" :max="10" :precision="2" :step="0.1"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="工序内容">
            <el-input v-model="sequence.sequenceDescription" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item
            :label-width="formLabelWidth"
            label="工序备注"
          >
            <el-row v-for="(item, markIndex) in sequence.sequenceRemark" :key="markIndex">
              <el-col :span="18">
                <el-input v-model="sequence.sequenceRemark[markIndex]"></el-input>
              </el-col>
              <el-col :span="6">
                <el-button circle icon="el-icon-remove" @click.prevent="deleteRemark(markIndex)"></el-button>
                <el-button circle icon="el-icon-circle-plus" @click.prevent="addRemark"></el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :offset="18" :span="6">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifySequence">确 定</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="工序使用资源编辑">
        <el-form :model="resources">
          <el-form-item :label-width="formLabelWidth" label="使用设备">
            <el-select v-model="resources.useEquipment" filterable multiple placeholder="请选择">
              <el-option
                v-for="item in equipments"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用模具">
            <el-select v-model="resources.useMouldTool" filterable multiple placeholder="请选择">
              <el-option
                v-for="item in mouldTools"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用量具">
            <el-select v-model="resources.useMeasuringTool" filterable multiple placeholder="请选择">
              <el-option
                v-for="item in measuringTools"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="专用工装">
            <el-select v-model="resources.useSpecialTool" filterable multiple placeholder="请选择">
              <el-option
                v-for="item in specialTools"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用吊具">
            <el-select v-model="resources.useSpreaderTool" filterable multiple placeholder="请选择">
              <el-option
                v-for="item in spreadersTools"
                :key="item.resourceId"
                :label="item.resourceName"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :offset="18" :span="6">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>

      </el-tab-pane>
      <el-tab-pane label="工序关系编辑">
        <el-row>
          <p style="text-align: center; margin: 0 0 20px">工序关系选择</p>
          <el-select v-model="relation" clearable placeholder="请选择工序关系" style="right :0px"
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
            :data="sequences"
            :filter-method="filterMethod"
            :format="{noChecked: '${total}',hasChecked: '${checked}/${total}'}"
            :titles="['待选工序', '已选工序']"
            filterable
            style="text-align: left; display: inline-block"
          >
            <span slot-scope="{ option }">{{ option.label }}-{{ option.number }}</span>
            <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">修改工序关系</el-button>
          </el-transfer>
        </div>

      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>

import * as processManagement from '@/api/system/processManagement'

export default {

  name: 'ModifySequence',

  mounted() {
    this.init()
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
    },
    pId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.processId = newVal
        }
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
    pId: {
      type: Number,
      default: 0
    }
  },

  data() {
    return {
      sequenceId: 0,
      processId: 0,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      sequence: {},
      resources: {
        useEquipment: [],
        useMouldTool: [],
        useMeasuringTool: [],
        useSpecialTool: [],
        useSpreaderTool: []
      },
      equipments: [],
      mouldTools: [],
      measuringTools: [],
      specialTools: [],
      sequences: [],
      beforeSequences:[],
      afterSequences:[],
      andeSequences:[],
      orSequences:[],
      selectedRelations: [],
      relations: [{
        value: 'before',
        label: '前道工序关系'
      }, {
        value: 'after',
        label: '后道工序关系'
      }, {
        value: 'and',
        label: '工序与关系'
      }, {
        value: 'or',
        label: '工序或关系'
      }],
      relation: ''
    }
  },
  methods: {

    handleOpen() {
      processManagement.getSequenceById(this.sequenceId).then(result => {
        if (result.code == 200) {
          this.sequence = result.data
          this.beforeSequences =  this.sequence.beforeSequence.map(item=>{
            return item.sequenceId;
          })
          this.afterSequences =  this.sequence.afterSequence.map(item=>{
            return item.sequenceId;
          })
          this.andSequences =  this.sequence.andSequence.map(item=>{
            return item.sequenceId;
          })
          this.orSequences =  this.sequence.orSequence.map(item=>{
            return item.sequenceId;
          })
        }
      });
      let oriSequences;
      processManagement.getSequenceByProcess(this.processId).then(result => {
        if(result.code == 200){
          oriSequences = result.data
          console.log(oriSequences)
          this.sequences = oriSequences.map(item=>{
            return Object.assign({},{'key':item.sequenceId,
              'label':item.sequenceName,'number':item.sequenceNumber
            })
          })
          this.removeItemSelf(this.sequences)
          console.log(this.sequences)
        }
      });
    },

    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },

    init() {
      // this.generateData()
    },

    addRemark() {
      this.sequence.sequenceRemark.push('')
    },

    deleteRemark(markIndex) {
      this.sequence.sequenceRemark.removeAt(markIndex)
    },

    modifySequence() {
      processManagement.updateSequence(this.sequence).then(result => {
        if (result.code == 200) {
          this.sequence = result.data
        }
      })
    },

    removeItemSelf(arr){
      let index = NaN;
      for(let i in arr){
        if(arr[i]['key'] === this.sequenceId){
          index=i;
          break;
        }
      }
      if (!isNaN(index)) {
        arr.splice(index,1);
      }
    },
    selectRelationKind(rel) {

      if (rel == 'before') {
        this.selectedRelations = this.beforeSequences
      } else if (rel == 'after') {
        this.selectedRelations = this.afterSequences
      } else if (rel == 'and') {
        this.selectedRelations = this.andSequences
      } else if (rel == 'or') {
        this.selectedRelations = this.orSequences
      }
    },
    generateData() {


    },

    selectRelation() {

    },

    filterMethod(query, item) {
      return item.label.indexOf(query) > -1
    },

    modifyRelation() {
      if(this.relation == "before"){
        let ss = {
          sequenceId:this.sequenceId,
          sequenceList:this.beforeSequences
        }
        processManagement.modifyBeforeSequence(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工序关系修改成功！")
          }
        })
      } else if(this.relation == "after"){
        let ss = {
          sequenceId:this.sequenceId,
          sequenceList:this.afterSequences
        }
        processManagement.modifyAfterSequence(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工序关系修改成功！")
          }
        })
      } else if(this.relation =="and"){
        let ss = {
          sequenceId:this.sequenceId,
          sequenceList:this.andSequences
        }
        processManagement.modifyAndSequence(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工序关系修改成功！")
          }
        })
      } else if(this.relation == "or"){
        let ss = {
          sequenceId:this.sequenceId,
          sequenceList:this.orSequences
        }
        processManagement.modifyOrSequence(ss).then(result => {
          if(result.code ==200){
            this.$modal.msgSuccess("工序关系修改成功！")
          }
        })
      }
    },

    onSubmit() {
      processManagement.updateSequence(this.sequence).then(result => {
        if(result.code == 200){
          this.sequence = result.data
          this.$modal.msgSuccess("修改成功！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
