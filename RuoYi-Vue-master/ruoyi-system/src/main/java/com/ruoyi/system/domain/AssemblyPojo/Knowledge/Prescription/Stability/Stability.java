package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("配方安定性")
public abstract class Stability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

    public Long getStabilityId() {
        return stabilityId;
    }

    public void setStabilityId(Long stabilityId) {
        this.stabilityId = stabilityId;
    }

    public String getStabilityName() {
        return stabilityName;
    }

    public void setStabilityName(String stabilityName) {
        this.stabilityName = stabilityName;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public Stability() {
    }

    public Stability(Long stabilityId, String stabilityName, Map<String, String> otherProperty) {
        this.stabilityId = stabilityId;
        this.stabilityName = stabilityName;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stability stability = (Stability) o;
        return Objects.equals(stabilityId, stability.stabilityId) && Objects.equals(stabilityName, stability.stabilityName) && Objects.equals(otherProperty, stability.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stabilityId, stabilityName, otherProperty);
    }

    @Override
    public String toString() {
        return "Stability{" +
                "stabilityId=" + stabilityId +
                ", stabilityName='" + stabilityName + '\'' +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
