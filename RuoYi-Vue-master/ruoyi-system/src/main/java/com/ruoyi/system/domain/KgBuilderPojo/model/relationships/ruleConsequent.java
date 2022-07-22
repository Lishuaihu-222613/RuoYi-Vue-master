package com.ruoyi.system.domain.KgBuilderPojo.model.relationships;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.resultItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class ruleConsequent {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private String cluster;

    @TargetNode
    private resultItem result;

    @Override
    public String toString() {
        return "ruleConsequent{" +
                "Id=" + id +
                ", content='" + content + '\'' +
                ", cluster='" + cluster + '\'' +
                '}';
    }
}
