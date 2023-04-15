package com.ruoyi.system.domain.AssemblyPojo.Process.Interface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ElementInterface {

    Long getElementId();

    String getElementName();

    String getElementNumber();

    String getElementDescription();

    List<String> getElementRemark();

    Map<String, String> getElementRequirements();

    Map<String, String> getElementOtherProperties();

    Set<String> getDynamicLabels();

    boolean isHasSubElements();


}
