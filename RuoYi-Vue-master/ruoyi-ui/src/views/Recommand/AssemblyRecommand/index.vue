<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="12" :xs="24">
        <div class="head-container">
          <treeselect v-model="elementType" :multiple="true" :options="elementTree" :show-count="true"
                      placeholder="请选择类别" @select="changeLabel(node)"
          />
          <el-select v-model="selectElementId" :disabled="disabled" placeholder="请选择产品或组件">
            <el-option
              v-for="item in elementOptions"
              :key="item.elementId"
              :label="item.elementName"
              :value="item.elementId"
            >
            </el-option>
          </el-select>
          <el-button type="primary" @click="handleClick">加 载</el-button>
        </div>
        <div class="head-container">
          <iFrame :src="selectElement.modelFile.fileUrl"></iFrame>
        </div>
        <div class="head-container">
          <el-tabs type="border-card">
            <el-tab-pane>
              <span slot="label"><i class="el-icon-date"></i> 产品BOM</span>
              <el-tree
                ref="tree"
                :data="elementTree"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                :props="defaultProps"
                default-expand-all
                node-key="elementId"
                @node-click="handleNodeClick"
              />
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label"><i class="el-icon-date"></i> 材料组分</span>
              <el-table
                :data="prscriptionElements"
                border
                style="width: 100%"
              >
                <el-table-column
                  label="组分"
                  prop="date"
                  width="180"
                >
                </el-table-column>
                <el-table-column
                  label="质量分数"
                  prop="name"
                  width="180"
                >
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="12" :xs="24">
        <el-row>
          <treeselect v-model="processType" :multiple="true" :options="processTree" :show-count="true"
                      placeholder="请选择工艺类别"
          />
          <el-button @click="processRecommand">推荐工艺</el-button>
          <el-button @click="processCreate">创建工艺</el-button>
        </el-row>
        <el-row>
          <el-table :data="processData" border style="width: 100%">
            <el-table-column key="elementNumber" :show-overflow-tooltip="true"
                             align="center"
                             label="序号"
                             prop="elementNumber"
            />

            <el-table-column key="elementName" :show-overflow-tooltip="true"
                             align="center"
                             label="名称"
                             prop="elementName"
            />
            <el-table-column key="elementDescription" :show-overflow-tooltip="true"
                             align="center"
                             label="内容"
                             prop="elementDescription"
            />
            <el-table-column key="usedResources" :show-overflow-tooltip="true"
                             align="center"
                             label="使用资源"
                             prop="usedResources"
            >
              <template slot-scope="scope">
                <el-tag v-for="(item, index) in scope.row.usedResources" :key="index" :index="index+''" type="success">
                  {{ scope.row.usedResources[index].resourceType + ':' + scope.row.usedResources[index].resourceName }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column key="elementRemark" :show-overflow-tooltip="true"
                             align="center"
                             label="备注"
                             prop="elementRemark"
            >

            </el-table-column>
            <el-table-column key="elementRequirements" :show-overflow-tooltip="true"
                             align="center"
                             label="要求" prop="elementRequirements"
            >

            </el-table-column>
            <el-table-column key="elementOtherProperties" :show-overflow-tooltip="true"
                             align="center"
                             label="其他" prop="elementOtherProperties"
            >

            </el-table-column>

            <el-table-column
              align="center"
              class-name="small-padding fixed-width"
              fixed="right"
              label="操作"
              width="160"
            >
              <template slot-scope="scope">
                <el-button
                  icon="el-icon-edit"
                  size="mini"
                  type="text"
                  @click="handleUpdate(scope.row)"
                >修改工艺
                </el-button>
                <el-button
                  icon="el-icon-delete"
                  size="mini"
                  type="text"
                  @click="handleDelete(scope.row)"
                >删除工艺
                </el-button>
                <el-button
                  icon="el-icon-delete"
                  size="mini"
                  type="text"
                  @click="handleSave(scope.row)"
                >保存工艺
                </el-button>
                <el-button
                  icon="el-icon-edit"
                  size="mini"
                  type="text"
                  @click="recommandAfter(scope.row)"
                >推荐下一步
                </el-button>
                <el-button
                  icon="el-icon-edit"
                  size="mini"
                  type="text"
                  @click="createProcess(scope.row)"
                >添加工艺
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-col>
    </el-row>

    <el-dialog :visible.sync="modifyProcessShow" append-to-body title="修改工艺" width="600px">
      <el-form ref="resource" :model="selectProcess" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="序号" prop="resourceName">
              <el-input v-model="selectResource.resourceName" maxlength="30" placeholder="请输入资源名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="resourceTypes">
              <treeselect v-model="selectResource.resourceTypes" :multiple="true" :options="labelTree"
                          :show-count="true" placeholder="请选择资源类别"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="内容" prop="resourceDescription">
            <el-input v-model="selectResource.resourceDescription" placeholder="请输入资源描述" type="textarea"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="使用资源" prop="resourceDescription">
            <el-tag
              :key="resource.resourceId"
              v-for="resource in selectResource.usedResources"
              closable
              :disable-transitions="false"
              @close="handleClose(resource)">
              {{resource.dynamicLabels[0]+":"+resource.resourceName}}
            </el-tag>
            <el-button class="button-new-tag" size="small" @click="showInput">+ 添加资源</el-button>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="designAttentions">
              <el-button style="float: right" type="primary" @click="addDesignAttentions">添加事项</el-button>
              <el-row v-for="(item,index) in selectResource.designAttentions" :key="index">
                <el-col :span="20">
                  <el-input v-model="selectResource.designAttentions[index]"></el-input>
                </el-col>
                <el-col :span="4">
                  <el-button
                    circle icon="el-icon-delete" type="danger"
                    @click="removeDesignAttentions(selectResource.designAttentions[index])"
                  ></el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="要求">
              <el-button @click="addRequirement"/>
              <el-row v-for="(value, key) in selectResource.toolCapacity"
                      :key="key"
                      :gutter="20"
              >
                <el-col :span="6">
                  <el-input v-model="key" placeholder="名称" style="width:100%"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="6">
                  <el-input v-model="value" placeholder="内容" style="width:100%"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click.prevent="removeRequirement(key)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="其他属性">
              <el-button @click="addOtherProperty"/>
              <el-row v-for="(value, key) in selectResource.toolCapacity"
                      :key="key"
                      :gutter="20"
              >
                <el-col :span="6">
                  <el-input v-model="key" placeholder="名称" style="width:100%"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="6">
                  <el-input v-model="value" placeholder="内容" style="width:100%"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click.prevent="removeOtherProperty(key)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="seleceResourceShow" append-to-body title="选择资源" width="300px">
      <el-form :inline="true" :model="newResource" class="demo-form-inline">
        <el-form-item label="资源类别">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资源标签">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择资源">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">取消</el-button>
          <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


  </div>

</template>

<script>

import iFrame from '@/components/iFrame/index.vue'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'index',
  components: { Treeselect, iFrame }
}
</script>

<style scoped>

</style>
