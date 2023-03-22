package com.ruoyi.system.service.KnowledgeService.QualityProblem;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;

import java.util.List;

public interface QualityProblemService {

    QualityProblem createQualityProblem(QualityProblem problem);

    QualityProblem updateQualityProblem(QualityProblem problem);

    List<QualityProblem> getAllQualityProblems();

    List<QualityProblem> getQualityProblemsByName(String problemName);

    QualityProblem getQualityProblemById(Long problemId);

    void deleteQualityProblem(Long problemId);

    Appearance createAppearanceForQualityProblem(Long problemId, Appearance appearance);

    Appearance updateAppearance(Appearance appearance);

    List<Appearance> getAppearanceForQualityProblem(Long problemId);

    void deleteAppearanceForQualityProblem(Long problemId);

    void deleteAppearanceById(Long appearanceId);

    Reason createReasonForQualityProblem(Long problemId,Reason reason);

    Reason updateReason(Reason reason);

    List<Reason> getReasonForQualityProblem(Long problemId);

    void deleteReasonForQualityProblem(Long problemId);

    void deleteReasonById(Long reasonId);

    Solution createSolutionForQualityProblem(Long problemId,Solution solution);

    Solution updateSolution(Solution solution);

    List<Solution> getSolutionForQualityProblem(Long problemId);

    void deleteSolutionForQualityProblem(Long problemId);

    void deleteSolutionById(Long solutionId);
}