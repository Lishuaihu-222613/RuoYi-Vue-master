package com.ruoyi.system.service.ElementService.impl;

import com.ruoyi.system.Repository.StructureRepository.ConstraintRepository;
import com.ruoyi.system.Repository.StructureRepository.StructureElementRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.Repository.StructureRepository.StructureRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ConstraintForElement;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ElementForParent;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.PartsWithConstraints;
import com.ruoyi.system.service.ElementService.ElementService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel) {
        return structureElementRepository.findProductsByLabel(dynamicLabel);
    }

    @Override
    public Page<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel, Pageable pageable) {
        return structureElementRepository.findProductsByLabel(dynamicLabel, pageable);
    }

    @Override
    public List<AssemblyElement> getProductsByParams(Example<AssemblyElement> example) {
        return structureElementRepository.findAll(example);
    }

    @Override
    public void deleteElements(Long[] elements) {
        structureElementRepository.deleteAllById(Arrays.asList(elements));
    }

    @Override
    public AssemblyElement createElementForParent(ElementForParent EP) {
        Optional<AssemblyElement> parentElement = structureElementRepository.findById(EP.getParentId());
        if(parentElement.isPresent()){
            AssemblyElement parent = parentElement.get();
            AssemblyElement childElement = structureElementRepository.save(EP.getOriginElement());
            Set<AssemblyElement> subElements = parent.getSubElements();
            subElements.add(childElement);
            parent.setSubElements(subElements);
            structureElementRepository.save(parent);
            return childElement;
        }
        return null;
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
    public AssemblyConstraint createConstraintForElement(Long elementId) {
        AssemblyConstraint constraint = new AssemblyConstraint();
        Optional<AssemblyElement> elementOptional = structureElementRepository.findById(elementId);
        if(elementOptional.isPresent()){
            AssemblyElement element = elementOptional.get();
            Set<AssemblyConstraint> constraints = element.getConstraints();
            constraints.add(constraint);
            element.setConstraints(constraints);
            structureElementRepository.save(element);
            return constraint;
        }
        return null;
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
    public List<AssemblyStructure> getAllStructureByProductId(Long ProductId) {
        return null;
    }

    @Override
    public List<AssemblyStructure> getAllStructureByComponentId(Long ComponentId) {
        return null;
    }

    @Override
    public List<AssemblyConstraint> getAllConstraintByStructureId(Long ASItemId) {
        return null;
    }

    @Override
    public AssemblyStructure getAssemblyStructureId(Long ASItemId) {
        return null;
    }


    @Override
    public AssemblyProduct getProductById(Long ProductId) {
        return null;
    }

    @Override
    public AssemblyComponent getComponentById(Long ComponentId) {
        return null;
    }

    @Override
    public AssemblyPart getPartById(Long PartId) {
        return null;
    }

    @Override
    public AssemblyConstraint getConstraintById(Long ConstraintId) {
        return null;
    }

    @Override
    public AssemblyProduct createProduct(AssemblyProduct product) {
        return null;
    }

    @Override
    public AssemblyComponent createComponent(AssemblyComponent component) {
        return null;
    }

    @Override
    public AssemblyPart createPart(AssemblyPart part) {
        return null;
    }


    @Override
    public AssemblyConstraint createConstraint(AssemblyConstraint constraint) {
        AssemblyConstraint assemblyConstraint = constraintRepository.save(constraint);
        return assemblyConstraint;
    }

    @Override
    public AssemblyStructure updateStructure(AssemblyStructure structure) {
        return null;
    }

    @Override
    public Set<AssemblyPart> createUnknownParts(List<PartsWithConstraints> constraints) {
     return null;
    }

}
