package com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppearanceRepository extends Neo4jRepository<Appearance,Long> {

    @Override
    <S extends Appearance> S save(S Appearance);

    @Override
    Optional<Appearance> findById(Long appearanceId);

    @Query("Match (n:Appearance)<-[r]-(m:QualityProblem) where id(m) =$problemId return n ")
    List<Appearance> findAppearanceByProblemId(@Param("problemId") Long problemId);

    @Override
    void deleteById(Long problemId);
}
