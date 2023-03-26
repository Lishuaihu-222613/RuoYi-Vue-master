package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"影响因素","InspectionFactor"})
public class InspectionFactor {

    @Id
    @GeneratedValue
    private Long factorId;

    @Property(name = "label")
    private String factorName;

    @Property(name = "因素描述")
    private String factorDescription;

    @Property(name = "控制因素")
    private List<String> controlFactors;

    public Long getFactorId() {
        return factorId;
    }

    public void setFactorId(Long factorId) {
        this.factorId = factorId;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorDescription() {
        return factorDescription;
    }

    public void setFactorDescription(String factorDescription) {
        this.factorDescription = factorDescription;
    }

    public List<String> getControlFactors() {
        return controlFactors;
    }

    public void setControlFactors(List<String> controlFactors) {
        this.controlFactors = controlFactors;
    }

    public InspectionFactor() {
    }

    public InspectionFactor(Long factorId, String factorName, String factorDescription, List<String> controlFactors) {
        this.factorId = factorId;
        this.factorName = factorName;
        this.factorDescription = factorDescription;
        this.controlFactors = controlFactors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectionFactor that = (InspectionFactor) o;
        return Objects.equals(factorId, that.factorId) && Objects.equals(factorName, that.factorName) && Objects.equals(factorDescription, that.factorDescription) && Objects.equals(controlFactors, that.controlFactors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factorId, factorName, factorDescription, controlFactors);
    }

    @Override
    public String toString() {
        return "InspectionFactor{" +
                "factorId=" + factorId +
                ", factorName='" + factorName + '\'' +
                ", factorDescription='" + factorDescription + '\'' +
                ", controlFactors=" + controlFactors +
                '}';
    }
}
