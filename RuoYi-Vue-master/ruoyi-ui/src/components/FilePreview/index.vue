<template>

  <el-dialog
    :before-close="handleClose"
    :destroy-on-close="true"
    :visible.sync="dialogVisible"
    title="文件预览"
    width="80%"
    @closed="handleClose"
    @open="handleOpen"
  >
    <div v-show="loading" class="well loading">正在加载中，请耐心等待...</div>
    <div v-show="!loading" ref="output" class="well"></div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </el-dialog>

</template>

<script>
import { getExtend, readBuffer, render } from './util'
import { isExternal } from '@/utils/validate'
import axios from 'axios'

export default {
  name: 'FilePreview',
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: true
    },
    url: {
      type: String,
      default: '',
      required: true
    }
  },
  watch: {
    visible: {
      handler(newVal, oldVal) {
        if (newVal !== null) {
          this.dialogVisible = newVal
        }
      }
    }
  },
  data() {
    return {
      // 加载状态跟踪
      loading: false,
      // 上个渲染实例
      last: null,
      // 隐藏头部，当基于消息机制渲染，将隐藏
      hidden: false,
      dialogVisible: false,
    }
  },
  computed: {
    realUrl() {
      let real_url = this.url.split(',')[0]
      if (isExternal(real_url)) {
        return real_url
      }
      return process.env.VUE_APP_BASE_API + real_url
    },
  },
  created() {},
  methods: {
    //解析url,下载文件
    loadFromUrl() {
      let fileUrl = this.realUrl;
      let filename = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
      axios({
        fileUrl,
        method:'get',
        responseType:'blob',
      }).then(response =>{
        if(!response) {
          this.$modal.alertError("文件不存在，预览失败！");
        }
        console.log(response)
        let file = new File([response], filename,{});
        this.hidden = true;
        this.handleChange({ target: { files: [file] } });
      })
    },
    //文件更改方法
    async handleChange(e) {
      this.loading = true
      try {
        const [file] = e.target.files
        const arrayBuffer = await readBuffer(file)
        this.loading = false
        this.last = await this.displayResult(arrayBuffer, file)
      } catch (e) {
        console.error(e)
      } finally {
        this.loading = false
      }
    },
    //文件显示方法
    displayResult(buffer, file) {
      // 取得文件名
      const { name } = file
      // 取得扩展名
      const extend = getExtend(name)
      // 输出目的地
      const { output } = this.$refs
      // 生成新的dom
      const node = document.createElement('div')
      // 添加孩子，防止vue实例替换dom元素
      if (this.last) {
        output.removeChild(this.last.$el)
        this.last.$destroy()
      }
      const child = output.appendChild(node)
      // 调用渲染方法进行渲染
      return new Promise((resolve, reject) =>
        render(buffer, extend, child).then(resolve).catch(reject)
      )
    },
    handleOpen() {
      this.loadFromUrl()
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    }
  }
}
</script>

<style scoped>

</style>
