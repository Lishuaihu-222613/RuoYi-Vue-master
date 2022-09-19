<template>
  <el-dialog :visible.sync="dialogFormVisible" title="新建决策知识" @closed="handleClose" @open="handleOpen">
    <el-form :model="dkItem">
      <el-form-item :label-width="formLabelWidth" label="知识名称">
        <el-input v-model="dkItem.dkName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="知识来源">
        <el-input v-model="dkItem.dkSource"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="知识描述">
        <el-input v-model="dkItem.description" autosize type="textarea"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="创建/修改日期">
        <el-input v-model="dkItem.modifyTime"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="责任人">
        <el-input v-model="dkItem.manager"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth"
                    :prop="'dkItem.ruleAntecedents'"
                    :rules="{required: true, message: '前项不能为空', trigger: 'blur'}"
                    label="规则前项"
      >
        <el-row :gutter="20">
          <el-col :offset="6" :span="6">
            <el-button @click.prevent="addCondition">新增</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in dkItem.ruleAntecedents"
                :key="index"
                :gutter="20"
                class="conditions"
        >
          <el-col :span="2">
            <el-tag type="primary">
              前项{{ index }}
            </el-tag>
          </el-col>
          <el-col :span="6">
            <el-input v-model="item.condition.cluster" placeholder="前项类别" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="item.condition.content" placeholder="前项内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeCondition(item)">删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth"
                    :prop="'dkItem.ruleConsequents'"
                    :rules="{required: true, message: '后项不能为空', trigger: 'blur'}"
                    label="规则后项"
      >
        <el-row :gutter="20">
          <el-col :offset="6" :span="6">
            <el-button @click.prevent="addResult">新增</el-button>
          </el-col>
        </el-row>
        <el-row v-for="(item, index) in dkItem.ruleConsequents"
                :key="index"
                :gutter="20"
                class="conditions"
        >
          <el-col :span="2">
            <el-tag type="primary">
              后项{{ index }}
            </el-tag>
          </el-col>
          <el-col :span="6">
            <el-input v-model="item.result.cluster" placeholder="后项类别" style="width:100%"></el-input>
          </el-col>
          <el-col :span="2" class="line">----</el-col>
          <el-col :span="6">
            <el-input v-model="item.result.content" placeholder="后项内容" style="width:100%"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button @click.prevent="removeResult(item)">删除</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

import * as decisionKnowledge from '@/api/system/decisionKnowledge'
import { updateDK } from '@/api/system/decisionKnowledge'

export default {

  name: 'modifyDK',

  props: {
    selectItem: {
      type: Object,
      default: () => {
      }
    },
    dialog: {
      type: Boolean,
      default: false
    }
  },
  mounted() {
    console.log('chushihua')

  },
  watch: {
    selectItem: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.dkItem = newVal
        }
      },
      deep: true
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    }
  },
  data: () => ({
    dialogFormVisible: false,
    formLabelWidth: '120px',
    dkItem: {
      id: '',
      dkName: '',
      domain: '',
      description: '',
      dkSource: '',
      modifyTime: '',
      manager: '',
      ruleAntecedents: [
        {
          id: '',
          content: '',
          cluster: '',
          condition: {
            id: '',
            cluster: '',
            content: ''
          }
        }
      ],
      ruleConsequents: [
        {
          id: '',
          content: '',
          cluster: '',
          result: {
            id: '',
            cluster: '',
            content: ''
          }
        }
      ]
    }
  }),
  methods: {
    handleOpen() {

    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    addCondition() {
      this.dkItem.ruleAntecedents.push(
        {
          id: '',
          content: '',
          cluster: '',
          condition: {
            id: '',
            cluster: '',
            content: ''
          }
        }
      )
    },
    removeCondition(item) {
      let index = this.dkItem.ruleAntecedents.indexOf(item)
      if (index !== -1) {
        this.dkItem.ruleAntecedents.splice(index, 1)
      }
    },
    addResult() {
      this.dkItem.ruleConsequents.push(
        {
          id: '',
          content: '',
          cluster: '',
          result: {
            id: '',
            cluster: '',
            content: ''
          }
        }
      )
    },
    removeResult() {
      let index = this.dkItem.ruleConsequents.indexOf(item)
      if (index !== -1) {
        this.dkItem.ruleConsequents.splice(index, 1)
      }
    },
    onSubmit() {
      if (this.dkItem.dkName !== '') {
        console.log(this.dkItem)
        decisionKnowledge.updateDK(this.dkItem).then(result => {
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
