package com.ruoyi.system.Neo4jDao.dKMapper;

import com.ruoyi.system.domain.KgBuilderPojo.model.nodes.decisionKnowledge;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface dKDao extends Neo4jRepository<decisionKnowledge,Long> {

    @Query("MATCH (n:decisionKnowledge) where n.cluster CONTAINS $cluster return n")
    List<decisionKnowledge> findByCluster(@Param("cluster") String cluster);

    @Override
    Optional<decisionKnowledge> findById(Long DId);

    List<decisionKnowledge> findAll();

    @Query("MATCH (n:decisionKnowledge) where n.name = $Dname return n")
    List<decisionKnowledge> findByName(@Param("Dname") String Dname);

    decisionKnowledge save(decisionKnowledge DI);

    void deleteById(Long DId);

}
