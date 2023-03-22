package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;

public class ReasonForProblem {

    private Long problemId;

    private Reason reason;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public ReasonForProblem() {
    }

    public ReasonForProblem(Long problemId, Reason reason) {
        this.problemId = problemId;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ReasonForProblem{" +
                "problemId=" + problemId +
                ", reason=" + reason +
                '}';
    }
}
