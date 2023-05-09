package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementForParentProcess {

    Long parentId;

    ProcessElement originElement;
}
