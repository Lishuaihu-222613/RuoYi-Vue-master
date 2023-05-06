package com.ruoyi.system.Repository.StructureRepository;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyConstraint;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConstraintRepository extends Neo4jRepository<AssemblyConstraint, Long> {

    @Override
    Optional<AssemblyConstraint> findById(Long constraintId);

    @Override
    <S extends AssemblyConstraint> S save(S constraint);

    @Override
    void deleteById(Long constraintId);

    @Query("MATCH (n:AssemblyElement) -[r:hasConstraint]-> (m:AssemblyConstraint) where id(n) = $elementId return m")
    List<AssemblyConstraint> findConstraintsByElementId(@Param("elementId") Long elementId);

    @Query("Match (n:AssemblyElement) -[r:hasConstraint]-> (m:AssemblyConstraint) where id(m) = $constraintId return m ,collect(n)")
    AssemblyConstraint findSingleConstraintById(@Param("constraintId") Long constraintId);

    @Query("Match (n:AssemblyElement) -[r:hasConstraint]- (m:AssemblyConstraint) where id(m) = $constraintId delete r")
    void deleteRelationForConstraint(@Param("constraintId") Long constraintId);

    @Query("Match (n:AssemblyElement) where id(n) = $elementId " +
            "Match (m:AssemblyConstraint) where id(m) = $constraintId " +
            "Merge (n)-[r:hasConstraint]->(m) " )
    void updateRelationShipForConstraint(@Param("constraintId") Long constraintId,@Param("elementId") Long elementId);
}
