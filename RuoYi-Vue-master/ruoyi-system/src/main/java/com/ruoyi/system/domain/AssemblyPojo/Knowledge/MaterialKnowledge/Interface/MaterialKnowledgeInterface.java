package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface;

import java.util.List;

public interface MaterialKnowledgeInterface {

    String getMKId();

    String getMKName();

    String getMaterialType();

    List<String> getMaterialFunctions();

    List<String> getMaterialRequirements();

}
