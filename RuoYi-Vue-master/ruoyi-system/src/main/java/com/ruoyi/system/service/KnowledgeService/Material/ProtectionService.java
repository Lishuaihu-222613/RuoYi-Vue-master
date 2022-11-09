package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Protection;

import java.util.Collection;

public interface ProtectionService {

    /**
     * 创建安全要求节点
     * @param protection
     * @return
     */
    Protection createProtection(Protection protection);

    /**
     * 检索材料关联安全要求
     * @param materialId
     * @return
     */
    Collection<Protection> getProtectionsByMaterialId(Long materialId);

    /**
     * 根据名称检索
     * @param protectionName
     * @return
     */
    Collection<Protection> getProtectionsByName(String protectionName);

    /**
     * 根据Id检索
     * @param protectionId
     * @return
     */
    Protection getProtectionById(Long protectionId);

    /**
     * 更新
     * @param protection
     * @return
     */
    Protection updateProtection(Protection protection);

    /**
     * 删除
     * @param protection
     */
    void deleteProtection(Protection protection);

    /**
     * 根据Id删除
     * @param protectionId
     */
    void deleteProtectionById(Long protectionId);
}
