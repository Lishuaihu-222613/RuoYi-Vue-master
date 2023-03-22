package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.PrescriptionProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface PropertyRepository extends Neo4jRepository<PrescriptionProperty,Long> {

}
