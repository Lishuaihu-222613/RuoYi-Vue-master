package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.OxidantRatio;

public class PrescriptionAndOxidant {

    private Long prescriptionId;

    private OxidantRatio property;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public OxidantRatio getProperty() {
        return property;
    }

    public void setProperty(OxidantRatio property) {
        this.property = property;
    }

    public PrescriptionAndOxidant() {
    }

    public PrescriptionAndOxidant(Long prescriptionId, OxidantRatio property) {
        this.prescriptionId = prescriptionId;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PrescriptionAndOxidant{" +
                "prescriptionId=" + prescriptionId +
                ", property=" + property +
                '}';
    }
}
