package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.ForceProperty;

public class PrescriptionAndForce {

    private Long prescriptionId;

    private ForceProperty property;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public ForceProperty getProperty() {
        return property;
    }

    public void setProperty(ForceProperty property) {
        this.property = property;
    }

    public PrescriptionAndForce() {
    }

    public PrescriptionAndForce(Long prescriptionId, ForceProperty property) {
        this.prescriptionId = prescriptionId;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PrescriptionAndForce{" +
                "prescriptionId=" + prescriptionId +
                ", property=" + property +
                '}';
    }
}
