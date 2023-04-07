package com.ruoyi.system.Repository.KnowledgeRepository.ClassificationTree;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreeRepository extends Neo4jRepository<ClassificationTree,Long> {

    @Override
    <S extends ClassificationTree> S save(S leaf);

    @Override
    Optional<ClassificationTree> findById(Long leafId);

    @Query("Match (n:ClassificationTree :$dynamicLabel) return n")
    List<ClassificationTree> findLeafsByType(@Param("dynamicLabel") String dynamicLabel);

    @Query("Match (n:ClassificationTree)<-[r:hasSubLeaf]-(m) where id(m) = $parentId return n")
    List<ClassificationTree> findLeafsByParentId(@Param("parentId") Long parentId, Sort sort);

    @Override
    void deleteById(Long leafId);
}
