<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="filterText"
            clearable
            placeholder="请输入配方分类名称"
            prefix-icon="el-icon-search"
            size="small"
            style=" margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            ref="tree"
            :data="prescriptionTree"
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
          <el-form-item label="配方名称" prop="prescriptionName">
            <el-input
              v-model="queryParams.originPrescription.prescriptionName"
              clearable
              placeholder="请输入配方名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="配方描述" prop="prescriptionDescription">
            <el-input
              v-model="queryParams.originPrescription.prescriptionDescription"
              clearable
              placeholder="请输入配方内容"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="配方密度" prop="density">
            <el-input
              v-model="queryParams.originPrescription.density"
              clearable
              placeholder="请输入配方密度"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="危险等级" prop="dangerLevel">
            <el-input
              v-model="queryParams.originPrescription.dangerLevel"
              clearable
              placeholder="请输入危险等级"
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
                          @createPrescription="createPrescription"
                          @deletePrescription="deletePrescription"
                          @updatePrescription="updatePrescription"
                          @expandPrescription="expandPrescription"
                          @queryProperty="() =>{ this.prescriptionPropertyShow = true }"
                          @queryStability="() =>{ this.prescriptionStabilityShow = true }"
                          @queryElements="() =>{ this.prescriptionElementsShow = true }"
        >
        </vue-context-menu>

        <ModifyPrescription ref="modifyPrescription"
                            :dialog="modifyPrescriptionShow"
                            :selectPrescriptionId="selectPrescriptionId"
                            :title="title"
                            @closeDialog="() =>{ this.modifyPrescriptionShow = false }"
                            @restore="() =>{this.selectPrescription = {}}"
        >
        </ModifyPrescription>

        <PrescriptionProperty ref="prescriptionProperty"
                            :dialog="prescriptionPropertyShow"
                            :selectPrescriptionId="selectPrescriptionId"
                            :title="title"
                            @closeDialog="() =>{ this.prescriptionPropertyShow = false }"
                            @restore="() =>{this.selectPrescription = {}}"
        >
        </PrescriptionProperty>

        <PrescriptionStability ref="prescriptionStability"
                            :dialog="prescriptionStabilityShow"
                            :selectPrescriptionId="selectPrescriptionId"
                            :title="title"
                            @closeDialog="() =>{ this.prescriptionStabilityShow = false }"
                            @restore="() =>{this.selectPrescription = {}}"
        >
        </PrescriptionStability>

        <PrescriptionElements ref="prescriptionElements"
                            :dialog="prescriptionElementsShow"
                            :selectPrescriptionId="selectPrescriptionId"
                            :title="title"
                            @closeDialog="() =>{ this.prescriptionElementsShow = false }"
                            @restore="() =>{this.selectPrescription = {}}"
        >
        </PrescriptionElements>

        <el-table v-loading="loading" :data="prescriptions" @selection-change="handleSelectionChange"
                  @row-contextmenu="showContextMenu"
        >
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="prescriptionId" align="center" label="配方编号" prop="prescriptionId"/>
          <el-table-column v-if="columns[1].visible" key="prescriptionName" :show-overflow-tooltip="true" align="center"
                           label="配方名称"
                           prop="prescriptionName"
          />
          <el-table-column v-if="columns[2].visible" key="prescriptionDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="配方描述" prop="prescriptionDescription"
          />
          <el-table-column v-if="columns[3].visible" key="density" :show-overflow-tooltip="true"
                           align="center"
                           label="配方密度" prop="density"
          />
          <el-table-column v-if="columns[4].visible" key="dangerLevel" :show-overflow-tooltip="true"
                           align="center"
                           label="危险等级" prop="dangerLevel"
          />
          <el-table-column v-if="columns[5].visible" key="curingTime" :show-overflow-tooltip="true"
                           align="center"
                           label="固化时间" prop="curingTime"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.curingTime" :key="key" :index="key+''" type="success">
                {{ key + ':' + value }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[6].visible" key="solutions" :show-overflow-tooltip="true" align="center"
                           label="其他属性"
                           prop="solutions"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.otherProperty" :key="key" :index="key+''" type="success">
                {{ key + ':' + value }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[7].visible" :show-overflow-tooltip="true" align="center"
                           label="相关性质"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-edit"
                size="mini"
                type="text"
                @click="handleProperty(scope.row)"
              >查看性质
              </el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[8].visible" :show-overflow-tooltip="true" align="center"
                           label="稳定性指标"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-edit"
                size="mini"
                type="text"
                @click="handleStability(scope.row)"
              >查看稳定性
              </el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[9].visible" :show-overflow-tooltip="true" align="center"
                           label="配方组分"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-edit"
                size="mini"
                type="text"
                @click="handleElements(scope.row)"
              >查看配方组分
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.prescriptionId !== 1" slot-scope="scope">
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

    <!-- 配方导入对话框 -->
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
            是否更新已经存在的配方数据
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
import { getToken } from '@/utils/auth'
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as treeManagement from '@/api/system/treeManagement'
import ModifyPrescription from '@/views/Prescription/components/modifyPrescription.vue'
import PrescriptionProperty from '@/views/Prescription/components/prescriptionProperty.vue'
import PrescriptionStability from '@/views/Prescription/components/prescriptionStability.vue'
import PrescriptionElements from '@/views/Prescription/components/prescriptionElements.vue'

export default {
  name: 'index',
  components: { Treeselect,ModifyPrescription,PrescriptionProperty,PrescriptionStability,PrescriptionElements },
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
        url: process.env.VUE_APP_BASE_API + '/prescription/importData'
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'prescriptionId',
        sortType: 'ascending',
        dynamicLabel: "",
        originPrescription:{
          prescriptionId:0,
          prescriptionName:'',
          dynamicLabels:[],
          density:'',
          dangerLevel:'',
          prescriptionDescription:'',
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `配方编号`, visible: true },
        { key: 1, label: `配方名称`, visible: true },
        { key: 2, label: `配方描述`, visible: true },
        { key: 3, label: `配方密度`, visible: true },
        { key: 4, label: `危险等级`, visible: true },
        { key: 5, label: `固化时间`, visible: true },
        { key: 6, label: `其他属性`, visible: true },
        { key: 7, label: `相关性质`, visible: true },
        { key: 8, label: `配方稳定性`, visible: true },
        { key: 9, label: `配方组分`, visible: true },
      ],
      title: '',
      modifyPrescriptionShow:false,
      prescriptionPropertyShow:false,
      prescriptionStabilityShow:false,
      prescriptionElementsShow:false,
      showSearch: true,
      selectPrescriptionId: 0,
      tableHeight: 0,
      selectLabel: '',
      contextMenuData: {
        menuName: 'prescriptionManage',
        axis: {
          x: null,
          y: null
        },
        menulists: []
      },
      tableMenu: [
        {
          fnHandler: 'createPrescription', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建质量配方' // 菜单名称
        },
        {
          fnHandler: 'updatePrescription',
          icoName: 'el-icon-search',
          btnName: '修改质量配方'
        },
        {
          fnHandler: 'expandPrescription',
          icoName: 'el-icon-search',
          btnName: '展开质量配方'
        },
        {
          fnHandler: 'deletePrescription',
          icoName: 'el-icon-paperclip',
          btnName: '删除质量配方'
        }
      ],
      filterText: '',
      prescriptions: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      selectPrescription: {},
      modifyState: false,
      prescriptionTree: [],
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

    /** 查询配方列表 */
    getList() {
      this.loading = true
      if(this.queryParams.dynamicLabel === ''){
        prescriptionManagement.getAllPrescriptions(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.prescriptions = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== ''){
        prescriptionManagement.getAllPrescriptionsByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.prescriptions = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if(this.queryParams.originPrescription.prescriptionName !== '' || this.queryParams.originPrescription.prescriptionDescription !== ''){
        prescriptionManagement.getPrescriptionsByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.prescriptions = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      }

    },
    /** 查询知识下拉树结构 */
    getTreeselect() {
      treeManagement.getTreeManagement(25822).then(response => {
        console.log(response.data)
        this.prescriptionTree.push(response.data)
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
      this.queryParams.sortableField = "n.label"
      prescriptionManagement.getAllPrescriptionsByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.prescriptions = result.data.content
            this.total = result.data.totalElements
            this.loading = false
          }
        }
      )
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.loading = true;
      prescriptionManagement.getPrescriptionsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.prescriptions = result.data.content
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
      this.ids = selection.map(item => item.prescriptionId)
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
      this.selectPrescription = row
    },
    createPrescription() {
      this.modifyPrescriptionShow = true
      this.modifyState = false
    },
    updatePrescription(data) {
      this.modifyPrescriptionShow = true
      this.modifyState = true
    },
    expandPrescription(){
      this.prescriptionElementsShow = true
    },
    deletePrescription() {
      prescriptionManagement.deletePrescription(this.selectPrescriptionId).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('删除配方成功！')
        }
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyPrescriptionShow = true
      console.log(this.modifyPrescriptionShow)
      this.title = "新增配方"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyPrescriptionShow = true;
      this.title = "修改配方";
      this.selectPrescriptionId = row.prescriptionId;
    },
    handleProperty(row) {
      this.prescriptionPropertyShow = true;
      this.title = "查看性质";
      this.selectPrescriptionId = row.prescriptionId;
    },
    handleStability(row) {
      this.prescriptionStabilityShow = true;
      this.title = "查看稳定性";
      this.selectPrescriptionId = row.prescriptionId;
    },
    handleElements(row) {
      this.prescriptionElementsShow = true;
      this.title = "查看组分";
      this.selectPrescriptionId = row.prescriptionId;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const prescriptionIds = row.prescriptionId || this.ids
      this.$modal.confirm('是否确认删除配方编号为"' + prescriptionIds + '"的数据项？').then(function() {
        return prescriptionManagement.deletePrescription(prescriptionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prescription/export', {
        ...this.queryParams
      }, `prescription_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '配方导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/prescription/importTemplate', {}, `prescription_template_${new Date().getTime()}.xlsx`)
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
