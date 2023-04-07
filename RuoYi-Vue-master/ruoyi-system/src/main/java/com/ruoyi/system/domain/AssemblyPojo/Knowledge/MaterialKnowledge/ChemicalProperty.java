package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node({"ChemicalProperty","化学性质"})
public class ChemicalProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "label")
    private String propertyName;

    @CompositeProperty(prefix = "相关属性")
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

    public ChemicalProperty() {
    }

    public ChemicalProperty(Long propertyId, String propertyName, Map<String, String> propertyAttributes) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyAttributes = propertyAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChemicalProperty that = (ChemicalProperty) o;
        return Objects.equals(propertyId, that.propertyId) && Objects.equals(propertyName, that.propertyName) && Objects.equals(propertyAttributes, that.propertyAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, propertyAttributes);
    }

    @Override
    public String toString() {
        return "ChemicalProperty{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyAttributes=" + propertyAttributes +
                '}';
    }
}
