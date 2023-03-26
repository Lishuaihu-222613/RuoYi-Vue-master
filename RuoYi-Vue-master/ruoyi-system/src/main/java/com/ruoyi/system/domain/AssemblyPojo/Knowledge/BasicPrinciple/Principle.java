package com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"设计原则","Principle"})
public class Principle {

    @Id
    @GeneratedValue
    private Long principleId;

    @Property(name = "label")
    private String principleName;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Property(name = "设计原则内容")
    private String principleDescription;

    @Property(name = "应用条件")
    private List<String> principleConditions;

    @Property(name = "应用效果 ")
    private List<String> principleResults;

    public Long getPrincipleId() {
        return principleId;
    }

    public void setPrincipleId(Long principleId) {
        this.principleId = principleId;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public String getPrincipleDescription() {
        return principleDescription;
    }

    public void setPrincipleDescription(String principleDescription) {
        this.principleDescription = principleDescription;
    }

    public List<String> getPrincipleConditions() {
        return principleConditions;
    }

    public void setPrincipleConditions(List<String> principleConditions) {
        this.principleConditions = principleConditions;
    }

    public List<String> getPrincipleResults() {
        return principleResults;
    }

    public void setPrincipleResults(List<String> principleResults) {
        this.principleResults = principleResults;
    }

    public Principle() {
    }

    public Principle(Long principleId, String principleName, Set<String> dynamicLabels, String principleDescription, List<String> principleConditions, List<String> principleResults) {
        this.principleId = principleId;
        this.principleName = principleName;
        this.dynamicLabels = dynamicLabels;
        this.principleDescription = principleDescription;
        this.principleConditions = principleConditions;
        this.principleResults = principleResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principle principle = (Principle) o;
        return Objects.equals(principleId, principle.principleId) && Objects.equals(principleName, principle.principleName) && Objects.equals(dynamicLabels, principle.dynamicLabels) && Objects.equals(principleDescription, principle.principleDescription) && Objects.equals(principleConditions, principle.principleConditions) && Objects.equals(principleResults, principle.principleResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principleId, principleName, dynamicLabels, principleDescription, principleConditions, principleResults);
    }

    @Override
    public String toString() {
        return "Principle{" +
                "principleId=" + principleId +
                ", principleName='" + principleName + '\'' +
                ", dynamicLabels=" + dynamicLabels +
                ", principleDescription='" + principleDescription + '\'' +
                ", principleConditions=" + principleConditions +
                ", principleResults=" + principleResults +
                '}';
    }
}
