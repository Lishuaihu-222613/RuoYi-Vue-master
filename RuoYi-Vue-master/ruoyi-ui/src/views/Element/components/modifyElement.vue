<template>
  <!-- 添加或修改产品配置对话框 -->
  <el-dialog :title="windowTitle" :visible.sync="dialogFormVisible" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form ref="form" :model="element" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="名称" prop="elementName">
            <el-input v-model="element.elementName" maxlength="30" placeholder="请输入元素名称"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数量" prop="elementQuantity">
            <el-input-number v-model="element.elementQuantity" :max="10" :min="1" controls-position="right"
            ></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="描述" prop="elementDescription">
          <el-input v-model="element.elementDescription" autosize placeholder="请输入元素描述" type="textarea"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="密度" prop="elementDensity">
            <el-input-number v-model="element.elementDensity" :precision="2" placeholder="请输入元素密度"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="质量" prop="elementMass">
            <el-input-number v-model="element.elementMass" :precision="2" placeholder="请输入元素质量"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="体积" prop="elementVolume">
            <el-input-number v-model="element.elementVolume" :precision="2" placeholder="请输入元素体积"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="表面积" prop="elementWetArea">
            <el-input-number v-model="element.elementWetArea" :precision="2" placeholder="请输入元素表面积"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="包容盒" prop="elementBoundingBox">
          <el-input v-model="element.elementBoundingBox" maxlength="30" placeholder="请输入元素包容盒"/>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="来源">
            <el-radio-group v-model="element.elementSource">
              <el-radio :label="'自制'">自制</el-radio>
              <el-radio :label="'外源'">外源</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="包含子级">
            <el-switch
              v-model="element.hasSubElements"
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
      <el-row>
        <el-col :span="12">
          <el-form-item label="类别">
            <el-radio-group v-model="elementType">
              <el-radio :label="'AssemblyProduct'">产 品</el-radio>
              <el-radio :label="'AssemblyComponent'">组 件</el-radio>
              <el-radio :label="'AssemblyPart'">零 件</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="标签">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="dynamicLabels"
                          :clearable="true"
                          :multiple="true"
                          :normalizer="labelNormalizer"
                          :options="labelOptions"
                          :searchable="true"
                          placeholder="请选择标签"
              />
            </el-row>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="!(elementType === 'AssemblyProduct')">
        <el-form-item label="父级">
          <treeselect v-model="parentId"
                      :clearable="true"
                      :normalizer="elementNormalizer"
                      :options="elementOptions"
                      :searchable="true"
                      placeholder="请选择父级"
          />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="模型文件">
            <el-row>
              <el-tag
                v-if="structureModel === {}"
                closable
                type="info"
                @close="handleCloseModel(structureModel)"
              >
                {{ structureModel.fileName }}
              </el-tag>
            </el-row>
            <el-select v-model="structureModel" filterable placeholder="请选择模型文件" value-key="fileId">
              <el-option
                v-for="item in modelOptions"
                :key="item.fileId"
                :label="item.fileName"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联文件">
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
                            :normalizer="labelNormalizer"
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
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>

</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as elementManagement from '@/api/system/elementManagement'
import * as treeManagement from '@/api/system/treeManagement'
import * as fileManagement from '@/api/system/fileManagement'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'modifyElement',
  components: { Treeselect },
  props: {
    selectElementId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    proId: {
      type: Number,
      default: 0
    },
    pId: {
      type: Number,
      default: 0
    },
    title: {
      type: String,
      default: ''
    }
  },
  watch: {
    selectElementId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
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
        this.parentId = newVal
      }
    },
    proId: {
      handler(newVal, oldVal) {
        this.productId = newVal
      }
    },
    title: {
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },
  data() {
    return {
      dialogFormVisible: false,
      windowTitle: '创建元素',
      element: {
        elementId: 0,
        elementName: '',
        elementDescription: '',
        elementQuantity: 0,
        elementDensity: 0,
        elementMass: 0,
        elementVolume: 0,
        elementWetArea: 0,
        elementBoundingBox: '',
        elementSource: '自制',
        hasSubElements: false,
        dynamicLabels: []
      },
      elementType: '',
      dynamicLabels: [],
      labelOptions: [],
      fileLabelOptions: [],
      files: [],
      fileLabel: undefined,
      newFile: {},
      parentId: 0,
      productId: 0,
      structureModel: {},
      modelFileId: undefined,
      modelOptions: [],
      associatedFileId: [],
      fileOptions: [],
      elementOptions: [],
      elementId: 0,
      isTypical: false
    }
  },
  methods: {
    handleOpen() {
      treeManagement.getTreeManagement(25877).then(result => {
        this.labelOptions = []
        this.labelOptions.push(result.data)
      })
      treeManagement.getTreeManagement(25843).then(response => {
        console.log(response.data)
        this.fileLabelOptions = []
        this.fileLabelOptions.push(response.data)
      })
      if (this.windowTitle === '创建元素') {
        if (this.parentId !== 0) {
          this.elementType = 'AssemblyComponent'
          elementManagement.getElementById(this.productId).then(result => {
            this.elementOptions = []
            this.elementOptions.push(result.data)
          })
        } else {
          this.elementType = 'AssemblyProduct'
        }
      }
      else {
        elementManagement.getElementById(this.elementId).then(result => {
          if (result.code === 200) {
            this.element = result.data
            console.log(result.data)
            if (this.element.dynamicLabels.includes('TypicalElement')) {
              this.isTypical = true
              this.dynamicLabels = this.element.dynamicLabels.filter(item => item !== 'TypicalElement')
            }
            if (this.dynamicLabels.includes('AssemblyProduct')) {
              this.elementType = 'AssemblyProduct'
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'AssemblyProduct')
            } else if (this.dynamicLabels.includes('AssemblyComponent')) {
              this.elementType = 'AssemblyComponent'
              elementManagement.getParentElement(this.element.elementId).then(result => {
                this.parentId = result.data.elementId
              })
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'AssemblyComponent')
            } else if (this.dynamicLabels.includes('AssemblyPart')) {
              this.elementType = 'AssemblyPart'
              elementManagement.getParentElement(this.element.elementId).then(result => {
                this.parentId = result.data.elementId
              })
              this.dynamicLabels = this.dynamicLabels.filter(item => item !== 'AssemblyPart')
            }
          }
        })
        if (this.productId !== 0) {
          elementManagement.getElementById(this.productId).then(result => {
            if (result.code === 200) {
              this.elementOptions = []
              this.elementOptions.push(result.data)
            }
          })
        }
        fileManagement.getFilesByRelatedId(this.element.elementId).then(result => {
          if (result.code === 200) {
            this.files = result.data
          }
        })
        fileManagement.getModelFileByStructure(this.element.elementId).then(result => {
          if (result.code === 200) {
            this.structureModel = result.data
          }
        })
      }
      fileManagement.getAllModelFile().then(result => {
        if (result.code === 200) {
          this.modelOptions = result.data
        }
      })
      console.log(this.element)
      console.log(this.elementType)
    },
    handleClose() {
      this.dialogFormVisible = false
      this.reset()
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    labelNormalizer(node) {
      if (node.subLeafs && !node.subLeafs.length) {
        delete node.subLeafs
      }
      return {
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleCloseModel(data) {
      this.model = {}
    },
    addFile() {
      this.files.push(this.newFile)
      this.newFile = {}
    },
    handleCloseFile(data) {
      this.files.splice(this.files.indexOf(data), 1)
    },
    selectFileLabel(node, instanceId) {
      this.fileOptions = []
      fileManagement.getFileOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.fileOptions = result.data
        }
      })
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
    submitForm() {
      if (this.isTypical === true) {
        this.element.dynamicLabels.push('TypicalElement')
      }
      this.element.dynamicLabels.push(this.elementType)
      this.element.dynamicLabels = this.element.dynamicLabels.concat(this.dynamicLabels)
      if (this.windowTitle === '创建元素') {
        let data = {
          parentId: this.parentId,
          originElement: this.element
        }
        elementManagement.createElement(data).then(result => {
          if (result.code === 200) {
            this.element = result.data
            this.$modal.msgSuccess('创建成功！')
          }
        })
      } else {
        let data = {
          parentId: this.parentId,
          originElement: this.element
        }
        console.log(this.element)
        elementManagement.updateElement(data).then(result => {
          if (result.code === 200) {
            this.element = result.data
            this.$modal.msgSuccess('修改成功！')
          }
        })
      }
      console.log(this.element)
      if (this.model.fileId !== 0 || this.files.length > 0) {
        let relations = {
          elementId: this.element.elementId,
          modelFileId: this.model.fileId,
          associatedFileIds: this.files.map(item => {
            return item.fileId
          })
        }
        elementManagement.modifyFiles(relations).then(result => {
          if (result.code === 200) {

          }
        })
      }
      this.loading = false
    },
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.element = {
        elementId: 0,
        elementName: '',
        elementDescription: '',
        elementQuantity: 0,
        elementDensity: 0,
        elementMass: 0,
        elementVolume: 0,
        elementWetArea: 0,
        elementBoundingBox: '',
        elementSource: '自制',
        hasSubElements: false,
        dynamicLabels: []
      }
      this.elementType = ''
      this.modelOptions = []
      this.associatedFileId = []
      this.fileOptions = []
      this.parentId = 0
      this.elementId = 0
      this.files = []
      this.model = {}
      this.isTypical = false
      this.dynamicLabels = []
      this.resetForm('form')
    }
  }
}
</script>

<style scoped>

</style>
