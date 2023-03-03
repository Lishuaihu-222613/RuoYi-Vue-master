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
            <el-input v-model="sequence.methodName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="准结工时">
            <el-input v-model="sequence.QuasiClosingHours"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="单件工时">
            <el-input v-model="sequence.TaktTime"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="工序内容">
            <el-input v-model="sequence.sequenceDescription" autosize
                      placeholder="请输入内容"
                      type="textarea"
            ></el-input>
          </el-form-item>
          <el-form-item v-for="(value, markIndex) in sequence.sequenceRemark" :key="markIndex"
                        :label-width="formLabelWidth"
                        label="工序备注"
          >
            <el-row>
              <el-input v-model="sequence.sequenceRemark[markIndex]" type="textarea"></el-input>
              <el-button circle icon="el-icon-remove" @click.prevent="deleteRemark(item,stepIndex)"></el-button>
              <el-button circle icon="el-icon-circle-plus" @click.prevent="addRemark(item)"></el-button>
            </el-row>
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
      <el-tab-pane label="工序使用资源编辑">


      </el-tab-pane>
      <el-tab-pane label="工序关系编辑">
        <el-row>
          <p style="text-align: center; margin: 0 0 20px">工序关系选择</p>
          <el-select v-model="relation" clearable placeholder="请选择" style="right :0px"
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
            :format="{noChecked: '${total}',hasChecked: '${checked}/${total}'}"
            :titles="['待选工序', '已选工序']"
            filterable
            :filter-method="filterMethod"
            style="text-align: left; display: inline-block"
          >
            <span slot-scope="{ seq }">{{ seq.sequenceNumber }}-{{ seq.sequenceName }}</span>
            <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">操作</el-button>
          </el-transfer>
        </div>

      </el-tab-pane>
    </el-tabs>

  </el-dialog>

</template>

<script>
export default {

  name: 'ModifySequence',

  mounted() {
    this.init();
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

  data() {
    return {
      sequenceId: 0,
      formLabelWidth: '120px',
      dialogFormVisible:false,
      sequence:{},
      sequences:[],
      selectedRelations:[],
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
  methods:{

    handleOpen(){

    },

    handleClose(){

    },

    init(){
      this.generateData();
    },

    addRemark(){

    },

    deleteRemark(){

    },

    selectRelationKind(rel){

      this.selectRelation();
    },

    generateData(seqs){
      for (let i = 0; i <= seqs.length; i++) {
        this.sequences.push({
          key: seqs[i].sequenceId,
          label: seqs[i].sequenceName,
          Number: seqs[i].sequenceNumber
        });
      }
    },

    selectRelation(){

    },

    filterMethod(query, item) {
      return item.sequenceName.indexOf(query) > -1;
    },

    modifyRelation(){

    },

    onSubmit(){

    }
  },
}
</script>

<style scoped>

</style>
