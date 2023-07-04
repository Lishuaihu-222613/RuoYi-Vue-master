<template>
  <el-dialog :visible.sync="dialogFormVisible" title="理化指标与检验方法" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen" @opened="handleOpened"
  >
    <el-row>
      <el-button style="display: inline-block;float: right;" @click="addProject">添加指标</el-button>
    </el-row>
    <el-table id="inspectProjects" :data="newInspectProjects" border>
      <el-table-column label="指标名" prop="projectName"></el-table-column>
      <el-table-column v-for="(project, key) in projects" id="table" :key="key" :label="project">
        <template v-slot="scope">
          {{ scope.row.classificationAndIndicators[project] }}
        </template>
      </el-table-column>
<!--      <el-table-column label="检验方法">-->
<!--        <template v-slot="scope">-->
<!--          <el-button @click="getInspectMethodById(scope.row.projectId)">查看</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column
        fixed="right"
        label="操作"
        width="100"
      >
        <template v-slot="scope">
          <el-button size="small" type="text" @click="deleteProject(scope.row)">删除</el-button>
          <el-button size="small" type="text" @click="editProject(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="modifyVisible"
      append-to-body
      title="理化指标"
      width="30%"
      @close="refreshProject"
    >
      <el-form :model="project">
        <el-row>
          <el-col>
            <el-form-item :label-width="formLabelWidth" label="指标Id">
              <el-input v-model="project.projectId" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item :label-width="formLabelWidth" label="指标名称">
              <el-input v-model="project.projectName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item :label-width="formLabelWidth" label="表头">
          <el-button @click="projects.push()" :disabled="disabled">添加</el-button>
        </el-form-item>
        <el-form-item v-for="(item, index) in projects" :key="index" :label="item">
          <template v-slot="scope">
            <el-input v-model="project.classificationAndIndicators[item]"></el-input>
          </template>
        </el-form-item>
<!--        <el-form-item :label-width="formLabelWidth" label="检验方法">-->
<!--          <el-button @click="getInspectMethodById(project.projectId)" >查看</el-button>-->
<!--          <el-button @click="editInspectMethod(project.projectId)" >修改</el-button>-->
<!--        </el-form-item>-->
        <el-form-item>
          <el-button @click="modifyVisible = false">取 消</el-button>
          <el-button type="primary" @click="onSubmit">确 定</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </el-dialog>
</template>

<script>

import * as materialManagement from '@/api/system/materialManagement'

export default {

  name: 'InspectProjectAndMethods',

  props: {
    selectId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    }
  },

  mounted() {

  },
  created() {


  },

  watch: {
    selectId: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.materialId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    }
  },

  data: () => ({
    formLabelWidth:'120px',
    newInspectProjects:[],
    materialId: 0,
    dialogFormVisible: false,
    modifyVisible: false,
    project_List: [],
    projects: [""],
    disabled:true,
    project: {
      projectId: 0,
      projectName: '',
      classificationAndIndicators: {}
    },
    inspectProjects: [
      {
        projectId: 0,
        projectName: '',
        classificationAndIndicators: {}
      }
    ]
  }),
  methods: {
    initProjects() {
      this.projects = Object.keys(this.inspectProjects[0].classificationAndIndicators)
      console.log(this.projects)
    },
    handleOpen() {
      materialManagement.getInspectProjectsByMaterialId(this.materialId).then(result => {
        if (result.code === 200) {
          this.inspectProjects = result.data
          this.initProjects()
          this.newInspectProjects = this.inspectProjects
          console.log(this.inspectProjects)
        }
      })

    },
    handleOpened(){

    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    deleteProject(item){
      materialManagement.deleteInspectProjectById(item.projectId).then(result => {
        if (result.code === 200) {
          this.$modal.msgSuccess("删除成功！")
        }
      })
    },
    editProject(item){
      if(this.projects.length === 0){
        this.$modal.msg("请注意表头的创建")
        this.disabled = false
      }
      this.modifyVisible = true
      this.project = item
    },
    refreshProject(){
      console.log(this.inspectProjects)
    },
    addProject(){
      let item ;
      materialManagement.createInspectProject(this.materialId).then(result =>{
        if (result.code === 200) {
          item = result.data
        }
      })
      for(let attr in this.projects){
        Object.defineProperty(item.classificationAndIndicators, attr, String);
      }
      materialManagement.updateInspectProject(item).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("添加成功！")
        }
      })

    },
    // //跳转页面
    // getInspectMethodById(projectId){
    //   this.$router.push({ path: "/system/user", query: {projectId: projectId} });
    // },
    // //跳转页面
    // editInspectMethod(projectId){
    //   this.$router.push({ path: "/system/user", query: {projectId: projectId} });
    // },
    onSubmit(){
      materialManagement.updateInspectProject(this.project).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess("修改成功！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
