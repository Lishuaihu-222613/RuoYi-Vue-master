package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;


import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ProduceMethod;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ProduceMethodRepository extends Neo4jRepository<ProduceMethod,Long> {

    @Override
    Optional<ProduceMethod> findById(Long methodId);

    @Query("MATCH (n:生产方法) where id(n) = $methodId return n")
    Optional<ProduceMethodInterface> findProduceMethodInterfaceById(@Param("methodId") Long methodId);

    @Query("MATCH (n:生产方法) where n.label = $methodName return n")
    Collection<ProduceMethodInterface> findByMethodName(@Param("methodName")String methodName);

    ProduceMethod save(ProduceMethod produceMethod);

    @Override
    void delete(ProduceMethod produceMethod);

    @Override
    void deleteById(Long methodId);

    @Query("MATCH (n:生产方法)<-[r:hasProduceMethod]-(m:材料) where id(m) = $materialId return n")
    Collection<ProduceMethodInterface> findProduceMethodByMaterialId(@Param("materialId") Long materialId);

    @Query("MATCH (n:生产方法) where id(n) = $methodId "
            + "MATCH (m:材料) where id(m) = $materialId "
            + "MERGE (m)-[r:hasProduceMethod]->(n)")
    void createRelationshipForProduceMethod(@Param("materialId") Long materialId, @Param("methodId") Long $methodId);
}
