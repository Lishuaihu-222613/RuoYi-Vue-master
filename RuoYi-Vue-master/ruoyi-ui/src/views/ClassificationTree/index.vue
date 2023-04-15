<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="节点名称" prop="name">
        <el-input
          v-model="queryParams.leafName"
          clearable
          placeholder="请输入节点名称"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="节点描述" prop="description">
        <el-input
          v-model="queryParams.leafDescription"
          clearable
          placeholder="请输入节点描述"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :columns="columns" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="leafList"
      :load="load"
      :tree-props="{children: 'subLeafs', hasChildren: 'hasSubLeafs'}"
      height="1000"
      :indent="indent"
      lazy
      row-key="leafId"
      @selection-change="handleSelectionChange"
    >
      <el-table-column align="center" type="selection" width="50"/>
      <el-table-column label="节点编号" prop="leafId" width="100"/>
      <el-table-column label="节点名称" prop="leafName" width="180"/>
      <el-table-column align="center" label="节点描述" prop="leafDescription" width="400"/>
      <el-table-column align="center" label="节点值" prop="leafValue" width="200"/>
      <el-table-column align="center" label="属性" prop="leafAttributes" width="200">
        <template slot-scope="scope">
                <span v-for="(item,index) in scope.row.leafAttributes">
                  {{ scope.row.leafAttributes[index] }}
                </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="要求" prop="leafRequirements" width="200">
        <template slot-scope="scope">
                <span v-for="(item,index) in scope.row.leafRequirements">
                  {{ scope.row.leafRequirements[index] }}
                </span>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            icon="el-icon-plus"
            size="mini"
            type="text"
            @click="handleAdd(scope.row)"
          >新增
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

    <!-- 添加或修改知识树管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="leaf" :rules="rules" label-width="80px">
        <el-form-item label="节点名称" prop="leafName">
          <el-input v-model="leaf.leafName" placeholder="请输入知识名称"/>
        </el-form-item>
        <el-form-item label="节点描述" prop="leafDescription">
          <el-input v-model="leaf.leafDescription" placeholder="请输入概念描述"/>
        </el-form-item>
        <el-form-item label="父级节点">
          <treeselect v-model="parentId" :normalizer="normalizer" :options="leafOptions" placeholder="请选择父级概念"/>
        </el-form-item>
        <el-form-item label="节点值" prop="leafValue">
          <el-input v-model="leaf.leafValue" placeholder="请输入节点数量"/>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否为根节点">
              <el-switch
                v-model="RootNode"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否存在子节点" prop="hasSubLeafs">
              <el-switch
                v-model="leaf.hasSubLeafs"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="节点属性" prop="leafAttributes">
          <el-button style="float: right" type="primary" @click="addAttribute">添加属性</el-button>
          <el-row v-for="(item,index) in leaf.leafAttributes" :key="index">
            <el-col :span="20">
              <el-input v-model="leaf.leafAttributes[index]"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button
                circle icon="el-icon-delete" type="danger"
                @click="removeAttribute(leaf.leafAttributes[index])"
              ></el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="节点要求" prop="leafRequirements">
          <el-button style="float: right" type="primary" @click="addRequirement">添加要求</el-button>
          <el-row v-for="(item,index) in leaf.leafRequirements" :key="index">
            <el-col :span="20">
              <el-input v-model="leaf.leafRequirements[index]"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button
                circle icon="el-icon-delete" type="danger"
                @click="removeRequirement(leaf.leafRequirements[index])"
              ></el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'index',
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 知识树管理表格数据
      leafList: [],
      // 知识树管理树选项
      leafOptions: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        leafName: '',
        leafDescription: ''
      },
      parentId: undefined,
      RootNode: true,
      indent:4,
      // 表单参数
      leaf: {
        leafId: undefined,
        leafName: '',
        leafDescription: '',
        leafValue: '',
        leafAttributes: [''],
        leafRequirements: [''],
        hasSubLeafs: true,
        dynamicLabels: [],
        subLeafs: []
      },
      // 表单校验
      rules: {
        leafName: [
          { required: true, message: '知识名称不能为空', trigger: 'blur' }
        ],
        leafDescription: [
          { required: true, message: '知识描述不能为空', trigger: 'blur' }
        ],
        leafValue: [
          { required: true, message: '知识值不能为空', trigger: 'blur' }
        ]
      },
      columns: [
        { key: 0, label: `节点编号`, visible: true },
        { key: 1, label: `节点名称`, visible: true },
        { key: 2, label: `节点描述`, visible: true },
        { key: 3, label: `节点值`, visible: true },
        { key: 4, label: `属性`, visible: true },
        { key: 5, label: `要求`, visible: true }
      ]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询知识树管理列表 */
    getList() {
      this.loading = true
      if (this.queryParams.leafName === '' && this.queryParams.leafDescription === '') {
        treeManagement.getAllRootNode().then(result => {
          this.leafList = result.data
          this.loading = false
        })
      } else {
        treeManagement.getleafsByParams(this.queryParams).then(response => {
          this.leafList = response.data
          this.loading = false
        })
      }
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs
      }
      return {
        id: node.leafId,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    /** 查询知识树管理下拉树结构 */
    getTreeselect() {
      treeManagement.getAllRootNode().then(response => {
        this.leafOptions = []
        console.log(response.data)
        this.leafOptions = (response.data)
      })
    },
    load(tree, treeNode, resolve) {
      setTimeout(() => {
        let parentId = treeNode.leafId
        treeManagement.getSubLeafsByParentId(parentId).then(result => {
          if (result.code === 200) {
            resolve(result.data)
          }
        })
      }, 1000)
    },
    handleSelectionChange(selection){
      this.ids = selection.map(item => item.resourceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {}
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleOpen() {
      if (this.leaf.dynamicLabels.includes('根节点')) {
        this.RootNode = true
      } else {
        this.RootNode = false
      }
      treeManagement.getParentLeaf(this.leaf.leafId).then(result => {
        this.parentId = result.data.leafId
      })
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset()
      this.getTreeselect()
      if (row != null && row.leafId) {
        this.parentId = row.leafId
      } else {
        this.parentId = undefined
        this.RootNode = true
      }
      this.open = true
      this.title = '添加知识树管理'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.getTreeselect()
      if (row != null) {
        this.handleOpen()
        // this.form.parentId = row.leafId;
      }
      treeManagement.getTreeManagement(row.leafId).then(response => {
        this.leaf = response.data
        this.open = true
        this.title = '修改知识树管理'
      })
    },
    addAttribute() {
      this.leaf.leafAttributes.push('')
    },
    removeAttribute(item) {
      let index = this.leaf.leafAttributes.indexOf(item)
      if (index !== -1) {
        this.leaf.leafAttributes.splice(index, 1)
      }
    },
    addRequirement() {
      this.leaf.leafRequirements.push('')
    },
    removeRequirement(item) {
      let index = this.leaf.leafRequirements.indexOf(item)
      if (index !== -1) {
        this.leaf.leafRequirements.splice(index, 1)
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.leaf.leafId !== undefined) {
            if (this.RootNode === true) {
              this.leaf.dynamicLabels.push('根节点')
            }
            let node = {
              parentId: this.parentId,
              subLeaf: this.leaf
            }
            treeManagement.updateTreeManagement(node).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            if (this.RootNode === true) {
              this.leaf.dynamicLabels.push('根节点')
            }
            if (this.parentId === undefined) {
              treeManagement.addTreeManagement(this.leaf).then(response => {
                this.$modal.msgSuccess('新增成功')
                this.open = false
                this.getList()
              })
            } else {
              let node = {
                parentId: this.parentId,
                subLeaf: this.leaf
              }
              treeManagement.addSubLeaf(node).then(response => {
                this.$modal.msgSuccess('新增子节点成功')
                this.open = false
                this.getList()
              })
            }
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除知识树管理编号为"' + row.leafId + '"的数据项？').then(function() {
        return treeManagement.delTreeManagement(row.leafId)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>

<style scoped>

</style>
