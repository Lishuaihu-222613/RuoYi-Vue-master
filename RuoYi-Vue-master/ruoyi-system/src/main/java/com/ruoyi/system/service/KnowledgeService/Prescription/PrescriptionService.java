package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;

import java.util.List;

public interface PrescriptionService {

    /**
     *
     * @param prescription
     * @return
     */
    PrescriptionInterface createPrescription(Prescription prescription);

    /**
     *
     * @param prescription
     * @return
     */
    PrescriptionInterface updatePrescription(Prescription prescription);

    /**
     * @param prescriptionId
     * @return
     */
    PrescriptionInterface getPrescriptionById(Long prescriptionId);

    /**
     *
     * @return
     */
    List<PrescriptionInterface> getAllPrescriptions();

    /**
     *
     * @param dynamicLabel
     * @return
     */
    List<PrescriptionInterface> getPrescriptionsByLabel(String dynamicLabel);

    /**
     *
     * @param prescriptionId
     * @return
     */
    PrescriptionInterface getPrescriptionInterfaceById(Long prescriptionId);

    /**
     *
     * @param prescriptionName
     * @return
     */
    PrescriptionInterface getPrescriptionInterfaceByName(String prescriptionName);

    /**
     *
     * @param prescriptionId
     */
    void deletePrescriptionById(Long prescriptionId);

    /**
     *
     * @param prescription
     */
    void deletePrescription(Prescription prescription);
}
