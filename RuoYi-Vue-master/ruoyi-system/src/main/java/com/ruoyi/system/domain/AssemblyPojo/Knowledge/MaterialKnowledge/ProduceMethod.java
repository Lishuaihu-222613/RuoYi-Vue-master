package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"ProduceMethod","Method"})
public class ProduceMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "Description")
    private String methodDescription;

    @Property(name = "Steps")
    private List<String> methodSteps;

    @Relationship(type = "hasAssociatedFile" , direction = Relationship.Direction.OUTGOING)
    private Set<fileKnowledge> associatedFiles;

    @Relationship(type = "hasAssociatedProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> associatedProcesses;

    @Relationship(type = "hasAssociatedProblem" , direction = Relationship.Direction.OUTGOING)
    private Set<QualityProblem> associatedProblems;

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

    public Set<fileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<fileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
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

    public List<String> getMethodSteps() {
        return methodSteps;
    }

    public void setMethodSteps(List<String> methodSteps) {
        this.methodSteps = methodSteps;
    }

    public ProduceMethod() {
    }

    public ProduceMethod(Long methodId, String methodName, String methodDescription, List<String> methodSteps, Set<fileKnowledge> associatedFiles, Set<TypicalProcess> associatedProcesses, Set<QualityProblem> associatedProblems) {
        this.methodId = methodId;
        this.methodName = methodName;
        this.methodDescription = methodDescription;
        this.methodSteps = methodSteps;
        this.associatedFiles = associatedFiles;
        this.associatedProcesses = associatedProcesses;
        this.associatedProblems = associatedProblems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduceMethod that = (ProduceMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(methodName, that.methodName) && Objects.equals(methodDescription, that.methodDescription) && Objects.equals(methodSteps, that.methodSteps) && Objects.equals(associatedFiles, that.associatedFiles) && Objects.equals(associatedProcesses, that.associatedProcesses) && Objects.equals(associatedProblems, that.associatedProblems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, methodName, methodDescription, methodSteps, associatedFiles, associatedProcesses, associatedProblems);
    }

    @Override
    public String toString() {
        return "ProduceMethod{" +
                "methodId=" + methodId +
                ", methodName='" + methodName + '\'' +
                ", methodDescription='" + methodDescription + '\'' +
                ", methodSteps=" + methodSteps +
                ", associatedFiles=" + associatedFiles +
                ", associatedProcesses=" + associatedProcesses +
                ", associatedProblems=" + associatedProblems +
                '}';
    }
}
