package com.ruoyi.system.service.ProcessService;

import com.ruoyi.system.domain.AssemblyPojo.Process.Interface.ProcessInterface;
import com.ruoyi.system.domain.AssemblyPojo.Process.Process;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Normal;
import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.RelationAndElement;

import java.util.List;
import java.util.Set;

public interface ProcessService {

    /**
     * 获得所有装药工艺规程
     *
     * @return
     */
    List<ProcessInterface> getAllProcess();

    /**
     * 根据Id获得工艺规程
     *
     * @return
     */
    Process getProcessById(Long processId);

    /**
     * 根据Id获得工艺规程接口
     * @param processId
     * @return
     */
    ProcessInterface getProcessInterfaceById(Long processId);

    /**
     * 根据名称获得工艺规程
     * @param processName
     * @return
     */
    List<ProcessInterface> getProcessByName(String processName);

    /**
     * 创建工艺规程
     * @param process
     * @return
     */
    Process createProcess(Process process);

    /**
     * 修改工艺规程基本信息
     * @param process
     * @return
     */
    Process updateProcess(Process process);

    /**
     * 根据Id删除工艺规程
     * @param processId
     */
    void deleteProcessById(Long processId);

    /**
     * 获得全部工序
     * @return
     */
    List<Sequence> getAllSequences();

    /**
     * 根据工艺规程Id获得其下关联工序
     * @param processId
     * @return
     */
    List<Sequence> getSequencesByProcessId(Long processId);

    /**
     * 根据名称获得工序
     * @param sequenceName
     * @return
     */
    List<Sequence> getSequencesByName(String sequenceName);

    /**
     * 根据Id获得工序
     * @param sequenceId
     * @return
     */
    Sequence getSequenceById(Long sequenceId);

    /**
     * 创建工序
     * @param sequence
     * @return
     */
    <T extends Sequence> T createSequence(T sequence);

    /**
     * 修改工序基本信息
     * @param sequence
     * @return
     */
    <T extends Sequence> T updateSequence(T sequence);

    /**
     * 根据Id删除工序
     * @param sequenceId
     */
    void deleteSequenceById(Long sequenceId);

    /**
     * 向工艺规程添加工序
     * @param processId
     * @param sequence
     * @return
     */
    <T extends Sequence> T addSequenceForProcess(Long processId, T sequence);

    /**
     * 向工艺规程移除工序
     * @param processId
     * @param sequenceId
     */
    void removeSequenceForProcess(Long processId, Long sequenceId);

    /**
     * 为工序添加前项工序
     * @param sequenceId
     * @param beforeSequence
     * @return
     */
    <T extends Sequence> T addBeforeSequence(Long sequenceId, T beforeSequence);

    /**
     * 为工序移除前项工序
     * @param sequenceId
     * @param beforeSequenceId
     * @return
     */
    Sequence removeBeforeSequence(Long sequenceId, Long beforeSequenceId);

    /**
     * 修改前项工序关系
     * @param sequenceId
     * @param beforeSequenceId
     * @return
     */
    Sequence modifyBeforeSequence(Long sequenceId, List<Long> beforeSequenceId);

    /**
     * 为工序添加后项工序
     * @param sequenceId
     * @param afterSequence
     * @return
     */
    Sequence addAfterSequence(Long sequenceId, Sequence afterSequence);

    /**
     * 为工序移除后项工序
     * @param sequenceId
     * @param afterSequenceId
     * @return
     */
    Sequence removeAfterSequence(Long sequenceId,Long afterSequenceId);

    /**
     * 修改后项工序关系
     * @param sequenceId
     * @param AfterSequenceIds
     * @return
     */
    Sequence modifyAfterSequence(Long sequenceId, List<Long> AfterSequenceIds);

    /**
     * 为工序添加与工序
     * @param sequenceId
     * @param andSequence
     * @return
     */
    Sequence addAndSequence(Long sequenceId, Sequence andSequence);

    /**
     * 为工序移除工序与
     * @param sequenceId
     * @param andSequenceId
     * @return
     */
    Sequence removeAndSequence(Long sequenceId,Long andSequenceId);

    /**
     * 为工序修改工序与关系
     * @param sequenceId
     * @param sequenceIds
     * @return
     */
    Sequence modifyAndSequence(Long sequenceId,List<Long> sequenceIds);

    /**
     * 为工序添加或工序
     * @param sequenceId
     * @param orSequence
     * @return
     */
    Sequence addOrSequence(Long sequenceId, Sequence orSequence);

    /**
     * 为工序移除或工序
     * @param sequenceId
     * @param orSequenceId
     * @return
     */
    Sequence removeOrSequence(Long sequenceId,Long orSequenceId);

    /**
     * 修改工序或关系
     * @param sequenceId
     * @param sequenceIds
     * @return
     */
    Sequence modifyOrSequence(Long sequenceId,List<Long> sequenceIds);

    /**
     * 为工序修改使用设备
     * @param sequenceId
     * @param resourceIds
     * @return
     */
    Sequence modifyEquipmentForSequence(Long sequenceId, List<Long> resourceIds);

    /**
     * 为工序修改使用模具
     * @param sequenceId
     * @param resourceIds
     * @return
     */
    Sequence modifyMouldToolForSequence(Long sequenceId, List<Long> resourceIds);

    /**
     * 为工序修改使用量具
     * @param sequenceId
     * @param resourceIds
     * @return
     */
    Sequence modifyMeasuringToolForSequence(Long sequenceId, List<Long> resourceIds);

    /**
     * 为工序修改专用工装
     * @param sequenceId
     * @param resourceIds
     * @return
     */
    Sequence modifySpecialToolForSequence(Long sequenceId, List<Long> resourceIds);

    /**
     * 为工序修改使用吊具
     * @param sequenceId
     * @param resourceIds
     * @return
     */
    Sequence modifySpreaderToolForSequence(Long sequenceId, List<Long> resourceIds);

    /**
     * 向工序添加工步
     * @param sequenceId
     * @param step
     * @return
     */
    Step addStepForSequence(Long sequenceId, Step step);

    /**
     * 向工序移除工步
     * @param sequenceId
     * @param stepId
     * @return
     */
    void removeStepForSequence(Long sequenceId, Long stepId);

    /**
     * 创建工步
     * @param step
     * @return
     */
    Step createStep(Step step);

    /**
     * 更新工步基本信息
     * @param step
     * @return
     */
    Step updateStep(Step step);

    /**
     * 根据Id获得工步
     * @param stepId
     * @return
     */
    Step getStepById(Long stepId);

    /**
     * 根据工序获得工步
     * @param sequenceId
     * @return
     */
    List<Step> getStepBySequence(Long sequenceId);

    /**
     * 根据名称获得工步
     * @param stepName
     * @return
     */
    List<Step> getStepByName(String stepName);

    /**
     * 删除工步
     * @param stepId
     */
    void deleteStepById(Long stepId);

    /**
     * 修改工步关联资源
     * @param stepId
     * @param resourceIds
     * @return
     */
    Step modifyResourceForStep(Long stepId, List<Long> resourceIds);

    /**
     * 创建前道工步
     * @param stepId
     * @param beforeStep
     * @return
     */
    Step addBeforeStep(Long stepId, Step beforeStep);

    /**
     * 移除前道工步
     * @param stepId
     * @param beforeStepId
     * @return
     */
    Step removeBeforeStep(Long stepId, Long beforeStepId);

    /**
     * 修改前道工步关系
     * @param stepId
     * @param stepIds
     * @return
     */
    Step modifyBeforeStep(Long stepId, Set<Long> stepIds);

    /**
     * 创建后道工步
     * @param stepId
     * @param afterStep
     * @return
     */
    Step addAfterStep(Long stepId, Step afterStep);

    /**
     * 移除后道工步
     * @param stepId
     * @param afterStepId
     * @return
     */
    Step removeAfterStep(Long stepId, Long afterStepId);

    /**
     * 修改后道工步关系
     * @param stepId
     * @param stepIds
     * @return
     */
    Step modifyAfterStep(Long stepId, Set<Long> stepIds);

    /**
     * 创建工步与
     * @param stepId
     * @param andStep
     * @return
     */
    Step addAndStep(Long stepId, Step andStep);

    /**
     * 移除工步与
     * @param stepId
     * @param andStepId
     * @return
     */
    Step removeAndStep(Long stepId, Long andStepId);

    /**
     * 修改工步与关系
     * @param stepId
     * @param stepIds
     * @return
     */
    Step modifyAndStep(Long stepId, Set<Long> stepIds);

    /**
     * 创建工步或
     * @param stepId
     * @param orStep
     * @return
     */
    Step addOrStep(Long stepId, Step orStep);

    /**
     * 移除工步或
     * @param stepId
     * @param orStepId
     * @return
     */
    Step removeOrStep(Long stepId, Long orStepId);

    /**
     * 修改工步或关系
     * @param stepId
     * @param stepIds
     * @return
     */
    Step modifyOrStep(Long stepId, Set<Long> stepIds);

    /**
     * 创建子工步
     * @param stepId
     * @param subStep
     * @return
     */
    Step addSubStep(Long stepId, Step subStep);

    /**
     * 移除子工步
     * @param stepId
     * @param subStepId
     * @return
     */
    Step removeSubStep(Long stepId, Long subStepId);

    /**
     * 修改子工步关系
     * @param stepId
     * @param stepIds
     * @return
     */
    Step modifySubStep(Long stepId, Set<Long> stepIds);

    ProcessElement createElement(ProcessElement element);

    ProcessElement createElementForParent(RelationAndElement RE);

    ProcessElement updateElement(ProcessElement element);

    ProcessElement deleteElement(List<Long> elementIds);

    Long getParentRelation(Long elementId);

    ProcessElement modifyParentRelation(Long elementId,Long parentId);

    List<Long> getBeforeRelation(Long elementId);

    ProcessElement modifyBeforeRelation(Long elementId,Set<Long> elementIds);

    List<Long> getAfterRelation(Long elementId);

    ProcessElement modifyAfterRelation(Long elementId,Set<Long> elementIds);

    List<Long> getAndRelation(Long elementId);

    ProcessElement modifyAndRelation(Long elementId,Set<Long> elementIds);

    List<Long> getOrRelation(Long elementId);

    ProcessElement modifyOrRelation(Long elementId,Set<Long> elementIds);

    List<Long> getUsedResources(Long elementId);

    ProcessElement modifyUsedResources(Long elementId,Set<Long> elementIds);

    List<Long> getAssociatedStructures(Long elementId);

    ProcessElement modifyAssociatedStructures(Long elementId,Set<Long> elementIds);

    List<Long> getAssociatedFiles(Long elementId);

    ProcessElement modifyAssociatedFiles(Long elementId,Set<Long> elementIds);

}
