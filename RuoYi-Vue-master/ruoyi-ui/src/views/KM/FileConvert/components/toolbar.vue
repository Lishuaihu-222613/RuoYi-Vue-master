<template>
  <div class="toolbar">
    <v-toolbar height="50">
      <v-tooltip v-for="(item, index) in tools" :key="index" bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            @click="comment_event(item.event)"
            icon
            v-bind="attrs"
            v-on="on"
          >
            <v-icon style="color: #35495e">{{ item.icon }}</v-icon>
          </v-btn>
          <template style="color: #c0c4cc" v-if="index == 8">{{ size }}%</template>
          <template v-else-if="index == 10">
            <v-spacer></v-spacer>
            <el-select v-model="layout"   @change="changeLayout" size="mini" placeholder="请选择">
              <el-option
                v-for="item in layouts"
                :label="item.label"
                :value="item.value"
                :key="item.value">
              </el-option>
            </el-select>
            <v-spacer></v-spacer>
          </template>
          <template
            v-else-if="
              index == 2 ||
              index == 5 ||
              index == 7 ||
              index == 11 ||
              index == 13
            "
          >
            <v-divider vertical></v-divider>
          </template>
        </template>
        <span>{{ item.tip }}</span>
      </v-tooltip>
    </v-toolbar>
    <el-dialog title="上传txt文件" :visible.sync="dialogVisible" width="50%">
      数据格式：（<a href="https://github.com/qiaolufei/KG-Editor/issues/3" target="_blank">进阶版数据格式</a>）
      <pre style="color:#000">
        {
          "nodes":[
              {"id": "node1", "label": "luffy"},
              {"id": "node2", "label": "24岁"},
              {"id": "node3", "label": "62kg"}
              ...
          ],
          "edges":[
            {"source": "node1", "target": "node2", "label": "年龄"},
            {"source": "node1", "target": "node3", "label": "体重"}
            ...
          ]
        }
      </pre>
      <div style="text-align:center">
        <el-upload
          drag
          :limit="1"
          action="https://jsonplaceholder.typicode.com/posts/"
          ref="upload"
          accept=".txt"
          :close-on-click-modal="false"
          :file-list="fileList"
          :on-success="onSuccess"
          :on-remove="onRemove"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">
            上传txt文件，且只能上传 1 个文件
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitdata"
        >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>

</template>
<script>

import defaultNode from '@/views/KM/FileConvert/default/default_node'
import defaultEdge from '@/views/KM/FileConvert/default/default_edge'
import { isNullAndEmpty, objectJS } from '@/views/KM/FileConvert/actions/commen'
import * as G6 from '@antv/g6'

export default {
  props: {

    size: {
      type: Number,
      default: 100
    },

    selectedNode: {
      type: Object,
      default: () => {}
    },

    selectedEdge: {
      type: Object,
      default: () => {}
    },

    graph: {
      type: Object,
      default: () => {}
    }
  },
  watch: {

    selectedEdge: {
      handler (newVal, oldVal) {
        if (newVal !== '') {
          this.edge = newVal
        }
      }
    },

    selectedNode: {
      handler (newVal, oldVal) {
        if (newVal !== '') {
          this.node = newVal
          this.radius = newVal.size[0] / 2
        }
      }
    }
  },
  data: () => ({
    tools: [
      { icon: 'mdi-book-open-page-variant-outline', tip: '查找', event: 'search' },
      { icon: 'mdi-vector-polygon', tip: '扩展', event: 'restore' },
      { icon: 'mdi-content-copy', tip: '复制 Ctrl+C', event: 'copy' },
      { icon: 'mdi-content-paste', tip: '粘贴 Ctrl+V', event: 'paste' },
      { icon: 'mdi-trash-can-outline', tip: '删除 Ctrl+Backspace', event: 'delete' },
      { icon: 'mdi-vector-arrange-above', tip: '置于顶层', event: 'onTop' },
      { icon: 'mdi-vector-arrange-below', tip: '置于底层', event: 'onBottom' },
      { icon: 'mdi-magnify-plus-outline', tip: '放大', event: 'plus' },
      { icon: 'mdi-magnify-minus-outline', tip: '缩小', event: 'minus' },
      { icon: 'mdi-arrow-collapse-all', tip: '适应画布', event: 'adaptCanvas' },
      { icon: 'mdi-cloud-upload', tip: '导入文件', event: 'importFile' },
      { icon: 'mdi-file-image', tip: '导出图片', event: 'saveImage' },
      { icon: 'mdi-magnify', tip: '放大镜', event: 'saveJson' },
      { icon: 'mdi-cube-scan', tip: '滤镜', event: 'help' }
    ],
    show3: false,
    node: {},
    edge: {},
    cloneNode: {},
    dialogVisible: false,
    uploadData: {},
    fileList: [],
    layout: 'random',
    layouts: [{label: '随机布局', value: 'random'},
              {label: '力导向布局', value: 'force'},
              {label: 'Fruchterman布局', value: 'fruchterman'},
              {label: '环形布局', value: 'circular'},
              {label: '辐射布局', value: 'radial'},
              {label: '层次布局', value: 'dagre'},
              {label: '同心圆布局', value: 'concentric'},
              {label: '网格布局', value: 'grid'},
              {label: 'Ai布局', value: 'predictLayout'},
    ],
    filterLens: false,
    fishEyeCondition:false,
    edgefilterLens:new G6.EdgeFilterLens({
      trigger: 'mousemove',
      r: 200,
      type:"both",
      showLabel:'both',
      scaleRBy:'wheel',
      // shouldShow: d => {
      //   return d.size > 10;
      // }
    }),
    fisheye:new G6.Fisheye({
      r: 200,
      d: 10,
      showLabel: true,
      delegateStyle: {
        fill: '#f00',
        lineDash: [5, 5],
        stroke: '#666',
      },
    }),
  }),
  mounted () {
    this.keyCodeForEvent()
  },
  methods: {

    changeLayout () {this.graph.updateLayout({ type: this.layout ,preventOverlap: true, nodeSpaceing: (d) => {return d.size;} ,strictRadial: true})},

    onSuccess (res, file, fileList) {
      let reader = new FileReader()
      reader.readAsText(file.raw)
      reader.onload = (e) => {
        this.uploadData = []
        const str = String(e.target.result).replace(/\s*/g, '')
        if (str === '' || str === null) {
          this.$message.error('文档数据不能为空！')
        } else {
          try {
            this.uploadData = JSON.parse(str)
          } catch (err) {
            this.$message.error(String(err))
          }
        }
      }
    },
    onRemove (file) {
      this.fileList = []
    },

    comment_event (event) {
      this[event]()
    },

    search(){
      this.show3 = !this.show3;
      this.$emit("changeshow3",this.show3)
    },
    revoke () { // 撤销
      let log = this.$store.state.log
      let action = log[0].action
      switch (action) {
        case 'addNode':
          this.graph.removeItem(log[0].data.id)
          this.$store.commit('deleteNode', log[0].data)
          break
        case 'deleteNode':
          this.graph.addItem('node', log[0].data)
          this.$store.commit('addNode', log[0].data)
          break
        case 'addEdge':
          this.graph.removeItem(log[0].data.id)
          this.$store.commit('deleteEdge', log[0].data)
          break
        case 'deleteEdge':
          this.graph.addItem('edge', log[0].data)
          this.$store.commit('addEdge', log[0].data)
          break
      }
      this.$store.commit('deleteLog')
    },
    restore () {
      this.graph.clear()
    },
    copy () {
      if (this.selectedNode === null) {
        this.$message.error('未选择节点！')
      } else {
            this.cloneNode = objectJS.deepClone(node)
            this.$message.success('复制成功')
          }
      },

    paste () {
      if (this.selectedNode === null) {
        this.$message.error('未选择节点！')
      } else {
        this.cloneNode.x = this.cloneNode.x + 20
        this.cloneNode.y = this.cloneNode.y + 20
        let obj = objectJS.deepClone(this.cloneNode)
        this.graph.addItem('node', obj)
        this.$message.success('粘贴成功')
      }
    },
    delete () {
      if (this.selectedEdge === null && this.selectedNode === null) {
        this.$message.error('未选择元素！')
      } else if (this.selectedEdge !== null) {
        let obj = {}
        this.graph.getEdges().forEach((edge) => {
          if (edge._cfg.id === this.selectedEdge.id) {
            obj = edge._cfg.model
          }
        })
        this.graph.removeItem(this.selectedEdge.id)
      } else if (this.selectedNode !== null) {
        let obj = {}
        this.graph.getNodes().forEach((node) => {
          if (node._cfg.id === this.selectedNode.id) {
            obj = node._cfg.model
          }
        })
        this.graph.removeItem(this.selectedNode.id)
      }
    },
    onTop () {
      if (this.selectedEdge.id === '' && this.selectedNode.id === '') {
        this.$message.error('未选择元素！')
      } else if (this.selectedEdge.id !== '') {
        this.graph.getEdges().forEach((edge) => {
          if (edge._cfg.id === this.selectedEdge.id) {
            edge.toFront()
          }
        })
      } else if (this.selectedNode.id !== '') {
        this.graph.getNodes().forEach((node) => {
          if (node._cfg.id === this.selectedNode.id) {
            node.toFront()
          }
        })
      }
    },
    onBottom () {
      if (this.selectedEdge.id === '' && this.selectedNode.id === '') {
        this.$message.error('未选择元素！')
      } else if (this.selectedEdge.id !== '') {
        this.graph.getEdges().forEach((edge) => {
          if (edge._cfg.id === this.selectedEdge.id) {
            edge.toBack()
          }
        })
      } else if (this.selectedNode.id !== '') {
        this.graph.getNodes().forEach((node) => {
          if (node._cfg.id === this.selectedNode.id) {
            node.toBack()
          }
        })
      }
    },
    plus () {
      const currentZoom = Number(this.graph.getZoom())
      this.graph.zoomTo(currentZoom + 0.1)
      this.size = Number(((currentZoom + 0.1) * 100).toFixed(0))
    },
    minus () {
      const currentZoom = Number(this.graph.getZoom())
      this.graph.zoomTo(currentZoom - 0.1)
      this.size = Number(((currentZoom - 0.1) * 100).toFixed(0))
    },
    adaptCanvas () {
      this.graph.fitView(20)
      this.graph.fitCenter()
      this.graph.zoomTo(1)
    },
    importFile () {
      this.dialogVisible = true
    },
    initializeObj (source, target) { // 初始化node/edge，防止上传文件数据中缺少参数
      for (let key in source) {
        if (!target.hasOwnProperty(key)) {
          target[key] = source[key]
        }
        if (typeof source[key] === 'object') {
          this.initializeObj(source[key], target[key])
        }
      }
      return target
    },
    saveImage () {
      if (!isNullAndEmpty(this.$store.state.dataList.nodes)) {
        this.graph.downloadFullImage('graph', 'image/png', {
          backgroundColor: '#fff',
          padding: [15, 15, 15, 15]
        })
      } else {
        this.$message.warning('画布为空！')
      }
    },
    saveJson () {
      //鱼眼放大镜
      if(!this.fishEyeCondition) {
        this.graph.removePlugin(this.fisheye);
        this.fishEyeCondition = true;
      }
      else {
        this.graph.addPlugin(this.fisheye);
        this.fishEyeCondition = false;
      }
    },
    help () {

      if(!this.filterLens) {
        this.graph.removePlugin(this.edgefilterLens);
        this.filterLens = true;
      }
      else {
        //滤镜
        this.graph.addPlugin(this.edgefilterLens);
        this.filterLens = false;
      }
    },
    // 模拟组合键触发函数
    keyCodeForEvent () {
      let code = 0
      let code2 = 0
      let _this = this
      document.onkeydown = function (e) {
        let evn = e || event
        let key = evn.keyCode || evn.which || evn.charCode
        if (key === 17) {
          code = 17
        }
        if (key === 90) {
          code2 = 90
        }
        if (key === 8) {
          code2 = 8
        }
        if (key === 67) {
          code2 = 67
        }
        if (key === 86) {
          code2 = 86
        }
        if (code === 17 && code2 === 90) {
          _this.revoke()
          code = 0
          code2 = 0
        }
        if (code === 17 && code2 === 8) {
          _this.delete()
          code = 0
          code2 = 0
        }
        if (code === 17 && code2 === 67) {
          _this.copy()
          code = 0
          code2 = 0
        }
        if (code === 17 && code2 === 86) {
          _this.paste()
          code = 0
          code2 = 0
        }
      }
      document.onkeyup = function (e) {
        if (e.keyCode === 17) {
          code = 0
        }
        if (e.keyCode === 13) {
          code2 = 0
        }
      }
    }
}}
</script>
<style lang="less" scoped>
.toolbar {
  position: fixed;
  background: #fff;
  top: 10%;
  left:25%;
  width: 50%;
  z-index: 999;
}
.v-modal {
  display: none;
}
.el-select .el-input.is-focus .el-input__inner {
  border-color: #35495e;
}
.el-select-dropdown__item.selected {
  color: #35495e;
}
.el-select .el-input__inner:focus {
  border-color: #35495e;
}
.el-input__inner:focus {
  border-color: #35495e;
}
.el-scrollbar__wrap{
  overflow-x: hidden;
}
</style>
