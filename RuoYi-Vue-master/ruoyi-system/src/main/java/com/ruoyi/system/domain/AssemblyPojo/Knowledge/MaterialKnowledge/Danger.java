package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@Node("Danger")
public class Danger {

    @Id
    @GeneratedValue
    private Long dangerId;

    @Property(name = "label")
    private String dangerName;

    @Property(name = "Description")
    private String dangerDescription;

 }
