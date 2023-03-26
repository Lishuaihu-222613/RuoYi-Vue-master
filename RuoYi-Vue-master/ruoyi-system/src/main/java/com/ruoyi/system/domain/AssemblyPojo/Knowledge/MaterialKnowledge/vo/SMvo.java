package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.StorageRequirement;

public class SMvo {

    private Long materialId;

    private StorageRequirement storageRequirement;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public StorageRequirement getStorageRequirement() {
        return storageRequirement;
    }

    public void setStorageRequirement(StorageRequirement storageRequirement) {
        this.storageRequirement = storageRequirement;
    }

    public SMvo() {
    }

    public SMvo(Long materialId, StorageRequirement storageRequirement) {
        this.materialId = materialId;
        this.storageRequirement = storageRequirement;
    }

    @Override
    public String toString() {
        return "SMvo{" +
                "materialId=" + materialId +
                ", storageRequirement=" + storageRequirement +
                '}';
    }
}
