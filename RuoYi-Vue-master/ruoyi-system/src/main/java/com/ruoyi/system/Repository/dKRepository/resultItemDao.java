package com.ruoyi.system.Repository.dKRepository;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.resultItem;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface resultItemDao extends Neo4jRepository<resultItem,Long> {

    @Query("MATCH (n:result) WHERE n.cluster CONTAINS $cluster RETURN n")
    List<resultItem> findByCluster(@Param("cluster")String cluster);

    Optional<resultItem> findById(@Param("RId")Long RId);

    @Query("MATCH (n:decisionKnowledge)-[r:after]->(m:result) where n.id = $DId RETURN m")
    List<resultItem> findByDId(@Param("DId")Long DId);

    resultItem save(resultItem RI);

    void deleteById(Long RId);

}
