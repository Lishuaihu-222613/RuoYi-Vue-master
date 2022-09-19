package com.ruoyi.system.Repository.StructureRepository;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyConstraint;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConstraintRepository extends Neo4jRepository<AssemblyConstraint, Long> {

    Optional<AssemblyConstraint> findById(@Param("Id") Long ACId);

    AssemblyConstraint save(AssemblyConstraint ACItem);

    void deleteById(Long ACId);

    @Query("MATCH (n:AssemblyStructure) -[r:hasConstraint]-> (m:AssemblyConstraint) where n.Id = ASId return m")
    List<AssemblyConstraint> findAllByASItem(Long ASId);
}
