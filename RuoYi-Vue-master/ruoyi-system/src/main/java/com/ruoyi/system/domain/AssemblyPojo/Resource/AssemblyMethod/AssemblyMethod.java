package com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement.GeometricElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Set;

@Node("AssemblyMethod")
public class AssemblyMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "Description")
    private String methodDescription;

    private String reachableDimensionalAccuracy;

    private String reachableGeometricTolerance;

    private Set<String> methodType;

    private Set<GeometricElement> suitableElements;

    private Set<AssemblyResource> suitableResources;

    private Set<Material> suitableMaterials;

}
