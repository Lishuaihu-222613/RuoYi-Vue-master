package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.StorageRequirement;

import java.util.Collection;

public interface StorageRequirementService {

    /**
     * 创建存储要求
     * @param requirement
     * @return
     */
    StorageRequirement createStorageRequirement(StorageRequirement requirement);

    /**
     * 获得材料关联存储要求
     * @param materialId
     * @return
     */
    Collection<StorageRequirement> getStorageRequirementsByMaterialId(Long materialId);

    /**
     * 根据名称获得存储要求
     * @param requirementName
     * @return
     */
    Collection<StorageRequirement> getStorageRequirementsByName(String requirementName);

    /**
     * 根据Id获得存储要求
     * @param requirementId
     * @return
     */
    StorageRequirement getStorageRequirementById(Long requirementId);

    /**
     * 更新存储要求
     * @param requirement
     * @return
     */
    StorageRequirement updateStorageRequirement(StorageRequirement requirement);

    /**
     * 删除存储要求
     * @param requirement
     */
    void deleteStorageRequirement(StorageRequirement requirement);

    /**
     * 根据Id删除存储要求
     * @param requirementId
     */
    void deleteStorageRequirementById(Long requirementId);

}
