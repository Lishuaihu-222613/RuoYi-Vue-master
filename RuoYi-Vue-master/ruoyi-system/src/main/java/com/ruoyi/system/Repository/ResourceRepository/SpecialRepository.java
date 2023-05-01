package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialRepository extends Neo4jRepository<SpecialTool,Long> {

    @Query(value = " Match (n:SpecialTool :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:SpecialTool :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<SpecialTool> findResourcesByResourceType(@Param("dynamicLabel")String dynamicLabel, Pageable pageable);
}
