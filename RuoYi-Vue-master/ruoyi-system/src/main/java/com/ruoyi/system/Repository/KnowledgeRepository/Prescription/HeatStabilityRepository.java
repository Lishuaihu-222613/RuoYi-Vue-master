package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface HeatStabilityRepository extends Neo4jRepository<HeatStability,Long> {

    @Override
    Optional<HeatStability> findById(Long stabilityId);

    @Override
    List<HeatStability> findAll();

    @Override
    <S extends HeatStability> S save(S stability);

    @Override
    void delete(HeatStability stability);

    @Override
    void deleteById(Long stabilityId);

    @Query("MATCH (n:推进剂配方)-[r:hasHeatStability]->(m:热敏感性) where id(n) = $prescriptionId return m")
    Optional<HeatStability> findHeatStabilityByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:热敏感性) where n.label = $stabilityName return n")
    Collection<HeatStability> findHeatStabilityByName(@Param("stabilityName")String stabilityName);
}
