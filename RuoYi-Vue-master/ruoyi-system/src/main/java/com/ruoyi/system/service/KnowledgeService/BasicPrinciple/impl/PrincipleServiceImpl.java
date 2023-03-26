package com.ruoyi.system.service.KnowledgeService.BasicPrinciple.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.BasicPrinciple.PrincipleRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import com.ruoyi.system.service.KnowledgeService.BasicPrinciple.PrincipleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrincipleServiceImpl implements PrincipleService {

    @Resource
    private PrincipleRepository principleRepository;

    PageRequest of = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "principleId"));

    @Override
    public Page<Principle> getAllPrinciple() {
        return principleRepository.findAll(of);
    }

    @Override
    public Page<Principle> getAllPrincipleByLabel(String label) {
        return principleRepository.findPrincipleByType(label,of);
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
