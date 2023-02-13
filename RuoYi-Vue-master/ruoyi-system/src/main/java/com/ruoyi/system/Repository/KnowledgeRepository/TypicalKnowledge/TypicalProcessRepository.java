package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Interface.Process;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface TypicalProcessRepository extends Neo4jRepository<TypicalProcess,Long> {

    @Override
    Optional<TypicalProcess> findById(Long processId);

    @Query("MATCH (n:TypicalProcess) where n.label = $processName return n")
    Collection<TypicalProcess> findByProcessName(String processName);

    TypicalProcess save(TypicalProcess process);

    @Override
    void delete(TypicalProcess process);

    @Override
    void deleteById(Long processId);

    @Query("MATCH (n:TypicalProcess)<-[r:hasAssociatedProcess]-(m:Method) where m.id = $methodId return n")
    Collection<Process> findProcessByMethodId(Long methodId);
}
