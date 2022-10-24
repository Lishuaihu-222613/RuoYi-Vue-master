package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.AccuracyRequirement.Requirement.Requirement;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.AdvancedBerpShape;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

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

    public AssemblyProduct(Long structureId, Set<AssemblyComponent> components, Set<AssemblyPart> parts, Set<AssemblyConstraint> constraints, Location location, AdvancedBerpShape geometry, Set<fileKnowledge> associatedFiles, String productName, String productDescription, Set<AssemblyComponent> components1, Set<AssemblyPart> parts1, Set<Requirement> requirements) {
        super(structureId, components, parts, constraints, location, geometry, associatedFiles);
        this.productName = productName;
        this.productDescription = productDescription;
        this.components = components1;
        this.parts = parts1;
        this.requirements = requirements;
    }
}
