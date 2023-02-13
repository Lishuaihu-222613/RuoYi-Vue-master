package com.ruoyi.system.service.algorithm;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyComponent;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyConstraint;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyPart;
import com.ruoyi.system.service.StructureService.StructureService;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssemblyUnitDivision {

    @Resource
    private KieContainer kieContainer;

    @Resource
    private StructureService structureService;

    //传入待推理零件集合
    private List<AssemblyPart> originalParts;

    //未约束零件集合
    private List<AssemblyPart> unConstraintParts;

    //约束零件集合
    private List<AssemblyPart> constraintParts;

    //基准件集合
    private List<AssemblyPart> referenceParts;

    //划分单元结果
    private List<AssemblyComponent> unitComponents;

    //判断是否存在未约束零件
    public boolean checkConstraintsOfParts(List<AssemblyPart> parts) {

        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        unConstraintParts = new ArrayList<>();
        constraintParts = new ArrayList<>();
        session.setGlobal("unConstraintParts", unConstraintParts);
        session.setGlobal("constraintParts", constraintParts);
        session.insert(originalParts);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("check_Constraints_Of_Parts"));
        session.destroy();
        return unConstraintParts.size() == 0;
    };

    //确定装配单元数目
    public int calculateUnitNumbers(List<AssemblyPart> parts) {
        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        Map<String,Integer> unitNumber = new HashMap<>();
        unitNumber.put("number",0);
        session.insert(originalParts);
        session.setGlobal("unitNumber", unitNumber);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("calculate_Unit_Numbers"));
        session.destroy();
        return unitNumber.get("number");
    };


    //确定装配基准件(规则一)
    public List<AssemblyPart> determineReferencePart_1(List<AssemblyPart> parts, int unitNumber) {
        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        referenceParts = new ArrayList<>();
        session.insert(originalParts);
        session.setGlobal("partSequence", referenceParts);
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("determineReferencePart--1"));
        session.destroy();
        return referenceParts.subList(0,unitNumber);
    };

    //确定装配基准件(规则二)
    public List<AssemblyPart> determineReferencePart_2(List<AssemblyPart> parts, int unitNumber) {
        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        referenceParts = new ArrayList<>();
        session.insert(originalParts);
        session.setGlobal("partSequence", referenceParts);
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("determineReferencePart--2"));
        session.destroy();
        return referenceParts.subList(0,unitNumber);
    };

    //装配单元划分算法1（主要）
    public List<AssemblyComponent> unitDivision1(List<AssemblyPart> ReferenceParts, List<AssemblyPart> parts) {
        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        referenceParts = ReferenceParts;
        session.insert(originalParts);
        session.setGlobal("referenceParts", referenceParts);
        unitComponents = new ArrayList<>();
        session.setGlobal("unitComponents", unitComponents);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("UnitDivision--1"));
        session.destroy();
        return unitComponents;
    };

    //装配单元划分算法2（简单）
    public List<AssemblyComponent> unitDivision2(List<AssemblyPart> parts) {
        KieSession session = kieContainer.newKieSession();
        originalParts = parts;
        AssemblyConstraint[][] constraintMatrix = getMatrixOfPart(originalParts);
        session.insert(originalParts);
        unitComponents = new ArrayList<>();
        session.setGlobal("unitComponents", unitComponents);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("UnitDivision--2"));
        session.destroy();
        return unitComponents;
    };

    //构建零件矩阵
    public AssemblyConstraint[][] getMatrixOfPart(List<AssemblyPart> parts) {
        int n = parts.size();
        AssemblyConstraint[][] weightMatrix = new AssemblyConstraint[n][n];
        AssemblyPart partA;
        AssemblyPart partB;
        KieSession session = kieContainer.newKieSession();
        for (int i = 0; i <= n; i++) {
            partA = parts.get(i);
            for (int j = 0; j <= n; j++) {
                partB = parts.get(j);
                List<AssemblyConstraint> constraintsOfPartA = structureService.getAllConstraintByStructureId(partA.getStructureId());
                List<AssemblyConstraint> constraintsOfPartB = structureService.getAllConstraintByStructureId(partB.getStructureId());
                AssemblyConstraint cab;
                List<AssemblyConstraint> constraintAB = constraintsOfPartA.stream().map(cA -> constraintsOfPartB.stream()
                                .filter(cB -> Objects.equals(cA.getConstraintId(), cB.getConstraintId()))
                                .findAny()
                                .orElse(null))
                        .filter(Objects::nonNull).collect(Collectors.toList());
                if (constraintAB.size() > 0) {
                    cab = constraintAB.get(0);
                    session.insert(cab);
                    weightMatrix[i][j] = cab;
                };
            }
        }
        return weightMatrix;
    };

}