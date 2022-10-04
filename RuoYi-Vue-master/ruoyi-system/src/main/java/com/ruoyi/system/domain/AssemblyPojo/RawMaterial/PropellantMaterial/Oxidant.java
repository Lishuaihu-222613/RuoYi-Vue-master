package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

@Node()
public class Oxidant extends RawMaterial {

    private double density;

    private double flashPoint;

    private double boilingPoint;

    private double meltingPoint;
}
