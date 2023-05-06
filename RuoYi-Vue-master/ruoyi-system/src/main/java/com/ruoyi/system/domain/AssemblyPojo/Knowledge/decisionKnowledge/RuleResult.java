package com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"规则结果 ","RuleResult"})
public class RuleResult {

    @Id
    @GeneratedValue
    private Long resultId;

    @Property(name = "结果类型")
    private String resultCluster;

    @Property(name = "label")
    private String resultContent;

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getResultCluster() {
        return resultCluster;
    }

    public void setResultCluster(String resultCluster) {
        this.resultCluster = resultCluster;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public RuleResult() {
    }

    public RuleResult(Long resultId, String resultCluster, String resultContent) {
        this.resultId = resultId;
        this.resultCluster = resultCluster;
        this.resultContent = resultContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleResult that = (RuleResult) o;
        return Objects.equals(resultId, that.resultId) && Objects.equals(resultCluster, that.resultCluster) && Objects.equals(resultContent, that.resultContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultId, resultCluster, resultContent);
    }

    @Override
    public String toString() {
        return "RuleResult{" +
                "resultId=" + resultId +
                ", resultCluster='" + resultCluster + '\'' +
                ", resultContent='" + resultContent + '\'' +
                '}';
    }
}
