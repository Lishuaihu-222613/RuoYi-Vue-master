package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.PrescriptionRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.service.KnowledgeService.Prescription.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public PrescriptionInterface createPrescription(Prescription prescription) {
        return (PrescriptionInterface) prescriptionRepository.save(prescription);
    }

    @Override
    public PrescriptionInterface updatePrescription(Prescription prescription) {
        return (PrescriptionInterface) prescriptionRepository.save(prescription);
    }

    @Override
    public PrescriptionInterface getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findPrescriptionInterfaceById(prescriptionId).get();
    }

    @Override
    public List<PrescriptionInterface> getAllPrescriptions() {
        return null;
    }

    @Override
    public List<PrescriptionInterface> getPrescriptionsByLabel(String dynamicLabel) {
        return null;
    }

    @Override
    public PrescriptionInterface getPrescriptionInterfaceById(Long prescriptionId) {
        return null;
    }

    @Override
    public PrescriptionInterface getPrescriptionInterfaceByName(String prescriptionName) {
        return null;
    }

    @Override
    public void deletePrescriptionById(Long prescriptionId) {

    }

    @Override
    public void deletePrescription(Prescription prescription) {

    }
}
