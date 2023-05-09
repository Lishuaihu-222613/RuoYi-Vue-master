<template>
  <el-dialog :visible.sync="dialogFormVisible" title="关系编辑" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs type="border-card">
      <el-tab-pane label="关联信息编辑">
        <el-form >
          <el-form-item :label-width="formLabelWidth" label="关联零、部、组（整）件">
            <el-row>
              <el-tag
                v-for="structure in structures"
                :key="structure.elementId"
                closable
                @close="handleCloseStructure(structure)"
                type="info">
                {{structure.elementName}}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="structureLabel"
                            :clearable="true"
                            :searchable="true"
                            :normalizer="normalizer"
                            @select="selectStructureLabel"
                            :options="structureLabelOptions"
                            placeholder="请选择结构标签"
                />
              </el-col>
              <el-col :span="10">
                <treeselect v-model="structureId"
                            :clearable="true"
                            :searchable="true"
                            @select="selectStructure"
                            :load-options="loadOptions"
                            :normalizer="elementNormalizer"
                            :options="structureOptions"
                            placeholder="请选择结构元素"
                />
              </el-col>
              <el-col :span="4">
                <el-button @click.prevent="addStructure">添加</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用资源">
            <el-row>
              <el-tag
                v-for="resource in resources"
                :key="resource.resourceId"
                closable
                @close="handleCloseResource(resource)"
                type="info">
                {{resource.resourceName}}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="resourceLabel"
                            :clearable="true"
                            :searchable="true"
                            :normalizer="normalizer"
                            @select="selectResourceLabel"
                            :options="resourceLabelOptions"
                            placeholder="请选择资源标签"
                />
              </el-col>
              <el-col :span="10">
                <el-select value-key="resourceId" v-model="newResource" multiple placeholder="请选择">
                  <el-option
                    v-for="item in resourceOptions"
                    :key="item.resourceId"
                    :label="item.resourceName"
                    :value="item">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click.prevent="addResource">添加</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row>
              <el-tag
                v-for="file in files"
                :key="file.fileId"
                closable
                @close="handleCloseFile(file)"
                type="info">
                {{file.fileName}}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="fileLabel"
                            :clearable="true"
                            :searchable="true"
                            :normalizer="normalizer"
                            @select="selectFileLabel"
                            :options="fileLabelOptions"
                            placeholder="请选择标签"
                />
              </el-col>
              <el-col :span="10">
                <el-select value-key="fileId" v-model="newFile" multiple placeholder="请选择">
                  <el-option
                    v-for="item in fileOptions"
                    :key="item.fileId"
                    :label="item.fileName"
                    :value="item">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click.prevent="addFile">添加</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :offset="18" :span="6">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>

      </el-tab-pane>
      <el-tab-pane label="元素关系编辑">
        <el-row>
          <p style="text-align: center; margin: 0 0 20px">关系选择</p>
          <el-select v-model="relation" clearable placeholder="请选择关系" style="right :0px"
                     @change="selectRelationKind"
          >
            <el-option
              v-for="item in relations"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-row>
        <div style="text-align: center">
          <el-transfer
            v-model="selectedRelations"
            :button-texts="['移除', '添加']"
            :data="elements"
            :filter-method="filterMethod"
            :format="{noChecked: '${total}',hasChecked: '${checked}/${total}'}"
            :titles="['待选元素', '已选元素']"
            filterable
            style="text-align: left; display: inline-block"
          >
            <span slot-scope="{ option }">{{ option.number }}-{{ option.label }}</span>
            <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">修改关系</el-button>
          </el-transfer>
        </div>

      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>

import * as processManagement from '@/api/system/processManagement'
import Treeselect from '@riophae/vue-treeselect'
import * as fileManagement from '@/api/system/fileManagement'
import * as resourceManagement from '@/api/system/resourceManagement'
import * as structureManagement from '@/api/system/elementManagement'
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import * as treeManagement from '@/api/system/treeManagement'
import { LOAD_CHILDREN_OPTIONS } from '@riophae/vue-treeselect'
import * as elementManagement from '@/api/system/elementManagement'

const simulateAsyncOperation = fn => {
  setTimeout(fn, 2000)
}

export default {

  name: 'relationWindow',
  components: { Treeselect },

  watch: {
    selectElementId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.elementId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    pId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.parentId = newVal
        }
      }
    }
  },

  props: {
    selectElementId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    pId: {
      type: Number,
      default: 0
    }
  },

  data() {
    return {
      elementId: 0,
      parentId: 0,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      element: {},
      elements: [],
      beforeElements:[],
      afterElements:[],
      andElements:[],
      orElements:[],
      subElements:[],
      structures:[
        {
          elementId:0,
          elementName:'测试产品1'
        }
      ],
      structureLabel:'',
      structureLabelOptions:[],
      resourceLabelOptions:[],
      fileLabelOptions:[],
      structureId:undefined,
      structure:{},
      newResource:{},
      structureOptions:[],
      resourceOptions:[],
      fileOptions:[],
      resources:[
        {
          resourceId:0,
          resourceName:'吊车1'
        },
        {
          resourceId:1,
          resourceName:'搅拌釜1'
        },
        {
          resourceId:2,
          resourceName:'喷涂系统Ⅱ'
        },
        {
          resourceId:3,
          resourceName:'电热鼓风干燥箱'
        },
        {
          resourceId:4,
          resourceName:'软吊具1'
        },
        {
          resourceId:5,
          resourceName:'立式喷砂工装1'
        },
        {
          resourceId:6,
          resourceName:'排气管固定工装1'
        },
        {
          resourceId:7,
          resourceName:'立式喷砂工装1'
        },
      ],
      resourceLabel:'',
      fileLabel:'',
      files:[
        {
          fileId:0,
          fileName:'壳体准备工艺规程文件'
        }
      ],
      newFile:{},
      selectedRelations: [],
      relations: [
        {
        value: 'before',
        label: '前道关系'
      }, {
        value: 'after',
        label: '后道关系'
      }, {
        value: 'and',
        label: '逻辑与关系'
      }, {
        value: 'or',
        label: '逻辑或关系'
      }],
      relation: ''
    }
  },
  methods: {

    getTreeselect() {
      treeManagement.getTreeManagement(25877).then(response => {
        this.structureLabelOptions = []
        console.log(response.data)
        this.structureLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25780).then(response => {
        this.resourceLabelOptions = []
        console.log(response.data)
        this.resourceLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25843).then(response => {
        this.fileLabelOptions = []
        console.log(response.data)
        this.fileLabelOptions.push(response.data)
      })
    },

    handleOpen() {

      this.getTreeselect()

      processManagement.getBeforeElements(this.elementId).then(result =>{
        if(result.code === 200){
          this.beforeElements = result.data.map(item=>{
            return item.elementId;
          })
        }
      })
      processManagement.getAfterElements(this.elementId).then(result =>{
        if(result.code === 200){
          this.afterElements = result.data.map(item=>{
            return item.elementId;
          })
        }
      })
      processManagement.getAndElements(this.elementId).then(result =>{
        if(result.code === 200){
          this.andElements = result.data.map(item=>{
            return item.elementId;
          })
        }
      })
      processManagement.getOrElements(this.elementId).then(result =>{
        if(result.code === 200){
          this.orElements = result.data.map(item=>{
            return item.elementId;
          })
        }
      })
      structureManagement.getStructureByRelatedId(this.elementId).then(result =>{
        this.structures = result.data
      } )
      fileManagement.getFilesByRelatedId(this.elementId).then(result =>{
        this.files = result.data
      })
      resourceManagement.getResourcesByRelatedId(this.elementId).then(result =>{
        this.resources = result.data
      })

      let oriElements;
      processManagement.getSubElementsById(this.parentId).then(result => {
        if(result.code === 200){
          oriElements = result.data
          this.elements = oriElements.map(item=>{
            return Object.assign({},{'key':item.elementId,
              'label':item.elementName,'number':item.elementNumber
            })
          })
          this.removeItemSelf(this.elements)
          console.log(this.elements)
        }
      });
    },

    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },

    normalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs;
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },

    elementNormalizer(node){
      if (node.subElements && !node.subElements.length) {
        node.subElements = null
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },

    loadOptions({ action, parentNode, callback }) {

      if (action === LOAD_CHILDREN_OPTIONS) {
        simulateAsyncOperation(() =>{
          elementManagement.getSubElementsById(parentNode.elementId).then(result =>{
            if(result.code === 200){
              parentNode.subElements = result.data
              callback()
            }
          })
        })
      }
    },

    handleCloseStructure(data){
      this.structures.splice(this.structures.indexOf(data), 1);
    },

    handleCloseResource(data){
      this.resources.splice(this.resources.indexOf(data), 1);
    },

    handleCloseFile(data){
      this.files.splice(this.files.indexOf(data), 1);
    },

    selectStructureLabel(node,instanceId){
      this.structureOptions = []
      structureManagement.getProductOptionsByLabel(node.label).then(result =>{
        if(result.code === 200){
          this.structureOptions.push(result.data)
        }
      })
    },
    selectResourceLabel(node,instanceId){
      this.resourceOptions = []
      resourceManagement.getResourceOptionsByLabel(node.label).then(result =>{
        if(result.code === 200 ){
          this.resourceOptions.push(result.data)
        }
      })
    },
    selectFileLabel(node,instanceId){
      this.fileOptions = []
      fileManagement.getFileOptionsByLabel(node.label).then(result =>{
        if(result.code === 200){
          this.files.push(result.data)
        }
      })
    },

    selectStructure(node,instanceId){
      this.structure = {
        elementId:node.id,
        elementName:node.label
      }
    },

    addStructure(){
      this.structures.push(this.newStructure)
      this.newStructure = {}
    },

    addResource(){
      this.resources.push(this.newResource)
      this.newResource = {}
    },
    addFile(){
      this.files.push(this.newFile)
      this.newFile = {}
    },

    removeItemSelf(arr){
      let index = NaN;
      for(let i in arr){
        if(arr[i]['key'] === this.elementId){
          index=i;
          break;
        }
      }
      if (!isNaN(index)) {
        arr.splice(index,1);
      }
    },
    selectRelationKind(rel) {

      if (rel === 'before') {
        this.selectedRelations = this.beforeElements
      } else if (rel === 'after') {
        this.selectedRelations = this.afterElements
      } else if (rel === 'and') {
        this.selectedRelations = this.andElements
      } else if (rel === 'or') {
        this.selectedRelations = this.orElements
      }
    },

    filterMethod(query, item) {
      return item.label.indexOf(query) > -1
    },

    modifyRelation() {
      let ss = {
        elementId:this.elementId,
        relations:this.selectedRelations
      }

      if(this.relation === "before"){
        processManagement.modifyBeforeElement(ss).then(result => {
          if(result.code === 200){
            this.$modal.msgSuccess("关系修改成功！")
          }
        })
      } else if(this.relation === "after"){
        processManagement.modifyAfterElement(ss).then(result => {
          if(result.code === 200){
            this.$modal.msgSuccess("关系修改成功！")
          }
        })
      } else if(this.relation ==="and"){
        processManagement.modifyAndElement(ss).then(result => {
          if(result.code === 200){
            this.$modal.msgSuccess("关系修改成功！")
          }
        })
      } else if(this.relation === "or"){
        processManagement.modifyOrElement(ss).then(result => {
          if(result.code === 200){
            this.$modal.msgSuccess("关系修改成功！")
          }
        })
      }
    },

    onSubmit() {
      let relation = {
        structures:this.structures.map((item,index) => {
          return Object.assign({},{'elementId':item.elementId})
        }),
        resources:this.resources.map((item,index) => {
          return Object.assign({},{'resources':item.resourceId})
        }),
        files:this.files.map((item,index) => {
          return Object.assign({},{'fileId':item.fileId})
        }),
      }
      processManagement.updateRelation(this.element).then(result => {
        if(result.code == 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>


