package com.ruoyi.system.service.KnowledgeService.DecisionRule.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule.DecisionRuleRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule.RuleConditionRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule.RuleResultRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.DecisionRule;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.RuleCondition;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.RuleResult;
import com.ruoyi.system.service.KnowledgeService.DecisionRule.DecisionRuleService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class DecisionRuleServiceImpl implements DecisionRuleService {
    
    @Resource
    private DecisionRuleRepository decisionRuleRepository;

    @Resource
    private RuleConditionRepository ruleConditionRepository;

    @Resource
    private RuleResultRepository ruleResultRepository;

    @Override
    public DecisionRule createDecisionRule(DecisionRule rule) {
        DecisionRule decisionRule = new DecisionRule(rule.getRuleId(),
                rule.getRuleName(), rule.getRuleDescription(),rule.getRuleSource(),
                rule.getDynamicLabels());
        DecisionRule singleRule = decisionRuleRepository.save(decisionRule);
        HashSet<RuleCondition> ruleConditions = new HashSet<>();
        HashSet<RuleResult> ruleResults = new HashSet<>();

        for (RuleCondition ruleCondition : rule.getConditions()) {
            RuleCondition newRuleCondition = ruleConditionRepository.save(ruleCondition);
            ruleConditions.add(newRuleCondition);
        };
        for (RuleResult ruleResult : rule.getResults()) {
            RuleResult newRuleResult = ruleResultRepository.save(ruleResult);
            ruleResults.add(newRuleResult);
        }
        singleRule.setConditions(ruleConditions);
        singleRule.setResults(ruleResults);
        return decisionRuleRepository.save(singleRule);
    }

    @Override
    public DecisionRule updateDecisionRule(DecisionRule rule) {
        Optional<DecisionRule> oRule = decisionRuleRepository.findById(rule.getRuleId());
        if(oRule.isPresent()){
            DecisionRule oldRule = oRule.get();
            oldRule.setRuleName(rule.getRuleName());
            oldRule.setRuleDescription(rule.getRuleDescription());
            oldRule.setRuleSource(rule.getRuleSource());
            oldRule.setDynamicLabels(rule.getDynamicLabels());
            ruleConditionRepository.deleteAll(oldRule.getConditions());
            List<RuleCondition> ruleConditions = ruleConditionRepository.saveAll(rule.getConditions());
            ruleResultRepository.deleteAll(oldRule.getResults());
            List<RuleResult> ruleResults = ruleResultRepository.saveAll(rule.getResults());
            oldRule.setConditions(new HashSet<>(ruleConditions));
            oldRule.setResults(new HashSet<>(ruleResults));
            DecisionRule newRule = decisionRuleRepository.save(oldRule);
            return newRule;
        }
        return null;
    }

    @Override
    public Page<DecisionRule> getAllDecisionRules(Pageable pageable) {
        return decisionRuleRepository.findAll(pageable);
    }

    @Override
    public Page<DecisionRule> getDecisionRulesByLabel(String dynamicLabel, Pageable pageable) {
        return decisionRuleRepository.findByRuleLabel(dynamicLabel,pageable);
    }

    @Override
    public Page<DecisionRule> getDecisionRulesByParams(Example<DecisionRule> example, Pageable pageable) {
        return decisionRuleRepository.findAll(example,pageable);
    }

    @Override
    public DecisionRule getDecisionRuleById(Long ruleId) {
        Optional<DecisionRule> decisionRule = decisionRuleRepository.findById(ruleId);
        if (decisionRule.isPresent()) {
            return decisionRule.get();
        }
        return null;
    }

    @Override
    public void deleteDecisionRule(Long ruleId) {
        decisionRuleRepository.deleteById(ruleId);
    }

    @Override
    public void deleteDecisionRules(Long[] ruleIds) {
        decisionRuleRepository.deleteAllById(Arrays.asList(ruleIds));
    }
    
    
}
