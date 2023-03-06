<template>
  <el-container style="border: 1px solid #eee;">
    <el-aside style="background-color: rgb(238, 241, 246)" width="400px">
      <el-row>
        <el-col :span="15">
          <el-input
            v-model="filterText"
            placeholder="输入关键字进行过滤"
          >
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-button circle icon="el-icon-plus" style="float: right" type="primary"
                     @click="addProcess"
          ></el-button>
        </el-col>
      </el-row>
      <el-tree
        ref="tree"
        :data="processes"
        :filter-node-method="filterNode"
        :props="defaultProps"
        class="filter-tree"
        icon-class="el-icon-folder"
        @node-click="nodeClick"
      >
      </el-tree>
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px; height: 150px ; margin: 10px">
        <ModifyProcess
          ref="ModifyProcess"
          :dialog="modifyProcessShow"
          :selectId="selectProcessId"
          @closeDialog="() =>{ this.modifyProcessShow = false }"
        ></ModifyProcess>
        <el-descriptions :column="3" border class="margin-top" title="工艺规程信息">
          <template slot="extra">
            <el-dropdown split-button type="primary" @command="handleMenuClick">
              更多操作
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="modifyProcess">信息修改</el-dropdown-item>
                <el-dropdown-item command="addSequence">添加工序</el-dropdown-item>
                <el-dropdown-item command="showGraph">查看图谱</el-dropdown-item>
                <el-dropdown-item command="deleteProcess">删除工艺</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              工艺规程Id
            </template>
            <span>{{ this.selectedProcess.processId }}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              工艺规程名
            </template>
            <span>{{ this.selectedProcess.processName }}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              备注
            </template>
            <span>{{ this.selectedProcess.processDescription }}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              关联零、部、组（整）件
            </template>
            <!--            <span>{{ this.selectedProcess.associatedStructure.structureName }}</span>-->
            <el-button size="small" type="primary" @click="openProcessModify">查看</el-button>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              关联产品系统
            </template>
            <!--            <span>{{ this.selectedProcess.associatedProduct.structureName }}</span>-->
            <el-button size="small" type="primary" @click="openProcessModify">查看</el-button>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              关联文件
            </template>
            <el-button @click="getAssociatedFile">查看文件</el-button>
          </el-descriptions-item>
        </el-descriptions>
      </el-header>
      <el-main>
        <vue-context-menu :contextMenuData="contextMenuData"
                          @createSequence="createSequence"
                          @deleteSequence="deleteSequence"
                          @updateSequence="updateSequence"
        ></vue-context-menu>
        <ModifySequence ref="ModifySequence"
                        :dialog="modifySequenceShow"
                        :pId="selectProcessId"
                        :selectId="selectSequenceId"
                        @closeDialog="() =>{ this.modifySequenceShow = false }"
        >
        </ModifySequence>
        <StepTable ref="stepTable"
                   :dialog="stepTableShow"
                   :selectId="selectSequenceId"
                   @closeDialog="() =>{ this.stepTableShow = false }"
        >
        </StepTable>
        <div ref="tableBox" class="table-box">
          <el-table
            v-if="tableHeight"
            :data="sequences"
            :max-height="tableHeight + 'px'"
            highlight-current-row
            stripe
            style="width: 100%"
            @current-change="handleCurrentChange"
            @row-contextmenu="showContextMenu"
            @expand-change="expandSequence"
          >
            <el-table-column type="expand">
              <template v-slot="props">
                <el-form class="demo-table-expand" inline label-position="left">
                  <el-row>
                    <el-col :span="6">
                      <el-form-item label="工序号">
                        <span>{{ props.row.sequenceNumber }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="工序名称">
                        <span>{{ props.row.sequenceName }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="准结工时">
                        <span>{{ props.row.QuasiClosingHours }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="单件工时">
                        <span>{{ props.row.TaktTime }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-form-item label="工序内容">
                      <span>{{ props.row.nickName }}</span>
                    </el-form-item>
                  </el-row>
                  <el-row>
                    <el-form-item label="使用资源">
                      <el-button>查看使用资源</el-button>
                    </el-form-item>
                  </el-row>
                  <el-row>
                    <el-form-item label="工序备注">
                      <span v-for="(item, markIndex) in props.row.sequenceRemark" :key="markIndex"
                      >{{ props.row.sequenceRemark[markIndex] }}</span>
                    </el-form-item>
                  </el-row>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column align="center" label="工序号" prop="sequenceNumber" width="160"></el-table-column>
            <el-table-column align="center" label="工序名称" prop="sequenceName" width="160"></el-table-column>
            <el-table-column align="center" label="工序内容" prop="sequenceDescription" width="400"></el-table-column>
            <el-table-column label="操作" width="250">
              <template v-slot="scope">
                <el-row>
                  <el-col :span="8">
                    <el-button type="small" @click.native.prevent="showModifySequence(scope.row.sequenceId)">
                      修改工序
                    </el-button>
                  </el-col>
                  <el-col :span="8">
                    <el-button type="small" @click.native.prevent="addSequence(scope.row.sequenceId)">
                      添加工序
                    </el-button>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="8">
                    <el-button type="small" @click.native.prevent="removeSequence(scope.row.sequenceId)">
                      移除工序
                    </el-button>
                  </el-col>
                  <el-col :span="8">
                    <el-button type="small" @click.native.prevent="showStepTable(scope.row.sequenceId)">
                      查看工步
                    </el-button>
                  </el-col>
                </el-row>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
      <el-footer>
        <el-pagination :total="100" background class="el-pagination" layout="prev, pager, next"></el-pagination>
      </el-footer>
    </el-container>
  </el-container>

</template>

<script>

import ModifySequence from '@/views/Process/components/ModifySequence.vue'
import ModifyProcess from '@/views/Process/components/ModifyProcess.vue'
import StepTable from '@/views/Process/components/StepTable.vue'
import * as processManagement from '@/api/system/processManagement'

export default {

  name: 'process',

  components: {
    ModifySequence,
    ModifyProcess,
    StepTable
  },

  watch: {
    filterKnowledge(val) {
      this.$refs.tree.filter(val)
    }
  },

  data() {
    return {
      filterText: '',
      tableHeight: 0,
      contextMenuData: {
        menuName: 'sequenceManage',
        axis: {
          x: null,
          y: null
        },
        menulists: []
      },
      tableMenu: [
        {
          fnHandler: 'createSequence', // 绑定事件
          icoName: 'el-icon-circle-plus-outline', // 图标
          btnName: '创建工序' // 菜单名称
        },
        {
          fnHandler: 'updateSequence',
          icoName: 'el-icon-search',
          btnName: '修改工序'
        },
        {
          fnHandler: 'removeSequence',
          icoName: 'el-icon-paperclip',
          btnName: '移除工序'
        }
      ],
      processes: [],
      sequences: [
        {
          sequenceId: 0,
          sequenceName: '',
          sequenceNumber: '',
          sequenceDescription: '',
          QuasiClosingHours: 0.0,
          TaktTime: 0.0,
          sequenceRemark: []
        }
      ],
      selectedProcess: {
        processId: 0,
        processName: '',
        processDescription: '',
        associatedStructure: {
          structureId: 0,
          structureName: ''
        },
        associatedProduct: {
          structureId: 0,
          structureName: ''
        }
      },
      defaultProps: {
        label: 'processName',
        children: 'children'
      },
      openProcess: false,
      modifySequenceShow: false,
      modifyProcessShow: false,
      stepTableShow: false,
      selectSequenceId: 0,
      selectProcessId: 0

    }
  },

  created() {
    this.initPage()
  },
  mounted() {
    this.tableHeight = this.$refs.tableBox.clientHeight - 60
    // this.selectedProcess = this.processes[0]
    // this.$refs.tree.setChecked(this.processes[0].processId)
    // this.getSequencesByProcess()
  },

  methods: {
    initPage() {
      processManagement.getAllProcess().then(result => {
        if (result.code == 200) {
          this.processes = result.data
        }
      })
    },
    showContextMenu() {

    },
    addProcess() {
      let newProcess = {
        processId: 0,
        processName: '待修改',
        processDescription: '待修改'
      }
      processManagement.createProcess(newProcess).then(result => {
        if (result.code == 200) {
          this.$modal.msgSuccess('创建工艺成功！')
          this.processes.push(result.data)
        }
      })
    },
    handleMenuClick(command) {

      if (command == 'addSequence') {
        let newSequence = {
          sequenceId: 0,
          sequenceName: '待编辑',
          sequenceNumber: '待编辑',
          sequenceDescription: '待编辑',
          QuasiClosingHours: 0.0,
          TaktTime: 0.0,
          sequenceRemark: ['待编辑', '待编辑']
        }
        let pAnds = {
          processId: this.selectedProcess.processId,
          sequence: newSequence
        }
        processManagement.addSequenceForProcess(pAnds).then(result => {
          if (result.code == 200) {
            this.sequences.push(result.data)
          }
        })
      } else if (command == 'showGraph') {

      } else if (command == 'deleteProcess') {
        processManagement.deleteProcessById(this.selectedProcess.processId).then(result => {
          if (result.code == 200) {
            this.$message.success(result.statusText)
          }
        })
      } else if (command == 'modifyProcess') {
        this.modifyProcessShow = true
      }
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    nodeClick(pro, node, com) {
      this.selectedProcess = pro
      this.selectProcessId = pro.processId
      this.getSequencesByProcess()
    },
    getSelectedProcess(data) {
      this.selectedProcess = data
      this.getSequencesByProcess()
    },
    getSequencesByProcess() {
      processManagement.getSequenceByProcess(this.selectedProcess.processId).then(result => {
        if (result.code == 200) {
          console.log(result.data)
          this.sequences = result.data
        }
      })
    },
    openProcessModify() {
      this.openProcess = true
    },

    getAssociatedFile() {

    },
    expandSequence(row, expandedRows) {

    },
    showModifySequence(sequenceId) {
      this.selectSequenceId = sequenceId
      this.modifySequenceShow = true
    },
    handleCurrentChange(val) {
      this.selectSequence = val
      console.log(this.selectSequence)
    },
    showStepTable(sequenceId) {
      this.selectSequenceId = sequenceId
      this.stepTableShow = true
    }
  }
}
</script>

<style scoped>

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

</style>
