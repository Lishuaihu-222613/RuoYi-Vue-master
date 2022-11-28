package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.EnergyProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EnergyPropertyRepository extends Neo4jRepository<EnergyProperty,Long> {

    @Override
    Optional<EnergyProperty> findById(Long propertyId);

    @Override
    List<EnergyProperty> findAll();

    @Override
    <S extends EnergyProperty> S save(S property);

    @Override
    void delete(EnergyProperty Property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:能量性能)-[r:hasCombustionProperty]->(m:燃烧性能) where n.id = :prescriptionId return m")
    Optional<EnergyProperty> findEnergyPropertyByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:能量性能) where n.name = :propertyName return n")
    Collection<EnergyProperty> findEnergyPropertyByName(String propertyName);
}
