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

    @Query("MATCH (n:AssemblyElement) where id(n) = $elementId return n ")
    AssemblyElement findSingleElement(@Param("elementId") Long elementId);

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
    Page<AssemblyElement> findSubElementsByParentId(@Param("parentId")Long parentId, Pageable pageable);

    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m) where id(n) = $parentId return m ")
    List<AssemblyElement> findSubElementsByParentId(@Param("parentId")Long parentId);

    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m) where id(m) = $elementId return n ")
    AssemblyElement findParentElement(@Param("elementId")Long elementId);

    @Query(value = "MATCH (n:AssemblyElement)-[r:isComposedOf]->(m:AssemblyElement) where id(m) = $elementId delete r ")
    void deleteParentRelation(@Param("elementId") Long elementId);

    @Query(value = "Match (n:AssemblyElement) where id(n) = $parentId " +
            " Match (m:AssemblyElement) where id(m) = $elementId " +
            "Merge (n)-[r:isComposedOf]-> (m)  ")
    void createParentRelation(@Param("parentId") Long parentId,@Param("elementId") Long elementId);

    @Query("Match (n:FileKnowledge) where id(n) = $fileId " +
            "Match (m) where id(m) = $elementId " +
            "MERGE (n)<-[r:hasModelFile]-(m)")
    void createModelFileRelation(@Param("fileId") Long fileId, @Param("elementId") Long elementId);

    @Query("Match (n:FileKnowledge)<-[r:hasModelFile]-(m:AssemblyElement) where id(m) = $elementId delete r")
    void deleteModelFileRelation(@Param("elementId") Long elementId);

    @Query("Match (n:FileKnowledge)<-[r]-(m:AssemblyElement) where id(m) = $elementId delete r")
    void deleteAssociatedFileRelation(@Param("elementId") Long elementId);

    @Query("Match (n:AssemblyElement)<-[r:hasAssociatedStructure]-(m) where id(m) = $relatedId return n")
    List<AssemblyElement> findRelatedStructure(@Param("relatedId") Long relatedId);

    @Query("Match (n:AssemblyElement)<-[r:hasAssociatedStructure]-(m) where id(m) = $relatedId delete r")
    void deleteRelatedStructure(@Param("relatedId") Long relatedId);

    @Query("Match (n) where id(n) = $relatedId " +
            "Match (m) where id(m) = $elementId " +
            "MERGE (n)-[r:hasAssociatedStructure]->(m)")
    void createRelatedStructure(@Param("relatedId") Long relatedId,@Param("elementId") Long elementId);

}
