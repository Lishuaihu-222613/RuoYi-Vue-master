package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

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
}
