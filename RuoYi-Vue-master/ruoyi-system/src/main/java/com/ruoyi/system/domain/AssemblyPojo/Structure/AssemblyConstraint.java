package com.ruoyi.system.domain.AssemblyPojo.Structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;



@Node("AssemblyConstraint")
public class AssemblyConstraint {

    @Id
    @GeneratedValue
    private Long constraintId;

    @Property(name = "label")
    private String constraintName;

    @Property(name = "Type")
    private String constraintType;

    @Property(name = "Value")
    private String constraintValue;

    @JsonIgnore
    @Relationship(type = "with", direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyStructure> structures;

    public Long getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(Long constraintId) {
        this.constraintId = constraintId;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    public String getConstraintValue() {
        return constraintValue;
    }

    public void setConstraintValue(String constraintValue) {
        this.constraintValue = constraintValue;
    }

    public Set<AssemblyStructure> getStructures() {
        return structures;
    }

    public void setStructures(Set<AssemblyStructure> structures) {
        this.structures = structures;
    }

    public AssemblyConstraint() {
    }

    public AssemblyConstraint(Long constraintId, String constraintName, String constraintType, String constraintValue, Set<AssemblyStructure> structures) {
        this.constraintId = constraintId;
        this.constraintName = constraintName;
        this.constraintType = constraintType;
        this.constraintValue = constraintValue;
        this.structures = structures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyConstraint that = (AssemblyConstraint) o;
        return Objects.equals(constraintId, that.constraintId) && Objects.equals(constraintName, that.constraintName) && Objects.equals(constraintType, that.constraintType) && Objects.equals(constraintValue, that.constraintValue) && Objects.equals(structures, that.structures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constraintId, constraintName, constraintType, constraintValue);
    }

    @Override
    public String toString() {
        return "AssemblyConstraint{" +
                "constraintId=" + constraintId +
                ", constraintName='" + constraintName + '\'' +
                ", constraintType='" + constraintType + '\'' +
                ", constraintValue='" + constraintValue + '\'' +
                '}';
    }
}
