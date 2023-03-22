package com.ruoyi.system.Repository.ProcessRepository;

import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StepRepository extends Neo4jRepository<Step,Long> {

    @Override
    Optional<Step> findById(Long stepId);

    @Override
    List<Step> findAll();

    @Query("Match (n:Step) <-[r]- (m:Sequence) where id(m) = $sequenceId order by n.stepNumber return n")
    Collection<Step> findStepBySequenceId(@Param("sequenceId")Long sequenceId);

    @Query("Match (n:Step) where n.label contains $stepName return n")
    Collection<Step> findStepByName(@Param("stepName") String stepName);

    @Override
    <S extends Step> S save(S step);

    @Override
    void delete(Step step);

    void deleteById(Long stepId);
}
