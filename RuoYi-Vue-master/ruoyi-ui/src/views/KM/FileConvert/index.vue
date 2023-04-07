<template>
  <div class="app-container">
    <el-container>
      <el-collapse-transition>
        <el-aside v-show="show3" width="330px">
          <KnowledgeTree ref="KnowledgeTree"
                         @searchDomain="(text) =>{getDomainGraph(text)}"
          ></KnowledgeTree>
        </el-aside>
      </el-collapse-transition>
      <el-main data-app="true">
        <Toolbar ref="Toolbar"
                 :graph="graph"
                 :selectedEdge.sync="selectedGraphItem.Edge"
                 :selectedNode.sync="selectedGraphItem.Node"
                 :size="size"
                 @changeshow3="changeshow3"
        >
        </Toolbar>
        <div id="G6" ref="G6" class="knowledgeGraph" style="width:100%"></div>
        <vue-context-menu :contextMenuData="contextMenuData"
                          @addEdge="addEdge"
                          @addNode="addNode"
                          @deleteEdge="deleteEdge"
                          @deleteNode="deleteNode"
                          @expandNode="expandNode"
                          @findShortestPath="findShortestPath"
                          @modeMatch="modeMatch"
                          @openFile="openFile"
                          @updateEdge="updateEdge"
                          @updateNode="updateNode"
        >
        </vue-context-menu>
        <Sidebar ref="Sidebar"
                 :graph="graph"
                 :selectedEdge="selectedGraphItem.Edge"
                 :selectedNode="selectedGraphItem.Node"
                 :sidebarDrawer="sidebarDrawer"
                 @closeSidebar="() => {this.sidebarDrawer = false}"
                 @restore="() => {this.selectedGraphItem.Node = null; this.selectedGraphItem.Edge = null }"
        >
        </Sidebar>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Toolbar from '@/views/KM/FileConvert/components/toolbar'
import Sidebar from '@/views/KM/FileConvert/components/sidebar'
import Navmenu from '@/views/KM/FileConvert/components/Navmenu'
import KnowledgeTree from '@/views/KM/FileConvert/components/KnowledgeTree'
import defaultNode from '@/views/KM/FileConvert/default/default_node'
import defaultEdge from '@/views/KM/FileConvert/default/default_edge'
import addNode from '@/views/KM/FileConvert/actions/add_node'
import hoverNode from '@/views/KM/FileConvert/actions/hover_node'
import addEdge from '@/views/KM/FileConvert/actions/add_edge'
import selectEdge from '@/views/KM/FileConvert/actions/select_edge'
import * as G6 from '@antv/g6'
import { GraphLayoutPredict } from '@antv/vis-predict-engine'
import * as kgBuilderApi from '@/api/system/KgBuilder'
import { getTextBoundingRect } from 'echarts/lib/util/format'

export default {
  name: 'FileConvert',

  components: {
    Navmenu,
    Toolbar,
    Sidebar,
    KnowledgeTree
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  data() {
    return {
      contextMenuData: {
        menuName: 'test',
        // 菜单显示的位置
        axis: {
          x: null,
          y: null
        },
        // 菜单选项
        menulists: []
      },
      graphEvent: {},
      filterText: '',
      show3: false,
      treeData: [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }],
      treeProps: {
        children: 'children',
        label: 'label'
      },
      count: 1,
      drawer: false,
      graph: null,
      selectedGraphItem: {
        Node: {},
        Edge: {},
        Combo: {}
      },
      sidebarDrawer: false,
      item: {},
      addingEdge: true,
      edge: null,
      size: 100,
      svg: null,
      timer: null,
      simulation: null,
      linkGroup: null,
      linkTextGroup: null,
      nodeGroup: null,
      nodeTextGroup: null,
      nodeSymbolGroup: null,
      nodeButtonGroup: null,
      nodeButtonAction: '',
      tooltip: null,
      mouserPos: { left: '-1000px', top: '-1000px' },
      isAddLink: false,
      isDeleteLink: false,
      selectSourceNodeId: 0,
      selectTargetNodeId: 0,
      domain: '',
      domainId: 0,
      nodeName: '',
      pageSize: 500,
      activeNode: null,
      nodeImageList: [],
      showImageList: [],
      editorContent: '',
      pageModel: {
        pageIndex: 1,
        pageSize: 30,
        totalCount: 0,
        totalPage: 0,
        nodeList: []
      },
      graphs: {
        nodes: [],
        edges: []
      },
      G6graphs: {
        nodes: [],
        edges: []
      },
      jsonShow: false,
      helpShow: false,
      patternData: {
        nodes: [{
          id: '',
          cluster: ''
        }],
        edges: [{
          source: '',
          target: '',
          cluster: ''
        }]
      },
      hulls: [{}],
      //画布右键菜单项
      canvasMenu: [
        {
          fnHandler: 'addNode', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建节点' // 菜单名称
        },
        {
          fnHandler: 'addEdge',
          icoName: 'el-icon-bottom-left',
          btnName: '创建关系'
        },
        {
          fnHandler: 'modeMatch',
          icoName: 'el-icon-search',
          btnName: '模式匹配'
        },
        {
          fnHandler: 'findShortestPath',
          icoName: 'el-icon-paperclip',
          btnName: '最短路查找'
        }
      ],
      //节点右键菜单项
      nodeMenu: [
        {
          fnHandler: 'addEdge', // 绑定事件
          icoName: 'el-icon-bottom-left', // 图标
          btnName: '添加关系' // 菜单名称
        },
        {
          fnHandler: 'updateNode',
          icoName: 'el-icon-edit',
          btnName: '修改节点'
        },
        {
          fnHandler: 'deleteNode',
          icoName: 'el-icon-delete',
          btnName: '删除节点'
        },
        {
          fnHandler: 'expandNode',
          icoName: 'el-icon-rank',
          btnName: '展开节点'
        },
        {
          fnHandler: '3dModel',
          icoName: 'el-icon-folder-opened',
          btnName: '查看关联文件'
        }
      ],
      //关系右键菜单项
      edgeMenu: [
        {
          fnHandler: 'updateEdge', // 绑定事件
          icoName: 'el-icon-edit', // 图标
          btnName: '修改关系' // 菜单名称
        },
        {
          fnHandler: 'deleteEdge',
          icoName: 'el-icon-delete',
          btnName: '删除关系'
        }
      ]
    }
  },
  mounted() {
    this.getDomainGraph(),
      console.log('aaa' + this.graph)
    this.initG6(),
      console.log('bbb' + this.graph)
  },
  methods: {

    //展示右键菜单
    showMenu(event) {
      event.preventDefault()
      let x = event.clientX
      let y = event.clientY
      // 获得当前位置
      this.contextMenuData.axis = {
        x, y
      }
      if (event.target && event.target.isCanvas && event.target.isCanvas()) {
        this.contextMenuData.menulists = this.canvasMenu
      } else if (event.item && event.item.getType() === 'node') {
        this.contextMenuData.menulists = this.nodeMenu
      } else if (event.item && event.item.getType() === 'edge') {
        this.contextMenuData.menulists = this.edgeMenu
      }
      this.graphEvent = event
      console.log(this.graphEvent)
    },
    //添加节点
    addNode() {
      let obj = {
        label: '',
        x: this.graphEvent.target.clientX,
        y: this.graphEvent.target.clientY
      }
      obj.domain = this.graph.get('domain')
      kgBuilderApi.createNode(obj).then(result => {
        if (result.code === 200) {
          alert('创建节点成功！')
          this.graph.addItem('node', result.data)
        } else {
          alert('创建节点失败！')
        }
      })
    },
    //添加关系
    addEdge() {
      this.graph.setMode('addEdge')
    },
    //删除节点
    deleteNode() {
      let obj = {
        nodeId: this.graphEvent.item._cfg.id,
        domain: this.graph.get('domain')
      }
      kgBuilderApi.deleteNode(obj).then(result => {
        if (result.code === 200) {
          alert('删除节点成功！')
          this.graph.removeItem(this.graphEvent.item)
        } else {
          alert('删除节点失败！')
        }
      })
    },
    //删除关系
    deleteEdge() {
      let obj = {
        domain: this.graph.get('domain'),
        edgeId: this.graphEvent.item._cfg.id
      }
      kgBuilderApi.deleteEdge(obj).then(result => {
        if (result.code === 200) {
          alert('删除关系成功！')
          this.graph.removeItem(this.graphEvent.item)
        } else {
          alert('删除关系失败！')
        }
      })
    },
    //修改节点
    updateNode() {
      this.selectedGraphItem.Edge = null
      this.selectedGraphItem.Node = this.graphEvent.item.getModel()
      console.log(this.selectedGraphItem)
      this.sidebarDrawer = true
    },
    //修改关系
    updateEdge() {
      this.selectedGraphItem.Node = null
      this.selectedGraphItem.Edge = this.graphEvent.item.getModel()
      console.log(this.selectedGraphItem)
      this.sidebarDrawer = true
    },
    //模式匹配
    modeMatch() {
      const { GADDI } = G6.Algorithm
      const resultMatches = GADDI(this.graphs, this.patternData, true, undefined, undefined, 'cluster', 'cluster')
      resultMatches.forEach((match, i) => {
        this.hulls[i] = this.graph.createHull({
          id: `${i}`,
          members: match.nodes.map(node => node.id)
        })
      })
      this.graph.on('afterupdateitem', (e) => {
        this.hulls.forEach((h, i) => {
          h.updateData(h.members)
        })
      })
    },
    //最短路查找
    findShortestPath() {
      let selectedNodes = this.graph.findAllByState('node', 'selected')
      let { findShortestPath } = G6.Algorithm
      // path 为其中一条最短路径，allPath 为所有的最短路径
      let { path, allPath } = findShortestPath(
        this.graphs,
        selectedNodes[0].getID(),
        selectedNodes[1].getID()
      )
      let pathNodeMap = {}
      path.forEach((id) => {
        let pathNode = this.graph.findById(id)
        pathNode.toFront()
        this.graph.setItemState(pathNode, 'highlight', true)
        pathNodeMap[id] = true
      })
      this.graph.getEdges().forEach((edge) => {
        const edgeModel = edge.getModel()
        const source = edgeModel.source
        const target = edgeModel.target
        const sourceInPathIdx = path.indexOf(source)
        const targetInPathIdx = path.indexOf(target)
        if (sourceInPathIdx === -1 || targetInPathIdx === -1) return
        if (Math.abs(sourceInPathIdx - targetInPathIdx) === 1) {
          this.graph.setItemState(edge, 'highlight', true)
        } else {
          this.graph.setItemState(edge, 'inactive', true)
        }
      })
      this.graph.getNodes().forEach((node) => {
        if (!pathNodeMap[node.getID()]) {
          this.graph.setItemState(node, 'inactive', true)
        }
      })
    },
    //查看相关文件
    openFile() {
      let url = this.graphEvent.item.getModel().modelLocation
      window.open(url)
    },
    //展开节点
    expandNode() {
      let params = {
        domain: '',
        nodeId: this.graphEvent.item._cfg.id
      }
      kgBuilderApi.getMoreRelatedNode(params).then(result => {
        if (result.code === 200) {
          alert('节点展开成功！')
          for (let item of result.data.nodes) {
            let node = this.graph.findById(item.id)
            if(node === undefined){
              console.log(result.data.nodes)
              this.graph.addItem('node', item)
            }
          }
          for (let item of result.data.edges) {
            let edge = this.graph.findById(item.id)
            if(edge === undefined) {
              console.log(result.data.edges)
              this.graph.addItem('edge', item)
            }
          }
          this.graph.layout()
        } else {
          alert('节点展开失败！')
        }
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    changeshow3(messageInfo) {
      this.show3 = messageInfo
    },
    //创建新领域
    createDomain(value) {
      this.$prompt('请输入领域名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(res => {
          value = res.value
          let data = { domain: value, type: 0 }
          kgBuilderApi.createDomain(data).then(result => {
            if (result.code == 200) {
              this.getDomain()
              this.domain = value
              this.getDomainGraph()
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          })
        })
    },

    getDomain(pageIndex) {
      this.pageModel.pageIndex = pageIndex
        ? pageIndex
        : this.pageModel.pageIndex
      let data = {
        pageIndex: this.pageModel.pageIndex,
        pageSize: this.pageModel.pageSize,
        command: 0
      }
      this.getLabels(data)
    },
    getLabels(data) {
      kgBuilderApi.getDomains(data).then(result => {
        if (result.code == 200) {
          this.pageModel = result.data
          this.pageModel.totalPage = parseInt((result.data.totalCount - 1) / result.data.pageSize) + 1
        }
      })
    },
    matchDomainGraph(domain) {
      this.domain = domain.name
      this.domainId = domain.id
      console.log(this.domain + this.domainId)
      this.getDomainGraph('TestDomain')
    },
    getDomainGraph(domain) {
      this.loading = true
      this.domain = 'TestDomain'
      let data = {
        domain: domain,
        nodeName: this.nodeName,
        pageSize: 50
      }
      kgBuilderApi.getDomainGraph(data).then(result => {
        if (result.code == 200) {
          if (result.data != null) {
            this.graphs.nodes = result.data.nodes
            this.graphs.edges = result.data.edges
            console.log(this.graphs)
            this.updateGraph()
          }
        }
      })
    },

    updateGraph() {
      this.graph.data(this.graphs)
      this.graph.render()
    },
    //删除领域
    deleteDomain(id, value) {
      this.$confirm(
        '此操作将删除该标签及其下节点和关系(不可恢复), 是否继续?',
        '三思而后行',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      )
        .then(function(res) {
          let data = { domainId: id, domain: value }
          kgBuilderApi.deleteDomain(data).then(result => {
            if (result.code == 200) {
              this.getDomain()
              this.domain = ''
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },

    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate)
    },
    handleNodeClick(data) {
      console.log(data)
    },

    pluginsControl() {

    },

    async initG6() {

      G6.registerBehavior('hover-node', hoverNode)
      // 双击添加节点
      G6.registerBehavior('click-add-node', addNode)
      // 添加连线
      G6.registerBehavior('click-add-edge', addEdge)
      // 选中连线
      G6.registerBehavior('select-edge', selectEdge)

      let grid = new G6.Grid()

      // 缩略图
      // let minimap = new G6.Minimap({
      //   container: this.$refs.sidebar.$refs.minimap,
      //   size: [
      //     this.$refs.sidebar.$refs.minimap.offsetWidth - 8,
      //     this.$refs.sidebar.$refs.minimap.offsetHeight
      //   ]
      // })

      let toolbar = new G6.ToolBar({
        position: { x: 100, y: 20 }
      })

      //提示插件
      let tooltip = new G6.Tooltip({
        offsetX: 10,
        offsetY: 20,
        getContent(e) {
          const outDiv = document.createElement('div')
          outDiv.style.width = '300px'
          const model = e.item.getModel()
          let li = ""
          for (let [key, value] of Object.entries(model)) {
            if (key !== 'labelCfg' && key != 'style' && key != 'type' && key != 'x' && key != 'y'
              && key != 'size' && key != 'anchorPoints'&& key != 'linkPoints'
              && key != 'stateStyles'&& key != 'id'&& key != 'layoutOrder' && key != 'label'&& key != 'weight'
              && key != 'degree' && key != 'source' && key != 'target' && key != 'startPoint' && key != 'endPoint'
             && key != '_order'&& key != 'vx'&& key != 'vy'&& key != 'index'
            ) {
              li += "<li>" + key + '：' + value + '<li>'
            }
          }
          outDiv.innerHTML = `
      <h4>${e.item.getModel().label || e.item.getModel().id}</h4>
      <ul>`+li+`</ul>`
          return outDiv
        },
        itemTypes: ['node', 'edge']
      })
      const { predictLayout, confidence } = await GraphLayoutPredict.predict(this.graphs)
      console.log(predictLayout, confidence)

      this.graph = new G6.Graph({
        container: 'G6',
        // width: this.$refs.G6.offsetWidth,
        // height: this.$refs.G6.offsetHeight,
        fitView: false,
        enabledStack: true,
        domain: this.domain,
        animate: true,
        plugins: [grid, tooltip],
        layout: {
          type: 'gForce',
          preventOverlap: true,
          linkDistance: 50,         // 可选，边长
          nodeStrength: 30,         // 可选
          edgeStrength: 0.1,        // 可选
          collideStrength: 0.8,     // 可选
          nodeSize: 30,             // 可选
          alpha: 0.3,               // 可选
          alphaDecay: 0.028,        // 可选
          alphaMin: 0.01,           // 可选
          forceSimulation: null    // 可选
        },
        modes: {
          default: [
            'hover-node',
            // 缩放canvas
            {
              type: 'zoom-canvas',
              enableOptimize: true
            },
            // 拖拽canvas
            {
              type: 'drag-canvas',
              enableOptimize: true
            },
            'activate-relations',
            {
              type: 'lasso-select',
              delegateStyle: {
                fill: '#f00',
                fillOpacity: 0.05,
                stroke: '#f00',
                lineWidth: 1
              },
              onSelect: (nodes, edges) => {
                edges.forEach((edge, i) => {
                    const edgeModel = edge.getModel()
                    this.patternData.edges[i] = edgeModel
                  }
                )
                nodes.forEach((node, i) => {
                    const nodeModel = node.getModel()
                    this.patternData.nodes[i] = nodeModel
                  }
                )
              },
              trigger: 'drag'
            },

            {
              type: 'drag-node' // 拖拽node
            },
            'click-add-node',
            'click-select',
            'select-edge',
            'context-menu'
          ],
          dragLasso: [
            'drag-node'
          ],
          addEdge: [
            'click-add-edge',
            // 'hover-node',
            'zoom-canvas',
            'drag-canvas',
            'click-add-node'
          ]
        },
        defaultNode: defaultNode,
        defaultEdge: defaultEdge,
        edgeStateStyles: {
          hover: {
            stroke: '#409eff' // 颜色
          },
          selected: {
            stroke: '#409eff' // 颜色
          }
        },
        nodeStateStyles: {
          selected: {
            stroke: '#409eff',
            lineWidth: 1,
            fill: '#409eff'
          },
          active: {
            stroke: '#409eff',
            lineWidth: 1,
            fill: '#409eff'
          },
          inactive: {
            opacity: 0.2,
            fill: '#4682B4', // 填充色
            stroke: '#4682B4', // 描边颜色
            lineWidth: 1, // 描边宽度
            shadowColor: '#909399', // 阴影颜色
            shadowBlur: 10, // 阴影范围
            shadowOffsetX: 3, // 阴影 x 方向偏移量
            shadowOffsetY: 3, // 阴影 y 方向偏移量
            cursor: 'pointer'
          }
        }
      })
      this.graph.on('node:dblclick', (ev) => {
        this.selectedGraphItem.Edge = null
        this.selectedGraphItem.Node = ev.item.getModel()
        console.log(this.selectedGraphItem)
        this.sidebarDrawer = true
      })
      this.graph.on('edge:dblclick', (ev) => {
        this.selectedGraphItem.Node = null
        this.selectedGraphItem.Edge = ev.item.getModel()
        console.log(this.selectedGraphItem)
        this.sidebarDrawer = true
      })
      this.graph.on('canvas:click', (ev) => {
        this.selectedGraphItem.Node = null
        this.selectedGraphItem.Edge = null
      })
      this.graph.on('contextmenu', (ev) => {
          this.showMenu(ev)
        }
      )
      this.graph.on('viewportchange', (e) => {
        if (e.action === 'zoom') {
          this.size = Number((Number(this.graph.getZoom()) * 100).toFixed(0))
        }
      })

      this.pluginsControl()
    }
  }
}
</script>

<style lang="less" scoped>

.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  padding: 0px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  padding: 12px;
}

.tree {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  width: 330px;
  padding: 12px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  display: flex;
  padding: 0;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {

}

.el-container:nth-child(7) .el-aside {

}

.contextmenu {
  margin: 0;
  background: #fff;
  z-index: 3000;
  position: absolute;
  list-style-type: none;
  padding: 5px 0;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  color: #333;
  box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
}

.contextmenu li {
  margin: 0;
  padding: 7px 16px;
  cursor: pointer;
}

.contextmenu li:hover {
  background: #eee;
}

.knowledgeGraph {
  width: 100%;
  display: flex;
  flex-direction: row;
  box-sizing: border-box;
  height: 88vh;
  border: 2px solid #3333;
  margin: 2vw 2vw 2vw 2vw;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}

.index {
  width: 100%;

  &__main {
    width: 100%;

    &-left {

    }

    &-right {
      height: 88vh;
      border: 1px solid #3333;
      margin-top: 2vw;
      margin-right: 10px;
      overflow-y: scroll;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);

      &__pot {
        position: absolute;
        width: 2vw;
        height: 2vw;
        background: #35495e;
        border-radius: 1vw;
        clip: rect(0px 1vw 2vw 0px);
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
        top: 50%;
        cursor: pointer;
        z-index: 1000 !important;
      }

      &__pot:hover {
        background: #dcdfe6;
      }
    }
  }
}

.g6-tooltip {
  padding: 10px 6px;
  color: #444;
  background-color: rgba(255, 255, 255, 0.9);
  border: 1px solid #e2e2e2;
  border-radius: 4px;
}

.G6 {
  z-index: 0;
}

td.table_td_h {
  padding: 17px 0;
}

.right-menu {
  display: none;
  position: fixed;
  background: #fff;
  border: 1px solid #bababa;
  border-radius: 3px;
  z-index: 999;
  box-shadow: 2px 2px 3px 0px rgba(51, 42, 40, 0.7);
  border-radius: 1px;

  p {
    margin: 0;
    display: block;
    width: 200px;
    height: 35px;
    line-height: 35px;
    text-align: left;
    padding: 0 24px 0 32px;
    color: #000000;
    cursor: pointer;
    font-size: 15px;
    border-bottom: 1px solid #e8eaed;

    &:hover {
      background: #e8eaed;
    }
  }

  .menu-disable {
    cursor: no-drop;
    color: #80868b;
  }
}

</style>
