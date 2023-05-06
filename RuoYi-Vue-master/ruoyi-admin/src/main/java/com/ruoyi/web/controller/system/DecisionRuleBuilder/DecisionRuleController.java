package com.ruoyi.web.controller.system.DecisionRuleBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.DecisionRule;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.vo.RuleQueryVo;
import com.ruoyi.system.service.KnowledgeService.DecisionRule.DecisionRuleService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/decisionRule")
public class DecisionRuleController extends BaseController {

    @Resource
    private DecisionRuleService decisionRuleService;

    @ResponseBody
    @PostMapping("/createDecisionRule")
    public R<DecisionRule> createRule(@RequestBody DecisionRule rule){
        try{
            DecisionRule newRule = decisionRuleService.createDecisionRule(rule);
            System.out.println(newRule);
            return R.success(newRule);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateDecisionRule")
    public R<DecisionRule> updateRule(@RequestBody DecisionRule rule){
        try{
            DecisionRule newRule = decisionRuleService.updateDecisionRule(rule);
            System.out.println(newRule);
            return R.success(newRule);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllRules")
    public R<Page<DecisionRule>> getAllRules(@RequestBody RuleQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<DecisionRule> rules = decisionRuleService.getAllDecisionRules(pageable);
            System.out.println(rules);
            return R.success(rules);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllRulesByLabel")
    public R<Page<DecisionRule>> getAllRulesByLabel(@RequestBody RuleQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<DecisionRule> rules = decisionRuleService.getDecisionRulesByLabel(params.getDynamicLabel(),pageable);
            System.out.println(rules);
            return R.success(rules);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllRulesByParams")
    public R<Page<DecisionRule>> getAllRulesByParams(@RequestBody RuleQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("ruleName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("ruleSource", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("ruleDescription",ExampleMatcher.GenericPropertyMatcher::contains);
            DecisionRule qualityRule = new DecisionRule();
            qualityRule.setRuleName(params.getOriginRule().getRuleName());
            qualityRule.setRuleDescription(params.getOriginRule().getRuleDescription());
            Example<DecisionRule> example = Example.of(qualityRule,matcher);
            Page<DecisionRule> rules = decisionRuleService.getDecisionRulesByParams(example,pageable);
            System.out.println(rules);
            return R.success(rules);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getRuleById/{ruleId}")
    public R<DecisionRule> getRuleById(@PathVariable Long ruleId){
        try{
            DecisionRule rule = decisionRuleService.getDecisionRuleById(ruleId);
            System.out.println(rule);
            return R.success(rule);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteDecisionRule/{ruleId}")
    public R<String> deleteRule(@PathVariable Long ruleId){
        try{
            decisionRuleService.deleteDecisionRule(ruleId);
            return R.success("删除"+ruleId+"的决策规则");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
