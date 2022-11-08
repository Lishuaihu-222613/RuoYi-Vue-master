package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface;

import java.util.Map;

public interface InspectProjectInterface {

    Long getProjectId();

    String getProjectName();

    Map<String, String> getClassificationAndIndicators();

}
