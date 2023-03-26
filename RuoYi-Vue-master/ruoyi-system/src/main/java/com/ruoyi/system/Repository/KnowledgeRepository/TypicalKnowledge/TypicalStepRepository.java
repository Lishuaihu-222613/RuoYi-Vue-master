package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalStep;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TypicalStepRepository extends Neo4jRepository<TypicalStep,Long> {
}
