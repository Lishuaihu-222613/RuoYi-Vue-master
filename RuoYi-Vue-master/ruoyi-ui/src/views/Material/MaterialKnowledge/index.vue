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
          <el-form-item label="材料名称" prop="materialName">
            <el-input
              v-model="queryParams.originMaterial.materialName"
              clearable
              placeholder="请输入材料名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="材料别称" prop="nickName">
            <el-input
              v-model="queryParams.originMaterial.nickName"
              clearable
              placeholder="请输入材料别称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="登记号" prop="casRegistryNumber">
            <el-input
              v-model="queryParams.originMaterial.casRegistryNumber"
              clearable
              placeholder="请输入CAS-登记号"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="材料描述" prop="materialDescription">
            <el-input
              v-model="queryParams.originMaterial.materialDescription"
              clearable
              placeholder="请输入材料描述"
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

        <modifyMaterial ref="modifyMaterial"
                        :dialog="modifyMaterialShow"
                        :selectMaterial="selectMaterial"
                        @closeDialog="() =>{ this.modifyMaterialShow = false }"
                        @restore="() =>{this.selectMaterial = {}}"
        >
        </modifyMaterial>
        <AnalysisSpectrogram ref="AnalysisSpectrogram"
                             :dialog="SpectrogramShow"
                             :selectId="selectId"
                             @closeDialog="() =>{ this.SpectrogramShow = false }"
        ></AnalysisSpectrogram>
        <ChemicalProperty ref="ChemicalProperty"
                          :dialog="ChemicalPropertyShow"
                          :selectId="selectId"
                          @closeDialog="() =>{ this.ChemicalPropertyShow = false }"
        >
        </ChemicalProperty>
        <PhysicalProperty ref="PhysicalProperty"
                          :dialog="PhysicalPropertyShow"
                          :selectId="selectId"
                          @closeDialog="() =>{ this.PhysicalPropertyShow = false }"
        >
        </PhysicalProperty>
        <DangerAndProtection ref="DangerAndProtection"
                             :dialog="DangerAndProtectionShow"
                             :selectId="selectId"
                             @closeDialog="() =>{ this.DangerAndProtectionShow = false }"
        >
        </DangerAndProtection>
        <InspectProjectAndMethods ref="InspectProjectAndMethods"
                                  :dialog="InspectProjectAndMethodsShow"
                                  :selectId="selectId"
                                  @closeDialog="() =>{ this.InspectProjectAndMethodsShow = false }"
        >
        </InspectProjectAndMethods>
        <ProduceMethod ref="ProduceMethod"
                       :dialog="ProduceMethodShow"
                       :selectId="selectId"
                       @closeDialog="() =>{ this.ProduceMethodShow = false }"
        ></ProduceMethod>
        <RelatedFile ref="RelatedFile"
                     :dialog="RelatedFileShow"
                     :selectId="selectId"
                     @closeDialog="() =>{ this.RelatedFileShow = false }"
        ></RelatedFile>
        <StorageRequirement ref="StorageRequirement"
                            :dialog="StorageRequirementShow"
                            :selectId="selectId"
                            @closeDialog="() =>{ this.StorageRequirementShow = false }"
        ></StorageRequirement>
        <el-table v-loading="loading" :data="materials" @selection-change="handleSelectionChange"
        >
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column align="center" type="expand" >
            <template v-slot="props">
              <el-form class="demo-table-expand" inline label-position="left">
                <el-form-item label="材料名称">
                  <span>{{ props.row.materialName }}</span>
                </el-form-item>
                <el-form-item label="英文名">
                  <span>{{ props.row.englishNickName }}</span>
                </el-form-item>
                <el-form-item label="材料别称">
                  <span>{{ props.row.nickName }}</span>
                </el-form-item>
                <el-form-item label="英文别称">
                  <span>{{ props.row.englishNickName }}</span>
                </el-form-item>
                <el-form-item label="相对分子质量">
                  <span>{{ props.row.relativeMolecularMass }}</span>
                </el-form-item>
                <el-form-item label="CAS-登记号">
                  <span>{{ props.row.casRegistryNumber }}</span>
                </el-form-item>
                <el-form-item label="材料描述">
                  <span>{{ props.row.materialDescription }}</span>
                </el-form-item>
                <el-form-item label="外观">
                  <span>{{ props.row.materialAppearance }}</span>
                </el-form-item>
                <el-form-item label="材料用途">
                  <span v-for="usage in props.row.materialUsage">{{ usage }}</span>
                </el-form-item>
                <el-form-item label="化学结构式">
                  <el-image
                    :preview-src-list="[props.row.structuralFormula]"
                    :src="props.row.structuralFormula"
                    style="width: 800px; height: 100px"
                  >
                  </el-image>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[0].visible" key="materialId" align="center"
                           label="材料编号"
                           prop="materialId"
          />
          <el-table-column v-if="columns[1].visible" key="materialName" :show-overflow-tooltip="true"
                           align="center"
                           label="材料名称"
                           prop="materialName"
          />
          <el-table-column v-if="columns[2].visible" key="materialDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="材料描述" prop="materialDescription"
          />
          <el-table-column v-if="columns[3].visible" key="materialAppearance" :show-overflow-tooltip="true"
                           align="center"
                           label="材料外观" prop="materialAppearance"
          />
          <el-table-column v-if="columns[4].visible" key="AnalysisSpectrogram" :show-overflow-tooltip="true"
                           align="center"
                           label="理化分析谱图" prop="AnalysisSpectrogram"
          >
            <template v-slot="scope">
              <el-button type="text" @click.native.prevent="showAnalysisSpectrogram(scope.row.materialId)">
                查 看
              </el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[5].visible" key="property" :show-overflow-tooltip="true"
                           align="center"
                           label="理化性质"
          >
            <template v-slot="scope">
              <el-row>
                <el-col :span="12">
                  <el-button type="text" @click.native.prevent="showPhysicalProperty(scope.row.materialId)">
                    物理
                  </el-button>
                </el-col>
                <el-col :span="12">
                  <el-button type="text" @click.native.prevent="showChemicalProperty(scope.row.materialId)">
                    化学
                  </el-button>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[6].visible"  :show-overflow-tooltip="true"
                           align="center"
                           label="理化指标"
          >
            <template v-slot="scope">
              <el-row>
                <el-button type="text" @click.native.prevent="showInspectProjectAndMethods(scope.row.materialId)">
                  查 看
                </el-button>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[7].visible"  :show-overflow-tooltip="true"
                           align="center"
                           label="生产方法" prop="manufacturer"
          >
            <template v-slot="scope">
              <el-button type="text" @click.native.prevent="showProduceMethod(scope.row.materialId)">
                查 看
              </el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[8].visible"  :show-overflow-tooltip="true"
                           align="center"
                           label="存储要求" prop="toolSpecification"
          >
            <template v-slot="scope">
              <el-button type="text" @click.native.prevent="showStorageRequirement(scope.row.materialId)">
                查 看
              </el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[9].visible"  :show-overflow-tooltip="true"
                           align="center"
                           label="关联文件" prop="toolUsage"
          >
            <el-button type="text" @click.native.prevent="showRelatedFile(scope.row.materialId)">
              下 载
            </el-button>
          </el-table-column>
          <el-table-column v-if="columns[10].visible" key="toolState" :show-overflow-tooltip="true"
                           align="center"
                           label="危险与防护" prop="toolState"
          >
            <template slot-scope="scope">
              <el-row>
                <el-button type="text" @click.native.prevent="showDangerAndProtection(scope.row.materialId)">
                  查 看
                </el-button>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.materialId !== 1" slot-scope="scope">
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

    <!-- 添加或修改材料对话框 -->
    <el-dialog :title="title" :visible.sync="modifyMaterialShow" width="800px" append-to-body>
      <el-form ref="material" :model="selectMaterial"  label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="材料名称" prop="materialName">
              <el-input v-model="selectMaterial.materialName" placeholder="请输入材料名称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="材料类别" prop="materialTypes">
              <treeselect v-model="selectMaterial.materialLabels" :options="labelTree" :normalizer="normalizer" :multiple="true" :show-count="true" placeholder="请选择材料类别" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="材料描述" prop="materialDescription">
            <el-input v-model="selectMaterial.materialDescription" placeholder="请输入材料描述" type="textarea" />
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="材料别称" prop="nickName">
              <el-input v-model="selectMaterial.nickName" placeholder="请输入材料别称"  />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="相对分子质量" prop="relativeMolecularMass">
              <el-input v-model="selectMaterial.relativeMolecularMass" placeholder="请输入相对分子质量"  />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="英文名称" prop="englishName">
              <el-input v-model="selectMaterial.englishName" placeholder="请输入英文名称"  />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="英文别称" prop="englishNickName">
              <el-input v-model="selectMaterial.englishNickName" placeholder="请输入英文别称"  />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="CAS-登记号" prop="casRegistryNumber">
              <el-input-number v-model="selectMaterial.casRegistryNumber" placeholder="请输入CAS-登记号"  />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="材料外观" prop="materialAppearance">
              <el-input v-model="selectMaterial.materialAppearance" placeholder="请输入材料外观"  />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="化学结构式" prop="structuralFormula">
              <el-card class="box-card" shadow="hover">
                <div slot="header" class="clearfix">
                  <el-row>
                    <el-tag>化学结构式</el-tag>
                    <ImageUploader ref="ImageUploader"
                                   class="upLoader"
                                   @success="(url) =>{ handleUpLoad(url, 'structuralFormula')}"
                    ></ImageUploader>
                  </el-row>
                </div>
                <el-image
                  fit="cover"
                  :preview-src-list="[selectMaterial.structuralFormula]"
                  :src="selectMaterial.structuralFormula"
                  class="structuralImage"
                >
                  <div slot="placeholder" class="image-slot">
                    加载中<span class="dot">...</span>
                  </div>
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
              </el-card>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="材料用途">
            <el-button type="primary" @click="addUsage" style="float: right">添加用途</el-button>
            <el-row v-for="(item,index) in selectMaterial.materialUsage" :key="index">
              <el-col :span="20">
                <el-input v-model="selectMaterial.materialUsage[index]"></el-input>
              </el-col>
              <el-col :span="4">
                <el-button
                  circle icon="el-icon-delete" type="danger"
                  @click="removeUsage(selectMaterial.materialUsage[index])"
                ></el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 材料导入对话框 -->
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

import AnalysisSpectrogram from '@/views/Material/MaterialKnowledge/components/RelatedDialog/AnalysisSpectrogram.vue'
import ChemicalProperty from '@/views/Material/MaterialKnowledge/components/RelatedDialog/ChemicalProperty.vue'
import DangerAndProtection from '@/views/Material/MaterialKnowledge/components/RelatedDialog/DangerAndProtection.vue'
import InspectProjectAndMethods
  from '@/views/Material/MaterialKnowledge/components/RelatedDialog/InspectProjectAndMethods.vue'
import modifyMaterial from '@/views/Material/MaterialKnowledge/components/modifyMaterial'
import PhysicalProperty from '@/views/Material/MaterialKnowledge/components/RelatedDialog/PhysicalProperty.vue'
import ProduceMethod from '@/views/Material/MaterialKnowledge/components/RelatedDialog/ProduceMethod.vue'
import RelatedFile from '@/views/Material/MaterialKnowledge/components/RelatedDialog/RelatedFile.vue'
import StorageRequirement from '@/views/Material/MaterialKnowledge/components/RelatedDialog/StorageRequirement.vue'
import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import * as materialManagement from '@/api/system/materialManagement'
import ImageUploader from '@/views/Material/MaterialKnowledge/components/ImageUpload'
import { getToken } from '@/utils/auth'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {

  name: 'materialKnowledge',

  components: {
    ImageUploader,
    Treeselect,
    AnalysisSpectrogram,
    ChemicalProperty,
    DangerAndProtection,
    InspectProjectAndMethods,
    modifyMaterial,
    PhysicalProperty,
    ProduceMethod,
    RelatedFile,
    StorageRequirement
  },

  watch: {
    filterKnowledge(val) {
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
        url: process.env.VUE_APP_BASE_API + '/FixtureTool/importData'
      },
      // 列信息
      columns: [
        { key: 0, label: `材料编号`, visible: true },
        { key: 1, label: `材料名称`, visible: true },
        { key: 2, label: `材料描述`, visible: true },
        { key: 3, label: `材料外观`, visible: true },
        { key: 4, label: `理化分析谱图`, visible: true },
        { key: 5, label: `理化性质`, visible: true },
        { key: 6, label: `理化指标`, visible: true },
        { key: 7, label: `生产方法`, visible: true },
        { key: 8, label: `存储要求`, visible: true },
        { key: 9, label: `关联文件`, visible: true },
        { key: 10, label: `危险与防护`, visible: true },
      ],
      title: '',
      modifyMaterialShow:false,
      SpectrogramShow: false,
      ChemicalPropertyShow: false,
      PhysicalPropertyShow: false,
      DangerAndProtectionShow: false,
      InspectProjectAndMethodsShow: false,
      ProduceMethodShow: false,
      RelatedFileShow: false,
      StorageRequirementShow: false,
      selectId: 0,
      tableHeight: 0,
      filterText: '',
      materials: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      selectMaterial: {
        materialId:undefined,
        materialName:'',
        englishName:'',
        nickName:'',
        englishNickName: '',
        relativeMolecularMass:'',
        structuralFormula:'',
        casRegistryNumber:'',
        materialDescription:'',
        materialAppearance:'',
        materialUsage:[],
        materialLabels:[],
      },
      labelTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
      showSearch:true,
      queryParams:{
        pageNum:1,
        pageSize:9,
        sortType:"ascending",
        sortableField:"materialId",
        dynamicLabel:"",
        originMaterial:{
          materialName:'',
          nickName:'',
          casRegistryNumber:'',
          materialDescription:''
        }
      },
    }
  },
  created() {
    this.getList()
    this.getTreeselect()
  },

  methods: {
    /** 查询材料列表 */
    getList() {
      this.loading = true
      if (this.queryParams.dynamicLabel === '') {
        materialManagement.getAllMaterials(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.materials = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== '') {
        materialManagement.getAllMaterialsByType(this.queryParams).then(result => {
            if (result.code === 200) {
              this.materials = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.originMaterial.materialName !== '' || this.queryParams.originMaterial.nickName !== '' || this.queryParams.originMaterial.casRegistryNumber !== '') {
        materialManagement.getMaterialsByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.materials = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      }

    },
    /** 查询知识下拉树结构 */
    getTreeselect() {
      treeManagement.getTreeManagement(25451).then(response => {
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

    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    handleNodeClick(data,node,component){
      this.queryParams.dynamicLabel = data.leafName
      this.loading = true
      this.queryParams.sortableField = "n.label"
      materialManagement.getAllMaterialsByType(this.queryParams).then(result => {
          if (result.code === 200) {
            this.materials = result.data.content
            this.total = result.data.totalElements
            this.loading = false
          }
        }
      )
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.loading = true
      materialManagement.getMaterialsByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.materials = result.data.content
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
    handleUpLoad(url, attr) {
      this.material.structuralFormula = url
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyMaterialShow = true
      this.title = '新增材料'
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.materialId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyMaterialShow = true
      this.title = '修改材料'
      this.selectMaterial = row
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const materialIds = row.material || this.ids
      this.$modal.confirm('是否确认删除材料编号为"' + materialIds + '"的数据项？').then(function() {
        return materialManagement.deleteMaterial(materialIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('material/export', {
        ...this.queryParams
      }, `material_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '材料导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/material/importTemplate', {}, `material_template_${new Date().getTime()}.xlsx`)
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
    showAnalysisSpectrogram(materialId) {
      this.selectId = materialId
      this.SpectrogramShow = true
    },
    showPhysicalProperty(materialId) {
      this.selectId = materialId
      this.PhysicalPropertyShow = true
    },
    showChemicalProperty(materialId) {
      this.selectId = materialId
      this.ChemicalPropertyShow = true
    },
    showDangerAndProtection(materialId) {
      this.selectId = materialId
      this.DangerAndProtectionShow = true
    },
    showInspectProjectAndMethods(materialId) {
      this.selectId = materialId
      this.InspectProjectAndMethodsShow = true
    },
    showProduceMethod(materialId) {
      this.selectId = materialId
      this.ProduceMethodShow = true
    },
    showStorageRequirement(materialId) {
      this.selectId = materialId
      this.StorageRequirementShow = true
    },
    showRelatedFile(materialId) {
      this.selectId = materialId
      this.RelatedFileShow = true
    },
    addUsage(){
      this.selectMaterial.materialUsage.push("")
    },
    removeUsage(item){
      let index = this.material.materialUsage.indexOf(item)
      if (index !== -1) {
        this.selectMaterial.materialUsage.splice(index, 1)
      }
    },
    submitForm:function() {
      this.$refs["material"].validate(valid => {
        if (valid) {
          if (this.selectMaterial.materialId !== undefined) {
            materialManagement.updateMaterial(this.selectMaterial).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            materialManagement.createMaterial(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    cancel(){
      this.modifyMaterialShow = false;
      this.reset();
    },
    reset(){
      this.selectMaterial = {
        materialId:undefined,
        materialName:'',
        englishName:'',
        nickName:'',
        englishNickName: '',
        relativeMolecularMass:'',
        structuralFormula:'',
        casRegistryNumber:'',
        materialDescription:'',
        materialAppearance:'',
        materialUsage:'',
        materialLabels:[],
      };
      this.resetForm("material");
    }
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

</style>
