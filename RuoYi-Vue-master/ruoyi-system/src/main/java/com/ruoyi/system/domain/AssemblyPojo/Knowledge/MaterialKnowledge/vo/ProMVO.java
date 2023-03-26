package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Protection;

public class ProMVO {

    private Long materialId;

    private Protection protection;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Protection getProtection() {
        return protection;
    }

    public void setProtection(Protection protection) {
        this.protection = protection;
    }

    public ProMVO() {
    }

    public ProMVO(Long materialId, Protection protection) {
        this.materialId = materialId;
        this.protection = protection;
    }

    @Override
    public String toString() {
        return "ProMVO{" +
                "materialId=" + materialId +
                ", protection=" + protection +
                '}';
    }
}
