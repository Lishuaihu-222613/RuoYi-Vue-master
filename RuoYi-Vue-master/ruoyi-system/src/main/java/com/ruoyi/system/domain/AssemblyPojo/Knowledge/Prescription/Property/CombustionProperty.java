package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node({"燃烧性能","CombustionProperty"})
public class CombustionProperty extends PrescriptionProperty{

    @Property(name = "燃速压力指数")
    private double BRPressureIndex;

    @CompositeProperty(prefix = "燃速")
    private Map<String,String> burningRate;

    @CompositeProperty(prefix = "燃速温度指数")
    private Map<String,String> BRTemperatureSensitivity;

    @CompositeProperty(prefix = "压力温度指数")
    private Map<String,String> pressureTemperatureSensitivity;

    public double getBRPressureIndex() {
        return BRPressureIndex;
    }

    public void setBRPressureIndex(double BRPressureIndex) {
        this.BRPressureIndex = BRPressureIndex;
    }

    public Map<String, String> getBurningRate() {
        return burningRate;
    }

    public void setBurningRate(Map<String, String> burningRate) {
        this.burningRate = burningRate;
    }

    public Map<String, String> getBRTemperatureSensitivity() {
        return BRTemperatureSensitivity;
    }

    public void setBRTemperatureSensitivity(Map<String, String> BRTemperatureSensitivity) {
        this.BRTemperatureSensitivity = BRTemperatureSensitivity;
    }

    public Map<String, String> getPressureTemperatureSensitivity() {
        return pressureTemperatureSensitivity;
    }

    public void setPressureTemperatureSensitivity(Map<String, String> pressureTemperatureSensitivity) {
        this.pressureTemperatureSensitivity = pressureTemperatureSensitivity;
    }

    public CombustionProperty() {
    }

    public CombustionProperty(Long propertyId, String propertyName, Map<String, String> otherProperty, double BRPressureIndex, Map<String, String> burningRate, Map<String, String> BRTemperatureSensitivity, Map<String, String> pressureTemperatureSensitivity) {
        super(propertyId, propertyName, otherProperty);
        this.BRPressureIndex = BRPressureIndex;
        this.burningRate = burningRate;
        this.BRTemperatureSensitivity = BRTemperatureSensitivity;
        this.pressureTemperatureSensitivity = pressureTemperatureSensitivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CombustionProperty that = (CombustionProperty) o;
        return Double.compare(that.BRPressureIndex, BRPressureIndex) == 0 && Objects.equals(burningRate, that.burningRate) && Objects.equals(BRTemperatureSensitivity, that.BRTemperatureSensitivity) && Objects.equals(pressureTemperatureSensitivity, that.pressureTemperatureSensitivity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BRPressureIndex, burningRate, BRTemperatureSensitivity, pressureTemperatureSensitivity);
    }

    @Override
    public String toString() {
        return "CombustionProperty{" +
                "BRPressureIndex=" + BRPressureIndex +
                ", burningRate=" + burningRate +
                ", BRTemperatureSensitivity=" + BRTemperatureSensitivity +
                ", pressureTemperatureSensitivity=" + pressureTemperatureSensitivity +
                "} " + super.toString();
    }
}
