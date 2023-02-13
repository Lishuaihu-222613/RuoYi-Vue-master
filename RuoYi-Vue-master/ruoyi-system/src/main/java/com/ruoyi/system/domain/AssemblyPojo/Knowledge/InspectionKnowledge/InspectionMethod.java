package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"InspectionMethod","Method"})
public class InspectionMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "Description")
    private String methodDescription;

    @Property(name = "Principle")
    private String methodPrinciple;

    @Property(name = "FutureExpansion")
    private String futureExpansion;

    @Relationship(type = "hasCondition",direction = Relationship.Direction.OUTGOING)
    private Set<InspectionCondition> methodConditions;

    @Relationship(type = "hasFactor",direction = Relationship.Direction.OUTGOING)
    private Set<InspectionFactor> methodFactors;

    @Relationship(type = "hasMode",direction = Relationship.Direction.OUTGOING)
    private Set<InspectionMode> methodModes;

    @Relationship(type = "hasAssociatedProcess",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> associatedProcesses;

    @Relationship(type = "hasAssociatedProblem",direction = Relationship.Direction.OUTGOING)
    private Set<QualityProblem> associatedProblems;

    @Relationship(type = "hasAssociatedDevice",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> associatedDevices;

    @Relationship(type = "hasAssociatedFile",direction = Relationship.Direction.OUTGOING)
    private Set<FileKnowledge> associatedFiles;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String getMethodPrinciple() {
        return methodPrinciple;
    }

    public void setMethodPrinciple(String methodPrinciple) {
        this.methodPrinciple = methodPrinciple;
    }

    public String getFutureExpansion() {
        return futureExpansion;
    }

    public void setFutureExpansion(String futureExpansion) {
        this.futureExpansion = futureExpansion;
    }

    public Set<InspectionCondition> getMethodConditions() {
        return methodConditions;
    }

    public void setMethodConditions(Set<InspectionCondition> methodConditions) {
        this.methodConditions = methodConditions;
    }

    public Set<InspectionFactor> getMethodFactors() {
        return methodFactors;
    }

    public void setMethodFactors(Set<InspectionFactor> methodFactors) {
        this.methodFactors = methodFactors;
    }

    public Set<InspectionMode> getMethodModes() {
        return methodModes;
    }

    public void setMethodModes(Set<InspectionMode> methodModes) {
        this.methodModes = methodModes;
    }

    public Set<TypicalProcess> getAssociatedProcesses() {
        return associatedProcesses;
    }

    public void setAssociatedProcesses(Set<TypicalProcess> associatedProcesses) {
        this.associatedProcesses = associatedProcesses;
    }

    public Set<QualityProblem> getAssociatedProblems() {
        return associatedProblems;
    }

    public void setAssociatedProblems(Set<QualityProblem> associatedProblems) {
        this.associatedProblems = associatedProblems;
    }

    public Set<AssemblyResource> getAssociatedDevices() {
        return associatedDevices;
    }

    public void setAssociatedDevices(Set<AssemblyResource> associatedDevices) {
        this.associatedDevices = associatedDevices;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public InspectionMethod() {
    }

    public InspectionMethod(Long methodId, String methodName, String methodDescription, String methodPrinciple, String futureExpansion, Set<InspectionCondition> methodConditions, Set<InspectionFactor> methodFactors, Set<InspectionMode> methodModes, Set<TypicalProcess> associatedProcesses, Set<QualityProblem> associatedProblems, Set<AssemblyResource> associatedDevices, Set<FileKnowledge> associatedFiles) {
        this.methodId = methodId;
        this.methodName = methodName;
        this.methodDescription = methodDescription;
        this.methodPrinciple = methodPrinciple;
        this.futureExpansion = futureExpansion;
        this.methodConditions = methodConditions;
        this.methodFactors = methodFactors;
        this.methodModes = methodModes;
        this.associatedProcesses = associatedProcesses;
        this.associatedProblems = associatedProblems;
        this.associatedDevices = associatedDevices;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectionMethod that = (InspectionMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(methodName, that.methodName) && Objects.equals(methodDescription, that.methodDescription) && Objects.equals(methodPrinciple, that.methodPrinciple) && Objects.equals(futureExpansion, that.futureExpansion) && Objects.equals(methodConditions, that.methodConditions) && Objects.equals(methodFactors, that.methodFactors) && Objects.equals(methodModes, that.methodModes) && Objects.equals(associatedProcesses, that.associatedProcesses) && Objects.equals(associatedProblems, that.associatedProblems) && Objects.equals(associatedDevices, that.associatedDevices) && Objects.equals(associatedFiles, that.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, methodName, methodDescription, methodPrinciple, futureExpansion, methodConditions, methodFactors, methodModes, associatedProcesses, associatedProblems, associatedDevices, associatedFiles);
    }

    @Override
    public String toString() {
        return "InspectionMethod{" +
                "methodId=" + methodId +
                ", methodName='" + methodName + '\'' +
                ", methodDescription='" + methodDescription + '\'' +
                ", methodPrinciple='" + methodPrinciple + '\'' +
                ", futureExpansion='" + futureExpansion + '\'' +
                ", methodConditions=" + methodConditions +
                ", methodFactors=" + methodFactors +
                ", methodModes=" + methodModes +
                ", associatedProcesses=" + associatedProcesses +
                ", associatedProblems=" + associatedProblems +
                ", associatedDevices=" + associatedDevices +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
