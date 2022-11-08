package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ChemicalProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface ChemicalPropertyRepository extends Neo4jRepository<ChemicalProperty,Long> {

    @Override
    Optional<ChemicalProperty> findById(Long propertyId);

    @Query("MATCH (n:ChemicalProperty) where n.label = $propertyName return n")
    Collection<ChemicalProperty> findPhysicalPropertiesByPropertyName(String propertyName);

    ChemicalProperty save(String propertyName);

    @Override
    void delete(ChemicalProperty property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:ChemicalProperty)<-[r:hasChemicalProperty]-(m:Material) where m.id = $materialId return n")
    Collection<ChemicalProperty> findChemicalPropertiesByMaterialId(Long materialId);
}
