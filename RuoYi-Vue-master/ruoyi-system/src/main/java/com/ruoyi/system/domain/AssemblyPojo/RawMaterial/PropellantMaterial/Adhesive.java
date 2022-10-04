package com.ruoyi.system.domain.AssemblyPojo.RawMaterial.PropellantMaterial;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;
import java.util.Map;

@Node()
public class Adhesive extends RawMaterial {

    private double hydroxylValue;

    private double viscosity;

    private double water;

    private double numberAverageMolecularWeight;

    private double peroxide;

    private double density;

    private Map<String,String> otherAttributes;

}
