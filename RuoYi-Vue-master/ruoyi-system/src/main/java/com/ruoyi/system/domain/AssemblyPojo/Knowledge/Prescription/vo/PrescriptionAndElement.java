package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionAndElement {

    private Long prescriptionId;

    private List<MaterialAndValue> elements;

}
