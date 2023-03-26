package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;

import java.util.Objects;

public class ModeForMethod {

    private Long methodId;

    private InspectionMode mode;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public InspectionMode getMode() {
        return mode;
    }

    public void setMode(InspectionMode mode) {
        this.mode = mode;
    }

    public ModeForMethod() {
    }

    public ModeForMethod(Long methodId, InspectionMode mode) {
        this.methodId = methodId;
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeForMethod that = (ModeForMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(mode, that.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, mode);
    }

    @Override
    public String toString() {
        return "ModeForMethod{" +
                "methodId=" + methodId +
                ", mode=" + mode +
                '}';
    }
}
