package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Set;

@Node("MaterialKnowledge")
public class MaterialKnowledge {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String mKName;

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

    public Long getId() {

        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getmKName() {
        return mKName;
    }

    public void setmKName(String mKName) {
        this.mKName = mKName;
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
}
