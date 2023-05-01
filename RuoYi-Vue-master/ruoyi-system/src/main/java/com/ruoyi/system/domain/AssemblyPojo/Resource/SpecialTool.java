package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"专用资源","SpecialTool"})
public class SpecialTool extends AssemblyResource{

    @CompositeProperty(prefix = "能力属性描述")
    private Map<String,String> capacityDescriptions;

    public Map<String, String> getCapacityDescriptions() {
        return capacityDescriptions;
    }

    public void setCapacityDescriptions(Map<String, String> capacityDescriptions) {
        this.capacityDescriptions = capacityDescriptions;
    }

    public SpecialTool() {
    }

    public SpecialTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, Map<String, String> capacityDescriptions) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site);
        this.capacityDescriptions = capacityDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpecialTool that = (SpecialTool) o;
        return Objects.equals(capacityDescriptions, that.capacityDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacityDescriptions);
    }

    @Override
    public String toString() {
        return "SpecialTool{" +
                "capacityDescriptions=" + capacityDescriptions +
                "} " + super.toString();
    }
}
