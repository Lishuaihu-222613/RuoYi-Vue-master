package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import org.springframework.data.neo4j.core.schema.*;

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

    @Relationship(type = "hasRawMaterialElement" , direction = Relationship.Direction.OUTGOING)
    private Set<hasRawMaterialElement> materialElements;

    @Relationship(type = "hasUsage" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyProduct> product;

    @Relationship(type = "hasTypicalProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> typicalProcesses;



}
