package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class hasRawMaterialElement {

    @Id
    @GeneratedValue
    private Long Id;

    @TargetNode
    private Material material;

    @Property(name = "percentage")
    private double percentage;



}
