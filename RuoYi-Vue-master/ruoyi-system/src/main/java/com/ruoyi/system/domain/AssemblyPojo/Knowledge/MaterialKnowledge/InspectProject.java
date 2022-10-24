package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Set;

@Node("InspectProject")
public class InspectProject {

    @Id
    @GeneratedValue
    private Long projectId;

    @Property(name = "label")
    private String projectName;

    @Property(name = "ClassificationAndIndicators")
    private Map<String, String> ClassificationAndIndicators;

    @Relationship(type = "hasInspectMethod" , direction = Relationship.Direction.OUTGOING)
    private Set<InspectionMethod> InspectionMethods;

}
