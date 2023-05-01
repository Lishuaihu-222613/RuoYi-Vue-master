package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface MeasuringToolRepository extends Neo4jRepository<MeasuringTool,Long> {

    @Query(value = " Match (n:MeasuringTool :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:MeasuringTool :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<MeasuringTool> findResourcesByResourceType(@Param("dynamicLabel")String dynamicLabel, Pageable pageable);
}
