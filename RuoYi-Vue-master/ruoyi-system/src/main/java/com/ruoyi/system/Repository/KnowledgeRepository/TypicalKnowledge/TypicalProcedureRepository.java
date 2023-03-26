package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalSequence;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface TypicalProcedureRepository extends Neo4jRepository<TypicalSequence,Long> {

    @Override
    Optional<TypicalSequence> findById(Long procedureId);

    @Query("MATCH (n:TypicalProcedure) where n.label = $procedureName return n")
    Collection<TypicalSequence> findByProcedureName(String procedureName);

    TypicalSequence save(TypicalSequence procedure);

    @Override
    void delete(TypicalSequence procedure);

    @Override
    void deleteById(Long procedureId);

    @Query("MATCH (n:TypicalProcedure)<-[r:hasSubProcedure]-(m:TypicalProcess) where m.id = $processId return n")
    Collection<TypicalSequence> findTypicalProceduresByProcessId(Long processId);


}
