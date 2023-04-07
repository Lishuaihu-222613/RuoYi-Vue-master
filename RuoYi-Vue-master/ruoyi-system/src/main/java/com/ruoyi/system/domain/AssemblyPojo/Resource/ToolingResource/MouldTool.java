package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.Site;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"模具工具","MouldTool"})
public class MouldTool extends ToolingResource{

    @Relationship(type = "适用材料", direction = Relationship.Direction.OUTGOING)
    private Set<Material> suitableMaterials;

    @Property(name = "设计注意事项")
    private List<String> designAttentions;

    public Set<Material> getSuitableMaterials() {
        return suitableMaterials;
    }

    public void setSuitableMaterials(Set<Material> suitableMaterials) {
        this.suitableMaterials = suitableMaterials;
    }

    public List<String> getDesignAttentions() {
        return designAttentions;
    }

    public void setDesignAttentions(List<String> designAttentions) {
        this.designAttentions = designAttentions;
    }

    public MouldTool() {
    }

    public MouldTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Site> sites, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures, Set<Material> suitableMaterials, List<String> designAttentions) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, sites, sequences, steps, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses, unSuitableProcesses, suitableProcedures, unSuitableProcedures);
        this.suitableMaterials = suitableMaterials;
        this.designAttentions = designAttentions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MouldTool mouldTool = (MouldTool) o;
        return Objects.equals(suitableMaterials, mouldTool.suitableMaterials) && Objects.equals(designAttentions, mouldTool.designAttentions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), suitableMaterials, designAttentions);
    }

    @Override
    public String toString() {
        return "MouldTool{" +
                "suitableMaterials=" + suitableMaterials +
                ", designAttentions=" + designAttentions +
                "} " + super.toString();
    }
}
