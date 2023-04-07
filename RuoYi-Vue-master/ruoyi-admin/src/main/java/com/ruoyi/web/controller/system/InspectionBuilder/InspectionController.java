package com.ruoyi.web.controller.system.InspectionBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ConditionForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.FactorForMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.MethodQueryVo;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.vo.ModeForMethod;
import com.ruoyi.system.service.KnowledgeService.InspectionMethod.InspectionMethodService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/method")
public class InspectionController extends BaseController {

    @Resource
    private InspectionMethodService inspectionMethodService;

    @ResponseBody
    @PostMapping("/getInspectionMethodsByType")
    public R<Page<InspectionMethod>> getInspectionMethodsByType(@RequestBody MethodQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<InspectionMethod>  methods = inspectionMethodService.getInspectionMethodsByType(params.getDynamicLabel(),pageable);
            System.out.println(methods);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllInspectionMethods")
    public R<Page<InspectionMethod>> getAllInspectionMethods(@RequestBody MethodQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<InspectionMethod>  methods = inspectionMethodService.getAllInspectionMethods(pageable);
            System.out.println(methods);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllInspectionMethodsByParams")
    public R<Page<InspectionMethod>> getAllInspectionMethodsByParams(@RequestBody MethodQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Example<InspectionMethod> example = Example.of(params.getOriginMethod());
            Page<InspectionMethod>  methods = inspectionMethodService.getAllInspectionMethods(pageable);
            System.out.println(methods);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectionMethodsByName/{methodName}")
    public R<List<InspectionMethod>> getInspectionMethodsByName(@PathVariable String methodName) {
        try {
            List<InspectionMethod> methods = inspectionMethodService.getInspectionMethodsByName(methodName);
            System.out.println(methods);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectionMethodById/{methodId}")
    public R<InspectionMethod> getInspectionMethodById(@PathVariable Long methodId) {
        try {
            InspectionMethod method = inspectionMethodService.getInspectionMethodById(methodId);
            System.out.println(method);
            return R.success(method);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createInspectionMethod")
    public R<InspectionMethod> createInspectionMethod(@RequestBody InspectionMethod method) {
        try {
            InspectionMethod inspectionMethod = inspectionMethodService.createInspectionMethod(method);
            System.out.println(inspectionMethod);
            return R.success(inspectionMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectionMethod")
    public R<InspectionMethod> updateInspectionMethod(@RequestBody InspectionMethod method) {
        try {
            InspectionMethod inspectionMethod = inspectionMethodService.updateInspectionMethod(method);
            System.out.println(inspectionMethod);
            return R.success(inspectionMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteInspectionMethod/{methodId}")
    public R<Principle> deleteInspectionMethod(@PathVariable Long methodId) {
        try {
            inspectionMethodService.deleteInspectionMethod(methodId);
            return R.success("删除" + methodId + "的检测方法");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createInspectionCondition")
    public R<InspectionCondition> createInspectionCondition(@RequestBody ConditionForMethod CM) {
        try {
            InspectionCondition condition = inspectionMethodService.createInspectionCondition(CM);
            System.out.println(condition);
            return R.success(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectionCondition")
    public R<InspectionCondition> updateInspectionCondition(@RequestBody InspectionCondition condition) {
        try {
            InspectionCondition newCondition = inspectionMethodService.updateInspectionCondition(condition);
            System.out.println(newCondition);
            return R.success(newCondition);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteInspectionCondition/{conditionId}")
    public R<InspectionCondition> deleteInspectionCondition(@PathVariable Long conditionId) {
        try {
            inspectionMethodService.deleteInspectionCondition(conditionId);
            return R.success("删除" + conditionId + "的检测条件");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectionConditionByMethod/{methodId}")
    public R<List<InspectionCondition>> getInspectionConditionByMethod(@PathVariable Long methodId) {
        try {
            List<InspectionCondition> conditions = inspectionMethodService.getInspectionConditionsByMethod(methodId);
            System.out.println(conditions);
            return R.success(conditions);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createInspectionFactor")
    public R<InspectionFactor> createInspectionFactor(@RequestBody FactorForMethod FM) {
        try {
            InspectionFactor Factor = inspectionMethodService.createInspectionFactor(FM);
            System.out.println(Factor);
            return R.success(Factor);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectionFactor")
    public R<InspectionFactor> updateInspectionFactor(@RequestBody InspectionFactor factor) {
        try {
            InspectionFactor newFactor = inspectionMethodService.updateInspectionFactor(factor);
            System.out.println(newFactor);
            return R.success(newFactor);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteInspectionFactor/{factorId}")
    public R<InspectionFactor> deleteInspectionFactor(@PathVariable Long factorId) {
        try {
            inspectionMethodService.deleteInspectionFactor(factorId);
            return R.success("删除" + factorId + "的影响因素");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectionFactorByMethod/{methodId}")
    public R<List<InspectionFactor>> getInspectionFactorByMethod(@PathVariable Long methodId) {
        try {
            List<InspectionFactor> factors = inspectionMethodService.getInspectionFactorsByMethod(methodId);
            System.out.println(factors);
            return R.success(factors);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createInspectionMode")
    public R<InspectionMode> createInspectionMode(@RequestBody ModeForMethod MM) {
        try {
            InspectionMode mode = inspectionMethodService.createInspectionMode(MM);
            System.out.println(mode);
            return R.success(mode);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectionMode")
    public R<InspectionMode> updateInspectionMode(@RequestBody InspectionMode mode) {
        try {
            InspectionMode newMode = inspectionMethodService.updateInspectionMode(mode);
            System.out.println(newMode);
            return R.success(newMode);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteInspectionMode/{modeId}")
    public R<InspectionMode> deleteInspectionMode(@PathVariable Long modeId) {
        try {
            inspectionMethodService.deleteInspectionMode(modeId);
            return R.success("删除" + modeId + "的检测模式");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectionModeByMethod/{methodId}")
    public R<List<InspectionMode>> getInspectionModeByMethod(@PathVariable Long methodId) {
        try {
            List<InspectionMode> modes = inspectionMethodService.getInspectionModesByMethod(methodId);
            System.out.println(modes);
            return R.success(modes);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
