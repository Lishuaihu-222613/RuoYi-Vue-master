package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.EnergyProperty;

public class PrescriptionAndEnergy {

    private Long prescriptionId;

    private EnergyProperty property;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public EnergyProperty getProperty() {
        return property;
    }

    public void setProperty(EnergyProperty property) {
        this.property = property;
    }

    public PrescriptionAndEnergy() {
    }

    public PrescriptionAndEnergy(Long prescriptionId, EnergyProperty property) {
        this.prescriptionId = prescriptionId;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PrescriptionAndEnergy{" +
                "prescriptionId=" + prescriptionId +
                ", property=" + property +
                '}';
    }
}
