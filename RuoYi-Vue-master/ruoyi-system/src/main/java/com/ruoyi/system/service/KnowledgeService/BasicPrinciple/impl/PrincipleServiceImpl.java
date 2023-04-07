package com.ruoyi.system.service.KnowledgeService.BasicPrinciple.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.BasicPrinciple.PrincipleRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import com.ruoyi.system.service.KnowledgeService.BasicPrinciple.PrincipleService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrincipleServiceImpl implements PrincipleService {

    @Resource
    private PrincipleRepository principleRepository;

    @Override
    public Page<Principle> getAllPrinciple(Pageable pageable) {
        return principleRepository.findAll(pageable);
    }

    @Override
    public Page<Principle> getAllPrincipleByLabel(String label,Pageable pageable) {
        return principleRepository.findPrincipleByType(label,pageable);
    }

    @Override
    public Page<Principle> getAllPrincipleByParams(Example example, Pageable pageable) {
        return principleRepository.findAll(example,pageable);
    }

    @Override
    public Principle getPrincipleById(Long principleId) {
        return principleRepository.findById(principleId).get();
    }

    @Override
    public List<Principle> getPrincipleByName(String name) {
        return principleRepository.findPrinciplesByPrincipleName(name);
    }

    @Override
    public List<Principle> getAllPrincipleByCondition(String condition) {
        return null;
    }

    @Override
    public Principle createPrinciple(Principle principle) {
        return principleRepository.save(principle);
    }

    @Override
    public Principle updatePrinciple(Principle principle) {
        return principleRepository.save(principle);
    }

    @Override
    public void deletePrinciple(Long principleId) {
        principleRepository.deleteById(principleId);
    }
}
