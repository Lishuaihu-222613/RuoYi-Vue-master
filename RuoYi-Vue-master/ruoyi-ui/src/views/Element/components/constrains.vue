<template>
  <el-dialog :title="windowTitle" :visible.sync="dialogFormVisible" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form>
      <el-row>
        <el-col :span="2">
          <el-tag type="info">约束：</el-tag>
        </el-col>
        <el-col :span="6">
          <el-select v-model="newConstraint.constraintType" placeholder="约束类型">
            <el-option
              v-for="item in constraintOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="6">
          <treeselect v-model="newConstraint.elementId"
                      :clearable="true"
                      :load-options="loadOptions"
                      :normalizer="elementNormalizer"
                      :options="elementOptions"
                      :searchable="true"
                      placeholder="请选择约束元素"
          />
        </el-col>
        <el-col :span="4" >
          <el-input v-model="newConstraint.constraintName" placeholder="请输入约束名"/>
        </el-col>
        <el-col :span="4">
          <el-input v-model="newConstraint.constraintValue" placeholder="请输入约束值"/>
        </el-col>
        <el-col :span="1" :offset="1">
          <el-button type="primary" icon="el-icon-edit" @click="addConstraint" circle></el-button>
        </el-col>
      </el-row>
    </el-form>
    <el-table ref="table" :data="constraints">
      <el-table-column label="约束类型" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.constraintType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="约束元素" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.elements[0].elementName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="约束名" width="180">
        <template slot-scope="scope">
          <el-input v-model="scope.row.constraintName" placeholder="请输入约束名"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="约束值" width="180">
        <template slot-scope="scope">
          <el-input v-model="scope.row.constraintValue" placeholder="请输入约束值"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>

</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as elementManagement from '@/api/system/elementManagement'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { LOAD_CHILDREN_OPTIONS } from '@riophae/vue-treeselect'

const simulateAsyncOperation = fn => {
  setTimeout(fn, 2000)
}

export default {
  name: 'constrains',
  components: { Treeselect },
  props: {
    selectElementId: {
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
  watch: {
    selectElementId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.elementId = newVal
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
        this.productId = newVal
      }
    }
  },
  data() {
    return {
      dialogFormVisible: false,
      windowTitle: '约束信息',
      elementId: 0,
      productId: 0,
      constraints: [{
        constraintId: 25694,
        constraintType: '同轴',
        elements:[{}],
        constraintName: '同轴约束1',
        constraintValue: '-'
      }],
      newConstraint: {},
      constraintOptions: [
        {
          value: '接触',
          label: '接触'
        },
        {
          value: '角度',
          label: '角度'
        },
        {
          value: '同轴',
          label: '同轴'
        }, {
          value: '偏移',
          label: '偏移'
        }
      ],
      elementOptions: []

    }
  },
  methods: {
    handleOpen() {
      this.constraints = []
      this.elementOptions = []
      elementManagement.getConstrainsByElementId(this.elementId).then(result => {
        if (result.code === 200) {
          if(result.data.length >0) {
            this.constraints = result.data
          }
        }
      })
      elementManagement.getSingleElementById(this.productId).then(result => {
        if (result.code === 200) {
          this.elementOptions.push(result.data)
        }
      })
    },
    handleClose() {
      this.dialogFormVisible = false
      this.reset()
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    elementNormalizer(node) {
      if (node.subElements && !node.subElements.length) {
        node.subElements = null
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    loadOptions({ action, parentNode, callback }) {

      if (action === LOAD_CHILDREN_OPTIONS) {
        simulateAsyncOperation(() =>{
          elementManagement.getSubElementsById(parentNode.elementId).then(result =>{
            if(result.code === 200){
              parentNode.subElements = result.data
              callback()
            }
          })
        })
      }
    },
    addConstraint() {
      let item = {
        elementId:this.elementId,
        constraintType:this.newConstraint.constraintType,
        otherElementId:this.newConstraint.elementId,
        constraintName:this.newConstraint.constraintName,
        constraintValue:this.newConstraint.constraintValue
      }
      elementManagement.createConstraint(item).then(result => {
        if (result.code === 200) {
          this.constraints.push(result.data)
        }
      })
    },
    handleUpdate(data) {
      let con = {
        elementId: this.elementId,
        constraint: data
      }
      elementManagement.updateConstraint(con).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('修改成功')
        }
      })
    },
    handleDelete(data) {
      elementManagement.deleteConstraint(data.constraintId).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('修改成功')
        }
      })
    },
    reset(){

    }
  }
}
</script>

<style scoped>

</style>
