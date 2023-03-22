package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface PrescriptionRepository extends Neo4jRepository<Prescription,Long> {

    @Override
    Optional<Prescription> findById(Long prescriptionId);

    @Query("MATCH (n:Prescription) WHERE id(n) = $prescriptionId return n")
    Optional<PrescriptionInterface> findPrescriptionInterfaceById(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:Prescription) WHERE n.label = $prescriptionName return n")
    Collection<PrescriptionInterface> findPrescriptionInterfaceByName(@Param("prescriptionName") String prescriptionName);

    @Query("MATCH (n:Prescription) where n:Prescription and n:$prescriptionType return n")
    Collection<PrescriptionInterface> findPrescriptionByType(@Param("prescriptionType") String prescriptionType);

    @Override

    <S extends Prescription> S save(S prescription);

    @Override
    void delete(Prescription prescription);

    @Override
    void deleteById(Long prescriptionId);
}
