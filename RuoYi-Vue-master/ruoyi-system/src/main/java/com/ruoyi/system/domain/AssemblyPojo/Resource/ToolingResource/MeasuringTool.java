package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"量具工具", "MeasuringTool"})
public class MeasuringTool extends ToolingResource {

    @Property(name = "测量精度")
    private String measureAccuracy;

    @Property(name = "测量高度")
    private String measureHeight;

    public String getMeasureAccuracy() {
        return measureAccuracy;
    }

    public void setMeasureAccuracy(String measureAccuracy) {
        this.measureAccuracy = measureAccuracy;
    }

    public String getMeasureHeight() {
        return measureHeight;
    }

    public void setMeasureHeight(String measureHeight) {
        this.measureHeight = measureHeight;
    }

    public MeasuringTool() {
    }

    public MeasuringTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, List<String> suitableProcesses, String measureAccuracy, String measureHeight) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses);
        this.measureAccuracy = measureAccuracy;
        this.measureHeight = measureHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MeasuringTool that = (MeasuringTool) o;
        return Objects.equals(measureAccuracy, that.measureAccuracy) && Objects.equals(measureHeight, that.measureHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), measureAccuracy, measureHeight);
    }

    @Override
    public String toString() {
        return "MeasuringTool{" +
                "measureAccuracy='" + measureAccuracy + '\'' +
                ", measureHeight='" + measureHeight + '\'' +
                "} " + super.toString();
    }
}
