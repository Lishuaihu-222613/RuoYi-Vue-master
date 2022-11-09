package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyMethod.AssemblyMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node("CuttingTool")
public class CuttingTool extends ToolingResource{

    private Integer cuttingEdges;

    private double edgeLength;

    private String shankStyle;

    private String maxCuttingAmount;

    private String maxMachiningSize;

    private String economicAccuracy;

    private String machiningRoughness;

    private String unsuitableCondition;

    private Set<AssemblyMethod> suitableMethods;

    private Set<Material> suitableMaterials;

    private Set<Material> unSuitableMaterial;

}
