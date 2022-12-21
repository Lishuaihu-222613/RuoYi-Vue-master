package com.ruoyi.system.Repository.KnowledgeRepository.File;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FileRepository extends Neo4jRepository<FileKnowledge,Long> {

}
