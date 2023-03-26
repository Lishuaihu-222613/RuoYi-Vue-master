package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.AnalysisSpectrogram;

public class AMvo {

    private Long materialId;

    private AnalysisSpectrogram analysisSpectrogram;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public AnalysisSpectrogram getAnalysisSpectrogram() {
        return analysisSpectrogram;
    }

    public void setAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram) {
        this.analysisSpectrogram = analysisSpectrogram;
    }

    public AMvo() {
    }

    public AMvo(Long materialId, AnalysisSpectrogram analysisSpectrogram) {
        this.materialId = materialId;
        this.analysisSpectrogram = analysisSpectrogram;
    }

    @Override
    public String toString() {
        return "AMvo{" +
                "materialId=" + materialId +
                ", analysisSpectrogram=" + analysisSpectrogram +
                '}';
    }
}
