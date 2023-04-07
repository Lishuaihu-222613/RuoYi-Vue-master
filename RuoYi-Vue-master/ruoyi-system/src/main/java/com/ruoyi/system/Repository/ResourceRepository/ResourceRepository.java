package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends Neo4jRepository<AssemblyResource,Long> {

    @Override
    <S extends AssemblyResource> S save(S resource);

    @Override
    List<AssemblyResource> findAll(Sort sort);

    @Query(value = " Match (n) where any(label in labels(n) WHERE label in ['AssemblyResource', $dynamicLabel]) return n" +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n) where any(label in labels(n) WHERE label in ['AssemblyResource', $dynamicLabel]) return count(n)"
    )
    Page<AssemblyResource> findResourcesByResourceType(String resourceType, Pageable pageable);

    @Override
    Optional<AssemblyResource> findById(Long resourceId);

    List<AssemblyResource> findByResourceNameContainsOrderByResourceId(String resourceName);

    List<AssemblyResource> findByResourceTypesContains(String resourceType);

    @Override
    void deleteById(Long resourceId);

    @Override
    <S extends AssemblyResource> Page<S> findAll(Example<S> example, Pageable pageable);
}
