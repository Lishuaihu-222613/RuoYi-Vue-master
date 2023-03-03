package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepAndSequence {

    private Long sequenceId;

    private Step step;
}
