package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ChemicalProperty;

import java.util.Collection;

public interface ChemicalPropertyService {

    /**
     * 创建化学性质节点
     * @param property
     * @return
     */
    ChemicalProperty createChemicalProperty(ChemicalProperty property);

    /**
     * 根据Id查询
     * @param propertyId
     * @return
     */
    ChemicalProperty getChemicalPropertyById(Long propertyId);

    /**
     * 根据材料查询节点
     * @param materialId
     * @return
     */
    Collection<ChemicalProperty> getChemicalPropertyByMaterialId(Long materialId);

    /**
     * 更新化学性质节点
     * @param property
     * @return
     */
    ChemicalProperty updateChemicalProperty(ChemicalProperty property);

    /**
     * 删除节点
     * @param property
     */
    void deleteChemicalProperty(ChemicalProperty property);

    /**
     * 根据Id删除节点
     * @param propertyId
     */
    void deleteChemicalPropertyById(Long propertyId);
}
