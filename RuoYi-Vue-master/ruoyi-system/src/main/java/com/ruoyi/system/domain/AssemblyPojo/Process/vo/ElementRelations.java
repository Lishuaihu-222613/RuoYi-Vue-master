package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import com.alibaba.fastjson.support.geo.LineString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementRelations {

    private Long elementId;

    private List<Long> relations;
}
