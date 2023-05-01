package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialSingleInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;

public interface hasMaterialElementInterface {

    Long getId();

    double getPercentage();

    Material getMaterial();
}
