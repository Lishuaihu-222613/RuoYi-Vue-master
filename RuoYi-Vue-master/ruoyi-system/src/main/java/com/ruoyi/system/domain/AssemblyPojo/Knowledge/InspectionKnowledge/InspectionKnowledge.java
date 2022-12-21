package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;

import java.util.Set;

public class InspectionKnowledge {

    private Long Id;

    private String InspectionName;

    private String InspectionDescription;

    private String principle;

    private Set<InspectionMethod> commonMethods;

    private Set<FileKnowledge> associatedFiles;



}
