package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatedStructureVo {

    Long relatedId;

    List<Long> elementId;
}
