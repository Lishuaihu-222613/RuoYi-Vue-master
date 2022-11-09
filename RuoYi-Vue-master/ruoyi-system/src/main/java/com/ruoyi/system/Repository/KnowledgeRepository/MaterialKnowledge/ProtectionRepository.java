package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Protection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ProtectionRepository extends Neo4jRepository<Protection,Long> {

    @Override
    Optional<Protection> findById(Long protectionId);

    @Query("MATCH (n:Protection) where n.label = $protectionName return n")
    Collection<Protection> findByProtectionName(String protectionName);

    Protection save(Protection protection);

    @Override
    void delete(Protection protection);

    @Override
    void deleteById(Long protectionId);

    @Query("MATCH (n:Protection)<-[r:hasProtection]-(m:Material) where m.id = $materialId return n")
    Collection<Protection> findProtectionsByMaterialId(@Param("materialId")Long materialId);
}
