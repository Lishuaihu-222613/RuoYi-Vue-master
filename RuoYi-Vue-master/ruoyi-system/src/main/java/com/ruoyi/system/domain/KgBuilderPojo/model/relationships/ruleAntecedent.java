package com.ruoyi.system.domain.KgBuilderPojo.model.relationships;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class ruleAntecedent {

    //关系Id
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private String cluster;

    @TargetNode
    private conditionItem condition;

    @Override
    public String toString() {
        return "ruleAntecedent{" +
                "Id=" + id +
                ", content='" + content + '\'' +
                ", cluster='" + cluster + '\'' +
                '}';
    }
}
