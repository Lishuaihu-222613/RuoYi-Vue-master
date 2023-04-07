package com.ruoyi.system.domain.AssemblyPojo.Resource.vo;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;

public class ResourceQueryVo {

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

    private AssemblyResource originResource;
}
