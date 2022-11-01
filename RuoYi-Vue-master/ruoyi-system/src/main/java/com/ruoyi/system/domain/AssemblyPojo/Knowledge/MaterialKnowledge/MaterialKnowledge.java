package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node("MaterialKnowledge")
public class MaterialKnowledge {

    @Id
    @GeneratedValue
    private Long MKId;

    @Property(name = "label")
    private String MKName;

    @Property(name = "MaterialType")
    private String materialType;

    @Property(name = "Description")
    private String materialDescription;

    @Property(name = "materialFunction")
    private List<String> materialFunctions;

    @Property(name = "materialRequirement")
    private List<String> materialRequirements;

    @Relationship(type = "hasCommonMaterial" , direction = Relationship.Direction.OUTGOING)
    private Set<Material> commonMaterials;

    public Long getMKId() {
        return MKId;
    }

    public void setMKId(Long MKId) {
        this.MKId = MKId;
    }

    public String getMKName() {
        return MKName;
    }

    public void setMKName(String MKName) {
        this.MKName = MKName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public List<String> getMaterialFunctions() {
        return materialFunctions;
    }

    public void setMaterialFunctions(List<String> materialFunctions) {
        this.materialFunctions = materialFunctions;
    }

    public List<String> getMaterialRequirements() {
        return materialRequirements;
    }

    public void setMaterialRequirements(List<String> materialRequirements) {
        this.materialRequirements = materialRequirements;
    }

    public Set<Material> getCommonMaterials() {
        return commonMaterials;
    }

    public void setCommonMaterials(Set<Material> commonMaterials) {
        this.commonMaterials = commonMaterials;
    }

    public MaterialKnowledge() {
    }

    public MaterialKnowledge(Long MKId, String MKName, String materialType, String materialDescription, List<String> materialFunctions, List<String> materialRequirements, Set<Material> commonMaterials) {
        this.MKId = MKId;
        this.MKName = MKName;
        this.materialType = materialType;
        this.materialDescription = materialDescription;
        this.materialFunctions = materialFunctions;
        this.materialRequirements = materialRequirements;
        this.commonMaterials = commonMaterials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialKnowledge that = (MaterialKnowledge) o;
        return Objects.equals(MKId, that.MKId) && Objects.equals(MKName, that.MKName) && Objects.equals(materialType, that.materialType) && Objects.equals(materialDescription, that.materialDescription) && Objects.equals(materialFunctions, that.materialFunctions) && Objects.equals(materialRequirements, that.materialRequirements) && Objects.equals(commonMaterials, that.commonMaterials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MKId, MKName, materialType, materialDescription, materialFunctions, materialRequirements, commonMaterials);
    }
}
