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

    Danger save(Danger danger);

    @Override
    void delete(Danger danger);

    @Override
    void deleteById(Long dangerId);

    @Query("MATCH (n:Danger)<-[r:hasDanger]-(m:Material) where m.id = $materialId return n")
    Collection<Danger> findDangersByMaterialId(@Param("materialId") Long materialId);
}
