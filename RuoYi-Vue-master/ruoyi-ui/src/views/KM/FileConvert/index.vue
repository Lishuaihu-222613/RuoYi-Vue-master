<template>

  <div class="app-container">
    <el-container>
      <el-collapse-transition>
        <el-aside  v-show="show3">
          <KnowledgeTree ref="KnowledgeTree"></KnowledgeTree>
        </el-aside>
      </el-collapse-transition>
        <el-main data-app="true">
          <Toolbar ref="Toolbar"
                   :size="size"
                   :graph="graph"
                   @changeshow3="changeshow3"
                   :selectedNodeId.sync="selectedNodeId"
                   :selectedEdgeId.sync="selectedEdgeId">
          </Toolbar>
          <div ref="G6" id="G6" class="knowledgeGraph" style="width:100%"></div>
          <Sidebar
            ref="sidebar"
            :graph="graph"
            :selectedNodeId="selectedNodeId"
            :selectedEdgeId="selectedEdgeId"
            :drawer="sidebardrawer"
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
import * as G6 from '@antv/g6';
import { GraphLayoutPredict } from '@antv/vis-predict-engine'
import * as kgBuilderApi from '@/api/system/KgBuilder'
import _ from 'lodash'
import * as d3 from 'd3'
import { isNullAndEmpty } from '@/views/KM/FileConvert/actions/commen'
import { ToolBar } from '@antv/g6-pc'

export default {
  name: 'FileConvert',

  components:{
    Navmenu,
    Toolbar,
    Sidebar,
    KnowledgeTree
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
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
      selectedNodeId: '',
      selectedEdgeId: '',
      sidebardrawer: false,
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
      nodeButtonAction: "",
      tooltip: null,
      mouserPos: { left: "-1000px", top: "-1000px" },
      isAddLink: false,
      isDeleteLink: false,
      selectSourceNodeId: 0,
      selectTargetNodeId: 0,
      domain: "",
      domainId: 0,
      nodeName: "",
      pageSize: 500,
      activeNode: null,
      nodeImageList: [],
      showImageList: [],
      editorContent: "",
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
      G6graphs:{
        nodes:[],
        edges:[]
      },
      jsonShow: false,
      helpShow: false
    };
  },
  mounted() {
      this.getDomainGraph(),
      console.log("aaa"+this.graph)
      this.initG6(),
      console.log("bbb"+this.graph)
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    changeshow3(messageInfo){
      this.show3 = messageInfo
    },
    //创建新领域
    createDomain (value) {
      this.$prompt("请输入领域名称", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      })
        .then(res => {
          value = res.value;
          let data = { domain: value, type: 0 };
          kgBuilderApi.createDomain(data).then(result => {
            if (result.code == 200) {
              this.getDomain();
              this.domain = value;
              this.getDomainGraph();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },

    getDomain (pageIndex) {
      this.pageModel.pageIndex = pageIndex
        ? pageIndex
        : this.pageModel.pageIndex;
      let data = {
        pageIndex: this.pageModel.pageIndex,
        pageSize: this.pageModel.pageSize,
        command: 0
      };
      this.getLabels(data);
    },
    getLabels (data) {
      kgBuilderApi.getDomains(data).then(result => {
        if (result.code == 200) {
          this.pageModel = result.data;
          this.pageModel.totalPage = parseInt((result.data.totalCount - 1) / result.data.pageSize) + 1;
        }
      });
    },
    matchDomainGraph (domain) {
      this.domain = domain.name;
      this.domainId = domain.id;
      console.log(this.domain+this.domainId)
      this.getDomainGraph();
    },
    getDomainGraph () {
      this.loading = true;
      let data = {
        domain: "加工方法",
        nodeName: this.nodeName,
        pageSize: 50
      };
      kgBuilderApi.getDomainGraph(data).then(result => {
        if (result.code == 200) {
          if (result.data != null) {
            this.graphs.nodes = result.data.node;
            this.graphs.edges = result.data.relationship;
            console.log(this.graphs)
            this.updateGraph();
          }
        }
      });
    },
    updateGraph(){
      this.G6graphs.nodes.push(this.graphs.nodes.map(function(item){
        return{
          id:item.uuid,
          label:item.name
        }
      }));
      this.G6graphs.nodes = this.G6graphs.nodes[0]
      this.G6graphs.edges.push(this.graphs.edges.map(function(item){
        return{
          id: item.uuid,
          source: item.sourceId,
          target: item.targetId,
          label: item.name
        }
      }));
      this.G6graphs.edges = this.G6graphs.edges[0];
      console.log(this.graph)
      this.graph.data(this.G6graphs);
      this.graph.render();
    },
    //删除领域
    deleteDomain (id, value) {
      this.$confirm(
        "此操作将删除该标签及其下节点和关系(不可恢复), 是否继续?",
        "三思而后行",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function (res) {
          let data = { domainId: id, domain: value };
          kgBuilderApi.deleteDomain(data).then(result => {
            if (result.code == 200) {
              this.getDomain();
              this.domain = "";
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    },
    handleNodeClick(data) {
      console.log(data);
    },

    pluginsControl(name){

      let pluginName = name;

      //鱼眼放大镜
      let fisheye = new G6.Fisheye({
        r: 200,
        d: 5,
        showLabel: true,
        delegateStyle: {
          fill: '#f00',
          lineDash: [5, 5],
          stroke: '#666',
        },
      });

      //右键菜单
      let contextmenu = new G6.Menu({
        offsetX: 6,
        offsetY: 10,
        itemTypes: ['node','edge'],
        getContent(e) {
        const outDiv = document.createElement('div');
        outDiv.style.width = '180px';
        outDiv.innerHTML = `<ul>
        <li @click="handleClick('A','b')">测试01</li>
        <li>测试01</li>
        <li>测试01</li>
        <li>测试01</li>
        <li>测试01</li>
      </ul>`
        return outDiv
      },
        handleMenuClick(target, item) {
        console.log(target, item)
      },
      });

      //滤镜
      let filterLens = new G6.EdgeFilterLens({

      });

      this.graph.addPlugin(contextmenu);

    },

    initG6 () {
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
        position:{x:100,y:20},
      });

      //提示插件
      let tooltip = new G6.Tooltip({
        offsetX: 10,
        offsetY: 20,
        getContent(e) {
          const outDiv = document.createElement('div');
          outDiv.style.width = '180px';
          outDiv.innerHTML = `
      <h4>元素信息</h4>
      <ul>
        <li>Label: ${e.item.getModel().label || e.item.getModel().id}</li>
      </ul>`
          return outDiv
        },
        itemTypes: ['node','edge']
      });

      this.graph = new G6.Graph({
        container: 'G6',
        // width: this.$refs.G6.offsetWidth,
        // height: this.$refs.G6.offsetHeight,
        fitView: true,
        enabledStack: true,
        plugins: [grid,toolbar,tooltip],
        layout: {
          type: 'gForce',
          nodeStrength: 1000,
          preventOverlap: true,
          nodeSize: 30,
          edgeStrength: 200,
          linkDistance: 200,
          nodespacing:10,
          gpuEnabled:true
        },
        modes: {
          default: [
            'hover-node',
            'zoom-canvas', // 缩放canvas
            'drag-canvas', // 拖拽canvas
            {
              type: 'drag-node' // 拖拽node
            },
            'click-add-node',
            'click-select',
            'select-edge'
          ],
          addEdge: [
            'click-add-edge',
            'hover-node',
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
          }
        }
      })
      this.graph.read(this.$store.state.dataList)
      this.graph.on('node:dblclick',(e) => {
        this.item = e
        this.selectedEdgeId = ''
        this.selectedNodeId = e.select ? e.selectedItems.nodes[0]._cfg.id : ''
        this.sidebardrawer = true
      })
      this.graph.on('nodeselectchange', (e) => {
        this.item = e
        this.selectedEdgeId = ''
        this.selectedNodeId = e.select ? e.selectedItems.nodes[0]._cfg.id : ''
      })
      this.graph.on('edge:click', (e) => {
        this.selectedNodeId = ''
        this.selectedEdgeId = e.item._cfg.id
      })
      this.graph.on('canvas:click', (e) => {
        this.selectedNodeId = ''
        this.selectedEdgeId = ''
      })
      this.graph.on('viewportchange', (e) => {
        if (e.action === 'zoom') {
          this.size = Number((Number(this.graph.getZoom()) * 100).toFixed(0))
        }
      })
      this.pluginsControl();
    },
  },
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

.knowledgeGraph{
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
.G6{
  z-index: 0;
}

</style>
