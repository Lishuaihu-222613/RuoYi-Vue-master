package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyMethod.AssemblyMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.domain.AssemblyPojo.Resource.Site;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"刀具工具","CuttingTool"})
public class CuttingTool extends ToolingResource{

    @Property(name = "切割刃数")
    private Integer cuttingEdges;

    @Property(name = "刀刃长度")
    private double edgeLength;

    @Property(name = "刀柄样式")
    private String shankStyle;

    @Property(name = "最大切深")
    private String maxCuttingAmount;

    @Property(name = "最大加工尺寸")
    private String maxMachiningSize;

    @Property(name = "经济精度")
    private String economicAccuracy;

    @Property(name = "加工粗糙度")
    private String machiningRoughness;

    @Property(name = "不适合加工情况")
    private String unsuitableCondition;

    @Relationship(type = "适合加工方法",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyMethod> suitableMethods;

    @Relationship(type = "适合加工材料",direction = Relationship.Direction.OUTGOING)
    private Set<Material> suitableMaterials;

    @Relationship(type = "不适合加工材料",direction = Relationship.Direction.OUTGOING)
    private Set<Material> unSuitableMaterial;

    public Integer getCuttingEdges() {
        return cuttingEdges;
    }

    public void setCuttingEdges(Integer cuttingEdges) {
        this.cuttingEdges = cuttingEdges;
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(double edgeLength) {
        this.edgeLength = edgeLength;
    }

    public String getShankStyle() {
        return shankStyle;
    }

    public void setShankStyle(String shankStyle) {
        this.shankStyle = shankStyle;
    }

    public String getMaxCuttingAmount() {
        return maxCuttingAmount;
    }

    public void setMaxCuttingAmount(String maxCuttingAmount) {
        this.maxCuttingAmount = maxCuttingAmount;
    }

    public String getMaxMachiningSize() {
        return maxMachiningSize;
    }

    public void setMaxMachiningSize(String maxMachiningSize) {
        this.maxMachiningSize = maxMachiningSize;
    }

    public String getEconomicAccuracy() {
        return economicAccuracy;
    }

    public void setEconomicAccuracy(String economicAccuracy) {
        this.economicAccuracy = economicAccuracy;
    }

    public String getMachiningRoughness() {
        return machiningRoughness;
    }

    public void setMachiningRoughness(String machiningRoughness) {
        this.machiningRoughness = machiningRoughness;
    }

    public String getUnsuitableCondition() {
        return unsuitableCondition;
    }

    public void setUnsuitableCondition(String unsuitableCondition) {
        this.unsuitableCondition = unsuitableCondition;
    }

    public Set<AssemblyMethod> getSuitableMethods() {
        return suitableMethods;
    }

    public void setSuitableMethods(Set<AssemblyMethod> suitableMethods) {
        this.suitableMethods = suitableMethods;
    }

    public Set<Material> getSuitableMaterials() {
        return suitableMaterials;
    }

    public void setSuitableMaterials(Set<Material> suitableMaterials) {
        this.suitableMaterials = suitableMaterials;
    }

    public Set<Material> getUnSuitableMaterial() {
        return unSuitableMaterial;
    }

    public void setUnSuitableMaterial(Set<Material> unSuitableMaterial) {
        this.unSuitableMaterial = unSuitableMaterial;
    }

    public CuttingTool() {
    }

    public CuttingTool(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Site> sites, Set<Sequence> sequences, Set<Step> steps, double toolPrice, String manufacturer, String toolSize, String materialType, String toolUsage, String toolSpecification, String toolState, double wearCondition, Map<String, Object> toolCapacity, Set<TypicalProcess> suitableProcesses, Set<TypicalProcess> unSuitableProcesses, Set<TypicalSequence> suitableProcedures, Set<TypicalSequence> unSuitableProcedures, Integer cuttingEdges, double edgeLength, String shankStyle, String maxCuttingAmount, String maxMachiningSize, String economicAccuracy, String machiningRoughness, String unsuitableCondition, Set<AssemblyMethod> suitableMethods, Set<Material> suitableMaterials, Set<Material> unSuitableMaterial) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, sites, sequences, steps, toolPrice, manufacturer, toolSize, materialType, toolUsage, toolSpecification, toolState, wearCondition, toolCapacity, suitableProcesses, unSuitableProcesses, suitableProcedures, unSuitableProcedures);
        this.cuttingEdges = cuttingEdges;
        this.edgeLength = edgeLength;
        this.shankStyle = shankStyle;
        this.maxCuttingAmount = maxCuttingAmount;
        this.maxMachiningSize = maxMachiningSize;
        this.economicAccuracy = economicAccuracy;
        this.machiningRoughness = machiningRoughness;
        this.unsuitableCondition = unsuitableCondition;
        this.suitableMethods = suitableMethods;
        this.suitableMaterials = suitableMaterials;
        this.unSuitableMaterial = unSuitableMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CuttingTool that = (CuttingTool) o;
        return Double.compare(that.edgeLength, edgeLength) == 0 && Objects.equals(cuttingEdges, that.cuttingEdges) && Objects.equals(shankStyle, that.shankStyle) && Objects.equals(maxCuttingAmount, that.maxCuttingAmount) && Objects.equals(maxMachiningSize, that.maxMachiningSize) && Objects.equals(economicAccuracy, that.economicAccuracy) && Objects.equals(machiningRoughness, that.machiningRoughness) && Objects.equals(unsuitableCondition, that.unsuitableCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuttingEdges, edgeLength, shankStyle, maxCuttingAmount, maxMachiningSize, economicAccuracy, machiningRoughness, unsuitableCondition);
    }

    @Override
    public String toString() {
        return "CuttingTool{" +
                "cuttingEdges=" + cuttingEdges +
                ", edgeLength=" + edgeLength +
                ", shankStyle='" + shankStyle + '\'' +
                ", maxCuttingAmount='" + maxCuttingAmount + '\'' +
                ", maxMachiningSize='" + maxMachiningSize + '\'' +
                ", economicAccuracy='" + economicAccuracy + '\'' +
                ", machiningRoughness='" + machiningRoughness + '\'' +
                ", unsuitableCondition='" + unsuitableCondition + '\'' +
                "} " + super.toString();
    }
}
