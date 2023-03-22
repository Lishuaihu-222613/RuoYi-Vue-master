package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;

public class SolutionForProblem {

    private Long problemId;

    private Solution solution;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public SolutionForProblem() {
    }

    public SolutionForProblem(Long problemId, Solution solution) {
        this.problemId = problemId;
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "SolutionForProblem{" +
                "problemId=" + problemId +
                ", solution=" + solution +
                '}';
    }
}
