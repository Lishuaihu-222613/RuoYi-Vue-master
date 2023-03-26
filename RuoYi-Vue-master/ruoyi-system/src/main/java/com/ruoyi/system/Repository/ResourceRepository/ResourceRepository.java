package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends Neo4jRepository<AssemblyResource,Long> {

    @Override
    <S extends AssemblyResource> S save(S resource);

    @Override
    List<AssemblyResource> findAll(Sort sort);

    @Override
    Optional<AssemblyResource> findById(Long resourceId);

    List<AssemblyResource> findByResourceNameContainsOrderByResourceId(String resourceName);

    List<AssemblyResource> findByResourceTypesContains(String resourceType);

    @Override
    void deleteById(Long resourceId);
}
