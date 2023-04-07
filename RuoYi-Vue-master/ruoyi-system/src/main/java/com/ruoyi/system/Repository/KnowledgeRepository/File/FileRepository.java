package com.ruoyi.system.Repository.KnowledgeRepository.File;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends Neo4jRepository<FileKnowledge,Long> {

    @Override
    <S extends FileKnowledge> S save(S file);

    @Override
    Page<FileKnowledge> findAll(Pageable pageable);

    @Query(value = "Match (n) where any(label in labels(n) WHERE label in ['FileKnowledge', $dynamicLabel])  return n" +
            "ORDER BY n.Id ASC SKIP $skip LIMIT $limit",
            countQuery = "(n) where any(label in labels(n) WHERE label in ['FileKnowledge', $dynamicLabel])  return count(n)"
    )
    Page<FileKnowledge> findByFileClassification(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);

    List<FileKnowledge> findByFileName(String fileName);

    @Override
    Optional<FileKnowledge> findById(Long fileId);

    @Override
    void deleteById(Long fileId);

    @Override
    <S extends FileKnowledge> Page<S> findAll(Example<S> example, Pageable pageable);
}
