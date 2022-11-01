package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface StorageRequirementRepository extends Neo4jRepository<StorageRequirementRepository,Long> {

    @Override
    Optional<StorageRequirementRepository> findById(Long requirementId);

    @Query("MATCH (n:StorageRequirement) where n.label = $requirementName return n")
    Optional<StorageRequirementRepository> findByRequirementName(String requirementName);

    StorageRequirementRepository save(StorageRequirementRepository requirement);

    @Override
    void delete(StorageRequirementRepository requirement);

    @Override
    void deleteById(Long requirementId);

    @Query("MATCH (n:StorageRequirement)<-[r:hasRequirement]-(m:Material) where m.id = $materialId return n")
    Collection<StorageRequirementRepository> findStorageRequirementsByMaterialId(@Param("materialId")String materialId);
}
