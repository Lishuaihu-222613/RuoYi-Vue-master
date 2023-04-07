package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodQueryVo {

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

    /** 查询参数 */
    private InspectionMethod originMethod;
}
