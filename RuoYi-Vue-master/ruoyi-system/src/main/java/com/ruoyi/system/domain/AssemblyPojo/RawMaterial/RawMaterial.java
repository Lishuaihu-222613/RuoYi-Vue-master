package com.ruoyi.system.domain.AssemblyPojo.RawMaterial;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

@Node()
public abstract class RawMaterial {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String MaterialName;

    @Property(name = "Description")
    private String Description;

    @Property(name = "Appearance")
    private String Appearance;

    private String  productionBatch;

    private String manufacturer;

    private Date productionDate;

    private String responsible;

    @Relationship(type = "hasTreatmentProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> TreatmentProcess;

}
