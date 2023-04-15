package com.ruoyi.system.domain.AssemblyPojo.Resource;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

    @Property(name = "存储地址")
    private String site;

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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public AssemblyResource() {
    }

    public AssemblyResource(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceDescription = resourceDescription;
        this.resourceTypes = resourceTypes;
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyResource that = (AssemblyResource) o;
        return Objects.equals(resourceId, that.resourceId) && Objects.equals(resourceName, that.resourceName) && Objects.equals(resourceDescription, that.resourceDescription) && Objects.equals(resourceTypes, that.resourceTypes) && Objects.equals(site, that.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, resourceDescription, resourceTypes, site);
    }

    @Override
    public String toString() {
        return "AssemblyResource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", resourceTypes=" + resourceTypes +
                ", site='" + site + '\'' +
                '}';
    }
}
