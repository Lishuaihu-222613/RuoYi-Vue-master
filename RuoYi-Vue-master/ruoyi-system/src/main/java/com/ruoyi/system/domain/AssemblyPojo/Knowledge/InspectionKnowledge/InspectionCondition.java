package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"检验条件","InspectionCondition"})
public class InspectionCondition {

    @Id
    @GeneratedValue
    private Long conditionId;

    @Property(name = "label")
    private String conditionName;

    @Property(name = "条件描述")
    private String conditionDescription;

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public InspectionCondition() {
    }

    public InspectionCondition(Long conditionId, String conditionName, String conditionDescription) {
        this.conditionId = conditionId;
        this.conditionName = conditionName;
        this.conditionDescription = conditionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectionCondition that = (InspectionCondition) o;
        return Objects.equals(conditionId, that.conditionId) && Objects.equals(conditionName, that.conditionName) && Objects.equals(conditionDescription, that.conditionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionId, conditionName, conditionDescription);
    }

    @Override
    public String toString() {
        return "InspectionCondition{" +
                "conditionId=" + conditionId +
                ", conditionName='" + conditionName + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                '}';
    }
}
