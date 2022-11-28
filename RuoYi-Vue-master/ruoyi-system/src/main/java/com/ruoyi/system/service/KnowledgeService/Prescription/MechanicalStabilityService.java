package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;

import java.util.Collection;

public interface MechanicalStabilityService {

    /**
     * 创建机械敏感性节点
     * @param stability
     * @return
     */
    MechanicalStability createMechanicalStability(MechanicalStability stability);

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
    MechanicalStability getMechanicalStability(Long stabilityId);

    /**
     * 检索配方相关机械敏感性节点
     * @param prescriptionId
     * @return
     */
    Collection<MechanicalStability> getMechanicalStabilityByPrescriptionId(Long prescriptionId);

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
     删除机械敏感性节点
     * @param stability
     */
    void deleteMechanicalStability(MechanicalStability stability);
}
