package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;

public class PrescriptionAndExplosion {

    private Long prescriptionId;

    private ExplosionStability stability;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public ExplosionStability getStability() {
        return stability;
    }

    public void setStability(ExplosionStability stability) {
        this.stability = stability;
    }

    public PrescriptionAndExplosion() {
    }

    public PrescriptionAndExplosion(Long prescriptionId, ExplosionStability stability) {
        this.prescriptionId = prescriptionId;
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "PrescriptionAndExplosion{" +
                "prescriptionId=" + prescriptionId +
                ", stability=" + stability +
                '}';
    }
}
