<template>
  <el-dialog :visible.sync="dialogFormVisible" :title="windowTitle" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form ref="form" :model="prescription" :rules="rules">
      <el-row>
        <el-col :span="12">
          <el-form-item :label-width="formLabelWidth" label="推进剂配方名称">
            <el-input v-model="prescription.prescriptionName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item :label-width="formLabelWidth" label="推进剂配方标签">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="prescription.prescriptionLabels"
                          :clearable="true"
                          :multiple="true"
                          :flat="true"
                          :autoSelectAncestors="true"
                          :normalizer="normalizer"
                          :options="options"
                          :searchable="true"
                          placeholder="请选择分类标签"
              >
              </treeselect>
            </el-row>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item :label-width="formLabelWidth" label="推进剂配方密度">
            <el-input v-model="prescription.density"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item :label-width="formLabelWidth" label="推进剂配方危险等级">
            <el-input v-model="prescription.dangerLevel"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item :label-width="formLabelWidth" label="推进剂配方描述">
          <el-input v-model="prescription.prescriptionDescription"></el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item :label-width="formLabelWidth" label="固化时间">
          <el-row>
            <el-col :span="8">
              <el-input v-model="newCurTingTimeName" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line"></el-col>
            <el-col :span="8">
              <el-input v-model="newCurTingTimeValue" placeholder="内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="addCuringTime">添加</el-button>
            </el-col>
          </el-row>
          <el-row v-for="(value,name,index) in prescription.curingTime"
                  :key="name"
                  :gutter="20"
          >
            <el-col :span="8">
              <el-input v-model="name" :disabled="true" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line">----</el-col>
            <el-col :span="8">
              <el-input v-model="prescription.curingTime[name]" placeholder="内容" > </el-input>
            </el-col>
            <el-col :span="6">
              <el-button @click.prevent="removeCuringTime(name)">删除</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item :label-width="formLabelWidth" label="其他属性">
          <el-row>
            <el-col :span="8">
              <el-input v-model="newPropertyName" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line"></el-col>
            <el-col :span="8">
              <el-input v-model="newPropertyValue" placeholder="内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="addOtherProperty">添加</el-button>
            </el-col>
          </el-row>
          <el-row v-for="(value, name ,index) in prescription.otherProperty"
                  :key="name"
                  :gutter="20"
          >
            <el-col :span="8">
              <el-input v-model="name" :disabled="true" placeholder="名称"></el-input>
            </el-col>
            <el-col :span="2" class="line">----</el-col>
            <el-col :span="8">
              <el-input v-model="prescription.otherProperty[name]" placeholder="内容"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button @click.prevent="removeOtherProperty(name)">删除</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSubmit">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { ALL_WITH_INDETERMINATE } from '@riophae/vue-treeselect/src/constants'

export default {
  name: 'modifyPrescription',
  components: { Treeselect },

  props: {
    selectPrescriptionId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    }
  },
  watch: {
    selectPrescriptionId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== oldVal) {
          this.prescriptionId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    title: {
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },

  created() {
  },

  data() {
    return {
      // 遮罩层
      loading: true,
      // 知识树管理树选项
      options: [],
      windowTitle: '创建配方',
      prescription: {
        prescriptionId: 0,
        prescriptionName: '',
        prescriptionLabels: [],
        prescriptionDescription: '',
        density: '',
        dangerLevel: '',
        curingTime: {},
        otherProperty: {}
      },
      prescriptionId: 0,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      newPropertyName: '',
      newPropertyValue: '',
      newCurTingTimeName: '',
      newCurTingTimeValue: '',
      // 表单校验
      rules: {
        prescriptionName: [
          { required: true, message: '配方名称不能为空', trigger: 'blur' },
          { min: 2, max: 20, message: '配方名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        prescriptionDescription: [
          { required: true, message: '配方内容不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ALL_WITH_INDETERMINATE() {
      return ALL_WITH_INDETERMINATE
    },
    /** 查询标签列表 */
    getLabelList() {
      this.loading = true
      treeManagement.getTreeManagement(25822).then(response => {
        this.options = []
        console.log(response.data)
        this.options.push(response.data)
      })
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs
      }
      return {
        id: node.leafName,
        label: node.leafName,
        value: node.leafValue,
        children: node.subLeafs
      }
    },
    handleOpen() {
      if (this.prescriptionId !== 0) {
        prescriptionManagement.getPrescriptionById(this.prescriptionId).then(result => {
            if (result.code === 200) {
              this.prescription = result.data
            }
          }
        )
      }
      this.getLabelList()
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.prescription = {
        prescriptionId: 0,
        prescriptionName: '',
        prescriptionLabels: [],
        prescriptionDescription: '',
        density: '',
        dangerLevel: '',
        curingTime: {},
        otherProperty: {}
      };
        this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false
      this.reset()
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    addCuringTime() {
      this.$set(this.prescription.curingTime, this.newCurTingTimeName+"", this.newCurTingTimeValue+"")
    },
    removeCuringTime(key) {
      this.$delete(this.prescription.curingTime, key+"")
    },
    addOtherProperty() {
      this.$set(this.prescription.otherProperty, this.newPropertyName+"", this.newPropertyValue+"")
    },
    removeOtherProperty(key) {
      this.$delete(this.prescription.otherProperty, key+"")
    },
    onSubmit() {
      if (this.windowTitle === '新增配方') {
        prescriptionManagement.createPrescription(this.prescription).then(result => {
          if (result.code === 200) {
            this.prescription = result.data
            this.$modal.msgSuccess('创建成功！')
          }
        })
      } else {
        prescriptionManagement.updatePrescription(this.prescription).then(result => {
          if (result.code === 200) {
            this.prescription = result.data
            this.$modal.msgSuccess('修改成功！')
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
