package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;

public class PrescriptionAndRadio {

    private Long prescriptionId;

    private RadioStability stability;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public RadioStability getStability() {
        return stability;
    }

    public void setStability(RadioStability stability) {
        this.stability = stability;
    }

    public PrescriptionAndRadio() {
    }

    public PrescriptionAndRadio(Long prescriptionId, RadioStability stability) {
        this.prescriptionId = prescriptionId;
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "PrescriptionAndRadio{" +
                "prescriptionId=" + prescriptionId +
                ", stability=" + stability +
                '}';
    }
}
