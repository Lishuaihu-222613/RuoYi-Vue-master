package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.ForcePropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.ForceProperty;
import com.ruoyi.system.service.KnowledgeService.Prescription.ForcePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ForcePropertyServiceImpl implements ForcePropertyService {

    @Autowired
    private ForcePropertyRepository forcePropertyRepository;

    @Override
    public ForceProperty createForceProperty(ForceProperty property) {
        return forcePropertyRepository.save(property);
    }

    @Override
    public ForceProperty updateForceProperty(ForceProperty property) {
        return forcePropertyRepository.save(property);
    }

    @Override
    public ForceProperty getForcePropertyById(Long propertyId) {
        return forcePropertyRepository.findById(propertyId).get();
    }

    @Override
    public ForceProperty getForcePropertyByPrescriptionId(Long prescriptionId) {
        return forcePropertyRepository.findForcePropertyByPrescriptionId(prescriptionId).get();
    }

    @Override
    public void deleteForceProperty(ForceProperty property) {
        forcePropertyRepository.delete(property);
    }

    @Override
    public void deleteForcePropertyById(Long propertyId) {
        forcePropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<ForceProperty> getForcePropertyByName(String propertyName) {
        return forcePropertyRepository.findForcePropertyByName(propertyName);
    }
}
