package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;

import java.util.Objects;

public class FactorForMethod {

    private Long methodId;

    private InspectionFactor factor;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public InspectionFactor getFactor() {
        return factor;
    }

    public void setFactor(InspectionFactor factor) {
        this.factor = factor;
    }

    public FactorForMethod() {
    }

    public FactorForMethod(Long methodId, InspectionFactor factor) {
        this.methodId = methodId;
        this.factor = factor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorForMethod that = (FactorForMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(factor, that.factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, factor);
    }

    @Override
    public String toString() {
        return "FactorForMethod{" +
                "methodId=" + methodId +
                ", factor=" + factor +
                '}';
    }
}
