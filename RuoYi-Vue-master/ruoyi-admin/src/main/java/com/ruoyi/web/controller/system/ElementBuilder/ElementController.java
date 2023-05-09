package com.ruoyi.web.controller.system.ElementBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.*;
import com.ruoyi.system.service.ElementService.ElementService;
import com.ruoyi.system.service.KnowledgeService.File.FileKnowledgeService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/element")
public class ElementController extends BaseController {

    @Resource
    private ElementService elementService;

    @Resource
    private FileKnowledgeService fileKnowledgeService;

    @ResponseBody
    @GetMapping("/getAllProducts")
    public R<List<AssemblyElement>> getAllProducts(){
        try {
            List<AssemblyElement> productList = elementService.getAllAssemblyProducts();
            System.out.println(productList);
            return R.success(productList);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getProductListsByLabel")
    public R<List<AssemblyElement>> getProductListsByLabel(@RequestBody ElementQueryVo params){
        try {
            List<AssemblyElement> productList = elementService.getAllAssemblyProductsByLabel(params.getDynamicLabel());
            System.out.println(productList);
            return R.success(productList);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProductOptionsByLabel/{label}")
    public R<List<AssemblyElement>> getProductOptionsByLabel(@PathVariable String label){
        try {
            List<AssemblyElement> elements = elementService.getAllAssemblyProductsByLabel(label);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getProductPagesByLabel")
    public R<Page<AssemblyElement>> getProductPagesByLabel(@RequestBody ElementQueryVo params){
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<AssemblyElement> productPage = elementService.getAllAssemblyProductsByLabel(params.getDynamicLabel(), pageable);
            System.out.println(productPage);
            return R.success(productPage);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getElementsByParams")
    public R<Page<AssemblyElement>> getElementsByParams(@RequestBody ElementQueryVo params){
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("elementDescription",ExampleMatcher.GenericPropertyMatcher::contains);
            AssemblyElement element = new AssemblyElement();
            element.setElementName(params.getOriginElement().getElementName());
            element.setElementDescription(params.getOriginElement().getElementDescription());
            Example<AssemblyElement> example = Example.of(element,matcher);
            Page<AssemblyElement> productsByParams = elementService.getElementsByParams(example,pageable);
            System.out.println(productsByParams);
            return R.success(productsByParams);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createElement")
    public R<AssemblyElement> createElement(@RequestBody ElementForParent EP){
        try {
            AssemblyElement newElement = new AssemblyElement();
            if(EP.getParentId() == 0){
                newElement = elementService.createElement(EP.getOriginElement());
            } else {
                newElement = elementService.createElementForParent(EP);
            }
            System.out.println(newElement);
            return R.success(newElement);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateElement")
    public R<AssemblyElement> updateElement(@RequestBody ElementForParent EP){
        try {
            AssemblyElement newElement = new AssemblyElement();
            if(EP.getParentId() == 0){
                newElement = elementService.updateElement(EP.getOriginElement());
            } else {
                newElement = elementService.updateElementForParent(EP);
            }
            System.out.println(newElement);
            return R.success(newElement);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSubElementsById/{elementId}")
    public R<List<AssemblyElement>> getSubElementsById(@PathVariable Long elementId){
        try {
            List<AssemblyElement> elements = elementService.getSubElementsById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getParentElementById/{elementId}")
    public R<AssemblyElement> getParentElementById(@PathVariable Long elementId){
        try {
            AssemblyElement elements = elementService.getParentElementById(elementId);
            System.out.println(elements);
            return R.success(elements);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSingleElementById/{elementId}")
    public R<AssemblyElement> getSingleElementById(@PathVariable Long elementId){
        try {
            AssemblyElement element = elementService.getSingleElementById(elementId);
            System.out.println(element);
            return R.success(element);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };



    @ResponseBody
    @GetMapping("/getConstraintsByElementId/{elementId}")
    public R<List<AssemblyConstraint>> getConstraintsByElementId(@PathVariable Long elementId){
        try {
            List<AssemblyConstraint> constraints = elementService.getConstraintsByElementId(elementId);
            System.out.println(constraints);
            return R.success(constraints);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createConstraint")
    public R<AssemblyConstraint> createConstraint(@RequestBody ConstraintVo vo){
        try {
            AssemblyConstraint constraint1 = elementService.createConstraint(vo);
            System.out.println(constraint1);
            return R.success(constraint1);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateConstraint")
    public R<AssemblyConstraint> updateConstraint(@RequestBody ConstraintForElement CE){
        try {
            AssemblyConstraint constraint = elementService.updateConstraint(CE);
            System.out.println(constraint);
            return R.success(constraint);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteConstraint/{constraintId}")
    public R<String> deleteConstraint(@PathVariable Long constraintId){
        try {
            elementService.deleteConstraint(constraintId);
            return R.success("删除成功");
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getElementById/{elementId}")
    public R<AssemblyElement> getElementById(@PathVariable Long elementId){
        try {
            AssemblyElement element = elementService.getElementById(elementId);
            return R.success(element);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getRelatedStructure/{relatedId}")
    public R<List<AssemblyElement>> getRelatedStructure(@PathVariable Long relatedId){
        try {
            List<AssemblyElement> structure = elementService.getRelatedStructure(relatedId);
            return R.success(structure);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyRelatedStructure")
    public R<String> modifyRelatedStructure(@RequestBody RelatedStructureVo vo ){
        try {
            elementService.modifyRelatedStructure(vo);
            return R.success("修改成功！");
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/deleteElements")
    public R<String> deleteElements(@RequestBody Long[] elements ){
        try {
            elementService.deleteElements(elements);
            return R.success("删除成功！");
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyFiles")
    public R<String> modifyFiles(@RequestBody RelationsVoForElement relations ){
        try {
            fileKnowledgeService.createRelatedRelations(relations.getElementId(), relations.getAssociatedFileIds());
            elementService.modifyRelations(relations);
            return R.success("更改成功！");
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };





}
