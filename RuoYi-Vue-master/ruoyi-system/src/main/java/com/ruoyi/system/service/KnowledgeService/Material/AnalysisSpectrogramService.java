package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.AnalysisSpectrogram;

import java.util.Collection;

public interface AnalysisSpectrogramService {

    /**
     * 创建理化分析图谱节点
     * @param analysisSpectrogram
     * @return
     */
    AnalysisSpectrogram createAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram);

    /**
     * 根据id检索理化图谱分析节点
     * @param spectrogramId
     * @return
     */
    AnalysisSpectrogram getAnalysisSpectrogramById(Long spectrogramId);

    /**
     * 根据材料Id获得关联理化分析谱图节点
     * @param materialId
     * @return
     */
    Collection<AnalysisSpectrogram> getAnalysisSpectrogramByMaterialId(Long materialId);

    /**
     * 更新理化分析谱图节点
     * @param analysisSpectrogram
     * @return
     */
    AnalysisSpectrogram updateAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram);

    /**
     * 删除节点
     * @param analysisSpectrogram
     */
    void deleteAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram);

    /**
     *  依据Id删除节点
     * @param spectrogramId
     */
    void deleteAnalysisSpectrogramById(Long spectrogramId);

    /**
     * 依据名称查询理化分析谱图节点
     * @param spectrogramName
     * @return
     */
    Collection<AnalysisSpectrogram> getAnalysisSpectrogramByName(String spectrogramName);
}
