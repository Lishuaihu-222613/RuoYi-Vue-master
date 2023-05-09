package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"总装元素","AssemblyElement"})
public class AssemblyElement {

    @Id
    @GeneratedValue
    private Long elementId;

    @Property(name = "label")
    private String elementName;

    @Property(name = "描述")
    private String elementDescription;

    @Property(name = "数量")
    private Integer elementQuantity;

    @Property(name = "来源")
    private String elementSource;

    @Property(name = "密度")
    private double elementDensity;

    @Property(name = "表面积")
    private double elementWetArea;

    @Property(name = "体积")
    private double elementVolume;

    @Property(name = "质量")
    private double elementMass;

    @Property(name = "包容盒")
    private String elementBoundingBox;

    @Property(name = "包含子元素")
    private boolean hasSubElements;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyElement> subElements;

    @Relationship(type = "hasModelFile", direction = Relationship.Direction.OUTGOING)
    private FileKnowledge modelFile;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
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

    public String getElementDescription() {
        return elementDescription;
    }

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }

    public Integer getElementQuantity() {
        return elementQuantity;
    }

    public void setElementQuantity(Integer elementQuantity) {
        this.elementQuantity = elementQuantity;
    }

    public String getElementSource() {
        return elementSource;
    }

    public void setElementSource(String elementSource) {
        this.elementSource = elementSource;
    }

    public double getElementDensity() {
        return elementDensity;
    }

    public void setElementDensity(double elementDensity) {
        this.elementDensity = elementDensity;
    }

    public double getElementWetArea() {
        return elementWetArea;
    }

    public void setElementWetArea(double elementWetArea) {
        this.elementWetArea = elementWetArea;
    }

    public double getElementVolume() {
        return elementVolume;
    }

    public void setElementVolume(double elementVolume) {
        this.elementVolume = elementVolume;
    }

    public double getElementMass() {
        return elementMass;
    }

    public void setElementMass(double elementMass) {
        this.elementMass = elementMass;
    }

    public String getElementBoundingBox() {
        return elementBoundingBox;
    }

    public void setElementBoundingBox(String elementBoundingBox) {
        this.elementBoundingBox = elementBoundingBox;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Set<AssemblyElement> getSubElements() {
        return subElements;
    }

    public void setSubElements(Set<AssemblyElement> subElements) {
        this.subElements = subElements;
    }

    public FileKnowledge getModelFile() {
        return modelFile;
    }

    public void setModelFile(FileKnowledge modelFile) {
        this.modelFile = modelFile;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public boolean isHasSubElements() {
        return hasSubElements;
    }

    public void setHasSubElements(boolean hasSubElements) {
        this.hasSubElements = hasSubElements;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public AssemblyElement() {
    }

    public AssemblyElement(Long elementId, String elementName, String elementDescription, Integer elementQuantity, String elementSource, double elementDensity, double elementWetArea, double elementVolume, double elementMass, String elementBoundingBox, boolean hasSubElements, Set<String> dynamicLabels, Set<AssemblyElement> subElements, FileKnowledge modelFile, Set<FileKnowledge> associatedFiles) {
        this.elementId = elementId;
        this.elementName = elementName;
        this.elementDescription = elementDescription;
        this.elementQuantity = elementQuantity;
        this.elementSource = elementSource;
        this.elementDensity = elementDensity;
        this.elementWetArea = elementWetArea;
        this.elementVolume = elementVolume;
        this.elementMass = elementMass;
        this.elementBoundingBox = elementBoundingBox;
        this.hasSubElements = hasSubElements;
        this.dynamicLabels = dynamicLabels;
        this.subElements = subElements;
        this.modelFile = modelFile;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyElement element = (AssemblyElement) o;
        return Double.compare(element.elementDensity, elementDensity) == 0 && Double.compare(element.elementWetArea, elementWetArea) == 0 && Double.compare(element.elementVolume, elementVolume) == 0 && Double.compare(element.elementMass, elementMass) == 0 && hasSubElements == element.hasSubElements && Objects.equals(elementId, element.elementId) && Objects.equals(elementName, element.elementName) && Objects.equals(elementDescription, element.elementDescription) && Objects.equals(elementQuantity, element.elementQuantity) && Objects.equals(elementSource, element.elementSource) && Objects.equals(elementBoundingBox, element.elementBoundingBox) && Objects.equals(dynamicLabels, element.dynamicLabels) && Objects.equals(subElements, element.subElements) && Objects.equals(modelFile, element.modelFile) && Objects.equals(associatedFiles, element.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, elementName, elementDescription, elementQuantity, elementSource, elementDensity, elementWetArea, elementVolume, elementMass, elementBoundingBox, hasSubElements, dynamicLabels, subElements, modelFile, associatedFiles);
    }

    @Override
    public String toString() {
        return "AssemblyElement{" +
                "elementId=" + elementId +
                ", elementName='" + elementName + '\'' +
                ", elementDescription='" + elementDescription + '\'' +
                ", elementQuantity=" + elementQuantity +
                ", elementSource='" + elementSource + '\'' +
                ", elementDensity=" + elementDensity +
                ", elementWetArea=" + elementWetArea +
                ", elementVolume=" + elementVolume +
                ", elementMass=" + elementMass +
                ", elementBoundingBox='" + elementBoundingBox + '\'' +
                ", hasSubElements=" + hasSubElements +
                ", dynamicLabels=" + dynamicLabels +
                ", subElements=" + subElements +
                ", modelFile=" + modelFile +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
