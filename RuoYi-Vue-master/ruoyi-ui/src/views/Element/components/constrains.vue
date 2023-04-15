<template>
  <el-dialog :title="windowTitle" :visible.sync="dialogFormVisible" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-table ref="table" :model="constraints">
      <el-table-column label="约束类型" width="180">
        <template slot-scope="scope">
          <el-select v-model="scope.row.constraintType" placeholder="请选择">
            <el-option
              v-for="item in constraintOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="约束元素" width="180">
        <template slot-scope="scope">
          <treeselect v-model="scope.row.elements[0].elementId"
                      :clearable="true"
                      :normalizer="elementNormalizer"
                      :options="elementOptions"
                      :searchable="true"
                      placeholder="请选择约束元素"
          />
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
      <el-table-column width="180">
        <template slot="header" slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleAdd"
          >修改
          </el-button>
        </template>
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
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
import * as treeManagement from '@/api/system/treeManagement'

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
      windowTitle: '',
      elementId: 0,
      productId: 0,
      constraints: [{
        constraintId:0,
        constraintType: '',
        elements:[],
        constraintName: '',
        constraintValue: ''
      }],
      constraintOptions: [
        {
          value: '选项1',
          label: '黄金糕'
        },
        {
          value: '选项1',
          label: '黄金糕'
        },
        {
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项1',
          label: '黄金糕'
        }
      ],
      elementOptions: []

    }
  },
  methods: {
    handleOpen() {
      elementManagement.getConstrainsByElementId(this.elementId).then(result => {
        if (result.code === 200) {
          this.constraints = result.data
        }
      })
      elementManagement.getProductById(this.productId).then(result => {
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
      if (node.children && !node.children.length) {
        delete node.children
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    handleAdd() {
      let newConstraint = {
        constraintId: 0,
        constraintType: '',
        elements: [],
        constraintName: '',
        constraintValue: ''
      }
      elementManagement.createConstraint(newConstraint).then(result =>{
        if(result.code === 200){
          this.constraints.push(result.data);
        }
      })
    },
    handleUpdate(data) {
      let con = {
        elementId:this.elementId,
        constraint:data
      }
      elementManagement.updateConstraint(con).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功")
        }
      })
    },
    handleDelete(data) {
      elementManagement.deleteConstraint(data.constraintId).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
