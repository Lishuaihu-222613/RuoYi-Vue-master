package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExplosionStabilityRepository extends Neo4jRepository<ExplosionStability,Long> {

    @Override
    Optional<ExplosionStability> findById(Long stabilityId);

    @Override
    List<ExplosionStability> findAll();

    @Override
    <S extends ExplosionStability> S save(S stability);

    @Override
    void delete(ExplosionStability stability);

    @Override
    void deleteById(Long stabilityId);

    @Query("MATCH (n:推进剂配方)-[r:hasCombustionProperty]->(m:爆炸敏感性) where n.id = :prescriptionId return m")
    Optional<ExplosionStability> findExplosionStabilityByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:爆炸敏感性) where n.name = :stabilityName return n")
    Collection<ExplosionStability> findExplosionStabilityByName(@Param("stabilityName")String stabilityName);
}
