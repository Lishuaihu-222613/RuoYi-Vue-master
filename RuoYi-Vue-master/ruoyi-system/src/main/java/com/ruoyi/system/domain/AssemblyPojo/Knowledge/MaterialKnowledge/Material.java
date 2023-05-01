package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"材料","Material"})
public class Material {

    @Id
    @GeneratedValue
    private Long materialId;

    @Property(name = "label")
    private String materialName;

    @Property(name = "英文名")
    private String englishName;

    @Property(name = "别称")
    private String nickName;

    @Property(name = "英文别称")
    private String englishNickName;

    @Property(name = "相对分子质量")
    private String relativeMolecularMass;

    @Property(name = "化学结构式")
    private String structuralFormula;

    @Property(name = "CAS-登记号")
    private String casRegistryNumber;

    @Property(name = "描述")
    private String materialDescription;

    @Property(name = "外观")
    private String materialAppearance;

    @Property(name = "用途")
    private List<String> materialUsage;

    @DynamicLabels
    private Set<String> materialLabels;

    @Relationship(type = "hasPhysicalProperty", direction = Relationship.Direction.OUTGOING)
    private Set<PhysicalProperty> physicalProperty;

    @Relationship(type = "hasChemicalProperty", direction = Relationship.Direction.OUTGOING)
    private Set<ChemicalProperty> chemicalProperty;

    @Relationship(type = "hasAnalysisSpectrogram", direction = Relationship.Direction.OUTGOING)
    private Set<AnalysisSpectrogram> analysisSpectrogram;

    @Relationship(type = "hasInspectProject", direction = Relationship.Direction.OUTGOING)
    private Set<InspectProject> inspectProjects;

    @Relationship(type = "hasRequirement", direction = Relationship.Direction.OUTGOING)
    private Set<StorageRequirement> storageRequirements;

    @Relationship(type = "hasProduceMethod", direction = Relationship.Direction.OUTGOING)
    private Set<ProduceMethod> productMethods;

    @Relationship(type = "hasDanger", direction = Relationship.Direction.OUTGOING)
    private Set<Danger> dangers;

    @Relationship(type = "hasProtection", direction = Relationship.Direction.OUTGOING)
    private Set<Protection> protections;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
    private Set<FileKnowledge> associatedFiles;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEnglishNickName() {
        return englishNickName;
    }

    public void setEnglishNickName(String englishNickName) {
        this.englishNickName = englishNickName;
    }

    public String getRelativeMolecularMass() {
        return relativeMolecularMass;
    }

    public void setRelativeMolecularMass(String relativeMolecularMass) {
        this.relativeMolecularMass = relativeMolecularMass;
    }

    public String getStructuralFormula() {
        return structuralFormula;
    }

    public void setStructuralFormula(String structuralFormula) {
        this.structuralFormula = structuralFormula;
    }

    public String getCasRegistryNumber() {
        return casRegistryNumber;
    }

    public void setCasRegistryNumber(String casRegistryNumber) {
        this.casRegistryNumber = casRegistryNumber;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getMaterialAppearance() {
        return materialAppearance;
    }

    public void setMaterialAppearance(String materialAppearance) {
        this.materialAppearance = materialAppearance;
    }

    public List<String> getMaterialUsage() {
        return materialUsage;
    }

    public void setMaterialUsage(List<String> materialUsage) {
        this.materialUsage = materialUsage;
    }

    public Set<String> getMaterialLabels() {
        return materialLabels;
    }

    public void setMaterialLabels(Set<String> materialLabels) {
        this.materialLabels = materialLabels;
    }

    public Set<PhysicalProperty> getPhysicalProperty() {
        return physicalProperty;
    }

    public void setPhysicalProperty(Set<PhysicalProperty> physicalProperty) {
        this.physicalProperty = physicalProperty;
    }

    public Set<ChemicalProperty> getChemicalProperty() {
        return chemicalProperty;
    }

    public void setChemicalProperty(Set<ChemicalProperty> chemicalProperty) {
        this.chemicalProperty = chemicalProperty;
    }

    public Set<AnalysisSpectrogram> getAnalysisSpectrogram() {
        return analysisSpectrogram;
    }

    public void setAnalysisSpectrogram(Set<AnalysisSpectrogram> analysisSpectrogram) {
        this.analysisSpectrogram = analysisSpectrogram;
    }

    public Set<InspectProject> getInspectProjects() {
        return inspectProjects;
    }

    public void setInspectProjects(Set<InspectProject> inspectProjects) {
        this.inspectProjects = inspectProjects;
    }

    public Set<StorageRequirement> getStorageRequirements() {
        return storageRequirements;
    }

    public void setStorageRequirements(Set<StorageRequirement> storageRequirements) {
        this.storageRequirements = storageRequirements;
    }

    public Set<ProduceMethod> getProductMethods() {
        return productMethods;
    }

    public void setProductMethods(Set<ProduceMethod> productMethods) {
        this.productMethods = productMethods;
    }

    public Set<Danger> getDangers() {
        return dangers;
    }

    public void setDangers(Set<Danger> dangers) {
        this.dangers = dangers;
    }

    public Set<Protection> getProtections() {
        return protections;
    }

    public void setProtections(Set<Protection> protections) {
        this.protections = protections;
    }

    public Set<FileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<FileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public Material() {
    }

    public Material(Long materialId, String materialName, String englishName, String nickName, String englishNickName, String relativeMolecularMass, String structuralFormula, String casRegistryNumber, String materialDescription, String materialAppearance, List<String> materialUsage, Set<String> materialLabels, Set<PhysicalProperty> physicalProperty, Set<ChemicalProperty> chemicalProperty, Set<AnalysisSpectrogram> analysisSpectrogram, Set<InspectProject> inspectProjects, Set<StorageRequirement> storageRequirements, Set<ProduceMethod> productMethods, Set<Danger> dangers, Set<Protection> protections, Set<FileKnowledge> associatedFiles) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.englishName = englishName;
        this.nickName = nickName;
        this.englishNickName = englishNickName;
        this.relativeMolecularMass = relativeMolecularMass;
        this.structuralFormula = structuralFormula;
        this.casRegistryNumber = casRegistryNumber;
        this.materialDescription = materialDescription;
        this.materialAppearance = materialAppearance;
        this.materialUsage = materialUsage;
        this.materialLabels = materialLabels;
        this.physicalProperty = physicalProperty;
        this.chemicalProperty = chemicalProperty;
        this.analysisSpectrogram = analysisSpectrogram;
        this.inspectProjects = inspectProjects;
        this.storageRequirements = storageRequirements;
        this.productMethods = productMethods;
        this.dangers = dangers;
        this.protections = protections;
        this.associatedFiles = associatedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(materialId, material.materialId) && Objects.equals(materialName, material.materialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialName);
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", englishNickName='" + englishNickName + '\'' +
                ", relativeMolecularMass='" + relativeMolecularMass + '\'' +
                ", structuralFormula='" + structuralFormula + '\'' +
                ", casRegistryNumber='" + casRegistryNumber + '\'' +
                ", materialDescription='" + materialDescription + '\'' +
                ", materialAppearance='" + materialAppearance + '\'' +
                ", materialUsage=" + materialUsage +
                ", materialLabels=" + materialLabels +
                ", physicalProperty=" + physicalProperty +
                ", chemicalProperty=" + chemicalProperty +
                ", analysisSpectrogram=" + analysisSpectrogram +
                ", inspectProjects=" + inspectProjects +
                ", storageRequirements=" + storageRequirements +
                ", productMethods=" + productMethods +
                ", dangers=" + dangers +
                ", protections=" + protections +
                ", associatedFiles=" + associatedFiles +
                '}';
    }
}
