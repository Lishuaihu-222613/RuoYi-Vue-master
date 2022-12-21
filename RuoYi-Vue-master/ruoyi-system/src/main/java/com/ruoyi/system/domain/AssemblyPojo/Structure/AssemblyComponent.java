package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.AdvancedBerpShape;
import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node("AssemblyComponent")
public class AssemblyComponent extends AssemblyStructure {

    @Property(name = "label")
    private String componentName;

    @Property(name = "Description")
    private String componentDescription;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyComponent> components;

    @Relationship(type = "isComposedOf", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyPart> parts;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
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

    public AssemblyComponent() {
    }

    public AssemblyComponent(Long structureId, Set<AssemblyComponent> components, Set<AssemblyPart> parts, Set<AssemblyConstraint> constraints, Location location, AdvancedBerpShape geometry, Set<FileKnowledge> associatedFiles, String componentName, String componentDescription, Set<AssemblyComponent> components1, Set<AssemblyPart> parts1) {
        super(structureId, components, parts, constraints, location, geometry, associatedFiles);
        this.componentName = componentName;
        this.componentDescription = componentDescription;
        this.components = components1;
        this.parts = parts1;
    }
}
