<template>
  <el-container style="border: 1px solid #eee;">
    <el-aside style="background-color: rgb(238, 241, 246)" width="300px">
      <el-input v-model="filterText" placeholder="输入关键字进行过滤"></el-input>
      <el-tree
        ref="tree"
        :data="decisionKnowledge"
        :filter-node-method="filterNode"
        :props="defaultProps"
        class="filter-tree"
        highlight-current
        indent="0"
      >
      </el-tree>
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-button @click="OpenDialog">新增条目</el-button>
        <el-autocomplete
          v-model="queryRow"
          :fetch-suggestions="querySearch"
          :trigger-on-focus="false"
          class="inline-input"
          placeholder="请输入查询内容"
          @select="handleSelect"
        ></el-autocomplete>
      </el-header>
      <el-main style="padding : 0px">
        <modifyDK ref="modifyDK"
                  :dialog="dialog"
                  :selectItem="selectItem"
                  @closeDialog="() => { this.dialog = false }"
                  @restore="() => { this.selectItem = null }"
        ></modifyDK>
        <div ref="tableBox" class="table-box">
          <el-table
            v-if="tableHeight"
            :data="dkItems.filter(data => !queryRow || data.name.toLowerCase().includes(queryRow.toLowerCase()))"
            :max-height="tableHeight + 'px'" highlight-current-row stripe style="width: 100%"
            @current-change="handleCurrentChange"
          >
            <el-table-column align="center" label="知识条目" prop="dkName" width="140"></el-table-column>
            <el-table-column align="center" label="规则前项" prop="ruleAntecedents" width="300">
              <template v-slot="scope">
                <el-tag v-for="(item,index) in scope.row.ruleAntecedents" :key="index" :index="index+''" type="success">
                  {{ item.condition.cluster + ':' + item.condition.content }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column align="center" label="规则后项" prop="ruleConsequents" width="300">
              <template v-slot="scope">
                <el-tag v-for="(item,index) in scope.row.ruleConsequents" :key="index" :index="index+''" type="primary">
                  {{ item.result.cluster + ':' + item.result.content }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column align="center" label="知识描述" prop="description" width="240"></el-table-column>
            <el-table-column align="center" label="知识来源" prop="dkSource" width="140"></el-table-column>
            <el-table-column align="center" label="创建/修改日期" prop="modifyTime" width="120"></el-table-column>
            <el-table-column align="center" label="责任人" prop="manager" width="140"></el-table-column>
            <el-table-column fixed="right" label="知识操作" width="200">
              <template v-slot="scope">
                <el-button type="text" @click.native.prevent="showKnowledgeGraph(scope.row.id)">
                  查看图谱
                </el-button>
                <el-button type="text" @click.native.prevent="updateKnowledge(scope.row.id)">
                  修改内容
                </el-button>
                <el-button type="text" @click.native.prevent="findSource(scope.row.id)">
                  查看来源
                </el-button>
                <el-button type="text" @click.native.prevent="deleteKnowledge(scope.row.id)">
                  删除条目
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination :total="1000" background class="el-pagination" layout="prev, pager, next"></el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import modifyDK from '@/views/KM/decision/components/modifyDK'
import * as KgBuilder from '@/api/system/KgBuilder'
import * as decisionKnowledge from '@/api/system/decisionKnowledge'
import * as kgBuilderApi from '@/api/system/KgBuilder'

export default {
  name: 'decision',

  components: {
    modifyDK
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  data() {
    return {
      tableHeight: 0,
      filterText: '',
      decisionKnowledge: [
        {
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
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 3,
          label: '一级 3',
          children: [{
            id: 7,
            label: '二级 3-1'
          }, {
            id: 8,
            label: '二级 3-2'
          }]
        }
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      dkItems: [
        {
          id: '',
          dkName: '',
          domain: '',
          description: '',
          dkSource: '',
          modifyTime: '',
          manager: '',
          ruleAntecedents: [
            {
              id: '',
              content: '',
              cluster: '',
              condition: {
                id: '',
                cluster: '',
                content: ''
              }
            }
          ],
          ruleConsequents: [
            {
              id: '',
              content: '',
              cluster: '',
              result: {
                id: '',
                cluster: '',
                content: ''
              }
            }
          ]
        }
      ],
      selectItem: {},
      dialog: false
    }
  },

  mounted() {
    this.initPage()
    this.tableHeight = this.$refs.tableBox.clientHeight - 60
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    initPage() {
      decisionKnowledge.getAllDecisionKnowledge().then(result => {
        if (result.code == 200) {
          this.dkItems = result.data
        }
      })
    },
    getKnowledgeTable() {

    },
    getDomainKnowledge() {

    },
    addKnowledge() {

    },
    updateKnowledge() {
      this.OpenDialog();
    },
    deleteKnowledge(dkId) {
      console.log(dkId)
      decisionKnowledge.deleteDK(dkId).then(result => {
        if (result.code === 200) {
          alert('删除节点成功！')
        } else {
          alert('删除节点失败！')
        }
      })
    },
    showKnowledgeGraph() {

    },
    findSource() {

    },
    OpenDialog() {
      console.log(this.dialog)
      this.dialog = true
      console.log(this.dialog)
    },
    handleCurrentChange(val) {
      this.selectItem = val
      console.log(this.selectItem)
    }
  }
}
</script>

<style scoped>
.el-pagination {
  position: center;
}

.table-box {
  height: calc(100vh - 150px); /*示例中顶部区域固定高度190px*/
  margin: 0px;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

</style>
