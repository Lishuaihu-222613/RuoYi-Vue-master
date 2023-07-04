package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatedProcessVo {
    Long relatedId;

    List<Long> elementId;
}
