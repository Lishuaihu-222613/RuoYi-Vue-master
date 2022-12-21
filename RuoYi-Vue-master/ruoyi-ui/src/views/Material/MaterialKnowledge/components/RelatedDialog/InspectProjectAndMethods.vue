<template>
    <el-table border :data="data_list" id="inspectProjects">
      <el-table-column id="table"  :label="project"  v-for="(project, key) in projects">
        <template v-slot="scope">
          {{data_list[scope.$index][key]}}
        </template>
      </el-table-column>
    </el-table>

</template>

<script>
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
    this.initProjects();
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
    materialId: 0,
    dialogFormVisible: false,
    projects:[],
    dataList:[],
    inspectProjects:[
      {
        projectId:0,
        projectName:'',
        classificationAndIndicators:[],
        associatedFiles:[],
        InspectionMethods:[],
      }
    ],
  }),
  methods:{
    initProjects(){
      this.projects = Object.keys(this.inspectProjects[0].classificationAndIndicators)
    },
  }
}
</script>

<style scoped>

</style>
