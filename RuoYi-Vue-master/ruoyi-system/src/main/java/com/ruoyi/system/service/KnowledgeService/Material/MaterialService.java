package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;

import java.util.List;

public interface MaterialService {

    /**
     * 获得某类材料知识下所有常见材料
     * @param mkId
     * @return
     */
    List<MaterialInterface> getMaterialsByMkId(Long mkId);

    /**
     * 获得所有材料
     * @return
     */
    List<MaterialInterface> getAllMaterials();

    /**
     * 根据类型获得材料
     * @param dynamicLabel
     * @return
     */
    List<MaterialInterface> getMaterialsByType(String dynamicLabel);

    /**
     * 根据Id获得某材料完整信息
     * @param materialId
     * @return
     */
    MaterialInterface getMaterialById(Long materialId);

    /**
     * 创建材料
     * @param material
     * @return
     */
    Material createMaterial(Material material);

    /**
     * 创建单个材料节点
     * @param singleMaterial
     * @return
     */
    Material createSingleMaterial(Material singleMaterial);

    /**
     * 删除材料
     * @param material
     */
    void deleteMaterial(Material material);

    /**
     * 根据ID删除材料
     * @param MaterialId
     */
    void deleteMaterialById(Long MaterialId);

    /**
     * 更新材料
     * @param material
     * @return
     */
    Material updateMaterial(Material material);

}
