package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.TypicalKnowledge.TypicalProcess;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.Set;

@Node("EquipmentResource")
public class EquipmentResource extends AssemblyResource{

    @Property(name = "Capacity")
    private String equipmentCapacity;

    @Property(name = "Power")
    private double equipmentPower;

    @Property(name = "Price")
    private double equipmentPrice;

    @Property(name = "productionDate")
    private Date productionDate;

    @Property(name = "manufacturer")
    private String manufacturer;

    @Property(name = "Size")
    private String equipmentSize;

    @Property(name = "State")
    private String equipmentState;

    @Property(name = "depreciationRate")
    private double depreciationRate;

    @Property(name = "loadRate")
    private double loadRate;

    @Relationship(type = "hasSuitableTool" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> suitableTools;

    @Relationship(type = "hasSuitableProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;


}
