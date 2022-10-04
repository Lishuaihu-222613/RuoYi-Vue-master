package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class MetalFuel extends RawMaterial {

    private double aluminium;

    private double iron;

    private double copper;

    private double silicon;

    private double density;

    private double particleSize;
}
