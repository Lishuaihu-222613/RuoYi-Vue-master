package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface InspectProjectRepository extends Neo4jRepository<InspectProject,Long> {

    @Override
    Optional<InspectProject> findById(Long projectId);

    @Query("MATCH (n:InspectProject) where n.label = $projectName return n")
    Collection<InspectProject> findInspectProjectsByProjectName(String projectName);

    InspectProject save(InspectProject project);

    @Override
    void delete(InspectProject project);

    @Override
    void deleteById(Long aLong);

    @Query("MATCH (n:InspectProject)<-[r:hasInspectProject]-(m:Material) where m.id = $materialId return n")
    Collection<InspectProject> findInspectProjectsByMaterialId(Long MaterialId);
}
