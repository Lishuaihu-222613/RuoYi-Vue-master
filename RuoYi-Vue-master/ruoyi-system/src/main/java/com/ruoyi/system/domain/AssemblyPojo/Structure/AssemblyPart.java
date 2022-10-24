package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.AdvancedBerpShape;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;
import java.util.Set;

@Node("AssemblyPart")
public class AssemblyPart extends AssemblyStructure{

    @Property(name = "Quantity")
    private Integer partQuantity;

    @Property(name = "label")
    private String partName;

    @Property(name = "Description")
    private String partDescription;

    @Property(name = "Source")
    private String partSource;

    @Property(name = "Density")
    private double partDensity;

    @Property(name = "WetArea")
    private double partWetArea;

    @Property(name = "Volume")
    private double partVolume;

    @Property(name = "Mass")
    private double partMass;

    @Property(name = "BoundingBox")
    private String partBoundingBox;

    public Integer getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(Integer partQuantity) {
        this.partQuantity = partQuantity;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartSource() {
        return partSource;
    }

    public void setPartSource(String partSource) {
        this.partSource = partSource;
    }

    public double getPartDensity() {
        return partDensity;
    }

    public void setPartDensity(double partDensity) {
        this.partDensity = partDensity;
    }

    public double getPartWetArea() {
        return partWetArea;
    }

    public void setPartWetArea(double partWetArea) {
        this.partWetArea = partWetArea;
    }

    public double getPartVolume() {
        return partVolume;
    }

    public void setPartVolume(double partVolume) {
        this.partVolume = partVolume;
    }

    public double getPartMass() {
        return partMass;
    }

    public void setPartMass(double partMass) {
        this.partMass = partMass;
    }

    public String getPartBoundingBox() {
        return partBoundingBox;
    }

    public void setPartBoundingBox(String partBoundingBox) {
        this.partBoundingBox = partBoundingBox;
    }

    public AssemblyPart() {
    }

    public AssemblyPart(Long structureId, Set<AssemblyComponent> components, Set<AssemblyPart> parts, Set<AssemblyConstraint> constraints, Location location, AdvancedBerpShape geometry, Set<fileKnowledge> associatedFiles, Integer partQuantity, String partName, String partDescription, String partSource, double partDensity, double partWetArea, double partVolume, double partMass, String partBoundingBox) {
        super(structureId, components, parts, constraints, location, geometry, associatedFiles);
        this.partQuantity = partQuantity;
        this.partName = partName;
        this.partDescription = partDescription;
        this.partSource = partSource;
        this.partDensity = partDensity;
        this.partWetArea = partWetArea;
        this.partVolume = partVolume;
        this.partMass = partMass;
        this.partBoundingBox = partBoundingBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyPart that = (AssemblyPart) o;
        return Double.compare(that.partDensity, partDensity) == 0 && Double.compare(that.partWetArea, partWetArea) == 0 && Double.compare(that.partVolume, partVolume) == 0 && Double.compare(that.partMass, partMass) == 0 && partQuantity.equals(that.partQuantity) && partName.equals(that.partName) && Objects.equals(partDescription, that.partDescription) && Objects.equals(partSource, that.partSource) && Objects.equals(partBoundingBox, that.partBoundingBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), partQuantity, partName, partDescription, partSource, partDensity, partWetArea, partVolume, partMass, partBoundingBox);
    }
}
