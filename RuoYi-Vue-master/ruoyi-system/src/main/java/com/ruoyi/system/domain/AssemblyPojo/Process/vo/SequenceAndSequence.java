package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceAndSequence {

    private Long sequenceId;

    private Sequence sequence;
}
