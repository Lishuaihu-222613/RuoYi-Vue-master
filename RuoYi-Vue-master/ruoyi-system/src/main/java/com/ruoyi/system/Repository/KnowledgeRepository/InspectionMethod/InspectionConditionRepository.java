package com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionCondition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionConditionRepository extends Neo4jRepository<InspectionCondition,Long> {

    @Override
    <S extends InspectionCondition> S save(S condition);

    @Override
    Optional<InspectionCondition> findById(Long conditionId);

    @Query("Match (n:InspectionCondition)<-[r:hasCondition]-(m:InspectionMethod) where id(m) = $methodId return n")
    List<InspectionCondition> findConditionByMethodId(@Param("methodId") Long methodId);

    @Override
    void deleteById(Long conditionId);
}
