<template>
  <el-dialog :visible.sync="dialogFormVisible" title="材料基本信息编辑" @closed="handleClose" @open="handleOpen" width="70%">
    <el-form :model="material">
      <el-form-item :label-width="formLabelWidth" label="材料名称">
        <el-input v-model="material.materialName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="材料别称">
        <el-input v-model="material.nickName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="英文名称">
        <el-input v-model="material.englishName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="英文别称">
        <el-input v-model="material.englishNickName"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="相对分子质量">
        <el-input v-model="material.relativeMolecularMass"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" >
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>化学结构式</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) =>{ handleUpLoad(url, 'structuralFormula')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[material.structuralFormula]"
            :src="material.structuralFormula"
            class="structuralImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="材料描述">
        <el-input v-model="material.materialDescription" autosize type="textarea"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="CAS-登记号">
        <el-input v-model="material.casRegistryNumber"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="材料外观">
        <el-input v-model="material.materialAppearance"></el-input>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="材料用途">
        <el-button type="primary" @click="addUsage">添加用途</el-button>
        <el-row v-for = "(item,index) in material.materialUsage" :key="index">
          <el-col :span="20">
            <el-input v-model="material.materialUsage[index]"></el-input>
          </el-col>
          <el-col :span="4">
            <el-button
              circle icon="el-icon-delete" type="danger"
              @click="removeUsage(material.materialUsage[index])"
            ></el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="材料标签">
        <el-tag
          :key="tag"
          v-for="tag in material.materialLabels"
          closable
          :disable-transitions="false"
          @close="handleCloseTag(tag)">
          {{tag}}
        </el-tag>
        <el-input
          class="input-new-tag"
          v-if="inputVisible"
          v-model="inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 添加标签</el-button>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </div>
  </el-dialog>

</template>

<script>

import ImageUploader from '@/views/Material/MaterialKnowledge/components/ImageUpload'
import * as materialManagement from '@/api/system/materialManagement'

export default {
  name: 'modifyMaterial',
  components: {
    ImageUploader
  },
  props: {
    selectMaterial: {
      type: Object,
      default: {}
    },
    dialog: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    selectMaterial: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.material = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
  },
  data: () => ({
    dialogFormVisible: false,
    formLabelWidth: '120px',
    inputVisible: false,
    inputValue: '',
    material:{
      materialId:0,
      materialName:'',
      nickName:'',
      englishName:'',
      englishNickName:'',
      relativeMolecularMass:'',
      structuralFormula:'',
      casRegistryNumber:'',
      materialDescription:'',
      materialAppearance:'',
      materialUsage:[],
      materialLabels:[],
      physicalProperty:{},
      chemicalProperty:{},
      analysisSpectrogram:[],
      inspectProjects:[],
      storageRequirements:[],
      productMethods:[],
      dangers:[],
      protections:[],
      associatedFiles:[]
    }
  }),
  methods:{
    handleUpLoad(url, attr) {
      console.log(url)
      this.material.structuralFormula = url
    },
    handleOpen() {

    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    handleCloseTag(tag){
      this.material.materialLabels.splice(this.material.materialLabels.indexOf(tag), 1);
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    addUsage(){
      this.material.materialUsage.push("")
    },
    removeUsage(item){
      let index = this.material.materialUsage.indexOf(item)
      if (index !== -1) {
        this.material.materialUsage.splice(index, 1)
      }
    },
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.material.materialLabels.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },
    onSubmit(){
      materialManagement.createMaterial(this.material).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess('修改成功')
        }
      })

    }
  }
}
</script>

<style lang="scss" scoped>

.upLoader {
  display: inline-block;
  float: right;
}

</style>
