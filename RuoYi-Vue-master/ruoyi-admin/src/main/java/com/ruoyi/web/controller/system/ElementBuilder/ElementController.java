package com.ruoyi.web.controller.system.ElementBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.Constraint;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ConstraintForElement;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ElementForParent;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ElementQueryVo;
import com.ruoyi.system.service.ElementService.ElementService;
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
    public R<AssemblyConstraint> createConstraint(@RequestBody AssemblyConstraint constraint){
        try {
            AssemblyConstraint constraint1 = elementService.createConstraint(constraint);
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
    @GetMapping("/getProductById/{elementId}")
    public R<AssemblyElement> getWholeProductById(@PathVariable Long elementId){
        try {
            AssemblyElement element = elementService.getElementById(elementId);
            return R.success(element);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };



    @ResponseBody
    @PostMapping("/createProduct")
    public R<List<AssemblyProduct>> createProduct(@RequestBody AssemblyProduct product){
        try {
            AssemblyProduct assemblyproduct = elementService.createProduct(product);
            System.out.println(assemblyproduct);
            return R.success(assemblyproduct);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createComponent")
    public R<List<AssemblyProduct>> createComponent(@RequestBody AssemblyComponent component ){
        try {
            elementService.createComponent(component);
            System.out.println(component);
            return R.success(component);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPart")
    public R<List<AssemblyProduct>> createPart(@RequestBody AssemblyPart part ){
        try {
            elementService.createPart(part);
            System.out.println(part);
            return R.success(part);
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



}
