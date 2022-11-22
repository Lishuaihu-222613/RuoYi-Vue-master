package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("爆炸敏感性")
public class ExplosionStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @Property(name = "冲击试验")
    private String impactTest;

    @Property(name = "炸药分级")
    private String ExplosiveClassification;

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

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public ExplosionStability() {
    }

    public ExplosionStability(Long stabilityId, String stabilityName, String impactTest, String explosiveClassification, Map<String, String> otherProperty) {
        this.stabilityId = stabilityId;
        this.stabilityName = stabilityName;
        this.impactTest = impactTest;
        ExplosiveClassification = explosiveClassification;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplosionStability that = (ExplosionStability) o;
        return Objects.equals(stabilityId, that.stabilityId) && Objects.equals(stabilityName, that.stabilityName) && Objects.equals(impactTest, that.impactTest) && Objects.equals(ExplosiveClassification, that.ExplosiveClassification) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stabilityId, stabilityName, impactTest, ExplosiveClassification, otherProperty);
    }

    @Override
    public String toString() {
        return "ExplosionStability{" +
                "stabilityId=" + stabilityId +
                ", stabilityName='" + stabilityName + '\'' +
                ", impactTest='" + impactTest + '\'' +
                ", ExplosiveClassification='" + ExplosiveClassification + '\'' +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
