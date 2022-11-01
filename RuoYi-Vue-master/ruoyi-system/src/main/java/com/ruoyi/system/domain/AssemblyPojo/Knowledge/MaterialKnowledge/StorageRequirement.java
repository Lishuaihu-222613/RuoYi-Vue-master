package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node("StorageRequirement")
public class StorageRequirement {

    @Id
    @GeneratedValue
    private Long requirementId;

    @Property(name = "label")
    private String requirementName;

    @Property(name = "Description")
    private String requirementDescription;

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getRequirementDescription() {
        return requirementDescription;
    }

    public void setRequirementDescription(String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }

    public StorageRequirement() {
    }

    public StorageRequirement(Long requirementId, String requirementName, String requirementDescription) {
        this.requirementId = requirementId;
        this.requirementName = requirementName;
        this.requirementDescription = requirementDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageRequirement that = (StorageRequirement) o;
        return Objects.equals(requirementId, that.requirementId) && Objects.equals(requirementName, that.requirementName) && Objects.equals(requirementDescription, that.requirementDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementId, requirementName, requirementDescription);
    }

    @Override
    public String toString() {
        return "StorageRequirement{" +
                "requirementId=" + requirementId +
                ", requirementName='" + requirementName + '\'' +
                ", requirementDescription='" + requirementDescription + '\'' +
                '}';
    }
}
