package com.ruoyi.system.Repository.KnowledgeRepository.InspectionMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionFactor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionFactorRepository extends Neo4jRepository<InspectionFactor,Long> {

    @Override
    <S extends InspectionFactor> S save(S factor);

    @Override
    Optional<InspectionFactor> findById(Long factorId);

    @Override
    void deleteById(Long factorId);

    @Query("Match (n:InspectionFactor)<-[r:hasFactor]-(m:InspectionMethod) where id(m) = $methodId return n  ")
    List<InspectionFactor> findFactorByMethodId(@Param("methodId") Long methodId);
}
