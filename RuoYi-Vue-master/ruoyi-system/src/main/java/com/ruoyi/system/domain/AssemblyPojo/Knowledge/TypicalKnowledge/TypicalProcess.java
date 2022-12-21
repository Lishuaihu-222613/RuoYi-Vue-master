package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node("TypicalProcess")
public class TypicalProcess {

    @Id
    @GeneratedValue
    private Long processId;

    @Property(name = "label")
    private String processName;

    @Property(name = "Description")
    private String processDescription;

    @Relationship(type = "hasSubProcedure",direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> subProcedures;

    @Relationship(type = "hasAssociatedProblem",direction = Relationship.Direction.OUTGOING)
    private Set<QualityProblem> associatedProblems;

    @Relationship(type = "use",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> usedResources;

    @Relationship(type = "hasAssociatedFile",direction = Relationship.Direction.OUTGOING)
    private Set<FileKnowledge> associatedFiles;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public Set<TypicalProcedure> getSubProcedures() {
        return subProcedures;
    }

    public void setSubProcedures(Set<TypicalProcedure> subProcedures) {
        this.subProcedures = subProcedures;
    }

    public Set<QualityProblem> getAssociatedProblems() {
        return associatedProblems;
    }

    public void setAssociatedProblems(Set<QualityProblem> associatedProblems) {
        this.associatedProblems = associatedProblems;
    }

    public Set<AssemblyResource> getUsedResources() {
        return usedResources;
    }

    public void setUsedResources(Set<AssemblyResource> usedResources) {
        this.usedResources = usedResources;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public TypicalProcess() {
    }

    public TypicalProcess(Long processId, String processName, String processDescription, Set<TypicalProcedure> subProcedures, Set<QualityProblem> associatedProblems, Set<AssemblyResource> usedResources, Set<FileKnowledge> associatedFiles) {
        this.processId = processId;
        this.processName = processName;
        this.processDescription = processDescription;
        this.subProcedures = subProcedures;
        this.associatedProblems = associatedProblems;
        this.usedResources = usedResources;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypicalProcess that = (TypicalProcess) o;
        return Objects.equals(processId, that.processId) && Objects.equals(processName, that.processName) && Objects.equals(processDescription, that.processDescription) && Objects.equals(subProcedures, that.subProcedures) && Objects.equals(associatedProblems, that.associatedProblems) && Objects.equals(usedResources, that.usedResources) && Objects.equals(associatedFiles, that.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, processName, processDescription, subProcedures, associatedProblems, usedResources, associatedFiles);
    }

    @Override
    public String toString() {
        return "TypicalProcess{" +
                "processId=" + processId +
                ", processName='" + processName + '\'' +
                ", processDescription='" + processDescription + '\'' +
                ", subProcedures=" + subProcedures +
                ", associatedProblems=" + associatedProblems +
                ", usedResources=" + usedResources +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
