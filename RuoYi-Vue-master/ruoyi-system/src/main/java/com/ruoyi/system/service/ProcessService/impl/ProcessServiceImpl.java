package com.ruoyi.system.service.ProcessService.impl;

import com.ruoyi.system.Repository.ProcessRepository.ProcessElementRepository;
import com.ruoyi.system.Repository.ProcessRepository.ProcessRepository;
import com.ruoyi.system.Repository.ProcessRepository.SequenceRepository;
import com.ruoyi.system.Repository.ProcessRepository.StepRepository;
import com.ruoyi.system.Repository.ResourceRepository.ResourceRepository;
import com.ruoyi.system.Repository.StructureRepository.StructureElementRepository;
import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ProcessInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.RelationAndElement;
import com.ruoyi.system.service.ProcessService.ProcessService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessRepository processRepository;

    @Resource
    private SequenceRepository sequenceRepository;

    @Resource
    private StepRepository stepRepository;

    @Resource
    private ProcessElementRepository processElementRepository;

    @Resource
    private ResourceRepository resourceRepository;

    @Resource
    private StructureElementRepository structureElementRepository;


    @Override
    public List<ProcessInterface> getAllProcess() {
        return processRepository.findAllProcessInterfaces();
    }

    @Override
    public Process getProcessById(Long processId) {
        Optional<Process> process = processRepository.findById(processId);
        if(process.isPresent()) {
            return process.get();
        }
        return null;
    }

    @Override
    public ProcessInterface getProcessInterfaceById(Long processId) {

        Optional<ProcessInterface> process = processRepository.findInterfaceById(processId);
        if(process.isPresent()){
            return process.get();
        }
        return null;
    }

    @Override
    public List<ProcessInterface> getProcessByName(String processName) {

        Collection<ProcessInterface> processes = processRepository.findProcessByProcessName(processName);
        ArrayList<ProcessInterface> processList = new ArrayList<>(processes);
        return processList;
    }

    @Override
    public Process createProcess(Process process) {
        return processRepository.save(process);
    }

    @Override
    public Process updateProcess(Process process) {
        Optional<Process> oProcess = processRepository.findById(process.getProcessId());
        if(oProcess.isPresent()) {
            Process oldProcess = oProcess.get();
            oldProcess.setProcessName(process.getProcessName());
            oldProcess.setProcessDescription(process.getProcessDescription());
            Process updateProcess = processRepository.save(oldProcess);
            return updateProcess;
        }
        return null;
    }

    @Override
    public void deleteProcessById(Long processId) {
        processRepository.deleteById(processId);
    }

    @Override
    public List<Sequence> getAllSequences() {
        return sequenceRepository.findAll();
    }

    @Override
    public List<Sequence> getSequencesByProcessId(Long processId) {
        Collection<Sequence> sequences = sequenceRepository.findSequenceByProcessId(processId);
        ArrayList<Sequence> sequenceList = new ArrayList<>(sequences);
        return sequenceList;
    }

    @Override
    public List<Sequence> getSequencesByName(String sequenceName) {
        Collection<Sequence> sequences = sequenceRepository.findSequenceByName(sequenceName);
        ArrayList<Sequence> sequencesList = new ArrayList<>(sequences);
        return sequencesList;
    }

    @Override
    public Sequence getSequenceById(Long sequenceId) {
        Optional<Sequence> sequence = sequenceRepository.findById(sequenceId);
        if(sequence.isPresent()){
            return sequence.get();
        }
        return null;
    }

    @Override
    public <T extends Sequence> T createSequence(T sequence) {
        return sequenceRepository.save(sequence);
    }

    @Override
    public <T extends Sequence> T updateSequence(@NotNull T sequence) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequence.getSequenceId());
        if(oSequence.isPresent()){
            Sequence oldSequence = oSequence.get();
            oldSequence.setSequenceName(sequence.getSequenceName());
            oldSequence.setSequenceNumber(sequence.getSequenceNumber());
            oldSequence.setSequenceDescription(sequence.getSequenceDescription());
            oldSequence.setQuasiClosingHours(sequence.getQuasiClosingHours());
            oldSequence.setTaktTime(sequence.getTaktTime());
            oldSequence.setSequenceRemark(sequence.getSequenceRemark());
            T updateSequence = (T) sequenceRepository.save(oldSequence);
            return updateSequence;
        }
        return null;
    }

    @Override
    public void deleteSequenceById(Long sequenceId) {
        sequenceRepository.deleteById(sequenceId);
    }

    public <T extends Sequence> T addSequenceForProcess(Long processId, T sequence) {
        T newSequence = sequenceRepository.save(sequence);
        Optional<Process> oProcess = processRepository.findById(processId);
        if(oProcess.isPresent()){
            Process process = oProcess.get();
            Set<Sequence> subSequence = process.getSubSequence();
            subSequence.add(newSequence);
            process.setSubSequence(subSequence);
            processRepository.save(process);
            return newSequence;
        }
        return null;
    }

    @Override
    public void removeSequenceForProcess(Long processId, Long sequenceId) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if (oSequence.isPresent()){
            Optional<Process> oProcess = processRepository.findById(processId);
            if(oProcess.isPresent()){
                Process process = oProcess.get();
                Set<Sequence> subSequence = process.getSubSequence();
                subSequence.remove(oSequence.get());
                process.setSubSequence(subSequence);
                processRepository.save(process);
            }
        }
    }

    @Override
    public <T extends Sequence> T addBeforeSequence(Long sequenceId, T beforeSequence) {
        Sequence bSequence = sequenceRepository.save(beforeSequence);
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            Set<Sequence> beforeSequenceSet = sequence.getBeforeSequence();
            beforeSequenceSet.add(bSequence);
            sequence.setBeforeSequence(beforeSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return (T) newSequence;
        }
        return null;
    }

    @Override
    public Sequence removeBeforeSequence(Long sequenceId, Long beforeSequenceId) {
        Optional<Sequence> bSequence = sequenceRepository.findById(beforeSequenceId);
        if(bSequence.isPresent()) {
            Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
            if (oSequence.isPresent()) {
                Sequence sequence = oSequence.get();
                Set<Sequence> beforeSequenceSet = sequence.getBeforeSequence();
                beforeSequenceSet.remove(bSequence);
                sequence.setBeforeSequence(beforeSequenceSet);
                Sequence newSequence = sequenceRepository.save(sequence);
                return newSequence;
            }
        }
        return null;
    }

    @Override
    public Sequence modifyBeforeSequence(Long sequenceId, List<Long> beforeSequenceId) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            List<Sequence> newBeforeSequence = sequenceRepository.findAllById(beforeSequenceId);
            HashSet<Sequence> newBeforeSequenceSet = new HashSet<>(newBeforeSequence);
            sequence.setBeforeSequence(newBeforeSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence addAfterSequence(Long sequenceId, Sequence afterSequence) {
        Sequence aSequence = sequenceRepository.save(afterSequence);
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            Set<Sequence> afterSequenceSet = sequence.getAfterSequence();
            afterSequenceSet.add(aSequence);
            sequence.setAfterSequence(afterSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence removeAfterSequence(Long sequenceId, Long afterSequenceId) {
        Optional<Sequence> aSequence = sequenceRepository.findById(afterSequenceId);
        if(aSequence.isPresent()) {
            Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
            if (oSequence.isPresent()) {
                Sequence sequence = oSequence.get();
                Set<Sequence> afterSequenceSet = sequence.getAfterSequence();
                afterSequenceSet.remove(aSequence);
                sequence.setAfterSequence(afterSequenceSet);
                Sequence newSequence = sequenceRepository.save(sequence);
                return newSequence;
            }
        }
        return null;
    }

    @Override
    public Sequence modifyAfterSequence(Long sequenceId, List<Long> afterSequenceIds) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            List<Sequence> newAfterSequence = sequenceRepository.findAllById(afterSequenceIds);
            HashSet<Sequence> newAfterSequenceSet = new HashSet<>(newAfterSequence);
            sequence.setAfterSequence(newAfterSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence addAndSequence(Long sequenceId, Sequence andSequence) {
        Sequence aSequence = sequenceRepository.save(andSequence);
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            Set<Sequence> andSequenceSet = sequence.getAndSequence();
            andSequenceSet.add(aSequence);
            sequence.setAndSequence(andSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence removeAndSequence(Long sequenceId, Long andSequenceId) {
        Optional<Sequence> aSequence = sequenceRepository.findById(andSequenceId);
        if(aSequence.isPresent()) {
            Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
            if (oSequence.isPresent()) {
                Sequence sequence = oSequence.get();
                Set<Sequence> andSequenceSet = sequence.getAndSequence();
                andSequenceSet.remove(aSequence);
                sequence.setAndSequence(andSequenceSet);
                Sequence newSequence = sequenceRepository.save(sequence);
                return newSequence;
            }
        }
        return null;
    }

    @Override
    public Sequence modifyAndSequence(Long sequenceId, List<Long> sequenceIds) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            List<Sequence> newAndSequence = sequenceRepository.findAllById(sequenceIds);
            HashSet<Sequence> newAndSequenceSet = new HashSet<>(newAndSequence);
            sequence.setAndSequence(newAndSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence addOrSequence(Long sequenceId, Sequence orSequence) {
        Sequence rSequence = sequenceRepository.save(orSequence);
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            Set<Sequence> orSequenceSet = sequence.getOrSequence();
            orSequenceSet.add(rSequence);
            sequence.setOrSequence(orSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence removeOrSequence(Long sequenceId, Long orSequenceId) {
        Optional<Sequence> rSequence = sequenceRepository.findById(orSequenceId);
        if(rSequence.isPresent()) {
            Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
            if (oSequence.isPresent()) {
                Sequence sequence = oSequence.get();
                Set<Sequence> orSequenceSet = sequence.getOrSequence();
                orSequenceSet.remove(rSequence);
                sequence.setOrSequence(orSequenceSet);
                Sequence newSequence = sequenceRepository.save(sequence);
                return newSequence;
            }
        }
        return null;
    }

    @Override
    public Sequence modifyOrSequence(Long sequenceId, List<Long> sequenceIds) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Sequence sequence = oSequence.get();
            List<Sequence> newOrSequence = sequenceRepository.findAllById(sequenceIds);
            HashSet<Sequence> newOrSequenceSet = new HashSet<>(newOrSequence);
            sequence.setOrSequence(newOrSequenceSet);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newSequence;
        }
        return null;
    }

    @Override
    public Sequence modifyEquipmentForSequence(Long sequenceId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Sequence modifyMouldToolForSequence(Long sequenceId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Sequence modifyMeasuringToolForSequence(Long sequenceId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Sequence modifySpecialToolForSequence(Long sequenceId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Sequence modifySpreaderToolForSequence(Long sequenceId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Step addStepForSequence(Long sequenceId, Step step) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Step newStep = stepRepository.save(step);
            Sequence sequence = oSequence.get();
            Set<Step> associatedSteps = sequence.getAssociatedSteps();
            associatedSteps.add(newStep);
            sequence.setAssociatedSteps(associatedSteps);
            Sequence newSequence = sequenceRepository.save(sequence);
            return newStep;
        }
        return null;
    }

    @Override
    public void removeStepForSequence(Long sequenceId, Long stepId) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()) {
            Optional<Step> oStep = stepRepository.findById(stepId);
            if(oStep.isPresent()) {
                Step step = oStep.get();
                Sequence sequence = oSequence.get();
                Set<Step> associatedSteps = sequence.getAssociatedSteps();
                associatedSteps.remove(step);
                sequence.setAssociatedSteps(associatedSteps);
                Sequence newSequence = sequenceRepository.save(sequence);
            }
        }
    }

    @Override
    public Step createStep(Step step) {
        Step newStep = stepRepository.save(step);
        return newStep;
    }

    @Override
    public Step updateStep(Step step) {
        Optional<Step> oStep = stepRepository.findById(step.getStepId());
        if (oStep.isPresent()) {
            Step oldStep = oStep.get();
            oldStep.setStepName(step.getStepName());
            oldStep.setStepNumber(step.getStepNumber());
            oldStep.setStepDescription(step.getStepDescription());
            oldStep.setInspectRequirement(step.getInspectRequirement());
            oldStep.setStepRequirement(step.getStepRequirement());
            Step newStep = stepRepository.save(oldStep);
            return newStep;
        }
        return null;
    }

    @Override
    public Step getStepById(Long stepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if (oStep.isPresent()){
            Step step = oStep.get();
            return step;
        }
        return null;
    }

    @Override
    public List<Step> getStepBySequence(Long sequenceId) {
        Optional<Sequence> oSequence = sequenceRepository.findById(sequenceId);
        if(oSequence.isPresent()){
            Sequence sequence = oSequence.get();
            Set<Step> associatedSteps = sequence.getAssociatedSteps();
            ArrayList<Step> steps = new ArrayList<>(associatedSteps);
            return steps;
        }
        return null;
    }

    @Override
    public List<Step> getStepByName(String stepName) {
        Collection<Step> steps = stepRepository.findStepByName(stepName);
        ArrayList<Step> stepArrayList = new ArrayList<>(steps);
        return stepArrayList;
    }

    @Override
    public void deleteStepById(Long stepId) {
        stepRepository.deleteById(stepId);
    }

    @Override
    public Step modifyResourceForStep(Long stepId, List<Long> resourceIds) {
        return null;
    }

    @Override
    public Step addBeforeStep(Long stepId, Step beforeStep) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Step bStep = stepRepository.save(beforeStep);
            Step step = oStep.get();
            Set<Step> beforeStepSet = step.getBeforeStep();
            beforeStepSet.add(bStep);
            step.setBeforeStep(beforeStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step removeBeforeStep(Long stepId, Long beforeStepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Optional<Step> beforeStep = stepRepository.findById(beforeStepId);
            Step step = oStep.get();
            Set<Step> beforeStepSet = step.getBeforeStep();
            beforeStepSet.remove(beforeStep);
            step.setBeforeStep(beforeStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step modifyBeforeStep(Long stepId, Set<Long> stepIds) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            List<Step> beforeSteps = stepRepository.findAllById(stepIds);
            Step step = oStep.get();
            step.setBeforeStep(new HashSet<>(beforeSteps));
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step addAfterStep(Long stepId, Step afterStep) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Step aStep = stepRepository.save(afterStep);
            Step step = oStep.get();
            Set<Step> afterStepSet = step.getAfterStep();
            afterStepSet.add(aStep);
            step.setAfterStep(afterStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step removeAfterStep(Long stepId, Long afterStepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Optional<Step> afterStep = stepRepository.findById(afterStepId);
            Step step = oStep.get();
            Set<Step> afterStepSet = step.getAfterStep();
            afterStepSet.remove(afterStep);
            step.setAfterStep(afterStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step modifyAfterStep(Long stepId, Set<Long> stepIds) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            List<Step> afterSteps = stepRepository.findAllById(stepIds);
            Step step = oStep.get();
            step.setAfterStep(new HashSet<>(afterSteps));
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step addAndStep(Long stepId, Step andStep) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Step aStep = stepRepository.save(andStep);
            Step step = oStep.get();
            Set<Step> andStepSet = step.getAndStep();
            andStepSet.add(aStep);
            step.setAndStep(andStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step removeAndStep(Long stepId, Long andStepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Optional<Step> andStep = stepRepository.findById(andStepId);
            Step step = oStep.get();
            Set<Step> andStepSet = step.getAndStep();
            andStepSet.remove(andStep);
            step.setAndStep(andStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step modifyAndStep(Long stepId, Set<Long> stepIds) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            List<Step> andSteps = stepRepository.findAllById(stepIds);
            Step step = oStep.get();
            step.setAndStep(new HashSet<>(andSteps));
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step addOrStep(Long stepId, Step orStep) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Step rStep = stepRepository.save(orStep);
            Step step = oStep.get();
            Set<Step> orStepSet = step.getOrStep();
            orStepSet.add(rStep);
            step.setOrStep(orStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step removeOrStep(Long stepId, Long orStepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Optional<Step> rStep = stepRepository.findById(orStepId);
            Step step = oStep.get();
            Set<Step> orStepSet = step.getOrStep();
            orStepSet.remove(rStep);
            step.setBeforeStep(orStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step modifyOrStep(Long stepId, Set<Long> stepIds) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            List<Step> orSteps = stepRepository.findAllById(stepIds);
            Step step = oStep.get();
            step.setOrStep(new HashSet<>(orSteps));
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step addSubStep(Long stepId, Step subStep) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Step sub = stepRepository.save(subStep);
            Step step = oStep.get();
            Set<Step> subStepSet = step.getSubStep();
            subStepSet.add(sub);
            step.setSubStep(subStepSet);
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }

    @Override
    public Step removeSubStep(Long stepId, Long subStepId) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            Optional<Step> subStep = stepRepository.findById(subStepId);
            if(subStep.isPresent()){
                Step sub = subStep.get();
                Step step = oStep.get();
                Set<Step> subStepSet = step.getSubStep();
                subStepSet.remove(sub);
                step.setSubStep(subStepSet);
                Step newStep = stepRepository.save(step);
                return newStep;
            }
        }
        return null;
    }

    @Override
    public Step modifySubStep(Long stepId, Set<Long> stepIds) {
        Optional<Step> oStep = stepRepository.findById(stepId);
        if(oStep.isPresent()){
            List<Step> subs = stepRepository.findAllById(stepIds);
            Step step = oStep.get();
            step.setSubStep(new HashSet<>(subs));
            Step newStep = stepRepository.save(step);
            return newStep;
        }
        return null;
    }





    @Override
    public ProcessElement createElement(ProcessElement element) {
        return null;
    }

    @Override
    public ProcessElement createElementForParent(RelationAndElement RE) {
        return null;
    }

    @Override
    public ProcessElement updateElement(ProcessElement element) {
        return null;
    }

    @Override
    public ProcessElement deleteElement(List<Long> elementIds) {
        return null;
    }

    @Override
    public Long getParentRelation(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyParentRelation(Long elementId, Long parentId) {
        return null;
    }

    @Override
    public List<Long> getBeforeRelation(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyBeforeRelation(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getAfterRelation(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyAfterRelation(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getAndRelation(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyAndRelation(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getOrRelation(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyOrRelation(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getUsedResources(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyUsedResources(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getAssociatedStructures(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyAssociatedStructures(Long elementId, Set<Long> elementIds) {
        return null;
    }

    @Override
    public List<Long> getAssociatedFiles(Long elementId) {
        return null;
    }

    @Override
    public ProcessElement modifyAssociatedFiles(Long elementId, Set<Long> elementIds) {
        return null;
    }
}
