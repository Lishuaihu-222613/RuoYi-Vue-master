package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepAndStep {

    private Long stepId;

    private Step step;
}
