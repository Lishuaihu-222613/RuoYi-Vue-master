package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface AuxiliaryRepository extends Neo4jRepository<AuxiliaryResource,Long> {

    @Override
    Page<AuxiliaryResource> findAll(Pageable pageable);

    @Override
    <S extends AuxiliaryResource> Page<S> findAll(Example<S> example, Pageable pageable);

    @Query(value = " Match (n:AuxiliaryResource :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:AuxiliaryResource :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<AuxiliaryResource> findResourcesByResourceType(@Param("dynamicLabel")String resourceType, Pageable pageable);
}
