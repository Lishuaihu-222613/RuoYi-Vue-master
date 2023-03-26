package com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionMethodRepository extends Neo4jRepository<InspectionMethod,Long> {

    @Override
    <S extends InspectionMethod> S save(S method);

    @Override
    Optional<InspectionMethod> findById(Long methodId);

    @Override
    Page<InspectionMethod> findAll(Pageable pageable);

    @Query("MATCH (n:InspectionMethod :$dynamicLabel) return n")
    List<InspectionMethod> findMethodByType(@Param("dynamicLabel") String dynamicLabel);

    @Query("MATCH (n:InspectionMethod ) where n.label contains $methodName  return n")
    List<InspectionMethod> findMethodByName(@Param("methodName") String methodName);

    @Override
    void deleteById(Long methodId);
}
