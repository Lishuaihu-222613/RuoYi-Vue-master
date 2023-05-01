package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface SpreaderToolRepository extends Neo4jRepository<SpreaderTool,Long> {

    @Query(value = " Match (n:SpreaderTool :`:#{literal(#dynamicLabel)}`) return n " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n:SpreaderTool :`:#{literal(#dynamicLabel)}`) return count(n)"
    )
    Page<SpreaderTool> findResourcesByResourceType(@Param("dynamicLabel")String dynamicLabel, Pageable pageable);
}
