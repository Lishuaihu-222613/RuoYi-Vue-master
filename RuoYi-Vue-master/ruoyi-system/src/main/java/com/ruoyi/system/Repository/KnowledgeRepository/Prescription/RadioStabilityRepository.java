package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RadioStabilityRepository extends Neo4jRepository<RadioStability,Long> {

    @Override
    Optional<RadioStability> findById(Long stabilityId);

    @Override
    List<RadioStability> findAll();

    @Override
    <S extends RadioStability> S save(S stability);

    @Override
    void delete(RadioStability stability);

    @Override
    void deleteById(Long stabilityId);

    @Query("MATCH (n:推进剂配方)-[r:hasCombustionProperty]->(m:辐射敏感性) where id(n) = $prescriptionId return m")
    Optional<RadioStability> findRadioStabilityByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:辐射敏感性) where n.label = $stabilityName return n")
    Collection<RadioStability> findRadioStabilityByName(@Param("stabilityName")String stabilityName);
}
