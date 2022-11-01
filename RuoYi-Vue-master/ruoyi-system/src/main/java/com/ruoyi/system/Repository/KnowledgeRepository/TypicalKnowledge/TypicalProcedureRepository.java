package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcedure;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface TypicalProcedureRepository extends Neo4jRepository<TypicalProcedure,Long> {

    @Override
    Optional<TypicalProcedure> findById(Long procedureId);

    @Query("MATCH (n:TypicalProcedure) where n.label = $procedureName return n")
    Collection<TypicalProcedure> findByProcedureName(String procedureName);

    TypicalProcedure save(TypicalProcedure procedure);

    @Override
    void delete(TypicalProcedure procedure);

    @Override
    void deleteById(Long procedureId);

    @Query("MATCH (n:TypicalProcedure)<-[r:hasSubProcedure]-(m:TypicalProcess) where m.id = $processId return n")
    Collection<TypicalProcedure> findTypicalProceduresByProcessId(Long processId);


}
