package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ImpactStability;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImpactStabilityRepository extends Neo4jRepository<ImpactStability,Long> {

    @Override
    Optional<ImpactStability> findById(Long stabilityId);

    @Override
    List<ImpactStability> findAll();

    @Override
    <S extends ImpactStability> S save(S stability);

    @Override
    void delete(ImpactStability stability);

    @Override
    void deleteById(Long stabilityId);

    @Query("MATCH (n:推进剂配方)-[r:hasCombustionProperty]->(m:撞击敏感性) where n.id = :prescriptionId return m")
    Optional<ImpactStability> findImpactStabilityByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:撞击敏感性) where n.name = :stabilityName return n")
    Optional<ImpactStability> findImpactStabilityByName(@Param("stabilityName")String stabilityName);
}
