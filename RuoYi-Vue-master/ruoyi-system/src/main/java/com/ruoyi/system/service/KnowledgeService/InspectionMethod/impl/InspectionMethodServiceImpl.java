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
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.MethodRelations;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ModeForMethod;
import com.ruoyi.system.service.KnowledgeService.InspectionMethod.InspectionMethodService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        InspectionMethod inspectionMethod = new InspectionMethod(method.getMethodId(), method.getMethodName(),
                method.getMethodDescription(), method.getMethodPrinciple(), method.getFutureExpansion(), method.getDynamicLabels());
        InspectionMethod singleMethod = inspectionMethodRepository.save(inspectionMethod);
        HashSet<InspectionCondition> conditions = new HashSet<>();
        HashSet<InspectionFactor> factors = new HashSet<>();
        HashSet<InspectionMode> modes = new HashSet<>();
        for (InspectionCondition methodCondition : method.getMethodConditions()) {
            InspectionCondition condition = inspectionConditionRepository.save(methodCondition);
            conditions.add(condition);
        };
        for (InspectionFactor methodFactor : method.getMethodFactors()) {
            InspectionFactor factor = inspectionFactorRepository.save(methodFactor);
            factors.add(factor);
        }
        for (InspectionMode methodMode : method.getMethodModes()) {
            InspectionMode mode = inspectionModeRepository.save(methodMode);
            modes.add(mode);
        }
        singleMethod.setMethodConditions(conditions);
        singleMethod.setMethodFactors(factors);
        singleMethod.setMethodModes(modes);
        return inspectionMethodRepository.save(singleMethod);
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
            inspectionConditionRepository.deleteAll(oldMethod.getMethodConditions());
            List<InspectionCondition> inspectionConditions = inspectionConditionRepository.saveAll(method.getMethodConditions());
            inspectionFactorRepository.deleteAll(oldMethod.getMethodFactors());
            List<InspectionFactor> inspectionFactors = inspectionFactorRepository.saveAll(method.getMethodFactors());
            inspectionModeRepository.deleteAll(oldMethod.getMethodModes());
            List<InspectionMode> inspectionModes = inspectionModeRepository.saveAll(method.getMethodModes());
            oldMethod.setMethodConditions(new HashSet<>(inspectionConditions));
            oldMethod.setMethodFactors(new HashSet<>(inspectionFactors));
            oldMethod.setMethodModes(new HashSet<>(inspectionModes));
            InspectionMethod newMethod = inspectionMethodRepository.save(oldMethod);
            return newMethod;
        }
        return null;
    }

    @Override
    public InspectionMethod getInspectionMethodById(Long methodId) {
        Optional<InspectionMethod> optionalInspectionMethod = inspectionMethodRepository.findById(methodId);
        if(optionalInspectionMethod.isPresent()){
            InspectionMethod inspectionMethod = optionalInspectionMethod.get();
            return inspectionMethod;
        }
        return null;
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
    public Page<InspectionMethod> getInspectionMethodsByParams(Example<InspectionMethod> example, Pageable pageable) {
        return inspectionMethodRepository.findAll(example,pageable);
    }

    @Override
    public List<InspectionMethod> getInspectionMethodsByName(String methodName) {
        return inspectionMethodRepository.findMethodByName(methodName);
    }

    @Override
    public void deleteInspectionMethod(Long[] methodIds) {
        inspectionMethodRepository.deleteAllById(Arrays.asList(methodIds));
    }

    @Override
    public void deleteRelations(Long methodId) {
        inspectionMethodRepository.deleteOutRelationsById(methodId);
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
