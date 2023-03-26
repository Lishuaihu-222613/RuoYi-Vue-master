package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Danger;

public class DMvo {

    private Long materialId;

    private Danger danger;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Danger getDanger() {
        return danger;
    }

    public void setDanger(Danger danger) {
        this.danger = danger;
    }

    public DMvo() {
    }

    public DMvo(Long materialId, Danger danger) {
        this.materialId = materialId;
        this.danger = danger;
    }

    @Override
    public String toString() {
        return "DMvo{" +
                "materialId=" + materialId +
                ", danger=" + danger +
                '}';
    }
}
