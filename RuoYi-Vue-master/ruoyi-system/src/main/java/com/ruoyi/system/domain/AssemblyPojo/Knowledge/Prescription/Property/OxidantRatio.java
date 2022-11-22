package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("氧化剂比例")
public class OxidantRatio {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property("label")
    private String propertyName;

    @Property(name = "氧燃比")
    private double oxygenFuelRatio;

    @Property(name = "氧系数")
    private String oxygenCoefficient;

    @Property(name = "氧平衡")
    private String oxygenBalance;

    @Property(name = "氧含量")
    private String oxygenContent;

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

    public double getOxygenFuelRatio() {
        return oxygenFuelRatio;
    }

    public void setOxygenFuelRatio(double oxygenFuelRatio) {
        this.oxygenFuelRatio = oxygenFuelRatio;
    }

    public String getOxygenCoefficient() {
        return oxygenCoefficient;
    }

    public void setOxygenCoefficient(String oxygenCoefficient) {
        this.oxygenCoefficient = oxygenCoefficient;
    }

    public String getOxygenBalance() {
        return oxygenBalance;
    }

    public void setOxygenBalance(String oxygenBalance) {
        this.oxygenBalance = oxygenBalance;
    }

    public String getOxygenContent() {
        return oxygenContent;
    }

    public void setOxygenContent(String oxygenContent) {
        this.oxygenContent = oxygenContent;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public OxidantRatio() {
    }

    public OxidantRatio(Long propertyId, String propertyName, double oxygenFuelRatio, String oxygenCoefficient, String oxygenBalance, String oxygenContent, Map<String, String> otherProperty) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.oxygenFuelRatio = oxygenFuelRatio;
        this.oxygenCoefficient = oxygenCoefficient;
        this.oxygenBalance = oxygenBalance;
        this.oxygenContent = oxygenContent;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OxidantRatio that = (OxidantRatio) o;
        return Double.compare(that.oxygenFuelRatio, oxygenFuelRatio) == 0 && Objects.equals(propertyId, that.propertyId) && Objects.equals(propertyName, that.propertyName) && Objects.equals(oxygenCoefficient, that.oxygenCoefficient) && Objects.equals(oxygenBalance, that.oxygenBalance) && Objects.equals(oxygenContent, that.oxygenContent) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, oxygenFuelRatio, oxygenCoefficient, oxygenBalance, oxygenContent, otherProperty);
    }

    @Override
    public String toString() {
        return "OxidantRatio{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", oxygenFuelRatio=" + oxygenFuelRatio +
                ", oxygenCoefficient='" + oxygenCoefficient + '\'' +
                ", oxygenBalance='" + oxygenBalance + '\'' +
                ", oxygenContent='" + oxygenContent + '\'' +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
