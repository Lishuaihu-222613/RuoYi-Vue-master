package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.AdvancedBerpShape;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;


@Node("Structure")
public abstract class AssemblyStructure {

    @Id
    @GeneratedValue
    private Long structureId;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyComponent> components;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyPart> parts;

    @Relationship(type = "hasConstraint", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyConstraint> constraints;

    @Relationship(type = "hasLocation", direction = Relationship.Direction.OUTGOING)
    private Location location;

    @Relationship(type = "hasGeometry", direction = Relationship.Direction.OUTGOING)
    private AdvancedBerpShape geometry;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
    private Set<FileKnowledge> associatedFiles;

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public Set<AssemblyComponent> getComponents() {
        return components;
    }

    public void setComponents(Set<AssemblyComponent> components) {
        this.components = components;
    }

    public Set<AssemblyPart> getParts() {
        return parts;
    }

    public void setParts(Set<AssemblyPart> parts) {
        this.parts = parts;
    }

    public Set<AssemblyConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(Set<AssemblyConstraint> constraints) {
        this.constraints = constraints;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AdvancedBerpShape getGeometry() {
        return geometry;
    }

    public void setGeometry(AdvancedBerpShape geometry) {
        this.geometry = geometry;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public AssemblyStructure() {
    }

    public AssemblyStructure(Long structureId, Set<String> dynamicLabels, Set<AssemblyComponent> components, Set<AssemblyPart> parts, Set<AssemblyConstraint> constraints, Location location, AdvancedBerpShape geometry, Set<FileKnowledge> associatedFiles) {
        this.structureId = structureId;
        this.dynamicLabels = dynamicLabels;
        this.components = components;
        this.parts = parts;
        this.constraints = constraints;
        this.location = location;
        this.geometry = geometry;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyStructure that = (AssemblyStructure) o;
        return Objects.equals(structureId, that.structureId) && Objects.equals(dynamicLabels, that.dynamicLabels) && Objects.equals(components, that.components) && Objects.equals(parts, that.parts) && Objects.equals(constraints, that.constraints) && Objects.equals(location, that.location) && Objects.equals(geometry, that.geometry) && Objects.equals(associatedFiles, that.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, dynamicLabels, components, parts, constraints, location, geometry, associatedFiles);
    }

    @Override
    public String toString() {
        return "AssemblyStructure{" +
                "structureId=" + structureId +
                ", dynamicLabels=" + dynamicLabels +
                '}';
    }
}
