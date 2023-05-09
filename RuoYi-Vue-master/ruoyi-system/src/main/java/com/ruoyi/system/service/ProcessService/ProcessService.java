package com.ruoyi.system.service.ProcessService;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.ElementForParentProcess;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.ElementRelations;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.OtherRelations;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.RelationsVoForProcessElement;
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

    List<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel);

    Page<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel, Pageable pageable);

    Page<ProcessElement> getElementsByParams(Example<ProcessElement> example, Pageable pageable);

    void deleteElements(Long[] elements);

    ProcessElement getElementById(Long elementId);

    ProcessElement getSingleElementById(Long elementId);

    ProcessElement getParentElementById(Long elementId);

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


}
