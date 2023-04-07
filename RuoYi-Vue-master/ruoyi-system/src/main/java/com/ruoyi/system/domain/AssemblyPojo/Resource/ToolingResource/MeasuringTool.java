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

@Node({"量具工具", "MeasuringTool"})
public class MeasuringTool extends ToolingResource {

    @Property(name = "测量精度")
    private String measureAccuracy;

    @Property(name = "测量高度")
    private String measureHeight;

    @Property(name = "量具型号")
    private String modelNumber;

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

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public MeasuringTool() {
    }

    public MeasuringTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Site> sites, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures, String measureAccuracy, String measureHeight, String modelNumber) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, sites, sequences, steps, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses, unSuitableProcesses, suitableProcedures, unSuitableProcedures);
        this.measureAccuracy = measureAccuracy;
        this.measureHeight = measureHeight;
        this.modelNumber = modelNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MeasuringTool that = (MeasuringTool) o;
        return Objects.equals(measureAccuracy, that.measureAccuracy) && Objects.equals(measureHeight, that.measureHeight) && Objects.equals(modelNumber, that.modelNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), measureAccuracy, measureHeight, modelNumber);
    }

    @Override
    public String toString() {
        return "MeasuringTool{" +
                "measureAccuracy='" + measureAccuracy + '\'' +
                ", measureHeight='" + measureHeight + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                "} " + super.toString();
    }
}
