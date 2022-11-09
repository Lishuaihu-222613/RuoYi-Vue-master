package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Danger;

import java.util.Collection;

public interface DangerService {

    /**
     * 根据材料获得关联危险项
     * @param materialId
     * @return
     */
    Collection<Danger> getDangersByMaterialId(Long materialId);

    /**
     * 根据ID获得危险项
     * @param dangerId
     * @return
     */
    Danger getDangerById(Long dangerId);

    /**
     * 创建危险项
     * @param danger
     * @return
     */
    Danger createDanger(Danger danger);

    /**
     * 更新危险项
     * @param danger
     * @return
     */
    Danger updateDanger(Danger danger);

    /**
     * 删除危险项
     * @param danger
     */
    void deleteDanger(Danger danger);

    /**
     * 根据ID删除危险项
     * @param dangerId
     */
    void deleteDangerById(Long dangerId);

}
