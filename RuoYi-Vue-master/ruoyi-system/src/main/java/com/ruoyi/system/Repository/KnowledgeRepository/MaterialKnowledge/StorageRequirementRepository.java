package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.StorageRequirement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface StorageRequirementRepository extends Neo4jRepository<StorageRequirement,Long> {

    @Override
    Optional<StorageRequirement> findById(Long requirementId);

    @Query("MATCH (n:StorageRequirement) where n.label = $requirementName return n")
    Collection<StorageRequirement> findByRequirementName(String requirementName);

    StorageRequirement save(StorageRequirementRepository requirement);

    @Override
    void delete(StorageRequirement requirement);

    @Override
    void deleteById(Long requirementId);

    @Query("MATCH (n:StorageRequirement)<-[r:hasRequirement]-(m:Material) where m.id = $materialId return n")
    Collection<StorageRequirement> findStorageRequirementsByMaterialId(@Param("materialId")Long materialId);
}
