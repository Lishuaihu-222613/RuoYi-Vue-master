<template>
  <el-dialog :visible.sync="dialogFormVisible" title="相关敏感性编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="热敏感性">
        <el-form ref="heatStabilityForm" :model="heatStability">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="敏感性名称">
                <el-input v-model="heatStability.stabilityName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="燃点">
                <el-input v-model="heatStability.ignitionPoint"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="维也里简单法">
                <el-input v-model="heatStability.simpleVieri"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="维也里重复法">
                <el-input v-model="heatStability.repetitionVieri"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="自燃温度">
                <el-input v-model="heatStability.autoignitionTemperature"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="明火感度">
                <el-input v-model="heatStability.openFireSensitivity"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="电火花感度">
                <el-input v-model="heatStability.sparkSensitivity"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="闪点">
                <el-input v-model="heatStability.flashPoint"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="爆炸极限">
              <el-input v-model="heatStability.explosionLimit"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newHeatStabilityOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newHeatStabilityOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addHeatStabilityOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in heatStability.otherProperty" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="8">
                  <el-input v-model="heatStability.otherProperty[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteHeatStabilityOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitHeatStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="机械敏感性">
        <el-form ref="mechanicalStabilityForm" :model="mechanicalStability">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="敏感性名称">
                <el-input v-model="mechanicalStability.stabilityName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="冲击感度">
                <el-input-number v-model="mechanicalStability.impactSensitivity" :precision="2"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="摩擦感度">
                <el-input-number v-model="mechanicalStability.frictionSensitivity" :precision="2"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="震动感度">
                <el-input-number v-model="mechanicalStability.vibrationSensitivity" :precision="2"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item :label-width="formLabelWidth" label="枪击感度">
                <el-input-number v-model="mechanicalStability.vibrationSensitivity" :precision="2"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newMechanicalStabilityOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newMechanicalStabilityOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addMechanicalStabilityOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in mechanicalStability.otherProperty" :key="name">
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="8">
                  <el-input v-model="mechanicalStability.otherProperty[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteMechanicalStabilityOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitMechanicalStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="辐射敏感度">
        <el-form ref="radioStabilityForm" :model="radioStability">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="敏感性名称">
                <el-input v-model="radioStability.stabilityName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="辐射感度">
                <el-input-number v-model="radioStability.radiationSensitivity" precision="2"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newRadioStabilityOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newRadioStabilityOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addRadioStabilityOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in radioStability.otherProperty" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="8">
                  <el-input v-model="radioStability.otherProperty[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteRadioStabilityOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitRadioStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="爆炸敏感性">
        <el-form ref="explosionStabilityForm" :model="explosionStability">
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="敏感性名称">
              <el-input v-model="explosionStability.stabilityName"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="冲击试验">
                <el-input v-model="explosionStability.impactTest"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="炸药分级">
                <el-input v-model="explosionStability.ExplosiveClassification"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newExplosionStabilityOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newExplosionStabilityOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addExplosionStabilityOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in explosionStability.otherProperty" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col :span="8">
                  <el-input v-model="explosionStability.otherProperty[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteExplosionStabilityOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitExplosionStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>

import * as prescriptionManagement from '@/api/system/prescriptionManagement'

export default {
  name: 'prescriptionStability',
  props: {
    selectPrescriptionId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    }
  },
  watch: {
    selectPrescription: {
      handler(newVal, oldVal) {
        if (newVal !== null || newVal !== 0) {
          this.prescriptionId = newVal
        }
      }
    },
    dialog: {
      handler(newVal, oldVal) {
        this.dialogFormVisible = newVal
      }
    },
    title: {
      handler(newVal, oldVal) {
        this.windowTitle = newVal
      }
    }
  },
  created() {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      tabPosition: 'left',
      windowTitle: '创建配方',
      prescription: {
        prescriptionId: 0,
        prescriptionName: '',
        prescriptionLabels: [],
        prescriptionDescription: '',
        density: '',
        dangerLevel: '',
        curingTime: {},
        otherProperty: {}
      },
      heatStability: {
        stabilityId: 0,
        stabilityName: '',
        simpleVieri: '',
        repetitionVieri: '',
        autoignitionTemperature: '',
        openFireSensitivity: '',
        sparkSensitivity: '',
        flashPoint: '',
        ignitionPoint: '',
        explosionLimit: '',
        otherProperty: {}
      },
      mechanicalStability: {
        stabilityId: 0,
        stabilityName: '',
        impactSensitivity: 0.0,
        frictionSensitivity: 0.0,
        vibrationSensitivity: 0.0,
        gunshotSensitivity: 0.0,
        otherProperty: {}
      },
      radioStability: {
        stabilityId: 0,
        stabilityName: '',
        radiationSensitivity: 0.0,
        otherProperty: {}
      },
      explosionStability: {
        stabilityId: 0,
        stabilityName: '',
        impactTest: '',
        ExplosiveClassification: '',
        otherProperty: {}
      },
      dialogFormVisible: false,
      formLabelWidth: '100px',
      inputVisible: false,
      inputValue: '',
      prescriptionId:0,
      newHeatStabilityOtherPropertyName:'',
      newHeatStabilityOtherPropertyValue:'',
      newMechanicalStabilityOtherPropertyName:'',
      newMechanicalStabilityOtherPropertyValue:'',
      newRadioStabilityOtherPropertyName:'',
      newRadioStabilityOtherPropertyValue:'',
      newExplosionStabilityOtherPropertyName:'',
      newExplosionStabilityOtherPropertyValue:'',
    }
  },
  methods:{
    handleOpen(){
      prescriptionManagement.getHeatStabilityByPId(this.prescriptionId).then(result =>{
        this.heatStability = result.data
      });
      prescriptionManagement.getMechanicalStabilityByPId(this.prescriptionId).then(result =>{
        this.mechanicalStability = result.data
      });
      prescriptionManagement.getRadioStabilityByPId(this.prescriptionId).then(result =>{
        this.radioStability = result.data
      });
      prescriptionManagement.getExplosionStabilityByPId(this.prescriptionId).then(result =>{
        this.explosionStability = result.data
      });
    },
    handleClose(){
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addHeatStabilityOtherProperty(){
      this.$set(this.heatStability.otherProperty,this.newHeatStabilityOtherPropertyName,this.newHeatStabilityOtherPropertyValue)
    },
    deleteHeatStabilityOtherProperty(key){
      this.$delete(this.heatStability.otherProperty,key)
    },
    onSubmitHeatStability(){
      prescriptionManagement.updateHeatStability(this.heatStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addMechanicalStabilityOtherProperty(key){
      this.$set(this.mechanicalStability.otherProperty,this.newMechanicalStabilityOtherPropertyName,this.newMechanicalStabilityOtherPropertyValue)
    },
    deleteMechanicalStabilityOtherProperty(){
      this.$delete(this.mechanicalStability.otherProperty,key)
    },
    onSubmitMechanicalStability(){
      prescriptionManagement.updateMechanicalStability(this.mechanicalStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addRadioStabilityOtherProperty(){
      this.$set(this.radioStability.otherProperty,this.newRadioStabilityOtherPropertyName,this.newRadioStabilityOtherPropertyValue)
    },
    deleteRadioStabilityOtherProperty(key){
      this.$delete(this.radioStability.otherProperty,key)
    },
    onSubmitRadioStability(){
      prescriptionManagement.updateRadioStability(this.radioStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addExplosionStabilityOtherProperty(){
      this.$set(this.explosionStability.otherProperty,this.newExplosionStabilityOtherPropertyName,this.newExplosionStabilityOtherPropertyValue)
    },
    deleteExplosionStabilityOtherProperty(key){
      this.$delete(this.explosionStability.otherProperty,key)
    },
    onSubmitExplosionStability(){
      prescriptionManagement.updateExplosionStability(this.explosionStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    cancel(){
      this.dialogFormVisible = false
      this.reset()
    },
    reset(){
      this.heatStability = {
        stabilityId: 0,
        stabilityName: '',
        simpleVieri: '',
        repetitionVieri: '',
        autoignitionTemperature: '',
        openFireSensitivity: '',
        sparkSensitivity: '',
        flashPoint: '',
        ignitionPoint: '',
        explosionLimit: '',
        otherProperty: {}
      };
      this.mechanicalStability = {
        stabilityId: 0,
        stabilityName: '',
        impactSensitivity: 0.0,
        frictionSensitivity: 0.0,
        vibrationSensitivity: 0.0,
        gunshotSensitivity: 0.0,
        otherProperty: {}
      };
      this.radioStability = {
        stabilityId: 0,
        stabilityName: '',
        radiationSensitivity: 0.0,
        otherProperty: {}
      };
      this.explosionStability = {
        stabilityId: 0,
        stabilityName: '',
        impactTest: '',
        ExplosiveClassification: '',
        otherProperty: {}
      };
      this.resetForm('heatStabilityForm');
      this.resetForm('mechanicalStabilityForm');
      this.resetForm('radioStabilityForm');
      this.resetForm('explosionStabilityForm');
    }

  },
}
</script>

<style scoped>

</style>
