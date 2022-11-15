package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class hasRawMaterialElement {

    @Id
    @GeneratedValue
    private Long Id;

    @TargetNode
    private RawMaterial material;

    @Property(name = "percentage")
    private double percentage;


}
