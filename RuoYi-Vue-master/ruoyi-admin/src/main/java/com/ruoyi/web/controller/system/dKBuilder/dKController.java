package com.ruoyi.web.controller.system.dKBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.KgBuilderPojo.model.DKSubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import com.ruoyi.system.domain.KgBuilderPojo.model.ruleASubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.ruleCSubmitItem;
import com.ruoyi.system.service.dKService.dKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dK")
public class dKController extends BaseController {

    @Autowired
    private dKService dKService;

    @ResponseBody
    @PostMapping("/getAllDecisionKnowledge")
    public R<List<decisionKnowledge>> getAllDecisionKnowledge(){
      try {
          List<decisionKnowledge> dkList = dKService.getDecisionKnowledge();
          System.out.println(dkList);
          return R.success(dkList);
      } catch (Exception e){
          e.printStackTrace();
          return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getDecisionKnowledge/{DKId}")
    public R<decisionKnowledge> getDecisionKnowledge(@PathVariable String DKId){
        try {
            decisionKnowledge dkList = dKService.getDecisionKnowledgeById(Long.valueOf(DKId));
            System.out.println(dkList);
            return R.success(dkList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createDK")
    public R<decisionKnowledge> createDecisionKnowledge(@RequestBody DKSubmitItem DI) {
        try {
            decisionKnowledge knowledge = dKService.createDecisionKnowledge(DI);
            return R.success(knowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateDK")
    public R<decisionKnowledge> updateDecisionKnowledge(@RequestBody decisionKnowledge DK) {
        try {
            decisionKnowledge knowledge = dKService.updateDK(DK);
            return R.success(knowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSingleDK")
    public R<decisionKnowledge> createSingleDK(@RequestBody decisionKnowledge DI) {
        try {
            System.out.println(DI);
            decisionKnowledge DKItem = dKService.createSingleDK(DI);
            System.out.println(DKItem);
            return R.success(DKItem);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createCondition")
    public R<conditionItem> createCondition(@RequestBody conditionItem CI) {
        try {
            System.out.println(CI);
            conditionItem CItem = dKService.createCondition(CI);
            System.out.println(CItem);
            return R.success(CItem);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getDKByDomain")
    public R<conditionItem> getDKByDomain(@RequestBody conditionItem CI) {
        try {
            System.out.println(CI);
            conditionItem CItem = dKService.createCondition(CI);
            System.out.println(CItem);
            return R.success(CItem);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    @ResponseBody
    @GetMapping("/deleteDKById/{DKId}")
    public R<decisionKnowledge> deleteDKById(@PathVariable Long DKId) {
        try {
            System.out.println(DKId);
            decisionKnowledge dkItem = dKService.getDecisionKnowledgeById(DKId);
            System.out.println(dkItem);
            dKService.deleteDecision(DKId);
            return R.success(dkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };




}
