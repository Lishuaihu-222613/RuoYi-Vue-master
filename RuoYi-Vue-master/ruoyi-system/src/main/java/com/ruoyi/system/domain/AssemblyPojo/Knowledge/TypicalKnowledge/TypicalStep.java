package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node("TypicalStep")
public class TypicalStep {

    @Id
    @GeneratedValue
    private Long stepId;

    @Property(name = "label")
    private String stepName;

    @Property(name = "Description")
    private String stepDescription;

    @Property(name = "stepNumber")
    private String stepNumber;

    @Relationship(type = "use" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> useResources;

    @Relationship(type = "And",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalStep> stepAnd;

    @Relationship(type = "Or",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalStep> stepOr;

    @Relationship(type = "Before",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalStep> stepBefore;

    @Relationship(type = "After",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalStep> stepAfter;

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Set<AssemblyResource> getUseResources() {
        return useResources;
    }

    public void setUseResources(Set<AssemblyResource> useResources) {
        this.useResources = useResources;
    }

    public Set<TypicalStep> getStepAnd() {
        return stepAnd;
    }

    public void setStepAnd(Set<TypicalStep> stepAnd) {
        this.stepAnd = stepAnd;
    }

    public Set<TypicalStep> getStepOr() {
        return stepOr;
    }

    public void setStepOr(Set<TypicalStep> stepOr) {
        this.stepOr = stepOr;
    }

    public Set<TypicalStep> getStepBefore() {
        return stepBefore;
    }

    public void setStepBefore(Set<TypicalStep> stepBefore) {
        this.stepBefore = stepBefore;
    }

    public Set<TypicalStep> getStepAfter() {
        return stepAfter;
    }

    public void setStepAfter(Set<TypicalStep> stepAfter) {
        this.stepAfter = stepAfter;
    }

    public TypicalStep() {
    }

    public TypicalStep(Long stepId, String stepName, String stepDescription, String stepNumber, Set<AssemblyResource> useResources, Set<TypicalStep> stepAnd, Set<TypicalStep> stepOr, Set<TypicalStep> stepBefore, Set<TypicalStep> stepAfter) {
        this.stepId = stepId;
        this.stepName = stepName;
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
        this.useResources = useResources;
        this.stepAnd = stepAnd;
        this.stepOr = stepOr;
        this.stepBefore = stepBefore;
        this.stepAfter = stepAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypicalStep that = (TypicalStep) o;
        return Objects.equals(stepId, that.stepId) && Objects.equals(stepName, that.stepName) && Objects.equals(stepDescription, that.stepDescription) && Objects.equals(stepNumber, that.stepNumber) && Objects.equals(useResources, that.useResources) && Objects.equals(stepAnd, that.stepAnd) && Objects.equals(stepOr, that.stepOr) && Objects.equals(stepBefore, that.stepBefore) && Objects.equals(stepAfter, that.stepAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepId, stepName, stepDescription, stepNumber, useResources, stepAnd, stepOr, stepBefore, stepAfter);
    }

    @Override
    public String toString() {
        return "TypicalStep{" +
                "stepId=" + stepId +
                ", stepName='" + stepName + '\'' +
                ", stepDescription='" + stepDescription + '\'' +
                ", stepNumber='" + stepNumber + '\'' +
                ", useResources=" + useResources +
                ", stepAnd=" + stepAnd +
                ", stepOr=" + stepOr +
                ", stepBefore=" + stepBefore +
                ", stepAfter=" + stepAfter +
                '}';
    }
}
