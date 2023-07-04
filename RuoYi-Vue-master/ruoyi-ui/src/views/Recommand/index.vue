<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="12" :xs="24">
        <div class="head-container">
          <el-row>
            <el-col :span="10">
              <treeselect v-model="elementType" :normalizer="labelNormalizer"
                          :options="elementTree" placeholder="请选择类别" @select="changeElementLabel"
              />
            </el-col>
            <el-col :span="10">
              <treeselect v-model="selectElementId" :disabled="disabledElement" :normalizer="elementNormalizer"
                          :options="elementOptions"
                          :show-count="true" placeholder="请选择产品或组件"
              />
            </el-col>
            <el-col :offset="2" :span="2">
              <el-button @click="handleClickStructure">加 载</el-button>
            </el-col>
          </el-row>
<!--          <el-row>-->
<!--            <el-col :span="10">-->
<!--              <treeselect v-model="prescriptionType" :normalizer="labelNormalizer"-->
<!--                          :options="prescriptionTree" placeholder="请选择类别" @select="changePrescriptionLabel"-->
<!--              />-->
<!--            </el-col>-->
<!--            <el-col :span="10">-->
<!--              <el-select v-model="selectPrescriptionId" filterable placeholder="请选择推进剂配方" style="width: 100%">-->
<!--                <el-option-->
<!--                  v-for="item in prescriptionOptions"-->
<!--                  :key="item.prescriptionId"-->
<!--                  :label="item.prescriptionName"-->
<!--                  :value="item.prescriptionId"-->
<!--                >-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </el-col>-->
<!--            <el-col :offset="2" :span="2">-->
<!--              <el-button @click="handleClickPrescription">加 载</el-button>-->
<!--            </el-col>-->
<!--          </el-row>-->
        </div>
        <div class="head-container" style="height: 600px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>产品模型</span>
            </div>
            <iFrame :src="modelFile.fileUrl" frameborder="0" style="height: 570px"></iFrame>
          </el-card>
        </div>
        <div class="head-container">
          <el-tabs type="border-card">
            <el-tab-pane>
              <span slot="label"><i class="el-icon-date"></i>产品BOM</span>
              <el-tree
                ref="tree"
                :data="BOM"
                :expand-on-click-node="false"
                :props="defaultProps"
                default-expand-all
                node-key="elementId"
              />
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label"><i class="el-icon-date"></i>配方组分</span>
              <el-table
                :data="prescriptionElements"
                border
                style="width: 100%"
              >
                <el-table-column
                  label="组分"
                  prop="material"
                >
                  <template slot-scope="scope">
                    {{ scope.row.material.materialName }}
                  </template>
                </el-table-column>
                <el-table-column
                  label="质量分数"
                  prop="percentage"
                >
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="12" :xs="24">
        <el-row>
          <el-col :offset="8" :span="6">
            <treeselect v-model="processType" :normalizer="processNormalizer" :options="processTree"
                        :show-count="true"
                        placeholder="请选择工艺类别"
            />
          </el-col>
          <el-col :span="10">
            <el-button @click="processRecommand">推荐工艺</el-button>
            <el-button @click="processCreate">创建工艺</el-button>
            <el-button @click="processSave">保存工艺</el-button>
          </el-col>
        </el-row>
        <el-row>

          <el-table ref="processTable"
                    :data="processData"
                    :load="loadSubElement"
                    :tree-props="{children: 'subElements', hasChildren: 'hasSubElements'}"
                    border
                    default-expand-all
                    lazy
                    row-key="elementId"
          >
            <el-table-column key="elementNumber" :show-overflow-tooltip="true"
                             align="center"
                             label="序号"
                             prop="elementNumber"
            />
            <el-table-column key="elementName" :show-overflow-tooltip="true"
                             align="center"
                             label="名称"
                             prop="elementName"
            />
            <el-table-column key="elementDescription" :show-overflow-tooltip="true"
                             align="center"
                             label="内容"
                             prop="elementDescription"
            />
            <el-table-column key="usedResources" :show-overflow-tooltip="true"
                             align="center"
                             label="使用资源"
                             prop="usedResources"
            >
              <template slot-scope="scope">
                <el-tag v-for="(item, index) in scope.row.usedResources" :key="index" :index="index+''" type="success">
                  {{ scope.row.usedResources[index].resourceName }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              class-name="small-padding fixed-width"
              label="操作"
              width="160"
            >
              <template slot-scope="scope">
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
                  icon="el-icon-s-promotion"
                  size="mini"
                  type="text"
                  @click="addElement(scope.row)"
                >添加
                </el-button>
                <el-button
                  icon="el-icon-s-cooperation"
                  size="mini"
                  type="text"
                  @click="recommandResource(scope.row)"
                >资源
                </el-button>
                <el-button
                  icon="el-icon-bottom"
                  size="mini"
                  type="text"
                  @click="createSubProcess(scope.row)"
                >添加子级
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-col>
    </el-row>

    <el-dialog :title="title" :visible.sync="selectWindowShow" append-to-body width="800px">
      <el-table v-loading="loading" :data="recommandProcess" row-key="elementId">

        <el-table-column key="elementNumber" :show-overflow-tooltip="true" align="center"
                         label="序号" prop="elementNumber"
        />
        <el-table-column key="elementName" :show-overflow-tooltip="true" align="center"
                         label="名称" prop="elementName"
        />
        <el-table-column key="elementDescription" :show-overflow-tooltip="true" align="center"
                         label="内容"
                         prop="elementDescription"
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
              @click="handleSelect(scope.row)"
            >选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog :visible.sync="selectResourceShow" append-to-body title="选择资源" width="600px">
      <el-form>
        <el-form-item label="推荐资源集">
          <el-row>
            <el-tag
              v-for="resource in resources"
              :key="resource.resourceId"
              closable
              type="info"
              @close="handleCloseResource(resource)"
            >
              {{ resource.resourceName }}
            </el-tag>
          </el-row>
        </el-form-item>
        <el-form-item label="添加资源">
          <el-row class="row-bg" justify="space-around" type="flex">
            <el-col :span="10">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :normalizer="labelNormalizer"
                          :options="resourceLabelOptions"
                          :searchable="true"
                          placeholder="请选择资源标签"
                          @select="selectResourceLabel"
              />
            </el-col>
            <el-col :span="10">
              <el-select v-model="newResource" placeholder="请选择" value-key="resourceId">
                <el-option
                  v-for="item in resourceOptions"
                  :key="item.resourceId"
                  :label="item.resourceName"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-button @click.prevent="addResource">添加</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-col :offset="14" :span="10">
              <el-button type="primary" @click="cancel">取消</el-button>
              <el-button type="primary" @click="addResourceToElement">添加到表单</el-button>
            </el-col>
          </el-row>

        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="modifyProcessShow" append-to-body width="600px"
               @open="handleOpen"
    >
      <el-table v-if="title === '添加' " v-loading="loading" :data="recommandProcess" row-key="elementId">

        <el-table-column key="elementNumber" :show-overflow-tooltip="true" align="center"
                         label="序号" prop="elementNumber"
        />
        <el-table-column key="elementName" :show-overflow-tooltip="true" align="center"
                         label="名称" prop="elementName"
        />
        <el-table-column key="elementDescription" :show-overflow-tooltip="true" align="center"
                         label="内容"
                         prop="elementDescription"
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
              @click="handleAdd(scope.row)"
            >选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-divider></el-divider>
      <el-form ref="modifyForm" :model="newElement" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="序号" prop="elementNumber">
              <el-input v-model="newElement.elementNumber" maxlength="30" placeholder="请输入序号"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="elementName">
              <el-input v-model="newElement.elementName" maxlength="30" placeholder="请输入名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="内容" prop="elementDescription">
            <el-input v-model="newElement.elementDescription" autosize placeholder="请输入内容" type="textarea"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="类别">
              <el-radio-group v-model="elementType">
                <el-radio :label="'Process'">工 艺</el-radio>
                <el-radio :label="'Sequence'">工 序</el-radio>
                <el-radio :label="'Step'">工 步</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标签">
              <el-row class="row-bg" justify="space-around" type="flex">
                <treeselect v-model="dynamicLabels"
                            :clearable="true"
                            :multiple="true"
                            :normalizer="processNormalizer"
                            :options="processTree"
                            :searchable="true"
                            placeholder="请选择标签"
                />
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="包含子级">
              <el-switch
                v-model="newElement.hasSubElements"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="典型">
              <el-switch
                v-model="isTypical"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>

</template>

<script>

import * as elementManagement from '@/api/system/elementManagement'
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as processManagement from '@/api/system/processManagement'
import * as resourceManagement from '@/api/system/resourceManagement'
import * as fileManagement from '@/api/system/fileManagement'
import iFrame from '@/components/iFrame/index.vue'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import * as treeManagement from '@/api/system/treeManagement'
import * as recommand from '@/api/system/Recommand'

function getParentNodeId(tree, childId) {
  // 遍历树节点
  for (let node of tree) {
    // 如果当前节点就是目标节点的父节点，直接返回当前节点id
    if (node.subElements && node.subElements.some(child => child.elementId === childId)) {
      return node.elementId
    }
    // 否则继续遍历当前节点的子节点
    if (node.subElements) {
      const parentId = getParentNodeId(node.subElements, childId)
      if (parentId !== null) {
        return parentId
      }
    }
  }
  // 如果没有找到父节点，则返回null
  return null
}


function randomNum(n) {
  var res = "";
  for (var i = 0; i < n; i++) {
    res += Math.floor(Math.random() * 10);
  }
  return res;
}

// 删除某个节点
function removeNode(tree, id) {
  let pNode = getData(tree, getParentNodeId(tree, id)).subElements
  let currentIndex = pNode.findIndex(v => v['elementId'] === id)
  currentIndex > -1 && pNode.splice(currentIndex, 1)
}

// 插入某个子节点
function insertChildrenNode(tree, id, node) {
  if (getParentNodeId(tree, id)) { // 说明的时候某个节点
    let currentNode = getData(tree, getParentNodeId(tree, id))
    if (Object.keys(currentNode).length > 0) {
      if (!currentNode[['subElements']]) {
        currentNode['subElements'] = []
      }
      currentNode['subElements'].push(node)
    }
  } else if (getParentNodeId(tree, id) === '' || getParentNodeId(tree, id) === 0 || getParentNodeId(tree, id) === tree[0].elementId) { // 说明操作的根节点
    tree.push(node)
  }
}

// 插入某个节点之后
function insertAfter(tree, sourceId, targetNode) {
  console.log(tree, sourceId, targetNode)
  let p = getParentNodeId(tree, sourceId)
  console.log(p)
  let pNode = getData(tree, getParentNodeId(tree, sourceId)).subElements
  console.log(pNode)
  let sIndex = pNode.findIndex(v => v['elementId'] === sourceId)
  console.log(pNode)
  if (sIndex > -1) {
    pNode.splice(sIndex + 1, 0, targetNode)
  }
}

function getData(tree, id) {
  let stack = []
  let result = {}
  if (Array.isArray(tree) && tree.length > 0) {
    // tree.
    for (let i = 0; i < tree.length; i++) {
      stack.push(tree[i])
    }
  } else if (typeof tree === 'object') {
    stack = [tree]
  }
  while (stack.length) {
    let node = stack.shift()
    if (node['elementId'] === id) {
      result = node
      return result
    }
    if (node['subElements'] && node['subElements'].length > 0) {
      stack = stack.concat(node['subElements'])
    }
  }
  return result
}

export default {
  name: 'index',

  components: { Treeselect, iFrame },
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
      title: '',
      selectWindowShow: false,
      labelTree: [],
      elementLabel: '',
      elementType: undefined,
      prescriptionType: undefined,
      prescriptionTree: [],
      elementTree: [],
      selectElementId: undefined,
      selectPrescriptionId: undefined,
      disabledElement: true,
      disabledPrescription: true,
      prescriptionOptions: [],
      elementOptions: [],
      selectElement: {},
      newElement: {},
      modelFile: {
        fileUrl: ''
      },
      defaultProps: {
        label: 'elementName',
        children: 'subElements',
        hasChildren: 'hasSubElements'
      },
      prescriptionElements: [],
      processType: undefined,
      processTree: [],
      processData: [],
      BOM: [],
      modifyProcessShow: false,
      selectProcess: {},
      maps: new Map(),
      selectResourceShow: false,
      resources: [],
      newResource: {},
      resourceLabel: '',
      recommandProcess: [],
      resourceLabelOptions: [],
      resourceOptions: [],
      dynamicLabels: [],
      isTypical: false,
      parentElementId: undefined,
      parentId: undefined
    }
  },
  created() {
    this.getElementList()
  },
  methods: {

    handleOpen() {
      console.log(this.selectElement.dynamicLabels)
      this.dynamicLabels = this.selectElement.dynamicLabels
      if (this.dynamicLabels.includes('TypicalElement')) {
        this.isTypical = true
        this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'TypicalElement')
      }
      if (this.dynamicLabels.includes('Process')) {
        this.elementType = 'Process'
        this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Process')
      } else if (this.dynamicLabels.includes('Sequence')) {
        this.elementType = 'Sequence'
        this.parentElementId = getParentNodeId(this.processData, this.selectElement.elementId)
        this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Sequence')
      } else if (this.dynamicLabels.includes('Step')) {
        this.elementType = 'Step'
        this.parentElementId = getParentNodeId(this.processData, this.selectElement.elementId)
        this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'Step')
      }

    },
    submitForm() {
      if(this.title === "修改"){
        console.log(this.selectElement)
        console.log(this.newElement)
        this.selectElement = this.newElement
        this.selectElement = JSON.parse(JSON.stringify(this.newElement))
        // let parentId = getParentNodeId(this.processData,this.selectElement.elementId)
        // let parent = getData(this.processData,parentId).subElements
        // let index = parent.indexOf(this.selectElement)
        insertAfter(this.processData,this.selectElement.elementId,this.newElement)
        removeNode(this.processData,this.selectElement.elementId)

      }
      else if(this.title === "创建工艺"){
        this.newElement.elementId = randomNum(5)
        this.processData = []
        this.processData.push(this.newElement)
      }
      else if(this.title === "添加"){
        this.newElement.elementId = randomNum(5)
        insertAfter(this.processData, this.selectElement.elementId, this.newElement)
      }
      else {
        this.newElement.elementId = randomNum(5)
        insertChildrenNode(this.processData,this.selectElement.elementId,this.newElement)
      }
    },

    getElementList() {
      treeManagement.getTreeManagement(25877).then(response => {
        this.elementTree = []
        console.log(response.data)
        this.elementTree.push(response.data)
      })
      treeManagement.getTreeManagement(25500).then(response => {
        this.processTree = []
        console.log(response.data)
        this.processTree.push(response.data)
      })
      treeManagement.getTreeManagement(25780).then(response => {
        this.resourceLabelOptions = []
        console.log(response.data)
        this.resourceLabelOptions.push(response.data)
      })
    },
    changeElementLabel(node) {
      this.elementLabel = node.leafName
      this.disabledElement = false
      this.elementOptions = []
      elementManagement.getProductOptionsByLabel(this.elementLabel).then(result => {
        if (result.code === 200) {
          this.elementOptions = result.data
        }
      })
    },
    changePrescriptionLabel(node) {

    },
    selectResourceLabel(node) {
      this.resourceOptions = [
        {
          resourceId: 1,
          resourceName: '软吊具（>2t）'
        },
        {
          resourceId: 2,
          resourceName: '软吊具（>3t）'
        },
        {
          resourceId: 3,
          resourceName: '软吊具（>5t）'
        },
        {
          resourceId: 4,
          resourceName: '软吊具（>10t）'
        }
      ]
      resourceManagement.getResourceOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.resourceOptions = result.data
        }
      })
    },
    addResource() {
      this.resources.push(this.newResource)
    },
    cancel() {

    },
    loadSubElement(tree, treeNode, resolve) {
      setTimeout(() => {
        processManagement.getSubElementsById(tree.elementId).then(result => {
            if (result.code === 200) {
              resolve(result.data)
              tree.subElements = result.data
              this.parentId = tree.elementId
              console.log(this.processData)
              console.log(this.parentId)
            }
          }
        )
      }, 1000)
    },
    addResourceToElement() {
      this.selectElement.usedResources = this.resources
    },
    labelNormalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs
      }
      return {
        id: node.leafId,
        label: node.leafName,
        value: node.leafValue,
        children: node.subLeafs
      }
    },
    elementNormalizer(node) {
      if (node.subElements && !node.subElements.length) {
        delete node.subElements
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    processNormalizer(node) {
      if (node.subElements && !node.subElements.length) {
        delete node.subElements
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleClickStructure() {
      this.selectElement = {}
      elementManagement.getElementById(this.selectElementId).then(result => {
        if (result.code === 200) {
          this.BOM = []
          // this.selectElement = result.data
          this.BOM.push(result.data)
        }
      })

      fileManagement.getModelFileByStructure(this.selectElementId).then(result => {
        if (result.code === 200) {
          this.modelFile = result.data
        }
      })
    },
    handleClickPrescription() {
      this.selectPrescription = {}
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result => {
        if (result.code === 200) {
          this.selectPrescription = result.data
        }
      })
    },
    handleCloseResource(item) {

    },
    processRecommand() {
      this.recommandProcess = [
        {
          elementId: 25369,
          elementNumber: '',
          elementName: '壳体准备工艺',
          elementDescription: '',
          elementRemark: ['壳体内表面绝热层喷砂、打磨、清理'],
          elementRequirements: {},
          elementOtherProperties: {}
        }, {
          elementId: 25385,
          elementNumber: '',
          elementName: '金属壳体准备工艺',
          elementDescription: '',
          elementRemark: ['壳体除油、粗化处理'],
          elementRequirements: {},
          elementOtherProperties: {}

        }, {
          elementId: 31246,
          elementNumber: '',
          elementName: '典型壳体准备工艺',
          elementDescription: '金属壳体准备典型工艺方案',
          elementRemark: [''],
          elementRequirements: {},
          elementOtherProperties: {}

        }
      ]
      let vo = {
        elementId: this.selectElementId,
        processLabel: this.processType
      }
      recommand.getRecommandProcess(vo).then(result => {
        if (result.code === 200) {
          this.recommandProcess = result.data
        }
      })
      this.selectWindowShow = true
      this.title = '工艺方案推荐'
      this.loading = false
    },
    processCreate() {
      this.title = '创建工艺'
      this.modifyProcessShow = true
      this.newElement = {}
    },
    handleUpdate(row) {
      this.modifyProcessShow = true
      this.title = '修改'
      this.selectElement = row
      this.newElement = JSON.parse(JSON.stringify(row))
    },
    handleDelete(row) {
      let parentId = getParentNodeId(this.processData, row.elementId)
      this.parentElement = getData(this.processData, parentId)
      let index = this.parentElement.subElements.indexOf(row)
      if (index !== -1) {
        this.parentElement.subElements.splice(index, 1)
      }
    },
    handleAdd(data) {
      this.newElement = data
    },
    handleSave(row) {
      let data = {}
      processManagement.createElementForParent(data).then(result => {
        if (result.code === 200) {

        }
      })
    },
    addElement(row) {
      let item = row
      this.recommandProcess = []
      if (item.dynamicLabels.includes('Sequence')) {
        let vo = {
          sequenceId: item.elementId,
          label: this.processType,
          originSequence: item
        }
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        recommand.getRecommandSequence(vo).then(result => {
          if (result.code === 200) {
            this.recommandProcess = result.data
          }
        })
      } else if (item.dynamicLabels.includes('Step')){
        let vo = {
          stepId: item.elementId,
          label: this.processType,
          originStep: item
        }
        console.log("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
        recommand.getRecommandStep(vo).then(result => {
          if (result.code === 200) {
            this.recommandProcess = result.data
          }
        })
      }
      this.modifyProcessShow = true
      this.title = '添加'
      this.selectElement = row
    },
    createSubProcess(row) {
      this.title = '添加子级'
      this.newElement = {}
      this.selectElement = row
      this.modifyProcessShow = true
    },
    recommandResource(row) {
      let item = row
      let vo = {
        elementId: row.elementId,
        label: this.processType,
        originElement: item
      }
      recommand.getRecommandResource(vo).then(result => {
        if (result.code === 200) {
          this.resources = result.data
        }
      })
      this.selectResourceShow = true
      this.selectElement = row
    },
    handleSelect(data) {
      console.log(data)
      if (this.processData.length === 0) {
        this.processData.push(data)
        console.log(this.processData)
        this.recommandProcess = []
        // processManagement.getElementById(data.elementId).then(result =>{
        //   if(result.code === 200){
        //     console.log(result.data)
        //     this.processData.push(result.data)
        //     console.log(this.processData)
        //   }
        // })
      } else {
        let parentId = getParentNodeId(this.processData, this.selectElement.elementId)
        this.parentElement = getData(this.processData, parentId)
        this.parentElement.subElements.push(data)

      }
    },
    processSave(){
      processManagement.saveTreeData(this.processData[0]).then(result =>{
        if(result.code === 200){
          this.$modal.msg("保存成功")
        }
      })
    }
  }
}
</script>

<style scoped>
.el-button {
  left: 50%;
}

</style>
