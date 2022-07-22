<template>
  <div class="app-container">
    <el-container>
      <el-collapse-transition>
        <el-aside v-show="show3">
          <KnowledgeTree ref="KnowledgeTree"
                         @searchDomain="(text) =>{getDomainGraph(text)}"
          ></KnowledgeTree>
        </el-aside>
      </el-collapse-transition>
      <el-main data-app="true">
        <Toolbar ref="Toolbar"
                 :size="size"
                 :graph="graph"
                 @changeshow3="changeshow3"
                 :selectedNode.sync="selectedGraphItem.Node"
                 :selectedEdge.sync="selectedGraphItem.Edge"
        >
        </Toolbar>
        <div ref="G6" id="G6" class="knowledgeGraph" style="width:100%"></div>
        <Sidebar ref="Sidebar"
                 :graph="graph"
                 :selectedNode="selectedGraphItem.Node"
                 :selectedEdge="selectedGraphItem.Edge"
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
import _ from 'lodash'
import * as d3 from 'd3'
import { isNullAndEmpty } from '@/views/KM/FileConvert/actions/commen'
import { ToolBar } from '@antv/g6-pc'
import { updateData } from '@/api/system/dict/data'
// import { contextMenu } from '@/views/KM/FileConvert/actions/canvas_contextmenu'

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
      hulls: [{}]
    }
  },
  mounted() {
    this.getDomainGraph(),
      console.log('aaa' + this.graph)
    this.initG6(),
      console.log('bbb' + this.graph)
  },
  methods: {
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

      console.log(this.graph)
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

    pluginsControl(name) {

      let pluginName = name

      //右键菜单
      let contextMenu = new G6.Menu({
        getContent(evt) {
          if (evt.target && evt.target.isCanvas && evt.target.isCanvas()) {
            return `
        <ul>
            <li title="addNode">创建节点</li>
            <li title="addEdge">创建关系</li>
            <li title="modeMatch">模式匹配</li>
            <li title="findShortestPath">最短路查找</li>
        </ul>`
          } else if (evt.item && evt.item.getType() === 'node') {
            return `
        <ul>
            <li title="addNode">添加关系</li>
            <li title="deleteNode">删除节点</li>
            <li title="updateNode">修改节点</li>
            <li title="3dModel">查看模型</li>
        </ul>`
          } else if (evt.item && evt.item.getType() === 'edge') {
            return `
        <ul>
            <li title="updateEdge">修改关系</li>
            <li title="deleteEdge">删除关系</li>
            <li>li 3</li>
        </ul>`
          }
          let valueX = evt.x
        },
        handleMenuClick: (target, item) => {
          if (target.title === 'addNode') {
            let obj = {
              label: '',
              x: target.offsetTop,
              y: target.offsetLeft
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
          } else if (target.title === 'addEdge') {
            this.graph.setMode('addEdge')
          } else if (target.title === 'deleteNode') {
            console.log(item._cfg.id)
            let obj = {
              nodeId: item._cfg.id,
              domain: this.graph.get('domain')
            }
            console.log(obj.nodeId)
            kgBuilderApi.deleteNode(obj).then(result => {
              if (result.code === 200) {
                alert('删除节点成功！')
                this.graph.removeItem(item)
              } else {
                alert('删除节点失败！')
              }
            })
          } else if (target.title === 'deleteEdge') {
            let obj = {
              domain: this.graph.get('domain'),
              edgeId: item._cfg.id
            }
            console.log(obj)
            kgBuilderApi.deleteEdge(obj).then(result => {
              if (result.code === 200) {
                alert('删除关系成功！')
                this.graph.removeItem(item)
              } else {
                alert('删除关系失败！')
              }
            })

          } else if (target.title === 'updateNode') {
            this.selectedGraphItem.Edge = null
            this.selectedGraphItem.Node = item.getModel()
            console.log(this.selectedGraphItem)
            this.sidebarDrawer = true
          } else if (target.title === 'updateEdge') {
            this.selectedGraphItem.Node = null
            this.selectedGraphItem.Edge = ev.item.getModel()
            console.log(this.selectedGraphItem)
            this.sidebarDrawer = true
          } else if (target.title === 'modeMatch') {
            const { GADDI } = G6.Algorithm
            console.log(this.graphs)
            console.log(this.patternData)
            const resultMatches = GADDI(this.graphs, this.patternData, true, undefined, undefined, 'cluster', 'cluster')
            console.log(resultMatches)
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
          } else if (target.title === 'findShortestPath') {
            const selectedNodes = this.graph.findAllByState('node', 'selected')
            const { findShortestPath } = G6.Algorithm
            // path 为其中一条最短路径，allPath 为所有的最短路径
            const { path, allPath } = findShortestPath(
              this.graphs,
              selectedNodes[0].getID(),
              selectedNodes[1].getID()
            )

            const pathNodeMap = {}
            path.forEach((id) => {
              const pathNode = this.graph.findById(id)
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

          } else if (target.title === '3dModel') {
            let url = item.getModel().modelLocation
            window.open(url)
          }
          console.log(target.position)
          console.log(target, item)
        },
        // offsetX and offsetY include the padding of the parent container
        // 需要加上父级容器的 padding-left 16 与自身偏移量 10
        offsetX: 16 + 10,
        // 需要加上父级容器的 padding-top 24 、画布兄弟元素高度、与自身偏移量 10
        offsetY: 0,
        // the types of items that allow the menu show up
        // 在哪些类型的元素上响应
        itemTypes: ['node', 'edge', 'canvas']
      })

      this.graph.addPlugin(contextMenu)

    },

    async initG6() {

      G6.registerBehavior('hover-node', hoverNode)
      // 双击添加节点
      G6.registerBehavior('click-add-node', addNode)
      // 添加连线
      G6.registerBehavior('click-add-edge', addEdge)
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
          outDiv.style.width = '180px'
          outDiv.innerHTML = `
      <h4>元素信息</h4>
      <ul>
        <li>Label: ${e.item.getModel().label || e.item.getModel().id}</li>
      </ul>`
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
          nodeStrength: 1000,
          preventOverlap: true,
          nodeSize: 30,
          edgeStrength: 200,
          linkDistance: 200,
          nodespacing: 10,
          gpuEnabled: true
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
            'select-edge'
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
      // this.graph.read(this.$store.state.dataList)
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

<style scoped lang="less">

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

</style>
