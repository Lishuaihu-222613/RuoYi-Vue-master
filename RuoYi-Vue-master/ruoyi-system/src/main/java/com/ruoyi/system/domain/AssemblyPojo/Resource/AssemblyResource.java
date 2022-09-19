package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.Location.Location;
import com.ruoyi.system.domain.AssemblyPojo.Operation.AssemblyOperation;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node()
public abstract class AssemblyResource {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String resourceName;

    @Property(name = "resourceDescription")
    private String resourceDescription;

    @DynamicLabels
    private Set<String> resourceTypes;

    @Relationship(type = "hasLocation" , direction = Relationship.Direction.OUTGOING)
    private Set<Location> locations;

    @Relationship(type = "isUsedIn" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyOperation> operations;

    public AssemblyResource() {
    }

    public AssemblyResource(Long id, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Location> locations, Set<AssemblyOperation> operations) {
        Id = id;
        this.resourceName = resourceName;
        this.resourceDescription = resourceDescription;
        this.resourceTypes = resourceTypes;
        this.locations = locations;
        this.operations = operations;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public Set<String> getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(Set<String> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<AssemblyOperation> getOperations() {
        return operations;
    }

    public void setOperations(Set<AssemblyOperation> operations) {
        this.operations = operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyResource that = (AssemblyResource) o;
        return Objects.equals(Id, that.Id) && Objects.equals(resourceName, that.resourceName) && Objects.equals(resourceDescription, that.resourceDescription) && Objects.equals(resourceTypes, that.resourceTypes) && Objects.equals(locations, that.locations) && Objects.equals(operations, that.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, resourceName, resourceDescription, resourceTypes, locations, operations);
    }

    @Override
    public String toString() {
        return "AssemblyResource{" +
                "Id=" + Id +
                ", resourceName='" + resourceName + '\'' +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", resourceTypes=" + resourceTypes +
                ", locations=" + locations +
                ", operations=" + operations +
                '}';
    }
}
