package com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SolutionRepository extends Neo4jRepository<Solution,Long> {

    @Override
    Optional<Solution> findById(Long solutionId);

    @Override
    <S extends Solution> S save(S solution);

    @Query("Match (n:Solution)<-[r]-(m:QualityProblem) where id(m) = $problemId return n")
    List<Solution> findByProblemId(@Param("$problemId") Long problemId);

    @Override
    void deleteById(Long aLong);
}
