package com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.RuleResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RuleResultRepository extends Neo4jRepository<RuleResult,Long> {

    @Query("Match (n:RuleResult)<-[r:hasRuleResult]-(m) where id(m) = $ruleId return n")
    List<RuleResult> findResultsByRuleId(@Param("ruleId") Long ruleId);
}
