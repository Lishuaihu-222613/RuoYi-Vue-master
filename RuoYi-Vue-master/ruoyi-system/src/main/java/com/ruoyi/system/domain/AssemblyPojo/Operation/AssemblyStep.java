package com.ruoyi.system.domain.AssemblyPojo.Operation;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("AssemblyStep")
public class AssemblyStep {

    @Id
    @GeneratedValue
    private Long stepId;

    @Property(name = "label")
    private String stepName;

    @Property(name = "stepNumber")
    private String stepNumber;

    @Property(name = "Description")
    private String stepDescription;

    @Property(name = "Remark")
    private String stepRemark;

    @Relationship(type = "hasAssociatedTool" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> associatedResources;

    @Relationship(type = "beforeStep" , direction = Relationship.Direction.OUTGOING)
    private AssemblyStep beforeStep;

    @Relationship(type = "afterStep" , direction = Relationship.Direction.OUTGOING)
    private AssemblyStep afterStep;

    @Relationship(type = "hasAssociatedFile" , direction = Relationship.Direction.OUTGOING)
    private Set<FileKnowledge> associatedFiles;
}
