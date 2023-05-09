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
            <el-col :span="2" :offset="2">
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
              <el-select v-model="selectPrescriptionId" filterable placeholder="请选择推进剂配方" style="width: 100%">
                <el-option
                  v-for="item in prescriptionOptions"
                  :key="item.prescriptionId"
                  :label="item.prescriptionName"
                  :value="item.prescriptionId">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="2" :offset="2">
              <el-button @click="handleClickPrescription">加 载</el-button>
            </el-col>
          </el-row>
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
                :data="elementOptions"
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
          <el-col :offset="12" :span="6">
            <treeselect v-model="processType" :options="processTree" :show-count="true"
                        :normalizer="processNormalizer"
                        placeholder="请选择工艺类别"
            />
          </el-col>
          <el-col :span="6">
            <el-button @click="processRecommand" >推荐工艺</el-button>
            <el-button @click="processCreate" >创建工艺</el-button>
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
          <el-table :data="processData" border row-key="elementId" :tree-props="{children: 'subElements', hasChildren: 'hasChildren'}">
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
                  icon="el-icon-document-copy"
                  size="mini"
                  type="text"
                  @click="handleSave(scope.row)"
                >保存工艺
                </el-button>
                <el-button
                  icon="el-icon-s-promotion"
                  size="mini"
                  type="text"
                  @click="recommandAfter(scope.row)"
                >推荐下一步
                </el-button>
                <el-button
                  icon="el-icon-s-cooperation"
                  size="mini"
                  type="text"
                  @click="recommandResource(scope.row)"
                >推荐资源
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

    <el-dialog :visible.sync="selectWindowShow" append-to-body :title="title" width="800px">
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
<!--        <el-table-column  key="elementRequirements" :show-overflow-tooltip="true" align="center"-->
<!--                         label="相关要求" prop="elementRequirements"-->
<!--        >-->
<!--          <template slot-scope="scope">-->
<!--            <el-tag v-for="(value, key) in scope.row.elementRequirements" :key="key" :index="key+''" type="success">-->
<!--              {{ key + ':' + scope.row.elementRequirements[key+''] }}-->
<!--            </el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column  key="elementOtherProperties" :show-overflow-tooltip="true" align="center"-->
<!--                         label="其他属性"-->
<!--                         prop="elementOtherProperties"-->
<!--        >-->
<!--          <template slot-scope="scope">-->
<!--            <el-tag v-for="(value, key) in scope.row.elementOtherProperties" :key="key" :index="key+''" type="success">-->
<!--              {{ key + ':' + scope.row.elementOtherProperties[key+''] }}-->
<!--            </el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->

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
      <el-form >
        <el-form-item label="推荐资源集">
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
        </el-form-item>
        <el-form-item label="添加资源">
          <el-row class="row-bg" justify="space-around" type="flex">
            <el-col :span="10">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="labelNormalizer"
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
        <el-form-item>
          <el-row>
            <el-col :span="10" :offset="14">
              <el-button type="primary" @click="cancel">取消</el-button>
              <el-button type="primary" @click="addResourceToElement">添加到元素</el-button>
            </el-col>
          </el-row>

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
      title:'',
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
        fileUrl:""
      },
      defaultProps:{
        label:"elementName",
        children:"subElements",
        hasChildren:"hasSubChildren"
      },
      prescriptionElements:[],
      processType:undefined,
      processTree:[],
      processData:[],
      BOM:[],
      modifyProcessShow:false,
      selectProcess:{},
      selectResourceShow:false,
      resources:[
        {
          resourceId:0,
          resourceName:"软吊具（>1t）"
        },
        {
          resourceId:1,
          resourceName:"立式喷砂工装"
        },
        {
          resourceId:2,
          resourceName:"钢板尺"
        }
      ],
      newResource:{},
      resourceLabel:'',
      recommandProcess:[],
      resourceLabelOptions:[],
      resourceOptions:[],

    }
  },
  created() {
    this.getElementList();
  },
  methods:{
    getElementList(){
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
    changeElementLabel(node){
      this.elementLabel = node.label;
      this.disabledElement = false;
      this.elementOptions = [];
      elementManagement.getProductById(25677).then(result =>{
        if(result.code === 200){
          this.elementOptions.push(result.data)
        }
      })
    },
    changePrescriptionLabel(node){

    },
    selectResourceLabel(node){
      this.resourceOptions = [
        {
          resourceId:3,
          resourceName:"软吊具（>2t）"
        },
        {
          resourceId:3,
          resourceName:"软吊具（>3t）"
        },
        {
          resourceId:3,
          resourceName:"软吊具（>5t）"
        },
        {
          resourceId:3,
          resourceName:"软吊具（>10t）"
        }
      ]
    },
    addResource(){
      this.resources.push(this.newResource)
    },
    cancel(){

    },
    addResourceToElement(){
      this.processData[0].subElements[2].usedResources = this.resources;
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
        id: node.leafName,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleClickStructure(){
      this.selectElement = {};
      this.modelFile.fileUrl = "http://localhost:8080/profile/upload/2023/05/02/assem.html"
      elementManagement.getProductById(25677).then(result =>{
        if(result.code ===200){
          this.selectElement = result.data
          this.BOM.push(result.data)
        }
      })
      // fileManagement.getFilesByStructure(this.selectElementId).then(result =>{
      //   if(result.code === 200){
      //     this.modelFile = result.data
      //   }
      // })
    },
    handleClickPrescription(){
      this.selectPrescription = {}
      prescriptionManagement.getElementsByPrescriptionId(this.selectPrescriptionId).then(result =>{
        if(result.code === 200){
          this.selectPrescription = result.data
        }
      })
    },
    handleCloseResource(item){

    },
    processRecommand(){
      this.selectWindowShow = true
      this.title = '工艺方案推荐'
      this.recommandProcess = [
        {
          elementId:25369,
          elementNumber:'',
          elementName:'壳体准备工艺',
          elementDescription:'',
          elementRemark:["壳体内表面绝热层喷砂、打磨、清理"],
          elementRequirements:{},
          elementOtherProperties:{},
        },{
          elementId:25385,
          elementNumber:'',
          elementName:'金属壳体准备工艺',
          elementDescription:'',
          elementRemark:["壳体除油、粗化处理"],
          elementRequirements:{},
          elementOtherProperties:{},

        },{
          elementId:31246,
          elementNumber:'',
          elementName:'典型壳体准备工艺',
          elementDescription:'金属壳体准备典型工艺方案',
          elementRemark:[""],
          elementRequirements:{},
          elementOtherProperties:{},

        }
      ]
      // processManagement.getRecommandProcessByStructure(this.selectElementId).then(result =>{
      //   if(result.code === 200){
      //     this.recommandProcess.push(result.data)
      //   }
      // })
      // processManagement.getRecommandProcessByPrescription(this.selectPrescriptionId).then(result =>{
      //   if(result.code === 200){
      //     this.recommandProcess.push(result.data)
      //   }
      // })
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
      this.selectWindowShow = true
      this.title = "下步元素推荐"
      this.recommandProcess = []
      this.recommandProcess = [
        {
          elementId:25631,
          elementNumber:'40',
          elementName:'壳体清理',
          elementDescription:'壳内无沙粒等多余物残留',
          elementRemark:[],
          elementRequirements:{},
          elementOtherProperties:{}
        },{
          elementId:26589,
          elementNumber:'20',
          elementName:'壳体除油',
          elementDescription:'使用使用化学溶剂进行清洗',
          elementRemark:[],
          elementRequirements:{},
          elementOtherProperties:{}
        },{
          elementId:30785,
          elementNumber:'20',
          elementName:'金属壳体除油',
          elementDescription:'使用高温炭化除油',
          elementRemark:[],
          elementRequirements:{},
          elementOtherProperties:{}
        },{
          elementId:30926,
          elementNumber:'30',
          elementName:'壳体物理除油',
          elementDescription:'超声波气泡除油',
          elementRemark:[],
          elementRequirements:{},
          elementOtherProperties:{}
        }
      ]
    },
    createSubProcess(row){

    },
    recommandResource(row){
      this.selectResourceShow = true

    },
    handleSelect(data){
      if(this.processData.length === 0){
        this.processData = [{
          elementId:25369,
          elementNumber:'',
          elementName:'壳体准备工艺',
          elementDescription:'',
          elementRemark:["壳体内表面绝热层喷砂、打磨、清理"],
          elementRequirements:{},
          elementOtherProperties:{},
          usedResources:[],
          subElements:[
            {
              elementId:25412,
              elementNumber:'10',
              elementName:'壳体预处理工序',
              elementDescription:'壳体内表面绝热层喷砂、打磨、清理',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
              usedResources:[],
              subElements:[
                {
                  elementId:25501,
                  elementNumber:'1',
                  elementName:'壳体接收',
                  elementDescription:'',
                  elementRemark:[],
                  elementRequirements:{
                    技术要求:'见子工步',
                    检验要求:'三检'
                  },
                  elementOtherProperties:{},
                  usedResources:[],
                  subElements:[
                    {
                      elementId:25520,
                      elementNumber:'1.1',
                      elementName:'壳体接收1',
                      elementDescription:'壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
                      elementRemark:[],
                      elementRequirements:{},
                      elementOtherProperties:{},
                      usedResources:[],
                    },
                    {
                      elementId:25521,
                      elementNumber:'1.2',
                      elementName:'壳体接收2',
                      elementDescription:'填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
                        检验要求:'三检'
                      },
                      elementOtherProperties:{},
                      usedResources:[],
                    },
                    {
                      elementId:25522,
                      elementNumber:'1.3',
                      elementName:'壳体接收3',
                      elementDescription:'壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
                        检验要求:'三检'
                      },
                      elementOtherProperties:{},
                      usedResources:[],
                    }
                  ]
                },{
                  elementId:25524,
                  elementNumber:'2',
                  elementName:'内表面处理',
                  elementDescription:'内表面喷砂处理与内表面打磨',
                  elementRemark:[],
                  elementRequirements:{},
                  elementOtherProperties:{},
                  usedResources:[],
                  subElements:[
                    {
                      elementId:25525,
                      elementNumber:'2.1',
                      elementName:'内表面喷砂处理',
                      elementDescription:'内表面喷砂处理',
                      elementRemark:[],
                      elementRequirements:{},
                      elementOtherProperties:{},
                      usedResources:[],
                      subElements:[
                        {
                          elementId:25527,
                          elementNumber:'2.1.1',
                          elementName:'内表面喷砂处理1',
                          elementDescription:'使用胶带对金属裙上的螺孔进行粘贴保护',
                          elementRemark:[],
                          elementRequirements:{
                            技术要求:'内有螺纹的孔均需保护，光孔不需要保护。'
                          },
                          elementOtherProperties:{},
                          usedResources:[],
                        },
                        {
                          elementId:25528,
                          elementNumber:'2.1.2',
                          elementName:'内表面喷砂处理2',
                          elementDescription:'使用胶布对人工脱夹层进行简易封口。',
                          elementRemark:[],
                          elementRequirements:{
                            技术要求:'防止喷砂过程中多余物进入人脱夹层中。'
                          },
                          elementOtherProperties:{},
                          usedResources:[],
                        },
                        {
                          elementId:25529,
                          elementNumber:'2.1.3',
                          elementName:'内表面喷砂处理3',
                          elementDescription:'内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
                          elementRemark:[],
                          elementRequirements:{},
                          elementOtherProperties:{},
                          usedResources:[],
                        }
                      ]
                    },
                    {
                      elementId:25526,
                      elementNumber:'2.2',
                      elementName:'内表面打磨',
                      elementDescription:'打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
                      },
                      elementOtherProperties:{},
                      usedResources:[],
                    }
                  ]
                }
              ]
            },
            {
              elementId:25411,
              elementNumber:'20',
              elementName:'衬层成型工序',
              elementDescription:'待编辑',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
              usedResources:[],
            },
            {
              elementId:25432,
              elementNumber:'30',
              elementName:'壳体预处理工序',
              elementDescription:'待编辑',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
              usedResources:[],
            }

          ]
        }]
      } else {
        this.processData = [{
          elementId:25369,
          elementNumber:'',
          elementName:'壳体准备工艺',
          elementDescription:'',
          elementRemark:["壳体内表面绝热层喷砂、打磨、清理"],
          elementRequirements:{},
          elementOtherProperties:{},
          subElements:[
            {
              elementId:25412,
              elementNumber:'10',
              elementName:'壳体预处理工序',
              elementDescription:'壳体内表面绝热层喷砂、打磨、清理',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
              subElements:[
                {
                  elementId:25501,
                  elementNumber:'1',
                  elementName:'壳体接收',
                  elementDescription:'',
                  elementRemark:[],
                  elementRequirements:{
                    技术要求:'见子工步',
                    检验要求:'三检'
                  },
                  elementOtherProperties:{},
                  subElements:[
                    {
                      elementId:25520,
                      elementNumber:'1.1',
                      elementName:'壳体接收1',
                      elementDescription:'壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
                      elementRemark:[],
                      elementRequirements:{},
                      elementOtherProperties:{},
                    },
                    {
                      elementId:25521,
                      elementNumber:'1.2',
                      elementName:'壳体接收2',
                      elementDescription:'填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
                        检验要求:'三检'
                      },
                      elementOtherProperties:{},
                    },
                    {
                      elementId:25522,
                      elementNumber:'1.3',
                      elementName:'壳体接收3',
                      elementDescription:'壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
                        检验要求:'三检'
                      },
                      elementOtherProperties:{},
                    }
                  ]
                },{
                  elementId:25524,
                  elementNumber:'2',
                  elementName:'内表面处理',
                  elementDescription:'内表面喷砂处理与内表面打磨',
                  elementRemark:[],
                  elementRequirements:{},
                  elementOtherProperties:{},
                  subElements:[
                    {
                      elementId:25525,
                      elementNumber:'2.1',
                      elementName:'内表面喷砂处理',
                      elementDescription:'内表面喷砂处理',
                      elementRemark:[],
                      elementRequirements:{},
                      elementOtherProperties:{},
                      subElements:[
                        {
                          elementId:25527,
                          elementNumber:'2.1.1',
                          elementName:'内表面喷砂处理1',
                          elementDescription:'使用胶带对金属裙上的螺孔进行粘贴保护',
                          elementRemark:[],
                          elementRequirements:{
                            技术要求:'内有螺纹的孔均需保护，光孔不需要保护。'
                          },
                          elementOtherProperties:{},
                        },
                        {
                          elementId:25528,
                          elementNumber:'2.1.2',
                          elementName:'内表面喷砂处理2',
                          elementDescription:'使用胶布对人工脱夹层进行简易封口。',
                          elementRemark:[],
                          elementRequirements:{
                            技术要求:'防止喷砂过程中多余物进入人脱夹层中。'
                          },
                          elementOtherProperties:{},
                        },
                        {
                          elementId:25529,
                          elementNumber:'2.1.3',
                          elementName:'内表面喷砂处理3',
                          elementDescription:'内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
                          elementRemark:[],
                          elementRequirements:{},
                          elementOtherProperties:{},
                        }
                      ]
                    },
                    {
                      elementId:25526,
                      elementNumber:'2.2',
                      elementName:'内表面打磨',
                      elementDescription:'打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
                      elementRemark:[],
                      elementRequirements:{
                        技术要求:'如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
                      },
                      elementOtherProperties:{},
                    }
                  ]
                }
              ]
            },
            {
              elementId:25411,
              elementNumber:'20',
              elementName:'衬层成型工序',
              elementDescription:'待编辑',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
            },
            {
              elementId:25432,
              elementNumber:'30',
              elementName:'壳体预处理工序',
              elementDescription:'待编辑',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{},
            },
            {
              elementId:25631,
              elementNumber:'40',
              elementName:'壳体清理',
              elementDescription:'壳内无沙粒等多余物残留',
              elementRemark:[],
              elementRequirements:{},
              elementOtherProperties:{}
            }
          ]
        }]
      }
    },
  },
}
</script>

<style scoped>
.el-button{
  left: 50%;
}

</style>
