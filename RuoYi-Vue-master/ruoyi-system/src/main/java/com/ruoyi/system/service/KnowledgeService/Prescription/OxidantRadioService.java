package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.OxidantRatio;

import java.util.Collection;

public interface OxidantRadioService {

    /**
     *
     * @param oxidantRatio
     * @return
     */
    OxidantRatio createOxidantRatio(OxidantRatio oxidantRatio);

    /**
     *
     * @param oxidantRatio
     * @return
     */
    OxidantRatio updateOxidantRatio(OxidantRatio oxidantRatio);

    /**
     *
     * @param propertyId
     * @return
     */
    OxidantRatio getOxidantRatioById(Long propertyId);

    /**
     *
     * @param prescriptionId
     * @return
     */
    Collection<OxidantRatio> getOxidantRatioByPrescriptionId(Long prescriptionId);

    /**
     *
     * @param oxidantRatio
     */
    void deleteOxidantRatio(OxidantRatio oxidantRatio);

    /**
     *
     * @param propertyId
     */
    void deleteOxidantRatioById(Long propertyId);

    /**
     *
     * @param propertyName
     * @return
     */
    Collection<OxidantRatio> getOxidantRatioByName(String propertyName);
}
