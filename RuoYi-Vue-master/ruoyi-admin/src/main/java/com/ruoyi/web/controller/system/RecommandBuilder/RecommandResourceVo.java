package com.ruoyi.web.controller.system.RecommandBuilder;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommandResourceVo {

    Long elementId;

    String label;

    ProcessElement originElement;
}
