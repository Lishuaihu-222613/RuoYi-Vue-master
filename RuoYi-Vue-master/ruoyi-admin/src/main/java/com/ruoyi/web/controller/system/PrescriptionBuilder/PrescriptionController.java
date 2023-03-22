package com.ruoyi.web.controller.system.PrescriptionBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo.*;
import com.ruoyi.system.service.KnowledgeService.Prescription.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController extends BaseController {

    @Resource
    private PrescriptionService prescriptionService;

    @ResponseBody
    @GetMapping ("/getAllPrescriptionsByType/{dynamicLabel}")
    public R<List<PrescriptionInterface>> getAllPrescriptionsByType(@PathVariable String dynamicLabel) {
        try{
            List<PrescriptionInterface> prescriptions = prescriptionService.getPrescriptionsByLabel(dynamicLabel);
            System.out.println(prescriptions);
            return R.success(prescriptions);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPrescription")
    public R<Prescription> createPrescription(@RequestBody Prescription prescription) {
        try{
            Prescription newPrescription = prescriptionService.createPrescription(prescription);
            System.out.println(newPrescription);
            return R.success(newPrescription);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updatePrescription")
    public R<Prescription> updatePrescription(@RequestBody Prescription prescription) {
        try{
            Prescription newPrescription = prescriptionService.updatePrescription(prescription);
            System.out.println(newPrescription);
            return R.success(newPrescription);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getPrescriptionById/{prescriptionId}")
    public R<PrescriptionInterface> getPrescriptionInterfaceById(@PathVariable Long prescriptionId) {
        try{
            PrescriptionInterface prescription = prescriptionService.getPrescriptionInterfaceById(prescriptionId);
            System.out.println(prescription);
            return R.success(prescription);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getPrescriptionById/{prescriptionId}")
    public R<Prescription> getPrescriptionById(@PathVariable Long prescriptionId) {
        try{
            Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
            System.out.println(prescription);
            return R.success(prescription);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getAllPrescriptions")
    public R<List<PrescriptionInterface>> getAllPrescriptions() {
        try{
            List<Prescription> allPrescriptions = prescriptionService.getAllPrescriptions();
            System.out.println(allPrescriptions);
            return R.success(allPrescriptions);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getPrescriptionInterfaceByName/{prescriptionName}")
    public R<PrescriptionInterface> getPrescriptionInterfaceByName(@PathVariable String prescriptionName) {
        try{
            List<PrescriptionInterface> prescriptionInterfaces = prescriptionService.getPrescriptionInterfaceByName(prescriptionName);
            System.out.println(prescriptionInterfaces);
            return R.success(prescriptionInterfaces);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deletePrescriptionById/{prescriptionId}")
    public R<String> deletePrescriptionById(@PathVariable Long prescriptionId) {
        try{
            prescriptionService.deletePrescriptionById(prescriptionId);
            return R.success("删除"+prescriptionId+"的装药配方");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createCombustionProperty")
    public R<CombustionProperty> createCombustionProperty(@RequestBody PrescriptionAndCombustion PA) {
        try{
            CombustionProperty combustionProperty = prescriptionService.createCombustionProperty(PA.getPrescriptionId(), PA.getProperty());
            System.out.println(combustionProperty);
            return R.success(combustionProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateCombustionProperty")
    public R<CombustionProperty> updateCombustionProperty(@RequestBody CombustionProperty property) {
        try{
            CombustionProperty newProperty = prescriptionService.updateCombustionProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getCombustionPropertyById/{propertyId}")
    public R<CombustionProperty> getCombustionPropertyById(@PathVariable Long propertyId) {
        try{
            CombustionProperty property = prescriptionService.getCombustionPropertyById(propertyId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getCombustionPropertyByPrescriptionId/{prescriptionId}")
    public R<CombustionProperty> getCombustionPropertyByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            CombustionProperty property = prescriptionService.getCombustionPropertyByPrescriptionId(prescriptionId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteCombustionPropertyById/{propertyId}")
    public R<CombustionProperty> deleteCombustionPropertyById(@PathVariable Long propertyId) {
        try{
            prescriptionService.deleteCombustionPropertyById(propertyId);
            return R.success("删除"+propertyId+"的燃烧性能节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getCombustionPropertyByName/{propertyName}")
    public R<List<CombustionProperty>> getCombustionPropertyByName(@PathVariable String propertyName) {
        try{
            Collection<CombustionProperty> properties = prescriptionService.getCombustionPropertyByName(propertyName);
            return R.success(new ArrayList<>(properties));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createEnergyProperty")
    public R<EnergyProperty> createEnergyProperty(@RequestBody PrescriptionAndEnergy PA) {
        try{
            EnergyProperty energyProperty = prescriptionService.createEnergyProperty(PA.getPrescriptionId(), PA.getProperty());
            System.out.println(energyProperty);
            return R.success(energyProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateEnergyProperty")
    public R<EnergyProperty> updateEnergyProperty(@RequestBody EnergyProperty property) {
        try{
            EnergyProperty newProperty = prescriptionService.updateEnergyProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getEnergyPropertyById/{propertyId}")
    public R<EnergyProperty> getEnergyPropertyById(@PathVariable Long propertyId) {
        try{
            EnergyProperty property = prescriptionService.getEnergyPropertyById(propertyId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getEnergyPropertyByPrescriptionId/{prescriptionId}")
    public R<EnergyProperty> getEnergyPropertyByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            EnergyProperty property = prescriptionService.getEnergyPropertyByPrescriptionId(prescriptionId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteEnergyPropertyById/{propertyId}")
    public R<EnergyProperty> deleteEnergyPropertyById(@PathVariable Long propertyId) {
        try{
            prescriptionService.deleteEnergyPropertyById(propertyId);
            return R.success("删除"+propertyId+"的能量性能节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getEnergyPropertyByName/{propertyName}")
    public R<List<EnergyProperty>> getEnergyPropertyByName(@PathVariable String propertyName) {
        try{
            Collection<EnergyProperty> properties = prescriptionService.getEnergyPropertyByName(propertyName);
            return R.success(new ArrayList<>(properties));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createForceProperty")
    public R<ForceProperty> createForceProperty(@RequestBody PrescriptionAndForce PA) {
        try{
            ForceProperty forceProperty = prescriptionService.createForceProperty(PA.getPrescriptionId(), PA.getProperty());
            System.out.println(forceProperty);
            return R.success(forceProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateForceProperty")
    public R<ForceProperty> updateForceProperty(@RequestBody ForceProperty property) {
        try{
            ForceProperty newProperty = prescriptionService.updateForceProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getForcePropertyById/{propertyId}")
    public R<ForceProperty> getForcePropertyById(@PathVariable Long propertyId) {
        try{
            ForceProperty property = prescriptionService.getForcePropertyById(propertyId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getForcePropertyByPrescriptionId/{prescriptionId}")
    public R<ForceProperty> getForcePropertyByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            ForceProperty property = prescriptionService.getForcePropertyByPrescriptionId(prescriptionId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteForcePropertyById/{propertyId}")
    public R<ForceProperty> deleteForcePropertyById(@PathVariable Long propertyId) {
        try{
            prescriptionService.deleteForcePropertyById(propertyId);
            return R.success("删除"+propertyId+"的力学性能节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getForcePropertyByName/{propertyName}")
    public R<List<ForceProperty>> getForcePropertyByName(@PathVariable String propertyName) {
        try{
            Collection<ForceProperty> properties = prescriptionService.getForcePropertyByName(propertyName);
            return R.success(new ArrayList<>(properties));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createStorageProperty")
    public R<StorageProperty> createStorageProperty(@RequestBody PrescriptionAndStorage PA) {
        try{
            StorageProperty storageProperty = prescriptionService.createStorageProperty(PA.getPrescriptionId(), PA.getProperty());
            System.out.println(storageProperty);
            return R.success(storageProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateStorageProperty")
    public R<StorageProperty> updateStorageProperty(@RequestBody StorageProperty property) {
        try{
            StorageProperty newProperty = prescriptionService.updateStorageProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getStoragePropertyById/{propertyId}")
    public R<StorageProperty> getStoragePropertyById(@PathVariable Long propertyId) {
        try{
            StorageProperty property = prescriptionService.getStoragePropertyById(propertyId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getStoragePropertyByPrescriptionId/{prescriptionId}")
    public R<StorageProperty> getStoragePropertyByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            StorageProperty property = prescriptionService.getStoragePropertyByPrescriptionId(prescriptionId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteStoragePropertyById/{propertyId}")
    public R<StorageProperty> deleteStoragePropertyById(@PathVariable Long propertyId) {
        try{
            prescriptionService.deleteStoragePropertyById(propertyId);
            return R.success("删除"+propertyId+"的存储性能节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getStoragePropertyByName/{propertyName}")
    public R<List<StorageProperty>> getStoragePropertyByName(@PathVariable String propertyName) {
        try{
            Collection<StorageProperty> properties = prescriptionService.getStoragePropertyByName(propertyName);
            return R.success(new ArrayList<>(properties));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createOxidantRatio")
    public R<OxidantRatio> createOxidantRatio(@RequestBody PrescriptionAndOxidant PA) {
        try{
            OxidantRatio oxidantRatio = prescriptionService.createOxidantRatio(PA.getPrescriptionId(), PA.getProperty());
            System.out.println(oxidantRatio);
            return R.success(oxidantRatio);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateOxidantRatio")
    public R<OxidantRatio> updateOxidantRatio(@RequestBody OxidantRatio property) {
        try{
            OxidantRatio newProperty = prescriptionService.updateOxidantRatio(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getOxidantRatioById/{propertyId}")
    public R<OxidantRatio> getOxidantRatioById(@PathVariable Long propertyId) {
        try{
            OxidantRatio property = prescriptionService.getOxidantRatioById(propertyId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getOxidantRatioByPrescriptionId/{prescriptionId}")
    public R<OxidantRatio> getOxidantRatioByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            OxidantRatio property = prescriptionService.getOxidantRatioByPrescriptionId(prescriptionId);
            return R.success(property);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteOxidantRatioById/{propertyId}")
    public R<OxidantRatio> deleteOxidantRatioById(@PathVariable Long propertyId) {
        try{
            prescriptionService.deleteOxidantRatioById(propertyId);
            return R.success("删除"+propertyId+"的氧燃比节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getOxidantRatioByName/{propertyName}")
    public R<List<OxidantRatio>> getOxidantRatioByName(@PathVariable String propertyName) {
        try{
            Collection<OxidantRatio> properties = prescriptionService.getOxidantRatioByName(propertyName);
            return R.success(new ArrayList<>(properties));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createExplosionStability")
    public R<ExplosionStability> createExplosionStability(@RequestBody PrescriptionAndExplosion PA) {
        try{
            ExplosionStability explosionStability = prescriptionService.createExplosionStability(PA.getPrescriptionId(), PA.getStability());
            System.out.println(explosionStability);
            return R.success(explosionStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateExplosionStability")
    public R<ExplosionStability> updateExplosionStability(@RequestBody ExplosionStability stability) {
        try{
            ExplosionStability newStability = prescriptionService.updateExplosionStability(stability);
            System.out.println(newStability);
            return R.success(newStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getExplosionStabilityById/{stabilityId}")
    public R<ExplosionStability> getExplosionStabilityById(@PathVariable Long stabilityId) {
        try{
            ExplosionStability stability = prescriptionService.getExplosionStabilityById(stabilityId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getExplosionStabilityByPrescriptionId/{prescriptionId}")
    public R<ExplosionStability> getExplosionStabilityByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            ExplosionStability stability = prescriptionService.getExplosionStabilityByPrescriptionId(prescriptionId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteExplosionStabilityById/{stabilityId}")
    public R<ExplosionStability> deleteExplosionStabilityById(@PathVariable Long stabilityId) {
        try{
            prescriptionService.deleteExplosionStabilityById(stabilityId);
            return R.success("删除"+stabilityId+"的爆炸稳定性节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getExplosionStabilityByName/{stabilityName}")
    public R<List<ExplosionStability>> getExplosionStabilityByName(@PathVariable String stabilityName) {
        try{
            Collection<ExplosionStability> stabilitys = prescriptionService.getExplosionStabilityByName(stabilityName);
            return R.success(new ArrayList<>(stabilitys));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createHeatStability")
    public R<HeatStability> createHeatStability(@RequestBody PrescriptionAndHeat PA) {
        try{
            HeatStability heatStability = prescriptionService.createHeatStability(PA.getPrescriptionId(), PA.getStability());
            System.out.println(heatStability);
            return R.success(heatStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateHeatStability")
    public R<HeatStability> updateHeatStability(@RequestBody HeatStability stability) {
        try{
            HeatStability newStability = prescriptionService.updateHeatStability(stability);
            System.out.println(newStability);
            return R.success(newStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getHeatStabilityById/{stabilityId}")
    public R<HeatStability> getHeatStabilityById(@PathVariable Long stabilityId) {
        try{
            HeatStability stability = prescriptionService.getHeatStabilityById(stabilityId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getHeatStabilityByPrescriptionId/{prescriptionId}")
    public R<HeatStability> getHeatStabilityByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            HeatStability stability = prescriptionService.getHeatStabilityByPrescriptionId(prescriptionId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteHeatStabilityById/{stabilityId}")
    public R<HeatStability> deleteHeatStabilityById(@PathVariable Long stabilityId) {
        try{
            prescriptionService.deleteHeatStabilityById(stabilityId);
            return R.success("删除"+stabilityId+"的热稳定性节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getHeatStabilityByName/{stabilityName}")
    public R<List<HeatStability>> getHeatStabilityByName(@PathVariable String stabilityName) {
        try{
            Collection<HeatStability> stabilitys = prescriptionService.getHeatStabilityByName(stabilityName);
            return R.success(new ArrayList<>(stabilitys));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createMechanicalStability")
    public R<MechanicalStability> createMechanicalStability(@RequestBody PrescriptionAndMechanical PA) {
        try{
            MechanicalStability mechanicalStability = prescriptionService.createMechanicalStability(PA.getPrescriptionId(), PA.getStability());
            System.out.println(mechanicalStability);
            return R.success(mechanicalStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateMechanicalStability")
    public R<MechanicalStability> updateMechanicalStability(@RequestBody MechanicalStability stability) {
        try{
            MechanicalStability newStability = prescriptionService.updateMechanicalStability(stability);
            System.out.println(newStability);
            return R.success(newStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getMechanicalStabilityById/{stabilityId}")
    public R<MechanicalStability> getMechanicalStabilityById(@PathVariable Long stabilityId) {
        try{
            MechanicalStability stability = prescriptionService.getMechanicalStabilityById(stabilityId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getMechanicalStabilityByPrescriptionId/{prescriptionId}")
    public R<MechanicalStability> getMechanicalStabilityByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            MechanicalStability stability = prescriptionService.getMechanicalStabilityByPrescriptionId(prescriptionId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteMechanicalStabilityById/{stabilityId}")
    public R<MechanicalStability> deleteMechanicalStabilityById(@PathVariable Long stabilityId) {
        try{
            prescriptionService.deleteMechanicalStabilityById(stabilityId);
            return R.success("删除"+stabilityId+"的热稳定性节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getMechanicalStabilityByName/{stabilityName}")
    public R<List<MechanicalStability>> getMechanicalStabilityByName(@PathVariable String stabilityName) {
        try{
            Collection<MechanicalStability> stabilitys = prescriptionService.getMechanicalStabilityByName(stabilityName);
            return R.success(new ArrayList<>(stabilitys));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createRadioStability")
    public R<RadioStability> createRadioStability(@RequestBody PrescriptionAndRadio PA) {
        try{
            RadioStability radioStability = prescriptionService.createRadioStability(PA.getPrescriptionId(), PA.getStability());
            System.out.println(radioStability);
            return R.success(radioStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateRadioStability")
    public R<RadioStability> updateRadioStability(@RequestBody RadioStability stability) {
        try{
            RadioStability newStability = prescriptionService.updateRadioStability(stability);
            System.out.println(newStability);
            return R.success(newStability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getRadioStabilityById/{stabilityId}")
    public R<RadioStability> getRadioStabilityById(@PathVariable Long stabilityId) {
        try{
            RadioStability stability = prescriptionService.getRadioStabilityById(stabilityId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getRadioStabilityByPrescriptionId/{prescriptionId}")
    public R<RadioStability> getRadioStabilityByPrescriptionId(@PathVariable Long prescriptionId) {
        try{
            RadioStability stability = prescriptionService.getRadioStabilityByPrescriptionId(prescriptionId);
            return R.success(stability);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/deleteRadioStabilityById/{stabilityId}")
    public R<RadioStability> deleteRadioStabilityById(@PathVariable Long stabilityId) {
        try{
            prescriptionService.deleteRadioStabilityById(stabilityId);
            return R.success("删除"+stabilityId+"的放射稳定性节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getRadioStabilityByName/{stabilityName}")
    public R<List<RadioStability>> getRadioStabilityByName(@PathVariable String stabilityName) {
        try{
            Collection<RadioStability> stabilitys = prescriptionService.getRadioStabilityByName(stabilityName);
            return R.success(new ArrayList<>(stabilitys));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getAllMaterialElementsByProscriptionId/{proscriptionId}")
    public R<List<hasMaterialElement>> getAllMaterialElementsByProscriptionId(@PathVariable Long proscriptionId) {
        try{
            Set<hasMaterialElement> elements = prescriptionService.getAllMaterialElementsByProscriptionId(proscriptionId);
            return R.success(new ArrayList<>(elements));
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyAllMaterialElementsByProscriptionId")
    public R<List<hasMaterialElement>> modifyAllMaterialElementsByProscriptionId(@RequestBody PrescriptionAndElement PE) {
        try{
            Set<hasMaterialElement> newMaterialElements = prescriptionService.modifyAllMaterialElementsByProscriptionId(PE.getPrescriptionId(), PE.getElements());
            System.out.println(newMaterialElements);
            return R.success(newMaterialElements);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addMaterialElementForProscription")
    public R<hasMaterialElement> addMaterialElementForProscription(@RequestBody PrescriptionAndAElement PE) {
        try{
            hasMaterialElement hasMaterialElement = prescriptionService.addMaterialElementForProscription(PE.getPrescriptionId(), PE.getElement());
            System.out.println(hasMaterialElement);
            return R.success(hasMaterialElement);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    
    
}
