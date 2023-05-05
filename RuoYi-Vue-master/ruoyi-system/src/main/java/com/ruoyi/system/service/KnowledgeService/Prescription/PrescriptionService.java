package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.hasMaterialElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.PrescriptionElements;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo.MaterialAndValue;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PrescriptionService {

    /**
     *
     * @param prescription
     * @return
     */
    Prescription createPrescription(Prescription prescription);

    /**
     *
     * @param prescription
     * @return
     */
    Prescription updatePrescription(Prescription prescription);

    /**
     * @param prescriptionId
     * @return
     */
    Prescription getPrescriptionById(Long prescriptionId);

    /**
     *
     * @return
     */
    Page<Prescription> getAllPrescriptions(Pageable pageable);

    /**
     *
     * @return
     */
    Page<Prescription> getAllPrescriptionsByParams(Example example, Pageable pageable);

    /**
     *
     * @param dynamicLabel
     * @return
     */
    Page<Prescription> getPrescriptionsByLabel(String dynamicLabel, Pageable pageable);

    /**
     *
     * @param prescriptionId
     * @return
     */
    PrescriptionInterface getPrescriptionInterfaceById(Long prescriptionId);

    /**
     *
     * @param prescriptionName
     * @return
     */
    List<PrescriptionInterface> getPrescriptionInterfaceByName(String prescriptionName);

    /**
     *
     * @param prescriptionId
     */
    void deletePrescriptionById(Long prescriptionId);

    /**
     * 创建燃烧性能节点
     * @param property
     * @return
     */
    CombustionProperty createCombustionProperty(Long prescriptionId, CombustionProperty property);

    /**
     * 更新燃烧性能节点
     * @param property
     * @return
     */
    CombustionProperty updateCombustionProperty(CombustionProperty property);

    /**
     * 根据Id获得燃烧性能节点
     * @param propertyId
     * @return
     */
    CombustionProperty getCombustionPropertyById(Long propertyId);

    /**
     * 根据配方Id获得关联燃烧性能节点
     * @param prescriptionId
     * @return
     */
    CombustionProperty getCombustionPropertyByPrescriptionId(Long prescriptionId);

    /**
     * 根据Id删除节点
     * @param propertyId
     */
    void deleteCombustionPropertyById(Long propertyId);

    /**
     * 根据名称查询燃烧性能节点
     * @param propertyName
     * @return
     */
    Collection<CombustionProperty> getCombustionPropertyByName(String propertyName);

    /**
     * 创建能量性能节点
     * @param Property
     * @return
     */
    EnergyProperty createEnergyProperty(Long prescriptionId, EnergyProperty Property);

    /**
     * 更新能量性能节点
     * @param Property
     * @return
     */
    EnergyProperty updateEnergyProperty(EnergyProperty Property);

    /**
     * 根据Id获得能量性能节点
     * @param propertyId
     * @return
     */
    EnergyProperty getEnergyPropertyById(Long propertyId);

    /**
     * 根据配方Id获得关联能量性能节点
     * @param prescriptionId
     * @return
     */
    EnergyProperty getEnergyPropertyByPrescriptionId(Long prescriptionId);

    /**
     * 根据Id删除节点
     * @param propertyId
     */
    void deleteEnergyPropertyById(Long propertyId);

    /**
     * 依据名称查询能量性能节点
     * @param propertyName
     * @return
     */
    Collection<EnergyProperty> getEnergyPropertyByName(String propertyName);

    /**
     *
     * @param stability
     * @return
     */
    ExplosionStability createExplosionStability(Long prescriptionId,ExplosionStability stability);

    /**
     *
     * @param stability
     * @return
     */
    ExplosionStability updateExplosionStability(ExplosionStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    ExplosionStability getExplosionStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    ExplosionStability getExplosionStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param stabilityName
     * @return
     */
    Collection<ExplosionStability> getExplosionStabilityByName(String stabilityName);

    /**
     *
     * @param stabilityId
     */
    void deleteExplosionStabilityById(Long stabilityId);

    /**
     * 创捷力学性能节点
     * @param property
     * @return
     */
    ForceProperty createForceProperty(Long prescriptionId,ForceProperty property);

    /**
     * 更新力学性能节点
     * @param property
     * @return
     */
    ForceProperty updateForceProperty(ForceProperty property);

    /**
     * 根据Id获得力学性能节点
     * @param propertyId
     * @return
     */
    ForceProperty getForcePropertyById(Long propertyId);

    /**
     * 根据配方Id获得力学性能几点
     * @param prescriptionId
     * @return
     */
    ForceProperty getForcePropertyByPrescriptionId(Long prescriptionId);


    /**
     * 根据Id删除力学性能节点
     * @param propertyId
     */
    void deleteForcePropertyById(Long propertyId);

    /**
     * 依据名称查询力学性能节点
     * @param propertyName
     * @return
     */
    Collection<ForceProperty> getForcePropertyByName(String propertyName);

    /**
     *
     * @param stability
     * @return
     */
    HeatStability createHeatStability(Long prescriptionId,HeatStability stability);

    /**
     *
     * @param stability
     * @return
     */
    HeatStability updateHeatStability(HeatStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    HeatStability getHeatStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    HeatStability getHeatStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param stabilityName
     * @return
     */
    Collection<HeatStability> getHeatStabilityByName(String stabilityName);

    /**
     *
     * @param stabilityId
     */
    void deleteHeatStabilityById(Long stabilityId);

    /**
     * 创建机械敏感性节点
     * @param stability
     * @return
     */
    MechanicalStability createMechanicalStability(Long prescriptionId,MechanicalStability stability);

    /**
     * 更新机械敏感性节点
     * @param stability
     * @return
     */
    MechanicalStability updateMechanicalStability(MechanicalStability stability);

    /**
     * 根据Id检索机械敏感性节点
     * @param stabilityId
     * @return
     */
    MechanicalStability getMechanicalStabilityById(Long stabilityId);

    /**
     * 检索配方相关机械敏感性节点
     * @param prescriptionId
     * @return
     */
    MechanicalStability getMechanicalStabilityByPrescriptionId(Long prescriptionId);

    /**
     * 根据名字检索机械敏感性节点
     * @param stabilityName
     * @return
     */
    Collection<MechanicalStability> getMechanicalStabilityByName(String stabilityName);

    /**
     * 删除机械敏感性节点
     * @param stabilityId
     */
    void deleteMechanicalStabilityById(Long stabilityId);

    /**
     *
     * @param oxidantRatio
     * @return
     */
    OxidantRatio createOxidantRatio(Long prescriptionId,OxidantRatio oxidantRatio);

    /**
     *
     * @param oxidantRatio
     * @return
     */
    OxidantRatio updateOxidantRatio(OxidantRatio oxidantRatio);

    /**
     *
     * @param propertyId
     * @return
     */
    OxidantRatio getOxidantRatioById(Long propertyId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    OxidantRatio getOxidantRatioByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param propertyId
     */
    void deleteOxidantRatioById(Long propertyId);

    /**
     *
     * @param propertyName
     * @return
     */
    Collection<OxidantRatio> getOxidantRatioByName(String propertyName);

    /**
     *
     * @param stability
     * @return
     */
    RadioStability createRadioStability(Long prescriptionId,RadioStability stability);

    /**
     *
     * @param stability
     * @return
     */
    RadioStability updateRadioStability(RadioStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    RadioStability getRadioStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    RadioStability getRadioStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param prescriptionName
     * @return
     */
    Collection<RadioStability> getRadioStabilityByName(String prescriptionName);

    /**
     *
     * @param stabilityId
     */
    void deleteRadioStabilityById(Long stabilityId);

    /**
     *
     * @param property
     * @return
     */
    StorageProperty createStorageProperty(Long prescriptionId,StorageProperty property);

    /**
     *
     * @param property
     * @return
     */
    StorageProperty updateStorageProperty(StorageProperty property);

    /**
     *
     * @param prescriptionId
     * @return
     */
    StorageProperty getStoragePropertyByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param propertyId
     * @return
     */
    StorageProperty getStoragePropertyById(Long propertyId);

    /**
     *
     * @param propertyName
     * @return
     */
    Collection<StorageProperty> getStoragePropertyByName(String propertyName);

    /**
     *
     * @param propertyId
     */
    void deleteStoragePropertyById(Long propertyId);

    List<PrescriptionElements> getAllMaterialElementsByProscriptionId(Long proscriptionId);

    List<hasMaterialElement> modifyAllMaterialElementsByProscriptionId(Long proscriptionId,List<MaterialAndValue> materialElements);

    hasMaterialElement addMaterialElementForProscription(Long proscriptionId,hasMaterialElement newMaterialElement);

    void deleteMaterialElementForProscription(Long proscriptionId,Long elementId);
}
