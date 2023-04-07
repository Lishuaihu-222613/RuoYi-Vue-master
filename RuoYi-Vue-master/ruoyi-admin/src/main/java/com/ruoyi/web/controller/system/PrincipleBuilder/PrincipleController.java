package com.ruoyi.web.controller.system.PrincipleBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.vo.PrincipleQueryVo;
import com.ruoyi.system.service.KnowledgeService.BasicPrinciple.PrincipleService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/Principle")
public class PrincipleController extends BaseController {

    @Resource
    private PrincipleService principleService;

    @ResponseBody
    @PostMapping("/getPrinciplesByLabel")
    public R<Page<Principle>> getPrinciplesByLabel(@RequestBody PrincipleQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<Principle> principles = principleService.getAllPrincipleByLabel(params.getDynamicLabel(), pageable);
            System.out.println(principles);
            return R.success(principles);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllPrinciples")
    public R<Page<Principle>> getAllPrinciples(@RequestBody PrincipleQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<Principle> principles = principleService.getAllPrinciple(pageable);
            System.out.println(principles);
            return R.success(principles);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllPrinciplesByParams")
    public R<Page<Principle>> getAllPrinciplesByParams(@RequestBody PrincipleQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Example<Principle> example = Example.of(params.getOriginPrinciple());
            Page<Principle> principles = principleService.getAllPrincipleByParams(example,pageable);
            System.out.println(principles);
            return R.success(principles);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getPrinciplesByName/{principleName}")
    public R<List<Principle>> getPrinciplesByName(@PathVariable String principleName) {
        try {
            List<Principle> principles = principleService.getPrincipleByName(principleName);
            System.out.println(principles);
            return R.success(principles);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getPrincipleById/{principleId}")
    public R<Principle> getPrincipleById(@PathVariable Long principleId) {
        try {
            Principle principle = principleService.getPrincipleById(principleId);
            System.out.println(principle);
            return R.success(principle);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPrinciple")
    public R<Principle> createPrinciple(@RequestBody Principle principle) {
        try {
            Principle newPrinciple = principleService.createPrinciple(principle);
            System.out.println(newPrinciple);
            return R.success(newPrinciple);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updatePrinciple")
    public R<Principle> updatePrinciple(@RequestBody Principle principle) {
        try {
            Principle newPrinciple = principleService.updatePrinciple(principle);
            System.out.println(newPrinciple);
            return R.success(newPrinciple);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deletePrinciple/{principleId}")
    public R<Principle> deletePrinciple(@PathVariable Long principleId) {
        try {
            principleService.deletePrinciple(principleId);
            return R.success("删除" + principleId + "的设计原则");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };


}
