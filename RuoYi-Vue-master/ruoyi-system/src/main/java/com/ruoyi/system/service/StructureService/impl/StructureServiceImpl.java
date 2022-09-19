package com.ruoyi.system.service.StructureService.impl;

import com.ruoyi.system.Repository.StructureRepository.ConstraintRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.Repository.StructureRepository.StructureRepository;
import com.ruoyi.system.service.StructureService.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StructureServiceImpl implements StructureService {

    @Autowired
    private StructureRepository structureMapper;

    @Autowired
    private ConstraintRepository constraintRepository;


    @Override
    public List<AssemblyProduct> getAllAssemblyProduct() {
        List<AssemblyProduct> allProduct = structureMapper.findAllProduct();
        return allProduct;
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
        Optional<AssemblyStructure> structure = structureMapper.findById(ASItemId);
        return  structure.get();
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
        AssemblyStructure assemblyProduct = structureMapper.save(product);
        return (AssemblyProduct) assemblyProduct;
    }

    @Override
    public AssemblyComponent createComponent(AssemblyComponent component) {
        AssemblyStructure assemblyComponent = structureMapper.save(component);
        return (AssemblyComponent)assemblyComponent;
    }

    @Override
    public AssemblyPart createPart(AssemblyPart part) {
        AssemblyStructure assemblyPart = structureMapper.save(part);
        return (AssemblyPart) assemblyPart;
    }

    @Override
    public AssemblyConstraint createConstraint(AssemblyConstraint constraint) {
        AssemblyConstraint assemblyConstraint = constraintRepository.save(constraint);
        return assemblyConstraint;
    }

    @Override
    public AssemblyStructure updateStructure(AssemblyStructure structure) {
        AssemblyStructure assemblyStructure = structureMapper.save(structure);
        return assemblyStructure;
    }

}
