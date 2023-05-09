package com.ruoyi.system.domain.AssemblyPojo.Process;


import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyElement;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"工艺元素","ProcessElement"})
public class ProcessElement {

    @Id
    @GeneratedValue
    private Long elementId;

    @Property(name = "label")
    private String elementName;

    @Property(name = "序号")
    private String elementNumber;

    @Property(name = "内容描述")
    private String elementDescription;

    @Property(name = "备注")
    private List<String> elementRemark;

    @CompositeProperty(prefix = "要求")
    private Map<String,String> elementRequirements;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> elementOtherProperties;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Property(name = "存在子节点")
    private boolean hasSubElements;

    @Relationship(type = "hasSubElement")
    private Set<ProcessElement> subElements;

    @Relationship(type = "hasAssociatedStructure")
    private Set<AssemblyElement> associatedStructure;

    @Relationship(type = "use")
    private Set<AssemblyResource> usedResources;

    @Relationship(type = "hasAssociatedFile")
    private Set<FileKnowledge> associatedFiles;

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(String elementNumber) {
        this.elementNumber = elementNumber;
    }

    public String getElementDescription() {
        return elementDescription;
    }

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }

    public List<String> getElementRemark() {
        return elementRemark;
    }

    public void setElementRemark(List<String> elementRemark) {
        this.elementRemark = elementRemark;
    }

    public Map<String, String> getElementRequirements() {
        return elementRequirements;
    }

    public void setElementRequirements(Map<String, String> elementRequirements) {
        this.elementRequirements = elementRequirements;
    }

    public Map<String, String> getElementOtherProperties() {
        return elementOtherProperties;
    }

    public void setElementOtherProperties(Map<String, String> elementOtherProperties) {
        this.elementOtherProperties = elementOtherProperties;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public boolean isHasSubElements() {
        return hasSubElements;
    }

    public void setHasSubElements(boolean hasSubElements) {
        this.hasSubElements = hasSubElements;
    }

    public Set<ProcessElement> getSubElements() {
        return subElements;
    }

    public void setSubElements(Set<ProcessElement> subElements) {
        this.subElements = subElements;
    }

    public Set<AssemblyElement> getAssociatedStructure() {
        return associatedStructure;
    }

    public void setAssociatedStructure(Set<AssemblyElement> associatedStructure) {
        this.associatedStructure = associatedStructure;
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

    public ProcessElement() {
    }

    public ProcessElement(Long elementId, String elementName, String elementNumber, String elementDescription, List<String> elementRemark, Map<String, String> elementRequirements, Map<String, String> elementOtherProperties, Set<String> dynamicLabels, boolean hasSubElements, Set<ProcessElement> subElements, Set<AssemblyElement> associatedStructure, Set<AssemblyResource> usedResources, Set<FileKnowledge> associatedFiles) {
        this.elementId = elementId;
        this.elementName = elementName;
        this.elementNumber = elementNumber;
        this.elementDescription = elementDescription;
        this.elementRemark = elementRemark;
        this.elementRequirements = elementRequirements;
        this.elementOtherProperties = elementOtherProperties;
        this.dynamicLabels = dynamicLabels;
        this.hasSubElements = hasSubElements;
        this.subElements = subElements;
        this.associatedStructure = associatedStructure;
        this.usedResources = usedResources;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessElement that = (ProcessElement) o;
        return hasSubElements == that.hasSubElements && Objects.equals(elementId, that.elementId) && Objects.equals(elementName, that.elementName) && Objects.equals(elementNumber, that.elementNumber) && Objects.equals(elementDescription, that.elementDescription) && Objects.equals(elementRemark, that.elementRemark) && Objects.equals(elementRequirements, that.elementRequirements) && Objects.equals(elementOtherProperties, that.elementOtherProperties) && Objects.equals(dynamicLabels, that.dynamicLabels) && Objects.equals(subElements, that.subElements) && Objects.equals(associatedStructure, that.associatedStructure) && Objects.equals(usedResources, that.usedResources) && Objects.equals(associatedFiles, that.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, elementName, elementNumber, elementDescription, elementRemark, elementRequirements, elementOtherProperties, dynamicLabels, hasSubElements, subElements, associatedStructure, usedResources, associatedFiles);
    }

    @Override
    public String toString() {
        return "ProcessElement{" +
                "elementId=" + elementId +
                ", elementName='" + elementName + '\'' +
                ", elementNumber='" + elementNumber + '\'' +
                ", elementDescription='" + elementDescription + '\'' +
                ", elementRemark=" + elementRemark +
                ", elementRequirements=" + elementRequirements +
                ", elementOtherProperties=" + elementOtherProperties +
                ", dynamicLabels=" + dynamicLabels +
                ", hasSubElements=" + hasSubElements +
                ", subElements=" + subElements +
                ", associatedStructure=" + associatedStructure +
                ", usedResources=" + usedResources +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
