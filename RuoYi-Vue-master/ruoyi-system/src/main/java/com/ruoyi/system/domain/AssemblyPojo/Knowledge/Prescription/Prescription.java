package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node({"推进剂配方","Prescription"})
public class Prescription {

    @Id
    @GeneratedValue
    private Long prescriptionId;

    @Property(name = "label")
    private String prescriptionName;

    @Property(name = "配方描述")
    private String prescriptionDescription;

    @Property(name = "密度")
    private String density;

    @Property(name = "危险等级")
    private String dangerLevel;

    @CompositeProperty(prefix = "固化时间")
    private Map<String,String> curingTime;

    @CompositeProperty(prefix = "其他属性")
    private Map<String,String> otherProperty;

    @DynamicLabels
    private Set<String> prescriptionLabels;

    @Relationship(type = "hasMaterialElement" , direction = Relationship.Direction.OUTGOING)
    private Set<hasMaterialElement> materialElements;

    @Relationship(type = "hasEnergyProperty" , direction = Relationship.Direction.OUTGOING)
    private EnergyProperty energyProperty;

    @Relationship(type = "hasForceProperty" , direction = Relationship.Direction.OUTGOING)
    private ForceProperty forceProperty;

    @Relationship(type = "hasCombustionProperty" , direction = Relationship.Direction.OUTGOING)
    private CombustionProperty combustionProperty;

    @Relationship(type = "hasStorageProperty" , direction = Relationship.Direction.OUTGOING)
    private StorageProperty storageProperty;

    @Relationship(type = "hasOxidantRatio" , direction = Relationship.Direction.OUTGOING)
    private OxidantRatio oxidantRatio;

    @Relationship(type = "hasHeatStability" , direction = Relationship.Direction.OUTGOING)
    private HeatStability heatStability;

    @Relationship(type = "hasMechanicalStability" , direction = Relationship.Direction.OUTGOING)
    private MechanicalStability mechanicalStability;

    @Relationship(type = "hasRadioStability" , direction = Relationship.Direction.OUTGOING)
    private RadioStability radioStability;

    @Relationship(type = "hasExplosionStability" , direction = Relationship.Direction.OUTGOING)
    private ExplosionStability explosionStability;

    @Relationship(type = "hasUsage" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyProduct> product;

    @Relationship(type = "hasTypicalProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> typicalProcesses;

    public Prescription() {
    }

    public Prescription(Long prescriptionId, String prescriptionName, String prescriptionDescription, String density, String dangerLevel, Map<String, String> curingTime, Map<String, String> otherProperty, Set<String> prescriptionLabels, Set<hasMaterialElement> materialElements, EnergyProperty energyProperty, ForceProperty forceProperty, CombustionProperty combustionProperty, StorageProperty storageProperty, OxidantRatio oxidantRatio, HeatStability heatStability, MechanicalStability mechanicalStability, RadioStability radioStability, ExplosionStability explosionStability, Set<AssemblyProduct> product, Set<TypicalProcess> typicalProcesses) {
        this.prescriptionId = prescriptionId;
        this.prescriptionName = prescriptionName;
        this.prescriptionDescription = prescriptionDescription;
        this.density = density;
        this.dangerLevel = dangerLevel;
        this.curingTime = curingTime;
        this.otherProperty = otherProperty;
        this.prescriptionLabels = prescriptionLabels;
        this.materialElements = materialElements;
        this.energyProperty = energyProperty;
        this.forceProperty = forceProperty;
        this.combustionProperty = combustionProperty;
        this.storageProperty = storageProperty;
        this.oxidantRatio = oxidantRatio;
        this.heatStability = heatStability;
        this.mechanicalStability = mechanicalStability;
        this.radioStability = radioStability;
        this.explosionStability = explosionStability;
        this.product = product;
        this.typicalProcesses = typicalProcesses;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getPrescriptionDescription() {
        return prescriptionDescription;
    }

    public void setPrescriptionDescription(String prescriptionDescription) {
        this.prescriptionDescription = prescriptionDescription;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public Map<String, String> getCuringTime() {
        return curingTime;
    }

    public void setCuringTime(Map<String, String> curingTime) {
        this.curingTime = curingTime;
    }

    public Map<String, String> getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(Map<String, String> otherProperty) {
        this.otherProperty = otherProperty;
    }

    public Set<String> getPrescriptionLabels() {
        return prescriptionLabels;
    }

    public void setPrescriptionLabels(Set<String> prescriptionLabels) {
        this.prescriptionLabels = prescriptionLabels;
    }

    public Set<hasMaterialElement> getMaterialElements() {
        return materialElements;
    }

    public void setMaterialElements(Set<hasMaterialElement> materialElements) {
        this.materialElements = materialElements;
    }

    public EnergyProperty getEnergyProperty() {
        return energyProperty;
    }

    public void setEnergyProperty(EnergyProperty energyProperty) {
        this.energyProperty = energyProperty;
    }

    public ForceProperty getForceProperty() {
        return forceProperty;
    }

    public void setForceProperty(ForceProperty forceProperty) {
        this.forceProperty = forceProperty;
    }

    public CombustionProperty getCombustionProperty() {
        return combustionProperty;
    }

    public void setCombustionProperty(CombustionProperty combustionProperty) {
        this.combustionProperty = combustionProperty;
    }

    public StorageProperty getStorageProperty() {
        return storageProperty;
    }

    public void setStorageProperty(StorageProperty storageProperty) {
        this.storageProperty = storageProperty;
    }

    public OxidantRatio getOxidantRatio() {
        return oxidantRatio;
    }

    public void setOxidantRatio(OxidantRatio oxidantRatio) {
        this.oxidantRatio = oxidantRatio;
    }

    public HeatStability getHeatStability() {
        return heatStability;
    }

    public void setHeatStability(HeatStability heatStability) {
        this.heatStability = heatStability;
    }

    public MechanicalStability getMechanicalStability() {
        return mechanicalStability;
    }

    public void setMechanicalStability(MechanicalStability mechanicalStability) {
        this.mechanicalStability = mechanicalStability;
    }

    public RadioStability getRadioStability() {
        return radioStability;
    }

    public void setRadioStability(RadioStability radioStability) {
        this.radioStability = radioStability;
    }

    public ExplosionStability getExplosionStability() {
        return explosionStability;
    }

    public void setExplosionStability(ExplosionStability explosionStability) {
        this.explosionStability = explosionStability;
    }

    public Set<AssemblyProduct> getProduct() {
        return product;
    }

    public void setProduct(Set<AssemblyProduct> product) {
        this.product = product;
    }

    public Set<TypicalProcess> getTypicalProcesses() {
        return typicalProcesses;
    }

    public void setTypicalProcesses(Set<TypicalProcess> typicalProcesses) {
        this.typicalProcesses = typicalProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(prescriptionId, that.prescriptionId) && Objects.equals(prescriptionName, that.prescriptionName) && Objects.equals(prescriptionDescription, that.prescriptionDescription) && Objects.equals(density, that.density) && Objects.equals(dangerLevel, that.dangerLevel) && Objects.equals(curingTime, that.curingTime) && Objects.equals(otherProperty, that.otherProperty) && Objects.equals(prescriptionLabels, that.prescriptionLabels) && Objects.equals(materialElements, that.materialElements) && Objects.equals(energyProperty, that.energyProperty) && Objects.equals(forceProperty, that.forceProperty) && Objects.equals(combustionProperty, that.combustionProperty) && Objects.equals(storageProperty, that.storageProperty) && Objects.equals(oxidantRatio, that.oxidantRatio) && Objects.equals(heatStability, that.heatStability) && Objects.equals(mechanicalStability, that.mechanicalStability) && Objects.equals(radioStability, that.radioStability) && Objects.equals(explosionStability, that.explosionStability) && Objects.equals(product, that.product) && Objects.equals(typicalProcesses, that.typicalProcesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, prescriptionName, prescriptionDescription, density, dangerLevel, curingTime, otherProperty, prescriptionLabels, materialElements, energyProperty, forceProperty, combustionProperty, storageProperty, oxidantRatio, heatStability, mechanicalStability, radioStability, explosionStability, product, typicalProcesses);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", prescriptionDescription='" + prescriptionDescription + '\'' +
                ", density='" + density + '\'' +
                ", dangerLevel='" + dangerLevel + '\'' +
                ", curingTime=" + curingTime +
                ", otherProperty=" + otherProperty +
                ", prescriptionLabels=" + prescriptionLabels +
                ", materialElements=" + materialElements +
                ", energyProperty=" + energyProperty +
                ", forceProperty=" + forceProperty +
                ", combustionProperty=" + combustionProperty +
                ", storageProperty=" + storageProperty +
                ", oxidantRatio=" + oxidantRatio +
                ", heatStability=" + heatStability +
                ", impactStability=" + mechanicalStability +
                ", radioStability=" + radioStability +
                ", explosionStability=" + explosionStability +
                ", product=" + product +
                ", typicalProcesses=" + typicalProcesses +
                '}';
    }
}
