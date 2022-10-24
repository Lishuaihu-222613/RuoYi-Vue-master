package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Node("MouldTool")
public class MouldTool extends ToolingResource{

    @Relationship(type = "hasSuitableMaterial", direction = Relationship.Direction.OUTGOING)
    private Set<Material> suitableMaterials;

    @Property(name = "DesignAttentions")
    private List<String> designAttentions;

}
