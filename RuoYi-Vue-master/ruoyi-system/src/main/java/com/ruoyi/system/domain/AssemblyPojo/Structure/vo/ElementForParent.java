package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementForParent {

    private Long parentId;

    private AssemblyElement originElement;
}
