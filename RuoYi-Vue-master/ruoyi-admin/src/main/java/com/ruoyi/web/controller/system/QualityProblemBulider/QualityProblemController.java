package com.ruoyi.web.controller.system.QualityProblemBulider;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Appearance;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.QualityProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Reason;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.Solution;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo.AppearanceForProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo.ProblemQueryVo;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo.ReasonForProblem;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem.vo.SolutionForProblem;
import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import com.ruoyi.system.service.KnowledgeService.QualityProblem.QualityProblemService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/qualityProblem")
public class QualityProblemController extends BaseController {
    
    @Resource
    private QualityProblemService qualityProblemService;

    @ResponseBody
    @PostMapping("/createProblem")
    public R<QualityProblem> createProblem(@RequestBody QualityProblem problem){
        try{
            QualityProblem newProblem = qualityProblemService.createQualityProblem(problem);
            System.out.println(newProblem);
            return R.success(newProblem);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProblem")
    public R<QualityProblem> updateProblem(@RequestBody QualityProblem problem){
        try{
            QualityProblem newProblem = qualityProblemService.updateQualityProblem(problem);
            System.out.println(newProblem);
            return R.success(newProblem);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllProblems")
    public R<Page<QualityProblem>> getAllProblems(@RequestBody ProblemQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<QualityProblem> problems = qualityProblemService.getAllQualityProblems(pageable);
            System.out.println(problems);
            return R.success(problems);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllProblemsByLabel")
    public R<Page<QualityProblem>> getAllProblemsByLabel(@RequestBody ProblemQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<QualityProblem> problems = qualityProblemService.getQualityProblemsByLabel(params.getDynamicLabel(),pageable);
            System.out.println(problems);
            return R.success(problems);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllProblemsByParams")
    public R<Page<QualityProblem>> getAllProblemsByParams(@RequestBody ProblemQueryVo params){
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("problemName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("problemDescription",ExampleMatcher.GenericPropertyMatcher::contains);
            QualityProblem qualityProblem = new QualityProblem();
            qualityProblem.setProblemName(params.getOriginProblem().getProblemName());
            qualityProblem.setProblemDescription(params.getOriginProblem().getProblemDescription());
            Example<QualityProblem> example = Example.of(qualityProblem,matcher);
            Page<QualityProblem> problems = qualityProblemService.getQualityProblemsByParams(example,pageable);
            System.out.println(problems);
            return R.success(problems);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProblemsByName/{problemName}")
    public R<List<QualityProblem>> getProblemsByName(@PathVariable String problemName){
        try{
            List<QualityProblem> problems = qualityProblemService.getQualityProblemsByName(problemName);
            System.out.println(problems);
            return R.success(problems);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProblemById/{problemId}")
    public R<QualityProblem> getProblemById(@PathVariable Long problemId){
        try{
            QualityProblem problem = qualityProblemService.getQualityProblemById(problemId);
            System.out.println(problem);
            return R.success(problem);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteProblem/{problemId}")
    public R<String> deleteProblem(@PathVariable Long problemId){
        try{
            qualityProblemService.deleteQualityProblem(problemId);
            return R.success("删除"+problemId+"的质量问题");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createAppearance")
    public R<Appearance> createAppearance(@RequestBody AppearanceForProblem AP){
        try{
            Appearance appearance = qualityProblemService.createAppearanceForQualityProblem(AP.getProblemId(), AP.getAppearance());
            System.out.println(appearance);
            return R.success(appearance);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateAppearance")
    public R<Appearance> updateAppearance(@RequestBody Appearance appearance){
        try{
            Appearance newAppearance = qualityProblemService.updateAppearance(appearance);
            System.out.println(newAppearance);
            return R.success(newAppearance);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAppearanceForProblem/{problemId}")
    public R<List<Appearance>> getAppearanceForProblem(@PathVariable Long problemId){
        try{
            List<Appearance> appearances = qualityProblemService.getAppearanceForQualityProblem(problemId);
            System.out.println(appearances);
            return R.success(appearances);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteAppearanceForProblem/{problemId}")
    public R<String> deleteAppearanceForProblem(@PathVariable Long problemId){
        try{
            qualityProblemService.deleteAppearanceForQualityProblem(problemId);
            return R.success("删除质量问题"+problemId+"关联的所有外观节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteAppearance/{appearanceId}")
    public R<String> deleteAppearance(@PathVariable Long appearanceId){
        try{
            qualityProblemService.deleteAppearanceById(appearanceId);
            return R.success("删除"+appearanceId+"外观节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createReason")
    public R<Reason> createReason(@RequestBody ReasonForProblem RP){
        try{
            Reason Reason = qualityProblemService.createReasonForQualityProblem(RP.getProblemId(), RP.getReason());
            System.out.println(Reason);
            return R.success(Reason);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateReason")
    public R<Reason> updateReason(@RequestBody Reason Reason){
        try{
            Reason newReason = qualityProblemService.updateReason(Reason);
            System.out.println(newReason);
            return R.success(newReason);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getReasonForProblem/{problemId}")
    public R<List<Reason>> getReasonForProblem(@PathVariable Long problemId){
        try{
            List<Reason> Reasons = qualityProblemService.getReasonForQualityProblem(problemId);
            System.out.println(Reasons);
            return R.success(Reasons);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteReasonForProblem/{problemId}")
    public R<String> deleteReasonForProblem(@PathVariable Long problemId){
        try{
            qualityProblemService.deleteReasonForQualityProblem(problemId);
            return R.success("删除质量问题"+problemId+"关联的所有原因节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteReason/{reasonId}")
    public R<String> deleteReason(@PathVariable Long reasonId){
        try{
            qualityProblemService.deleteReasonById(reasonId);
            return R.success("删除"+reasonId+"原因节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSolution")
    public R<Solution> createSolution(@RequestBody SolutionForProblem SP){
        try{
            Solution Solution = qualityProblemService.createSolutionForQualityProblem(SP.getProblemId(), SP.getSolution());
            System.out.println(Solution);
            return R.success(Solution);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateSolution")
    public R<Solution> updateSolution(@RequestBody Solution Solution){
        try{
            Solution newSolution = qualityProblemService.updateSolution(Solution);
            System.out.println(newSolution);
            return R.success(newSolution);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSolutionForProblem/{problemId}")
    public R<List<Solution>> getSolutionForProblem(@PathVariable Long problemId){
        try{
            List<Solution> Solutions = qualityProblemService.getSolutionForQualityProblem(problemId);
            System.out.println(Solutions);
            return R.success(Solutions);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSolutionForProblem/{problemId}")
    public R<String> deleteSolutionForProblem(@PathVariable Long problemId){
        try{
            qualityProblemService.deleteSolutionForQualityProblem(problemId);
            return R.success("删除质量问题"+problemId+"关联的所有解决方法节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSolution/{solutionId}")
    public R<String> deleteSolution(@PathVariable Long solutionId){
        try{
            qualityProblemService.deleteSolutionById(solutionId);
            return R.success("删除"+solutionId+"解决方法节点");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
