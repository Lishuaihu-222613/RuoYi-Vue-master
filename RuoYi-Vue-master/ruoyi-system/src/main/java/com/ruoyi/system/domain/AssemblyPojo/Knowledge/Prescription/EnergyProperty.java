package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("能量特性热力学计算结果")
public class EnergyProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "label")
    private String propertyName;

    @Property(name = "理论比冲")
    private String theoreticalSpecificImpulse;

    @Property(name = "特征速度")
    private String characteristicVelocity;

    @Property(name = "燃烧温度")
    private String combustionTemperature;

    @Property(name = "燃烧产物平均分子质量")
    private double combustionAverageMolecularMass;

    @Property(name = "计算密度")
    private String calculatedDensity;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;
}
