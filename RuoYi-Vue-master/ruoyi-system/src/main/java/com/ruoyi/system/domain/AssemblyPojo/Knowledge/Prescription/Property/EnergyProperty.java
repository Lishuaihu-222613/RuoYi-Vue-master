package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("能量性能")
public class EnergyProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "label")
    private String propertyName;

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

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;
}
