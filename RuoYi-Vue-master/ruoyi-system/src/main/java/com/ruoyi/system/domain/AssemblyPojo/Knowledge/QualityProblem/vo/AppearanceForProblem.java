package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;

public class AppearanceForProblem {

    private Long problemId;

    private Appearance appearance;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public AppearanceForProblem() {
    }

    public AppearanceForProblem(Long problemId, Appearance appearance) {
        this.problemId = problemId;
        this.appearance = appearance;
    }

    @Override
    public String toString() {
        return "AppearanceForProblem{" +
                "problemId=" + problemId +
                ", appearance=" + appearance +
                '}';
    }
}
