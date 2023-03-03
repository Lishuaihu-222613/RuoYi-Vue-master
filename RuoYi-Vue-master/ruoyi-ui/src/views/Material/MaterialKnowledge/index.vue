<template>
  <el-container style="border: 1px solid #eee;">
    <el-aside style="background-color: rgb(238, 241, 246)" width="300px">
      <el-input v-model="filterText" placeholder="输入关键字进行过滤"></el-input>
      <el-tree
        ref="tree"
        :data="materialKnowledge"
        :filter-node-method="filterKnowledge"
        :props="defaultProps"
        :render-content="renderContent"
        class="filter-tree"
        highlight-current
        indent="0"
      >
      </el-tree>
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-button @click="createMaterial">新增材料</el-button>
      </el-header>
      <el-main>
        <vue-context-menu :contextMenuData="contextMenuData"
                          @createMaterial="createMaterial"
                          @deleteMaterial="deleteMaterial"
                          @updateMaterial="updateMaterial"
        >
        </vue-context-menu>
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
        <div ref="tableBox" class="table-box">
          <el-table
            v-if="tableHeight"
            :data="materials"
            :max-height="tableHeight + 'px'" highlight-current-row stripe style="width: 100%"
            @current-change="handleCurrentChange"
            @row-contextmenu="showContextMenu"
          >
            <el-table-column type="expand">
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
                      style="width: 100px; height: 100px"
                    >
                    </el-image>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column align="center" label="材料名称" prop="materialName" width="140"></el-table-column>
            <el-table-column align="center" label="材料别称" prop="nickName" width="200"></el-table-column>
            <el-table-column align="center" label="材料描述" prop="materialDescription" width="200"></el-table-column>
            <el-table-column align="center" label="外观" prop="materialAppearance" width="200"></el-table-column>
            <el-table-column align="center" label="理化分析谱图"  width="200">
              <template v-slot="scope">
                <el-button type="info" @click.native.prevent="showAnalysisSpectrogram(scope.row.materialId)">
                  查看理化分析谱图
                </el-button>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="关联信息" width="350">
              <template v-slot="scope">
                <el-button type="info" @click.native.prevent="showPhysicalProperty(scope.row.materialId)">
                  物理性质
                </el-button>
                <el-button type="info" @click.native.prevent="showChemicalProperty(scope.row.materialId)">
                  化学性质
                </el-button>
                <el-button type="info" @click.native.prevent="showInspectProjectAndMethods(scope.row.materialId)">
                  理化指标与检验方法
                </el-button>
                <el-button type="info" @click.native.prevent="showProduceMethod(scope.row.materialId)">
                  制备方法
                </el-button>
                <el-button type="info" @click.native.prevent="showDangerAndProtection(scope.row.materialId)">
                  危险事项与防护措施
                </el-button>
                <el-button type="info" @click.native.prevent="showStorageRequirement(scope.row.materialId)">
                  存储要求
                </el-button>
                <el-button type="info" @click.native.prevent="showRelatedFile(scope.row.materialId)">
                  相关文件
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination :total="1000" background class="el-pagination" layout="prev, pager, next"></el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
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
import * as materialManagement from '@/api/system/materialManagement'

export default {

  name: 'materialKnowledge',

  components: {
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
      contextMenuData: {
        menuName: 'materialManage',
        axis: {
          x: null,
          y: null
        },
        menulists: []
      },
      tableMenu: [
        {
          fnHandler: 'createMaterial', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建材料' // 菜单名称
        },
        {
          fnHandler: 'updateMaterial',
          icoName: 'el-icon-search',
          btnName: '修改材料'
        },
        {
          fnHandler: 'deleteMaterial',
          icoName: 'el-icon-paperclip',
          btnName: '删除材料'
        }
      ],
      filterText: '',
      materialKnowledge: [],
      materials: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      selectMaterial: {},
      dialog: false
    }
  },
  mounted() {
    this.initPage()
    this.tableHeight = this.$refs.tableBox.clientHeight - 60
  },
  methods: {
    filterKnowledge(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    initPage() {
      materialManagement.getMaterialById(25351).then(result => {
        if (result.code == 200) {
          console.log(result.data)
          this.materials.push(result.data)
        }
      })
    },
    renderContent(h, { node, data, store }) {
      return (
        <span class="custom-tree-node">
            <span>{node.label}</span>
            <span>
              <el-button size="mini" type="text" on-click={() => this.append(data)}>详情</el-button>
              <el-button size="mini" type="text" on-click={() => this.remove(node, data)}>删除</el-button>
            </span>
          </span>)
    },
    showContextMenu(row, column, event){
      event.preventDefault()
      let x = event.clientX
      let y = event.clientY
      // 获得当前位置
      this.contextMenuData.axis = {
        x, y
      }
      this.contextMenuData.menulists = this.tableMenu
      this.selectMaterial = row
      console.log(row)
    },
    createMaterial() {
      this.modifyMaterialShow = true
    },
    updateMaterial() {
      this.modifyMaterialShow = true

    },
    deleteMaterial() {

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
    OpenDialog() {
      this.dialog = true
    },
    handleCurrentChange(val) {
      this.selectItem = val
      console.log(this.selectItem)
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
