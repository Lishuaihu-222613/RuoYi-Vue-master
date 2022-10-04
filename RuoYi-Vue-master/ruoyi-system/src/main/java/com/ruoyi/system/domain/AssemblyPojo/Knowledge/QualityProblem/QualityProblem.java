package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;


import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node("QualityProblem")
public class QualityProblem {

    private Long Id;

    private String problemName;

    private String problemDescription;

    private Set<Reason> reasons;

    private Set<Solution> solutions;

    private Set<Appearance> appearances;


}
