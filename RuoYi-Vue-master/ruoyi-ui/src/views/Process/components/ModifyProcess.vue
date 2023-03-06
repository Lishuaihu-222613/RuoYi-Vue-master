<template>
  <el-dialog :visible.sync="dialogFormVisible" title="工艺规程编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="Process">
      <el-form-item :label-width="formLabelWidth" label="工艺规程名称">
        <el-input v-model="Process.processName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="工艺规程备注">
        <el-input v-model="Process.processDescription"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="关联零、部、组（整）件">
        <el-row class="row-bg" justify="space-around" type="flex">
          <el-col  :span="10">
            <el-select
              v-model="Process.associatedStructure"
              multiple
              filterable
              remote
              size="medium"
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="remoteMethod"
              :loading="loading">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="14">
            <span></span>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="关联产品系统">
        <el-row class="row-bg" justify="space-around" type="flex">
          <el-col :span="10">
            <el-select
              v-model="Process.associatedProduct"
              multiple
              filterable
              remote
              size="medium"
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="remoteMethod"
              :loading="loading">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="14">
            <span></span>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="关联文件">
        <el-row class="row-bg" justify="space-around" type="flex">
          <el-col :span="10">
            <el-select
              v-model="Process.associatedFiles"
              multiple
              filterable
              remote
              size="medium"
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="remoteMethod"
              :loading="loading">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="14">
            <span></span>
          </el-col>
        </el-row>
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
  </el-dialog>

</template>

<script>

import * as processManagement from '@/api/system/processManagement'

export default {

  name: 'ModifyProcess',

  watch: {
    selectId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.processId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
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
  },

  data() {
    return {
      processId: 0,
      formLabelWidth: '180px',
      dialogFormVisible: false,
      options: [],
      value: [],
      list: [],
      loading: false,
      states: ["Alabama", "Alaska", "Arizona",
        "Arkansas", "California", "Colorado",
        "Connecticut", "Delaware", "Florida",
        "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky",
        "Louisiana", "Maine", "Maryland",
        "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio",
        "Oklahoma", "Oregon", "Pennsylvania",
        "Rhode Island", "South Carolina",
        "South Dakota", "Tennessee", "Texas",
        "Utah", "Vermont", "Virginia",
        "Washington", "West Virginia", "Wisconsin",
        "Wyoming"],
      Process:{},
    }
  },
  mounted() {
    this.list = this.states.map(item => {
      return { value: `value:${item}`, label: `label:${item}` };
    });
  },
  methods: {
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options = this.list.filter(item => {
            return item.label.toLowerCase()
              .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options = [];
      }
    },
    handleOpen(){
      processManagement.getProcessById(this.processId).then(result => {
        if(result.code == 200){
          this.Process = result.data
        }
      })
    },
    handleClose(){
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    onSubmit(){
      processManagement.updateProcess(this.Process).then(result => {
        if(result.code == 200){
          this.Process = result.data
          this.$modal.msgSuccess("信息修改成功！")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
