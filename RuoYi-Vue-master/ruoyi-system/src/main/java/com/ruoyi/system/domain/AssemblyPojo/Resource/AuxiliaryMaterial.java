package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node("AuxiliaryMaterial")
public class AuxiliaryMaterial extends AssemblyResource{

    @Property(name = "Usage")
    private String materialUsage;

    @Property(name = "Material")
    private String materialType;

    @Relationship(type = "hasSuitableProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;

}
