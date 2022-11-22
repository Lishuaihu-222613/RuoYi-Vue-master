package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("辐射敏感性")
public class RadioStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @Property(name = "辐射感度")
    private double radiationSensitivity;

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

    public double getRadiationSensitivity() {
        return radiationSensitivity;
    }

    public void setRadiationSensitivity(double radiationSensitivity) {
        this.radiationSensitivity = radiationSensitivity;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public RadioStability() {
    }

    public RadioStability(Long stabilityId, String stabilityName, double radiationSensitivity, Map<String, String> otherProperty) {
        this.stabilityId = stabilityId;
        this.stabilityName = stabilityName;
        this.radiationSensitivity = radiationSensitivity;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioStability that = (RadioStability) o;
        return Double.compare(that.radiationSensitivity, radiationSensitivity) == 0 && Objects.equals(stabilityId, that.stabilityId) && Objects.equals(stabilityName, that.stabilityName) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stabilityId, stabilityName, radiationSensitivity, otherProperty);
    }

    @Override
    public String toString() {
        return "RadioStability{" +
                "stabilityId=" + stabilityId +
                ", stabilityName='" + stabilityName + '\'' +
                ", radiationSensitivity=" + radiationSensitivity +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
