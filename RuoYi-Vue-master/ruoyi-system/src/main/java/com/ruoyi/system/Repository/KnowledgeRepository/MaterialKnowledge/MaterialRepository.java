package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends Neo4jRepository<Material,Long> {

    Optional<Material> findById(Long materialId);

    @Query("MATCH (n:材料) where id(n) = $materialId return n")
    Optional<MaterialInterface> findMaterialInterfaceById(@Param("materialId") Long materialId);

    List<Material> findAll();

    Material save(Material material);

    void deleteById(Long materialId);

    @Query("MATCH (n:Material) where n.name contains $materialName return n")
    Optional<Material> findByMaterialName(@Param("materialName") String materialName);

    @Query("MATCH (n:Material)<-[r:hasCommonMaterial]-(m:MaterialKnowledge) where m.id = $MKId return n")
    Collection<MaterialInterface> findMaterialsByMKId(Long MKId);

    @Query("MATCH (n:Material) return n")
    Collection<MaterialInterface> findSingleMaterials();

    @Query("MATCH (n:材料 :$dynamicLabel) return n")
    Collection<MaterialInterface> findMaterialsByLabel(@Param("dynamicLabel") String dynamicLabel);
}
