package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.Location;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"工具资源", "ToolingResource"})
public abstract class ToolingResource extends AssemblyResource {

    @Property(name = "价格")
    private double toolPrice;

    @Property(name = "制造商")
    private String manufacturer;

    @Property(name = "工具型号")
    private String toolSize;

    @Property(name = "材料")
    private String materialType;

    @Property(name = "用途")
    private String toolUsage;

    @Property(name = "规格")
    private String toolSpecification;

    @Property(name = "状态")
    private String toolState;

    @Property(name = "磨损状况")
    private double wearCondition;

    @Property(name = "能力指数")
    private Map<String, Object> toolCapacity;

    @Relationship(type = "适用工艺", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;

    @Relationship(type = "不适用工艺", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> unSuitableProcesses;

    @Relationship(type = "适用工序", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalSequence> suitableProcedures;

    @Relationship(type = "不适用工序", direction = Relationship.Direction.OUTGOING)
    private Set<TypicalSequence> unSuitableProcedures;

    public double getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(double toolPrice) {
        this.toolPrice = toolPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getToolSize() {
        return toolSize;
    }

    public void setToolSize(String toolSize) {
        this.toolSize = toolSize;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getToolUsage() {
        return toolUsage;
    }

    public void setToolUsage(String toolUsage) {
        this.toolUsage = toolUsage;
    }

    public String getToolSpecification() {
        return toolSpecification;
    }

    public void setToolSpecification(String toolSpecification) {
        this.toolSpecification = toolSpecification;
    }

    public String getToolState() {
        return toolState;
    }

    public void setToolState(String toolState) {
        this.toolState = toolState;
    }

    public double getWearCondition() {
        return wearCondition;
    }

    public void setWearCondition(double wearCondition) {
        this.wearCondition = wearCondition;
    }

    public Map<String, Object> getToolCapacity() {
        return toolCapacity;
    }

    public void setToolCapacity(Map<String, Object> toolCapacity) {
        this.toolCapacity = toolCapacity;
    }

    public Set<TypicalProcess> getSuitableProcesses() {
        return suitableProcesses;
    }

    public void setSuitableProcesses(Set<TypicalProcess> suitableProcesses) {
        this.suitableProcesses = suitableProcesses;
    }

    public Set<TypicalProcess> getUnSuitableProcesses() {
        return unSuitableProcesses;
    }

    public void setUnSuitableProcesses(Set<TypicalProcess> unSuitableProcesses) {
        this.unSuitableProcesses = unSuitableProcesses;
    }

    public Set<TypicalSequence> getSuitableProcedures() {
        return suitableProcedures;
    }

    public void setSuitableProcedures(Set<TypicalSequence> suitableProcedures) {
        this.suitableProcedures = suitableProcedures;
    }

    public Set<TypicalSequence> getUnSuitableProcedures() {
        return unSuitableProcedures;
    }

    public void setUnSuitableProcedures(Set<TypicalSequence> unSuitableProcedures) {
        this.unSuitableProcedures = unSuitableProcedures;
    }

    public ToolingResource() {
    }

    public ToolingResource(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Location> locations, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, locations, sequences, steps);
        this.toolPrice = toolPrice;
        this.manufacturer = manufacturer;
        this.toolSize = toolSize;
        this.materialType = materialType;
        this.toolUsage = toolUsage;
        this.toolSpecification = toolSpecification;
        this.toolState = toolState;
        this.wearCondition = wearCondition;
        this.toolCapacity = toolCapacity;
        this.suitableProcesses = suitableProcesses;
        this.unSuitableProcesses = unSuitableProcesses;
        this.suitableProcedures = suitableProcedures;
        this.unSuitableProcedures = unSuitableProcedures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ToolingResource that = (ToolingResource) o;
        return Double.compare(that.toolPrice, toolPrice) == 0 && Double.compare(that.wearCondition, wearCondition) == 0 && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(toolSize, that.toolSize) && Objects.equals(materialType, that.materialType) && Objects.equals(toolUsage, that.toolUsage) && Objects.equals(toolSpecification, that.toolSpecification) && Objects.equals(toolState, that.toolState) && Objects.equals(toolCapacity, that.toolCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity);
    }

    @Override
    public String toString() {
        return "ToolingResource{" +
                "toolPrice=" + toolPrice +
                ", manufacturer='" + manufacturer + '\'' +
                ", toolSize='" + toolSize + '\'' +
                ", materialType='" + materialType + '\'' +
                ", toolUsage='" + toolUsage + '\'' +
                ", toolSpecification='" + toolSpecification + '\'' +
                ", toolState='" + toolState + '\'' +
                ", wearCondition=" + wearCondition +
                ", toolCapacity=" + toolCapacity +
                "} " + super.toString();
    }
}
