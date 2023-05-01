package com.ruoyi.system.Repository.KnowledgeRepository.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface

PrescriptionRepository extends Neo4jRepository<Prescription,Long> {

    @Override
    Optional<Prescription> findById(Long prescriptionId);

    @Query("MATCH (n:Prescription) WHERE id(n) = $prescriptionId return n")
    Optional<PrescriptionInterface> findPrescriptionInterfaceById(@Param("prescriptionId") Long prescriptionId);

    @Query("MATCH (n:Prescription) WHERE n.label contains $prescriptionName return n")
    Collection<PrescriptionInterface> findPrescriptionInterfaceByName(@Param("prescriptionName") String prescriptionName);

    @Query(value = "MATCH (n) where any(label in labels(n) WHERE label in ['Prescription', $dynamicLabel])  return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:Prescription :$prescriptionType) return count(n)"
    )
    Page<Prescription> findPrescriptionByType(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);

    @Override
    <S extends Prescription> S save(S prescription);

    @Override
    void delete(Prescription prescription);

    @Override
    void deleteById(Long prescriptionId);

    @Override
    <S extends Prescription> Page<S> findAll(Example<S> example, Pageable pageable);
    @Query("Match (n:Prescription)-[r]->(m:Material) where id(n) = $prescriptionId delete r")
    void deleteRelationForMaterial(@Param("prescriptionId") Long prescriptionId);

    @Query("CREATE (n:Prescription)-[r:hasMaterialElement{percentage: $percentage}]->(m:Material) where id(n) = $prescriptionId and id(m) = $materialId")
    void createRelationForMaterial(@Param("prescriptionId") Long prescriptionId,@Param("$materialId") Long $materialId,@Param("percentage") double percentage);

    @Query("Match (n:Prescription)-[r:hasMaterialElement]->(m:Material) where id(n) = $prescriptionId return n,collect(r),collect(m)")
    Prescription getMaterialElementsById(@Param("prescriptionId") Long prescriptionId);
}
