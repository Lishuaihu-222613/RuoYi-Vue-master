package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface InspectProjectRepository extends Neo4jRepository<InspectProject,Long> {

    @Override
    Optional<InspectProject> findById(Long projectId);

    @Query("MATCH (n:理化指标) where n.label = $projectName return n")
    Collection<InspectProject> findInspectProjectsByProjectName(String projectName);

    @Override
    <S extends InspectProject> S save(S project);

    @Override
    void delete(InspectProject project);

    @Override
    void deleteById(Long projectId);

    @Query("MATCH (n:理化指标)<-[r:hasInspectProject]-(m:材料) where id(m) = $materialId return n")
    Collection<InspectProject> findInspectProjectsByMaterialId(@Param("materialId") Long materialId);

    @Query("MATCH (n:理化指标) where id(n) = $projectId "
            + "MATCH (m:材料) where id(m) = $materialId "
            + "MERGE (m)-[r:hasInspectProject]->(n)")
    void createRelationshipForInspectProject(@Param("materialId") Long materialId, @Param("projectId") Long projectId);
}
