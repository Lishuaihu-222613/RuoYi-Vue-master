<template>
  <el-dialog :visible.sync="dialogFormVisible" title="工步信息" top="50vh" width="75%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-row>
      <el-button style="display: inline-block;float: right;" @click="addStep">添加工步</el-button>
    </el-row>
    <el-table id="Steps" :data="Steps" border>
      <el-table-column label="工步号" prop="stepNumber"></el-table-column>
      <el-table-column label="工步名" prop="stepName"></el-table-column>
      <el-table-column label="工步内容" prop="stepDescription"></el-table-column>
      <el-table-column label="技术要求" prop="stepRequirement"></el-table-column>
      <el-table-column label="检验要求" prop="inspectRequirement"></el-table-column>
      <el-table-column label="使用工具" prop="inspectRequirement">
        <template v-slot="scope">
          <el-button size="small" type="text" @click="getResource(scope.row.stepId)">查看工具</el-button>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100"
      >
        <template v-slot="scope">
          <el-button size="small" type="text" @click="deleteProject(scope.row)">移除工步</el-button>
          <el-button size="small" type="text" @click="editProject(scope.row)">编辑工步</el-button>
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
        <el-form-item :label-width="formLabelWidth" label="指标Id">
          <el-input v-model="project.projectId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="指标名称">
          <el-input v-model="project.projectName"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="表头">
          <el-button @click="project.classificationAndIndicators.push()" disabled="disabled">添加</el-button>
        </el-form-item>
        <el-form-item v-for="(item, key) in project.classificationAndIndicators" :key="key" :label="key">
          <template v-slot="scope">
            <el-input v-model="project.classificationAndIndicators[key]"></el-input>
          </template>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="检验方法">
          <el-button @click="getInspectMethodById(project.projectId)" >查看</el-button>
          <el-button @click="editInspectMethod(project.projectId)" >修改</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </el-dialog>
</template>

<script>
export default {
  name: 'StepTable'
}
</script>

<style scoped>

</style>
