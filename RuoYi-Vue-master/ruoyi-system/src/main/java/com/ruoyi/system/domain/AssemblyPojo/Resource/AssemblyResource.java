package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Operation.AssemblyOperation;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("AssemblyResource")
public class AssemblyResource {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String resourceName;

    @Property(name = "resourceType")
    private String resourceType;

    @Property(name = "resourceDescription")
    private String resourceDescription;

    @Relationship(type = "hasLocation" , direction = Relationship.Direction.OUTGOING)
    private Set<Location> locations;

    @Relationship(type = "isUsedIn" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> operations;

}
