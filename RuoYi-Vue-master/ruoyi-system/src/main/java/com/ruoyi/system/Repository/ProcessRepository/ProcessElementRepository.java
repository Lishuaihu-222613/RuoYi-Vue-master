package com.ruoyi.system.Repository.ProcessRepository;

import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface ProcessElementRepository extends Neo4jRepository<ProcessElement,Long> {

    @Override
    <S extends ProcessElement> S save(S element);

    @Query("Match (n) where any(label in labels(n) WHERE label in ['ProcessElement', $dynamicLabel]) return n")
    List<ElementInterface> findAllByLabel(String dynamicLabel);

    @Override
    Optional<ProcessElement> findById(Long elementId);

}
