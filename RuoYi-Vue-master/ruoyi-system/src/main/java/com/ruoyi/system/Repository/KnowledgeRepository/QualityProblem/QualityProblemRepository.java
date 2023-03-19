package com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QualityProblemRepository extends Neo4jRepository<QualityProblem,Long> {

    @Override
    List<QualityProblem> findAll();

    @Override
    Optional<QualityProblem> findById(Long problemId);

    @Query("Match (n:QualityProblem) where n.label contains $problemName return n")
    Optional<QualityProblem> findByProblemName(@Param("problemName") String problemName);

    @Override
    <S extends QualityProblem> S save(S qualityProblem);

    @Override
    void deleteById(Long problemId);

}
