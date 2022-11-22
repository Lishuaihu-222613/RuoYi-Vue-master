package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.CombustionProperty;

import java.util.Collection;

public interface CombustionPropertyService {

    /**
     * 创建燃烧性能节点
     * @param property
     * @return
     */
    CombustionProperty createCombustionProperty(CombustionProperty property);

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
    Collection<CombustionProperty> getCombustionPropertyByPrescriptionId(Long prescriptionId);

    /**
     * 删除节点
     * @param property
     */
    void deleteCombustionProperty(CombustionProperty property);

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
}
