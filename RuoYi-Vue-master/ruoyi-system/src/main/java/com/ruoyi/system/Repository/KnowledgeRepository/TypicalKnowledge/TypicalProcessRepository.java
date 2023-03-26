package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Interface.Process;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TypicalProcessRepository extends Neo4jRepository<TypicalProcess,Long> {

    @Override
    Optional<TypicalProcess> findById(Long processId);

    @Query("MATCH (n:TypicalProcess) where n.label = $processName return n")
    Collection<TypicalProcess> findByProcessName(String processName);

    @Override
    <S extends TypicalProcess> List<S> findAll(Example<S> example);

    @Override
    <S extends TypicalProcess> S save(S process);

    @Override
    void delete(TypicalProcess process);

    @Override
    void deleteById(Long processId);

    @Query("MATCH (n:TypicalProcess)<-[r:hasAssociatedProcess]-(m:Method) where id(m) = $methodId return n")
    Collection<Process> findProcessByMethodId(@Param("methodId") Long methodId);
}
