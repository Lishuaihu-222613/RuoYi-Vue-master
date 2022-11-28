package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MechanicalStabilityRepository extends Neo4jRepository<MechanicalStability,Long> {

    @Override
    Optional<MechanicalStability> findById(Long stabilityId);

    @Override
    List<MechanicalStability> findAll();

    @Override
    <S extends MechanicalStability> S save(S stability);

    @Override
    void delete(MechanicalStability stability);

    @Override
    void deleteById(Long stabilityId);

    @Query("MATCH (n:推进剂配方)-[r:hasMechanicalStability]->(m:机械敏感性) where n.id = :prescriptionId return m")
    Optional<MechanicalStability> findMechanicalStabilityByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:机械敏感性) where n.name = :stabilityName return n")
    Optional<MechanicalStability> findMechanicalStabilityByName(@Param("stabilityName")String stabilityName);
}
