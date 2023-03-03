package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Normal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessIdAndSequence {

    private Long processId;

    private Normal sequence;

}
