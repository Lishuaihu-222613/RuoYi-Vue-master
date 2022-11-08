package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.MaterialKnowledgeRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialKnowledgeInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.MaterialKnowledge;
import com.ruoyi.system.service.KnowledgeService.Material.MaterialKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialKnowledgeServiceImpl implements MaterialKnowledgeService {

    @Autowired
    private MaterialKnowledgeRepository materialKnowledgeRepository;

    @Override
    public List<MaterialKnowledge> getAllMaterialKnowledge() {
        return materialKnowledgeRepository.findAll();
    }

    @Override
    public Optional<MaterialKnowledgeInterface> getMaterialKnowledgeById(Long mKid) {
        return materialKnowledgeRepository.findMaterialKnowledgeInterfaceByMKId(mKid);
    }

    @Override
    public MaterialKnowledge createMaterialKnowledge(MaterialKnowledge materialKnowledge) {
        return materialKnowledgeRepository.save(materialKnowledge);
    }

    @Override
    public MaterialKnowledge updateMaterialKnowledge(MaterialKnowledge materialKnowledge) {
        return materialKnowledgeRepository.save(materialKnowledge);
    }

    @Override
    public void deleteMaterialKnowledge(MaterialKnowledge materialKnowledge) {
        materialKnowledgeRepository.delete(materialKnowledge);
    }

    @Override
    public void deleteMaterialKnowledgeById(Long mKid) {
        materialKnowledgeRepository.deleteById(mKid);
    }
}
