package com.ruoyi.system.Repository.ProcessRepository;

import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessElementRepository extends Neo4jRepository<ProcessElement,Long> {

    @Override
    <S extends ProcessElement> S save(S element);

    @Query("Match (n) where any(label in labels(n) WHERE label in ['ProcessElement', $dynamicLabel]) return n")
    List<ElementInterface> findAllByLabel(String dynamicLabel);

    @Override
    Optional<ProcessElement> findById(Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasBeforeElement]-(m:ProcessElement) where id(n) = $elementId return n")
    List<ProcessElement> findBeforeElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasAfterElement]-(m:ProcessElement) where id(n) = $elementId return n")
    List<ProcessElement> findAfterElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasAndElement]-(m:ProcessElement) where id(n) = $elementId return n")
    List<ProcessElement> findAndElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasBeforeElement]-(m:ProcessElement) where id(n) = $elementId return n")
    List<ProcessElement> findOrElementsById(@Param("elementId") Long elementId);

    @Query("Match (n:ProcessElement)-[r:hasSubElement]-(m:ProcessElement) where id(n) = $elementId return n")
    List<ProcessElement> findSubElementsById(@Param("elementId") Long elementId);
}
