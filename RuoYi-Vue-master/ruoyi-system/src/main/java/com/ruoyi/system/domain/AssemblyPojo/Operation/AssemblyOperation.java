package com.ruoyi.system.domain.AssemblyPojo.Operation;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Movement.Move;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Node("AssemblyOperation")
public class AssemblyOperation {

    @Id
    @GeneratedValue
    private Long operationId;

    @Property(name = "label")
    private String operationName;

    @Property(name = "operationNumber")
    private String operationNumber;

    @Property(name = "Description")
    private String operationDescription;

    @Property(name = "BeginAt")
    private Date beginTime;

    @Property(name = "EndAt")
    private Date endTime;

    @Relationship(type = "use", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> resources;

    @Relationship(type = "hasMove", direction = Relationship.Direction.OUTGOING)
    private Move move;

    @Relationship(type = "assembling" , direction = Relationship.Direction.OUTGOING)
    private AssemblingStructure assemblingStructure;

    @Relationship(type = "assembled", direction = Relationship.Direction.OUTGOING)
    private AssembledStructure assembledStructure;

    @Relationship(type = "AndOP", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> operationAnd;

    @Relationship(type = "OrOP", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> operationOr;

    @Relationship(type = "assembleBefore", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> beforeOperation;

    @Relationship(type = "assembleAfter", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> afterOperation;

    @Relationship(type = "hasAssociatedSteps", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyStep> associatedSteps;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
    private Set<fileKnowledge> associatedFiles;

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Set<AssemblyResource> getResources() {
        return resources;
    }

    public void setResources(Set<AssemblyResource> resources) {
        this.resources = resources;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public AssemblingStructure getAssemblingStructure() {
        return assemblingStructure;
    }

    public void setAssemblingStructure(AssemblingStructure assemblingStructure) {
        this.assemblingStructure = assemblingStructure;
    }

    public AssembledStructure getAssembledStructure() {
        return assembledStructure;
    }

    public void setAssembledStructure(AssembledStructure assembledStructure) {
        this.assembledStructure = assembledStructure;
    }

    public Set<AssemblyOperation> getOperationAnd() {
        return operationAnd;
    }

    public void setOperationAnd(Set<AssemblyOperation> operationAnd) {
        this.operationAnd = operationAnd;
    }

    public Set<AssemblyOperation> getOperationOr() {
        return operationOr;
    }

    public void setOperationOr(Set<AssemblyOperation> operationOr) {
        this.operationOr = operationOr;
    }

    public Set<AssemblyOperation> getBeforeOperation() {
        return beforeOperation;
    }

    public void setBeforeOperation(Set<AssemblyOperation> beforeOperation) {
        this.beforeOperation = beforeOperation;
    }

    public Set<AssemblyOperation> getAfterOperation() {
        return afterOperation;
    }

    public void setAfterOperation(Set<AssemblyOperation> afterOperation) {
        this.afterOperation = afterOperation;
    }

    public Set<AssemblyStep> getAssociatedSteps() {
        return associatedSteps;
    }

    public void setAssociatedSteps(Set<AssemblyStep> associatedSteps) {
        this.associatedSteps = associatedSteps;
    }

    public Set<fileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<fileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public AssemblyOperation() {
    }

    public AssemblyOperation(Long operationId, String operationName, String operationNumber, String operationDescription, Date beginTime, Date endTime, Set<AssemblyResource> resources, Move move, AssemblingStructure assemblingStructure, AssembledStructure assembledStructure, Set<AssemblyOperation> operationAnd, Set<AssemblyOperation> operationOr, Set<AssemblyOperation> beforeOperation, Set<AssemblyOperation> afterOperation, Set<AssemblyStep> associatedSteps, Set<fileKnowledge> associatedFiles) {
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.resources = resources;
        this.move = move;
        this.assemblingStructure = assemblingStructure;
        this.assembledStructure = assembledStructure;
        this.operationAnd = operationAnd;
        this.operationOr = operationOr;
        this.beforeOperation = beforeOperation;
        this.afterOperation = afterOperation;
        this.associatedSteps = associatedSteps;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyOperation that = (AssemblyOperation) o;
        return Objects.equals(operationId, that.operationId) && Objects.equals(operationName, that.operationName) && Objects.equals(operationNumber, that.operationNumber) && Objects.equals(operationDescription, that.operationDescription) && Objects.equals(beginTime, that.beginTime) && Objects.equals(endTime, that.endTime) && Objects.equals(resources, that.resources) && Objects.equals(move, that.move) && Objects.equals(assemblingStructure, that.assemblingStructure) && Objects.equals(assembledStructure, that.assembledStructure) && Objects.equals(operationAnd, that.operationAnd) && Objects.equals(operationOr, that.operationOr) && Objects.equals(beforeOperation, that.beforeOperation) && Objects.equals(afterOperation, that.afterOperation) && Objects.equals(associatedSteps, that.associatedSteps) && Objects.equals(associatedFiles, that.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, operationName, operationNumber, operationDescription, beginTime, endTime, resources, move, assemblingStructure, assembledStructure, operationAnd, operationOr, beforeOperation, afterOperation, associatedSteps, associatedFiles);
    }

    @Override
    public String toString() {
        return "AssemblyOperation{" +
                "operationId=" + operationId +
                ", operationName='" + operationName + '\'' +
                ", operationNumber='" + operationNumber + '\'' +
                ", operationDescription='" + operationDescription + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", resources=" + resources +
                ", move=" + move +
                ", assemblingStructure=" + assemblingStructure +
                ", assembledStructure=" + assembledStructure +
                ", operationAnd=" + operationAnd +
                ", operationOr=" + operationOr +
                ", beforeOperation=" + beforeOperation +
                ", afterOperation=" + afterOperation +
                ", associatedSteps=" + associatedSteps +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
