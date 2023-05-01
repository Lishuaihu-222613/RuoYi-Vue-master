<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="12" :xs="24">
        <div class="head-container">
          <el-row>
            <el-col :span="10">
              <treeselect v-model="elementType" :options="elementTree"
                          placeholder="请选择类别" @select="changeElementLabel" :normalizer="labelNormalizer"
              />
            </el-col>
            <el-col :span="10">
              <treeselect v-model="selectElementId" :disabled="disabledElement" :options="elementOptions" :show-count="true"
                          placeholder="请选择产品或组件" :normalizer="elementNormalizer"
              />
            </el-col>
            <el-col :span="4">
              <el-button @click="handleClickStructure" >加 载</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10">
              <treeselect v-model="prescriptionType" :options="prescriptionTree"
                          placeholder="请选择类别" @select="changePrescriptionLabel" :normalizer="labelNormalizer"
              />
            </el-col>
            <el-col :span="10">
              <el-select v-model="selectPrescriptionId" filterable placeholder="请选择推进剂配方">
                <el-option
                  v-for="item in prescriptionOptions"
                  :key="item.prescriptionId"
                  :label="item.prescriptionName"
                  :value="item.prescriptionId">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-button @click="handleClickPrescription">加 载</el-button>
            </el-col>
          </el-row>
        </div>
        <div class="head-container" style="height: 400px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>产品模型</span>
            </div>
            <iFrame :src="modelFile.fileUrl"></iFrame>
          </el-card>
        </div>
        <div class="head-container">
          <el-tabs type="border-card">
            <el-tab-pane>
              <span slot="label"><i class="el-icon-date"></i>产品BOM</span>
              <el-tree
                ref="tree"
                :data="elementTree"
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
                    {{scope.row.material.materialName}}
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
          <el-col :offset="10" :span="6">
            <treeselect v-model="processType" :options="processTree" :show-count="true"
                        :normalizer="processNormalizer"
                        placeholder="请选择工艺类别"
            />
          </el-col>
          <el-col :span="4">
            <el-button @click="processRecommand" >推荐工艺</el-button>
          </el-col>
          <el-col :span="4">
            <el-button @click="processCreate" >创建元素</el-button>
          </el-col>
        </el-row>
        <el-row>
          <modifyElement ref="modifyElement"
                         :dialog="modifyProcessShow"
                         :selectElement="selectElement"
                         :title="title"
                         @closeDialog="() =>{ this.modifyProcessShow = false }"
                         @restore="() =>{this.selectElement = {}}"
          />
          <el-table :data="processData" border style="width: 100%">
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
            <el-table-column key="elementRemark" :show-overflow-tooltip="true"
                             align="center"
                             label="备注"
                             prop="elementRemark"
            >
              <template slot-scope="scope">
                <el-tag v-for="(item, index) in scope.row.elementRemark" :key="index" :index="index+''" type="success">
                  {{ index+1 + ':' + scope.row.elementRemark[index] }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column key="elementRequirements" :show-overflow-tooltip="true"
                             align="center"
                             label="要求" prop="elementRequirements"
            >
              <template  slot-scope="scope">
                <el-tag v-for="(value, key) in scope.row.elementRequirements" :key="key" :index="key+''" type="success">
                  {{ key + ':' + scope.row.elementRequirements[key] }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column key="elementOtherProperties" :show-overflow-tooltip="true"
                             align="center"
                             label="其他" prop="elementOtherProperties"
            >
              <template slot-scope="scope">
                <el-tag v-for="(value, key) in scope.row.elementOtherProperties" :key="key" :index="key+''" type="success">
                  {{ key + ':' + scope.row.elementOtherProperties[key] }}
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
                >修改工艺
                </el-button>
                <el-button
                  icon="el-icon-delete"
                  size="mini"
                  type="text"
                  @click="handleDelete(scope.row)"
                >删除工艺
                </el-button>
                <el-button
                  icon="el-icon-delete"
                  size="mini"
                  type="text"
                  @click="handleSave(scope.row)"
                >保存工艺
                </el-button>
                <el-button
                  icon="el-icon-edit"
                  size="mini"
                  type="text"
                  @click="recommandAfter(scope.row)"
                >推荐下一步
                </el-button>
                <el-button
                  icon="el-icon-edit"
                  size="mini"
                  type="text"
                  @click="createProcess(scope.row)"
                >添加子级
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-col>
    </el-row>

    <el-dialog :visible.sync="selectResourceShow" append-to-body title="选择资源" width="800px">
      <el-table v-loading="loading" :data="recommandProcess" row-key="elementId" >
        <el-table-column  key="elementId" align="center" label="元素编号" prop="elementId"/>
        <el-table-column  key="elementNumber" :show-overflow-tooltip="true" align="center"
                         label="元素序号" prop="elementNumber"
        />
        <el-table-column  key="elementName" :show-overflow-tooltip="true" align="center"
                         label="元素名称" prop="elementName"
        />
        <el-table-column  key="elementDescription" :show-overflow-tooltip="true" align="center"
                         label="内容描述"
                         prop="elementDescription"
        />
        <el-table-column  key="elementRemark" :show-overflow-tooltip="true" align="center"
                         label="备注" prop="elementRemark"
        >
          <template slot-scope="scope">
            <el-tag v-for="(item,index) in scope.row.elementRemark" :key="index" :index="index+''" type="success">
              {{ scope.row.elementRemark[index] }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column  key="elementRequirements" :show-overflow-tooltip="true" align="center"
                         label="相关要求" prop="elementRequirements"
        >
          <template slot-scope="scope">
            <el-tag v-for="(value, key) in scope.row.elementRequirements" :key="key" :index="key+''" type="success">
              {{ key + ':' + scope.row.elementRequirements[key+''] }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column  key="elementOtherProperties" :show-overflow-tooltip="true" align="center"
                         label="其他属性"
                         prop="elementOtherProperties"
        >
          <template slot-scope="scope">
            <el-tag v-for="(value, key) in scope.row.elementOtherProperties" :key="key" :index="key+''" type="success">
              {{ key + ':' + scope.row.elementOtherProperties[key+''] }}
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
              @click="handleSelect(scope.row)"
            >选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog :visible.sync="selectResourceShow" append-to-body title="选择资源" width="300px">
      <el-form >
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
        <el-form-item>
          <el-button type="primary" @click="cancel">取消</el-button>
          <el-button type="primary" @click="addResource">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>

</template>

<script>

import * as elementManagement from '@/api/system/elementManagement'
import * as prescriptionManagement from '@/api/system/prescriptionManagement'
import * as processManagement from '@/api/system/processManagement'
import * as fileManagement from '@/api/system/fileManagement'
import iFrame from '@/components/iFrame/index.vue'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import * as treeManagement from '@/api/system/treeManagement'
import modifyElement from '@/views/Process/components/modifyElement.vue'


export default {
  name: 'index',

  components: { modifyElement, Treeselect, iFrame },
  data(){
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
      selectWindowShow:false,
      labelTree:[],
      elementLabel:'',
      elementType:undefined,
      prescriptionType:undefined,
      prescriptionTree:[],
      elementTree:[],
      selectElementId:undefined,
      selectPrescriptionId:undefined,
      disabledElement:true,
      disabledPrescription:true,
      prescriptionOptions:[],
      elementOptions:[],
      selectElement:{
        elementId:0,
        elementName:"",
      },
      modelFile:{
        fileUrl:undefined
      },
      defaultProps:{
        children:"subElements",
        hasChildren:"hasSubChildren"
      },
      prescriptionElements:[],
      processType:undefined,
      processTree:[],
      processData:[],
      modifyProcessShow:false,
      selectProcess:{},
      selectResourceShow:false,
      newResource:{},
    }
  },
  created() {
    this.getElementList();
  },
  methods:{
    getElementList(){
      treeManagement.getTreeManagement(25500).then(response => {
        this.labelTree = []
        console.log(response.data)
        this.labelTree.push(response.data)
      })
    },
    changeElementLabel(node){
      this.elementLabel = node.leafValue;
      this.disabledElement = false;
      this.elementOptions = [];
    },
    changePrescriptionLabel(node){

    },
    labelNormalizer(node){
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
    elementNormalizer(node){
      if (node.subElements && !node.subElements.length) {
        delete node.subElements
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    processNormalizer(node){
      if (node.subElements && !node.subElements.length) {
        delete node.subElements
      }
      return {
        id: node.elementId,
        label: node.elementName,
        children: node.subElements
      }
    },
    handleClickStructure(){
      this.selectElement = {};
      elementManagement.getElementById(this.selectElementId).then(result =>{
        if(result.code ===200){
          this.selectElement = result.data
        }
      })
      fileManagement.getFilesByStructure(this.selectElementId).then(result =>{
        if(result.code === 200){
          this.modelFile = result.data
        }
      })
    },
    handleClickPrescription(){
      this.selectPrescription = {}
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.selectPrescription = result.data
        }
      })
    },
    processRecommand(){
      this.selectWindowShow = true
      this.recommandProcess = []
      processManagement.getRecommandProcessByStructure(this.selectElementId).then(result =>{
        if(result.code === 200){
          this.recommandProcess.push(result.data)
        }
      })
      processManagement.getRecommandProcessByPrescription(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.recommandProcess.push(result.data)
        }
      })
      this.loading = false
    },
    processCreate(){
      this.modifyProcessShow = true
      
    },
    handleUpdate(row){
      this.modifyProcessShow = true
      this.selectProcessElement = row
    },
    handleDelete(row){

    },
    handleSave(row){
      let data = {

      }
      processManagement.createElementForParent(data).then(result =>{
        if(result.code === 200){

        }
      })
    },
    recommandAfter(row){

    },
    createProcess(){

    },
    handleSelect(data){
      if(this.processData.length === 0){
        this.processData.push(data)
      } else {

      }
    }
  },
}
</script>

<style scoped>
.el-button{
  left: 50%;
}

</style>
