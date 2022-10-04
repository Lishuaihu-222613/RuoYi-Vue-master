package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;


public class TypicalProcess {

    private Long Id;

    private String processName;

    private String processDescription;

    private Set<TypicalProcedure> procedures;



}
