package com.ruoyi.web.controller.system.RecommandBuilder;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommandSequenceVo {

    Long sequenceId;

    String label;

    ProcessElement originSequence;
}
