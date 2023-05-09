package com.ruoyi.system.Repository.ProcessRepository;


import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessElementRepository extends Neo4jRepository<ProcessElement, Long> {

    @Override
    <S extends ProcessElement> S save(S element);

    @Override
    <S extends ProcessElement> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    List<ProcessElement> findAll();

    @Override
    void deleteAllById(Iterable<? extends Long> elementIds);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends ProcessElement> List<S> findAll(Example<S> example, Sort sort);

    @Query("Match (n:ProcessElement :Process)  return n")
    List<ProcessElement> findAllProcess();

    @Query("Match (n:ProcessElement)-[r:hasAssociatedStructure]->(m) where id(m) = $structureId return n ")
    List<ProcessElement> findProcessByStructure(@Param("structureId") Long structureId);

    @Query("Match (n:ProcessElement)<-[r:hasAssociatedProcess]-(m) where id(m) = $prescriptionId return n ")
    List<ProcessElement> findProcessByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("Match (n:ProcessElement :TypicalProcess :`:#{literal(#dynamicLabel)}`) return n")
    List<ProcessElement> findTypicalProcessByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query("Match (n:ProcessElement :TypicalSequence :`:#{literal(#dynamicLabel)}`) return n")
    List<ProcessElement> findTypicalSequenceByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query("Match (n:ProcessElement :TypicalStep :`:#{literal(#dynamicLabel)}`) return n")
    List<ProcessElement> findTypicalStepByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query(value = "MATCH (n:ProcessElement :Sequence)<-[r:hasSubElement]-(m::ProcessElement :Process) WHERE id(m) = $processId RETURN n "
            + ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:ProcessElement :Sequence)<-[r:hasSubElement]-(m::ProcessElement :Process) WHERE id(m) = $processId RETURN count(n)"
    )
    Page<ProcessElement> findSequencesByProcessId(Long processId, Pageable pageable);

    @Query("MATCH (n:ProcessElement)<-[r:hasSubElement] WHERE n.name = $name RETURN n order by n.序号")
    List<ProcessElement> findStepsBySequenceId(Long sequenceId);

    @Query(value = "MATCH (n:ProcessElement :Process :`:#{literal(#dynamicLabel)}`) return n ")
    List<ProcessElement> findProcessByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query(value = "MATCH (n:ProcessElement :`:#{allOf(#dynamicLabels)}`) return n ")
    List<ProcessElement> findElementsByLabels(List<String> dynamicLabels);

    @Override
    Optional<ProcessElement> findById(Long elementId);

    @Query("Match (n:ProcessElement) where id(n) = $elementId return n")
    ProcessElement findSingleElementById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasSubElement]->(m:ProcessElement) where id(m) = $elementId return n")
    ProcessElement findParentElement( @Param("elementId")  Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasBeforeElement]->(m:ProcessElement) where id(n) = $elementId return m")
    List<ProcessElement> findBeforeElementsById( @Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasAfterElement]->(m:ProcessElement) where id(n) = $elementId return m")
    List<ProcessElement> findAfterElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasAndElement]->(m:ProcessElement) where id(n) = $elementId return m")
    List<ProcessElement> findAndElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasOrElement]->(m:ProcessElement) where id(n) = $elementId return m")
    List<ProcessElement> findOrElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasSubElement]->(m:ProcessElement) where id(n) = $elementId return m order by m.序号")
    List<ProcessElement> findSubElementsById(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasSubElement]->(m:ProcessElement) where id(n) = $elementId delete r ")
    void deleteSubRelation(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement) where id(n) = $parentId " +
            "MATCH (m:ProcessElement) where id(m) = $elementId " +
            "MERGE (n)-[r:hasSubElement]->(m)")
    void createSubRelation(@Param("parentId") Long parentId, @Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasBeforeElement]->(m:ProcessElement) where id(n) = $elementId delete r ")
    void deleteBeforeRelation(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement) where id(n) = $beforeId " +
            "MATCH (m:ProcessElement) where id(m) = $elementId " +
            "MERGE (n)<-[r:hasBeforeElement]-(m)" )
    void createBeforeRelation(@Param("beforeId") Long beforeId, @Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasAfterElement]->(m:ProcessElement) where id(n) = $elementId delete r ")
    void deleteAfterRelation(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement) where id(n) = $afterId " +
            "MATCH (m:ProcessElement) where id(m) = $elementId " +
            "MERGE (n)<-[r:hasAfterElement]-(m)")
    void createAfterRelation(@Param("afterId") Long afterId, @Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasAndElement]->(m:ProcessElement) where id(n) = $elementId delete r ")
    void deleteAndRelation(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement) where id(n) = $andId " +
            "MATCH (m:ProcessElement) where id(m) = $elementId " +
            "MERGE (n)<-[r:hasAndElement]-(m)")
    void createAndRelation(@Param("andId") Long andId, @Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasOrElement]->(m:ProcessElement) where id(n) = $elementId delete r ")
    void deleteOrRelation(@Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement) where id(n) = $orId " +
            "MATCH (m:ProcessElement) where id(m) = $elementId " +
            "MERGE (n)<-[r:hasOrElement]-(m)")
    void createOrRelation(@Param("orId") Long orId, @Param("elementId") Long elementId);

    @Query(value = "MATCH (n:ProcessElement)-[r:hasAssociatedStructure|:use|:hasAssociatedFile]->(m) where id(n) = $elementId delete r ")
    void deleteOtherRelations(@Param("elementId") Long elementId);
}
