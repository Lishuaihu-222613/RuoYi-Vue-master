package com.ruoyi.system.domain.AssemblyPojo.Process;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"Step","装药工步"})
public class  Step {

    @Id
    @GeneratedValue
    private Long stepId;

    @Property(name = "label")
    private String stepName;

    @Property(name = "工步号")
    private String stepNumber;

    @Property(name = "工步内容")
    private String stepDescription;

    @Property(name = "技术要求")
    private String stepRequirement;

    @Relationship(type = "使用工具" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> associatedResources;

    @Property(name = "检验要求")
    private String inspectRequirement;

    @Relationship(type = "前道工步" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> beforeStep;

    @Relationship(type = "后道工步" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> afterStep;

    @Relationship(type = "工步与" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> andStep;

    @Relationship(type = "工步或" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> orStep;

    @Relationship(type = "子工步" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> subStep;

    public Step() {
    }

    public Step(Long stepId, String stepName, String stepNumber, String stepDescription, String stepRequirement, Set<AssemblyResource> associatedResources, String inspectRequirement, Set<Step> beforeStep, Set<Step> afterStep, Set<Step> andStep, Set<Step> orStep, Set<Step> subStep) {
        this.stepId = stepId;
        this.stepName = stepName;
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
        this.stepRequirement = stepRequirement;
        this.associatedResources = associatedResources;
        this.inspectRequirement = inspectRequirement;
        this.beforeStep = beforeStep;
        this.afterStep = afterStep;
        this.andStep = andStep;
        this.orStep = orStep;
        this.subStep = subStep;
    }

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

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public String getStepRequirement() {
        return stepRequirement;
    }

    public void setStepRequirement(String stepRequirement) {
        this.stepRequirement = stepRequirement;
    }

    public Set<AssemblyResource> getAssociatedResources() {
        return associatedResources;
    }

    public void setAssociatedResources(Set<AssemblyResource> associatedResources) {
        this.associatedResources = associatedResources;
    }

    public String getInspectRequirement() {
        return inspectRequirement;
    }

    public void setInspectRequirement(String inspectRequirement) {
        this.inspectRequirement = inspectRequirement;
    }

    public Set<Step> getBeforeStep() {
        return beforeStep;
    }

    public void setBeforeStep(Set<Step> beforeStep) {
        this.beforeStep = beforeStep;
    }

    public Set<Step> getAfterStep() {
        return afterStep;
    }

    public void setAfterStep(Set<Step> afterStep) {
        this.afterStep = afterStep;
    }

    public Set<Step> getAndStep() {
        return andStep;
    }

    public void setAndStep(Set<Step> andStep) {
        this.andStep = andStep;
    }

    public Set<Step> getOrStep() {
        return orStep;
    }

    public void setOrStep(Set<Step> orStep) {
        this.orStep = orStep;
    }

    public Set<Step> getSubStep() {
        return subStep;
    }

    public void setSubStep(Set<Step> subStep) {
        this.subStep = subStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(stepId, step.stepId) && Objects.equals(stepName, step.stepName) && Objects.equals(stepNumber, step.stepNumber) && Objects.equals(stepDescription, step.stepDescription) && Objects.equals(stepRequirement, step.stepRequirement) && Objects.equals(inspectRequirement, step.inspectRequirement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepId, stepName, stepNumber, stepDescription, stepRequirement, inspectRequirement);
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepId=" + stepId +
                ", stepName='" + stepName + '\'' +
                ", stepNumber='" + stepNumber + '\'' +
                ", stepDescription='" + stepDescription + '\'' +
                ", stepRequirement='" + stepRequirement + '\'' +
                ", associatedResources=" + associatedResources +
                ", inspectRequirement='" + inspectRequirement + '\'' +
                ", beforeStep=" + beforeStep +
                ", afterStep=" + afterStep +
                ", andStep=" + andStep +
                ", orStep=" + orStep +
                ", subStep=" + subStep +
                '}';
    }
}
