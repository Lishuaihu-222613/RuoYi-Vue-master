package com.ruoyi.system.service.KnowledgeService.InspectionMethod;


import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ConditionForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.FactorForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ModeForMethod;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InspectionMethodService {
    
    InspectionMethod createInspectionMethod(InspectionMethod method);
    
    InspectionMethod updateInspectionMethod(InspectionMethod method);
    
    InspectionMethod getInspectionMethodById(Long methodId);
    
    Page<InspectionMethod> getAllInspectionMethods();
    
    List<InspectionMethod> getInspectionMethodsByType(String type);
    
    List<InspectionMethod> getInspectionMethodsByName(String methodName);
    
    void deleteInspectionMethod(Long methodId);
    
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
