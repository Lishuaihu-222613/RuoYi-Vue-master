package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;


import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ProduceMethod;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ProduceMethodRepository extends Neo4jRepository<ProduceMethod,Long> {

    @Override
    Optional<ProduceMethod> findById(Long methodId);

    @Query("MATCH (n:ProduceMethod) where n.label = $methodName return n")
    Collection<ProduceMethod> findByMethodName(String methodName);

    ProduceMethod save(ProduceMethod produceMethod);

    @Override
    void delete(ProduceMethod produceMethod);

    @Override
    void deleteById(Long methodId);

    @Query("MATCH (n:ProduceMethod)<-[r:hasProduceMethod]-(m:Material) where m.id = $materialId return n")
    Collection<ProduceMethod> findProduceMethodByMaterialId(@Param("materialId") Long materialId);
}
