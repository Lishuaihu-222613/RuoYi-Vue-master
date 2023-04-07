package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ChemicalProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ChemicalPropertyRepository extends Neo4jRepository<ChemicalProperty,Long> {

    @Override
    Optional<ChemicalProperty> findById(Long propertyId);

    @Query("MATCH (n:ChemicalProperty) where n.label = $propertyName return n")
    Collection<ChemicalProperty> findPhysicalPropertiesByPropertyName(String propertyName);

    @Override
    <S extends ChemicalProperty> S save(S property);

    @Override
    void delete(ChemicalProperty property);

    @Override
    void deleteById(Long propertyId);

    @Query("MATCH (n:化学性质)<-[r:hasChemicalProperty]-(m:材料) where id(m) = $materialId return n")
    ChemicalProperty findChemicalPropertiesByMaterialId(@Param("materialId") Long materialId);
}
