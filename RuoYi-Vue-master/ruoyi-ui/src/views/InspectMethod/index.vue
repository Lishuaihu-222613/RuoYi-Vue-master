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
            :data="methodTree"
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
          <el-form-item label="方法名称" prop="methodName">
            <el-input
              v-model="queryParams.methodName"
              clearable
              placeholder="请输入方法名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="方法描述" prop="methodDescription">
            <el-input
              v-model="queryParams.methodDescription"
              clearable
              placeholder="请输入方法描述"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="方法原则" prop="methodDescription">
            <el-input
              v-model="queryParams.methodDescription"
              clearable
              placeholder="请输入方法原则"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="未来效果" prop="methodDescription">
            <el-input
              v-model="queryParams.methodDescription"
              clearable
              placeholder="请输入未来效果"
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
                          @createMethod="createMethod"
                          @deleteMethod="deleteMethod"
                          @updateMethod="updateMethod"
        >
        </vue-context-menu>

        <modifyMethod ref="modifyMethod"
                       :dialog="modifyMethodShow"
                       :selectMethod="selectMethod"
                       :title="title"
                       @closeDialog="() =>{ this.modifyMethodShow = false }"
                       @restore="() =>{this.selectMethod = {}}"
        >
        </modifyMethod>

        <el-table v-loading="loading" :data="methods" @selection-change="handleSelectionChange"
                  @row-contextmenu="showContextMenu"
        >
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="methodId" align="center" label="方法编号" prop="methodId"/>
          <el-table-column v-if="columns[1].visible" key="methodName" :show-overflow-tooltip="true" align="center"
                           label="方法名称"
                           prop="methodName"
          />
          <el-table-column v-if="columns[2].visible" key="methodDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="方法内容" prop="methodDescription"
          />
          <el-table-column v-if="columns[3].visible" key="methodPrinciple" :show-overflow-tooltip="true"
                           align="center"
                           label="方法原则" prop="methodPrinciple"
          >
          </el-table-column>
          <el-table-column v-if="columns[4].visible" key="futureExpansion" :show-overflow-tooltip="true"
                           align="center"
                           label="未来效果" prop="futureExpansion"
          >
          </el-table-column>
          <el-table-column v-if="columns[5].visible" key="methodConditions" :show-overflow-tooltip="true" align="center"
                           label="检验条件"
                           prop="methodConditions"
          >
            <template slot-scope="scope">
              <el-tag v-for="(item,index) in scope.row.methodConditions" :key="index" :index="index+''" type="success">
                {{ item.conditionName + ':' + item.conditionDescription }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[6].visible" key="methodFactors" :show-overflow-tooltip="true" align="center"
                           label="影响因素"
                           prop="methodFactors"
          >
            <template slot-scope="scope">
              <el-tag v-for="(item,index) in scope.row.methodFactors" :key="index" :index="index+''" type="success">
                {{ item.methodFactors + ':' + item.methodFactors }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[7].visible" key="methodModes" :show-overflow-tooltip="true" align="center"
                           label="检测模式"
                           prop="methodModes"
          >
            <template slot-scope="scope">
              <el-tag v-for="(item,index) in scope.row.methodModes" :key="index" :index="index+''" type="success">
                {{ item.modeName + ':' + item.modeDescription }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.methodId !== 1" slot-scope="scope">
              <el-button
                icon="el-icon-edit"
                size="mini"
                type="text"
                @click="handleUpdate(scope.row)"
              >修改
              </el-button>
              <el-button
                icon="el-icon-search"
                size="mini"
                type="text"
                @click="handleRelation(scope.row)"
              >关联信息
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
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'
import { getToken } from '@/utils/auth'
import * as methodManagement from '@/api/system/inspectMethodManagement'
import ModifyMethod from '@/views/InspectMethod/components/modifyMethod.vue'
import RelatedWindows from '@/views/InspectMethod/components/relatedWindows.vue'
export default {
  name: 'index',

  components: { ModifyMethod, Treeselect, RelatedWindows},

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
        url: process.env.VUE_APP_BASE_API + '/method/importData'
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'methodId',
        sortType: 'ascending',
        dynamicLabel: "",
        originMethod:{
          methodId:0,
          methodName:'',
          dynamicLabels:[],
          methodDescription:"",
          methodPrinciple:'',
          futureExpansion:"",
          methodConditions:[],
          methodFactors:[],
          methodModes:[],
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `方法编号`, visible: true },
        { key: 1, label: `方法名称`, visible: true },
        { key: 2, label: `方法描述`, visible: true },
        { key: 3, label: `方法原则`, visible: true },
        { key: 4, label: `未来效果`, visible: true },
        { key: 5, label: `检验条件`, visible: true },
        { key: 6, label: `影响因素`, visible: true },
        { key: 7, label: `检测模式`, visible: true },
      ],
      title: '',
      modifyMethodShow: false,
      relatedWindowShow: false,
      showSearch: true,
      selectId: 0,
      tableHeight: 0,
      selectLabel: '',
      contextMenuData: {
        menuName: 'methodManage',
        axis: {
          x: null,
          y: null
        },
        menulists: []
      },
      tableMenu: [
        {
          fnHandler: 'createMethod', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建方法' // 菜单名称
        },
        {
          fnHandler: 'updateMethod',
          icoName: 'el-icon-search',
          btnName: '修改检测方法'
        },
        {
          fnHandler: 'deleteMethod',
          icoName: 'el-icon-paperclip',
          btnName: '删除检测方法'
        }
      ],
      filterText: '',
      methods: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      selectMethod: {},
      modifyState: false,
      methodTree: [],
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

    /** 查询方法列表 */
    getList() {
      this.loading = true
      if(this.queryParams.dynamicLabel === ''){
        methodManagement.getAllInspectMethods(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.methods = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== ''){
        methodManagement.getAllInspectMethodsByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.methods = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if(this.queryParams.originMethod.methodName !== '' || this.queryParams.originMethod.methodDescription !== ''){
        methodManagement.getInspectMethodsByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.methods = result.data.content
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
        this.methodTree.push(response.data)
      })
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.dynamicLabel = data.leafValue
      this.loading = true
      methodManagement.getAllInspectMethodsByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.methods = result.data.content
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
      methodManagement.getInspectMethodsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.methods = result.data.content
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
      this.ids = selection.map(item => item.methodId)
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
      this.selectMethod = row
    },
    createMethod() {
      this.modifyMethodShow = true
      this.modifyState = false
    },
    updateMethod(data) {
      this.modifyMethodShow = true
      this.modifyState = true
    },
    openRelationWindow(){
      this.relationWindow = true
    },
    deleteMethod() {
      methodManagement.deleteInspectMethod(this.selectId).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('删除原则成功！')
        }
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyMethodShow = true;
      this.title = "新增原则"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyMethodShow = true;
      this.title = "修改原则";
      this.selectMethod = row;
    },
    handleRelationWindow(row) {
      this.relationWindow = true;
      this.title = "关联信息"
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const methodIds = row.methodId || this.ids
      this.$modal.confirm('是否确认删除原则编号为"' + methodIds + '"的数据项？').then(function() {
        return methodManagement.deleteInspectMethod(methodIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('method/export', {
        ...this.queryParams
      }, `method_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '原则导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/method/importTemplate', {}, `method_template_${new Date().getTime()}.xlsx`)
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
