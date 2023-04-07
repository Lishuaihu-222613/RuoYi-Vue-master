package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.Site;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"吊具工具","SpreaderTool"})
public class SpreaderTool extends ToolingResource{

    @Property(name = "最大载荷")
    private String maximumLoad;

    public String getMaximumLoad() {
        return maximumLoad;
    }

    public void setMaximumLoad(String maximumLoad) {
        this.maximumLoad = maximumLoad;
    }

    public SpreaderTool() {
    }

    public SpreaderTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Site> sites, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures, String maximumLoad) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, sites, sequences, steps, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses, unSuitableProcesses, suitableProcedures, unSuitableProcedures);
        this.maximumLoad = maximumLoad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpreaderTool that = (SpreaderTool) o;
        return Objects.equals(maximumLoad, that.maximumLoad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maximumLoad);
    }

    @Override
    public String toString() {
        return "SpreaderTool{" +
                "maximumLoad='" + maximumLoad + '\'' +
                "} " + super.toString();
    }
}
