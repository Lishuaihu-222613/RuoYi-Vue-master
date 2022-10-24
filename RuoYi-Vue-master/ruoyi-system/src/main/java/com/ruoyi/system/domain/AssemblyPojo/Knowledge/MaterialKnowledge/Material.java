package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node("Material")
public class Material {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "label")
    private String materialName;

    @Property(name = "EnglishName")
    private String englishName;

    @Property(name = "NickName")
    private String nickName;

    @Property(name = "EnglishNickName")
    private String englishNickName;

    @Property(name = "RelativeMolecularMass")
    private String relativeMolecularMass;

    @Property(name = "StructuralFormula")
    private String structuralFormula;

    @Property(name = "CAS-RegistryNumber")
    private String casRegistryNumber;

    @Property(name = "Description")
    private String materialDescription;

    @Property(name = "Appearance")
    private String materialAppearance;

    @Property(name = "PhysicalProperty")
    private List<String> physicalProperty;

    @Property(name = "ChemicalProperty")
    private List<String> chemicalProperty;

    @Property(name = "Usage")
    private List<String> materialUsage;

    @Property(name = "AnalysisSpectrogram")
    private Map<String, String> analysisSpectrogram;

    @Relationship(type = "hasInspectProject", direction = Relationship.Direction.OUTGOING)
    private Set<InspectProject> inspectProjects;

    @Relationship(type = "hasRequirement", direction = Relationship.Direction.OUTGOING)
    private Set<StorageRequirement> storageRequirements;

    @Relationship(type = "hasProduceMethod", direction = Relationship.Direction.OUTGOING)
    private Set<produceMethod> productMethods;

    @Relationship(type = "hasDanger", direction = Relationship.Direction.OUTGOING)
    private Set<Danger> dangers;

    @Relationship(type = "hasProtection", direction = Relationship.Direction.OUTGOING)
    private Set<Protection> protections;

    @Relationship(type = "hasAssociatedFile", direction = Relationship.Direction.OUTGOING)
    private Set<fileKnowledge> associatedFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getPhysicalProperty() {
        return physicalProperty;
    }

    public void setPhysicalProperty(List<String> physicalProperty) {
        this.physicalProperty = physicalProperty;
    }

    public List<String> getChemicalProperty() {
        return chemicalProperty;
    }

    public void setChemicalProperty(List<String> chemicalProperty) {
        this.chemicalProperty = chemicalProperty;
    }

    public List<String> getMaterialUsage() {
        return materialUsage;
    }

    public void setMaterialUsage(List<String> materialUsage) {
        this.materialUsage = materialUsage;
    }

    public Map<String, String> getAnalysisSpectrogram() {
        return analysisSpectrogram;
    }

    public void setAnalysisSpectrogram(Map<String, String> analysisSpectrogram) {
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

    public Set<produceMethod> getProductMethods() {
        return productMethods;
    }

    public void setProductMethods(Set<produceMethod> productMethods) {
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

    public Set<fileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<fileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public Material() {
    }

    public Material(Long id, String materialName, String englishName, String nickName, String englishNickName, String relativeMolecularMass, String structuralFormula, String casRegistryNumber, String materialDescription, String materialAppearance, List<String> physicalProperty, List<String> chemicalProperty, List<String> materialUsage, Map<String, String> analysisSpectrogram, Set<InspectProject> inspectProjects, Set<StorageRequirement> storageRequirements, Set<produceMethod> productMethods, Set<Danger> dangers, Set<Protection> protections, Set<fileKnowledge> associatedFiles) {
        this.id = id;
        this.materialName = materialName;
        this.englishName = englishName;
        this.nickName = nickName;
        this.englishNickName = englishNickName;
        this.relativeMolecularMass = relativeMolecularMass;
        this.structuralFormula = structuralFormula;
        this.casRegistryNumber = casRegistryNumber;
        this.materialDescription = materialDescription;
        this.materialAppearance = materialAppearance;
        this.physicalProperty = physicalProperty;
        this.chemicalProperty = chemicalProperty;
        this.materialUsage = materialUsage;
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
        return Objects.equals(id, material.id) && Objects.equals(materialName, material.materialName) && Objects.equals(englishName, material.englishName) && Objects.equals(nickName, material.nickName) && Objects.equals(englishNickName, material.englishNickName) && Objects.equals(relativeMolecularMass, material.relativeMolecularMass) && Objects.equals(structuralFormula, material.structuralFormula) && Objects.equals(casRegistryNumber, material.casRegistryNumber) && Objects.equals(materialDescription, material.materialDescription) && Objects.equals(materialAppearance, material.materialAppearance) && Objects.equals(physicalProperty, material.physicalProperty) && Objects.equals(chemicalProperty, material.chemicalProperty) && Objects.equals(materialUsage, material.materialUsage) && Objects.equals(analysisSpectrogram, material.analysisSpectrogram) && Objects.equals(inspectProjects, material.inspectProjects) && Objects.equals(storageRequirements, material.storageRequirements) && Objects.equals(productMethods, material.productMethods) && Objects.equals(dangers, material.dangers) && Objects.equals(protections, material.protections) && Objects.equals(associatedFiles, material.associatedFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialName, englishName, nickName, englishNickName, relativeMolecularMass, structuralFormula, casRegistryNumber, materialDescription, materialAppearance, physicalProperty, chemicalProperty, materialUsage, analysisSpectrogram, inspectProjects, storageRequirements, productMethods, dangers, protections, associatedFiles);
    }
}
