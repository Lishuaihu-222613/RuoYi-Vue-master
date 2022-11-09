package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.ChemicalPropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ChemicalProperty;
import com.ruoyi.system.service.KnowledgeService.Material.ChemicalPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChemicalPropertyServiceImpl implements ChemicalPropertyService {

    @Autowired
    private ChemicalPropertyRepository chemicalPropertyRepository;
    @Override
    public ChemicalProperty createChemicalProperty(ChemicalProperty property) {
        return chemicalPropertyRepository.save(property);
    }

    @Override
    public ChemicalProperty getChemicalPropertyById(Long propertyId) {
        return chemicalPropertyRepository.findById(propertyId).get();
    }

    @Override
    public Collection<ChemicalProperty> getChemicalPropertyByMaterialId(Long materialId) {
        return chemicalPropertyRepository.findChemicalPropertiesByMaterialId(materialId);
    }

    @Override
    public ChemicalProperty updateChemicalProperty(ChemicalProperty property) {
        return chemicalPropertyRepository.save(property);
    }

    @Override
    public void deleteChemicalProperty(ChemicalProperty property) {
        chemicalPropertyRepository.delete(property);
    }

    @Override
    public void deleteChemicalPropertyById(Long propertyId) {
        chemicalPropertyRepository.deleteById(propertyId);
    }
}
