package com.ruoyi.system.Repository.KnowledgeRepository.DecisionRule;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.decisionKnowledge.DecisionRule;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DecisionRuleRepository extends Neo4jRepository<DecisionRule,Long> {

    @Override
    Optional<DecisionRule> findById(Long aLong);

    @Override
    <S extends DecisionRule> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    Page<DecisionRule> findAll(Pageable pageable);

    @Query(value = " Match (n:DecisionRule :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:DecisionRule :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<DecisionRule> findByRuleLabel(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);
}
