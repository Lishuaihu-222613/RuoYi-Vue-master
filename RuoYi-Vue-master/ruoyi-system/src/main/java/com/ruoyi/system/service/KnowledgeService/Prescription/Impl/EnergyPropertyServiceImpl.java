package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.EnergyPropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.EnergyProperty;
import com.ruoyi.system.service.KnowledgeService.Prescription.EnergyPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnergyPropertyServiceImpl implements EnergyPropertyService {

    @Autowired
    private EnergyPropertyRepository energyPropertyRepository;

    @Override
    public EnergyProperty createEnergyProperty(EnergyProperty Property) {
        return energyPropertyRepository.save(Property);
    }

    @Override
    public EnergyProperty updateEnergyProperty(EnergyProperty Property) {
        return energyPropertyRepository.save(Property);
    }

    @Override
    public EnergyProperty getEnergyPropertyById(Long propertyId) {
        return energyPropertyRepository.findById(propertyId).get();
    }

    @Override
    public EnergyProperty getEnergyPropertyByPrescriptionId(Long prescriptionId) {
        return energyPropertyRepository.findEnergyPropertyByPrescription(prescriptionId).get();
    }

    @Override
    public void deleteEnergyProperty(EnergyProperty property) {
        energyPropertyRepository.delete(property);
    }

    @Override
    public void deleteEnergyPropertyById(Long propertyId) {
        energyPropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<EnergyProperty> getEnergyPropertyByName(String propertyName) {
        return energyPropertyRepository.findEnergyPropertyByName(propertyName);
    }
}
