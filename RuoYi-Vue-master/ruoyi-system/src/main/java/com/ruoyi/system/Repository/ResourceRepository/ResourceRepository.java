package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends Neo4jRepository<AssemblyResource,Long> {

    @Override
    <S extends AssemblyResource> S save(S resource);

    @Override
    List<AssemblyResource> findAll(Sort sort);

    @Query(value = " Match (n) where any(label in labels(n) WHERE label in ['AssemblyResource', $dynamicLabel]) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n) where any(label in labels(n) WHERE label in ['AssemblyResource', $dynamicLabel]) return count(n)"
    )
    Page<AssemblyResource> findResourcesByResourceType(String resourceType, Pageable pageable);

    @Query(value = "MATCH (n:`:#{allOf(#label)}`) RETURN n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (n:`:#{allOf(#label)}`) RETURN count(n) "
    )
    Page<AssemblyResource> findSingleResourcesByType(List<String> labels, Pageable pageable);

    @Override
    Optional<AssemblyResource> findById(Long resourceId);

    List<AssemblyResource> findByResourceNameContainsOrderByResourceId(String resourceName);

    List<AssemblyResource> findByResourceTypesContains(String resourceType);

    @Override
    void deleteById(Long resourceId);

    @Override
    <S extends AssemblyResource> Page<S> findAll(Example<S> example, Pageable pageable);

    @Query("Match (n:AssemblyResource)<-[]-(m) where id(m) = $associatedId return n ")
    List<AssemblyResource> findResourceByAssociatedId(@Param("associatedId") Long associatedId);

    @Query("Match (n:AssemblyResource)<-[]-(m) where id(n) = $resourceId and id(m) = $associatedId delete r ")
    void deleteRelation(@Param("resourceId") Long resourceId,@Param("associatedId") Long associatedId);

    @Query("Match (n:AssemblyResource) where id(n) = $resourceId " +
            "Match (m) where id(m) = $associatedId " +
            "Merge  (n)<-[r:use]-(m)")
    void createUseRelation(@Param("resourceId") Long resourceId,@Param("associatedId") Long associatedId);

    @Query("Merge (n:AssemblyResource)<-[r:hasAssociatedDevice]-(m) where id(n) = $resourceId and id(m) = $associatedId ")
    void createDeviceRelation(@Param("resourceId") Long resourceId,@Param("associatedId") Long associatedId);

}
