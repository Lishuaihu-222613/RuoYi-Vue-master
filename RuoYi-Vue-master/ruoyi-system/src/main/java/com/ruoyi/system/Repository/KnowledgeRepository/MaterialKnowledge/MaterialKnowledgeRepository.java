package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialKnowledgeInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.MaterialKnowledge;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface MaterialKnowledgeRepository extends Neo4jRepository<MaterialKnowledge,Long> {

    @Override
    Optional<MaterialKnowledge> findById(Long MKId);

    Optional<MaterialKnowledgeInterface> findMaterialKnowledgeInterfaceByMKId(Long MKId);

    @Query("MATCH (n:MaterialKnowledge) where n.label = $mKName return n")
    Optional<MaterialKnowledge> findByMKName(@Param("mKName") String mKName);

    @Query("MATCH (n:MaterialKnowledge) where n.label = $mKName return n")
    Optional<MaterialKnowledgeInterface> findMaterialKnowledgeInterfaceByMKName(@Param("mKName") String mKName);

    MaterialKnowledge save(MaterialKnowledge mKItem);

    @Override
    void delete(MaterialKnowledge mKItem);

    @Override
    void deleteById(Long mKId);

    @Override
    List<MaterialKnowledge> findAll();

    @Query("MATCH (n:MaterialKnowledge) return n")
    Collection<MaterialKnowledgeInterface> findAllMaterialKnowledgeInterfaces();

}
