package com.ruoyi.system.domain.KgBuilderPojo.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("result")
public class resultItem {

    //节点Id
    @Id
    @GeneratedValue
    private Long id;
    //节点类型
    @Property(name = "cluster")
    private String cluster;
    //节点内容
    @Property(name = "content")
    private String content;

    @JsonIgnore
    @Relationship(type = "after", direction = Relationship.Direction.INCOMING)
    private ruleConsequent ruleConsequents;

}
