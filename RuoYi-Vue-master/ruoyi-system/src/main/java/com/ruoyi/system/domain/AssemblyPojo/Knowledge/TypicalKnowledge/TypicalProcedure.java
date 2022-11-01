package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node("TypicalProcedure")
public class TypicalProcedure {

    @Id
    @GeneratedValue
    private Long procedureId;

    @Property(name = "label")
    private String procedureName;

    @Property(name = "operationNumber")
    private String operationNumber;

    @Property(name = "Description")
    private String procedureDescription;

    @Property(name = "Attentions")
    private List<String> procedureAttentions;

    @Property(name = "Theory")
    private String procedureTheory;

    @Relationship(type = "And",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> procedureAnd;

    @Relationship(type = "Or",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> procedureOr;

    @Relationship(type = "Before",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> procedureBefore;

    @Relationship(type = "After",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> procedureAfter;

    @Relationship(type = "hasSubStep",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalStep> subSteps;

    @Relationship(type = "use",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> useResources;

    @Relationship(type = "hasAssociatedProblem",direction = Relationship.Direction.OUTGOING)
    private Set<QualityProblem> associatedProblems;

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }

    public List<String> getProcedureAttentions() {
        return procedureAttentions;
    }

    public void setProcedureAttentions(List<String> procedureAttentions) {
        this.procedureAttentions = procedureAttentions;
    }

    public String getProcedureTheory() {
        return procedureTheory;
    }

    public void setProcedureTheory(String procedureTheory) {
        this.procedureTheory = procedureTheory;
    }

    public Set<TypicalProcedure> getProcedureAnd() {
        return procedureAnd;
    }

    public void setProcedureAnd(Set<TypicalProcedure> procedureAnd) {
        this.procedureAnd = procedureAnd;
    }

    public Set<TypicalProcedure> getProcedureOr() {
        return procedureOr;
    }

    public void setProcedureOr(Set<TypicalProcedure> procedureOr) {
        this.procedureOr = procedureOr;
    }

    public Set<TypicalProcedure> getProcedureBefore() {
        return procedureBefore;
    }

    public void setProcedureBefore(Set<TypicalProcedure> procedureBefore) {
        this.procedureBefore = procedureBefore;
    }

    public Set<TypicalProcedure> getProcedureAfter() {
        return procedureAfter;
    }

    public void setProcedureAfter(Set<TypicalProcedure> procedureAfter) {
        this.procedureAfter = procedureAfter;
    }

    public Set<TypicalStep> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(Set<TypicalStep> subSteps) {
        this.subSteps = subSteps;
    }

    public Set<AssemblyResource> getUseResources() {
        return useResources;
    }

    public void setUseResources(Set<AssemblyResource> useResources) {
        this.useResources = useResources;
    }

    public Set<QualityProblem> getAssociatedProblems() {
        return associatedProblems;
    }

    public void setAssociatedProblems(Set<QualityProblem> associatedProblems) {
        this.associatedProblems = associatedProblems;
    }

    public TypicalProcedure() {
    }

    public TypicalProcedure(Long procedureId, String procedureName, String operationNumber, String procedureDescription, List<String> procedureAttentions, String procedureTheory, Set<TypicalProcedure> procedureAnd, Set<TypicalProcedure> procedureOr, Set<TypicalProcedure> procedureBefore, Set<TypicalProcedure> procedureAfter, Set<TypicalStep> subSteps, Set<AssemblyResource> useResources, Set<QualityProblem> associatedProblems) {
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.operationNumber = operationNumber;
        this.procedureDescription = procedureDescription;
        this.procedureAttentions = procedureAttentions;
        this.procedureTheory = procedureTheory;
        this.procedureAnd = procedureAnd;
        this.procedureOr = procedureOr;
        this.procedureBefore = procedureBefore;
        this.procedureAfter = procedureAfter;
        this.subSteps = subSteps;
        this.useResources = useResources;
        this.associatedProblems = associatedProblems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypicalProcedure that = (TypicalProcedure) o;
        return Objects.equals(procedureId, that.procedureId) && Objects.equals(procedureName, that.procedureName) && Objects.equals(operationNumber, that.operationNumber) && Objects.equals(procedureDescription, that.procedureDescription) && Objects.equals(procedureAttentions, that.procedureAttentions) && Objects.equals(procedureTheory, that.procedureTheory) && Objects.equals(procedureAnd, that.procedureAnd) && Objects.equals(procedureOr, that.procedureOr) && Objects.equals(procedureBefore, that.procedureBefore) && Objects.equals(procedureAfter, that.procedureAfter) && Objects.equals(subSteps, that.subSteps) && Objects.equals(useResources, that.useResources) && Objects.equals(associatedProblems, that.associatedProblems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(procedureId, procedureName, operationNumber, procedureDescription, procedureAttentions, procedureTheory, procedureAnd, procedureOr, procedureBefore, procedureAfter, subSteps, useResources, associatedProblems);
    }

    @Override
    public String toString() {
        return "TypicalProcedure{" +
                "procedureId=" + procedureId +
                ", procedureName='" + procedureName + '\'' +
                ", operationNumber='" + operationNumber + '\'' +
                ", procedureDescription='" + procedureDescription + '\'' +
                ", procedureAttentions=" + procedureAttentions +
                ", procedureTheory='" + procedureTheory + '\'' +
                ", procedureAnd=" + procedureAnd +
                ", procedureOr=" + procedureOr +
                ", procedureBefore=" + procedureBefore +
                ", procedureAfter=" + procedureAfter +
                ", subSteps=" + subSteps +
                ", useResources=" + useResources +
                ", associatedProblems=" + associatedProblems +
                '}';
    }
}
