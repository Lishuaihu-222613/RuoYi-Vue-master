package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("撞击敏感性")
public class ImpactStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @Property(name = "冲击感度")
    private double impactSensitivity;

    @Property(name = "摩擦感度")
    private double frictionSensitivity;

    @Property(name = "震动感度")
    private double vibrationSensitivity;

    @Property(name = "枪击感度")
    private double gunshotSensitivity;

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

    public double getImpactSensitivity() {
        return impactSensitivity;
    }

    public void setImpactSensitivity(double impactSensitivity) {
        this.impactSensitivity = impactSensitivity;
    }

    public double getFrictionSensitivity() {
        return frictionSensitivity;
    }

    public void setFrictionSensitivity(double frictionSensitivity) {
        this.frictionSensitivity = frictionSensitivity;
    }

    public double getVibrationSensitivity() {
        return vibrationSensitivity;
    }

    public void setVibrationSensitivity(double vibrationSensitivity) {
        this.vibrationSensitivity = vibrationSensitivity;
    }

    public double getGunshotSensitivity() {
        return gunshotSensitivity;
    }

    public void setGunshotSensitivity(double gunshotSensitivity) {
        this.gunshotSensitivity = gunshotSensitivity;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public ImpactStability() {
    }

    public ImpactStability(Long stabilityId, String stabilityName, double impactSensitivity, double frictionSensitivity, double vibrationSensitivity, double gunshotSensitivity, Map<String, String> otherProperty) {
        this.stabilityId = stabilityId;
        this.stabilityName = stabilityName;
        this.impactSensitivity = impactSensitivity;
        this.frictionSensitivity = frictionSensitivity;
        this.vibrationSensitivity = vibrationSensitivity;
        this.gunshotSensitivity = gunshotSensitivity;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImpactStability that = (ImpactStability) o;
        return Double.compare(that.impactSensitivity, impactSensitivity) == 0 && Double.compare(that.frictionSensitivity, frictionSensitivity) == 0 && Double.compare(that.vibrationSensitivity, vibrationSensitivity) == 0 && Double.compare(that.gunshotSensitivity, gunshotSensitivity) == 0 && Objects.equals(stabilityId, that.stabilityId) && Objects.equals(stabilityName, that.stabilityName) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stabilityId, stabilityName, impactSensitivity, frictionSensitivity, vibrationSensitivity, gunshotSensitivity, otherProperty);
    }

    @Override
    public String toString() {
        return "ImpactStability{" +
                "stabilityId=" + stabilityId +
                ", stabilityName='" + stabilityName + '\'' +
                ", impactSensitivity=" + impactSensitivity +
                ", frictionSensitivity=" + frictionSensitivity +
                ", vibrationSensitivity=" + vibrationSensitivity +
                ", gunshotSensitivity=" + gunshotSensitivity +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
