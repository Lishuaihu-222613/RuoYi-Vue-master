<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--分类树数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="filterText"
            clearable
            placeholder="请输入工艺分类名称"
            prefix-icon="el-icon-search"
            size="small"
            style=" margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            ref="tree"
            :data="labelOptions"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :props="defaultProps"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
        <div class="head-container">
          <el-row>
            <el-col :span="12" :offset="6" >
              <span>工艺列表</span>
            </el-col>
          </el-row>
          <el-divider></el-divider>
          <el-tree
            ref="processTree"
            :data="processes"
            :expand-on-click-node="false"
            :props="processProps"
            default-expand-all
            @node-click="handleProcessClick"
          />
        </div>
      </el-col>
      <!--工艺数据-->
      <el-col :span="20" :xs="24">
        <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px"
                 size="small"
        >
          <el-form-item label="工艺名称" prop="elementName">
            <el-input
              v-model="queryParams.originElement.elementName"
              clearable
              placeholder="请输入工艺名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="工艺描述" prop="elementDescription">
            <el-input
              v-model="queryParams.originElement.elementDescription"
              clearable
              placeholder="请输入工艺描述"
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

        <modifyElement ref="modifyElement"
                       :dialog="modifyElementShow"
                       :selectElement="selectElement"
                       :title="title"
                       @closeDialog="() =>{ this.modifyElementShow = false }"
                       @restore="() =>{this.selectElement = {}}"
        />
        <relationWindow ref="constrains"
                    :dialog="modifyConstraintShow"
                    :selectElement="selectElement"
                    :title="title"
                    @closeDialog="() =>{ this.modifyConstraintShow = false }"
                    @restore="() =>{this.selectElement = {}}"
        />

        <el-table v-loading="loading" :data="elements" row-key="elementId" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="elementId" align="center" label="元素编号" prop="elementId"/>
          <el-table-column v-if="columns[1].visible" key="elementNumber" :show-overflow-tooltip="true" align="center"
                           label="元素序号" prop="elementNumber"
          />
          <el-table-column v-if="columns[2].visible" key="elementName" :show-overflow-tooltip="true" align="center"
                           label="元素名称" prop="elementName"
          />
          <el-table-column v-if="columns[3].visible" key="elementDescription" :show-overflow-tooltip="true" align="center"
                           label="内容描述"
                           prop="elementDescription"
          />
          <el-table-column v-if="columns[4].visible" key="elementRemark" :show-overflow-tooltip="true" align="center"
                           label="备注" prop="elementRemark"
          >
            <template slot-scope="scope">
              <el-tag v-for="(item,index) in scope.row.elementRemark" :key="index" :index="index+''" type="success">
                {{ scope.row.elementRemark[index] }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[5].visible" key="elementRequirements" :show-overflow-tooltip="true" align="center"
                           label="相关要求" prop="elementRequirements"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.elementRequirements" :key="key" :index="key+''" type="success">
                {{ key + ':' + scope.row.elementRequirements[key+''] }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[6].visible" key="elementOtherProperties" :show-overflow-tooltip="true" align="center"
                           label="其他属性"
                           prop="elementOtherProperties"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.elementOtherProperties" :key="key" :index="key+''" type="success">
                {{ key + ':' + scope.row.elementOtherProperties[key+''] }}
              </el-tag>
            </template>
          </el-table-column>

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
              <el-button
                icon="el-icon-key"
                size="mini"
                type="text"
                @click="handleRelations(scope.row)"
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
            是否更新已经存在的工艺数据
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

import * as processManagement from '@/api/system/processManagement'
import modifyElement from '@/views/Process/components/modifyElement.vue'
import RelationWindow from '@/views/Process/components/relationWindow.vue'
import { getToken } from '@/utils/auth'
import * as treeManagement from '@/api/system/treeManagement'
import { getAllProcess } from '@/api/system/processManagement'

export default {
  name: 'index1',
  components: { modifyElement, RelationWindow },

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
        url: process.env.VUE_APP_BASE_API + '/process/importData'
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
        { key: 0, label: `元素编号`, visible: true },
        { key: 1, label: `元素序号`, visible: true },
        { key: 2, label: `元素名称`, visible: true },
        { key: 3, label: `内容描述`, visible: true },
        { key: 4, label: `备注`, visible: true },
        { key: 5, label: `相关要求`, visible: true },
        { key: 6, label: `其他属性`, visible: true }
      ],
      title: '',
      modifyElementShow: false,
      modifyConstraintShow: false,
      modelShow: false,
      showSearch: true,
      selectId: 0,
      selectLabel: '',
      filterText: '',
      labelOptions: [],
      processes: [],
      elements:[],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      processProps: {
        children: 'subElements',
        label: 'elementName'
      },
      treeProps: {
        hasChildren: 'hasSubElements',
        children: 'subElements'
      },
      selectElement: {},
      model: {},
      modifyState: false,
      elementTree: [],
      dialog: false,
      total: 0,
      currentPage: 1
    }
  },

  methods: {

    /** 查询问题列表 */
    getList() {
      this.loading = true
      if (this.queryParams.dynamicLabel === '') {
        processManagement.getAllProcess(this.queryParams).then(result => {
            if (result.code === 200) {
              this.processes = result.data
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== '') {
        processManagement.getAllProcessByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.processes = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.originElement.elementName !== '' || this.queryParams.originElement.elementDescription !== '') {
        processManagement.getAllElementsByParams(this.queryParams).then(result => {
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
      treeManagement.getTreeManagement(25500).then(response => {
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
      this.queryParams.sortableField = "n.label"
      processManagement.getAllProcessByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.processes = result.data
            this.loading = false
          }
        }
      )
    },
    //产品树节点单击
    handleProductClick(data) {
      this.queryParams.originElement.elementId = data.elementId
      this.loading = true
      processManagement.getProcessById(this.queryParams).then(result => {
          if (result.code === 200) {
            this.elements = []
            this.elements.push(result.data)
            this.loading = false
          }
        }
      )
    },

    handleRelations(data) {
      this.modifyConstraintShow = true
      this.selectId = data.elementId
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.loading = true
      processManagement.getAllElementsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.elements = []
            this.elements.push(result.data)
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

    /** 新增按钮操作 */
    handleAdd() {
      this.modifyElementShow = true
      this.title = '新增元素'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyElementShow = true
      this.title = '修改元素'
      this.selectId = row.elementId
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const elementIds = row.elementId || this.ids
      this.$modal.confirm('是否确认删除编号为"' + elementIds + '"的数据项？').then(function() {
        return processManagement.deleteElements(elementIds)
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

