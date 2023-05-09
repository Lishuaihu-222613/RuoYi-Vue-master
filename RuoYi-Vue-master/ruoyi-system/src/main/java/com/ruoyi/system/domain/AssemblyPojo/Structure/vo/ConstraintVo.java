package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstraintVo {

    Long elementId;

    String constraintType;

    Long otherElementId;

    String constraintName;

    String constraintValue;
}
