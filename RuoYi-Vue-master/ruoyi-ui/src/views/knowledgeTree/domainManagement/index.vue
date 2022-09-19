<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="知识名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入知识名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="概念描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入概念描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父级概念" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父级概念"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="节点数量" prop="nodecount">
        <el-input
          v-model="queryParams.nodecount"
          placeholder="请输入节点数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关系数量" prop="shipcount">
        <el-input
          v-model="queryParams.shipcount"
          placeholder="请输入关系数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createuser">
        <el-input
          v-model="queryParams.createuser"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createtime">
        <el-date-picker clearable
          v-model="queryParams.createtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="modifyuser">
        <el-input
          v-model="queryParams.modifyuser"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="modifytime">
        <el-date-picker clearable
          v-model="queryParams.modifytime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="推荐" prop="commend">
        <el-input
          v-model="queryParams.commend"
          placeholder="请输入推荐"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
	    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['knowledgeTree:domainManagement:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="domainManagementList"
      row-key="id"
      height="1000"
      indent="4"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="知识名称" prop="name" width="200"/>
      <el-table-column label="知识来源" align="center" prop="type" width="80"/>
      <el-table-column label="概念描述" align="center" prop="description" width="400"/>
      <el-table-column label="父级概念" align="center" prop="parentId" width="80"/>
      <el-table-column label="节点数量" align="center" prop="nodecount" width="50"/>
      <el-table-column label="关系数量" align="center" prop="shipcount" width="50"/>
      <el-table-column label="状态" align="center" prop="status" width="35"/>
      <el-table-column label="创建者" align="center" prop="createuser" width="75"/>
      <el-table-column label="创建时间" align="center" prop="createtime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="modifyuser" width="75"/>
      <el-table-column label="更新时间" align="center" prop="modifytime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifytime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="推荐" align="center" prop="commend" width="35"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['knowledgeTree:domainManagement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['knowledgeTree:domainManagement:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['knowledgeTree:domainManagement:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改知识树管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="知识名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入知识名称" />
        </el-form-item>
        <el-form-item label="概念描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入概念描述" />
        </el-form-item>
        <el-form-item label="父级概念" prop="parentId">
          <treeselect v-model="form.parentId" :options="domainManagementOptions" :normalizer="normalizer" placeholder="请选择父级概念" />
        </el-form-item>
        <el-form-item label="节点数量" prop="nodecount">
          <el-input v-model="form.nodecount" placeholder="请输入节点数量" />
        </el-form-item>
        <el-form-item label="关系数量" prop="shipcount">
          <el-input v-model="form.shipcount" placeholder="请输入关系数量" />
        </el-form-item>
        <el-form-item label="创建者" prop="createuser">
          <el-input v-model="form.createuser" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="更新者" prop="modifyuser">
          <el-input v-model="form.modifyuser" placeholder="请输入更新者" />
        </el-form-item>
        <el-form-item label="更新时间" prop="modifytime">
          <el-date-picker clearable
            v-model="form.modifytime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="推荐" prop="commend">
          <el-input v-model="form.commend" placeholder="请输入推荐" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDomainManagement, getDomainManagement, delDomainManagement, addDomainManagement, updateDomainManagement } from "@/api/knowledgeTree/domainManagement";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "DomainManagement",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 知识树管理表格数据
      domainManagementList: [],
      // 知识树管理树选项
      domainManagementOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        name: null,
        type: null,
        description: null,
        parentId: null,
        nodecount: null,
        shipcount: null,
        status: null,
        createuser: null,
        createtime: null,
        modifyuser: null,
        modifytime: null,
        commend: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "知识名称不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "父级概念不能为空", trigger: "blur" }
        ],
        nodecount: [
          { required: true, message: "节点数不能为空", trigger: "blur" }
        ],
        shipcount: [
          { required: true, message: "关系数不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询知识树管理列表 */
    getList() {
      this.loading = true;
      listDomainManagement(this.queryParams).then(response => {
        this.domainManagementList = this.handleTree(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
	/** 查询知识树管理下拉树结构 */
    getTreeselect() {
      listDomainManagement().then(response => {
        this.domainManagementOptions = [];
        const data = { id: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "parentId");
        this.domainManagementOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        type: null,
        description: null,
        parentId: null,
        nodecount: null,
        shipcount: null,
        status: 0,

        createuser: null,
        createtime: null,
        modifyuser: null,
        modifytime: null,
        commend: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parentId = row.id;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加知识树管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.id;
      }
      getDomainManagement(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改知识树管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDomainManagement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDomainManagement(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除知识树管理编号为"' + row.id + '"的数据项？').then(function() {
        return delDomainManagement(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
