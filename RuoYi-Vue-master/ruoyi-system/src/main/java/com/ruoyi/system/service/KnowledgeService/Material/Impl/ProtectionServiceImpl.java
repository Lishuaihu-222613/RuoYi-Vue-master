package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.ProtectionRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Protection;
import com.ruoyi.system.service.KnowledgeService.Material.ProtectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProtectionServiceImpl implements ProtectionService {

    @Autowired
    private ProtectionRepository protectionRepository;

    @Override
    public Protection createProtection(Protection protection) {
        return protectionRepository.save(protection);
    }

    @Override
    public Collection<Protection> getProtectionsByMaterialId(Long materialId) {
        return protectionRepository.findProtectionsByMaterialId(materialId);
    }

    @Override
    public Collection<Protection> getProtectionsByName(String protectionName) {
        return protectionRepository.findByProtectionName(protectionName);
    }

    @Override
    public Protection getProtectionById(Long protectionId) {
        return protectionRepository.findById(protectionId).get();
    }

    @Override
    public Protection updateProtection(Protection protection) {
        return protectionRepository.save(protection);
    }

    @Override
    public void deleteProtection(Protection protection) {
        protectionRepository.delete(protection);
    }

    @Override
    public void deleteProtectionById(Long protectionId) {
        protectionRepository.deleteById(protectionId);
    }
}
