package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationsVoForProcessElement {

    Long elementId;

    List<Long> beforeElementIds;

    List<Long> afterElementIds;

    List<Long> andElementIds;

    List<Long> orElementIds;
}
