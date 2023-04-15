package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constraint {

    private Long constraintId;

    private String constraintName;

    private String constraintType;

    private String constraintValue;

    private Long elementId;
}
