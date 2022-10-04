package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;


public class TypicalProcedure {

    private Long Id;

    private String procedureName;

    private String procedureDescription;

    private Set<String> procedureAttention;

    private String procedureTheory;

    private Set<AssemblyResource> resources;

    private Set<QualityProblem> problems;

}
