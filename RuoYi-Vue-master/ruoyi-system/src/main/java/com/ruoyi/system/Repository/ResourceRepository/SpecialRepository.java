package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface SpecialRepository extends Neo4jRepository<SpecialTool,Long> {

    @Query(value = " Match (n) where any(label in labels(n) WHERE label in ['SpecialTool', $dynamicLabel]) return n" +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n) where any(label in labels(n) WHERE label in ['SpecialTool', $dynamicLabel]) return count(n)"
    )
    Page<SpecialTool> findResourcesByResourceType(String dynamicLabel, Pageable pageable);
}
