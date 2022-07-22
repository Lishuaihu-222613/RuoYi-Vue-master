package com.ruoyi.system.domain.KgBuilderPojo.model;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import lombok.Data;

@Data
public class ruleASubmitItem {

    private conditionItem condition;
    private ruleAntecedent ruleA;

    public ruleASubmitItem() {
    }

    public ruleASubmitItem(conditionItem conditionItem, ruleAntecedent ruleA) {
        this.condition = conditionItem;
        this.ruleA = ruleA;
    }
}
