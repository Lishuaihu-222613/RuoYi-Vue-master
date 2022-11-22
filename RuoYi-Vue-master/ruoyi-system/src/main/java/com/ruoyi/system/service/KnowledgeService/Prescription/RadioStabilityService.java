package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;

import java.util.Collection;

public interface RadioStabilityService {

    /**
     *
     * @param stability
     * @return
     */
    RadioStability createRadioStability(RadioStability stability);

    /**
     *
     * @param stability
     * @return
     */
    RadioStability updateRadioStability(RadioStability stability);

    /**
     *
     * @param stabilityId
     * @return
     */
    RadioStability getRadioStabilityById(Long stabilityId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    Collection<RadioStability> getRadioStabilityByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param prescriptionName
     * @return
     */
    Collection<RadioStability> getRadioStabilityByName(String prescriptionName);

    /**
     *
     * @param stabilityId
     */
    void deleteRadioStabilityById(Long stabilityId);

    /**
     *
     * @param stability
     */
    void deleteRadioStability(RadioStability stability);
}
