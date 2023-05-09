package com.ruoyi.system.service.ElementService.impl;

import com.ruoyi.system.Repository.StructureRepository.ConstraintRepository;
import com.ruoyi.system.Repository.StructureRepository.StructureElementRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.Repository.StructureRepository.StructureRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.*;
import com.ruoyi.system.service.ElementService.ElementService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.constraint;

@Service
public class ElementServiceImpl implements ElementService {

    @Resource
    private StructureElementRepository structureElementRepository;

    @Resource
    private ConstraintRepository constraintRepository;


    @Override
    public List<AssemblyElement> getAllAssemblyProducts() {
        return structureElementRepository.findAllProducts();
    }

    @Override
    public List<AssemblyElement> getSubElementsById(Long elementId) {

        List<AssemblyElement> elements = structureElementRepository.findSubElementsByParentId(elementId);
        return elements;
    }

    @Override
    public List<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel) {
        return structureElementRepository.findProductsByLabel(dynamicLabel);
    }

    @Override
    public Page<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel, Pageable pageable) {
        return structureElementRepository.findProductsByLabel(dynamicLabel, pageable);
    }

    @Override
    public Page<AssemblyElement> getElementsByParams(Example<AssemblyElement> example,Pageable pageable) {
        return structureElementRepository.findAll(example,pageable);
    }

    @Override
    public void deleteElements(Long[] elements) {
        structureElementRepository.deleteAllById(Arrays.asList(elements));
    }

    @Override
    public AssemblyElement getElementById(Long elementId) {
        Optional<AssemblyElement> element = structureElementRepository.findById(elementId);
        return element.orElse(null);
    }

    @Override
    public List<AssemblyElement> getRelatedStructure(Long relatedId) {
        List<AssemblyElement> structure = structureElementRepository.findRelatedStructure(relatedId);
        return structure;
    }

    @Override
    public void modifyRelatedStructure(RelatedStructureVo vo) {
        structureElementRepository.deleteRelatedStructure(vo.getRelatedId());
        for (Long elementId : vo.getElementId()) {
            structureElementRepository.createRelatedStructure(vo.getRelatedId(), elementId);
        }

    }

    @Override
    public AssemblyElement getSingleElementById(Long elementId) {
        AssemblyElement singleElement = structureElementRepository.findSingleElement(elementId);
        return singleElement;
    }

    @Override
    public AssemblyElement getParentElementById(Long elementId) {
        return structureElementRepository.findParentElement(elementId);
    }


    @Override
    public AssemblyElement createElementForParent(ElementForParent EP) {
        AssemblyElement parentElement = structureElementRepository.findSingleElement(EP.getParentId());
        if(parentElement != null){
            AssemblyElement childElement = structureElementRepository.save(EP.getOriginElement());
            structureElementRepository.createParentRelation(parentElement.getElementId(),childElement.getElementId());
            return childElement;
        }
        return null;
    }

    @Override
    public AssemblyElement createElement(AssemblyElement assemblyElement) {
        return structureElementRepository.save(assemblyElement);
    }

    @Override
    public AssemblyElement updateElementForParent(ElementForParent EP) {
        Optional<AssemblyElement> parentElement = structureElementRepository.findById(EP.getParentId());
        if(parentElement.isPresent()){
            AssemblyElement assemblyElement = parentElement.get();
            Optional<AssemblyElement> element = structureElementRepository.findById(EP.getOriginElement().getElementId());
            if(element.isPresent()){
                AssemblyElement element1 = element.get();
                structureElementRepository.deleteParentRelation(element1.getElementId());
                element1.setElementName(EP.getOriginElement().getElementName());
                element1.setElementDescription(EP.getOriginElement().getElementDescription());
                element1.setElementQuantity(EP.getOriginElement().getElementQuantity());
                element1.setElementSource(EP.getOriginElement().getElementSource());
                element1.setElementWetArea(EP.getOriginElement().getElementWetArea());
                element1.setElementVolume(EP.getOriginElement().getElementVolume());
                element1.setElementMass(EP.getOriginElement().getElementMass());
                element1.setElementBoundingBox(EP.getOriginElement().getElementBoundingBox());
                structureElementRepository.createParentRelation(assemblyElement.getElementId(),element1.getElementId());
                return structureElementRepository.save(element1);
            }
        }
        return null;
    }

    @Override
    public AssemblyElement updateElement(AssemblyElement assemblyElement) {
        Optional<AssemblyElement> element = structureElementRepository.findById(assemblyElement.getElementId());
        if(element.isPresent()){
            AssemblyElement oldElement = element.get();
            oldElement.setElementName(assemblyElement.getElementName());
            oldElement.setElementDescription(assemblyElement.getElementDescription());
            oldElement.setElementQuantity(assemblyElement.getElementQuantity());
            oldElement.setElementSource(assemblyElement.getElementSource());
            oldElement.setElementWetArea(assemblyElement.getElementWetArea());
            oldElement.setElementVolume(assemblyElement.getElementVolume());
            oldElement.setElementMass(assemblyElement.getElementMass());
            oldElement.setElementBoundingBox(assemblyElement.getElementBoundingBox());
            oldElement.setHasSubElements(assemblyElement.isHasSubElements());
            return structureElementRepository.save(oldElement);
        }
        return null;
    }

    @Override
    public List<AssemblyConstraint> getConstraintsByElementId(Long elementId) {
        List<AssemblyConstraint> constraints = new ArrayList<>();
        for (AssemblyConstraint constraint : constraintRepository.findConstraintsByElementId(elementId)) {
            AssemblyConstraint singleConstraint = constraintRepository.findSingleConstraintById(constraint.getConstraintId());
            singleConstraint.getElements().removeIf(element -> Objects.equals(element.getElementId(), elementId));
            constraints.add(singleConstraint);
        }
        return constraints;
    }

    @Override
    public AssemblyConstraint createConstraintForElement(ConstraintVo vo) {
        AssemblyConstraint constraint = new AssemblyConstraint();
        constraint.setConstraintType(vo.getConstraintType());
        constraint.setConstraintValue(vo.getConstraintValue());
        constraint.setConstraintName(vo.getConstraintName());
        AssemblyConstraint newConstraint = constraintRepository.save(constraint);
        constraintRepository.updateRelationShipForConstraint(newConstraint.getConstraintId(), vo.getElementId());
        constraintRepository.updateRelationShipForConstraint(newConstraint.getConstraintId(), vo.getOtherElementId());
        newConstraint.getElements().add(structureElementRepository.findById(vo.getElementId()).get());
        return newConstraint;
    }


    //
    @Override
    public AssemblyConstraint updateConstraint(ConstraintForElement CE) {
        constraintRepository.deleteRelationForConstraint(CE.getOriginConstraint().getConstraintId());
        Optional<AssemblyConstraint> constraint = constraintRepository.findById(CE.getOriginConstraint().getConstraintId());
        if(constraint.isPresent()){
            AssemblyConstraint assemblyConstraint = constraint.get();
            assemblyConstraint.setConstraintName(CE.getOriginConstraint().getConstraintName());
            assemblyConstraint.setConstraintType(CE.getOriginConstraint().getConstraintType());
            assemblyConstraint.setConstraintValue(CE.getOriginConstraint().getConstraintValue());
            constraintRepository.save(assemblyConstraint);
            constraintRepository.updateRelationShipForConstraint(assemblyConstraint.getConstraintId(),CE.getElementId());
            for (AssemblyElement element : CE.getOriginConstraint().getElements()) {
                constraintRepository.updateRelationShipForConstraint(assemblyConstraint.getConstraintId(),element.getElementId());
            }
            return constraintRepository.findSingleConstraintById(CE.getOriginConstraint().getConstraintId());
        }
        return null;
    }

    @Override
    public void deleteConstraint(Long constraintId) {
        constraintRepository.deleteById(constraintId);
    }

    @Override
    public void modifyRelations(RelationsVoForElement relationsVoForElement) {
        structureElementRepository.deleteAssociatedFileRelation(relationsVoForElement.getElementId());
        structureElementRepository.createModelFileRelation(relationsVoForElement.getModelFileId(),relationsVoForElement.getElementId());
    }


    @Override
    public List<AssemblyConstraint> getAllConstraintByStructureId(Long ASItemId) {
        return null;
    }



    @Override
    public AssemblyPart createPart(AssemblyPart part) {
        return null;
    }


    @Override
    public AssemblyConstraint createConstraint(ConstraintVo vo) {
            AssemblyConstraint assemblyConstraint = new AssemblyConstraint();
            assemblyConstraint.setConstraintType(vo.getConstraintType());
            assemblyConstraint.setConstraintName(vo.getConstraintName());
            assemblyConstraint.setConstraintValue(vo.getConstraintValue());
            AssemblyConstraint newConstraint = constraintRepository.save(assemblyConstraint);
            constraintRepository.updateRelationShipForConstraint(newConstraint.getConstraintId(),vo.getElementId());
            constraintRepository.updateRelationShipForConstraint(newConstraint.getConstraintId(),vo.getOtherElementId());
            AssemblyConstraint otherConstraint = constraintRepository.findSingleConstraintById(newConstraint.getConstraintId());
            otherConstraint.getElements().removeIf(item -> Objects.equals(item.getElementId(), vo.getElementId()));
            return otherConstraint;
    }



    @Override
    public Set<AssemblyPart> createUnknownParts(List<PartsWithConstraints> constraints) {
     return null;
    }

}
