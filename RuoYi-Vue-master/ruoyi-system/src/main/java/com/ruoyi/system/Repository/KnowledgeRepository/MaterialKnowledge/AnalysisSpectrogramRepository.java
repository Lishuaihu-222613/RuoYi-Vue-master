package com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.AnalysisSpectrogram;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.Optional;

public interface AnalysisSpectrogramRepository extends Neo4jRepository<AnalysisSpectrogram,Long> {

    @Override
    Optional<AnalysisSpectrogram> findById(Long spectrogramId);

    @Query("MATCH (n:AnalysisSpectrogram) where n.label = $SpectrogramName return n")
    Collection<AnalysisSpectrogram> findAnalysisSpectrogramsBySpectrogramName(String SpectrogramName);

    AnalysisSpectrogram save(AnalysisSpectrogram spectrogram);

    @Override
    void delete(AnalysisSpectrogram spectrogram);

    @Override
    void deleteById(Long spectrogramId);

    @Query("MATCH (n:AnalysisSpectrogram)<-[r:hasAnalysisSpectrogram]-(m:Material) where m.id = $materialId return n")
    Collection<AnalysisSpectrogram> findAnalysisSpectrogramsByMaterialId(Long materialId);
}
