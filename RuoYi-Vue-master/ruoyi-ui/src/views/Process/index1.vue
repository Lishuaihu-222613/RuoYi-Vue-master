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
            ref="processTree"
            :data="processes"
            :expand-on-click-node="false"
            :props="processProps"
            default-expand-all
            @node-click="handleProcessClick"
          >
            <span slot-scope="{ node, data }" class="custom-tree-node">
              <span>{{ node.label }}</span>
                <span>
                  <el-button
                    size="mini"
                    type="text"
                    @click="() => handleUpdate(data)"
                  >
                    修改
                  </el-button>
                  <el-button
                    size="mini"
                    type="text"
                    @click="() => handleDelete(data)"
                  >
                    删除
                  </el-button>
                </span>
              </span>
          </el-tree>
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
          <el-form-item label="只看典型">
            <template slot-scope="scope">
              <el-switch
                v-model="typical"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
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

        <modifyElement ref="modifyElement"
                       :dialog="modifyElementShow"
                       :pId="processId"
                       :proId="parentId"
                       :selectElementId="selectElementId"
                       :title="title"
                       @closeDialog="() =>{ this.modifyElementShow = false }"
                       @restore="() =>{this.selectElement = {}}"
        />
        <relationWindow ref="relatedWindow"
                        :dialog="modifyConstraintShow"
                        :selectElementId="selectElementId"
                        @closeDialog="() =>{ this.modifyConstraintShow = false }"
                        @restore="() =>{this.selectElement = {}}"
        />

        <el-table v-loading="loading" :data="elements"
                  :load="loadSubElement"
                  lazy
                  :tree-props="{children: 'subElements', hasChildren: 'hasSubElements'}"
                  row-key="elementId"
                  @selection-change="handleSelectionChange"
        >
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="elementId" align="center" label="编号" prop="elementId"/>
          <el-table-column v-if="columns[1].visible" key="elementNumber" :show-overflow-tooltip="true" align="center"
                           label="序号" prop="elementNumber"
          />
          <el-table-column v-if="columns[2].visible" key="elementName" :show-overflow-tooltip="true" align="center"
                           label="名称" prop="elementName"
          />
          <el-table-column v-if="columns[3].visible" key="elementDescription" :show-overflow-tooltip="true"
                           align="center"
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
          <el-table-column v-if="columns[5].visible" key="elementRequirements" :show-overflow-tooltip="true"
                           align="center"
                           label="相关要求" prop="elementRequirements"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.elementRequirements" :key="key" :index="key+''" type="success">
                {{ key + ':' + scope.row.elementRequirements[key + ''] }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[6].visible" key="elementOtherProperties" :show-overflow-tooltip="true"
                           align="center"
                           label="其他属性"
                           prop="elementOtherProperties"
          >
            <template slot-scope="scope">
              <el-tag v-for="(value, key) in scope.row.elementOtherProperties" :key="key" :index="key+''"
                      type="success"
              >
                {{ key + ':' + scope.row.elementOtherProperties[key + ''] }}
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
              >更改关系
              </el-button>
              <el-button
                icon="el-icon-key"
                size="mini"
                type="text"
                @click="handleGraph(scope.row)"
              >展示图谱
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

    <!-- 工艺导入对话框 -->
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
        pageSize: 10,
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
        { key: 0, label: `元素编号`, visible: false },
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
      typical: false,
      selectLabel: '',
      filterText: '',
      labelOptions: [],
      processes: [
        {
          elementId: 0,
          elementName: '测试工艺方案1'
        }
      ],
      elements: [{
        elementId: 25369,
        elementNumber: '',
        elementName: '壳体准备工艺',
        elementDescription: '',
        elementRemark: ['壳体内表面绝热层喷砂、打磨、清理'],
        elementRequirements: {},
        elementOtherProperties: {},
        subElements: [
          {
            elementId: 25412,
            elementNumber: '10',
            elementName: '壳体预处理工序',
            elementDescription: '壳体内表面绝热层喷砂、打磨、清理',
            elementRemark: [],
            elementRequirements: {},
            elementOtherProperties: {},
            subElements: [
              {
                elementId: 25501,
                elementNumber: '1',
                elementName: '壳体接收',
                elementDescription: '',
                elementRemark: [],
                elementRequirements: {
                  技术要求: '见子工步',
                  检验要求: '三检'
                },
                elementOtherProperties: {},
                subElements: [
                  {
                    elementId: 25520,
                    elementNumber: '1.1',
                    elementName: '壳体接收1',
                    elementDescription: '壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
                    elementRemark: [],
                    elementRequirements: {},
                    elementOtherProperties: {}
                  },
                  {
                    elementId: 25521,
                    elementNumber: '1.2',
                    elementName: '壳体接收2',
                    elementDescription: '填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
                    elementRemark: [],
                    elementRequirements: {
                      技术要求: '粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
                      检验要求: '三检'
                    },
                    elementOtherProperties: {}
                  },
                  {
                    elementId: 25522,
                    elementNumber: '1.3',
                    elementName: '壳体接收3',
                    elementDescription: '壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
                    elementRemark: [],
                    elementRequirements: {
                      技术要求: '环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
                      检验要求: '三检'
                    },
                    elementOtherProperties: {}
                  }
                ]
              }, {
                elementId: 25524,
                elementNumber: '2',
                elementName: '内表面处理',
                elementDescription: '内表面喷砂处理与内表面打磨',
                elementRemark: [],
                elementRequirements: {},
                elementOtherProperties: {},
                subElements: [
                  {
                    elementId: 25525,
                    elementNumber: '2.1',
                    elementName: '内表面喷砂处理',
                    elementDescription: '内表面喷砂处理',
                    elementRemark: [],
                    elementRequirements: {},
                    elementOtherProperties: {},
                    subElements: [
                      {
                        elementId: 25527,
                        elementNumber: '2.1.1',
                        elementName: '内表面喷砂处理1',
                        elementDescription: '使用胶带对金属裙上的螺孔进行粘贴保护',
                        elementRemark: [],
                        elementRequirements: {
                          技术要求: '内有螺纹的孔均需保护，光孔不需要保护。'
                        },
                        elementOtherProperties: {}
                      },
                      {
                        elementId: 25528,
                        elementNumber: '2.1.2',
                        elementName: '内表面喷砂处理2',
                        elementDescription: '使用胶布对人工脱夹层进行简易封口。',
                        elementRemark: [],
                        elementRequirements: {
                          技术要求: '防止喷砂过程中多余物进入人脱夹层中。'
                        },
                        elementOtherProperties: {}
                      },
                      {
                        elementId: 25529,
                        elementNumber: '2.1.3',
                        elementName: '内表面喷砂处理3',
                        elementDescription: '内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
                        elementRemark: [],
                        elementRequirements: {},
                        elementOtherProperties: {}
                      }
                    ]
                  },
                  {
                    elementId: 25526,
                    elementNumber: '2.2',
                    elementName: '内表面打磨',
                    elementDescription: '打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
                    elementRemark: [],
                    elementRequirements: {
                      技术要求: '如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
                    },
                    elementOtherProperties: {}
                  }
                ]
              }
            ]
          },
          {
            elementId: 25411,
            elementNumber: '20',
            elementName: '衬层成型工序',
            elementDescription: '待编辑',
            elementRemark: [],
            elementRequirements: {},
            elementOtherProperties: {}
          },
          {
            elementId: 25432,
            elementNumber: '30',
            elementName: '壳体预处理工序',
            elementDescription: '待编辑',
            elementRemark: [],
            elementRequirements: {},
            elementOtherProperties: {}
          }

        ]
      }],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      processProps: {

        label: 'elementName'
      },
      treeProps: {
        hasChildren: 'hasSubElements',
        children: 'subElements'
      },
      selectElement: {},
      selectElementId:0,
      model: {},
      modifyState: false,
      elementTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
      processId: 0,
      parentId: 0
    }
  },

  methods: {

    /** 查询问题列表 */
    getList() {
      this.loading = false
      if (this.queryParams.dynamicLabel === '') {
        processManagement.getAllWholeProcess().then(result => {
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
              // this.total = result.data.totalElements
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
    handleGraph(data){
      this.$router.push({ path: "/KM/FileConvert", query: {id: data.elementId}});
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
      this.queryParams.sortableField = 'n.label'
      processManagement.getAllProcessByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.processes = result.data
            this.loading = false
          }
        }
      )
    },
    //工艺树节点单击
    handleProcessClick(data) {
      this.elements = []
      this.processId = data.elementId
      this.parentId = data.elementId
      this.loading = true
      this.elements.push(data)
      processManagement.getSubElementsById(data.elementId).then(result => {
          if (result.code === 200) {
            this.elements[0].subElements = result.data
            this.loading = false
          }
        }
      )
    },

    loadSubElement(tree, treeNode, resolve){
      setTimeout(() => {
        let id = tree.elementId
        processManagement.getSubElementsById(id).then(result => {
            if (result.code === 200) {
              resolve(result.data)
            }
          }
        )
      }, 1000)
    },
    handleRelations(data) {
      this.modifyConstraintShow = true
      this.selectElement = data
      this.selectElementId = data.elementId
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.loading = true
      processManagement.getAllElementsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.elements = result.data
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
      this.title = '创建元素'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyElementShow = true
      this.title = '修改元素'
      this.selectElement = row
      this.selectElementId = row.elementId
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
      this.upload.title = '工艺导入'
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

