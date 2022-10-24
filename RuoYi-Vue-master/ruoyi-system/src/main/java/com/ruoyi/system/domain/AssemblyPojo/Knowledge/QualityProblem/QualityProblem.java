package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;


import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("QualityProblem")
public class QualityProblem {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String problemName;

    @Property(name = "Description")
    private String problemDescription;

    @Relationship(type = "hasReason" , direction = Relationship.Direction.OUTGOING)
    private Set<Reason> reasons;

    @Relationship(type = "hasSolution" , direction = Relationship.Direction.OUTGOING)
    private Set<Solution> solutions;

    @Relationship(type = "hasAppearance" , direction = Relationship.Direction.OUTGOING)
    private Set<Appearance> appearances;


}
