<template>
  <el-drawer :visible.sync="drawer" :direction="direction" @open="handleOpen" @closed="handleClose">
    <el-form title="节点配置" v-if="nodeShow" :model="node" class="nodeForm">
      <el-form-item label="节点内容">
        <el-input v-model="node.label"
                  @input="nodeChange"
                  style="width: 81%"
                  size="mini">
        </el-input>
      </el-form-item>
      <el-form-item label="节点类别">
        <el-input v-model="node.cluster"
                  @input="nodeChange"
                  style="width: 81%"
                  size="mini">
        </el-input>
      </el-form-item>
      <el-form-item label="节点字体">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">大小：
            <el-input @input="nodeChange"
                      v-model="node.labelCfg.style.fontSize"
                      style="width: 60%"
                      size="mini"
                      :min="1"
                      type="number">
            </el-input>
          </el-col>
          <el-col :span="12">粗细：
            <el-input @input="nodeChange"
                      v-model="node.labelCfg.style.fontWeight"
                      :min="100"
                      :max="900"
                      :step="100"
                      style="width: 60%"
                      size="mini"
                      type="number">
            </el-input>
          </el-col>
        </el-row>
        <el-row type="flex" :gutter="20">
          <el-col :span="12">颜色：
            <el-color-picker @change="nodeChange"
                             v-model="node.labelCfg.style.fill"
                             style="vertical-align: top"
                             size="mini">
            </el-color-picker>
          </el-col>
          <el-col :span="12">位置：
            <el-select @change="nodeChange"
                       v-model="node.labelCfg.position"
                       style="width: 60%"
                       size="mini">
              <el-option v-for="item in placeList"
                         :key="item"
                         :label="item"
                         :value="item">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="节点样式">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">半径：
            <el-input @input="nodeChange"
                      v-model="radius"
                      style="width: 40%"
                      type="number"
                      :min="1"
                      size="mini">
            </el-input>
          </el-col>
          <el-col :span="12" >颜色：
            <el-color-picker @change="nodeChange"
                             v-model="node.style.fill"
                             style="vertical-align: top">
            </el-color-picker>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="节点坐标">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">X：
            <el-input-number v-model="node.x"
                             controls-position="right"
                             size="mini">
            </el-input-number>
          </el-col>
          <el-col :span="12">Y：
            <el-input-number v-model="node.y"
                             controls-position="right"
                             size="mini">
            </el-input-number>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="节点边框">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">宽度：
            <el-input @input="nodeChange"
                      v-model="node.style.lineWidth"
                      style="width: 40%"
                      type="number"
                      :min="1"
                      size="mini">
            </el-input>
          </el-col>
          <el-col :span="12">颜色：
            <el-color-picker @change="nodeChange"
                             v-model="node.style.stroke"
                             style="vertical-align: top"
                             size="mini">
            </el-color-picker>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="节点阴影">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">颜色：
            <el-color-picker @change="nodeChange"
                             v-model="node.style.shadowColor"
                             style="vertical-align: top"
                             size="mini">
            </el-color-picker>
          </el-col>
          <el-col :span="12">范围：
            <el-input @input="nodeChange"
                      v-model="node.style.shadowBlur"
                      style="width: 40%"
                      type="number"
                      :min="0"
                      size="mini">
            </el-input>
          </el-col>
        </el-row>
        <el-row type="flex" :gutter="20">
          <el-col :span="12">&bigtriangleup;X：
            <el-input @input="nodeChange"
                      v-model="node.style.shadowOffsetX"
                      style="width: 40%"
                      type="number"
                      :min="0"
                      size="mini">
            </el-input>
          </el-col>
          <el-col :span="12">&bigtriangleup;Y：
            <el-input @input="nodeChange"
                      v-model="node.style.shadowOffsetY"
                      style="width: 40%"
                      type="number"
                      :min="0"
                      size="mini"></el-input>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item size="large" style="bottom : 0px">
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
    <el-form title="关系配置" v-if="edgeShow" :model="edge" class="edgeForm">
      <el-form-item label="关系内容">
        <el-input @input="edgeChange"
                  v-model="edge.label"
                  style="width: 81%"
                  size="mini">
        </el-input>
      </el-form-item>
      <el-form-item label="关系类别">
        <el-input @input="edgeChange"
                  v-model="edge.cluster"
                  style="width: 81%"
                  size="mini">
        </el-input>
      </el-form-item>
      <el-form-item label="关系字体">
        <el-row type="flex" :gutter="20">
          <el-col :span="12">大小：
            <el-input @input="edgeChange"
                      v-model="edge.labelCfg.style.fontSize"
                      style="width: 60%"
                      size="mini"
                      type="number"
                      :min="1">
            </el-input>
          </el-col>
          <el-col :span="12">粗细：
            <el-input @input="edgeChange"
                      v-model="edge.labelCfg.style.fontWeight"
                      :min="100"
                      :max="900"
                      :step="100"
                      style="width: 60%"
                      size="mini"
                      type="number">
            </el-input>
          </el-col>
        </el-row>
        <el-row type="flex" :gutter="20">
          <el-col :span="12">颜色：
            <el-color-picker @change="edgeChange"
                             v-model="edge.labelCfg.style.fill"
                             style="vertical-align: top"
                             size="mini">
            </el-color-picker>
          </el-col>
          <el-col :span="12">定位：
            <el-select @change="edgeChange"
                       v-model="edge.labelCfg.position"
                       style="width: 60%"
                       size="mini">
              <el-option v-for="item in placeList1"
                         :key="item"
                         :label="item"
                         :value="item">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="关系样式">
        <el-row type="flex" :gutter="20">
          <el-col>类型：
            <el-select @change="edgeChange"
                       v-model="edge.type"
                       style="width: 72%"
                       size="mini">
              <el-option v-for="item in lineList"
                         :key="item"
                         :label="item"
                         :value="item">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row type="flex" :gutter="20">
          <el-col :span="12">宽度：
            <el-input @input="edgeChange"
                      v-model="edge.style.lineWidth"
                      style="width: 40%"
                      type="number"
                      :min="1"
                      size="mini">
            </el-input>
          </el-col>
          <el-col :span="12">颜色：
            <el-color-picker @change="edgeChange"
                             v-model="edge.style.stroke"
                             style="vertical-align: top"
                             size="mini">
            </el-color-picker>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item size="large" style="bottom : 0px">
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>
<script>

import * as kgBuilderApi from '@/api/system/KgBuilder'

export default {

  props: {
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
    },
    sidebarDrawer: {
      type: Boolean,
      default: false
    }
  },
  mounted () {

  },
  watch: {
    selectedEdge: {
      handler (newVal, oldVal) {
        if (newVal !== null) {
          this.edge = newVal
          this.nodeShow = false
          this.edgeShow = true
          this.bigPanels = [0, 1]
          this.littlePanels = [0, 1]
        }
      },
      deep: true
    },
    selectedNode: {
      handler (newVal, oldVal) {
        if (newVal !== null) {
          this.node = newVal
          this.nodeShow = true
          this.edgeShow = false
          this.radius = newVal.size[0] / 2
          this.bigPanels = [0, 1]
          this.littlePanels = [0, 1]
        }
      },
      deep:true
    },
    sidebarDrawer:{
      handler (newVal, oldVal) {
        this.drawer = newVal
        }
      }
    },
  data: () => ({
    lineList: ['line', 'polyline', 'arc', 'quardratic', 'cubic', 'cubic-horizontal'],
    placeList: ['center', 'top', 'bottom', 'left', 'right'],
    placeList1: ['start', 'middle', 'end'],
    bigPanels: [0, 1],
    littlePanels: [0, 1],
    radius: '',
    drawer:false,
    nodeShow:false,
    edgeShow:false,
    node: {},
    edge: {},
    direction: 'rtl',
  }),
  methods: {
    nodeChange () {
      this.node.size = [this.radius * 2, this.radius * 2]
      this.graph.updateItem(this.node.id, this.node)
    },
    edgeChange () {
      this.graph.updateItem(this.edge.id, this.edge)
    },
    handleOpen(){
      this.node = this.selectedNode;
      this.edge = this.selectedEdge;
    },
    handleClose(){
      this.drawer = false;
      this.$emit('closeSidebar',null)
      this.$emit('restore',null)
      console.log(this.drawer)
    },
    onSubmit(){
      if(this.node !== null && this.node.id !== ""){
        console.log(this.node)
        kgBuilderApi.createNode(this.node).then(result => {
          if (result.code === 200) {
            alert("更改成功！")
            this.graph.addItem('node',result.data)
          }
          else{
            alert("更改失败！")
          }
        })
      }else{
        console.log(this.edge)
        if(this.edge.stateStyles === ""){
          this.edge.stateStyles = null;
        }
        kgBuilderApi.updateEdge(this.edge).then(result => {
          if (result.code === 200) {
            alert("更改成功！")
            this.graph.addItem('edge',result.data)
          }
          else{
            alert("更改失败！")
          }
        })
      }
      }

  }
}
</script>
<style lang="less" scoped>
.g6-minimap {
  width: 100%;
  height: 20vw;
  border: 1px solid #35495e;
}
.nodeForm{

}
/deep/ .el-col-12 {
  margin-top: 5px;
}
/deep/ .panel .v-expansion-panel-content__wrap {
  padding: 0 3px 10px 3px;
  box-sizing: border-box;
}
/deep/ .littlepanel .v-expansion-panel-content__wrap {
  padding: 0 10px 10px 20px;
  box-sizing: border-box;
}
/deep/ .el-input__inner{
  padding: 0 0 0 15px;
}
</style>
