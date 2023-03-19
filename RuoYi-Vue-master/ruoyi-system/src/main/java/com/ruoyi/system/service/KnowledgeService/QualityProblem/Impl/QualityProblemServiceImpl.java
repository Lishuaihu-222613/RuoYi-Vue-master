package com.ruoyi.system.service.KnowledgeService.QualityProblem.Impl;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;
import com.ruoyi.system.service.KnowledgeService.QualityProblem.QualityProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QualityProblemServiceImpl implements QualityProblemService {

    @Resource
    private QualityProblemRepository qualityProblemRepository;

    @Resource
    private AppearanceRepository appearanceRepository;

    @Resource
    private ReasonRepository reasonRepository;

    @Resource
    private SolutionRepository solutionRepository;

    @Override
    public QualityProblem createQualityProblem(QualityProblem problem) {
        qualityProblemRepository.save(problem);
        return null;
    }

    @Override
    public QualityProblem updateQualityProblem(QualityProblem problem) {
        return null;
    }

    @Override
    public List<QualityProblem> getAllQualityProblems() {
        return null;
    }

    @Override
    public List<QualityProblem> getQualityProblemsByName(String problemName) {
        return null;
    }

    @Override
    public QualityProblem getQualityProblemById(Long problemId) {
        return null;
    }

    @Override
    public void deleteQualityProblem(Long problemId) {

    }

    @Override
    public Appearance createAppearanceForQualityProblem(Long problemId, Appearance appearance) {
        return null;
    }

    @Override
    public Appearance updateAppearance(Appearance appearance) {
        return null;
    }

    @Override
    public List<Appearance> getAppearanceForQualityProblem(Long problemId) {
        return null;
    }

    @Override
    public void deleteAppearanceForQualityProblem(Long problemId) {

    }

    @Override
    public void deleteAppearanceById(Long appearanceId) {

    }

    @Override
    public Reason createReasonForQualityProblem(Long problemId, Reason reason) {
        return null;
    }

    @Override
    public Reason updateReason(Reason reason) {
        return null;
    }

    @Override
    public List<Reason> getReasonForQualityProblem(Long problemId) {
        return null;
    }

    @Override
    public void deleteReasonForQualityProblem(Long problemId) {

    }

    @Override
    public void deleteReasonById(Long reasonId) {

    }

    @Override
    public Solution createsSolutionForQualityProblem(Long problemId, Solution solution) {
        return null;
    }

    @Override
    public Solution updateSolution(Solution solution) {
        return null;
    }

    @Override
    public List<Solution> getSolutionForQualityProblem(Long problemId) {
        return null;
    }

    @Override
    public void deleteSolutionForQualityProblem(Long problemId) {

    }

    @Override
    public void deleteSolutionById(Long solutionId) {

    }
}
