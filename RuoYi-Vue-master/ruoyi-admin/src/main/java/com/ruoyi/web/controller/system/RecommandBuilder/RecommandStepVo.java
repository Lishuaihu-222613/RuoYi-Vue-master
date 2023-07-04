package com.ruoyi.web.controller.system.RecommandBuilder;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommandStepVo {

    Long stepId;

    String label;

    ProcessElement originStep;
}
