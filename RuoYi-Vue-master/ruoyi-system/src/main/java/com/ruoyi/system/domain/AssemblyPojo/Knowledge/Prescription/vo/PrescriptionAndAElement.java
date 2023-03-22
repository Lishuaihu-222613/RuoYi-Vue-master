package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;

public class PrescriptionAndAElement {

    private Long prescriptionId;

    private hasMaterialElement element;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public hasMaterialElement getElement() {
        return element;
    }

    public void setElement(hasMaterialElement element) {
        this.element = element;
    }

    public PrescriptionAndAElement() {
    }

    public PrescriptionAndAElement(Long prescriptionId, hasMaterialElement element) {
        this.prescriptionId = prescriptionId;
        this.element = element;
    }

    @Override
    public String toString() {
        return "PrescriptionAndAElement{" +
                "prescriptionId=" + prescriptionId +
                ", element=" + element +
                '}';
    }
}
