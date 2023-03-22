package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.CombustionProperty;

public class PrescriptionAndCombustion {

    private Long prescriptionId;

    private CombustionProperty property;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public CombustionProperty getProperty() {
        return property;
    }

    public void setProperty(CombustionProperty property) {
        this.property = property;
    }

    public PrescriptionAndCombustion() {
    }

    public PrescriptionAndCombustion(Long prescriptionId, CombustionProperty property) {
        this.prescriptionId = prescriptionId;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PrescriptionAndCombustion{" +
                "prescriptionId=" + prescriptionId +
                ", property=" + property +
                '}';
    }
}
