package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node({"辐射敏感性","RadioStability"})
public class RadioStability extends Stability{

    @Property(name = "辐射感度")
    private double radiationSensitivity;

    public double getRadiationSensitivity() {
        return radiationSensitivity;
    }

    public void setRadiationSensitivity(double radiationSensitivity) {
        this.radiationSensitivity = radiationSensitivity;
    }

    public RadioStability() {
    }

    public RadioStability(Long stabilityId, String stabilityName, Map<String, String> otherProperty, double radiationSensitivity) {
        super(stabilityId, stabilityName, otherProperty);
        this.radiationSensitivity = radiationSensitivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RadioStability that = (RadioStability) o;
        return Double.compare(that.radiationSensitivity, radiationSensitivity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radiationSensitivity);
    }

    @Override
    public String toString() {
        return "RadioStability{" +
                "radiationSensitivity=" + radiationSensitivity +
                "} " + super.toString();
    }
}
