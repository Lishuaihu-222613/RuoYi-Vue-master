package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ImpactStability;

import java.util.Collection;

public interface ImpactStabilityService {

    /**
     *
     * @param stability
     * @return
     */
    ImpactStability createImpactStability(ImpactStability stability);

    /**
     *
     * @param stability
     * @return
     */
    ImpactStability updateImpactStability(ImpactStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    ImpactStability getImpactStability(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    Collection<ImpactStability> getImpactStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param stabilityName
     * @return
     */
    Collection<ImpactStability> getImpactStabilityByName(String stabilityName);

    /**
     *
     * @param stabilityId
     */
    void deleteImpactStabilityById(Long stabilityId);

    /**
     *
     * @param stability
     */
    void deleteImpactStability(ImpactStability stability);
}
