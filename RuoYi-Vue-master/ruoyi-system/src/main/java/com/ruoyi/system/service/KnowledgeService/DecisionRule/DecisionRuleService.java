package com.ruoyi.system.service.KnowledgeService.DecisionRule;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.DecisionRule;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DecisionRuleService {
    DecisionRule createDecisionRule(DecisionRule rule);

    DecisionRule updateDecisionRule(DecisionRule rule);

    Page<DecisionRule> getAllDecisionRules(Pageable pageable);

    Page<DecisionRule> getDecisionRulesByLabel(String dynamicLabel, Pageable pageable);

    Page<DecisionRule> getDecisionRulesByParams(Example<DecisionRule> example, Pageable pageable);

    DecisionRule getDecisionRuleById(Long ruleId);

    void deleteDecisionRule(Long ruleId);

    void deleteDecisionRules(Long[] ruleIds);
}
