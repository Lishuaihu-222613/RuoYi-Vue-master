package com.ruoyi.system.service.KnowledgeService.InspectionMethod;


import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ConditionForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.FactorForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ModeForMethod;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InspectionMethodService {
    
    InspectionMethod createInspectionMethod(InspectionMethod method);
    
    InspectionMethod updateInspectionMethod(InspectionMethod method);
    
    InspectionMethod getInspectionMethodById(Long methodId);
    
    Page<InspectionMethod> getAllInspectionMethods(Pageable pageable);
    
    Page<InspectionMethod> getInspectionMethodsByType(String type, Pageable pageable);

    Page<InspectionMethod> getInspectionMethodsByParams(Example<InspectionMethod> example, Pageable pageable);

    List<InspectionMethod> getInspectionMethodsByName(String methodName);
    
    void deleteInspectionMethod(Long[] methodId);
    
    InspectionCondition createInspectionCondition(ConditionForMethod CM);
    
    InspectionCondition updateInspectionCondition(InspectionCondition condition);
    
    List<InspectionCondition> getInspectionConditionsByMethod(Long methodId);
    
    void deleteInspectionCondition(Long conditionId);

    InspectionFactor createInspectionFactor(FactorForMethod FM);

    InspectionFactor updateInspectionFactor(InspectionFactor factor);

    List<InspectionFactor> getInspectionFactorsByMethod(Long methodId);

    void deleteInspectionFactor(Long factorId);

    InspectionMode createInspectionMode(ModeForMethod MM);

    InspectionMode updateInspectionMode(InspectionMode mode);

    List<InspectionMode> getInspectionModesByMethod(Long methodId);

    void deleteInspectionMode(Long modeId);
    
}
