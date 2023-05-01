package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface EquipmentRepository extends Neo4jRepository<EquipmentResource,Long> {

    @Query(value = " Match (n:EquipmentResource :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:EquipmentResource :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<EquipmentResource> findResourcesByResourceType(@Param("dynamicLabel")String dynamicLabel, Pageable pageable);


}
