package com.ruoyi.system.Repository.dKRepository;

import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleConsequent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;


public interface ruleCDao extends Neo4jRepository<ruleConsequent,Long> {

    Optional<ruleConsequent> findById(Long RCId);

    ruleConsequent save(ruleConsequent RC);

    void deleteById(Long RCId);

    @Query("MATCH (n:decisionKnowledge)-[r:ruleAfter]->(m:resultItem) where r.cluster contains $cluster return r")
    List<ruleConsequent> findByCluster(String cluster);

    @Query("MATCH (n:decisionKnowledge)-[r:ruleAfter]->(m:resultItem) where m.id = $RId return r")
    List<ruleConsequent> findByRId(String RId);

    @Query("MATCH (n:decisionKnowledge)-[r:ruleAfter]->(m:resultItem) where n.id = $DId return r")
    List<ruleConsequent> findByDId(String DId);
}
