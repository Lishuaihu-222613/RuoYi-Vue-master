package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionElements {

    private Long relationId;

    private Double percentage;

    private Long materialId;

    private String materialName;
}
