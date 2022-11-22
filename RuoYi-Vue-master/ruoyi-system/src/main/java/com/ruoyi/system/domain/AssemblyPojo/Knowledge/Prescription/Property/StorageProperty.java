package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;

@Node("贮存性能")
public class StorageProperty {

    @Id
    @GeneratedValue
    private Long propertyId;

    @Property(name = "预估贮存期")
    private String estimatedStoragePeriod;

    @Property(name = "外观变化")
    private String appearanceChange;

    @Property(name = "物理性能变化")
    private String physicalPropertyChange;

    @Property(name = "化学性能变化")
    private String chemicalPropertyChange;

    @Property(name = "力学性能变化")
    private String forcePropertyChange;

    @Property(name = "燃烧性能变化")
    private String combustionPropertyChange;

    @Property(name = "药柱-衬层（绝热层）界面粘结强度变化")
    private String interfacialBondStrengthChange;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getEstimatedStoragePeriod() {
        return estimatedStoragePeriod;
    }

    public void setEstimatedStoragePeriod(String estimatedStoragePeriod) {
        this.estimatedStoragePeriod = estimatedStoragePeriod;
    }

    public String getAppearanceChange() {
        return appearanceChange;
    }

    public void setAppearanceChange(String appearanceChange) {
        this.appearanceChange = appearanceChange;
    }

    public String getPhysicalPropertyChange() {
        return physicalPropertyChange;
    }

    public void setPhysicalPropertyChange(String physicalPropertyChange) {
        this.physicalPropertyChange = physicalPropertyChange;
    }

    public String getChemicalPropertyChange() {
        return chemicalPropertyChange;
    }

    public void setChemicalPropertyChange(String chemicalPropertyChange) {
        this.chemicalPropertyChange = chemicalPropertyChange;
    }

    public String getForcePropertyChange() {
        return forcePropertyChange;
    }

    public void setForcePropertyChange(String forcePropertyChange) {
        this.forcePropertyChange = forcePropertyChange;
    }

    public String getCombustionPropertyChange() {
        return combustionPropertyChange;
    }

    public void setCombustionPropertyChange(String combustionPropertyChange) {
        this.combustionPropertyChange = combustionPropertyChange;
    }

    public String getInterfacialBondStrengthChange() {
        return interfacialBondStrengthChange;
    }

    public void setInterfacialBondStrengthChange(String interfacialBondStrengthChange) {
        this.interfacialBondStrengthChange = interfacialBondStrengthChange;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public StorageProperty() {
    }

    public StorageProperty(Long propertyId, String estimatedStoragePeriod, String appearanceChange, String physicalPropertyChange, String chemicalPropertyChange, String forcePropertyChange, String combustionPropertyChange, String interfacialBondStrengthChange, Map<String, String> otherProperty) {
        this.propertyId = propertyId;
        this.estimatedStoragePeriod = estimatedStoragePeriod;
        this.appearanceChange = appearanceChange;
        this.physicalPropertyChange = physicalPropertyChange;
        this.chemicalPropertyChange = chemicalPropertyChange;
        this.forcePropertyChange = forcePropertyChange;
        this.combustionPropertyChange = combustionPropertyChange;
        this.interfacialBondStrengthChange = interfacialBondStrengthChange;
        this.otherProperty = otherProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageProperty that = (StorageProperty) o;
        return Objects.equals(propertyId, that.propertyId) && Objects.equals(estimatedStoragePeriod, that.estimatedStoragePeriod) && Objects.equals(appearanceChange, that.appearanceChange) && Objects.equals(physicalPropertyChange, that.physicalPropertyChange) && Objects.equals(chemicalPropertyChange, that.chemicalPropertyChange) && Objects.equals(forcePropertyChange, that.forcePropertyChange) && Objects.equals(combustionPropertyChange, that.combustionPropertyChange) && Objects.equals(interfacialBondStrengthChange, that.interfacialBondStrengthChange) && Objects.equals(otherProperty, that.otherProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, estimatedStoragePeriod, appearanceChange, physicalPropertyChange, chemicalPropertyChange, forcePropertyChange, combustionPropertyChange, interfacialBondStrengthChange, otherProperty);
    }

    @Override
    public String toString() {
        return "StorageProperty{" +
                "propertyId=" + propertyId +
                ", estimatedStoragePeriod='" + estimatedStoragePeriod + '\'' +
                ", appearanceChange='" + appearanceChange + '\'' +
                ", physicalPropertyChange='" + physicalPropertyChange + '\'' +
                ", chemicalPropertyChange='" + chemicalPropertyChange + '\'' +
                ", forcePropertyChange='" + forcePropertyChange + '\'' +
                ", combustionPropertyChange='" + combustionPropertyChange + '\'' +
                ", interfacialBondStrengthChange='" + interfacialBondStrengthChange + '\'' +
                ", otherProperty=" + otherProperty +
                '}';
    }
}
