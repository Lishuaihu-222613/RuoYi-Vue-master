package com.ruoyi.system.service.StructureService.impl;

import com.ruoyi.system.Repository.StructureRepository.ConstraintRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.Repository.StructureRepository.StructureRepository;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.PartsWithConstraints;
import com.ruoyi.system.service.StructureService.StructureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StructureServiceImpl implements StructureService {

    @Resource
    private StructureRepository structureMapper;

    @Resource
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

    @Override
    public Set<AssemblyPart> createUnknownParts(List<PartsWithConstraints> constraints) {
        Set<AssemblyPart> parts = new HashSet<>();
        constraints.forEach(c ->{
            c.getParts().forEach(p -> {
                parts.add(p);
            });
        });
        parts.forEach(p -> {
            createPart(p);
        });
        Set<AssemblyConstraint> newConstraints = new HashSet<>();
        constraints.forEach(c ->{
            AssemblyConstraint constraint = c.getConstraint();
            c.getParts().forEach(p -> {
                parts.forEach(p1 -> {
                    AssemblyPart p2 = p1;
                    if(p2.getPartName().equals(p.getPartName())){
                        Set<AssemblyStructure> newParts;
                        if(constraint.getStructures() == null || constraint.getStructures().isEmpty()){
                            newParts = new HashSet<>();
                        } else {
                            newParts = constraint.getStructures();
                        }
                        newParts.add(p1);
                        constraint.setStructures(newParts);
                    }
                });
            });
            newConstraints.add(createConstraint(constraint));
        });
        newConstraints.forEach(n ->{
            n.getStructures().forEach(s ->{
                if(s.getConstraints() == null || s.getConstraints().isEmpty()){
                    Set<AssemblyConstraint> c1 = new HashSet<>();
                    c1.add(n);
                    s.setConstraints(c1);
                }else {
                    s.getConstraints().add(n);
                }
                updateStructure(s);
            });
        });
        return parts;
    }

}
