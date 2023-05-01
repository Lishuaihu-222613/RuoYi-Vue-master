package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.hasMaterialElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface hasMaterialElementRepository extends Neo4jRepository<hasMaterialElement,Long> {

    @Query("Match (n:推进剂配方)-[r:hasMaterialElement]->(m:Material) where id(n) = $prescriptionId return r")
    List<hasMaterialElementInterface> findMaterialElementByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

    @Query("Match (n:推进剂配方)-[r:hasMaterialElement]->(m:Material) where id(n) = $prescriptionId return r")
    List<hasMaterialElement> findElementByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

    @Query("Match (n:推进剂配方)-[r:hasMaterialElement]->(m:Material) where id(r) = $prescriptionId return r,m")
    hasMaterialElement findRelationById(@Param("prescriptionId") Long prescriptionId);

    @Override
    Optional<hasMaterialElement> findById(Long aLong);
}
