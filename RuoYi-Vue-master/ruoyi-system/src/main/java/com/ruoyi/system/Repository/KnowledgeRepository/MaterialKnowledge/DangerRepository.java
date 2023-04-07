package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Danger;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface DangerRepository extends Neo4jRepository<Danger,Long> {

    @Override
    Optional<Danger> findById(Long dangerId);

    @Query("MATCH (n:Danger) where n.label = $dangerName return n")
    Collection<Danger> findByDangerName(String dangerName);

    @Override
    <S extends Danger> S save(S danger);

    @Override
    void delete(Danger danger);

    @Override
    void deleteById(Long dangerId);

    @Query("MATCH (n:危险事项)<-[r:hasDanger]-(m:材料) where id(m) = $materialId return n")
    Collection<Danger> findDangersByMaterialId(@Param("materialId") Long materialId);

    @Query("MATCH (n:危险事项) where id(n) = $dangerId "
            + "MATCH (m:材料) where id(m) = $materialId "
            + "MERGE (m)-[r:hasDanger]->(n)")
    void createRelationshipForDanger(@Param("materialId") Long materialId, @Param("dangerId") Long dangerId);
}
