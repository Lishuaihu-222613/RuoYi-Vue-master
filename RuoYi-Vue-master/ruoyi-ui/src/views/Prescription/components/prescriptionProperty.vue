<template>
  <el-dialog :visible.sync="dialogFormVisible" title="相关性质编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="燃烧性能">
        <el-form :model="combustionProperty" ref="combustionPropertyForm">
          <el-form-item :label-width="formLabelWidth" label="性能名称">
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
                <el-button @click="deleteBurningRate(key)"/>
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
                <el-button @click="deleteBRTemperatureSensitivity(key)"/>
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
                <el-button @click="deletePressureTemperatureSensitivity(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addCombustionPropertyOtherProperty" />
            <el-row v-for="(value, key) in combustionProperty.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteCombustionPropertyOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitCombustionProperty">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="能量性能">
        <el-form :model="energyProperty" ref="energyPropertyForm">
          <el-form-item :label-width="formLabelWidth" label="性能名称">
            <el-input v-model="energyProperty.propertyName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="爆热">
            <el-input v-model="energyProperty.explosiveHeat"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="爆温">
            <el-input v-model="energyProperty.explosiveTemperature"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="比容">
            <el-input v-model="energyProperty.specificCapacity"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="比冲">
            <el-button @click="addSpecificImpulse" />
            <el-row v-for="(value, key) in energyProperty.specificImpulse" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteSpecificImpulse(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="特征速度">
            <el-input v-model="energyProperty.characteristicVelocity"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃烧温度">
            <el-input v-model="energyProperty.combustionTemperature"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃烧产物平均分子质量">
            <el-input-number v-model="energyProperty.combustionAverageMolecularMass" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="计算密度">
            <el-input v-model="energyProperty.calculatedDensity"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="火药力">
            <el-input v-model="energyProperty.gunpowderPower"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addEnergyPropertyOtherProperty" />
            <el-row v-for="(value, key) in energyProperty.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteEnergyPropertyOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitEnergyProperty">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="力学性能">
        <el-form :model="forceProperty" ref="forcePropertyForm">
          <el-form-item :label-width="formLabelWidth" label="性能名称">
            <el-input v-model="forceProperty.propertyName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="应力">
            <el-input v-model="forceProperty.stress"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="应变">
            <el-input v-model="forceProperty.strain"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="弹性模量">
            <el-input v-model="forceProperty.elasticModulus"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="料浆黏度">
            <el-button @click="addViscosity" />
            <el-row v-for="(value, key) in forceProperty.Viscosity" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteViscosity(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="抗拉强度">
            <el-button @click="addTensileStrength" />
            <el-row v-for="(value, key) in forceProperty.tensileStrength" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteTensileStrength(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="抗压强度">
            <el-button @click="addCompressiveStrength" />
            <el-row v-for="(value, key) in forceProperty.compressiveStrength" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteCompressiveStrength(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="延伸率">
            <el-button @click="addElongation" />
            <el-row v-for="(value, key) in forceProperty.elongation" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteElongation(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addForcePropertyOtherProperty" />
            <el-row v-for="(value, key) in forceProperty.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteForcePropertyOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitForceProperty">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="氧燃比">
        <el-form :model="oxidantRatio" ref="oxidantRatioForm">
          <el-form-item :label-width="formLabelWidth" label="性能名称">
            <el-input v-model="oxidantRatio.propertyName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="氧燃比">
            <el-input-number v-model="oxidantRatio.oxygenFuelRatio" precision="2"></el-input-number>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="氧系数">
            <el-input v-model="oxidantRatio.oxygenCoefficient"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="氧平衡">
            <el-input v-model="oxidantRatio.oxygenBalance"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="氧含量">
            <el-input v-model="oxidantRatio.oxygenContent"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addOxidantRatioOtherProperty" />
            <el-row v-for="(value, key) in oxidantRatio.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteOxidantRatio0therProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitOxidantRatio">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="贮存性能">
        <el-form :model="storageProperty" ref="storagePropertyForm">
          <el-form-item :label-width="formLabelWidth" label="性能名称">
            <el-input v-model="storageProperty.propertyName"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="预估贮存期">
            <el-input v-model="storageProperty.estimatedStoragePeriod"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="外观变化">
            <el-input v-model="storageProperty.appearanceChange"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="物理性能变化">
            <el-input v-model="storageProperty.physicalPropertyChange"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="化学性能变化">
            <el-input v-model="storageProperty.chemicalPropertyChange"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="力学性能变化">
            <el-input v-model="storageProperty.forcePropertyChange"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="燃烧性能变化">
            <el-input v-model="storageProperty.combustionPropertyChange" precision="2"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="药柱-衬层（绝热层）界面粘结强度变化">
            <el-input v-model="storageProperty.interfacialBondStrengthChange"></el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth" label="其他属性">
            <el-button @click="addStoragePropertyOtherProperty" />
            <el-row v-for="(value, key) in storageProperty.otherProperty" :key="key" :index="key+''">
              <el-col>
                <el-input v-model="key"></el-input>
              </el-col>
              <el-col :span="2" class="line">----</el-col>
              <el-col>
                <el-input v-model="value"></el-input>
              </el-col>
              <el-col>
                <el-button @click="deleteStoragePropertyOtherProperty(key)"/>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="onSubmitStorageProperty">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>

  </el-dialog>
</template>

<script>

import * as prescriptionManagement from '@/api/system/prescriptionManagement'

export default {
  name: 'prescriptionProperty',
  props: {
    selectPrescriptionId: {
      type: Number,
      default: 0
    },
    dialog: {
      type: Boolean,
      default: false
    },
    title:{
      type: String,
      default: ""
    }
  },
  watch: {
    selectPrescriptionId: {
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
    title:{
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
      tabPosition:'left',
      windowTitle:"创建配方",
      prescriptionId:0,
      combustionProperty:{
        propertyId:0,
        propertyName:'',
        BRPressureIndex:0.0,
        burningRate:new Map(),
        BRTemperatureSensitivity:new Map(),
        pressureTemperatureSensitivity:new Map(),
        otherProperty:new Map()
      },
      energyProperty:{
        propertyId:0,
        propertyName:'',
        explosiveHeat:'',
        explosiveTemperature:'',
        specificCapacity:'',
        specificImpulse:new Map(),
        characteristicVelocity:'',
        combustionTemperature:'',
        combustionAverageMolecularMass:0.0,
        calculatedDensity:'',
        gunpowderPower:'',
        otherProperty:new Map()
      },
      forceProperty:{
        propertyId:0,
        propertyName:'',
        stress:'',
        strain:'',
        elasticModulus:'',
        Viscosity:new Map(),
        tensileStrength:new Map(),
        compressiveStrength:new Map(),
        elongation:new Map(),
        otherProperty:new Map(),
      },
      storageProperty:{
        propertyId:0,
        propertyName:'',
        estimatedStoragePeriod:'',
        appearanceChange:'',
        physicalPropertyChange:'',
        chemicalPropertyChange:'',
        forcePropertyChange:'',
        combustionPropertyChange:'',
        interfacialBondStrengthChange:'',
        otherProperty:new Map(),
      },
      oxidantRatio:{
        propertyId:0,
        propertyName:'',
        oxygenFuelRatio:0.0,
        oxygenCoefficient:'',
        oxygenBalance:'',
        oxygenContent:'',
        otherProperty:new Map(),
      },
      dialogFormVisible: false,
      formLabelWidth: '120px',
      inputVisible: false,
      inputValue: '',
    }
  },
  methods: {
    handleOpen() {
      prescriptionManagement.getCombustionPropertyByPId(this.prescriptionId).then(result =>{
        this.combustionProperty = result.data
      });
      prescriptionManagement.getEnergyPropertyByPId(this.prescriptionId).then(result =>{
        this.energyProperty = result.data
      });
      prescriptionManagement.getForcePropertyByPId(this.prescriptionId).then(result =>{
        this.forceProperty = result.data
      });
      prescriptionManagement.getStoragePropertyByPId(this.prescriptionId).then(result =>{
        this.storageProperty = result.data
      });
      prescriptionManagement.getOxidantRatioByPId(this.prescriptionId).then(result =>{
        this.oxidantRatio = result.data
      });
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.combustionProperty = {
        propertyId:0,
        propertyName:'',
        BRPressureIndex:0.0,
        burningRate:new Map(),
        BRTemperatureSensitivity:new Map(),
        pressureTemperatureSensitivity:new Map(),
        otherProperty:new Map()
      };
      this.energyProperty = {
        propertyId:0,
        propertyName:'',
        explosiveHeat:'',
        explosiveTemperature:'',
        specificCapacity:'',
        specificImpulse:new Map(),
        characteristicVelocity:'',
        combustionTemperature:'',
        combustionAverageMolecularMass:0.0,
        calculatedDensity:'',
        gunpowderPower:'',
        otherProperty:new Map()
      };
      this.forceProperty = {
        propertyId:0,
        propertyName:'',
        stress:'',
        strain:'',
        elasticModulus:'',
        Viscosity:new Map(),
        tensileStrength:new Map(),
        compressiveStrength:new Map(),
        elongation:new Map(),
        otherProperty:new Map(),
      };
      this.storageProperty = {
        propertyId:0,
        propertyName:'',
        estimatedStoragePeriod:'',
        appearanceChange:'',
        physicalPropertyChange:'',
        chemicalPropertyChange:'',
        forcePropertyChange:'',
        combustionPropertyChange:'',
        interfacialBondStrengthChange:'',
        otherProperty:new Map(),
      };
      this.oxidantRatio = {
        propertyId:0,
        propertyName:'',
        oxygenFuelRatio:0.0,
        oxygenCoefficient:'',
        oxygenBalance:'',
        oxygenContent:'',
        otherProperty:new Map(),
      };
      this.resetForm('form')
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },

    addBurningRate(){
      this.combustionProperty.burningRate.set("","")
    },
    deleteBurningRate(key){
      this.combustionProperty.burningRate.delete(key)
    },
    addBRTemperatureSensitivity(){
      this.combustionProperty.BRTemperatureSensitivity.set("","")
    },
    deleteBRTemperatureSensitivity(key){
      this.combustionProperty.BRTemperatureSensitivity.delete(key)
    },
    addPressureTemperatureSensitivity(){
      this.combustionProperty.pressureTemperatureSensitivity.set("","")
    },
    deletePressureTemperatureSensitivity(key){
      this.combustionProperty.pressureTemperatureSensitivity.delete(key)
    },
    addCombustionPropertyOtherProperty(){
      this.combustionProperty.otherProperty.set("","")
    },
    deleteCombustionPropertyOtherProperty(){
      this.combustionProperty.otherProperty.delete(key)
    },
    onSubmitCombustionProperty(){
      prescriptionManagement.updateCombustionProperty(this.combustionProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addSpecificImpulse(){
      this.energyProperty.specificImpulse.set("","")
    },
    deleteSpecificImpulse(key){
      this.energyProperty.specificImpulse.delete(key)
    },
    addEnergyPropertyOtherProperty(){
      this.energyProperty.otherProperty.set("","")
    },
    deleteEnergyPropertyOtherProperty(key){
      this.energyProperty.otherProperty.delete(key)
    },
    onSubmitEnergyProperty(){
      prescriptionManagement.updateEnergyProperty(this.energyProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },

    addViscosity(){
      this.forceProperty.Viscosity.set("","")
    },
    deleteViscosity(key){
      this.forceProperty.Viscosity.delete(key)
    },
    addTensileStrength(){
      this.forceProperty.tensileStrength.set("","")
    },
    deleteTensileStrength(key){
      this.forceProperty.tensileStrength.delete(key)
    },
    addCompressiveStrength(){
      this.forceProperty.compressiveStrength.set("","")
    },
    deleteCompressiveStrength(key){
      this.forceProperty.compressiveStrength.delete(key)
    },
    addElongation(){
      this.forceProperty.elongation.set("","")
    },
    deleteElongation(key){
      this.forceProperty.elongation.delete(key)
    },
    addForcePropertyOtherProperty(){
      this.forceProperty.otherProperty.set("","")
    },
    deleteForcePropertyOtherProperty(key){
      this.forceProperty.otherProperty.delete(key)
    },
    onSubmitForceProperty(){
      prescriptionManagement.updateForceProperty(this.forceProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },

    addOxidantRatioOtherProperty(){
      this.oxidantRatio.otherProperty.set("","")
    },
    deleteOxidantRatio0therProperty(key){
      this.oxidantRatio.otherProperty.delete(key)
    },
    onSubmitOxidantRatio(){
      prescriptionManagement.updateOxidantRatio(this.oxidantRatio).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },

    addStoragePropertyOtherProperty(){
      this.storageProperty.otherProperty.set("","")
    },
    deleteStoragePropertyOtherProperty(key){
      this.storageProperty.otherProperty.delete(key)
    },
    onSubmitStorageProperty(){
      prescriptionManagement.updateStorageProperty(this.storageProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    }
  }


}
</script>

<style scoped>

</style>
