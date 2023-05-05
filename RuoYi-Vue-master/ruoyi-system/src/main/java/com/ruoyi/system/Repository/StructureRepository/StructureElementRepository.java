package com.ruoyi.system.Repository.StructureRepository;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyElement;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StructureElementRepository extends Neo4jRepository<AssemblyElement,Long> {

    @Override
    <S extends AssemblyElement> S save(S element);

    @Override
    <S extends AssemblyElement> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    List<AssemblyElement> findAll();

    @Override
    Optional<AssemblyElement> findById(Long elementId);

    @Override
    void deleteAllById(Iterable<? extends Long> Ids);

    @Override
    void deleteById(Long aLong);

    @Override
    List<AssemblyElement> findAll(Sort sort);

    @Override
    <S extends AssemblyElement> List<S> findAll(Example<S> example, Sort sort);

    @Query("MATCH (n:AssemblyElement :AssemblyProduct) return n ")
    List<AssemblyElement> findAllProducts();

    @Query(value = "MATCH (n:AssemblyProduct :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:AssemblyProduct :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<AssemblyElement> findProductsByLabel(@Param("dynamicLabel")String dynamicLabel, Pageable pageable);

    @Query(value = "MATCH (n:AssemblyProduct :`:#{literal(#dynamicLabel)}`) return n ")
    List<AssemblyElement> findProductsByLabel(@Param("dynamicLabel")String dynamicLabel);

    @Query(value = "MATCH (n:AssemblyElement :`:#{allOf(#dynamicLabels)}`) return n ")
    List<AssemblyElement> findElementsByLabels(List<String> dynamicLabels);

    @Query(value = "MATCH (n:AssemblyElement)-[r:hasConstraint]->(m:AssemblyConstraint) where id(m)= $constraintId return n ")
    List<AssemblyElement> findElementsByConstraintId(@Param("constraintId") Long constraintId);


    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m) where id(n) = $parentId return m " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m) where id(n) = $productId return count(m)"
    )
    Page<AssemblyElement> findSubElementsByParentId(@Param("$parentId")Long $parentId, Pageable pageable);

    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m) where id(n) = $parentId return m ")
    List<AssemblyElement> findSubElementsByParentId(@Param("$parentId")Long $parentId);

    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m:AssemblyElement) where id(n) = $elementId delete r ")
    void deleteParentRelation(@Param("elementId") Long elementId);

    @Query(value = "CREATE (n:AssemblyElement)-[r:isComposedOf]->(m:AssemblyElement) where id(n) = $parentId and id(m) = $elementId ")
    void createParentRelation(@Param("parentId") Long parentId,@Param("elementId") Long elementId);

}
