package com.ruoyi.system.service.KnowledgeService.InspectionMethod.impl;


import com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod.InspectionConditionRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod.InspectionFactorRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod.InspectionMethodRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod.InspectionModeRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ConditionForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.FactorForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ModeForMethod;
import com.ruoyi.system.service.KnowledgeService.InspectionMethod.InspectionMethodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InspectionMethodServiceImpl implements InspectionMethodService {

    @Resource
    private InspectionConditionRepository inspectionConditionRepository;

    @Resource
    private InspectionMethodRepository inspectionMethodRepository;

    @Resource
    private InspectionFactorRepository inspectionFactorRepository;

    @Resource
    private InspectionModeRepository inspectionModeRepository;


    @Override
    public InspectionMethod createInspectionMethod(InspectionMethod method) {
        return inspectionMethodRepository.save(method);
    }

    @Override
    public InspectionMethod updateInspectionMethod(InspectionMethod method) {
        Optional<InspectionMethod> optionalMethod = inspectionMethodRepository.findById(method.getMethodId());
        if(optionalMethod.isPresent()){
            InspectionMethod oldMethod = optionalMethod.get();
            oldMethod.setMethodName(method.getMethodName());
            oldMethod.setMethodDescription(method.getMethodDescription());
            oldMethod.setDynamicLabels(method.getDynamicLabels());
            oldMethod.setFutureExpansion(method.getFutureExpansion());
            oldMethod.setMethodPrinciple(method.getMethodPrinciple());
            InspectionMethod newMethod = inspectionMethodRepository.save(oldMethod);
            return newMethod;
        }
        return null;
    }

    @Override
    public InspectionMethod getInspectionMethodById(Long methodId) {
        return inspectionMethodRepository.findById(methodId).get();
    }

    @Override
    public Page<InspectionMethod> getAllInspectionMethods(Pageable pageable) {
        return inspectionMethodRepository.findAll(pageable);
    }

    @Override
    public Page<InspectionMethod> getInspectionMethodsByType(String type, Pageable pageable) {
        return inspectionMethodRepository.findMethodByType(type,pageable);
    }

    @Override
    public List<InspectionMethod> getInspectionMethodsByName(String methodName) {
        return inspectionMethodRepository.findMethodByName(methodName);
    }

    @Override
    public void deleteInspectionMethod(Long methodId) {
        inspectionMethodRepository.deleteById(methodId);
    }

    @Override
    public InspectionCondition createInspectionCondition(ConditionForMethod CM) {
        Optional<InspectionMethod> optionalMethod = inspectionMethodRepository.findById(CM.getMethodId());
        if(optionalMethod.isPresent()){
            InspectionMethod method = optionalMethod.get();
            InspectionCondition condition = inspectionConditionRepository.save(CM.getCondition());
            Set<InspectionCondition> methodConditions = method.getMethodConditions();
            methodConditions.add(condition);
            method.setMethodConditions(methodConditions);
            inspectionMethodRepository.save(method);
            return condition;
        }
        return null;
    }

    @Override
    public InspectionCondition updateInspectionCondition(InspectionCondition condition) {
        return inspectionConditionRepository.save(condition);
    }

    @Override
    public List<InspectionCondition> getInspectionConditionsByMethod(Long methodId) {
        return inspectionConditionRepository.findConditionByMethodId(methodId);
    }

    @Override
    public void deleteInspectionCondition(Long conditionId) {
        inspectionConditionRepository.deleteById(conditionId);
    }

    @Override
    public InspectionFactor createInspectionFactor(FactorForMethod FM) {
        Optional<InspectionMethod> optionalMethod = inspectionMethodRepository.findById(FM.getMethodId());
        if(optionalMethod.isPresent()){
            InspectionMethod method = optionalMethod.get();
            InspectionFactor factor = inspectionFactorRepository.save(FM.getFactor());
            Set<InspectionFactor> methodFactors = method.getMethodFactors();
            methodFactors.add(factor);
            method.setMethodFactors(methodFactors);
            inspectionMethodRepository.save(method);
            return factor;
        }
        return null;
    }

    @Override
    public InspectionFactor updateInspectionFactor(InspectionFactor factor) {
        return inspectionFactorRepository.save(factor);
    }

    @Override
    public List<InspectionFactor> getInspectionFactorsByMethod(Long methodId) {
        return inspectionFactorRepository.findFactorByMethodId(methodId);
    }

    @Override
    public void deleteInspectionFactor(Long factorId) {
        inspectionFactorRepository.deleteById(factorId);
    }

    @Override
    public InspectionMode createInspectionMode(ModeForMethod MM) {
        Optional<InspectionMethod> optionalMethod = inspectionMethodRepository.findById(MM.getMethodId());
        if(optionalMethod.isPresent()){
            InspectionMethod method = optionalMethod.get();
            InspectionMode mode = inspectionModeRepository.save(MM.getMode());
            Set<InspectionMode> methodModes = method.getMethodModes();
            methodModes.add(mode);
            method.setMethodModes(methodModes);
            inspectionMethodRepository.save(method);
            return mode;
        }
        return null;
    }

    @Override
    public InspectionMode updateInspectionMode(InspectionMode mode) {
        return inspectionModeRepository.save(mode);
    }

    @Override
    public List<InspectionMode> getInspectionModesByMethod(Long methodId) {
        return inspectionModeRepository.findModeByMethodId(methodId);
    }

    @Override
    public void deleteInspectionMode(Long modeId) {
        inspectionModeRepository.deleteById(modeId);
    }
}
