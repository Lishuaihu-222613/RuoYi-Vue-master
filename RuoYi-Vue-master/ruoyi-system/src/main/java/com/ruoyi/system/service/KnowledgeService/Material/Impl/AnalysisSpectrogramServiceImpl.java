package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.AnalysisSpectrogramRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.AnalysisSpectrogram;
import com.ruoyi.system.service.KnowledgeService.Material.AnalysisSpectrogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnalysisSpectrogramServiceImpl implements AnalysisSpectrogramService {

    @Autowired
    private AnalysisSpectrogramRepository analysisSpectrogramRepository;
    @Override
    public AnalysisSpectrogram createAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram) {
        return analysisSpectrogramRepository.save(analysisSpectrogram);
    }

    @Override
    public AnalysisSpectrogram getAnalysisSpectrogramById(Long spectrogramId) {
        return analysisSpectrogramRepository.findById(spectrogramId).get();
    }

    @Override
    public Collection<AnalysisSpectrogram> getAnalysisSpectrogramByMaterialId(Long materialId) {
        return analysisSpectrogramRepository.findAnalysisSpectrogramsByMaterialId(materialId);
    }

    @Override
    public AnalysisSpectrogram updateAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram) {
        return analysisSpectrogramRepository.save(analysisSpectrogram);
    }

    @Override
    public void deleteAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram) {
        analysisSpectrogramRepository.delete(analysisSpectrogram);
    }

    @Override
    public void deleteAnalysisSpectrogramById(Long spectrogramId) {
        analysisSpectrogramRepository.deleteById(spectrogramId);
    }

    @Override
    public Collection<AnalysisSpectrogram> getAnalysisSpectrogramByName(String spectrogramName) {
        return analysisSpectrogramRepository.findAnalysisSpectrogramsBySpectrogramName(spectrogramName);
    }
}
