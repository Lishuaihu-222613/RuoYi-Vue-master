package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;


import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"QualityProblem","质量问题"})
public class QualityProblem {

    @Id
    @GeneratedValue
    private Long problemId;

    @Property(name = "label")
    private String problemName;

    @Property(name = "问题描述")
    private String problemDescription;

    @Relationship(type = "产生原因" , direction = Relationship.Direction.OUTGOING)
    private Set<Reason> reasons;

    @Relationship(type = "解决方法" , direction = Relationship.Direction.OUTGOING)
    private Set<Solution> solutions;

    @Relationship(type = "外观表现" , direction = Relationship.Direction.OUTGOING)
    private Set<Appearance> appearances;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Set<Reason> getReasons() {
        return reasons;
    }

    public void setReasons(Set<Reason> reasons) {
        this.reasons = reasons;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
    }

    public Set<Appearance> getAppearances() {
        return appearances;
    }

    public void setAppearances(Set<Appearance> appearances) {
        this.appearances = appearances;
    }

    public QualityProblem() {
    }

    public QualityProblem(Long problemId, String problemName, String problemDescription, Set<Reason> reasons, Set<Solution> solutions, Set<Appearance> appearances) {
        this.problemId = problemId;
        this.problemName = problemName;
        this.problemDescription = problemDescription;
        this.reasons = reasons;
        this.solutions = solutions;
        this.appearances = appearances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityProblem that = (QualityProblem) o;
        return Objects.equals(problemId, that.problemId) && Objects.equals(problemName, that.problemName) && Objects.equals(problemDescription, that.problemDescription) && Objects.equals(reasons, that.reasons) && Objects.equals(solutions, that.solutions) && Objects.equals(appearances, that.appearances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId, problemName, problemDescription, reasons, solutions, appearances);
    }

    @Override
    public String toString() {
        return "QualityProblem{" +
                "problemId=" + problemId +
                ", problemName='" + problemName + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", reasons=" + reasons +
                ", solutions=" + solutions +
                ", appearances=" + appearances +
                '}';
    }
}
