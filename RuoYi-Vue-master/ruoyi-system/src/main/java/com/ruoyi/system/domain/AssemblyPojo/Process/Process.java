package com.ruoyi.system.domain.AssemblyPojo.Process;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"装药工艺","Process"})
public class Process {

    @Id
    @GeneratedValue
    private Long processId;

    @Property(name = "label")
    private String processName;

    @Relationship(type = "关联零、部、组（整）件")
    private AssemblyStructure associatedStructure;

    @Relationship(type = "关联产品系统")
    private AssemblyProduct associatedProduct;

    @Property(name = "备注")
    private String processDescription;

    @Relationship(type = "包含工序")
    private Set<Sequence> subSequence;

    @Relationship(type = "关联文件")
    private Set<FileKnowledge> associatedFiles;

    public Process() {
    }

    public Process(Long processId, String processName, AssemblyStructure associatedStructure, AssemblyProduct associatedProduct, String processDescription, Set<Sequence> subSequence, Set<FileKnowledge> associatedFiles) {
        this.processId = processId;
        this.processName = processName;
        this.associatedStructure = associatedStructure;
        this.associatedProduct = associatedProduct;
        this.processDescription = processDescription;
        this.subSequence = subSequence;
        this.associatedFiles = associatedFiles;
    }

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

    public AssemblyStructure getAssociatedStructure() {
        return associatedStructure;
    }

    public void setAssociatedStructure(AssemblyStructure associatedStructure) {
        this.associatedStructure = associatedStructure;
    }

    public AssemblyProduct getAssociatedProduct() {
        return associatedProduct;
    }

    public void setAssociatedProduct(AssemblyProduct associatedProduct) {
        this.associatedProduct = associatedProduct;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public Set<Sequence> getSubSequence() {
        return subSequence;
    }

    public void setSubSequence(Set<Sequence> subSequence) {
        this.subSequence = subSequence;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Objects.equals(processId, process.processId) && Objects.equals(processName, process.processName) && Objects.equals(processDescription, process.processDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, processName, processDescription);
    }

    @Override
    public String toString() {
        return "Process{" +
                "processId=" + processId +
                ", processName='" + processName + '\'' +
                ", associatedStructure=" + associatedStructure +
                ", associatedProduct=" + associatedProduct +
                ", processDescription='" + processDescription + '\'' +
                ", subSequence=" + subSequence +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
