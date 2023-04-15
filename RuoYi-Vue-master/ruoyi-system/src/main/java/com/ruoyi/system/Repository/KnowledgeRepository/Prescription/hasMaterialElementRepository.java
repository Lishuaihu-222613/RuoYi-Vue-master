package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.hasMaterialElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface hasMaterialElementRepository extends Neo4jRepository<hasMaterialElement,Long> {

    @Query("Match (n:推进剂配方)-[r:hasMaterialElement]->(m:Material) where id(n) = $prescriptionId return r,m.id")
    List<hasMaterialElementInterface> findMaterialElementByPrescriptionId(Long prescriptionId);

    @Query("Match (n:推进剂配方)-[r:hasMaterialElement]->(m:Material) where id(n) = $prescriptionId return r,m")
    List<hasMaterialElement> findElementByPrescriptionId(Long prescriptionId);
}
