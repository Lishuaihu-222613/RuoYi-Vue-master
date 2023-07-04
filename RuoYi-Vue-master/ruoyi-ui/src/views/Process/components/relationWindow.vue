<template>
  <el-dialog :visible.sync="dialogFormVisible" title="关系编辑" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs type="border-card">
      <el-tab-pane label="关联信息编辑">
        <el-form>
          <el-form-item :label-width="formLabelWidth" label="关联零、部、组（整）件">
            <el-row>
              <el-tag
                v-for="structure in structures"
                :key="structure.elementId"
                closable
                type="info"
                @close="handleCloseStructure(structure)"
              >
                {{ structure.elementName }}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="structureLabel"
                            :clearable="true"
                            :normalizer="normalizer"
                            :options="structureLabelOptions"
                            :searchable="true"
                            placeholder="请选择结构标签"
                            @select="selectStructureLabel"
                />
              </el-col>
              <el-col :span="10">
                <treeselect v-model="structureId"
                            :clearable="true"
                            :load-options="loadOptions"
                            :normalizer="elementNormalizer"
                            :options="structureOptions"
                            :searchable="true"
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
                type="info"
                @close="handleCloseResource(resource)"
              >
                {{ resource.resourceName }}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="resourceLabel"
                            :clearable="true"
                            :normalizer="normalizer"
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
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row>
              <el-tag
                v-for="file in files"
                :key="file.fileId"
                closable
                type="info"
                @close="handleCloseFile(file)"
              >
                {{ file.fileName }}
              </el-tag>
            </el-row>
            <el-row class="row-bg" justify="space-around" type="flex">
              <el-col :span="10">
                <treeselect v-model="fileLabel"
                            :clearable="true"
                            :normalizer="normalizer"
                            :options="fileLabelOptions"
                            :searchable="true"
                            placeholder="请选择标签"
                            @select="selectFileLabel"
                />
              </el-col>
              <el-col :span="10">
                <el-select v-model="newFile" placeholder="请选择" value-key="fileId">
                  <el-option
                    v-for="item in fileOptions"
                    :key="item.fileId"
                    :label="item.fileName"
                    :value="item"
                  >
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
            <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">修改关系
            </el-button>
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
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
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
      beforeElements: [],
      afterElements: [],
      andElements: [],
      orElements: [],
      subElements: [],
      structures: [],
      structureLabel: '',
      structureLabelOptions: [],
      resourceLabelOptions: [],
      fileLabelOptions: [],
      structureId: undefined,
      structure: {},
      newResource: {},
      structureOptions: [],
      resourceOptions: [],
      fileOptions: [],
      resources: [],
      resourceLabel: '',
      fileLabel: '',
      files: [],
      newFile: {},
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

      processManagement.getBeforeElements(this.elementId).then(result => {
        if (result.code === 200) {
          this.beforeElements = result.data.map(item => {
            return item.elementId
          })
          console.log("beforeElements",this.beforeElements)
        }
      })
      processManagement.getAfterElements(this.elementId).then(result => {
        if (result.code === 200) {
          this.afterElements = result.data.map(item => {
            return item.elementId
          })
        }
        console.log("afterElements",this.afterElements)
      })
      processManagement.getAndElements(this.elementId).then(result => {
        if (result.code === 200) {
          this.andElements = result.data.map(item => {
            return item.elementId
          })
          console.log("andElements",this.andElements)
        }
      })
      processManagement.getOrElements(this.elementId).then(result => {
        if (result.code === 200) {
          this.orElements = result.data.map(item => {
            return item.elementId
          })
          console.log("orElements",this.orElements)
        }
      })
      structureManagement.getStructureByRelatedId(this.elementId).then(result => {
        this.structures = result.data
      })
      fileManagement.getFilesByRelatedId(this.elementId).then(result => {
        this.files = result.data
      })
      resourceManagement.getResourcesByRelatedId(this.elementId).then(result => {
        this.resources = result.data
      })
      processManagement.getParentElementById(this.elementId).then(result => {
        if (result.code === 200) {
          this.parentId = result.data.elementId
        }
      })

      processManagement.getSubElementsById(this.parentId).then(result => {
        console.log(this.parentId)
        if (result.code === 200) {
          console.log(result.data)
          this.elements = result.data.map(item => {
            return Object.assign({}, {
              'key': item.elementId,
              'label': item.elementName, 'number': item.elementNumber
            })
          })
          this.removeItemSelf(this.elements)
          console.log(this.elements)
        }
      })
    },

    handleClose() {
      this.elements = []
      this.selectedRelations = []
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },

    normalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },

    elementNormalizer(node) {
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
        simulateAsyncOperation(() => {
          elementManagement.getSubElementsById(parentNode.elementId).then(result => {
            if (result.code === 200) {
              parentNode.subElements = result.data
              callback()
            }
          })
        })
      }
    },

    handleCloseStructure(data) {
      this.structures.splice(this.structures.indexOf(data), 1)
    },

    handleCloseResource(data) {
      this.resources.splice(this.resources.indexOf(data), 1)
    },

    handleCloseFile(data) {
      this.files.splice(this.files.indexOf(data), 1)
    },

    selectStructureLabel(node, instanceId) {
      this.structureOptions = []
      structureManagement.getProductOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.structureOptions = result.data
        }
      })
    },
    selectResourceLabel(node, instanceId) {
      this.resourceOptions = []
      resourceManagement.getResourceOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.resourceOptions = result.data
        }
      })
    },
    selectFileLabel(node, instanceId) {
      this.fileOptions = []
      fileManagement.getFileOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.files.push = result.data
        }
      })
    },

    addStructure() {
      elementManagement.getSingleElementById(this.structureId).then(result => {
        if (result.code === 200) {
          this.structures.push(result.data)
        }
      })
      console.log(this.structure)
      this.structureId = undefined
    },

    addResource() {
      this.resources.push(this.newResource)
      this.newResource = {}
    },
    addFile() {
      this.files.push(this.newFile)
      this.newFile = {}
    },

    removeItemSelf(arr) {
      let index = NaN
      for (let i in arr) {
        if (arr[i]['key'] === this.elementId) {
          index = i
          break
        }
      }
      if (!isNaN(index)) {
        arr.splice(index, 1)
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
        elementId: this.elementId,
        relations: this.selectedRelations
      }
      if (this.relation === 'before') {
        processManagement.modifyBeforeElement(ss).then(result => {
          if (result.code === 200) {
            this.$modal.msgSuccess('关系修改成功！')
          }
        })
      } else if (this.relation === 'after') {
        processManagement.modifyAfterElement(ss).then(result => {
          if (result.code === 200) {
            this.$modal.msgSuccess('关系修改成功！')
          }
        })
      } else if (this.relation === 'and') {
        processManagement.modifyAndElement(ss).then(result => {
          if (result.code === 200) {
            this.$modal.msgSuccess('关系修改成功！')
          }
        })
      } else if (this.relation === 'or') {
        processManagement.modifyOrElement(ss).then(result => {
          if (result.code === 200) {
            this.$modal.msgSuccess('关系修改成功！')
          }
        })
      }
    },

    onSubmit() {
      let relation = {
        elementId: this.elementId,
        structures: this.structures.map(item => {
          return item.elementId
        }),
        resources: this.resources.map(item => {
          return item.resourceId
        }),
        files: this.files.map(item => {
          return item.fileId
        })
      }
      console.log(relation)
      processManagement.updateRelation(relation).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess('修改成功！')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>


