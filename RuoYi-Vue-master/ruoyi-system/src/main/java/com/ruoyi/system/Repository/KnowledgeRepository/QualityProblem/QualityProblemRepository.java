package com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QualityProblemRepository extends Neo4jRepository<QualityProblem,Long> {

    @Override
    Page<QualityProblem> findAll(Pageable pageable);

    @Override
    Optional<QualityProblem> findById(Long problemId);

    @Query(value = "Match (n:QualityProblem) where n.label contains $problemName return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:QualityProblem) where n.label contains $problemName return count(n)"
    )
    List<QualityProblem> findByProblemName(@Param("problemName") String problemName);

    @Override
    <S extends QualityProblem> S save(S qualityProblem);

    @Override
    void deleteById(Long problemId);

    @Override
    <S extends QualityProblem> Page<S> findAll(Example<S> example, Pageable pageable);

    @Query(value = "MATCH (n:QualityProblem :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:QualityProblem :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<QualityProblem> findProblemsByLabel(@Param("dynamicLabel") String dynamicLabel , Pageable pageable);

    @Query(value = "MATCH (n:QualityProblem :`:#{literal(#dynamicLabel)}`) return n ")
    List<QualityProblem> getQualityProblemOptionsByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query(value = "MATCH (n:QualityProblem :`:#{literal(#dynamicLabel)}`) return n " )
    List<QualityProblem> findProblemsByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query("Match (n:QualityProblem)<-[]-(m) where id(m) = $associatedId return n")
    List<QualityProblem> findByAssociatedId(@Param("associatedId") Long associatedId);

    @Query("Match (n:QualityProblem) where id(n) = $problemId " +
            "Match (m) where id(m) = $associatedId " +
            "Merge (n)<-[r:hasAssociatedProblem]-(m)")
    void createRelationById(@Param("problemId") Long problemId,@Param("associatedId") Long associatedId);

    @Query("Merge (n:QualityProblem)<-[r:hasAssociatedProblem]-(m) where id(n) = $problemId and id(m) = $associatedId delete r")
    void deleteRelationById(@Param("problemId") Long problemId,@Param("associatedId") Long associatedId);
}
