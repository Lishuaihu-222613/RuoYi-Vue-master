package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("撞击敏感性")
public class ImpactStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @Property(name = "冲击感度")
    private double impactSensitivity;

    @Property(name = "摩擦感度")
    private double frictionSensitivity;

    @Property(name = "震动感度")
    private double vibrationSensitivity;

    @Property(name = "枪击感度")
    private double gunshotSensitivity;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

}
