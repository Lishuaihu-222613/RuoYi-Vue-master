package com.ruoyi.system.domain.AssemblyPojo.Resource.vo;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.FixtureTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private AuxiliaryResource originAuxiliaryResource;

    private EquipmentResource originEquipmentResource;

    private FixtureTool originFixtureTool;

    private MeasuringTool originMeasuringTool;

    private SpreaderTool originSpreaderTool;

    private SpecialTool originSpecialTool;

    private MouldTool originMouldTool;

}
