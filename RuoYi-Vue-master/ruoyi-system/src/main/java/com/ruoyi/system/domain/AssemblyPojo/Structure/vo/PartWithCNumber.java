package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyPart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartWithCNumber {

    private AssemblyPart part;

    private int number;
}
