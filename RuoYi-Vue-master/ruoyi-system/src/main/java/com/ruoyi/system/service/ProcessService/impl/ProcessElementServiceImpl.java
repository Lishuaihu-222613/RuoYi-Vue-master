package com.ruoyi.system.service.ProcessService.impl;

import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.RelationAndElement;
import com.ruoyi.system.service.ProcessService.ProcessElementService;

import java.util.List;
import java.util.Set;

public class ProcessElementServiceImpl implements ProcessElementService {

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
