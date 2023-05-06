package com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"规则条件 ","RuleCondition"})
public class RuleCondition {

    @Id
    @GeneratedValue
    private Long conditionId;

    @Property(name = "条件类型")
    private String conditionCluster;

    @Property(name = "label")
    private String conditionContent;

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionCluster() {
        return conditionCluster;
    }

    public void setConditionCluster(String conditionCluster) {
        this.conditionCluster = conditionCluster;
    }

    public String getConditionContent() {
        return conditionContent;
    }

    public void setConditionContent(String conditionContent) {
        this.conditionContent = conditionContent;
    }

    public RuleCondition() {
    }

    public RuleCondition(Long conditionId, String conditionCluster, String conditionContent) {
        this.conditionId = conditionId;
        this.conditionCluster = conditionCluster;
        this.conditionContent = conditionContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleCondition that = (RuleCondition) o;
        return Objects.equals(conditionId, that.conditionId) && Objects.equals(conditionCluster, that.conditionCluster) && Objects.equals(conditionContent, that.conditionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionId, conditionCluster, conditionContent);
    }

    @Override
    public String toString() {
        return "RuleCondition{" +
                "conditionId=" + conditionId +
                ", conditionCluster='" + conditionCluster + '\'' +
                ", conditionContent='" + conditionContent + '\'' +
                '}';
    }
}
