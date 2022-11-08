package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface;

import java.util.List;

public interface MaterialInterface {

    Long getMaterialId();

    String getMaterialName();

    String getEnglishName();

    String getNickName();

    String getEnglishNickName();

    String getRelativeMolecularMass();

    String getStructuralFormula();

    String getCasRegistryNumber();

    String getMaterialDescription();

    String getMaterialAppearance();

    List<String> getMaterialUsage();
}
