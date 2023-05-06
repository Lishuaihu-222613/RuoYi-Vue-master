package com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.RuleCondition;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RuleConditionRepository extends Neo4jRepository<RuleCondition,Long> {

    @Query("Match (n:RuleCondition)<-[r:hasRuleCondition]-(m) where id(m) = $ruleId return n")
    List<RuleCondition> findConditionsByRuleId(@Param("ruleId") Long ruleId);
}
