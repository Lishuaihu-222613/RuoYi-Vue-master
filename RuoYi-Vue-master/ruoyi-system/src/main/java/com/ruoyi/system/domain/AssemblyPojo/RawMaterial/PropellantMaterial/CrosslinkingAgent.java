package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Map;

@Node
public class CrosslinkingAgent extends RawMaterial {

    private double purity;

    private String formula;

    private double numberAverageMolecularWeight;

    private double water;

    private double density;

    private double flashPoint;

    private double boilingPoint;

    private double meltingPoint;

    private Map<String,String> otherAttributes;
}
