package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends Neo4jRepository<Material,Long> {

    @Override
    Optional<Material> findById(Long materialId);

    List<Material> findAll();

    Material save(Material material);

    void deleteById(Long materialId);

    @Query("MATCH (n:Material) where n.name = materialName return n")
    Optional<Material> findByMaterialName(@Param("materialName") String materialName);

    @Query("MATCH (n:Danger)<-[r:hasDanger]-(m:Material) where m.id = $materialId delete r, n")
    void removeDangersById(Long materialId);
}
