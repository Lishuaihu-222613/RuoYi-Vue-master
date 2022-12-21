package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.PhysicalPropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.PhysicalProperty;
import com.ruoyi.system.service.KnowledgeService.Material.PhysicalPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PhysicalPropertyServiceImpl implements PhysicalPropertyService {

    @Autowired
    private PhysicalPropertyRepository physicalPropertyRepository;

    @Override
    public PhysicalProperty createPhysicalProperty(PhysicalProperty property) {
        return physicalPropertyRepository.save(property);
    }

    @Override
    public Collection<PhysicalProperty> getPhysicalPropertyByMaterialId(Long materialId) {
        return physicalPropertyRepository.findPhysicalPropertiesByMaterialId(materialId);
    }

    @Override
    public Collection<PhysicalProperty> getPhysicalPropertyByName(String propertyName) {
        return physicalPropertyRepository.findPhysicalPropertiesByPropertyName(propertyName);
    }

    @Override
    public PhysicalProperty getPhysicalPropertyById(Long propertyId) {
        return physicalPropertyRepository.findById(propertyId).get();
    }

    @Override
    public PhysicalProperty updatePhysicalProperty(PhysicalProperty property) {
        return physicalPropertyRepository.save(property);
    }

    @Override
    public void deletePhysicalProperty(PhysicalProperty property) {
        physicalPropertyRepository.delete(property);
    }

    @Override
    public void deletePhysicalPropertyById(Long propertyId) {
        physicalPropertyRepository.deleteById(propertyId);
    }
}
