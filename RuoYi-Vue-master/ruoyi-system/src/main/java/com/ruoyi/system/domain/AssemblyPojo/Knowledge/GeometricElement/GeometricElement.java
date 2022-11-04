package com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("GeometricElement")
public class GeometricElement {

    @Id
    @GeneratedValue
    private Long elementId;

    @Property("Description")
    private String elementDescription;

    @Property("label")
    private String elementName;

    @DynamicLabels
    private Set<String> elementTypes;



}
