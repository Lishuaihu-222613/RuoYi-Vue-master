package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;

import java.util.Set;

public class PrescriptionAndElement {

    private Long prescriptionId;

    private Set<hasMaterialElement> elements;

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Set<hasMaterialElement> getElements() {
        return elements;
    }

    public void setElements(Set<hasMaterialElement> elements) {
        this.elements = elements;
    }

    public PrescriptionAndElement() {
    }

    public PrescriptionAndElement(Long prescriptionId, Set<hasMaterialElement> elements) {
        this.prescriptionId = prescriptionId;
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "PrescriptionAndElement{" +
                "prescriptionId=" + prescriptionId +
                ", elements=" + elements +
                '}';
    }
}
