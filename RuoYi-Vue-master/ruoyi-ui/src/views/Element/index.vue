<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--分类树数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="filterText"
            clearable
            placeholder="请输入产品分类名称"
            prefix-icon="el-icon-search"
            size="small"
            style=" margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            ref="tree"
            :data="elementTree"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :props="defaultProps"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
        <div class="head-container">
          <el-divider></el-divider>
          <el-tree
            ref="productTree"
            :data="products"
            :expand-on-click-node="false"
            :props="productProps"
            default-expand-all
            @node-click="handleProductClick"
            @node-contextmenu="showContextMenu"
          />
        </div>
      </el-col>
      <!--产品数据-->
      <el-col :span="20" :xs="24">
        <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px"
                 size="small"
        >
          <el-form-item label="结构名称" prop="elementName">
            <el-input
              v-model="queryParams.originElement.elementName"
              clearable
              placeholder="请输入结构名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="结构描述" prop="elementDescription">
            <el-input
              v-model="queryParams.originElement.elementDescription"
              clearable
              placeholder="请输入结构描述"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="只看典型">
            <template slot-scope="scope">
                <el-switch
                  v-model="typical"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
                </el-switch>
            </template>
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
          <el-col :span="1.5">
            <el-button
              icon="el-icon-upload2"
              plain
              size="mini"
              type="info"
              @click="handleImport"
            >导入
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              icon="el-icon-download"
              plain
              size="mini"
              type="warning"
              @click="handleExport"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :columns="columns" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <ModifyElement ref="modifyElement"
                       :dialog="modifyElementShow"
                       :selectElementId="selectElementId"
                       :pId="productId"
                       :proId="parentId"
                       :title="title"
                       @closeDialog="() =>{ this.modifyElementShow = false }"
                       @restore="() =>{this.selectElement = {}}"
        />
        <constrains ref="constrains"
                    :dialog="modifyConstraintShow"
                    :selectElement="selectElement"
                    :title="title"
                    @closeDialog="() =>{ this.modifyConstraintShow = false }"
                    @restore="() =>{this.selectElement = {}}"
        />

        <el-table v-loading="loading" :data="elements" row-key="elementId"
                  :tree-props="{children: 'subElements', hasChildren: 'hasChildren'}" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="elementId" align="center" label="结构编号" prop="elementId"/>
          <el-table-column v-if="columns[1].visible" key="elementName" :show-overflow-tooltip="true" align="center"
                           label="结构名称" prop="elementName"
          />
          <el-table-column v-if="columns[2].visible" key="elementQuantity" :show-overflow-tooltip="true" align="center"
                           label="结构数量" prop="elementQuantity"
          />
          <el-table-column v-if="columns[3].visible" key="elementDescription" align="center" label="结构描述"
                           prop="elementDescription"
          />
          <el-table-column v-if="columns[4].visible" key="elementSource" :show-overflow-tooltip="true" align="center"
                           label="结构来源" prop="elementSource"
          >
            <template slot-scope="scope">
              <el-radio v-model="scope.row.elementSource" label="自制">自制</el-radio>
              <el-radio v-model="scope.row.elementSource" label="外源">外源</el-radio>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[5].visible" key="elementDensity" :show-overflow-tooltip="true" align="center"
                           label="结构密度" prop="elementDensity"
          />
          <el-table-column v-if="columns[6].visible" key="elementWetArea" align="center" label="表面积"
                           prop="elementWetArea" width="120"
          />
          <el-table-column v-if="columns[7].visible" key="elementVolume" align="center" label="体积"
                           prop="elementVolume" width="160"
          />
          <el-table-column v-if="columns[8].visible" key="elementMass" align="center" label="质量"
                           prop="elementMass" width="160"
          />
          <el-table-column v-if="columns[9].visible" key="elementBoundingBox" align="center" label="包容盒"
                           prop="elementBoundingBox" width="160"
          />
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.elementId !== 1" slot-scope="scope">
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
              <el-dropdown size="mini"
                           @command="(command) => handleCommand(command, scope.row)"
              >
                <span class="el-dropdown-link">
                  <i class="el-icon-d-arrow-right el-icon--right"></i>更多
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="handleConstraints" icon="el-icon-key">查看约束</el-dropdown-item>
                  <el-dropdown-item command="handleModel" icon="el-icon-circle-check">查看模型</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :limit.sync="queryParams.pageSize"
          :page.sync="queryParams.pageNum"
          :total="total"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <el-dialog title="轻模型展示" :visible.sync="modelShow" append-to-body >
      <iframe :src="fileUrl" frameborder="0" style="width: 800px;height: 600px"></iframe>
    </el-dialog>

    <!-- 产品导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" append-to-body width="400px">
      <el-upload
        ref="upload"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :auto-upload="false"
        :disabled="upload.isUploading"
        :headers="upload.headers"
        :limit="1"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        accept=".xlsx, .xls"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div slot="tip" class="el-upload__tip text-center">
          <div slot="tip" class="el-upload__tip">
            <el-checkbox v-model="upload.updateSupport"/>
            是否更新已经存在的产品数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link :underline="false" style="font-size:12px;vertical-align: baseline;" type="primary"
                   @click="importTemplate"
          >下载模板
          </el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import * as elementManagement from '@/api/system/elementManagement'
import constrains from '@/views/Element/components/constrains.vue'
import { getToken } from '@/utils/auth'
import * as treeManagement from '@/api/system/treeManagement'
import ModifyElement from '@/views/Element/components/modifyElement.vue'
import { getAllElementsByParams } from '@/api/system/elementManagement'

export default {
  name: 'index',
  components: { ModifyElement, constrains },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  created() {
    this.getList()
    this.getTreeselect()
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: '',
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: 'Bearer ' + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + '/element/importData'
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'elementId',
        sortType: 'ascending',
        dynamicLabel: '',
        originElement: {
          elementId: 0,
          elementName: '',
          dynamicLabels: [],
          elementDescription: '',
          elementSource: ''
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `结构编号`, visible: true },
        { key: 1, label: `结构名称`, visible: true },
        { key: 2, label: `结构描述`, visible: true },
        { key: 3, label: `结构数量`, visible: true },
        { key: 4, label: `结构密度`, visible: true },
        { key: 5, label: `结构来源`, visible: true },
        { key: 6, label: `表面积`, visible: true },
        { key: 7, label: `体积`, visible: true },
        { key: 8, label: `质量`, visible: true },
        { key: 9, label: `包容盒`, visible: true }
      ],
      title: '',
      modifyElementShow: false,
      modifyConstraintShow: false,
      modelShow: false,
      showSearch: true,
      selectId: 0,
      tableHeight: 0,
      selectLabel: '',
      filterText: '',
      elementOptions: [],
      products: [],
      elements: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      productProps: {
        children: 'subElements',
        label: 'elementName'
      },
      treeProps: {
        hasChildren: 'hasSubElements',
        children: 'subElements'
      },
      selectElement: {},
      model: {
        fileId:0,
        fileName:"轻模型-测试1",
        fileUrl:"D:\\Desktop\\请模型\\assem.html"
      },
      fileUrl:"http://localhost:8080/profile/upload/2023/05/02/assem.html",
      modifyState: false,
      elementTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
      typical:false,
      selectElementId:0,
      productId:0,
      parentId:0,
    }
  },

  methods: {

    /** 查询问题列表 */
    getList() {
      this.loading = true
      if (this.queryParams.dynamicLabel === '') {
        elementManagement.getAllProducts(this.queryParams).then(result => {
            if (result.code === 200) {
              this.products = result.data
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== '') {
        elementManagement.getProductListByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.elements = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.originProblem.elementName !== '' || this.queryParams.originProblem.elementDescription !== '') {
        elementManagement.getAllElementsByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.elements = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      }

    },
    /** 查询知识下拉树结构 */
    getTreeselect() {
      treeManagement.getTreeManagement(25877).then(response => {
        this.elementTree = []
        console.log(response.data)
        this.elementTree.push(response.data)
      })
    },
    // 筛选分类节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 分类树节点单击事件
    handleNodeClick(data) {
      this.queryParams.dynamicLabel = data.leafValue
      this.loading = true
      elementManagement.getProductListByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.products = result.data
            this.loading = false
          }
        }
      )
    },
    //产品树节点单击
    handleProductClick(data) {
      this.productId = data.elementId
      this.parentId = data.elementId
      // this.queryParams.originElement.elementId = data.elementId
      this.loading = true
      elementManagement.getProductById(data.elementId).then(result => {
          if (result.code === 200) {
            this.elements = result.data.subElements
            this.loading = false
          }
        }
      )
    },
    handleCommand(command, data) {
      switch (command) {
        case 'handleConstraints':
          this.handleConstraints(data)
          break
        case 'handleModel':
          this.handleModel(data)
          break
        default:
          break
      }
    },

    handleConstraints(data) {
      this.modifyConstraintShow = true
      this.selectElement = data
    },
    handleModel(data) {
      this.modelShow = true
      console.log(44444)
      this.model = data.model
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      // this.queryParams.originElement.dynamicLabels.push('AssemblyProduct')
      this.loading = true
      elementManagement.getAllElementsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.products = result.data
            this.loading = false
          }
        }
      )
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      // this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.elementId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    showContextMenu(event, product, node, nodeComponent) {
      event.preventDefault()
      let x = event.clientX
      let y = event.clientY
      // 获得当前位置
      this.contextMenuData.axis = {
        x, y
      }
      this.contextMenuData.menulists = this.tableMenu
      this.selectElement = product
    },
    show3DModel(){
      this.model = this.selectElement.modelFile;
      this.modelShow =true;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.modifyElementShow = true
      this.title = '创建元素'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyElementShow = true
      this.title = '修改元素'
      this.selectElementId = row.elementId
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const elementIds = row.elementId || this.ids
      this.$modal.confirm('是否确认删除结构编号为"' + elementIds + '"的数据项？').then(function() {
        return elementManagement.deleteElements(elementIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('element/export', {
        ...this.queryParams
      }, `element_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '原则导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/element/importTemplate', {}, `element_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false
      this.upload.isUploading = false
      this.$refs.upload.clearFiles()
      this.$alert('<div style=\'overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;\'>' + response.msg + '</div>', '导入结果', { dangerouslyUseHTMLString: true })
      this.getList()
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit()
    }
  }

}
</script>

<style scoped>

</style>
