package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("ProduceMethod")
public class produceMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "Description")
    private String methodDescription;

    @Relationship(type = "hasAssociatedFile")
    private Set<fileKnowledge> associatedFiles;
}
