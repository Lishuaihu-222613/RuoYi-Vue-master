package com.ruoyi.system.Repository.ResourceRepository;

import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface SpreaderToolRepository extends Neo4jRepository<SpreaderTool,Long> {

    @Query(value = " Match (n) where any(label in labels(n) WHERE label in ['SpreaderTool', $dynamicLabel]) return n" +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "Match (n) where any(label in labels(n) WHERE label in ['SpreaderTool', $dynamicLabel]) return count(n)"
    )
    Page<SpreaderTool> findResourcesByResourceType(String dynamicLabel, Pageable pageable);
}
