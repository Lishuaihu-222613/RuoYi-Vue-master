package com.ruoyi.system.service.dKService.impl;

import com.ruoyi.system.Repository.dKRepository.*;
import com.ruoyi.system.domain.KgBuilderPojo.model.DKSubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.resultItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import com.ruoyi.system.service.dKService.dKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class dKServiceImpl implements dKService {

    @Autowired
    private conditionItemDao conditionDao;
    @Autowired
    private com.ruoyi.system.Repository.dKRepository.dKDao dKDao;
    @Autowired
    private resultItemDao resultDao;
    @Autowired
    private ruleADao RADao;
    @Autowired
    private ruleCDao RCDao;


    @Override
    public List<conditionItem> getConditionItems(Long DkId) {
        return conditionDao.findByDId(DkId);
    }

    @Override
    public List<resultItem> getResults(Long DkId) {
        return resultDao.findByDId(DkId);
    }

    @Override
    public List<decisionKnowledge> getDecisionKnowledge() {
        return dKDao.findAll();
    }

    @Override
    public decisionKnowledge getDecisionKnowledgeById(Long DkId) {
        return dKDao.findById(DkId).get();
    }

    @Override
    public decisionKnowledge createDecisionKnowledge(DKSubmitItem DI) {
        decisionKnowledge DKItem = DI.getDkItem();
        HashSet<ruleAntecedent> ruleAntecedents = new HashSet<>();
        HashSet<ruleConsequent> ruleConsequents = new HashSet<>();
        DI.getRuleAs().forEach(s -> {
            s.getRuleA().setCondition(s.getCondition());
            ruleAntecedents.add(s.getRuleA());
        });
        DI.getRuleCs().forEach(s -> {
            s.getRuleC().setResult(s.getResult());
            ruleConsequents.add(s.getRuleC());
        });
        DKItem.setRuleAntecedents(ruleAntecedents);
        DKItem.setRuleConsequents(ruleConsequents);
        return dKDao.save(DKItem);
    }

    @Override
    public conditionItem createCondition(conditionItem CI) {
        return conditionDao.save(CI);
    }

    @Override
    public resultItem createResult(resultItem RI) {
        return resultDao.save(RI);
    }

    @Override
    public decisionKnowledge createSingleDK(decisionKnowledge DK) {
        return dKDao.save(DK);
    }

    @Override
    public void deleteDecision(Long DKId) {
//        List<conditionItem> cs = conditionDao.findByDId(DKId);
//        conditionDao.deleteAll(cs);
//        List<resultItem> rs = resultDao.findByDId(DKId);
//        resultDao.deleteAll(rs);
        dKDao.deleteById(DKId);
    }

    @Override
    public void deleteCondition(Long CIId) {
        conditionDao.deleteById(CIId);
    }

    @Override
    public void deleteResult(Long RIId) {
        resultDao.deleteById(RIId);
    }

    @Override
    public decisionKnowledge updateDK(decisionKnowledge DK) {
        return dKDao.save(DK);
    }

    @Override
    public conditionItem updateCondition(conditionItem CI) {
        return conditionDao.save(CI);
    }

    @Override
    public resultItem updateResult(resultItem RI) {
        return resultDao.save(RI);
    }

    @Override
    public ruleConsequent createRuleC(ruleConsequent RuleC) {
        return RCDao.save(RuleC);
    }

    @Override
    public ruleAntecedent createRuleA(ruleAntecedent RuleA) {
        return RADao.save(RuleA);
    }

    @Override
    public void deleteRuleC(Long RCId) {
        RCDao.deleteById(RCId);
    }

    @Override
    public void deleteRuleA(Long RAId) {
        RADao.deleteById(RAId);
    }

}
