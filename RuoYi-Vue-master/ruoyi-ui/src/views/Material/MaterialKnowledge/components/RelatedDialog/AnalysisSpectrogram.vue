<template>
  <el-dialog :visible.sync="dialogFormVisible" title="理化分析谱图" @closed="handleClose" @open="handleOpen" top="50vh" width="75%" >
    <el-carousel :interval="1000000" height="700px" >
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>红外光谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) =>{ handleUpLoad(url, 'infraredSpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.infraredSpectrumUrl]"
            :src="analysisSpectrogram.infraredSpectrumUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>热分析谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) =>{ handleUpLoad(url, 'thermalSpectrogramUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.thermalSpectrogramUrl]"
            :src="analysisSpectrogram.thermalSpectrogramUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>核磁共振谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) => {handleUpLoad(url, 'nmrSpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.nmrSpectrumUrl]"
            :src="analysisSpectrogram.nmrSpectrumUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>X射线衍射谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) => {handleUpLoad(url, 'XraySpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.XraySpectrumUrl]"
            :src="analysisSpectrogram.XraySpectrumUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>拉曼光谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) => {handleUpLoad(url, 'ramanSpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.ramanSpectrumUrl]"
            :src="analysisSpectrogram.ramanSpectrumUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card">
          <div slot="header" class="clearfix" shadow="hover">
            <el-row>
              <el-tag>紫外光谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) => {handleUpLoad(url, 'ultravioletSpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <div>
            <el-image
              fit="cover"
              :preview-src-list="[analysisSpectrogram.ultravioletSpectrumUrl]"
              :src="analysisSpectrogram.ultravioletSpectrumUrl"
              class="SpectrogramImage"
            >
              <div slot="placeholder" class="image-slot">
                加载中<span class="dot">...</span>
              </div>
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </div>
        </el-card>
      </el-carousel-item>
      <el-carousel-item>
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <el-row>
              <el-tag>质谱图</el-tag>
              <ImageUploader ref="ImageUploader"
                             class="upLoader"
                             @success="(url) => {handleUpLoad(url, 'massSpectrumUrl')}"
              ></ImageUploader>
            </el-row>
          </div>
          <el-image
            fit="cover"
            :preview-src-list="[analysisSpectrogram.massSpectrumUrl]"
            :src="analysisSpectrogram.massSpectrumUrl"
            class="SpectrogramImage"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
      </el-carousel-item>
    </el-carousel>
  </el-dialog>

</template>

<script>

import ImageUploader from '@/views/Material/MaterialKnowledge/components/ImageUpload'
import * as materialManagement from '@/api/system/materialManagement'

export default {

  name: 'AnalysisSpectrogram',

  components: {
    ImageUploader
  },

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
    formLabelWidth: '120px',
    analysisSpectrogram: {
      spectrogramId: '',
      spectrogramName: '',
      infraredSpectrumUrl: '',
      thermalSpectrogramUrl: '',
      nmrSpectrumUrl: '',
      XraySpectrumUrl: '',
      ramanSpectrumUrl: '',
      ultravioletSpectrumUrl: '',
      massSpectrumUrl: ''
    }
  }),
  methods: {
    handleOpen() {
      materialManagement.getAnalysisSpectrogramByMaterialId(this.materialId).then(result => {
        if (result.code == 200) {
          this.analysisSpectrogram = result.data
        }
      })
    },
    handleClose() {
      this.dialogFormVisible = false
      this.$emit('closeDialog', null)
      this.$emit('restore', null)
    },
    handleUpLoad(url, attr) {
      console.log(url)
      this.analysisSpectrogram[attr] = url
      console.log(this.analysisSpectrogram)
      materialManagement.updateAnalysisSpectrogram(this.analysisSpectrogram).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess('上传成功')
        }
      })
    },
    onSubmit() {
      if (this.dkItem.dkName !== '') {
        console.log(this.dkItem)
        decisionKnowledge.updateDK(this.dkItem).then(result => {
          if (result.code == 200) {
            alert('创建成功！')
          } else {
            alert('创建失败！')
          }
        })
      }
      this.dialogFormVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>

el-card{
  color: #121313;
}

.dialog{

}
.upLoader {
  display: inline-block;
  float: right;
}
.box-card{
  height:650px;
}
.clearfix{

}
.SpectrogramImage{
  height:100%;
}
</style>
