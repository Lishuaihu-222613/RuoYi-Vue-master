package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

public interface MaterialRepository extends Neo4jRepository<Material,Long> {

    Optional<Material> findById(Long materialId);

    @Query("MATCH (n:材料) where id(n) = $materialId return n")
    Optional<MaterialInterface> findMaterialInterfaceById(@Param("materialId") Long materialId);

    @Override
    Page<Material> findAll(Pageable pageable);

    @Override
    <S extends Material> S save(S material);

    void deleteById(Long materialId);

    @Query("MATCH (n:Material) where n.name contains $materialName return n")
    Optional<Material> findByMaterialName(@Param("materialName") String materialName);

    @Override
    <S extends Material> List<S> findAll(Example<S> example);

    @Query("MATCH (n:Material) return n")
    Page<MaterialInterface> findSingleMaterials(Pageable pageable);

    @Query("MATCH (n:material :$dynamicLabel) return n")
    Page<MaterialInterface> findMaterialsByLabel(@Param("dynamicLabel") String dynamicLabel , Pageable pageable);
}
