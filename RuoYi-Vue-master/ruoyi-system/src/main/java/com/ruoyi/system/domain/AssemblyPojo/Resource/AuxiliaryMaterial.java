package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node({"辅助工具","AuxiliaryMaterial"})
public class AuxiliaryMaterial extends AssemblyResource{

    @Property(name = "工具用途")
    private String materialUsage;

    @Property(name = "材料类型")
    private String materialType;

    @Relationship(type = "适用工艺" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;

}
