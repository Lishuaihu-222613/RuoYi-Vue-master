package com.ruoyi.system.domain.KgBuilderPojo.model;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.resultItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import lombok.Data;

@Data
public class ruleCSubmitItem {

    private resultItem result;
    private ruleConsequent ruleC;

    public ruleCSubmitItem() {
    }

    public ruleCSubmitItem(resultItem result, ruleConsequent ruleC) {
        this.result = result;
        this.ruleC = ruleC;
    }
}
