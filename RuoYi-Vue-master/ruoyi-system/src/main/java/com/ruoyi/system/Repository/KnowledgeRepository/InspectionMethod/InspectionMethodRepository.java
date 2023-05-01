package com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionMethodRepository extends Neo4jRepository<InspectionMethod, Long> {

    @Override
    <S extends InspectionMethod> S save(S method);

    @Override
    Optional<InspectionMethod> findById(Long methodId);

    @Override
    Page<InspectionMethod> findAll(Pageable pageable);

    @Query(value = "MATCH (n:InspectionMethod :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:InspectionMethod :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<InspectionMethod> findMethodByType(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);

    @Query("MATCH (n:InspectionMethod ) where n.label contains $methodName return n")
    List<InspectionMethod> findMethodByName(@Param("methodName") String methodName);

    @Override
    void deleteById(Long methodId);

    @Override
    <S extends InspectionMethod> Page<S> findAll(Example<S> example, Pageable pageable);
}
