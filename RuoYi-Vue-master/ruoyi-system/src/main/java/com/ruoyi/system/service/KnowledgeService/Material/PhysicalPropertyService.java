package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.PhysicalProperty;

import java.util.Collection;

public interface PhysicalPropertyService {

    /**
     * 创建物理性质节点
     * @param property
     * @return
     */
    PhysicalProperty createPhysicalProperty(PhysicalProperty property);

    /**
     * 根据材料查询物理性质节点
     * @param materialId
     * @return
     */
    Collection<PhysicalProperty> getPhysicalPropertyByMaterialId(Long materialId);

    /**
     * 根据名称查询物理性质节点
     * @param propertyName
     * @return
     */
    Collection<PhysicalProperty> getPhysicalPropertyByName(String propertyName);

    /**
     * 查询物理性质节点
     * @param propertyId
     * @return
     */
    PhysicalProperty getPhysicalPropertyById(Long propertyId);

    /**
     * 更新物理性质节点
     * @param property
     * @return
     */
    PhysicalProperty updatePhysicalProperty(PhysicalProperty property);

    /**
     * 删除节点
     * @param property
     */
    void deletePhysicalProperty(PhysicalProperty property);

    /**
     * 根据ID删除节点
     * @param propertyId
     */
    void deletePhysicalPropertyById(Long propertyId);
}
