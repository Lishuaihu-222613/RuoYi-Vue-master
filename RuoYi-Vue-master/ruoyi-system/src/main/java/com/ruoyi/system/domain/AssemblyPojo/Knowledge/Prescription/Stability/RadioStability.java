package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("辐射敏感性")
public class RadioStability {

    @Id
    @GeneratedValue
    private Long stabilityId;

    @Property(name = "label")
    private String stabilityName;

    @Property(name = "辐射感度")
    private double radiationSensitivity;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;
}
