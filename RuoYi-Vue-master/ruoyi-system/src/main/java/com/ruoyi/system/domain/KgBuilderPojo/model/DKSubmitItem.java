package com.ruoyi.system.domain.KgBuilderPojo.model;

import org.springframework.cglib.core.CollectionUtils;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import lombok.Data;

import java.util.Set;

@Data
public class DKSubmitItem {

    private decisionKnowledge dkItem;
    private Set<ruleASubmitItem> ruleAs;
    private Set<ruleCSubmitItem> ruleCs;

    public DKSubmitItem() {
    }

    public DKSubmitItem(decisionKnowledge dkItem, Set<ruleASubmitItem> ruleAs, Set<ruleCSubmitItem> ruleCs) {
        this.dkItem = dkItem;
        this.ruleAs = ruleAs;
        this.ruleCs = ruleCs;
    }
}
