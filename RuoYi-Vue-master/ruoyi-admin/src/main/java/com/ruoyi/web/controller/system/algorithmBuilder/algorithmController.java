package com.ruoyi.web.controller.system.algorithmBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyPart;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.PartsWithConstraints;
import com.ruoyi.system.service.ElementService.ElementService;
import com.ruoyi.system.service.algorithm.AssemblyUnitDivision;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/algorithm")
public class algorithmController extends BaseController {

    @Resource
    private AssemblyUnitDivision assemblyUnitDivision;

    @Resource
    private ElementService elementService;

    @ResponseBody
    @PostMapping("/checkConstraints")
    public R<Boolean> checkConstraints(@RequestBody List<PartsWithConstraints> constraints){
        try {
            Set<AssemblyPart> unknownParts = elementService.createUnknownParts(constraints);
            boolean check = assemblyUnitDivision.checkConstraintsOfParts(new ArrayList<>(unknownParts));
//            int numbers = assemblyUnitDivision.calculateUnitNumbers(new ArrayList<>(unknownParts));
//            System.out.println(numbers);
            List<AssemblyPart> assemblyParts = assemblyUnitDivision.determineReferencePart_2(new ArrayList<>(unknownParts), 3);
            System.out.println(check);
            return R.success(check);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
