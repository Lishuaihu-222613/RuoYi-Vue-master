package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("力学性能")
public class ForceProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "label")
    private String propertyName;

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

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

}
