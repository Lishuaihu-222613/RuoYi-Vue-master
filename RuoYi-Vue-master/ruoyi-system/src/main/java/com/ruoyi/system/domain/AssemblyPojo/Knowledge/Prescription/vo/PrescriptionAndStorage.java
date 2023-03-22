package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.StorageProperty;

public class PrescriptionAndStorage {

    private Long prescriptionId;

    private StorageProperty property;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public StorageProperty getProperty() {
        return property;
    }

    public void setProperty(StorageProperty property) {
        this.property = property;
    }

    public PrescriptionAndStorage() {
    }

    public PrescriptionAndStorage(Long prescriptionId, StorageProperty property) {
        this.prescriptionId = prescriptionId;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PrescriptionAndStorage{" +
                "prescriptionId=" + prescriptionId +
                ", property=" + property +
                '}';
    }
}
