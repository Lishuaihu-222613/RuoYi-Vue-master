package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("爆炸敏感性")
public class ExplosionStability extends Stability{

    @Property(name = "冲击试验")
    private String impactTest;

    @Property(name = "炸药分级")
    private String ExplosiveClassification;

    public String getImpactTest() {
        return impactTest;
    }

    public void setImpactTest(String impactTest) {
        this.impactTest = impactTest;
    }

    public String getExplosiveClassification() {
        return ExplosiveClassification;
    }

    public void setExplosiveClassification(String explosiveClassification) {
        ExplosiveClassification = explosiveClassification;
    }

    public ExplosionStability() {
    }

    public ExplosionStability(Long stabilityId, String stabilityName, Map<String, String> otherProperty, String impactTest, String explosiveClassification) {
        super(stabilityId, stabilityName, otherProperty);
        this.impactTest = impactTest;
        ExplosiveClassification = explosiveClassification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExplosionStability that = (ExplosionStability) o;
        return Objects.equals(impactTest, that.impactTest) && Objects.equals(ExplosiveClassification, that.ExplosiveClassification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), impactTest, ExplosiveClassification);
    }

    @Override
    public String toString() {
        return "ExplosionStability{" +
                "impactTest='" + impactTest + '\'' +
                ", ExplosiveClassification='" + ExplosiveClassification + '\'' +
                "} " + super.toString();
    }
}
