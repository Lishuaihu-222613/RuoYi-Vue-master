package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Operation.AssemblyOperation;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"装药总装资源","AssemblyResource"})
public abstract class AssemblyResource {

    @Id
    @GeneratedValue
    private Long resourceId;

    @Property(name = "label")
    private String resourceName;

    @Property(name = "资源描述")
    private String resourceDescription;

    @DynamicLabels
    private Set<String> resourceTypes;

    @Relationship(type = "存在于" , direction = Relationship.Direction.OUTGOING)
    private Set<Location> locations;

    @Relationship(type = "被使用在" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> sequences;

    @Relationship(type = "使用于" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> steps;

    public AssemblyResource() {
    }


}
