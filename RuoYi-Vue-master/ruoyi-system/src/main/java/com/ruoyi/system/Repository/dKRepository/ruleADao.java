package com.ruoyi.system.Repository.dKRepository;

import com.ruoyi.system.domain.KgBuilderPojo.model.relationships.ruleAntecedent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;


public interface ruleADao extends Neo4jRepository<ruleAntecedent,Long> {

    Optional<ruleAntecedent> findById(Long RAId);

    ruleAntecedent save(ruleAntecedent RA);

    void deleteById(Long RAId);

    @Query("MATCH (n:condition)-[r:ruleBefore]->(m:decisionKnowledge) where r.cluster contains $cluster return r")
    List<ruleAntecedent> findByCluster(String cluster);

    @Query("MATCH (n:condition)-[r:ruleBefore]->(m:decisionKnowledge) where n.id = $CId return r")
    List<ruleAntecedent> findByCId(String CId);

    @Query("MATCH (n:condition)-[r:ruleBefore]->(m:decisionKnowledge) where m.id = $DId return r")
    List<ruleAntecedent> findByDId(String DId);

}
