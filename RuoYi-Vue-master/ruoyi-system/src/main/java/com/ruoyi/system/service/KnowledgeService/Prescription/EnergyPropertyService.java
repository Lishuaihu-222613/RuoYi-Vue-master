package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.EnergyProperty;

import java.util.Collection;

public interface EnergyPropertyService {

    /**
     * 创建能量性能节点
     * @param Property
     * @return
     */
    EnergyProperty createEnergyProperty(EnergyProperty Property);

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
     * 删除节点
     * @param property
     */
    void deleteEnergyProperty(EnergyProperty property);

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
}
