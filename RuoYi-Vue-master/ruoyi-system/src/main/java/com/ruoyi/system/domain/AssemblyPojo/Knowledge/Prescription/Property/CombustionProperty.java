package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("燃烧性能")
public class CombustionProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property("label")
    private String propertyName;

    @Property(name = "燃速压力指数")
    private double BRPressureIndex;

    @CompositeProperty(prefix = "燃速")
    private Map<String,String> burningRate;

    @CompositeProperty(prefix = "燃速温度指数")
    private Map<String,String> BRTemperatureSensitivity;

    @CompositeProperty(prefix = "压力温度指数")
    private Map<String,String> pressureTemperatureSensitivity;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

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

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public CombustionProperty() {
    }

    public CombustionProperty(Long propertyId, String propertyName, double BRPressureIndex, Map<String, String> burningRate, Map<String, String> BRTemperatureSensitivity, Map<String, String> pressureTemperatureSensitivity, Map<String, String> otherProperty) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.BRPressureIndex = BRPressureIndex;
        this.burningRate = burningRate;
        this.BRTemperatureSensitivity = BRTemperatureSensitivity;
        this.pressureTemperatureSensitivity = pressureTemperatureSensitivity;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombustionProperty that = (CombustionProperty) o;
        return Double.compare(that.BRPressureIndex, BRPressureIndex) == 0 && Objects.equals(propertyId, that.propertyId) && Objects.equals(propertyName, that.propertyName) && Objects.equals(burningRate, that.burningRate) && Objects.equals(BRTemperatureSensitivity, that.BRTemperatureSensitivity) && Objects.equals(pressureTemperatureSensitivity, that.pressureTemperatureSensitivity) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, BRPressureIndex, burningRate, BRTemperatureSensitivity, pressureTemperatureSensitivity, otherProperty);
    }

    @Override
    public String toString() {
        return "CombustionProperty{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", BRPressureIndex=" + BRPressureIndex +
                ", burningRate=" + burningRate +
                ", BRTemperatureSensitivity=" + BRTemperatureSensitivity +
                ", pressureTemperatureSensitivity=" + pressureTemperatureSensitivity +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
