package com.ruoyi.web.controller.system.ProcessBulider;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ProcessInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Normal;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.*;
import com.ruoyi.system.service.ProcessService.ProcessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController extends BaseController {

    @Resource
    private ProcessService processService;

    @ResponseBody
    @GetMapping("/getAllProcess")
    public R<List<ProcessInterface>> getAllProcess(){
        try{
            List<ProcessInterface> processes = processService.getAllProcess();
            System.out.println(processes);
            return R.success(processes);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getProcessById/{processId}")
    public R<ProcessInterface> getProcessById(@PathVariable Long processId){
        try{
            ProcessInterface process = processService.getProcessInterfaceById(processId);
            System.out.println(process);
            return R.success(process);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping ("/getProcessByName/{processName}")
    public R<List<ProcessInterface>> getProcessByName(@PathVariable String processName){
        try{
            List<ProcessInterface> processes = processService.getProcessByName(processName);
            System.out.println(processes);
            return R.success(processes);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createProcess")
    public R<Process> createProcess(@RequestBody Process process){
        try{
            Process newProcess = processService.createProcess(process);
            System.out.println(newProcess);
            return R.success(newProcess);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProcess")
    public R<Process> updateProcess(@RequestBody Process process){
        try{
            Process newProcess = processService.updateProcess(process);
            System.out.println(newProcess);
            return R.success(newProcess);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteProcess/{processId}")
    public R<String> deleteProcess(@PathVariable Long processId){
        try{
            processService.deleteProcessById(processId);
            return R.success("成功删除工艺规程");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };


    @ResponseBody
    @GetMapping("/getSequenceByProcess/{processId}")
    public R<List<Sequence>> getSequenceByProcess(@PathVariable Long processId){
        try{
            List<Sequence> sequences = processService.getSequencesByProcessId(processId);
            System.out.println(sequences);
            return R.success(sequences);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSequenceById/{sequenceId}")
    public R<Sequence> getSequenceById(@PathVariable Long sequenceId){
        try{
            Sequence sequence = processService.getSequenceById(sequenceId);
            System.out.println(sequence);
            return R.success(sequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSequenceByName/{sequenceName}")
    public R<List<Sequence>> getSequenceByName(@PathVariable String sequenceName){
        try{
            List<Sequence> sequences = processService.getSequencesByName(sequenceName);
            System.out.println(sequences);
            return R.success(sequences);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSequence")
    public R<Sequence> createSequence(@RequestBody Sequence sequence){
        try{
            Sequence newSequence = processService.createSequence(sequence);
            System.out.println(newSequence);
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };



    @ResponseBody
    @PostMapping("/updateSequence")
    public R<Sequence> updateSequence(@RequestBody Normal sequence){
        try{
            Sequence newSequence = processService.updateSequence(sequence);
            System.out.println(newSequence);
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSequence/{sequenceId}")
    public R<String> deleteSequenceById(@PathVariable Long sequenceId){
        try{
            processService.deleteSequenceById(sequenceId);
            return R.success("删除工序成功！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addSequenceForProcess")
    public R<Sequence> addSequenceForProcess(@RequestBody ProcessIdAndSequence pAnds){
        try{
            Sequence s = processService.addSequenceForProcess(pAnds.getProcessId(), pAnds.getSequence());
            System.out.println(s);
            return R.success(s);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSequence/{processId}/{sequenceId}")
    public R<String> removeSequenceForProcess(@PathVariable Long processId , @PathVariable Long sequenceId){
        try{
            processService.removeSequenceForProcess(processId,sequenceId);
            return R.success("为工艺"+processId+"解除与工序"+sequenceId+"的包含关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addBeforeSequence")
    public R<Process> addBeforeSequence(@RequestBody SequenceAndSequence sAnds){
        try{
            Sequence newSequence = processService.addBeforeSequence(sAnds.getSequenceId(), sAnds.getSequence());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeBeforeSequence/{sequenceId}/{beforeSequenceId}")
    public R<String> removeBeforeSequence(@PathVariable Long sequenceId , @PathVariable Long beforeSequenceId){
        try{
            processService.removeBeforeSequence(sequenceId,beforeSequenceId);
            return R.success("为工序"+sequenceId+"解除与工序"+beforeSequenceId+"的前道工序关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyBeforeSequence")
    public R<Sequence> modifyBeforeSequence(@RequestBody Sequences ss){
        try{
            Sequence newSequence = processService.modifyBeforeSequence(ss.getSequenceId(), ss.getSequencesList());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addAfterSequence")
    public R<Process> addAfterSequence(@RequestBody SequenceAndSequence sAnds){
        try{
            Sequence newSequence = processService.addAfterSequence(sAnds.getSequenceId(), sAnds.getSequence());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSequence/{sequenceId}/{AfterSequenceId}")
    public R<String> removeAfterSequence(@PathVariable Long sequenceId , @PathVariable Long AfterSequenceId){
        try{
            processService.removeAfterSequence(sequenceId,AfterSequenceId);
            return R.success("为工序"+sequenceId+"解除与工序"+AfterSequenceId+"的后道工序关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyAfterSequence")
    public R<Sequence> modifyAfterSequence(@RequestBody Sequences ss){
        try{
            Sequence newSequence = processService.modifyAfterSequence(ss.getSequenceId(), ss.getSequencesList());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addAndSequence")
    public R<Process> addAndSequence(@RequestBody SequenceAndSequence sAnds){
        try{
            Sequence newSequence = processService.addAndSequence(sAnds.getSequenceId(), sAnds.getSequence());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSequence/{sequenceId}/{AndSequenceId}")
    public R<String> removeAndSequence(@PathVariable Long sequenceId , @PathVariable Long AndSequenceId){
        try{
            processService.removeAndSequence(sequenceId,AndSequenceId);
            return R.success("为工序"+sequenceId+"解除与工序"+AndSequenceId+"的后道工序关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyAndSequence")
    public R<Sequence> modifyAndSequence(@RequestBody Sequences ss){
        try{
            Sequence newSequence = processService.modifyAndSequence(ss.getSequenceId(), ss.getSequencesList());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addOrSequence")
    public R<Process> addOrSequence(@RequestBody SequenceAndSequence sAnds){
        try{
            Sequence newSequence = processService.addOrSequence(sAnds.getSequenceId(), sAnds.getSequence());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteSequence/{sequenceId}/{OrSequenceId}")
    public R<String> removeOrSequence(@PathVariable Long sequenceId , @PathVariable Long OrSequenceId){
        try{
            processService.removeOrSequence(sequenceId,OrSequenceId);
            return R.success("为工序"+sequenceId+"解除与工序"+OrSequenceId+"的后道工序关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyOrSequence")
    public R<Sequence> modifyOrSequence(@RequestBody Sequences ss){
        try{
            Sequence newSequence = processService.modifyOrSequence(ss.getSequenceId(), ss.getSequencesList());
            return R.success(newSequence);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addStepForSequence")
    public R<Step> addStepForSequence(@RequestBody StepAndSequence sAnds){
        try{
            Step newStep = processService.addStepForSequence(sAnds.getSequenceId(), sAnds.getStep());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeStepForSequence/{sequenceId}/{stepId}")
    public R<String> removeStepForSequence(@PathVariable Long sequenceId, @PathVariable Long stepId){
        try{
            processService.removeStepForSequence(sequenceId,stepId);
            return R.success("解除工序"+sequenceId+"与工步"+stepId+"的包含关系！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createStep")
    public R<Step> createStep(@RequestBody Step step){
        try{
            Step newStep = processService.createStep(step);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateStep")
    public R<Step> updateStep(@RequestBody Step step){
        try{
            Step newStep = processService.updateStep(step);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStepById/{stepId}")
    public R<Step> getStepById( @PathVariable Long stepId){
        try{
            Step step = processService.getStepById(stepId);
            return R.success(step);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStepBySequence/{sequenceId}")
    public R<List<Step>> getStepBySequence( @PathVariable Long sequenceId){
        try{
            List<Step> steps = processService.getStepBySequence(sequenceId);
            return R.success(steps);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStepByName/{stepName}")
    public R<List<Step>> getStepByName(@PathVariable String stepName){
        try{
            List<Step> steps = processService.getStepByName(stepName);
            return R.success(steps);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteStepId/{stepId}")
    public R<String> deleteStepById(@PathVariable Long stepId){
        try{
            processService.deleteStepById(stepId);
            return R.success("删除"+stepId+"工步！");
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addBeforeStep")
    public R<Step> addBeforeStep(@RequestBody StepAndStep sAnds){
        try{
            Step newStep = processService.addBeforeStep(sAnds.getStepId(), sAnds.getStep());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeBeforeStep/{stepId}/{beforeStepId}")
    public R<Step> removeBeforeStep(@PathVariable Long stepId,@PathVariable Long beforeStepId ){
        try{
            Step newStep = processService.removeBeforeStep(stepId, beforeStepId);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyBeforeStep")
    public R<Step> modifyBeforeStep(@RequestBody Steps steps){
        try{
            Step newStep = processService.modifyBeforeStep(steps.getStepId(), steps.getStepIds());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addAfterStep")
    public R<Step> addAfterStep(@RequestBody StepAndStep sAnds){
        try{
            Step newStep = processService.addAfterStep(sAnds.getStepId(), sAnds.getStep());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeAfterStep/{stepId}/{AfterStepId}")
    public R<Step> removeAfterStep(@PathVariable Long stepId,@PathVariable Long AfterStepId ){
        try{
            Step newStep = processService.removeAfterStep(stepId, AfterStepId);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyAfterStep")
    public R<Step> modifyAfterStep(@RequestBody Steps steps){
        try{
            Step newStep = processService.modifyAfterStep(steps.getStepId(), steps.getStepIds());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addOrStep")
    public R<Step> addOrStep(@RequestBody StepAndStep sOrs){
        try{
            Step newStep = processService.addOrStep(sOrs.getStepId(), sOrs.getStep());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeOrStep/{stepId}/{OrStepId}")
    public R<Step> removeOrStep(@PathVariable Long stepId,@PathVariable Long OrStepId ){
        try{
            Step newStep = processService.removeOrStep(stepId, OrStepId);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifyOrStep")
    public R<Step> modifyOrStep(@RequestBody Steps steps){
        try{
            Step newStep = processService.modifyOrStep(steps.getStepId(), steps.getStepIds());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/addSubStep")
    public R<Step> addSubStep(@RequestBody StepAndStep sSubs){
        try{
            Step newStep = processService.addSubStep(sSubs.getStepId(), sSubs.getStep());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/removeSubStep/{stepId}/{SubStepId}")
    public R<Step> removeSubStep(@PathVariable Long stepId,@PathVariable Long SubStepId ){
        try{
            Step newStep = processService.removeSubStep(stepId, SubStepId);
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/modifySubStep")
    public R<Step> modifySubStep(@RequestBody Steps steps){
        try{
            Step newStep = processService.modifySubStep(steps.getStepId(), steps.getStepIds());
            return R.success(newStep);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    
    
}
