package com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypicalProcessVo {

    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String sortableField;

    /** 排序的方向desc或者asc */
    private String sortType;

    /** 查询标签 */
    private String dynamicLabel;

    private Process originProcess;

    private Sequence originSequence;

    private Step originStep;
}