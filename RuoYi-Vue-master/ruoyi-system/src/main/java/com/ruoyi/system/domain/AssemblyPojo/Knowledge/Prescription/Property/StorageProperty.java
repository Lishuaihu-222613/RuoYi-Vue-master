package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;

@Node("贮存老化性能")
public class StorageProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "预估贮存期")
    private String estimatedStoragePeriod;

    @Property(name = "外观变化")
    private String appearanceChange;

    @Property(name = "物理性能变化")
    private String physicalPropertyChange;

    @Property(name = "化学性能变化")
    private String chemicalPropertyChange;

    @Property(name = "力学性能变化")
    private String forcePropertyChange;

    @Property(name = "燃烧性能变化")
    private String combustionPropertyChange;

    @Property(name = "药柱-衬层（绝热层）界面粘结强度变化")
    private String interfacialBondStrengthChange;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

}
