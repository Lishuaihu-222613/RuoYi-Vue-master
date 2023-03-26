package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;

import java.util.Objects;

public class ConditionForMethod {

    private Long methodId;

    private InspectionCondition condition;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public InspectionCondition getCondition() {
        return condition;
    }

    public void setCondition(InspectionCondition condition) {
        this.condition = condition;
    }

    public ConditionForMethod() {
    }

    public ConditionForMethod(Long methodId, InspectionCondition condition) {
        this.methodId = methodId;
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionForMethod that = (ConditionForMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, condition);
    }

    @Override
    public String toString() {
        return "ConditionForMethod{" +
                "methodId=" + methodId +
                ", condition=" + condition +
                '}';
    }
}
