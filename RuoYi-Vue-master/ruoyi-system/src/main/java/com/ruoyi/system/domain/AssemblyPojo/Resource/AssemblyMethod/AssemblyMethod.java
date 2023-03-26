package com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement.GeometricElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node({"装配方法","AssemblyMethod"})
public class AssemblyMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "方法描述")
    private String methodDescription;

    @Property(name = "可达精度")
    private String reachableDimensionalAccuracy;

    @Property(name = "可达公差")
    private String reachableGeometricTolerance;

    @DynamicLabels
    private Set<String> methodType;

    @Relationship(type = "适用元素",direction = Relationship.Direction.OUTGOING)
    private Set<GeometricElement> suitableElements;

    @Relationship(type = "适合资源",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> suitableResources;

    @Relationship(type = "适用材料",direction = Relationship.Direction.OUTGOING)
    private Set<Material> suitableMaterials;

}
