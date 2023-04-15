package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialSingleInterface;

public interface hasMaterialElementInterface {

    Long getId();

    double getPercentage();

    MaterialSingleInterface getMaterial();
}
