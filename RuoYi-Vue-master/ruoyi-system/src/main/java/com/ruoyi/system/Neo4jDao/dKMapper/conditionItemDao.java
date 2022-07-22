package com.ruoyi.system.Neo4jDao.dKMapper;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.conditionItem;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface conditionItemDao extends Neo4jRepository<conditionItem,Long> {

    @Query("MATCH (n:condition) WHERE n.cluster CONTAINS $cluster RETURN n")
    List<conditionItem> findByCluster(@Param("cluster") String cluster);

    Optional<conditionItem> findById(@Param("Id") Long CId);

    @Query("MATCH (n:decisionKnowledge)-[r:front]->(m:condition) where n.id = $DId RETURN m")
    List<conditionItem> findByDId(@Param("DId") Long DId);

    conditionItem save(conditionItem CI);

    void deleteById(Long CId);

}
