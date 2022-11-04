package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Set;

@Node("Prescription")
public class Prescription {

    @Id
    @GeneratedValue
    private Long prescriptionId;

    @Property(name = "label")
    private String prescriptionName;

    @Property(name = "Description")
    private String prescriptionDescription;

    @Property(name = "Density")
    private String density;

    @Property(name = "oxygenFuelRatio")
    private double oxygenFuelRatio;

    @Property(name = "ExplosiveHeat")
    private String explosiveHeat;

    @Property(name = "SpecificCapacity")
    private String specificCapacity;

    @Property(name = "SlurryViscosity")
    private String slurryViscosity;

    @Property(name = "SpecificImpulse")
    private String specificImpulse;

    @Property(name = "DensitySpecificImpulse")
    private String densitySpecificImpulse;

    @Property(name = "PressureIndex")
    private double pressureIndex;

    @Property(name = "BRPressureIndex")
    private double BRPressureIndex;

    @Property(name = "BurningRate")
    private Map<String,String> burningRate;

    @Property(name = "BRTemperatureSensitivity")
    private Map<String,String> BRTemperatureSensitivity;

    @Property(name = "PressureTemperatureSensitivity")
    private Map<String,String> pressureTemperatureSensitivity;

    @Property(name = "DangerLevel")
    private String dangerLevel;

    @DynamicLabels
    private Set<String> prescriptionLabels;

    @Relationship(type = "hasRawMaterialElement" , direction = Relationship.Direction.OUTGOING)
    private Set<hasRawMaterialElement> materialElements;

    @Relationship(type = "hasUsage" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyProduct> product;

    @Relationship(type = "hasTypicalProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> typicalProcesses;





}
