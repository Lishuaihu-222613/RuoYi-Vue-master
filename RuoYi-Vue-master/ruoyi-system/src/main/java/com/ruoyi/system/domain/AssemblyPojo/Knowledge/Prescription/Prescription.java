package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.EnergyProperty;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.ForceProperty;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ImpactStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Set;

@Node("推进剂配方")
public class Prescription {

    @Id
    @GeneratedValue
    private Long prescriptionId;

    @Property(name = "label")
    private String prescriptionName;

    @Property(name = "描述")
    private String prescriptionDescription;

    @Property(name = "密度")
    private String density;

    @Property(name = "危险等级")
    private String dangerLevel;

    @CompositeProperty(prefix = "固化时间")
    private Map<String,String> curingTime;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

    @DynamicLabels
    private Set<String> prescriptionLabels;

    @Relationship(type = "hasMaterialElement" , direction = Relationship.Direction.OUTGOING)
    private Set<hasRawMaterialElement> materialElements;

    @Relationship(type = "hasEnergyProperty" , direction = Relationship.Direction.OUTGOING)
    private EnergyProperty energyProperty;

    @Relationship(type = "hasForceProperty" , direction = Relationship.Direction.OUTGOING)
    private ForceProperty forceProperty;

    @Relationship(type = "hasHeatStability" , direction = Relationship.Direction.OUTGOING)
    private HeatStability heatStability;

    @Relationship(type = "hasImpactStability" , direction = Relationship.Direction.OUTGOING)
    private ImpactStability impactStability;

    @Relationship(type = "hasRadioStability" , direction = Relationship.Direction.OUTGOING)
    private RadioStability radioStability;

    @Relationship(type = "hasExplosionStability" , direction = Relationship.Direction.OUTGOING)
    private ExplosionStability explosionStability;

    @Relationship(type = "hasUsage" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyProduct> product;

    @Relationship(type = "hasTypicalProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> typicalProcesses;



}
