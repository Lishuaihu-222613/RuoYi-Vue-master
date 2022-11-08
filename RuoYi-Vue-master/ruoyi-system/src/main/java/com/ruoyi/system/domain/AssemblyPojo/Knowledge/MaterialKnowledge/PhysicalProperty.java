package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Map;
import java.util.Objects;

@Node("PhysicalProperty")
public class PhysicalProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "label")
    private String propertyName;

    @Property(name = "Attribute")
    private Map<String,String> propertyAttributes;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Map<String, String> getPropertyAttributes() {
        return propertyAttributes;
    }

    public void setPropertyAttributes(Map<String, String> propertyAttributes) {
        this.propertyAttributes = propertyAttributes;
    }

    public PhysicalProperty() {
    }

    public PhysicalProperty(Long propertyId, String propertyName, Map<String, String> propertyAttributes) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyAttributes = propertyAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalProperty that = (PhysicalProperty) o;
        return Objects.equals(propertyId, that.propertyId) && Objects.equals(propertyName, that.propertyName) && Objects.equals(propertyAttributes, that.propertyAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, propertyAttributes);
    }

    @Override
    public String toString() {
        return "PhysicalProperty{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyAttributes=" + propertyAttributes +
                '}';
    }
}
