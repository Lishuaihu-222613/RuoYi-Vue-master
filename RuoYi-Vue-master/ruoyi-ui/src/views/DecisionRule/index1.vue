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
            :data="ruleTree"
            node-key="leafId"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            :props="defaultProps"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px"
                 size="small"
        >
          <el-form-item label="规则名称" prop="ruleName">
            <el-input
              v-model="queryParams.originRule.ruleName"
              clearable
              placeholder="请输入规则名称"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="规则描述" prop="ruleDescription">
            <el-input
              v-model="queryParams.originRule.ruleDescription"
              clearable
              placeholder="请输入规则描述"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="规则来源" prop="ruleSource">
            <el-input
              v-model="queryParams.originRule.ruleSource"
              clearable
              placeholder="请输入规则来源"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="经理人" prop="ruleSource">
            <el-input
              v-model="queryParams.originRule.manager"
              clearable
              placeholder="请输入经理人"
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

        <el-table v-loading="loading" :data="rules" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column v-if="columns[0].visible" key="ruleId" align="center" label="规则编号" prop="ruleId"/>
          <el-table-column v-if="columns[1].visible" key="ruleName" :show-overflow-tooltip="true" align="center"
                           label="规则名称"
                           prop="ruleName"
          />
          <el-table-column v-if="columns[2].visible" key="ruleDescription" :show-overflow-tooltip="true"
                           align="center"
                           label="规则内容" prop="ruleDescription"
          />
          <el-table-column v-if="columns[3].visible" key="ruleSource" :show-overflow-tooltip="true"
                           align="center"
                           label="规则来源" prop="ruleSource"
          />
          <el-table-column v-if="columns[4].visible" key="modifyTime" :show-overflow-tooltip="true"
                           align="center"
                           label="修改时间" prop="modifyTime"
          >
            <span>{{ parseTime(scope.row.modifyTime) }}</span>
          </el-table-column>
          <el-table-column v-if="columns[5].visible" key="manager" :show-overflow-tooltip="true"
                           align="center"
                           label="经理人" prop="manager"
          />
          <el-table-column v-if="columns[6].visible" key="ruleConditions" :show-overflow-tooltip="true"
                           align="center"
                           label="规则条件" prop="ruleConditions"
          >
            <template slot-scope="scope">
              <el-row v-for="(item,index) in scope.row.ruleConditions" :key="index" :index="index+''">
                <el-tag type="success">
                  {{ item.conditionCluster + ':' + item.conditionContent }}
                </el-tag>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column v-if="columns[7].visible" key="ruleResults" :show-overflow-tooltip="true"
                           align="center"
                           label="规则结果" prop="ruleResults"
          >
            <template slot-scope="scope">
              <el-row v-for="(item,index) in scope.row.ruleResults" :key="index" :index="index+''">
                <el-tag  type="success">
                  {{ item.resultCluster + ':' + item.resultContent }}
                </el-tag>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="160"
          >
            <template v-if="scope.row.ruleId !== 1" slot-scope="scope">
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

    <el-dialog :title="title" :visible.sync="modifyRuleShow" append-to-body width="600px">
      <el-form ref="ruleForm" :model="selectRule" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="规则名称" prop="ruleName">
              <el-input v-model="selectRule.ruleName" maxlength="30" placeholder="请输入规则名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规则类别" prop="ruleTypes">
              <treeselect v-model="selectRule.dynamicLabels" :multiple="true" :options="ruleTree"
                          :normalizer="normalizer"
                          :show-count="true" placeholder="请选择规则类别"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="规则来源" prop="ruleSource">
              <el-input v-model="selectRule.ruleSource" placeholder="请输入规则来源"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规则描述" prop="ruleDescription">
              <el-input v-model="selectRule.ruleDescription" placeholder="请输入规则描述" type="textarea"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="经理人" prop="manager">
              <el-input v-model="selectRule.manager" placeholder="请输入经理人"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="修改日期" prop="modifyTime">
              <el-date-picker
                v-model="selectRule.modifyTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="规则条件">
            <el-row>
              <el-col :offset="18" :span="6">
                <el-button type="primary" @click="addCondition" >添加条件</el-button>
              </el-col>
            </el-row>
            <el-row v-for="(item,index) in selectRule.ruleConditions" :key="index">
              <el-col :span="2">
                <el-tag type="primary">
                  条件{{ index }}
                </el-tag>
              </el-col>
              <el-col :span="6">
                <el-input v-model="item.conditionCluster" placeholder="条件类别"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col :span="6">
                <el-input v-model="item.conditionContent" placeholder="条件内容" ></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click.prevent="removeCondition(item)">删除</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="规则结果">
            <el-row>
              <el-col :offset="18" :span="6">
                <el-button type="primary" @click="addResult" >添加结果</el-button>
              </el-col>
            </el-row>
            <el-row v-for="(item,index) in selectRule.ruleResults" :key="index">
              <el-col :span="2">
                <el-tag type="primary">
                  结果{{ index }}
                </el-tag>
              </el-col>
              <el-col :span="6">
                <el-input v-model="item.resultCluster" placeholder="结果类别"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col :span="6">
                <el-input v-model="item.resultContent" placeholder="结果内容" ></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click.prevent="removeResult(item)">删除</el-button>
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

    <!-- 规则导入对话框 -->
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

import * as treeManagement from '@/api/system/treeManagement'
import Treeselect from '@riophae/vue-treeselect'
import { getToken } from "@/utils/auth";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import * as ruleManagement from '@/api/system/decisionRule'

export default {
  name: 'index',
  components: { Treeselect },
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
        url: process.env.VUE_APP_BASE_API + '/rule/importData'
      },
      queryParams: {
        pageNum: 1,
        pageSize: 9,
        sortableField: 'ruleId',
        sortType: 'ascending',
        dynamicLabel: "",
        originRule:{
          ruleName:'',
          ruleDescription:"",
          ruleSource:"",
          manager:"",
        }
      },
      // 列信息
      columns: [
        { key: 0, label: `规则编号`, visible: true },
        { key: 1, label: `规则名称`, visible: true },
        { key: 2, label: `规则内容`, visible: true },
        { key: 3, label: `规则来源`, visible: true },
        { key: 4, label: `修改时间`, visible: true },
        { key: 5, label: `经理人`, visible: true },
        { key: 5, label: `规则条件`, visible: true },
        { key: 5, label: `规则结果`, visible: true },
      ],
      title: '',
      modifyRuleShow: false,
      showSearch: true,
      selectId: 0,
      tableHeight: 0,
      selectLabel: '',
      filterText: '',
      rules: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
      selectRule: {
        ruleId:undefined,
        ruleName:'',
        dynamicLabels:[],
        ruleSource:'',
        ruleDescription:'' ,
        manager:'',
        modifyTime:'',
        ruleConditions:[""],
        ruleResults:[""]
      },
      modifyState: false,
      ruleTree: [],
      dialog: false,
      total: 0,
      currentPage: 1,
    };
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  created() {
    this.getList();
    this.getTreeselect();
  },

  methods: {

    /** 查询规则列表 */
    getList() {
      this.loading = true
      if(this.queryParams.dynamicLabel === ''){
        ruleManagement.getAllDecisionRules(this.queryParams).then(result => {
            if (result.code === 200) {
              console.log(result.data)
              this.rules = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if (this.queryParams.dynamicLabel !== ''){
        ruleManagement.getAllDecisionRulesByLabel(this.queryParams).then(result => {
            if (result.code === 200) {
              this.rules = result.data.content
              this.total = result.data.totalElements
              this.loading = false
            }
          }
        )
      } else if(this.queryParams.originRule.ruleName !== '' || this.queryParams.originRule.ruleDescription !== ''){
        ruleManagement.getDecisionRulesByParams(this.queryParams).then(result => {
            if (result.code === 200) {
              this.rules = result.data.content
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
        this.ruleTree.push(response.data)
      })
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
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
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.sortableField = "n.label"
      this.queryParams.dynamicLabel = data.leafName
      this.loading = true
      ruleManagement.getAllDecisionRulesByLabel(this.queryParams).then(result => {
          if (result.code === 200) {
            this.rules = result.data.content
            this.total = result.data.totalElements
            this.loading = false
          }
        }
      )
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.loading = true
      ruleManagement.getDecisionRulesByParams(this.queryParams).then(result => {
          if (result.code === 200) {
            this.rules = result.data.content
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.ruleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    addCondition() {
      this.selectRule.ruleConditions.push(
        {
          conditionId: undefined,
          conditionContent: '',
          conditionCluster: ''
        }
      )
    },
    removeCondition(item) {
      let index = this.selectRule.ruleConditions.indexOf(item)
      if (index !== -1) {
        this.selectRule.ruleConditions.splice(index, 1)
      }
    },
    addResult() {
      this.selectRule.ruleResults.push(
        {
          resultId: undefined,
          resultContent: '',
          resultCluster: '',
        }
      )
    },
    removeResult(item) {
      let index = this.selectRule.ruleResults.indexOf(item)
      if (index !== -1) {
        this.selectRule.ruleResults.splice(index, 1)
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.modifyRuleShow = true;
      this.title = "创建规则"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.modifyRuleShow = true;
      this.title = "修改规则";
      this.selectRule = row
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ruleIds = row.ruleId || this.ids
      this.$modal.confirm('是否确认删除原则编号为"' + ruleIds + '"的数据项？').then(function() {
        return ruleManagement.deleteDecisionRule(ruleIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('rule/export', {
        ...this.queryParams
      }, `rule_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '原则导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/rule/importTemplate', {}, `rule_template_${new Date().getTime()}.xlsx`)
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
    submitForm:function() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          if (this.selectRule.ruleId !== undefined) {
            ruleManagement.updateDecisionRule(this.selectRule).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            ruleManagement.createDecisionRule(this.selectRule).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    cancel(){
      this.modifyRuleShow = false;
      this.reset();
    },
    reset(){
      this.selectRule = {
        ruleId:undefined,
        ruleName:'',
        dynamicLabels:[],
        ruleSource:'',
        ruleDescription:'' ,
        manager:'',
        modifyTime:'',
        ruleConditions:[""],
        ruleResults:[""]
      };
      this.resetForm("ruleForm");
    }
  }
}

</script>

<style scoped>

</style>
