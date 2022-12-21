package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;

import java.util.List;
import java.util.Set;

public class InspectionFactor {

    private Long id;

    private String factorName;

    private String factorDescription;

    private List<String> controlFactors;

    private Set<FileKnowledge> associatedFiles;

}
