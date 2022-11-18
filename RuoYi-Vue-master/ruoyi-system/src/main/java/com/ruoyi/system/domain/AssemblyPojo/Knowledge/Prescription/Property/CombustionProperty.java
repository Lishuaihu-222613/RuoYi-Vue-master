package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("燃烧性能")
public class CombustionProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property("label")
    private String propertyName;

    @Property(name = "燃速压力指数")
    private double BRPressureIndex;

    @CompositeProperty(prefix = "燃速")
    private Map<String,String> burningRate;

    @CompositeProperty(prefix = "燃速温度指数")
    private Map<String,String> BRTemperatureSensitivity;

    @CompositeProperty(prefix = "压力温度指数")
    private Map<String,String> pressureTemperatureSensitivity;

}
