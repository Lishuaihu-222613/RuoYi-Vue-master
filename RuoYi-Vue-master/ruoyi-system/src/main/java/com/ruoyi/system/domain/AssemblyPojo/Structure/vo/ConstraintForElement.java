package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstraintForElement {

    private Long elementId;

    private AssemblyConstraint originConstraint;
}
