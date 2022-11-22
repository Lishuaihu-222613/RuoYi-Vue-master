package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;

import java.util.Map;
import java.util.Set;

public interface PrescriptionInterface {

    Long getPrescriptionId();

    String getPrescriptionName();

    String getPrescriptionDescription();

    String getDensity();

    String getDangerLevel();

    Map<String, String> getCuringTime();

    Map<String, String> getOtherProperty();

    Set<String> getPrescriptionLabels();

    Set<hasMaterialElementInterface> getMaterialElements();
}
