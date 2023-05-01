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

public interface MaterialRepository extends Neo4jRepository<Material, Long> {

    Optional<Material> findById(Long materialId);

    @Query("MATCH (n:Material) return n")
    List<Material> findAllMaterialOptions();

    @Query(value = "MATCH (n:Material :`:#{literal(#dynamicLabel)}`) return n ")
    List<Material> findMaterialOptionsByLabel(@Param("dynamicLabel") String dynamicLabel);

    @Query("MATCH (n:Material) where id(n) = $materialId return n")
    Optional<MaterialInterface> findMaterialInterfaceById(@Param("materialId") Long materialId);

    @Override
    Page<Material> findAll(Pageable pageable);

    @Override
    <S extends Material> S save(S material);

    void deleteById(Long materialId);

    @Query(value = "MATCH (n:Material) where n.name contains $materialName return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:Material) where n.name contains $materialName return count(n) "
    )
    Page<Material> findByMaterialName(@Param("materialName") String materialName, Pageable pageable);

    @Override
    <S extends Material> List<S> findAll(Example<S> example);

    @Query(value = "MATCH (n:Material) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:Material) return count(n)"
    )
    Page<Material> findSingleMaterials(Pageable pageable);

    @Query(value = "MATCH (n:Material :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:Material :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<Material> findMaterialsByLabel(@Param("dynamicLabel") String dynamicLabel, Pageable pageable);

    @Override
    <S extends Material> Page<S> findAll(Example<S> example, Pageable pageable);
}
