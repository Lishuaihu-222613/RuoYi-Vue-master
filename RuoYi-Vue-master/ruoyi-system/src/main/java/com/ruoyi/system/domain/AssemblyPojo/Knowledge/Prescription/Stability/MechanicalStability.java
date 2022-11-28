package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("机械敏感性")
public class MechanicalStability extends Stability{

    @Property(name = "冲击感度")
    private double impactSensitivity;

    @Property(name = "摩擦感度")
    private double frictionSensitivity;

    @Property(name = "震动感度")
    private double vibrationSensitivity;

    @Property(name = "枪击感度")
    private double gunshotSensitivity;

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

    public MechanicalStability() {
    }

    public MechanicalStability(Long stabilityId, String stabilityName, Map<String, String> otherProperty, double impactSensitivity, double frictionSensitivity, double vibrationSensitivity, double gunshotSensitivity) {
        super(stabilityId, stabilityName, otherProperty);
        this.impactSensitivity = impactSensitivity;
        this.frictionSensitivity = frictionSensitivity;
        this.vibrationSensitivity = vibrationSensitivity;
        this.gunshotSensitivity = gunshotSensitivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MechanicalStability that = (MechanicalStability) o;
        return Double.compare(that.impactSensitivity, impactSensitivity) == 0 && Double.compare(that.frictionSensitivity, frictionSensitivity) == 0 && Double.compare(that.vibrationSensitivity, vibrationSensitivity) == 0 && Double.compare(that.gunshotSensitivity, gunshotSensitivity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), impactSensitivity, frictionSensitivity, vibrationSensitivity, gunshotSensitivity);
    }

    @Override
    public String toString() {
        return "MechanicalStability{" +
                "impactSensitivity=" + impactSensitivity +
                ", frictionSensitivity=" + frictionSensitivity +
                ", vibrationSensitivity=" + vibrationSensitivity +
                ", gunshotSensitivity=" + gunshotSensitivity +
                "} " + super.toString();
    }
}
