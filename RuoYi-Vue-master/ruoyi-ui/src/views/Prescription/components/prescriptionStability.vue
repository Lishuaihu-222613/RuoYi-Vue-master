<template>
  <el-dialog :visible.sync="dialogFormVisible" title="相关敏感性编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="热敏感性">
        <el-form ref="heatStabilityForm" :model="heatStability">
          <el-form-item :label-width="formLabelWidth" label="敏感性名称">
            <el-input v-model="heatStability.stabilityName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="维也里简单法">
            <el-input v-model="heatStability.simpleVieri"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="维也里重复法">
            <el-input v-model="heatStability.repetitionVieri"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="自燃温度">
            <el-input v-model="heatStability.autoignitionTemperature"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="明火感度">
            <el-input v-model="heatStability.openFireSensitivity"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="电火花感度">
            <el-input v-model="heatStability.sparkSensitivity"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="闪点">
            <el-input v-model="heatStability.flashPoint"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃点">
            <el-input v-model="heatStability.ignitionPoint"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="爆炸极限">
            <el-input v-model="heatStability.explosionLimit"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addHeatStabilityOtherProperty"/>
            <el-row v-for="(value, key) in heatStability.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteHeatStabilityOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitHeatStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="机械敏感性">
        <el-form ref="mechanicalStabilityForm" :model="mechanicalStability">
          <el-form-item :label-width="formLabelWidth" label="敏感性名称">
            <el-input v-model="mechanicalStability.stabilityName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="冲击感度">
            <el-input-number v-model="mechanicalStability.impactSensitivity" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="摩擦感度">
            <el-input-number v-model="mechanicalStability.frictionSensitivity" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="震动感度">
            <el-input-number v-model="mechanicalStability.vibrationSensitivity" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="枪击感度">
            <el-input-number v-model="mechanicalStability.vibrationSensitivity" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addMechanicalStabilityOtherProperty"/>
            <el-row v-for="(value, key) in mechanicalStability.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteMechanicalStabilityOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitMechanicalStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="辐射敏感度">
        <el-form ref="radioStabilityForm" :model="radioStability">
          <el-form-item :label-width="formLabelWidth" label="敏感性名称">
            <el-input v-model="radioStability.stabilityName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="辐射感度">
            <el-input-number v-model="radioStability.radiationSensitivity" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addRadioStabilityOtherProperty"/>
            <el-row v-for="(value, key) in radioStability.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteRadioStabilityOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitRadioStability">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="爆炸敏感性">
        <el-form ref="explosionStabilityForm" :model="explosionStability">
          <el-form-item :label-width="formLabelWidth" label="敏感性名称">
            <el-input v-model="explosionStability.stabilityName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="冲击试验">
            <el-input v-model="explosionStability.impactTest"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="炸药分级">
            <el-input v-model="explosionStability.ExplosiveClassification"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addExplosionStabilityOtherProperty"/>
            <el-row v-for="(value, key) in explosionStability.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteExplosionStabilityOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
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
    selectPrescription: {
      type: Object,
      default: {}
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
          this.Prescription = newVal
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
        curingTime: new Map(),
        otherProperty: new Map()
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
        otherProperty: new Map()
      },
      mechanicalStability: {
        stabilityId: 0,
        stabilityName: '',
        impactSensitivity: 0.0,
        frictionSensitivity: 0.0,
        vibrationSensitivity: 0.0,
        gunshotSensitivity: 0.0,
        otherProperty: new Map()
      },
      radioStability: {
        stabilityId: 0,
        stabilityName: '',
        radiationSensitivity: 0.0,
        otherProperty: new Map()
      },
      explosionStability: {
        stabilityId: 0,
        stabilityName: '',
        impactTest: '',
        ExplosiveClassification: '',
        otherProperty: new Map()
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
      prescriptionId:0,
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
      prescriptionManagement.getHeatStabilityByPId(this.prescriptionId).then(result =>{
        this.radioStability = result.data
      });
      prescriptionManagement.getRadioStabilityByPId(this.prescriptionId).then(result =>{
        this.radioStability = result.data
      });
    },
    handleClose(){
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },
    addHeatStabilityOtherProperty(){
      this.heatStability.otherProperty.set("","")
    },
    deleteHeatStabilityOtherProperty(key){
      this.heatStability.otherProperty.delete(key)
    },
    onSubmitHeatStability(){
      prescriptionManagement.updateHeatStability(this.heatStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addMechanicalStabilityOtherProperty(){
      this.mechanicalStability.otherProperty.set("","")
    },
    deleteMechanicalStabilityOtherProperty(){
      this.mechanicalStability.otherProperty.delete(key)
    },
    onSubmitMechanicalStability(){
      prescriptionManagement.updateMechanicalStability(this.mechanicalStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addRadioStabilityOtherProperty(){
      this.radioStability.otherProperty.set("","")
    },
    deleteRadioStabilityOtherProperty(key){
      this.radioStability.otherProperty.delete(key)
    },
    onSubmitRadioStability(){
      prescriptionManagement.updateRadioStability(this.radioStability).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addExplosionStabilityOtherProperty(){
      this.explosionStability.otherProperty.set("","")
    },
    deleteExplosionStabilityOtherProperty(key){
      this.explosionStability.otherProperty.delete(key)
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
        otherProperty: new Map()
      };
      this.mechanicalStability = {
        stabilityId: 0,
        stabilityName: '',
        impactSensitivity: 0.0,
        frictionSensitivity: 0.0,
        vibrationSensitivity: 0.0,
        gunshotSensitivity: 0.0,
        otherProperty: new Map()
      };
      this.radioStability = {
        stabilityId: 0,
        stabilityName: '',
        radiationSensitivity: 0.0,
        otherProperty: new Map()
      };
      this.explosionStability = {
        stabilityId: 0,
        stabilityName: '',
        impactTest: '',
        ExplosiveClassification: '',
        otherProperty: new Map()
      };
    }

  },
}
</script>

<style scoped>

</style>
