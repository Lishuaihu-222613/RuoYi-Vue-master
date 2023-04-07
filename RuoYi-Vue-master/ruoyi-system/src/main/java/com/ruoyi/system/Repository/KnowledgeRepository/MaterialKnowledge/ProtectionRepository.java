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

    @Query("MATCH (n:安全要求) where n.label = $protectionName return n")
    Collection<Protection> findByProtectionName(String protectionName);

    @Override
    <S extends Protection> S save(S protection);

    @Override
    void delete(Protection protection);

    @Override
    void deleteById(Long protectionId);

    @Query("MATCH (n:安全要求)<-[r:hasProtection]-(m:材料) where id(m) = $materialId return n")
    Collection<Protection> findProtectionsByMaterialId(@Param("materialId")Long materialId);

    @Query("MATCH (n:安全要求) where id(n) = $protectionId "
            + "MATCH (m:材料) where id(m) = $materialId "
            + "MERGE (m)-[r:hasProtection]->(n)")
    void createRelationshipForProtection(@Param("materialId") Long materialId, @Param("protectionId") Long protectionId);
}
