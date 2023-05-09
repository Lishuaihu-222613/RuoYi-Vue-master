package com.ruoyi.system.service.ProcessService;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.RelationAndElement;

import java.util.List;
import java.util.Set;

public interface ProcessElementService {

    ProcessElement createElement(ProcessElement element);

    ProcessElement createElementForParent(RelationAndElement RE);

    ProcessElement updateElement(ProcessElement element);

    ProcessElement deleteElement(List<Long> elementIds);

    Long getParentRelation(Long elementId);

    ProcessElement modifyParentRelation(Long elementId,Long parentId);

    List<Long> getBeforeRelation(Long elementId);

    ProcessElement modifyBeforeRelation(Long elementId, Set<Long> elementIds);

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
