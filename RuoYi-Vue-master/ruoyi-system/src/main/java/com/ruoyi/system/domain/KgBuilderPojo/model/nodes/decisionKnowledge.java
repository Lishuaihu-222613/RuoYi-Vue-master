package com.ruoyi.system.domain.KgBuilderPojo.model.nodes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("decisionKnowledge")
public class decisionKnowledge {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "domain")
    private String domain;

    @Property(name = "dkName")
    private String dkName;

    @Property(name = "description")
    private String description;

    @Property(name = "source")
    private String dkSource;

    @Property(name = "modifyTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @Property(name = "manager")
    private String manager;

    @Relationship(type = "front", direction = Relationship.Direction.OUTGOING)
    private Set<ruleAntecedent> ruleAntecedents;

    @Relationship(type = "after", direction = Relationship.Direction.OUTGOING)
    private Set<ruleConsequent> ruleConsequents;

}
