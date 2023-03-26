package com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionModeRepository extends Neo4jRepository<InspectionMode,Long> {

    @Override
    <S extends InspectionMode> S save(S mode);

    @Override
    Optional<InspectionMode> findById(Long modeId);

    @Override
    void deleteById(Long modeId);

    @Query("Match (n:InspectionMode)<-[r:hasMode]-(m:InspectionMethod) where id(n) = $methodId return n ")
    List<InspectionMode> findModeByMethodId(@Param("methodId") Long methodId);
}
