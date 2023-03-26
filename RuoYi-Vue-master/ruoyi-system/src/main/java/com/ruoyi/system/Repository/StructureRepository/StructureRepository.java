package com.ruoyi.system.Repository.StructureRepository;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StructureRepository extends Neo4jRepository<AssemblyStructure, Long> {

    List<AssemblyStructure> findAll();

    Optional<AssemblyStructure> findById(@Param("Id") Long ASId);

    AssemblyStructure save(AssemblyStructure ASItem);

    void deleteById(Long ASId);

    @Query("MATCH (n:AssemblyProduct) return *")
    List<AssemblyProduct> findAllProduct();

    @Override
    <S extends AssemblyStructure> List<S> findAll(Example<S> example);


}
