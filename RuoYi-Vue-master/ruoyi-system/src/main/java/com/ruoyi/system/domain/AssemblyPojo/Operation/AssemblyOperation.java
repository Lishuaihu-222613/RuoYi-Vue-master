package com.ruoyi.system.domain.AssemblyPojo.Operation;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Movement.Move;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

@Node("AssemblyOperation")
public class AssemblyOperation {

    @Id
    @GeneratedValue
    private Long operationId;

    @Property(name = "label")
    private String operationName;

    @Property(name = "operationNumber")
    private String operationNumber;

    @Property(name = "Description")
    private String operationDescription;

    @Property(name = "BeginAt")
    private Date beginTime;

    @Property(name = "EndAt")
    private Date endTime;

    @Relationship(type = "use", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> resources;

    @Relationship(type = "hasMove", direction = Relationship.Direction.OUTGOING)
    private Move move;

    @Relationship(type = "assembling" , direction = Relationship.Direction.OUTGOING)
    private AssemblingStructure assemblingStructure;

    @Relationship(type = "assembled", direction = Relationship.Direction.OUTGOING)
    private AssembledStructure assembledStructure;

    @Relationship(type = "AndOP", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation operationAnd;

    @Relationship(type = "OrOP", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation operationOr;

    @Relationship(type = "assembleBefore", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation beforeOperation;

    @Relationship(type = "assembleAfter", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation afterOperation;

    @Relationship(type = "hasAssociatedSteps", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyStep> associatedSteps;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
    private Set<fileKnowledge> associatedFiles;
}
