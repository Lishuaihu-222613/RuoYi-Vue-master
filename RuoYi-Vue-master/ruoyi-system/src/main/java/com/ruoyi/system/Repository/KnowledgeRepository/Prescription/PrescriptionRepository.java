package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface PrescriptionRepository extends Neo4jRepository<Prescription,Long> {

    @Override
    Optional<Prescription> findById(Long prescriptionId);

    @Query("MATCH (n:推进剂配方) WHERE n.id = :prescriptionId return n")
    Optional<PrescriptionInterface> findPrescriptionInterfaceById(Long prescriptionId);

    @Query("MATCH (n:推进剂配方) WHERE n.label = :prescriptionName return n")
    Optional<PrescriptionInterface> findPrescriptionInterfaceByName(String prescriptionName);

    @Override
    <S extends Prescription> S save(S prescription);

    @Override
    void delete(Prescription prescription);

    @Override
    void deleteById(Long prescriptionId);
}
