package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.AnalysisSpectrogram;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface AnalysisSpectrogramRepository extends Neo4jRepository<AnalysisSpectrogram,Long> {

    @Override
    Optional<AnalysisSpectrogram> findById(Long spectrogramId);

    @Query("MATCH (n:AnalysisSpectrogram) where n.label = $SpectrogramName return n")
    Collection<AnalysisSpectrogram> findAnalysisSpectrogramBySpectrogramName(String SpectrogramName);

    @Override
    <S extends AnalysisSpectrogram> S save(S spectrogram);

    @Override
    void delete(AnalysisSpectrogram spectrogram);

    @Override
    void deleteById(Long spectrogramId);

    @Query("MATCH (n:理化分析谱图)<-[r:hasAnalysisSpectrogram]-(m:材料) where id(m) = $materialId return n")
    Collection<AnalysisSpectrogram> findAnalysisSpectrogramByMaterialId(@Param("materialId") Long materialId);
}
