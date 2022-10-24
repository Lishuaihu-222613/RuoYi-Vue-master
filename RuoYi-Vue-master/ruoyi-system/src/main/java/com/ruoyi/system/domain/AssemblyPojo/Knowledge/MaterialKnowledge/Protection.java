package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Protection")
public class Protection {

    @Id
    @GeneratedValue
    private Long protectionId;

    @Property(name = "label")
    private String protectionName;

    @Property(name = "Description")
    private String protectionDescription;

}
