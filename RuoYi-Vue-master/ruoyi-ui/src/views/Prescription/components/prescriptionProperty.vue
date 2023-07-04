<template>
  <el-dialog :visible.sync="dialogFormVisible" title="相关性质编辑" top="50vh" width="50%"
             @closed="handleClose" @open="handleOpen"
  >
    <el-tabs :tab-position="tabPosition">
      <el-tab-pane label="燃烧性能">
        <el-form :model="combustionProperty" ref="combustionPropertyForm">
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="性能名称">
              <el-input v-model="combustionProperty.propertyName"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="燃速">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newBurningRateName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newBurningRateValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addBurningRate">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in combustionProperty.burningRate"
                      :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="combustionProperty.burningRate[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteBurningRate(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="燃速压力指数">
              <el-input-number v-model="combustionProperty.brpressureIndex" :precision="precision" ></el-input-number>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="燃速温度指数">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newBRTemperatureSensitivityName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newBRTemperatureSensitivityValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addBRTemperatureSensitivity">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in combustionProperty.brtemperatureSensitivity" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="combustionProperty.brtemperatureSensitivity[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteBRTemperatureSensitivity(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="压力温度指数">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newPressureTemperatureSensitivityName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newPressureTemperatureSensitivityValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addPressureTemperatureSensitivity">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in combustionProperty.pressureTemperatureSensitivity" :key="key" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="combustionProperty.pressureTemperatureSensitivity[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deletePressureTemperatureSensitivity(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newCombustionPropertyOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newCombustionPropertyOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addCombustionPropertyOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in combustionProperty.otherProperty" :key="name" >
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col>
                  <el-input v-model="combustionProperty.otherProperty[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteCombustionPropertyOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="12">
              <el-form-item>
                <el-button type="primary" @click="onSubmitCombustionProperty">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="能量性能">
        <el-form :model="energyProperty" ref="energyPropertyForm">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="性能名称">
                <el-input v-model="energyProperty.propertyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="比容">
                <el-input v-model="energyProperty.specificCapacity"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="爆热">
                <el-input v-model="energyProperty.explosiveHeat"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="爆温">
                <el-input v-model="energyProperty.explosiveTemperature"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="比冲">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newSpecificImpulseName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newSpecificImpulseValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addSpecificImpulse">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in energyProperty.specificImpulse" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="energyProperty.specificImpulse[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteSpecificImpulse(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="特征速度">
                <el-input v-model="energyProperty.characteristicVelocity"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="燃烧温度">
                <el-input v-model="energyProperty.combustionTemperature"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="燃烧产物平均分子质量">
                <el-input-number v-model="energyProperty.combustionAverageMolecularMass" :precision="precision"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="计算密度">
                <el-input v-model="energyProperty.calculatedDensity"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item :label-width="formLabelWidth" label="火药力">
                <el-input v-model="energyProperty.gunpowderPower"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newEnergyPropertyOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newEnergyPropertyOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addEnergyPropertyOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in energyProperty.otherProperty" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="energyProperty.otherProperty[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click.prevent="deleteEnergyPropertyOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="12">
              <el-form-item>
                <el-button type="primary" @click="onSubmitEnergyProperty">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="力学性能">
        <el-form :model="forceProperty" ref="forcePropertyForm">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="性能名称">
                <el-input v-model="forceProperty.propertyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="应力">
                <el-input v-model="forceProperty.stress"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="应变">
                <el-input v-model="forceProperty.strain"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="弹性模量">
                <el-input v-model="forceProperty.elasticModulus"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="料浆黏度">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newViscosityName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newViscosityValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addViscosity">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in forceProperty.viscosity" :key="name" >
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="forceProperty.viscosity[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteViscosity(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="抗拉强度">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newTensileStrengthName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newTensileStrengthValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addTensileStrength">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in forceProperty.tensileStrength" :key="name">
                <el-col :span="8">
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="forceProperty.tensileStrength[name]"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button @click="deleteTensileStrength(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="抗压强度">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newCompressiveStrengthName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newCompressiveStrengthValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addCompressiveStrength">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in forceProperty.compressiveStrength" :key="name">
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col>
                  <el-input v-model="forceProperty.compressiveStrength[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteCompressiveStrength(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="延伸率">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newElongationName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newElongationValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addElongation">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in forceProperty.elongation" :key="name" >
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col>
                  <el-input v-model="forceProperty.elongation[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteElongation(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newForcePropertyOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newForcePropertyOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addForcePropertyOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in forceProperty.otherProperty" :key="name">
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col>
                  <el-input v-model="forceProperty.otherProperty[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteForcePropertyOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="12">
              <el-form-item>
                <el-button type="primary" @click="onSubmitForceProperty">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="氧燃比">
        <el-form :model="oxidantRatio" ref="oxidantRatioForm">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="性能名称">
                <el-input v-model="oxidantRatio.propertyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="氧燃比">
                <el-input-number v-model="oxidantRatio.oxygenFuelRatio" :precision="precision"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="氧系数">
                <el-input v-model="oxidantRatio.oxygenCoefficient"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="氧平衡">
                <el-input v-model="oxidantRatio.oxygenBalance"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="氧含量">
              <el-input v-model="oxidantRatio.oxygenContent"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newOxidantRatioOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newOxidantRatioOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addOxidantRatioOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in oxidantRatio.otherProperty" :key="name" >
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line">----</el-col>
                <el-col>
                  <el-input v-model="oxidantRatio.otherProperty[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteOxidantRatio0therProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="12">
              <el-button type="primary" @click="onSubmitOxidantRatio">确 定</el-button>
              <el-button @click="cancel">取 消</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="贮存性能">
        <el-form :model="storageProperty" ref="storagePropertyForm">
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="性能名称">
                <el-input v-model="storageProperty.propertyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="预估贮存期">
                <el-input v-model="storageProperty.estimatedStoragePeriod"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="外观变化">
                <el-input v-model="storageProperty.appearanceChange"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="物理性能变化">
                <el-input v-model="storageProperty.physicalPropertyChange"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="化学性能变化">
                <el-input v-model="storageProperty.chemicalPropertyChange"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="力学性能变化">
                <el-input v-model="storageProperty.forcePropertyChange"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="燃烧性能变化">
                <el-input v-model="storageProperty.combustionPropertyChange" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label-width="formLabelWidth" label="界面粘结强度变化">
                <el-input v-model="storageProperty.interfacialBondStrengthChange"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item :label-width="formLabelWidth" label="其他属性">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newStoragePropertyOtherPropertyName" placeholder="名称"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col :span="8">
                  <el-input v-model="newStoragePropertyOtherPropertyValue" placeholder="内容"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="addStoragePropertyOtherProperty">添加</el-button>
                </el-col>
              </el-row>
              <el-row v-for="(value,name,index) in storageProperty.otherProperty" :key="name">
                <el-col>
                  <el-input v-model="name"></el-input>
                </el-col>
                <el-col :span="2" class="line"></el-col>
                <el-col>
                  <el-input v-model="storageProperty.otherProperty[name]"></el-input>
                </el-col>
                <el-col>
                  <el-button @click="deleteStoragePropertyOtherProperty(name)">删除</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="12">
              <el-form-item>
                <el-button type="primary" @click="onSubmitStorageProperty">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
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
      precision:2,
      combustionProperty:{
        propertyId:0,
        propertyName:'',
        brpressureIndex:0.0,
        burningRate:{},
        brtemperatureSensitivity:{},
        pressureTemperatureSensitivity:{},
        otherProperty:{}
      },
      energyProperty:{
        propertyId:0,
        propertyName:'',
        explosiveHeat:'',
        explosiveTemperature:'',
        specificCapacity:'',
        specificImpulse:{},
        characteristicVelocity:'',
        combustionTemperature:'',
        combustionAverageMolecularMass:0.0,
        calculatedDensity:'',
        gunpowderPower:'',
        otherProperty:{}
      },
      forceProperty:{
        propertyId:0,
        propertyName:'',
        stress:'',
        strain:'',
        elasticModulus:'',
        viscosity:{},
        tensileStrength:{},
        compressiveStrength:{},
        elongation:{},
        otherProperty:{},
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
        otherProperty:{},
      },
      oxidantRatio:{
        propertyId:0,
        propertyName:'',
        oxygenFuelRatio:0.0,
        oxygenCoefficient:'',
        oxygenBalance:'',
        oxygenContent:'',
        otherProperty:{},
      },
      dialogFormVisible: false,
      formLabelWidth: '100px',
      inputVisible: false,
      inputValue: '',
      newBurningRateName:'',
      newBurningRateValue:'',
      newBRTemperatureSensitivityName:'',
      newBRTemperatureSensitivityValue:'',
      newPressureTemperatureSensitivityName:'',
      newPressureTemperatureSensitivityValue:'',
      newCombustionPropertyOtherPropertyName:'',
      newCombustionPropertyOtherPropertyValue:'',
      newSpecificImpulseName:'',
      newSpecificImpulseValue:'',
      newEnergyPropertyOtherPropertyName:'',
      newEnergyPropertyOtherPropertyValue:'',
      newViscosityName:'',
      newViscosityValue:'',
      newTensileStrengthName:'',
      newTensileStrengthValue:'',
      newCompressiveStrengthName:'',
      newCompressiveStrengthValue:'',
      newElongationName:'',
      newElongationValue:'',
      newForcePropertyOtherPropertyName:'',
      newForcePropertyOtherPropertyValue:'',
      newOxidantRatioOtherPropertyName:'',
      newOxidantRatioOtherPropertyValue:'',
      newStoragePropertyOtherPropertyName:'',
      newStoragePropertyOtherPropertyValue:''
    }
  },
  methods: {
    handleOpen() {
      prescriptionManagement.getCombustionPropertyByPId(this.prescriptionId).then(result =>{

        console.log(result.data)
        if(result.data.propertyName !== null){

          this.combustionProperty = result.data

        }

      });
      prescriptionManagement.getEnergyPropertyByPId(this.prescriptionId).then(result =>{
        console.log(result.data)
        if(result.data.propertyName !== null){
          this.energyProperty = result.data

        }

      });
      prescriptionManagement.getForcePropertyByPId(this.prescriptionId).then(result =>{
        console.log(result.data)
        if(result.data.propertyName !== null){
          this.forceProperty = result.data

        }

      });
      prescriptionManagement.getStoragePropertyByPId(this.prescriptionId).then(result =>{
        console.log(result.data)

        if(result.data.propertyName !== null){
          this.storageProperty = result.data
        }

      });
      prescriptionManagement.getOxidantRatioByPId(this.prescriptionId).then(result =>{
        console.log(result.data)
        console.log(this.combustionProperty)
        if(result.data.propertyName !== null){
          this.oxidantRatio = result.data
        }

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
        burningRate:{},
        BRTemperatureSensitivity:{},
        pressureTemperatureSensitivity:{},
        otherProperty:{}
      };
      this.energyProperty = {
        propertyId:0,
        propertyName:'',
        explosiveHeat:'',
        explosiveTemperature:'',
        specificCapacity:'',
        specificImpulse:{},
        characteristicVelocity:'',
        combustionTemperature:'',
        combustionAverageMolecularMass:0.0,
        calculatedDensity:'',
        gunpowderPower:'',
        otherProperty:{}
      };
      this.forceProperty = {
        propertyId:0,
        propertyName:'',
        stress:'',
        strain:'',
        elasticModulus:'',
        Viscosity:{},
        tensileStrength:{},
        compressiveStrength:{},
        elongation:{},
        otherProperty:{},
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
        otherProperty:{},
      };
      this.oxidantRatio = {
        propertyId:0,
        propertyName:'',
        oxygenFuelRatio:0.0,
        oxygenCoefficient:'',
        oxygenBalance:'',
        oxygenContent:'',
        otherProperty:{},
      };
      this.resetForm('combustionPropertyForm');
      this.resetForm('energyPropertyForm');
      this.resetForm('forcePropertyForm');
      this.resetForm('oxidantRatioForm');
      this.resetForm('storagePropertyForm');
    },
    handleClose() {
      this.dialogFormVisible = false;
      this.reset();
      this.$emit('closeDialog', null);
      this.$emit('restore', null);
    },

    addBurningRate(){
      this.$set(this.combustionProperty.burningRate,this.newBurningRateName,this.newBurningRateValue)
    },
    deleteBurningRate(key){
      this.$delete(this.combustionProperty.burningRate,key)
    },
    addBRTemperatureSensitivity(){
      this.$set(this.combustionProperty.brtemperatureSensitivity,this.newBRTemperatureSensitivityName,this.newBRTemperatureSensitivityValue)
    },
    deleteBRTemperatureSensitivity(key){
      this.$delete(this.combustionProperty.brtemperatureSensitivity,key)
    },
    addPressureTemperatureSensitivity(){
      this.$set(this.combustionProperty.pressureTemperatureSensitivity,this.newPressureTemperatureSensitivityName,this.newPressureTemperatureSensitivityValue)
    },
    deletePressureTemperatureSensitivity(key){
      this.$delete(this.combustionProperty.pressureTemperatureSensitivity,key)
    },
    addCombustionPropertyOtherProperty(){
      this.$set(this.combustionProperty.otherProperty,this.newCombustionPropertyOtherPropertyName,this.newCombustionPropertyOtherPropertyValue)
    },
    deleteCombustionPropertyOtherProperty(key){
      this.$delete(this.combustionProperty.otherProperty,key)
    },
    onSubmitCombustionProperty(){
      prescriptionManagement.updateCombustionProperty(this.combustionProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addSpecificImpulse(){
      this.$set(this.energyProperty.specificImpulse,this.newSpecificImpulseName,this.newSpecificImpulseValue)
    },
    deleteSpecificImpulse(key){
      this.$delete(this.energyProperty.specificImpulse,key)
    },
    addEnergyPropertyOtherProperty(){
      this.$set(this.energyProperty.otherProperty,this.newEnergyPropertyOtherPropertyName,this.newEnergyPropertyOtherPropertyValue)
    },
    deleteEnergyPropertyOtherProperty(key){
      this.$delete(this.energyProperty.otherProperty,key)
    },
    onSubmitEnergyProperty(){
      prescriptionManagement.updateEnergyProperty(this.energyProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },

    addViscosity(){
      this.$set(this.forceProperty.viscosity,this.newViscosityName,this.newViscosityValue)
    },
    deleteViscosity(key){
      this.$delete(this.forceProperty.viscosity,key)
    },
    addTensileStrength(){
      this.$set(this.forceProperty.tensileStrength,this.newTensileStrengthName,this.newTensileStrengthValue)
    },
    deleteTensileStrength(key){
      this.$delete(this.forceProperty.tensileStrength,key)
    },
    addCompressiveStrength(){
      this.$set(this.forceProperty.compressiveStrength,this.newCompressiveStrengthName,this.newCompressiveStrengthValue)
    },
    deleteCompressiveStrength(key){
      this.$delete(this.forceProperty.compressiveStrength,key)
    },
    addElongation(){
      this.$set(this.forceProperty.elongation,this.newElongationName,this.newElongationValue)
    },
    deleteElongation(key){
      this.$delete(this.forceProperty.elongation,key)
    },
    addForcePropertyOtherProperty(){
      this.$set(this.forceProperty.otherProperty,this.newForcePropertyOtherPropertyName,this.newEnergyPropertyOtherPropertyValue)
    },
    deleteForcePropertyOtherProperty(key){
      this.$delete(this.forceProperty.otherProperty,key)
    },
    onSubmitForceProperty(){
      prescriptionManagement.updateForceProperty(this.forceProperty).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },

    addOxidantRatioOtherProperty(){
      this.$set(this.oxidantRatio.otherProperty,this.newOxidantRatioOtherPropertyName,this.newOxidantRatioOtherPropertyValue)
    },
    deleteOxidantRatio0therProperty(key){
      this.$delete(this.oxidantRatio.otherProperty,key)
    },
    onSubmitOxidantRatio(){
      prescriptionManagement.updateOxidantRatio(this.oxidantRatio).then(result =>{
        if(result.code === 200){
          this.$modal.msgSuccess("修改成功！")
        }
      })
    },
    addStoragePropertyOtherProperty(){
      this.$set(this.storageProperty.otherProperty,this.newStoragePropertyOtherPropertyName,this.newStoragePropertyOtherPropertyValue)
    },
    deleteStoragePropertyOtherProperty(key){
      this.$delete(this.storageProperty.otherProperty,key)
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
