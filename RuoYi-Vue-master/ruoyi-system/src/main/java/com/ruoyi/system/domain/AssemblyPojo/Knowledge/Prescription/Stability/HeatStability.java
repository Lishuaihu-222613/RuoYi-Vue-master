package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("热敏感性")
public class HeatStability extends Stability{

    @Property(name = "维也里简单法")
    private String simpleVieri;

    @Property(name = "维也里重复法")
    private String repetitionVieri;

    @Property(name = "自燃温度")
    private String autoignitionTemperature;

    @Property(name = "明火感度")
    private String openFireSensitivity;

    @Property(name = "电火花感度")
    private String sparkSensitivity;

    @Property(name = "闪点")
    private String flashPoint;

    @Property(name = "燃点")
    private String ignitionPoint;

    @Property(name = "爆炸极限")
    private String explosionLimit;

    public String getSimpleVieri() {
        return simpleVieri;
    }

    public void setSimpleVieri(String simpleVieri) {
        this.simpleVieri = simpleVieri;
    }

    public String getRepetitionVieri() {
        return repetitionVieri;
    }

    public void setRepetitionVieri(String repetitionVieri) {
        this.repetitionVieri = repetitionVieri;
    }

    public String getAutoignitionTemperature() {
        return autoignitionTemperature;
    }

    public void setAutoignitionTemperature(String autoignitionTemperature) {
        this.autoignitionTemperature = autoignitionTemperature;
    }

    public String getOpenFireSensitivity() {
        return openFireSensitivity;
    }

    public void setOpenFireSensitivity(String openFireSensitivity) {
        this.openFireSensitivity = openFireSensitivity;
    }

    public String getSparkSensitivity() {
        return sparkSensitivity;
    }

    public void setSparkSensitivity(String sparkSensitivity) {
        this.sparkSensitivity = sparkSensitivity;
    }

    public String getFlashPoint() {
        return flashPoint;
    }

    public void setFlashPoint(String flashPoint) {
        this.flashPoint = flashPoint;
    }

    public String getIgnitionPoint() {
        return ignitionPoint;
    }

    public void setIgnitionPoint(String ignitionPoint) {
        this.ignitionPoint = ignitionPoint;
    }

    public String getExplosionLimit() {
        return explosionLimit;
    }

    public void setExplosionLimit(String explosionLimit) {
        this.explosionLimit = explosionLimit;
    }

    public HeatStability() {
    }

    public HeatStability(Long stabilityId, String stabilityName, Map<String, String> otherProperty, String simpleVieri, String repetitionVieri, String autoignitionTemperature, String openFireSensitivity, String sparkSensitivity, String flashPoint, String ignitionPoint, String explosionLimit) {
        super(stabilityId, stabilityName, otherProperty);
        this.simpleVieri = simpleVieri;
        this.repetitionVieri = repetitionVieri;
        this.autoignitionTemperature = autoignitionTemperature;
        this.openFireSensitivity = openFireSensitivity;
        this.sparkSensitivity = sparkSensitivity;
        this.flashPoint = flashPoint;
        this.ignitionPoint = ignitionPoint;
        this.explosionLimit = explosionLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HeatStability that = (HeatStability) o;
        return Objects.equals(simpleVieri, that.simpleVieri) && Objects.equals(repetitionVieri, that.repetitionVieri) && Objects.equals(autoignitionTemperature, that.autoignitionTemperature) && Objects.equals(openFireSensitivity, that.openFireSensitivity) && Objects.equals(sparkSensitivity, that.sparkSensitivity) && Objects.equals(flashPoint, that.flashPoint) && Objects.equals(ignitionPoint, that.ignitionPoint) && Objects.equals(explosionLimit, that.explosionLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), simpleVieri, repetitionVieri, autoignitionTemperature, openFireSensitivity, sparkSensitivity, flashPoint, ignitionPoint, explosionLimit);
    }

    @Override
    public String toString() {
        return "HeatStability{" +
                "simpleVieri='" + simpleVieri + '\'' +
                ", repetitionVieri='" + repetitionVieri + '\'' +
                ", autoignitionTemperature='" + autoignitionTemperature + '\'' +
                ", openFireSensitivity='" + openFireSensitivity + '\'' +
                ", sparkSensitivity='" + sparkSensitivity + '\'' +
                ", flashPoint='" + flashPoint + '\'' +
                ", ignitionPoint='" + ignitionPoint + '\'' +
                ", explosionLimit='" + explosionLimit + '\'' +
                "} " + super.toString();
    }
}
