package com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReasonRepository extends Neo4jRepository<Reason,Long> {

    @Override
    <S extends Reason> S save(S reason);

    @Override
    Optional<Reason> findById(Long reasonsId);

    @Query("Match (n:Reason)<-[r]-(m:QualityProblem) where id(m) = $problemId return n")
    List<Reason> findReasonsByProblemId(@Param("$problemId") Long problemId);

    @Override
    void deleteById(Long reasonsId);
}
