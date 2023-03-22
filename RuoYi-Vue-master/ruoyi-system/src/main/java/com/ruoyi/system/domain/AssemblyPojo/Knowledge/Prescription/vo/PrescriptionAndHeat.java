package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;

public class PrescriptionAndHeat {

    private Long prescriptionId;

    private HeatStability stability;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public HeatStability getStability() {
        return stability;
    }

    public void setStability(HeatStability stability) {
        this.stability = stability;
    }

    public PrescriptionAndHeat() {
    }

    public PrescriptionAndHeat(Long prescriptionId, HeatStability stability) {
        this.prescriptionId = prescriptionId;
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "PrescriptionAndHeat{" +
                "prescriptionId=" + prescriptionId +
                ", stability=" + stability +
                '}';
    }
}
