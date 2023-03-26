package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ChemicalProperty;

public class CMvo {

    private Long materialId;

    private ChemicalProperty chemicalProperty;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public ChemicalProperty getChemicalProperty() {
        return chemicalProperty;
    }

    public void setChemicalProperty(ChemicalProperty chemicalProperty) {
        this.chemicalProperty = chemicalProperty;
    }

    public CMvo() {
    }

    public CMvo(Long materialId, ChemicalProperty chemicalProperty) {
        this.materialId = materialId;
        this.chemicalProperty = chemicalProperty;
    }

    @Override
    public String toString() {
        return "CMvo{" +
                "materialId=" + materialId +
                ", chemicalProperty=" + chemicalProperty +
                '}';
    }
}
