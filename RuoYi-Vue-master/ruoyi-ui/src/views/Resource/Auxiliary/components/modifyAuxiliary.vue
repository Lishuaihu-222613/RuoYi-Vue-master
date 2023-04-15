<template>
  <el-dialog :visible.sync="dialogFormVisible" title="辅助资源编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-form :model="resource" :rules="rules" ref="form">
      <el-form-item :label-width="formLabelWidth" label="辅助资源名称">
        <el-input v-model="resource.resourceName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="辅助资源描述">
        <el-input v-model="resource.resourceDescription"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="辅助资源用途">
        <el-input v-model="resource.resourceUsage"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="辅助资源标签">
        <el-row class="row-bg" justify="space-around" type="flex">
          <treeselect v-model="resource.dynamicLabels"
                      :multiple="true"
                      :clearable="true"
                      :searchable="true"
                      :normalizer="normalizer"
                      :options="options"
                      placeholder="请选择标签"
          />
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="存在地址">
        <el-row class="row-bg" justify="space-around" type="flex">
          <el-select v-model="siteIds" multiple filterable placeholder="请选择地址">
            <el-option
              v-for="item in siteOptions"
              :key="item.siteId"
              :label="item.siteName"
              :value="item.siteId">
            </el-option>
          </el-select>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSubmit">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import * as treeManagement from '@/api/system/treeManagement'
import * as resourceManagement from '@/api/system/resourceManagement'


export default {
  name: 'modifyAuxiliary',
  components: { Treeselect },


  props: {
    selectResource: {
      type: Object,
      default: {}
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title:{
      type: String,
      default: ""
    }
  },
  watch: {
    selectResource: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.resource = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    title:{
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
      siteOptions:[],
      siteIds:[],
      windowTitle:"创建资源",
      resource: {
        resourceId: 0,
        resourceName: '',
        dynamicLabels: [],
        resourceDescription: '',
        resourceUsage: '',
        sites:[],
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      // 表单校验
      rules: {
        resourceName: [
          { required: true, message: "资源名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '资源名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        resourceDescription: [
          { required: true, message: "资源内容不能为空", trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    /** 查询标签列表 */
    getLabelList() {
      this.loading = true
      treeManagement.getTreeManagement(25500).then(response => {
        console.log(response.data)
        this.options.push(response.data)
      });
      siteManagement.getAllsiteList().then(response => {
        this.siteOptions.push(response.data)
      })
      this.siteIds = this.resource.sites.map( (item, index) => {
        return item.siteId
      });
    },
    /** 转换知识树管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.leafId,
        label: node.leafName,
        children: node.subLeafs
      }
    },
    handleOpen() {
      this.getLabelList();
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.resource = {
        resourceId: 0,
        resourceName: '',
        dynamicLabels: [],
        resourceDescription: '',
        resourceUsage: '',
        sites: []
      }
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    onSubmit(){
      if(this.windowTitle === "创建资源"){

        resourceManagement.createResource(this.resource).then(result =>{
          if(result.code === 200){
            this.resource = result.data;
            this.$modal.msgSuccess("创建资源成功！")
          }
        })
      } else {

        resourceManagement.updateResource(this.resource).then(result =>{
          if(result.code === 200){

            this.resource = result.data;
            this.$modal.msgSuccess("修改资源成功！")
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
