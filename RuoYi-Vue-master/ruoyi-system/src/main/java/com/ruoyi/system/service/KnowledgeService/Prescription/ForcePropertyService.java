package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.ForceProperty;

import java.util.Collection;

public interface ForcePropertyService {

    /**
     * 创捷力学性能节点
     * @param property
     * @return
     */
    ForceProperty createForceProperty(ForceProperty property);

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
    Collection<ForceProperty> getForcePropertyByPrescriptionId(Long prescriptionId);

    /**
     * 删除节点
     * @param property
     */
    void deleteForceProperty(ForceProperty property);

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
}
