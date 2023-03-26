package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement.GeometricElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.Location;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"夹具工具","FixtureTool"})
public class FixtureTool extends ToolingResource{

    @Property(name = "夹具型号")
    private String modelNumber;

    @Property(name = "夹紧元件")
    private String clampElement;

    @Property(name = "夹紧方式")
    private String clampStyle;

    @Property(name = "定位原件")
    private String positionElement;

    @Property(name = "定位方式")
    private String positionStyle;

    @Relationship(type = "适用加工面几何特征",direction = Relationship.Direction.OUTGOING)
    private Set<GeometricElement> suitableElements;

    @Relationship(type = "适用刀具",direction = Relationship.Direction.OUTGOING)
    private Set<CuttingTool> suitableTools;

    @Property(name = "适用工件尺寸")
    private String suitablePartSize;

    @Property(name = "适用工件形状")
    private String suitablePartShape;

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getClampElement() {
        return clampElement;
    }

    public void setClampElement(String clampElement) {
        this.clampElement = clampElement;
    }

    public String getClampStyle() {
        return clampStyle;
    }

    public void setClampStyle(String clampStyle) {
        this.clampStyle = clampStyle;
    }

    public String getPositionElement() {
        return positionElement;
    }

    public void setPositionElement(String positionElement) {
        this.positionElement = positionElement;
    }

    public String getPositionStyle() {
        return positionStyle;
    }

    public void setPositionStyle(String positionStyle) {
        this.positionStyle = positionStyle;
    }

    public Set<GeometricElement> getSuitableElements() {
        return suitableElements;
    }

    public void setSuitableElements(Set<GeometricElement> suitableElements) {
        this.suitableElements = suitableElements;
    }

    public Set<CuttingTool> getSuitableTools() {
        return suitableTools;
    }

    public void setSuitableTools(Set<CuttingTool> suitableTools) {
        this.suitableTools = suitableTools;
    }

    public String getSuitablePartSize() {
        return suitablePartSize;
    }

    public void setSuitablePartSize(String suitablePartSize) {
        this.suitablePartSize = suitablePartSize;
    }

    public String getSuitablePartShape() {
        return suitablePartShape;
    }

    public void setSuitablePartShape(String suitablePartShape) {
        this.suitablePartShape = suitablePartShape;
    }

    public FixtureTool() {
    }

    public FixtureTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Location> locations, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures, String modelNumber, String clampElement, String clampStyle, String positionElement, String positionStyle, Set<GeometricElement> suitableElements, Set<CuttingTool> suitableTools, String suitablePartSize, String suitablePartShape) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, locations, sequences, steps, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses, unSuitableProcesses, suitableProcedures, unSuitableProcedures);
        this.modelNumber = modelNumber;
        this.clampElement = clampElement;
        this.clampStyle = clampStyle;
        this.positionElement = positionElement;
        this.positionStyle = positionStyle;
        this.suitableElements = suitableElements;
        this.suitableTools = suitableTools;
        this.suitablePartSize = suitablePartSize;
        this.suitablePartShape = suitablePartShape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixtureTool that = (FixtureTool) o;
        return Objects.equals(modelNumber, that.modelNumber) && Objects.equals(clampElement, that.clampElement) && Objects.equals(clampStyle, that.clampStyle) && Objects.equals(positionElement, that.positionElement) && Objects.equals(positionStyle, that.positionStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modelNumber, clampElement, clampStyle, positionElement, positionStyle);
    }

    @Override
    public String toString() {
        return "FixtureTool{" +
                "modelNumber='" + modelNumber + '\'' +
                ", clampElement='" + clampElement + '\'' +
                ", clampStyle='" + clampStyle + '\'' +
                ", positionElement='" + positionElement + '\'' +
                ", positionStyle='" + positionStyle + '\'' +
                ", suitableElements=" + suitableElements +
                ", suitableTools=" + suitableTools +
                ", suitablePartSize='" + suitablePartSize + '\'' +
                ", suitablePartShape='" + suitablePartShape + '\'' +
                "} " + super.toString();
    }
}
