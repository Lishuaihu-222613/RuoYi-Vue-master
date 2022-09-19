package com.ruoyi.system.domain.AssemblyPojo.Operation;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Movement.Move;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("AssemblyOperation")
public class AssemblyOperation {

    @Id
    @GeneratedValue
    private Long operationId;

    @Property(name = "Label")
    private String OperationName;

    @Property(name = "Description")
    private String OperationDescription;

    @Relationship(type = "use", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> resources;

    @Relationship(type = "hasMove", direction = Relationship.Direction.OUTGOING)
    private Move move;

    @Relationship(type = "assembling" , direction = Relationship.Direction.OUTGOING)
    private AssemblingStructure assemblingStructure;

    @Relationship(type = "assembly" , direction = Relationship.Direction.OUTGOING)
    private AssemblyStructure assemblyStructure;

    @Relationship(type = "assembled", direction = Relationship.Direction.OUTGOING)
    private AssembledStructure assembledStructure;

    @Relationship(type = "AndOP", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation OperationAnd;

    @Relationship(type = "OrOP", direction = Relationship.Direction.OUTGOING)
    private AssemblyOperation OperationOr;

}
