package com.ruoyi.web.controller.system.ProcessBulider;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.*;
import com.ruoyi.system.service.ProcessService.ProcessService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/process")
public class ProcessController extends BaseController {

    @Resource
    private ProcessService processService;


    @ResponseBody
    @GetMapping("/getAllProcess")
    public R<List<ProcessElement>> getAllProcess() {
        try {
            List<ProcessElement> elements = processService.getAllAssemblyProcess();
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getProcessListsByLabel")
    public R<List<ProcessElement>> getProcessListsByLabel(@RequestBody PElementQueryVo params) {
        try {
            List<ProcessElement> elements = processService.getAllAssemblyProcessByLabel(params.getDynamicLabel());
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getProcessOptionsByLabel/{label}")
    public R<List<ProcessElement>> getProcessOptionsByLabel(@PathVariable String label) {
        try {
            List<ProcessElement> elements = processService.getAllAssemblyProcessByLabel(label);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getProcessPagesByLabel")
    public R<Page<ProcessElement>> getProcessPagesByLabel(@RequestBody PElementQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum() - 1, params.getPageSize(), sort);
            Page<ProcessElement> elements = processService.getAllAssemblyProcessByLabel(params.getDynamicLabel(), pageable);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getElementsByParams")
    public R<Page<ProcessElement>> getElementsByParams(@RequestBody PElementQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum() - 1, params.getPageSize(), sort);
            ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::contains).withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::contains);
            ProcessElement element = new ProcessElement();
            element.setElementName(params.getOriginElement().getElementName());
            element.setElementDescription(params.getOriginElement().getElementDescription());
            Example<ProcessElement> example = Example.of(element, matcher);
            Page<ProcessElement> elementsByParams = processService.getElementsByParams(example, pageable);
            System.out.println(elementsByParams);
            return R.success(elementsByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/createElement")
    public R<ProcessElement> createElement(@RequestBody ElementForParentProcess EP) {
        try {
            ProcessElement newElement = new ProcessElement();
            if (EP.getParentId() == 0 ) {
                newElement = processService.createElement(EP.getOriginElement());
            } else {
                newElement = processService.createElementForParent(EP);
            }
            System.out.println(newElement);
            return R.success(newElement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/updateElement")
    public R<ProcessElement> updateElement(@RequestBody ElementForParentProcess EP) {
        try {
            ProcessElement newElement = new ProcessElement();
            if (EP.getParentId() == 0) {
                newElement = processService.updateElement(EP.getOriginElement());
            } else {
                newElement = processService.updateElementForParent(EP);
            }
            System.out.println(newElement);
            return R.success(newElement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getSubElementsById/{elementId}")
    public R<List<ProcessElement>> getSubElementsById(@PathVariable Long elementId) {
        try {
            List<ProcessElement> elements = processService.getSubElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getElementById/{elementId}")
    public R<ProcessElement> getElementById(@PathVariable Long elementId) {
        try {
            ProcessElement element = processService.getElementById(elementId);
            System.out.println(element);
            return R.success(element);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getSingleElementsById/{elementId}")
    public R<ProcessElement> getSingleElementsById(@PathVariable Long elementId) {
        try {
            ProcessElement element = processService.getSingleElementById(elementId);
            System.out.println(element);
            return R.success(element);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;


    @ResponseBody
    @GetMapping("/getParentElementById/{elementId}")
    public R<ProcessElement> getParentElementById(@PathVariable Long elementId) {
        try {
            ProcessElement element = processService.getParentElementById(elementId);
            System.out.println(element);
            return R.success(element);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getBeforeElementsById/{elementId}")
    public R<List<ProcessElement>> getBeforeElementsById(@PathVariable Long elementId) {
        try {
            List<ProcessElement> elements = processService.getBeforeElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getAfterElementsById/{elementId}")
    public R<List<ProcessElement>> getAfterElementsById(@PathVariable Long elementId) {
        try {
            List<ProcessElement> elements = processService.getAfterElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getAndElementsById/{elementId}")
    public R<List<ProcessElement>> getAndElementsById(@PathVariable Long elementId) {
        try {
            List<ProcessElement> elements = processService.getAndElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @GetMapping("/getOrElementsById/{elementId}")
    public R<List<ProcessElement>> getOrElementsById(@PathVariable Long elementId) {
        try {
            List<ProcessElement> elements = processService.getOrElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/modifyRelations")
    public R<String> modifyRelations(@RequestBody RelationsVoForProcessElement vo) {
        try {
            processService.modifyRelations(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/updateRelation")
    public R<String> updateRelation(@RequestBody OtherRelations vo) {
        try {
            processService.updateRelation(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/modifyBeforeElement")
    public R<String> modifyBeforeElement(@RequestBody ElementRelations vo) {
        try {
            processService.modifyBeforeRelations(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/modifyAfterElement")
    public R<String> modifyAfterElement(@RequestBody ElementRelations vo) {
        try {
            processService.modifyAfterRelations(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/modifyAndElement")
    public R<String> modifyAndElement(@RequestBody ElementRelations vo) {
        try {
            processService.modifyAndRelations(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/modifyOrElement")
    public R<String> modifyOrElement(@RequestBody ElementRelations vo) {
        try {
            processService.modifyOrRelations(vo);
            return R.success("关系修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

}
