package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;

import java.util.Collection;

public interface HeatStabilityService {

    /**
     *
     * @param stability
     * @return
     */
    HeatStability createHeatStability(HeatStability stability);

    /**
     *
     * @param stability
     * @return
     */
    HeatStability updateHeatStability(HeatStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    HeatStability getHeatStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    Collection<HeatStability> getHeatStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param stabilityName
     * @return
     */
    Collection<HeatStability> getHeatStabilityByName(String stabilityName);

    /**
     *
     * @param stabilityId
     */
    void deleteHeatStabilityById(Long stabilityId);

    /**
     *
     * @param stability
     */
    void deleteHeatStability(HeatStability stability);
}
