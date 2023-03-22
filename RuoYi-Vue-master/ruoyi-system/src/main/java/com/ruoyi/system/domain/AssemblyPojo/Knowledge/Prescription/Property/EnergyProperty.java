package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node({"能量性能","EnergyProperty"})
public class EnergyProperty extends PrescriptionProperty{

    @Property(name = "爆热")
    private String explosiveHeat;

    @Property(name = "爆温")
    private String explosiveTemperature;

    @Property(name = "比容")
    private String specificCapacity;

    @CompositeProperty(prefix = "比冲")
    private Map<String,String> specificImpulse;

    @Property(name = "特征速度")
    private String characteristicVelocity;

    @Property(name = "燃烧温度")
    private String combustionTemperature;

    @Property(name = "燃烧产物平均分子质量")
    private double combustionAverageMolecularMass;

    @Property(name = "计算密度")
    private String calculatedDensity;

    @Property(name = "火药力")
    private String gunpowderPower;

    public String getExplosiveHeat() {
        return explosiveHeat;
    }

    public void setExplosiveHeat(String explosiveHeat) {
        this.explosiveHeat = explosiveHeat;
    }

    public String getExplosiveTemperature() {
        return explosiveTemperature;
    }

    public void setExplosiveTemperature(String explosiveTemperature) {
        this.explosiveTemperature = explosiveTemperature;
    }

    public String getSpecificCapacity() {
        return specificCapacity;
    }

    public void setSpecificCapacity(String specificCapacity) {
        this.specificCapacity = specificCapacity;
    }

    public Map<String, String> getSpecificImpulse() {
        return specificImpulse;
    }

    public void setSpecificImpulse(Map<String, String> specificImpulse) {
        this.specificImpulse = specificImpulse;
    }

    public String getCharacteristicVelocity() {
        return characteristicVelocity;
    }

    public void setCharacteristicVelocity(String characteristicVelocity) {
        this.characteristicVelocity = characteristicVelocity;
    }

    public String getCombustionTemperature() {
        return combustionTemperature;
    }

    public void setCombustionTemperature(String combustionTemperature) {
        this.combustionTemperature = combustionTemperature;
    }

    public double getCombustionAverageMolecularMass() {
        return combustionAverageMolecularMass;
    }

    public void setCombustionAverageMolecularMass(double combustionAverageMolecularMass) {
        this.combustionAverageMolecularMass = combustionAverageMolecularMass;
    }

    public String getCalculatedDensity() {
        return calculatedDensity;
    }

    public void setCalculatedDensity(String calculatedDensity) {
        this.calculatedDensity = calculatedDensity;
    }

    public String getGunpowderPower() {
        return gunpowderPower;
    }

    public void setGunpowderPower(String gunpowderPower) {
        this.gunpowderPower = gunpowderPower;
    }

    public EnergyProperty() {
    }

    public EnergyProperty(Long propertyId, String propertyName, Map<String, String> otherProperty, String explosiveHeat, String explosiveTemperature, String specificCapacity, Map<String, String> specificImpulse, String characteristicVelocity, String combustionTemperature, double combustionAverageMolecularMass, String calculatedDensity, String gunpowderPower) {
        super(propertyId, propertyName, otherProperty);
        this.explosiveHeat = explosiveHeat;
        this.explosiveTemperature = explosiveTemperature;
        this.specificCapacity = specificCapacity;
        this.specificImpulse = specificImpulse;
        this.characteristicVelocity = characteristicVelocity;
        this.combustionTemperature = combustionTemperature;
        this.combustionAverageMolecularMass = combustionAverageMolecularMass;
        this.calculatedDensity = calculatedDensity;
        this.gunpowderPower = gunpowderPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EnergyProperty that = (EnergyProperty) o;
        return Double.compare(that.combustionAverageMolecularMass, combustionAverageMolecularMass) == 0 && Objects.equals(explosiveHeat, that.explosiveHeat) && Objects.equals(explosiveTemperature, that.explosiveTemperature) && Objects.equals(specificCapacity, that.specificCapacity) && Objects.equals(specificImpulse, that.specificImpulse) && Objects.equals(characteristicVelocity, that.characteristicVelocity) && Objects.equals(combustionTemperature, that.combustionTemperature) && Objects.equals(calculatedDensity, that.calculatedDensity) && Objects.equals(gunpowderPower, that.gunpowderPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), explosiveHeat, explosiveTemperature, specificCapacity, specificImpulse, characteristicVelocity, combustionTemperature, combustionAverageMolecularMass, calculatedDensity, gunpowderPower);
    }

    @Override
    public String toString() {
        return "EnergyProperty{" +
                "explosiveHeat='" + explosiveHeat + '\'' +
                ", explosiveTemperature='" + explosiveTemperature + '\'' +
                ", specificCapacity='" + specificCapacity + '\'' +
                ", specificImpulse=" + specificImpulse +
                ", characteristicVelocity='" + characteristicVelocity + '\'' +
                ", combustionTemperature='" + combustionTemperature + '\'' +
                ", combustionAverageMolecularMass=" + combustionAverageMolecularMass +
                ", calculatedDensity='" + calculatedDensity + '\'' +
                ", gunpowderPower='" + gunpowderPower + '\'' +
                "} " + super.toString();
    }
}
