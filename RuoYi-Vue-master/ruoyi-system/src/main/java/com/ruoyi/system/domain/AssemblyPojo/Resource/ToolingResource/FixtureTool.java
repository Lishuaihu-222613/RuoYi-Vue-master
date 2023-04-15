package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"夹具工具","FixtureTool"})
public class FixtureTool extends ToolingResource{

    @Property(name = "夹紧元件")
    private String clampElement;

    @Property(name = "夹紧方式")
    private String clampStyle;

    @Property(name = "定位原件")
    private String positionElement;

    @Property(name = "定位方式")
    private String positionStyle;

    @Property(name = "适用工件尺寸")
    private String suitablePartSize;

    @Property(name = "适用工件形状")
    private String suitablePartShape;

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

    public FixtureTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, List<String> suitableProcesses, String clampElement, String clampStyle, String positionElement, String positionStyle, String suitablePartSize, String suitablePartShape) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses);
        this.clampElement = clampElement;
        this.clampStyle = clampStyle;
        this.positionElement = positionElement;
        this.positionStyle = positionStyle;
        this.suitablePartSize = suitablePartSize;
        this.suitablePartShape = suitablePartShape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixtureTool that = (FixtureTool) o;
        return Objects.equals(clampElement, that.clampElement) && Objects.equals(clampStyle, that.clampStyle) && Objects.equals(positionElement, that.positionElement) && Objects.equals(positionStyle, that.positionStyle) && Objects.equals(suitablePartSize, that.suitablePartSize) && Objects.equals(suitablePartShape, that.suitablePartShape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clampElement, clampStyle, positionElement, positionStyle, suitablePartSize, suitablePartShape);
    }

    @Override
    public String toString() {
        return "FixtureTool{" +
                "clampElement='" + clampElement + '\'' +
                ", clampStyle='" + clampStyle + '\'' +
                ", positionElement='" + positionElement + '\'' +
                ", positionStyle='" + positionStyle + '\'' +
                ", suitablePartSize='" + suitablePartSize + '\'' +
                ", suitablePartShape='" + suitablePartShape + '\'' +
                "} " + super.toString();
    }
}
