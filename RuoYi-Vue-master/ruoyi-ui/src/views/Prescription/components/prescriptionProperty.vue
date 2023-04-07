<template>
  <el-dialog :visible.sync="dialogFormVisible" title="检测方法编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="燃烧性能">
        <el-form :model="combustionProperty" :rules="rules" ref="combustionPropertyForm">
          <el-form-item :label-width="formLabelWidth" label="节点名称">
            <el-input v-model="combustionProperty.propertyName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃速">
            <el-button @click="addBurningRate" />
            <el-row v-for="(value, key) in combustionProperty.burningRate" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteBurningRate"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃速压力指数">
            <el-input v-model="combustionProperty.BRPressureIndex"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃速温度指数">
            <el-button @click="addBRTemperatureSensitivity" />
            <el-row v-for="(value, key) in combustionProperty.BRTemperatureSensitivity" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteBRTemperatureSensitivity"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="压力温度指数">
            <el-button @click="addPressureTemperatureSensitivity" />
            <el-row v-for="(value, key) in combustionProperty.pressureTemperatureSensitivity" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deletepressureTemperatureSensitivity"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-row v-for="(value, key) in combustionProperty.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmit">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="能量性能">
        <el-form :model="energyProperty" :rules="rules" ref="energyPropertyForm">
          <el-row>
              <el-form-item label="节点名称" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户昵称" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="归属部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户昵称" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="归属部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户昵称" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="归属部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户昵称" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="归属部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户昵称" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="归属部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="changeRelation">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="力学性能">
        <el-form :model="method" :rules="rules" ref="relatedForm">
          <el-form-item :label-width="formLabelWidth" label="关联典型工艺">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="processLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="processLabelOptions"
                          placeholder="请选择工艺标签"
              />
              <el-select v-model="processIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in processOptions"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="关联质量问题">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="problemLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="problemLabelOptions"
                          placeholder="请选择问题标签"
              />
              <el-select v-model="problemIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in problemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用设备">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="resourceLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="resourceIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in resourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="fileLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="fileLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="fileIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in fileOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="changeRelation">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="力学性能">
        <el-form :model="method" :rules="rules" ref="relatedForm">
          <el-form-item :label-width="formLabelWidth" label="关联典型工艺">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="processLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="processLabelOptions"
                          placeholder="请选择工艺标签"
              />
              <el-select v-model="processIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in processOptions"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="关联质量问题">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="problemLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="problemLabelOptions"
                          placeholder="请选择问题标签"
              />
              <el-select v-model="problemIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in problemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用设备">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="resourceLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="resourceIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in resourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="fileLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="fileLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="fileIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in fileOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="changeRelation">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="贮存性能">
        <el-form :model="method" :rules="rules" ref="relatedForm">
          <el-form-item :label-width="formLabelWidth" label="关联典型工艺">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="processLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="processLabelOptions"
                          placeholder="请选择工艺标签"
              />
              <el-select v-model="processIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in processOptions"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="关联质量问题">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="problemLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="problemLabelOptions"
                          placeholder="请选择问题标签"
              />
              <el-select v-model="problemIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in problemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="使用设备">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="resourceLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="resourceLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="resourceIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in resourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="相关文件">
            <el-row class="row-bg" justify="space-around" type="flex">
              <treeselect v-model="fileLabel"
                          :clearable="true"
                          :searchable="true"
                          :normalizer="normalizer"
                          :options="fileLabelOptions"
                          placeholder="请选择标签"
              />
              <el-select v-model="fileIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in fileOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="changeRelation">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>


import Treeselect from '@riophae/vue-treeselect'

export default {
  name: 'prescriptionProperty',
  components: { Treeselect },

}
</script>

<style scoped>

</style>
