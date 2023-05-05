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
            node-key="leafId"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :props="defaultProps"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px"
                 size="small"
        >
          <el-form-item label="文件名称" prop="fileName">
            <el-input
              v-model="queryParams.originFile.fileName"
              clearable
              placeholder="请输入文件名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="文件描述" prop="fileDescription">
            <el-input
              v-model="queryParams.originFile.fileDescription"
              clearable
              placeholder="请输入文件描述"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="文件类型" prop="fileType">
            <el-input
              v-model="queryParams.originFile.fileType"
              clearable
              placeholder="请输入文件类型"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="经理人" prop="manager">
            <el-input
              v-model="queryParams.originFile.manager"
              clearable
              placeholder="请输入经理人"
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


<!--        <FilePreview :url="this.fileUrl" :visible="this.dialogVisible"/>-->

        <el-table v-loading="loading" :data="files" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="fileId" align="center" label="文件编号" prop="fileId"/>
          <el-table-column v-if="columns[1].visible" key="fileName" :show-overflow-tooltip="true" align="center"
                           label="文件名称"
                           prop="fileName"
          />
          <el-table-column v-if="columns[2].visible" key="fileType" :show-overflow-tooltip="true"
                           align="center"
                           label="文件类型" prop="fileType"
          />
          <el-table-column v-if="columns[3].visible" key="fileSize" :show-overflow-tooltip="true"
                           align="center"
                           label="文件大小" prop="fileSize"
          >
          </el-table-column>
          <el-table-column v-if="columns[4].visible" key="fileDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="文件描述" prop="fileDescription"
          />
          <el-table-column v-if="columns[5].visible" key="manager" :show-overflow-tooltip="true" align="center"
                           label="经理人"
                           prop="manager"
          />
          <el-table-column v-if="columns[6].visible" key="modifyTime" :show-overflow-tooltip="true" align="center"
                           label="修改时间"
                           prop="modifyTime"
          >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.modifyTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.fileId !== 1" slot-scope="scope">
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
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleDownload(scope.row)"
              >下载
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

    <el-dialog :title="title" :visible.sync="modifyfileShow" width="600px" append-to-body>
      <el-form ref="form" :model="file"  label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件名称" prop="fileName">
              <el-input v-model="file.fileName" placeholder="请输入文件名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="文件类型" prop="fileType">
              <el-input v-model="file.fileType" placeholder="请输入文件类型"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件大小" prop="fileSize">
              <el-input v-model="file.fileSize" placeholder="请输入文件大小" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类标签" prop="fileClassification">
              <treeselect v-model="file.fileClassification" :options="labelTree" :show-count="true" placeholder="请选择分类标签" :normalizer="normalizer" :multiple="true"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="经理人" prop="manager">
              <el-input v-model="file.manager" placeholder="请输入经理人"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="修改时间" prop="modifyTime">
                <el-date-picker type="date" placeholder="选择日期" v-model="file.modifyTime"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="文件上传" prop="fileUrl">
            <el-upload
              ref="upload"
              :limit="1"
              accept=".jpg, .png, .pdf, .docx, .doc"
              :action="upload.url"
              :headers="upload.headers"
              :file-list="upload.fileList"
              :on-progress="handleFileUploadProgress"
              :on-success="handleFileSuccess"
              :auto-upload="false"
            >
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
              <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading"
                         @click="submitFileForm"
              >上传到服务器
              </el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png/pdf/docx/doc文件，且不超过500kb</div>
            </el-upload>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 方法导入对话框 -->
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
import * as fileManagement from '@/api/system/fileManagement'
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import { getToken } from "@/utils/auth";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: 'index',
  components: { Treeselect },
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
        url: process.env.VUE_APP_BASE_API + '/common/upload',
        // 上传的文件列表
        fileList: []
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'fileId',
        sortType: 'ascending',
        dynamicLabel: "",
        originFile:{
          fileName:'',
          fileType:'',
          fileDescription:"",
          manager:'',
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `文件编号`, visible: true },
        { key: 1, label: `文件名称`, visible: true },
        { key: 2, label: `文件类型`, visible: true },
        { key: 3, label: `文件大小`, visible: true },
        { key: 4, label: `文件描述`, visible: true },
        { key: 5, label: `经理人`, visible: true },
        { key: 6, label: `修改时间`, visible: true },
      ],
      title: '',
      modifyfileShow: false,
      showSearch: true,
      selectId: 0,
      tableHeight: 0,
      selectLabel: '',
      filterText: '',
      files: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      file:{
        fileId:0,
        fileName:'',
        fileType:'',
        fileSize:'',
        fileDescription:'',
        fileClassification:[],
        fileUrl:'',
        manager:'',
        modifyTime:'',
      },
      modifyState: false,
      labelTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
    };
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  created() {
    this.getList();
    this.getTreeselect();
  },

  methods: {

    /** 查询问题列表 */
    getList() {
      this.loading = true
      if(this.queryParams.dynamicLabel === ''){
        fileManagement.getAllFiles(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.files = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== ''){
        fileManagement.getAllFilesByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.files = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if(this.queryParams.originFile.fileName !== '' || this.queryParams.originFile.fileDescription !== '' ||this.queryParams.originFile.fileType !=='' || this.queryParams.originFile.manager !== ''){
        fileManagement.getFilesByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.files = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      }

    },
    /** 查询知识下拉树结构 */
    getTreeselect() {
      treeManagement.getTreeManagement(25843).then(response => {
        console.log(response.data)
        this.labelTree.push(response.data)
      })
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
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
      fileManagement.getAllFilesByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.files = result.data.content
            this.total = result.data.totalElements
            this.loading = false
          }
        }
      )
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.loading = true
      this.queryParams.sortableField = "n.label"
      fileManagement.getFilesByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.files = result.data.content
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
      this.ids = selection.map(item => item.problemId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyfileShow = true;
      this.title = "新增文件"
      this.reset();
      this.upload.fileList = []
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fileId = row.fileId
      fileManagement.getFileInfoById(fileId).then(response => {
        this.file = response.data
        this.open = true
        this.title = '修改文件信息'
        this.upload.fileList = [{ name: this.file.fileName, url: this.file.fileUrl }]
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fileIds = row.fileId || this.ids
      this.$modal.confirm('是否确认删除编号为"' + fileIds + '"的数据项？').then(function() {
        return fileManagement.fileDelete(fileIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    handleDownload(row){
      let name = row.fileName
      let url = row.fileUrl
      let suffix = url.substring(url.lastIndexOf('.'), url.length)
      const a = document.createElement('a')
      a.setAttribute('download', name + suffix)
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('problem/export', {
        ...this.queryParams
      }, `problem_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '原则导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/problem/importTemplate', {}, `problem_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false
      this.upload.isUploading = false
      this.file.fileUrl = response.url
      this.file.fileName = response.originalFilename
      this.$refs.upload.clearFiles()
      this.$alert('<div style=\'overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;\'>' + response.msg + '</div>', '导入结果', { dangerouslyUseHTMLString: true })
      this.getList()
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit()
    },
    cancel(){

    },
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.file.fileId != null) {
            fileManagement.updateFile(this.file).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            fileManagement.createFile(this.file).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
