package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.PhysicalProperty;

public class PMvo {

    private Long materialId;

    private PhysicalProperty physicalProperty;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public PhysicalProperty getPhysicalProperty() {
        return physicalProperty;
    }

    public void setPhysicalProperty(PhysicalProperty physicalProperty) {
        this.physicalProperty = physicalProperty;
    }

    public PMvo() {
    }

    public PMvo(Long materialId, PhysicalProperty physicalProperty) {
        this.materialId = materialId;
        this.physicalProperty = physicalProperty;
    }

    @Override
    public String toString() {
        return "PMvo{" +
                "materialId=" + materialId +
                ", physicalProperty=" + physicalProperty +
                '}';
    }
}
