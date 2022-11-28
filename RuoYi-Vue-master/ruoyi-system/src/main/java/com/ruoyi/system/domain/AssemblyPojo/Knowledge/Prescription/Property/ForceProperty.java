package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("力学性能")
public class ForceProperty extends PrescriptionProperty{

    @Property(name = "应力")
    private String stress;

    @Property(name = "应变")
    private String strain;

    @Property(name = "弹性模量")
    private String elasticModulus;

    @CompositeProperty(prefix = "料浆黏度")
    private Map<String, String> Viscosity;

    @CompositeProperty(prefix = "抗拉强度")
    private Map<String, String> tensileStrength;

    @CompositeProperty(prefix = "抗压强度")
    private Map<String,String> compressiveStrength;

    @CompositeProperty(prefix = "延伸率")
    private Map<String,String> elongation;

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getElasticModulus() {
        return elasticModulus;
    }

    public void setElasticModulus(String elasticModulus) {
        this.elasticModulus = elasticModulus;
    }

    public Map<String, String> getViscosity() {
        return Viscosity;
    }

    public void setViscosity(Map<String, String> viscosity) {
        Viscosity = viscosity;
    }

    public Map<String, String> getTensileStrength() {
        return tensileStrength;
    }

    public void setTensileStrength(Map<String, String> tensileStrength) {
        this.tensileStrength = tensileStrength;
    }

    public Map<String, String> getCompressiveStrength() {
        return compressiveStrength;
    }

    public void setCompressiveStrength(Map<String, String> compressiveStrength) {
        this.compressiveStrength = compressiveStrength;
    }

    public Map<String, String> getElongation() {
        return elongation;
    }

    public void setElongation(Map<String, String> elongation) {
        this.elongation = elongation;
    }

    public ForceProperty() {
    }

    public ForceProperty(Long propertyId, String propertyName, Map<String, String> otherProperty, String stress, String strain, String elasticModulus, Map<String, String> viscosity, Map<String, String> tensileStrength, Map<String, String> compressiveStrength, Map<String, String> elongation) {
        super(propertyId, propertyName, otherProperty);
        this.stress = stress;
        this.strain = strain;
        this.elasticModulus = elasticModulus;
        Viscosity = viscosity;
        this.tensileStrength = tensileStrength;
        this.compressiveStrength = compressiveStrength;
        this.elongation = elongation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ForceProperty that = (ForceProperty) o;
        return Objects.equals(stress, that.stress) && Objects.equals(strain, that.strain) && Objects.equals(elasticModulus, that.elasticModulus) && Objects.equals(Viscosity, that.Viscosity) && Objects.equals(tensileStrength, that.tensileStrength) && Objects.equals(compressiveStrength, that.compressiveStrength) && Objects.equals(elongation, that.elongation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stress, strain, elasticModulus, Viscosity, tensileStrength, compressiveStrength, elongation);
    }

    @Override
    public String toString() {
        return "ForceProperty{" +
                "stress='" + stress + '\'' +
                ", strain='" + strain + '\'' +
                ", elasticModulus='" + elasticModulus + '\'' +
                ", Viscosity=" + Viscosity +
                ", tensileStrength=" + tensileStrength +
                ", compressiveStrength=" + compressiveStrength +
                ", elongation=" + elongation +
                "} " + super.toString();
    }
}
