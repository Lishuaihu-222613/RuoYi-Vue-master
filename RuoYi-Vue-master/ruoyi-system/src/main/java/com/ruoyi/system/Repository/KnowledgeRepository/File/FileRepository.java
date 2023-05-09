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

    @Query(value = " Match (n:FileKnowledge :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:FileKnowledge :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<FileKnowledge> findByFileClassification(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);

    @Query(value = " Match (n:FileKnowledge :`:#{literal(#dynamicLabel)}`) return n ")
    List<FileKnowledge> getFileOptionsByLabel(@Param("dynamicLabel") String dynamicLabel);

    List<FileKnowledge> findByFileName(String fileName);

    @Override
    Optional<FileKnowledge> findById(Long fileId);

    @Override
    void deleteById(Long fileId);

    @Override
    <S extends FileKnowledge> Page<S> findAll(Example<S> example, Pageable pageable);

    @Query("Match (n:FileKnowledge)<-[r:hasAssociatedFile]-(m) where id(m) = $associatedId return n ")
    List<FileKnowledge> findByAssociatedId(@Param("associatedId") Long associatedId);

    @Query("Match (n:FileKnowledge :三维模型文件) return n ")
    List<FileKnowledge> getAllModelFile();

    @Query("Match (n:FileKnowledge)<-[r:hasModelFile]-(m) where id(m) = $associatedId return n ")
    FileKnowledge findByStructureId(@Param("associatedId") Long associatedId);

    @Query("Match (n:FileKnowledge) where id(n) = $fileId " +
            "Match (m) where id(m) = $associatedId " +
            "MERGE (n)<-[r:hasAssociatedFile]-(m)")
    void createdAssociatedRelationById(@Param("fileId") Long fileId, @Param("associatedId") Long associatedId);

    @Query("Match (n:FileKnowledge)<-[r:hasAssociatedFile]-(m) where id(n) = $fileId id(m) = $associatedId delete r")
    void deleteRelationById(@Param("fileId") Long fileId, @Param("associatedId") Long associatedId);
}
