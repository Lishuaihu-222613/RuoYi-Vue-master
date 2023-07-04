package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.OxidantRatio;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OxidantRatioRepository extends Neo4jRepository<OxidantRatio,Long> {

    @Override
    Optional<OxidantRatio> findById(Long propertyId);

    @Override
    List<OxidantRatio> findAll();

    @Override
    <S extends OxidantRatio> S save(S Property);

    @Override
    void delete(OxidantRatio Property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:推进剂配方)-[r:hasOxidantRatio]->(m:氧化剂比例) where id(n) = $prescriptionId return m")
    Optional<OxidantRatio> findOxidantRatioByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:氧化剂比例) where n.label = $propertyName return n")
    Collection<OxidantRatio> findOxidantRatioByName(@Param("propertyName")String propertyName);
}
