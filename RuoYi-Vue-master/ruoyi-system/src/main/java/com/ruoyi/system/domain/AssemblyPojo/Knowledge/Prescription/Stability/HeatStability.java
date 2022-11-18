package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("热敏感性")
public class HeatStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

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

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;
}
