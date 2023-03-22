package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.StorageProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StoragePropertyRepository extends Neo4jRepository<StorageProperty,Long> {

    @Override
    Optional<StorageProperty> findById(Long propertyId);

    @Override
    List<StorageProperty> findAll();

    @Override
    <S extends StorageProperty> S save(S property);

    @Override
    void delete(StorageProperty Property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:推进剂配方)-[r:hasCombustionProperty]->(m:贮存性能) where id(n) = $prescriptionId return m")
    Optional<StorageProperty> findStoragePropertyByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:贮存性能) where n.label = $propertyName return n")
    Collection<StorageProperty> findStoragePropertyByName(@Param("propertyName")String propertyName);
}
