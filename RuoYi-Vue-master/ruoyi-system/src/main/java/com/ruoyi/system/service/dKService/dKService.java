package com.ruoyi.system.service.dKService;

import com.ruoyi.system.domain.KgBuilderPojo.model.DKSubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.resultItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;

import java.util.List;

public interface dKService {

    /**
     * 获得决策规则的所有规则前项
     * @param DkId
     * @return
     */
    List<conditionItem> getConditionItems(Long DkId);

    /**
     * 获得决策规则的所有规则后项
     * @param DkId
     * @return
     */
    List<resultItem> getResults(Long DkId);

    /**
     * 获得所有决策规则
     * @return
     */
    List<decisionKnowledge> getDecisionKnowledge();

    /**
     *
     * @param name
     * @return
     */
    decisionKnowledge getDecisionKnowledgeByName(String name);

    /**
     * 获得一条决策规则
     * @param DkId
     * @return
     */
    decisionKnowledge getDecisionKnowledgeById(Long DkId);

    /**
     * 创建决策规则，包含前项与后向
     * @param DI
     */
    decisionKnowledge createDecisionKnowledge(DKSubmitItem DI);

    /**
     * 创建前项节点
     * @param CI
     */
    conditionItem createCondition(conditionItem CI);

    /**
     * 创建后向节点
     * @param RI
     */
    resultItem createResult(resultItem RI);

    /**
     * 创建单个决策规则节点，不含前项和后向
     * @param DK
     */
    decisionKnowledge createSingleDK (decisionKnowledge DK);

    /**
     * 删除决策规则
     * @param DKId
     */
    void deleteDecision(Long DKId);

    /**
     * 删除前项节点
     * @param CIId
     */
    void deleteCondition(Long CIId);

    /**
     * 删除后项节点
     * @param RIId
     */
    void deleteResult(Long RIId);

    /**
     * 更新决策知识
     * @param DK
     * @return
     */
    decisionKnowledge updateDK(decisionKnowledge DK);

    /**
     * 更新前项节点
     * @param CI
     * @return
     */
    conditionItem updateCondition(conditionItem CI);

    /**
     * 更新后项节点
     * @param RI
     * @return
     */
    resultItem updateResult(resultItem RI);

    /**
     * 创建、更新前项关系
     * @param RuleC
     * @return
     */
    ruleConsequent createRuleC(ruleConsequent RuleC);

    /**
     * 创建、更新后项关系
     * @param RuleA
     * @return
     */
    ruleAntecedent createRuleA(ruleAntecedent RuleA);

    /**
     * 删除前项关系
     * @param RCId
     */
    void deleteRuleC(Long RCId);

    /**
     * 删除后项关系
     * @param RAId
     */
    void deleteRuleA(Long RAId);

}
