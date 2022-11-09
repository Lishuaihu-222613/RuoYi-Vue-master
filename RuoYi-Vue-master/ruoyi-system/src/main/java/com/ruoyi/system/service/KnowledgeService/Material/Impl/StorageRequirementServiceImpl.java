package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.StorageRequirementRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.StorageRequirement;
import com.ruoyi.system.service.KnowledgeService.Material.StorageRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StorageRequirementServiceImpl implements StorageRequirementService {

    @Autowired
    private StorageRequirementRepository storageRequirementRepository;

    @Override
    public StorageRequirement createStorageRequirement(StorageRequirement requirement) {
        return storageRequirementRepository.save(requirement);
    }

    @Override
    public Collection<StorageRequirement> getStorageRequirementsByMaterialId(Long materialId) {
        return storageRequirementRepository.findStorageRequirementsByMaterialId(materialId);
    }

    @Override
    public Collection<StorageRequirement> getStorageRequirementsByName(String requirementName) {
        return storageRequirementRepository.findByRequirementName(requirementName);
    }

    @Override
    public StorageRequirement getStorageRequirementById(Long requirementId) {
        return storageRequirementRepository.findById(requirementId).get();
    }

    @Override
    public StorageRequirement updateStorageRequirement(StorageRequirement requirement) {
        return storageRequirementRepository.save(requirement);
    }

    @Override
    public void deleteStorageRequirement(StorageRequirement requirement) {
        storageRequirementRepository.delete(requirement);
    }

    @Override
    public void deleteStorageRequirementById(Long requirementId) {
        storageRequirementRepository.deleteById(requirementId);
    }
}
