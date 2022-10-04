package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class BurningRateRegulator extends RawMaterial {

    private double boilingPoint;

    private double meltingPoint;

    private double purity;

    private String formula;

    private double numberAverageMolecularWeight;

    private double density;


}
