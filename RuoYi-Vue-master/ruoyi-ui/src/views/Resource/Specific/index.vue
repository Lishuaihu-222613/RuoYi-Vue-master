<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="filterText"
            clearable
            placeholder="请输入分类名称"
            prefix-icon="el-icon-search"
            size="small"
            style=" margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            ref="tree"
            :data="labelTree"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :props="defaultProps"
            default-expand-all
            node-key="leafId"
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px"
                 size="small"
        >
          <el-form-item label="资源名称" prop="resourceName">
            <el-input
              v-model="queryParams.resourceName"
              clearable
              placeholder="请输入资源名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="资源描述" prop="resourceDescription">
            <el-input
              v-model="queryParams.resourceDescription"
              clearable
              placeholder="请输入资源内容"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="存在地址" prop="site">
            <el-input
              v-model="queryParams.site"
              clearable
              placeholder="请输入存在地址"
              style="width: 240px"
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

        <vue-context-menu :contextMenuData="contextMenuData"
                          @createResource="createResource"
                          @deleteResource="deleteResource"
                          @updateResource="updateResource"
        >
        </vue-context-menu>


        <el-table v-loading="loading" :data="resources" @selection-change="handleSelectionChange"
                  @row-contextmenu="showContextMenu"
        >
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="resourceId" align="center"
                           label="资源编号"
                           prop="resourceId"
          />
          <el-table-column v-if="columns[1].visible" key="resourceName" :show-overflow-tooltip="true"
                           align="center"
                           label="资源名称"
                           prop="resourceName"
          />
          <el-table-column v-if="columns[2].visible" key="resourceDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="资源描述" prop="resourceDescription"
          />
          <el-table-column v-if="columns[3].visible" key="site" :show-overflow-tooltip="true"
                           align="center"
                           label="存在地址" prop="site"
          />

          <el-table-column v-if="columns[4].visible" key="capacityDescriptions" :show-overflow-tooltip="true"
                           align="center"
                           label="能力属性" prop="capacityDescriptions"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.capacityDescriptions" :key="key" :index="key+''" type="success">
                {{ key + ':' + scope.row.capacityDescriptions[key+''] }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.resourceId !== 1" slot-scope="scope">
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

        <pagination
          v-show="total>0"
          :limit.sync="queryParams.pageSize"
          :page.sync="queryParams.pageNum"
          :total="total"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="modifyResourceShow" append-to-body width="600px">
      <el-form ref="resource" :model="selectResource"  label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="资源名称" prop="resourceName">
              <el-input v-model="selectResource.resourceName" maxlength="30" placeholder="请输入资源名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资源类别" prop="deptId">
              <treeselect v-model="selectResource.resourceTypes" :multiple="true" :options="labelTree"
                          :normalizer="normalizer"
                          :show-count="true" placeholder="请选择资源类别"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="存在地址" prop="site">
              <el-input v-model="selectResource.site" placeholder="请输入存在地址"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资源描述" prop="resourceDescription">
              <el-input v-model="selectResource.resourceDescription" placeholder="请输入资源描述" type="textarea"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="资源能力">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newCapacityName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line">--</el-col>
                <el-col :span="8">
                  <el-input v-model="newCapacityValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addCapacityDescriptions">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in selectResource.capacityDescriptions"
                      :key="name"
                      :gutter="20"
              >
                <el-col :span="6">
                  <el-input v-model="name" placeholder="名称" :disabled="true"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="6">
                  <el-input v-model="selectResource.capacityDescriptions[name]" placeholder="内容" ></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="text" @click.prevent="removeCapacityDescriptions(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 资源导入对话框 -->
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
            是否更新已经存在的用户数据
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

import { getToken } from '@/utils/auth'
import * as resourceManagement from '@/api/system/resourceManagement'
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'index',
  components: { Treeselect },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
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
        url: process.env.VUE_APP_BASE_API + '/SpecialTool/importData'
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'resourceId',
        sortType: 'ascending',
        dynamicLabel: '',
        originResource: {
          resourceId: 0,
          resourceName: '',
          resourceTypes: [],
          resourceDescription: '',
          sites: ''
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `资源编号`, visible: true },
        { key: 1, label: `资源名称`, visible: true },
        { key: 2, label: `资源描述`, visible: true },
        { key: 3, label: `存在地址`, visible: true },
        { key: 4, label: `能力属性`, visible: true }
      ],
      title: '',
      modifyResourceShow: false,
      showSearch: true,
      selectId: 0,
      tableHeight: 0,
      selectLabel: '',
      contextMenuData: {
        menuName: 'resourceManage',
        axis: {
          x: null,
          y: null
        },
        menulists: []
      },
      tableMenu: [
        {
          fnHandler: 'createResource', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建专用资源' // 菜单名称
        },
        {
          fnHandler: 'updateResource',
          icoName: 'el-icon-search',
          btnName: '修改专用资源'
        },
        {
          fnHandler: 'deleteResource',
          icoName: 'el-icon-paperclip',
          btnName: '删除专用资源'
        }
      ],
      filterText: '',
      resources: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      selectResource: {
        resourceId: undefined,
        resourceName: '',
        resourceTypes: [],
        resourceDescription: '',
        site: '',
        capacityDescriptions: {},
      },
      modifyState: false,
      labelTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
      newCapacityName:'',
      newCapacityValue:''
    }
  },

  created() {
    this.getList()
    this.getTreeselect()
  },

  methods: {

    /** 查询资源列表 */
    getList() {
      this.loading = true
      if (this.queryParams.dynamicLabel === '') {
        resourceManagement.getAllSpecialTools(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.resources = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== '') {
        resourceManagement.getAllSpecialToolsByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.resources = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.originResource.resourceName !== '' || this.queryParams.originResource.resourceDescription !== '' || this.queryParams.originResource.resourceUsage !== '') {
        resourceManagement.getSpecialToolsByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.resources = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      }

    },
    /** 查询知识下拉树结构 */
    getTreeselect() {
      treeManagement.getTreeManagement(25781).then(response => {
        console.log(response.data)
        this.labelTree.push(response.data)
      })
    },
    normalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs;
      }
      return {
        id: node.leafName,
        label: node.leafName,
        value:node.leafValue,
        children: node.subLeafs
      }
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.dynamicLabel = data.leafName
      this.loading = true
      this.queryParams.sortableField = "n.label"
      resourceManagement.getAllSpecialToolsByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.resources = result.data.content
            this.total = result.data.totalElements
            this.loading = false
          }
        }
      )
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.loading = true
      resourceManagement.getSpecialToolsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.resources = result.data.content
            this.total = result.data.totalElements
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
      this.ids = selection.map(item => item.resourceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    showContextMenu(row, column, event) {
      event.preventDefault()
      let x = event.clientX
      let y = event.clientY
      // 获得当前位置
      this.contextMenuData.axis = {
        x, y
      }
      this.contextMenuData.menulists = this.tableMenu
      this.selectResource = row
    },
    createResource() {
      this.modifyResourceShow = true
      this.modifyState = false
    },
    updateResource(data) {
      this.modifyResourceShow = true
      this.modifyState = true
    },
    deleteResource() {
      resourceManagement.deleteResource(this.selectResource.resourceId).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('删除资源成功！')
        }
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyResourceShow = true
      this.title = '新增资源'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyResourceShow = true
      this.title = '修改资源'
      this.selectResource = row
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const resourceIds = row.resourceId || this.ids
      this.$modal.confirm('是否确认删除资源编号为"' + resourceIds + '"的数据项？').then(function() {
        return resourceManagement.deleteResource(resourceIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('resource/export', {
        ...this.queryParams
      }, `resource_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '资源导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/user/importTemplate', {}, `user_template_${new Date().getTime()}.xlsx`)
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
    },
    addCapacityDescriptions() {
      this.$set(this.selectResource.capacityDescriptions,this.newCapacityName+"",this.newCapacityValue+"")
    },
    removeCapacityDescriptions(key) {
      this.$delete(this.selectResource.capacityDescriptions,key+"")
    },
    submitForm: function() {
      this.$refs['resource'].validate(valid => {
        if (valid) {
          if (this.selectResource.resourceId !== undefined) {
            resourceManagement.updateSpecialTool(this.selectResource).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            resourceManagement.createSpecialTool(this.selectResource).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    cancel() {
      this.modifyResourceShow = false
      this.reset()
    },
    reset() {
      this.selectResource = {
        resourceId: undefined,
        resourceName: '',
        resourceTypes: [],
        resourceDescription: '',
        site: '',
        capacityDescriptions: {},
      }
      this.resetForm('resource')
    }
  }
}
</script>

<style scoped>

</style>
