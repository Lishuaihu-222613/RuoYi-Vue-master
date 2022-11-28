package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("氧化剂比例")
public class OxidantRatio extends PrescriptionProperty{

    @Property(name = "氧燃比")
    private double oxygenFuelRatio;

    @Property(name = "氧系数")
    private String oxygenCoefficient;

    @Property(name = "氧平衡")
    private String oxygenBalance;

    @Property(name = "氧含量")
    private String oxygenContent;

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

    public OxidantRatio() {
    }

    public OxidantRatio(Long propertyId, String propertyName, Map<String, String> otherProperty, double oxygenFuelRatio, String oxygenCoefficient, String oxygenBalance, String oxygenContent) {
        super(propertyId, propertyName, otherProperty);
        this.oxygenFuelRatio = oxygenFuelRatio;
        this.oxygenCoefficient = oxygenCoefficient;
        this.oxygenBalance = oxygenBalance;
        this.oxygenContent = oxygenContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OxidantRatio that = (OxidantRatio) o;
        return Double.compare(that.oxygenFuelRatio, oxygenFuelRatio) == 0 && Objects.equals(oxygenCoefficient, that.oxygenCoefficient) && Objects.equals(oxygenBalance, that.oxygenBalance) && Objects.equals(oxygenContent, that.oxygenContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oxygenFuelRatio, oxygenCoefficient, oxygenBalance, oxygenContent);
    }

    @Override
    public String toString() {
        return "OxidantRatio{" +
                "oxygenFuelRatio=" + oxygenFuelRatio +
                ", oxygenCoefficient='" + oxygenCoefficient + '\'' +
                ", oxygenBalance='" + oxygenBalance + '\'' +
                ", oxygenContent='" + oxygenContent + '\'' +
                "} " + super.toString();
    }
}
