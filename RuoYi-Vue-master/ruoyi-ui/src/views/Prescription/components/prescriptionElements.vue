<template>
  <el-dialog :visible.sync="dialogFormVisible" title="配方组分编辑" top="50vh" width="70%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="配方组分">
        <el-form ref="form" label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="材料类别">
                <treeselect v-model="materialLabel" :normalizer="normalizer" :options="materialLabelOptions"
                            :show-count="true" placeholder="请选择材料类别" @select="changeMaterialLabel"
                />
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="材料种类">
                <el-select v-model="newElement" placeholder="请选择材料">
                  <el-option
                    v-for="option in materialOptions"
                    :key="option.materialId"
                    :label="option.materialName"
                    :value="option"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="质量分数">
                <el-input-number v-model="newMaterialPercentage" :precision="2" controls-position="right"
                ></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item>
                <el-button @click="addMaterialElement">增 加</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-table
          :data="elements"
          border
          style="width: 100%"
        >
          <el-table-column
            label="组分"
            prop="materialName"
          >
            <template slot-scope="scope">
              <el-tag>{{scope.row.material.materialName}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="质量分数"
            prop="percentage"
          >
            <template slot-scope="scope">
              <el-tag>{{scope.row.percentage+"%"}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-delete"
                size="mini"
                type="text"
                @click="removeMaterialElement(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="关联产品">
        <el-form ref="productForm">
          <el-form-item label="关联产品">
            <el-row>
              <el-tag
                v-for="(item,index) in products"
                :key="item.elementId"
                closable
                type="info"
                @close="handleCloseProduct(item)"
              >
                {{ item.elementName }}
              </el-tag>
            </el-row>
          </el-form-item>
          <el-form-item label="新增产品">
            <el-row>
              <el-col :span="10">
                <treeselect v-model="productLabel"
                            :clearable="true"
                            :normalizer="normalizer"
                            :options="productLabelOptions"
                            :searchable="true"
                            placeholder="请选择标签"
                            @select="changeProductLabel"
                />
              </el-col>
              <el-col :span="10">
                <el-select v-model="newProduct" filterable placeholder="请选择产品">
                  <el-option
                    v-for="option in filterProductOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.element"
                  >
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click.prevent="addProduct">添加</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-row>
              <el-col :offset="18" :span="6">
                <el-button type="primary" @click="onSubmitProduct">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="关联工艺">
        <el-form ref="processForm">
          <el-form-item label="相关工艺">
            <el-row>
              <el-tag
                v-for="item in processes"
                :key="item.elementId"
                closable
                type="info"
                @close="handleCloseProcess(item)"
              >
                {{ item.elementName }}
              </el-tag>
            </el-row>
          </el-form-item>
          <el-form-item label="新增工艺">
            <el-row>
              <el-col :span="10">
                <treeselect v-model="processLabel"
                            :clearable="true"
                            :normalizer="normalizer"
                            :options="processLabelOptions"
                            :searchable="true"
                            placeholder="请选择分类"
                            @select="changeProcessLabel"
                />
              </el-col>
              <el-col :span="10">
                <el-select v-model="newProcess" filterable placeholder="请选择工艺">
                  <el-option
                    v-for="option in filterProcessOptions"
                    :key="option.elementId"
                    :label="option.elementName"
                    :value="option.element"
                  >
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button @click="addProcess">增 加</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-row>
              <el-col :offset="18" :span="6">
                <el-button type="primary" @click="onSubmitProcess">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as materialManagement from '@/api/system/materialManagement'
import * as structureManagement from '@/api/system/elementManagement'
import * as processManagement from '@/api/system/processManagement'
import { getAllProducts } from '@/api/system/elementManagement'
import { getAllProcess } from '@/api/system/processManagement'
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'

export default {
  name: 'prescriptionElements',
  components: { Treeselect },
  props: {
    selectPrescriptionId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    }
  },
  watch: {
    selectPrescriptionId: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.prescriptionId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    title: {
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },

  created() {

  },

  data() {
    return {
      // 遮罩层
      loading: true,
      // 知识树管理树选项
      options: [],
      materialOptions: [],
      productOptions: [],
      processOptions: [],
      tabPosition: 'left',
      prescriptionId: 0,
      materialLabel: '',
      productLabel: '',
      processLabel: '',
      materialLabelOptions: [],
      productLabelOptions: [],
      processLabelOptions: [],
      newMaterial: {},
      newProduct: {},
      newProcess: {},
      filterMaterialOptions: [],
      filterProductOptions: [],
      filterProcessOptions: [],
      newMaterialPercentage: 0.0,
      windowTitle: '创建组分',
      elements: [
        {
          material:{
            materialId: 0,
            materialName: '端羧基聚丁二烯丙烯腈',
          },
          percentage: 99.65
        },
      ],
      newElement: {
        materialId: undefined,
        materialName: ''
      },
      products: [],
      processes: [],
      dialogFormVisible: false,
      formLabelWidth: '100px',
      inputVisible: false,
      inputValue: '',
      selectLabel: ''
    }
  },
  methods: {

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

    getTreeselect() {
      treeManagement.getTreeManagement(25451).then(response => {
        console.log(response.data)
        this.materialLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.processLabelOptions.push(response.data)
      })
      treeManagement.getTreeManagement(25451).then(response => {
        console.log(response.data)
        this.productLabelOptions.push(response.data)
      })
    },

    handleCloseProduct(item){
      this.products.splice(this.products.indexOf(item), 1)
    },
    handleCloseProcess(item){
      this.processes.splice(this.processes.indexOf(item), 1)
    },

    changeMaterialLabel(node,instanceId) {
      this.filterMaterialOptions = []
      materialManagement.getMaterialOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.filterMaterialOptions.push(result.data)
        }
      })
    },
    changeProductLabel(node,instanceId) {
      this.filterProductOptions = []
      structureManagement.getProductOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.filterProductOptions.push(result.data)
        }
      })
    },
    changeProcessLabel(node,instanceId) {
      this.filterProcessOptions = []
      processManagement.getProcessOptionsByLabel(node.leafName).then(result => {
        if (result.code === 200) {
          this.filterProcessOptions.push(result.data)
        }
      })
    },
    handleOpen() {

      this.getTreeselect()

      console.log(this.prescriptionId)
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result => {
        if (result.code === 200) {
          console.log('------------------')
          console.log(result.data)
          this.elements = []
          this.elements = result.data
          console.log(this.elements)
        }
      })
      structureManagement.getProductsByRelatedId(this.selectPrescriptionId).then(result => {
        if (result.code === 200) {
          this.products = []
          this.products.push(result.data)
        }
      })
      processManagement.getProcessByRelatedId(this.selectPrescriptionId).then(result => {
        if (result.code === 200) {
          this.processes = []
          this.processes.push(result.data)
        }
      })
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },

    // 表单重置
    reset() {
      this.elements = []
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false
      this.reset()
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },

    addMaterialElement() {
      let item = {
        material:this.newElement,
        percentage: this.newMaterialPercentage
      }
      this.elements.push(item)
      this.newMaterialPercentage = 0.0
    },
    removeMaterialElement(item) {
      let index = this.elements.indexOf(item)
      if (index !== -1) {
        this.elements.splice(index, 1)
      }
    },
    addProduct() {
      this.products.push(this.newProductId)
      this.newProductId = undefined
    },
    removeProductElement(item) {
      let index = this.products.indexOf(item)
      if (index !== -1) {
        this.products.splice(index, 1)
      }
    },
    addProcess() {
      this.processes.push(this.newProcessId)
      this.newProcessId = undefined
    },
    removeProcessElement(item) {
      let index = this.processes.indexOf(item)
      if (index !== -1) {
        this.processes.splice(index, 1)
      }
    },
    onSubmitMaterial() {
      let data = {
        prescriptionId: this.prescriptionId,
        elements: this.elements
      }
      console.log(data)
      prescriptionManagement.modifyElements(data).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('配方组分修改成功！')
        }
      })
    },
    onSubmitProduct() {
      let data = {
        prescriptionId: this.prescriptionId,
        products: this.products
      }
      console.log(data)
      prescriptionManagement.modifyProducts(data).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('关联产品修改成功！')
        }
      })
    },
    onSubmitProcess() {
      let data = {
        prescriptionId: this.prescriptionId,
        processes: this.processes
      }
      console.log(data)
      prescriptionManagement.modifyProcesses(data).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess('关联工艺修改成功！')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
