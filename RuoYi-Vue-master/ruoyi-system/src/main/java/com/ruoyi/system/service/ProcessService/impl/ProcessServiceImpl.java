package com.ruoyi.system.service.ProcessService.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.File.FileRepository;
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
import com.ruoyi.system.domain.AssemblyPojo.Process.vo.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyElement;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ElementForParent;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.RelationsVoForElement;
import com.ruoyi.system.service.KnowledgeService.File.impl.FileKnowledgeServiceImpl;
import com.ruoyi.system.service.ProcessService.ProcessService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessElementRepository processElementRepository;

    @Resource
    private ResourceRepository resourceRepository;

    @Resource
    private StructureElementRepository structureElementRepository;

    @Resource
    private FileRepository fileRepository;


    @Override
    public List<ProcessElement> getAllAssemblyProcess() {
        return processElementRepository.findAllProcess();
    }

    @Override
    public List<ProcessElement> getSubElementsById(Long elementId) {
        List<ProcessElement> elements = processElementRepository.findSubElementsById(elementId);
        return elements;
    }

    @Override
    public List<ProcessElement> getBeforeElementsById(Long elementId) {
        List<ProcessElement> elements = processElementRepository.findBeforeElementsById(elementId);
        return elements;
    }

    @Override
    public List<ProcessElement> getAfterElementsById(Long elementId) {
        List<ProcessElement> elements = processElementRepository.findAfterElementsById(elementId);
        return elements;
    }

    @Override
    public List<ProcessElement> getAndElementsById(Long elementId) {
        List<ProcessElement> elements = processElementRepository.findAndElementsById(elementId);
        return elements;
    }

    @Override
    public List<ProcessElement> getOrElementsById(Long elementId) {
        List<ProcessElement> elements = processElementRepository.findOrElementsById(elementId);
        return elements;
    }

    @Override
    public List<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel) {
        return processElementRepository.findProcessByLabel(dynamicLabel);
    }

    @Override
    public Page<ProcessElement> getAllAssemblyProcessByLabel(String dynamicLabel, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProcessElement> getElementsByParams(Example<ProcessElement> example, Pageable pageable) {
        return processElementRepository.findAll(example,pageable);
    }

    @Override
    public void deleteElements(Long[] elements) {
        processElementRepository.deleteAllById(Arrays.asList(elements));
    }

    @Override
    public ProcessElement getElementById(Long elementId) {
        Optional<ProcessElement> element = processElementRepository.findById(elementId);
        return element.orElse(null);
    }

    @Override
    public ProcessElement getSingleElementById(Long elementId) {
        ProcessElement element = processElementRepository.findSingleElementById(elementId);
        return element;
    }

    @Override
    public ProcessElement getParentElementById(Long elementId) {
        return processElementRepository.findParentElement(elementId);
    }

    @Override
    public ProcessElement createElementForParent(ElementForParentProcess EP) {
        ProcessElement parentElement = processElementRepository.findSingleElementById(EP.getParentId());
        if(parentElement != null){
            ProcessElement childElement = processElementRepository.save(EP.getOriginElement());
            processElementRepository.createSubRelation(parentElement.getElementId(),childElement.getElementId());
            return childElement;
        }
        return null;
    }

    @Override
    public ProcessElement createElement(ProcessElement processElement) {
        return processElementRepository.save(processElement);
    }

    @Override
    public ProcessElement updateElementForParent(ElementForParentProcess EP) {
        Optional<ProcessElement> parentElement = processElementRepository.findById(EP.getParentId());
        if(parentElement.isPresent()){
            ProcessElement pElement = parentElement.get();
            Optional<ProcessElement> element = processElementRepository.findById(EP.getOriginElement().getElementId());
            if(element.isPresent()){
                ProcessElement element1 = element.get();
                element1.setElementName(EP.getOriginElement().getElementName());
                element1.setElementDescription(EP.getOriginElement().getElementDescription());
                element1.setElementNumber(EP.getOriginElement().getElementNumber());
                element1.setElementRemark(EP.getOriginElement().getElementRemark());
                element1.setElementRequirements(EP.getOriginElement().getElementRequirements());
                element1.setElementOtherProperties(EP.getOriginElement().getElementOtherProperties());
                element1.setDynamicLabels(EP.getOriginElement().getDynamicLabels());
                ProcessElement save = processElementRepository.save(element1);
                processElementRepository.deleteSubRelation(element1.getElementId());
                processElementRepository.createSubRelation(pElement.getElementId(), element1.getElementId());
                return processElementRepository.findSingleElementById(element1.getElementId());
            }
        }
        return null;
    }

    @Override
    public ProcessElement updateElement(ProcessElement processElement) {
        Optional<ProcessElement> element = processElementRepository.findById(processElement.getElementId());
        if(element.isPresent()){
            ProcessElement oldElement = element.get();
            oldElement.setElementName(processElement.getElementName());
            oldElement.setElementDescription(processElement.getElementDescription());
            oldElement.setDynamicLabels(processElement.getDynamicLabels());
            oldElement.setElementRemark(processElement.getElementRemark());
            oldElement.setElementNumber(processElement.getElementNumber());
            oldElement.setElementRequirements(processElement.getElementRequirements());
            oldElement.setElementOtherProperties(processElement.getElementOtherProperties());
            return processElementRepository.save(oldElement);
        }
        return null;
    }

    @Override
    public void modifyRelations(RelationsVoForProcessElement relationsVoForElement) {
        Optional<ProcessElement> element = processElementRepository.findById(relationsVoForElement.getElementId());
        if(element.isPresent()){
            processElementRepository.deleteAndRelation(relationsVoForElement.getElementId());
            processElementRepository.deleteAfterRelation(relationsVoForElement.getElementId());
            processElementRepository.deleteOrRelation(relationsVoForElement.getElementId());
            processElementRepository.deleteBeforeRelation(relationsVoForElement.getElementId());
            for (Long beforeElementId : relationsVoForElement.getBeforeElementIds()) {
                processElementRepository.createBeforeRelation(beforeElementId,relationsVoForElement.getElementId());
            }
            for (Long afterElementId : relationsVoForElement.getAfterElementIds()) {
                processElementRepository.createAfterRelation(afterElementId,relationsVoForElement.getElementId());
            }
            for (Long andElementId : relationsVoForElement.getAndElementIds()) {
                processElementRepository.createSubRelation(andElementId,relationsVoForElement.getElementId());
            }
            for (Long orElementId : relationsVoForElement.getOrElementIds()) {
                processElementRepository.createOrRelation(orElementId,relationsVoForElement.getElementId());
            }
        }
    }

    @Override
    public void modifyBeforeRelations(ElementRelations vo) {
        Optional<ProcessElement> element = processElementRepository.findById(vo.getElementId());
        if(element.isPresent()){
            processElementRepository.deleteBeforeRelation(vo.getElementId());
            for (Long relation : vo.getRelations()) {
                processElementRepository.createBeforeRelation(relation,vo.getElementId());
            }
        }
    }

    @Override
    public void modifyAfterRelations(ElementRelations vo) {
        Optional<ProcessElement> element = processElementRepository.findById(vo.getElementId());
        if(element.isPresent()){
            processElementRepository.deleteAfterRelation(vo.getElementId());
            for (Long relation : vo.getRelations()) {
                processElementRepository.createAfterRelation(relation,vo.getElementId());
            }
        }
    }

    @Override
    public void modifyAndRelations(ElementRelations vo) {
        Optional<ProcessElement> element = processElementRepository.findById(vo.getElementId());
        if(element.isPresent()){
            processElementRepository.deleteAndRelation(vo.getElementId());
            for (Long relation : vo.getRelations()) {
                processElementRepository.createAndRelation(relation,vo.getElementId());
            }
        }
    }

    @Override
    public void modifyOrRelations(ElementRelations vo) {
        Optional<ProcessElement> element = processElementRepository.findById(vo.getElementId());
        if(element.isPresent()){
            processElementRepository.deleteOrRelation(vo.getElementId());
            for (Long relation : vo.getRelations()) {
                processElementRepository.createOrRelation(relation,vo.getElementId());
            }
        }
    }

    @Override
    public void updateRelation(OtherRelations vo) {
        processElementRepository.deleteOtherRelations(vo.getElementId());
        for (Long structure : vo.getStructures()) {
            structureElementRepository.createRelatedStructure(vo.getElementId(),structure);
        }
        for (Long resource : vo.getResources()) {
            resourceRepository.createUseRelation(resource, vo.getElementId());
        }
        for (Long file : vo.getFiles()) {
            fileRepository.createdAssociatedRelationById(file,vo.getElementId());
        }


    }
}
