package com.ruoyi.web.controller.system.StructureBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.PartsWithConstraints;
import com.ruoyi.system.service.StructureService.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/structure")
public class StructureController extends BaseController {

    @Autowired
    private StructureService structureService;

    @ResponseBody
    @PostMapping("/getAllProduct")
    public R<List<AssemblyProduct>> getAllProduct(){
        try {
            List<AssemblyProduct> productList = structureService.getAllAssemblyProduct();
            System.out.println(productList);
            return R.success(productList);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createProduct")
    public R<List<AssemblyProduct>> createProduct(@RequestBody AssemblyProduct product){
        try {
            AssemblyProduct assemblyproduct = structureService.createProduct(product);
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
            structureService.createComponent(component);
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
            structureService.createPart(part);
            System.out.println(part);
            return R.success(part);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createConstraint")
    public R<List<AssemblyConstraint>> createConstraint(@RequestBody AssemblyConstraint constraint ){
        try {
            structureService.createConstraint(constraint);
            System.out.println(constraint);
            return R.success(constraint);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPartsWithConstraints")
    public R<List<AssemblyConstraint>> createPartsWithConstraints(@RequestBody List<PartsWithConstraints> constraints ){
        try {
            Set<AssemblyStructure> parts = new HashSet<>();
            constraints.forEach(c ->{
                c.getParts().forEach(p -> {
                    parts.add(p);
                });
            });
            parts.forEach(p -> {
                structureService.createPart((AssemblyPart) p);
            });
            Set<AssemblyConstraint> newConstraints = new HashSet<>();
            constraints.forEach(c ->{
                AssemblyConstraint constraint = c.getConstraint();
                c.getParts().forEach(p -> {
                    parts.forEach(p1 -> {
                        AssemblyPart p2 = (AssemblyPart) p1;
                        if(p2.getPartName().equals(p.getPartName())){
                            Set<AssemblyStructure> newParts;
                            if(constraint.getStructures() == null || constraint.getStructures().isEmpty()){
                                newParts = new HashSet<>();
                            } else {
                                newParts = constraint.getStructures();
                            }
                            newParts.add(p1);
                            constraint.setStructures(newParts);
                        }
                    });
                });
                newConstraints.add(structureService.createConstraint(constraint));
            });
            newConstraints.forEach(n ->{
                n.getStructures().forEach(s ->{
                    if(s.getConstraints() == null || s.getConstraints().isEmpty()){
                        Set<AssemblyConstraint> c1 = new HashSet<>();
                        c1.add(n);
                        s.setConstraints(c1);
                    }else {
                        s.getConstraints().add(n);
                    }
                    structureService.updateStructure(s);
                });
            });
            return R.success(newConstraints);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };


}
