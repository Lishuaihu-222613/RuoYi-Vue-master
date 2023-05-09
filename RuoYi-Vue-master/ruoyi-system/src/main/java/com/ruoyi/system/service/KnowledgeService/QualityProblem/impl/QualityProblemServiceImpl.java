package com.ruoyi.system.service.KnowledgeService.QualityProblem.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem.AppearanceRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem.QualityProblemRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem.ReasonRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.QualityProblem.SolutionRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;
import com.ruoyi.system.service.KnowledgeService.QualityProblem.QualityProblemService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        QualityProblem qualityProblem = new QualityProblem(problem.getProblemId(),
                problem.getProblemName(), problem.getProblemDescription(),
                problem.getDynamicLabels());
        QualityProblem singleProblem = qualityProblemRepository.save(qualityProblem);
        HashSet<Appearance> appearances = new HashSet<>();
        HashSet<Reason> reasons = new HashSet<>();
        HashSet<Solution> solutions = new HashSet<>();
        for (Appearance appearance : problem.getAppearances()) {
            Appearance newAppearance = appearanceRepository.save(appearance);
            appearances.add(newAppearance);
        };
        for (Reason reason : problem.getReasons()) {
            Reason newReason = reasonRepository.save(reason);
            reasons.add(newReason);
        };
        for (Solution solution : problem.getSolutions()) {
            Solution newSolution = solutionRepository.save(solution);
            solutions.add(newSolution);
        }
        singleProblem.setAppearances(appearances);
        singleProblem.setReasons(reasons);
        singleProblem.setSolutions(solutions);
        return qualityProblemRepository.save(singleProblem);
    }

    @Override
    public QualityProblem updateQualityProblem(QualityProblem problem) {
        Optional<QualityProblem> oProblem = qualityProblemRepository.findById(problem.getProblemId());
        if(oProblem.isPresent()){
            QualityProblem oldProblem = oProblem.get();
            oldProblem.setProblemName(problem.getProblemName());
            oldProblem.setProblemDescription(problem.getProblemDescription());
            oldProblem.setDynamicLabels(problem.getDynamicLabels());
            appearanceRepository.deleteAll(oldProblem.getAppearances());
            List<Appearance> appearances = appearanceRepository.saveAll(problem.getAppearances());
            reasonRepository.deleteAll(oldProblem.getReasons());
            List<Reason> reasons = reasonRepository.saveAll(problem.getReasons());
            solutionRepository.deleteAll(oldProblem.getSolutions());
            List<Solution> solutions = solutionRepository.saveAll(problem.getSolutions());
            oldProblem.setAppearances(new HashSet<>(appearances));
            oldProblem.setReasons(new HashSet<>(reasons));
            oldProblem.setSolutions(new HashSet<>(solutions));
            QualityProblem newProblem = qualityProblemRepository.save(oldProblem);
            return newProblem;
        }
        return null;
    }

    @Override
    public Page<QualityProblem> getAllQualityProblems(Pageable pageable) {
        return qualityProblemRepository.findAll(pageable);
    }

    @Override
    public Page<QualityProblem> getQualityProblemsByLabel(String dynamicLabel, Pageable pageable) {
        return qualityProblemRepository.findProblemsByLabel(dynamicLabel,pageable);
    }

    @Override
    public List<QualityProblem> getQualityProblemOptionsByLabel(String dynamicLabel) {
        return qualityProblemRepository.getQualityProblemOptionsByLabel(dynamicLabel);
    }

    @Override
    public Page<QualityProblem> getQualityProblemsByParams(Example<QualityProblem> example, Pageable pageable) {
        return qualityProblemRepository.findAll(example,pageable);
    }

    @Override
    public List<QualityProblem> getQualityProblemsByName(String problemName) {
        List<QualityProblem> problems = qualityProblemRepository.findByProblemName(problemName);
        return problems;
    }

    @Override
    public QualityProblem getQualityProblemById(Long problemId) {
        Optional<QualityProblem> qualityProblem = qualityProblemRepository.findById(problemId);
        if (qualityProblem.isPresent()) {
            return qualityProblem.get();
        }
        return null;
    }

    @Override
    public void deleteQualityProblem(Long problemId) {
        qualityProblemRepository.deleteById(problemId);
    }

    @Override
    public void createRelatedRelations(Long relatedId, List<Long> problems) {
        for (Long problemId : problems) {
            qualityProblemRepository.createRelationById(problemId,relatedId);
        }
    }

    @Override
    public List<QualityProblem> getProblemsByRelatedId(Long relatedId) {
        return qualityProblemRepository.findByAssociatedId(relatedId);
    }

    @Override
    public Appearance createAppearanceForQualityProblem(Long problemId, Appearance appearance) {
        Appearance newAppearance = appearanceRepository.save(appearance);
        Optional<QualityProblem> qualityProblem = qualityProblemRepository.findById(problemId);
        if(qualityProblem.isPresent()){
            QualityProblem problem = qualityProblem.get();
            Set<Appearance> appearances = problem.getAppearances();
            appearances.add(newAppearance);
            problem.setAppearances(appearances);
            qualityProblemRepository.save(problem);
            return newAppearance;
        }
        return null;
    }

    @Override
    public Appearance updateAppearance(Appearance appearance) {
        Appearance newAppearance = appearanceRepository.save(appearance);
        return newAppearance;
    }

    @Override
    public List<Appearance> getAppearanceForQualityProblem(Long problemId) {
        List<Appearance> appearances = appearanceRepository.findAppearanceByProblemId(problemId);
        return appearances;
    }

    @Override
    public void deleteAppearanceForQualityProblem(Long problemId) {
        List<Appearance> appearances = appearanceRepository.findAppearanceByProblemId(problemId);
        appearanceRepository.deleteAll(appearances);
    }

    @Override
    public void deleteAppearanceById(Long appearanceId) {
        appearanceRepository.deleteById(appearanceId);
    }

    @Override
    public Reason createReasonForQualityProblem(Long problemId, Reason reason) {
        Reason newReason = reasonRepository.save(reason);
        Optional<QualityProblem> qualityProblem = qualityProblemRepository.findById(problemId);
        if(qualityProblem.isPresent()){
            QualityProblem problem = qualityProblem.get();
            Set<Reason> reasons = problem.getReasons();
            reasons.add(newReason);
            problem.setReasons(reasons);
            qualityProblemRepository.save(problem);
            return newReason;
        }
        return null;
    }

    @Override
    public Reason updateReason(Reason reason) {
        Reason newReason = reasonRepository.save(reason);
        return newReason;
    }

    @Override
    public List<Reason> getReasonForQualityProblem(Long problemId) {
        List<Reason> reasons = reasonRepository.findReasonsByProblemId(problemId);
        return reasons;
    }

    @Override
    public void deleteReasonForQualityProblem(Long problemId) {
        List<Reason> reasons = reasonRepository.findReasonsByProblemId(problemId);
        reasonRepository.deleteAll(reasons);
    }

    @Override
    public void deleteReasonById(Long reasonId) {
        reasonRepository.deleteById(reasonId);
    }

    @Override
    public Solution createSolutionForQualityProblem(Long problemId, Solution solution) {
        Solution newSolution = solutionRepository.save(solution);
        Optional<QualityProblem> qualityProblem = qualityProblemRepository.findById(problemId);
        if(qualityProblem.isPresent()) {
            QualityProblem problem = qualityProblem.get();
            Set<Solution> solutions = problem.getSolutions();
            solutions.add(newSolution);
            problem.setSolutions(solutions);
            qualityProblemRepository.save(problem);
            return newSolution;
        }
        return null;
    }

    @Override
    public Solution updateSolution(Solution solution) {
        Solution newSolution = solutionRepository.save(solution);
        return newSolution;
    }

    @Override
    public List<Solution> getSolutionForQualityProblem(Long problemId) {
        List<Solution> solutions = solutionRepository.findByProblemId(problemId);
        return solutions;
    }

    @Override
    public void deleteSolutionForQualityProblem(Long problemId) {
        List<Solution> solutions = solutionRepository.findByProblemId(problemId);
        solutionRepository.deleteAll(solutions);
    }

    @Override
    public void deleteSolutionById(Long solutionId) {
        solutionRepository.deleteById(solutionId);
    }
}
