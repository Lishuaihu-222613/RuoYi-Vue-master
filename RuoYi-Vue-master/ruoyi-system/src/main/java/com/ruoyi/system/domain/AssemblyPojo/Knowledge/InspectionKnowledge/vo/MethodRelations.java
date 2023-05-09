package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodRelations {


    Long methodId;
    List<Long> problems;

    List<Long> resources;

    List<Long> files;
}
