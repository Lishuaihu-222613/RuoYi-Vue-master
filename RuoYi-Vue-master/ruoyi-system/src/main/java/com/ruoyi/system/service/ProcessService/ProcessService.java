package com.ruoyi.system.service.ProcessService;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProcessService {

    List<ProcessElement> getAllAssemblyProcess();

    List<ProcessElement> getSubElementsById(Long elementId );

    List<ProcessElement> getBeforeElementsById(Long elementId );

    List<ProcessElement> getAfterElementsById(Long elementId );

    List<ProcessElement> getAndElementsById(Long elementId );

    List<ProcessElement> getOrElementsById(Long elementId );

    List<ProcessElement> getProcessByRelatedId(Long elementId );


    List<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel);

    Page<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel, Pageable pageable);

    Page<ProcessElement> getElementsByParams(Example<ProcessElement> example, Pageable pageable);

    void deleteElements(Long[] elements);

    ProcessElement getElementById(Long elementId);

    ProcessElement getSingleElementById(Long elementId);

    ProcessElement getParentElementById(Long elementId);

    List<ProcessElement> getElementsByStructure(Long structureId);

    List<ProcessElement> getElementsByStructureAndLabel(String label,Long structureId);

    List<ProcessElement> getTypicalProcessByLabel(String label);

    List<ProcessElement> getTypicalSequenceByLabel(String label);

    List<ProcessElement> getTypicalStepByLabel(String label);

    List<ProcessElement> getTypicalElementByLabel(String label);

    List<ProcessElement> getSimilarProcessByLabel(String label, Example<ProcessElement> example);

    List<ProcessElement> getSimilarSequenceByLabel(String label,Example<ProcessElement> example);

    List<ProcessElement> getSimilarStepByLabel(String label,Example<ProcessElement> example);

    List<ProcessElement> getSimilarElement(Example<ProcessElement> example);

    ProcessElement createElementForParent(ElementForParentProcess EP);

    ProcessElement createElement(ProcessElement processElement);

    ProcessElement updateElementForParent(ElementForParentProcess EP);

    ProcessElement updateElement(ProcessElement processElement);

    void modifyRelations(RelationsVoForProcessElement relationsVoForElement);

    void modifyBeforeRelations(ElementRelations vo);

    void modifyAfterRelations(ElementRelations vo);

    void modifyAndRelations(ElementRelations vo);

    void modifyOrRelations(ElementRelations vo);

    void updateRelation(OtherRelations vo);

    void modifyRelatedProcess(RelatedProcessVo vo);
}
