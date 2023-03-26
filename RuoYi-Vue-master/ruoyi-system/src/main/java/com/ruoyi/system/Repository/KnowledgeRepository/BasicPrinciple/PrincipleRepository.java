package com.ruoyi.system.Repository.KnowledgeRepository.BasicPrinciple;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrincipleRepository extends Neo4jRepository<Principle,Long> {

    @Override
    <S extends Principle> S save(S principle);

    @Override
    Page<Principle> findAll(Pageable pageable);

    @Override
    Optional<Principle> findById(Long principleId);
    @Query("Match (n:Principle :$dynamicLabel) return n")
    Page<Principle> findPrincipleByType(@Param("dynamicLabel") String dynamicLabel,Pageable pageable);

    @Override
    void deleteById(Long principleId);

    List<Principle> findPrinciplesByPrincipleName(String principalName);
}
