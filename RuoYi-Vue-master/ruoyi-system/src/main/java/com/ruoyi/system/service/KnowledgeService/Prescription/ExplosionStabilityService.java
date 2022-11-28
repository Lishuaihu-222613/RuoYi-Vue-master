package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;

import java.util.Collection;

public interface ExplosionStabilityService {

    /**
     *
     * @param stability
     * @return
     */
    ExplosionStability createExplosionStability(ExplosionStability stability);

    /**
     *
     * @param stability
     * @return
     */
    ExplosionStability updateExplosionStability(ExplosionStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    ExplosionStability getExplosionStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    ExplosionStability getExplosionStabilityByPrescription(Long prescriptionId);

    /**
     *
     * @param stabilityName
     * @return
     */
    Collection<ExplosionStability> getExplosionStabilityByName(String stabilityName);

    /**
     *
     * @param stabilityId
     */
    void deleteExplosionStabilityById(Long stabilityId);

    /**
     *
     * @param stability
     */
    void deleteExplosionStability(ExplosionStability stability);

}
