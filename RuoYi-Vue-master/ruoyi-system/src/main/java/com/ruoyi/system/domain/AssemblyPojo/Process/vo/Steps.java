package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Steps {

    private Long stepId;

    private Set<Long> stepIds;
}
