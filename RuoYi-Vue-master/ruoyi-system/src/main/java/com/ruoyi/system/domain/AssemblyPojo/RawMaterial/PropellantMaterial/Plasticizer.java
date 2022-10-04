package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Plasticizer extends RawMaterial {

    private double flashPoint;

    private double boilingPoint;

    private double meltingPoint;

    private double viscosity;

    private double refractive;

    private double numberAverageMolecularWeight;



}
