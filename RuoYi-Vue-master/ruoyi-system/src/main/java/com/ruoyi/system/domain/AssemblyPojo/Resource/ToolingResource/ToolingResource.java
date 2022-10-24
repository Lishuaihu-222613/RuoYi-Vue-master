package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcedure;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Map;
import java.util.Set;

@Node()
public abstract class ToolingResource extends AssemblyResource {

    @Property(name = "Price")
    private double toolPrice;

    @Property(name = "manufacturer")
    private String manufacturer;

    @Property(name = "Size")
    private String toolSize;

    @Property(name = "Material")
    private String materialType;

    @Property(name = "Usage")
    private String toolUsage;

    @Property(name = "Specification")
    private String toolSpecification;

    @Property(name = "State")
    private String toolState;

    @Property(name = "Condition")
    private double wearCondition;

    @Property(name = "Capacity")
    private Map<String,Object> toolCapacity;

    @Relationship(type = "hasSuitableProcess", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;

    @Relationship(type = "hasUnSuitableProcess", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> unSuitableProcesses;

    @Relationship(type = "hasSuitableProcedure", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> suitableProcedures;

    @Relationship(type = "hasUnSuitableProcedure", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> unSuitableProcedures;
}
