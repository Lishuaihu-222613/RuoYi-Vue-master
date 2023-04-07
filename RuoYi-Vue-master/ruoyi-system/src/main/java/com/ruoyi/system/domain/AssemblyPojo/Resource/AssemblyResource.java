package com.ruoyi.system.domain.AssemblyPojo.Resource;


import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"装药总装资源","AssemblyResource"})
public abstract class AssemblyResource {

    @Id
    @GeneratedValue
    private Long resourceId;

    @Property(name = "label")
    private String resourceName;

    @Property(name = "资源描述")
    private String resourceDescription;

    @DynamicLabels
    private Set<String> resourceTypes;

    @Relationship(type = "存在于" , direction = Relationship.Direction.OUTGOING)
    private Set<Site> sites;

    @Relationship(type = "被使用在" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> sequences;

    @Relationship(type = "使用于" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> steps;

    public AssemblyResource() {
    }

    public AssemblyResource(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, Set<Site> sites, Set<Sequence> sequences, Set<Step> steps) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceDescription = resourceDescription;
        this.resourceTypes = resourceTypes;
        this.sites = sites;
        this.sequences = sequences;
        this.steps = steps;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    public Set<Site> getLocations() {
        return sites;
    }

    public void setLocations(Set<Site> sites) {
        this.sites = sites;
    }

    public Set<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(Set<Sequence> sequences) {
        this.sequences = sequences;
    }

    public Set<Step> getSteps() {
        return steps;
    }

    public void setSteps(Set<Step> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyResource that = (AssemblyResource) o;
        return Objects.equals(resourceId, that.resourceId) && Objects.equals(resourceName, that.resourceName) && Objects.equals(resourceDescription, that.resourceDescription) && Objects.equals(resourceTypes, that.resourceTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, resourceDescription, resourceTypes);
    }

    @Override
    public String toString() {
        return "AssemblyResource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", resourceTypes=" + resourceTypes +
                ", locations=" + sites +
                ", sequences=" + sequences +
                ", steps=" + steps +
                '}';
    }
}
