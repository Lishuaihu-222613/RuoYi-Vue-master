package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.ProduceMethodRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ProduceMethod;
import com.ruoyi.system.service.KnowledgeService.Material.ProduceMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class ProduceMethodServiceImpl implements ProduceMethodService {

    @Resource
    private ProduceMethodRepository produceMethodRepository;

    @Override
    public ProduceMethod createProduceMethod(ProduceMethod produceMethod) {
        return produceMethodRepository.save(produceMethod);
    }

    @Override
    public ProduceMethodInterface getProduceMethodInterfaceById(Long methodId) {
        return produceMethodRepository.findProduceMethodInterfaceById(methodId).get();
    }

    @Override
    public Collection<ProduceMethodInterface> getProduceMethodsByMaterialId(Long materialId) {
        return produceMethodRepository.findProduceMethodByMaterialId(materialId);
    }

    @Override
    public Collection<ProduceMethodInterface> getProduceMethodsByName(String methodName) {
        return produceMethodRepository.findByMethodName(methodName);
    }

    @Override
    public ProduceMethod updateProduceMethod(ProduceMethod produceMethod) {
        return produceMethodRepository.save(produceMethod);
    }

    @Override
    public void deleteProduceMethod(ProduceMethod produceMethod) {
        produceMethodRepository.delete(produceMethod);
    }

    @Override
    public void deleteProduceMethodById(Long methodId) {
        produceMethodRepository.deleteById(methodId);
    }

    @Override
    public void createRelationshipForProduceMethod(Long materialId, Long methodId) {
        produceMethodRepository.createRelationshipForProduceMethod(materialId,methodId);
    }
}
