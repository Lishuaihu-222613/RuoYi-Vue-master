package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.PhysicalProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface PhysicalPropertyRepository extends Neo4jRepository<PhysicalProperty,Long> {

    @Override
    Optional<PhysicalProperty> findById(Long propertyId);

    @Query("MATCH (n:PhysicalProperty) where n.label = $propertyName return n")
    Collection<PhysicalProperty> findPhysicalPropertiesByPropertyName(String propertyName);

    PhysicalProperty save(String propertyName);

    @Override
    void delete(PhysicalProperty property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:PhysicalProperty)<-[r:hasPhysicalProperty]-(m:Material) where m.id = $materialId return n")
    Collection<PhysicalProperty> findPhysicalPropertiesByMaterialId(Long materialId);
}
