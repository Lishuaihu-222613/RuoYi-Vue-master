package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("StorageRequirement")
public class StorageRequirement {

    @Id
    @GeneratedValue
    private Long requirementId;

    @Property(name = "label")
    private String requirementName;

    @Property(name = "Description")
    private String requirementDescription;

}
