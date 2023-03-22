package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;

public class PrescriptionAndMechanical {

    private Long prescriptionId;

    private MechanicalStability stability;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public MechanicalStability getStability() {
        return stability;
    }

    public void setStability(MechanicalStability stability) {
        this.stability = stability;
    }

    public PrescriptionAndMechanical() {
    }

    public PrescriptionAndMechanical(Long prescriptionId, MechanicalStability stability) {
        this.prescriptionId = prescriptionId;
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "PrescriptionAndMechanical{" +
                "prescriptionId=" + prescriptionId +
                ", stability=" + stability +
                '}';
    }
}
