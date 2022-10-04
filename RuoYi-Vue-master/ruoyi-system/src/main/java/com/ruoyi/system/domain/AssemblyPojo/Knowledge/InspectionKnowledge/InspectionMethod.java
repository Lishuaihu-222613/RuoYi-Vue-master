package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;

import java.util.List;
import java.util.Set;

public class InspectionMethod {

    private Long Id;

    private String methodName;

    private String methodDescription;

    private String methodPrinciple;

    private List<String> methodConditions;

    private String futureExpansion;

    private Set<InspectionFactor> methodFactors;

    private Set<InspectionMode> methodModes;

    private Set<TypicalProcess> associatedProcesses;

    private Set<QualityProblem> associatedProblems;

    private Set<AssemblyResource> associatedDevices;

    private Set<fileKnowledge> associatedFiles;
}
