package com.ruoyi.system.Repository.KnowledgeRepository.TypicalKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Structure.TypicalStructure;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface TypicalStructureRepository extends Neo4jRepository<TypicalStructure,Long> {

    @Override
    <S extends TypicalStructure> S save(S structure);

    @Override
    <S extends TypicalStructure> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Optional<TypicalStructure> findById(Long structureId);

    @Override
    Page<TypicalStructure> findAll(Pageable pageable);

    @Override
    List<TypicalStructure> findAll(Sort sort);


}
