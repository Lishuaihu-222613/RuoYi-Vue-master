package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"模具工具","MouldTool"})
public class MouldTool extends ToolingResource{

    @Property(name = "设计注意事项")
    private List<String> designAttentions;

    public List<String> getDesignAttentions() {
        return designAttentions;
    }

    public void setDesignAttentions(List<String> designAttentions) {
        this.designAttentions = designAttentions;
    }

    public MouldTool() {
    }

    public MouldTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, List<String> suitableProcesses, List<String> designAttentions) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses);
        this.designAttentions = designAttentions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MouldTool mouldTool = (MouldTool) o;
        return Objects.equals(designAttentions, mouldTool.designAttentions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), designAttentions);
    }

    @Override
    public String toString() {
        return "MouldTool{" +
                "designAttentions=" + designAttentions +
                "} " + super.toString();
    }
}
