package com.ruoyi.system.Repository.ProcessRepository;

import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ProcessInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProcessRepository extends Neo4jRepository<Process,Long> {

    List<Process> findAll();

    Optional<Process> findById(Long processId);

    @Query("Match (n:Process) where id(n) = $processId return n")
    Optional<ProcessInterface> findInterfaceById(@Param("processId") Long processId);

    @Query("Match (n:Process) where n.label contains $processName return n")
    Collection<ProcessInterface> findProcessByProcessName(@Param("processName") String processName);

    @Query("Match (n:Process) return n")
    List<ProcessInterface> findAllProcessInterfaces();

    @Override
    <S extends Process> S save(S process);

    void deleteById(Long processId);

    void delete(Process process);
}
