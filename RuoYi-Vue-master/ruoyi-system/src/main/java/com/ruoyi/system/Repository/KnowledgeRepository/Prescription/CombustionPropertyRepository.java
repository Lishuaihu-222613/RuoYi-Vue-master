package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.CombustionProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CombustionPropertyRepository extends Neo4jRepository<CombustionProperty,Long> {

    @Override
    Optional<CombustionProperty> findById(Long propertyId);

    @Override
    List<CombustionProperty> findAll();

    @Override
    <S extends CombustionProperty> S save(S property);

    @Override
    void delete(CombustionProperty Property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:推进剂配方)-[r:hasCombustionProperty]->(m:燃烧性能) where id(n) = $prescriptionId return m")
    Optional<CombustionProperty> findCombustionPropertyByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:燃烧性能) where n.label = $propertyName return n")
    Collection<CombustionProperty> findCombustionPropertyByName(@Param("propertyName")String propertyName);
}
