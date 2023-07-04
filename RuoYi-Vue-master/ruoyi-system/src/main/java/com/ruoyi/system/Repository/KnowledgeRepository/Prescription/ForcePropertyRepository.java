package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.ForceProperty;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ForcePropertyRepository extends Neo4jRepository<ForceProperty,Long> {

    @Override
    Optional<ForceProperty> findById(Long propertyId);

    @Override
    List<ForceProperty> findAll();

    @Override
    <S extends ForceProperty> S save(S property);

    @Query("Match (n:推进剂配方)-[r:hasForceProperty]->(m:力学性能) where id(n) = $prescriptionId return m")
    Optional<ForceProperty> findForcePropertyByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

    @Query("Match (n:力学性能) where n.label = $propertyName")
    Collection<ForceProperty> findForcePropertyByName(@Param("propertyName") String propertyName);

    @Override
    void deleteById(Long propertyId);

    @Override
    void delete(ForceProperty property);
}
