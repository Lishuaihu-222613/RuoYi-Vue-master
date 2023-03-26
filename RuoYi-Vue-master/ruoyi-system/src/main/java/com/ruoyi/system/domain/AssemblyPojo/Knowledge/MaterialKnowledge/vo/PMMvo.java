package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ProduceMethod;

public class PMMvo {

    private Long materialId;

    private ProduceMethod produceMethod;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public ProduceMethod getProduceMethod() {
        return produceMethod;
    }

    public void setProduceMethod(ProduceMethod produceMethod) {
        this.produceMethod = produceMethod;
    }

    public PMMvo() {
    }

    public PMMvo(Long materialId, ProduceMethod produceMethod) {
        this.materialId = materialId;
        this.produceMethod = produceMethod;
    }

    @Override
    public String toString() {
        return "PMMvo{" +
                "materialId=" + materialId +
                ", produceMethod=" + produceMethod +
                '}';
    }
}
