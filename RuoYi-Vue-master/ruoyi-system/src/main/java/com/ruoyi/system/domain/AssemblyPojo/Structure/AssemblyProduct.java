package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.AccuracyRequirement.Requirement.Requirement;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.AdvancedBerpShape;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.Set;

@Node("AssemblyProduct")
public class AssemblyProduct extends AssemblyStructure {

    @Property(name = "label")
    private String productName;

    @Property(name = "Description")
    private String productDescription;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyComponent> components;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyPart> parts;

    @Relationship(type = "hasRequirement", direction = Relationship.Direction.OUTGOING)
    private Set<Requirement> requirements;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public Set<AssemblyComponent> getComponents() {
        return components;
    }

    @Override
    public void setComponents(Set<AssemblyComponent> components) {
        this.components = components;
    }

    @Override
    public Set<AssemblyPart> getParts() {
        return parts;
    }

    @Override
    public void setParts(Set<AssemblyPart> parts) {
        this.parts = parts;
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public AssemblyProduct() {
    }

    public AssemblyProduct(Long structureId, Set<String> dynamicLabels, Set<AssemblyComponent> components, Set<AssemblyPart> parts, Set<AssemblyConstraint> constraints, Location location, AdvancedBerpShape geometry, Set<FileKnowledge> associatedFiles, String productName, String productDescription, Set<AssemblyComponent> components1, Set<AssemblyPart> parts1, Set<Requirement> requirements) {
        super(structureId, dynamicLabels, components, parts, constraints, location, geometry, associatedFiles);
        this.productName = productName;
        this.productDescription = productDescription;
        this.components = components1;
        this.parts = parts1;
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AssemblyProduct that = (AssemblyProduct) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productDescription, that.productDescription) && Objects.equals(components, that.components) && Objects.equals(parts, that.parts) && Objects.equals(requirements, that.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productName, productDescription, components, parts, requirements);
    }

    @Override
    public String toString() {
        return "AssemblyProduct{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", components=" + components +
                ", parts=" + parts +
                ", requirements=" + requirements +
                "} " + super.toString();
    }
}
