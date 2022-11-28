package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.CombustionPropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.CombustionProperty;
import com.ruoyi.system.service.KnowledgeService.Prescription.CombustionPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CombustionPropertyServiceImpl implements CombustionPropertyService {

    @Autowired
    private CombustionPropertyRepository combustionPropertyRepository;

    @Override
    public CombustionProperty createCombustionProperty(CombustionProperty property) {
        return combustionPropertyRepository.save(property);
    }

    @Override
    public CombustionProperty updateCombustionProperty(CombustionProperty property) {
        return combustionPropertyRepository.save(property);
    }

    @Override
    public CombustionProperty getCombustionPropertyById(Long propertyId) {
        return combustionPropertyRepository.findById(propertyId).get();
    }

    @Override
    public CombustionProperty getCombustionPropertyByPrescriptionId(Long prescriptionId) {
        return combustionPropertyRepository.findCombustionPropertyByPrescription(prescriptionId).get();
    }

    @Override
    public void deleteCombustionProperty(CombustionProperty property) {
        combustionPropertyRepository.delete(property);
    }

    @Override
    public void deleteCombustionPropertyById(Long propertyId) {
        combustionPropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<CombustionProperty> getCombustionPropertyByName(String propertyName) {
        return combustionPropertyRepository.findCombustionPropertyByName(propertyName);
    }
}
