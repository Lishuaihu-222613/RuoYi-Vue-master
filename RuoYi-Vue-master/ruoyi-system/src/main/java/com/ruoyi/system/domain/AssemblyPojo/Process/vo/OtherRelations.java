package com.ruoyi.system.domain.AssemblyPojo.Process.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherRelations {

    Long elementId;

    List<Long> structures;

    List<Long> resources;

    List<Long> files;
}
