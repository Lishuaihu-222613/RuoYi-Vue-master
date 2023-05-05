package com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Node({"决策规则","DecisionRule"})
public class DecisionRule {

    @Id
    @GeneratedValue
    private Long ruleId;

    @Property(name = "label")
    private String ruleName;

    @Property(name = "规则描述")
    private String ruleDescription;

    @Property(name = "规则来源")
    private String ruleSource;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Relationship(type = "hasRuleCondition")
    private Set<RuleCondition> conditions;

    @Relationship(type = "hasRuleResult")
    private Set<RuleResult> results;

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Set<RuleCondition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<RuleCondition> conditions) {
        this.conditions = conditions;
    }

    public Set<RuleResult> getResults() {
        return results;
    }

    public void setResults(Set<RuleResult> results) {
        this.results = results;
    }

    public DecisionRule() {
    }

    public DecisionRule(Long ruleId, String ruleName, String ruleDescription, String ruleSource, Set<String> dynamicLabels) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.ruleSource = ruleSource;
        this.dynamicLabels = dynamicLabels;
    }

    public DecisionRule(Long ruleId, String ruleName, String ruleDescription, String ruleSource, Set<String> dynamicLabels, Set<RuleCondition> conditions, Set<RuleResult> results) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.ruleSource = ruleSource;
        this.dynamicLabels = dynamicLabels;
        this.conditions = conditions;
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecisionRule that = (DecisionRule) o;
        return Objects.equals(ruleId, that.ruleId) && Objects.equals(ruleName, that.ruleName) && Objects.equals(ruleDescription, that.ruleDescription) && Objects.equals(ruleSource, that.ruleSource) && Objects.equals(dynamicLabels, that.dynamicLabels) && Objects.equals(conditions, that.conditions) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId, ruleName, ruleDescription, ruleSource, dynamicLabels, conditions, results);
    }

    @Override
    public String toString() {
        return "DecisionRule{" +
                "ruleId=" + ruleId +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", ruleSource='" + ruleSource + '\'' +
                ", dynamicLabels=" + dynamicLabels +
                ", conditions=" + conditions +
                ", results=" + results +
                '}';
    }
}
