package com.ruoyi.system.Repository.ProcessRepository;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SequenceRepository extends Neo4jRepository<Sequence,Long> {

    List<Sequence> findAll();

    @Override
    Optional<Sequence> findById(Long aLong);

    @Query("Match (n:Sequence)<-[r]-(m:Process) where id(m) = $processId return n order by n.工序号")
    Collection<Sequence> findSequenceByProcessId(@Param("processId") Long processId);

    @Query("Match (n:Sequence) where n.label contains $processName return n" )
    Collection<Sequence> findSequenceByName(@Param("processName") String processName);

    @Override
    <S extends Sequence> List<S> findAll(Example<S> example);

    @Override
    <S extends Sequence> S save(S sequence);

    @Override
    void delete(Sequence sequence);

    void deleteById(Long sequenceId);

    @Override
    <S extends Sequence> Page<S> findAll(Example<S> example, Pageable pageable);

    @Query(value = "Match (n:Sequence)<-[r]-(m:Process) where id(m) = $processId return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:Sequence)<-[r]-(m:Process) where id(m) = $processId return count(n)"
    )
    Page<Sequence> findSequenceByProcessId(@Param("processId") Long processId,Pageable pageable);
}
