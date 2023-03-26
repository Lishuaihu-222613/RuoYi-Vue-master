package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.service.KnowledgeService.Material.MaterialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialRepository materialRepository;

    @Resource
    private AnalysisSpectrogramRepository analysisSpectrogramRepository;

    @Resource
    private ChemicalPropertyRepository chemicalPropertyRepository;

    @Resource
    private DangerRepository dangerRepository;

    @Resource
    private InspectProjectRepository inspectProjectRepository;

    @Resource
    private PhysicalPropertyRepository physicalPropertyRepository;

    @Resource
    private ProduceMethodRepository produceMethodRepository;

    @Resource
    private ProtectionRepository protectionRepository;

    @Resource
    private StorageRequirementRepository storageRequirementRepository;

    PageRequest of = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "principleId"));

    @Override
    public Page<MaterialInterface> getAllMaterials() {
        return materialRepository.findSingleMaterials(of);
    }

    @Override
    public Page<MaterialInterface> getMaterialsByType(String dynamicLabel) {
        return materialRepository.findMaterialsByLabel(dynamicLabel,of);
    }

    @Override
    public MaterialInterface getMaterialById(Long materialId) {
        return  materialRepository.findMaterialInterfaceById(materialId).get();
    }

    @Override
    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material createSingleMaterial(Material singleMaterial) {
        singleMaterial.setAnalysisSpectrogram(null);
        singleMaterial.setChemicalProperty(null);
        singleMaterial.setAssociatedFiles(null);
        singleMaterial.setDangers(null);
        singleMaterial.setInspectProjects(null);
        singleMaterial.setPhysicalProperty(null);
        singleMaterial.setProductMethods(null);
        singleMaterial.setProtections(null);
        singleMaterial.setStorageRequirements(null);
        return materialRepository.save(singleMaterial);
    }
    @Override
    public void deleteMaterial(Material material) {
        materialRepository.delete(material);
    }

    @Override
    public void deleteMaterialById(Long MaterialId) {
        materialRepository.deleteById(MaterialId);
    }

    @Override
    public Material updateMaterial(Material newMaterial) {
        Long materialId = newMaterial.getMaterialId();
        Material oldMaterial = materialRepository.findById(materialId).get();
        oldMaterial.setMaterialName(newMaterial.getMaterialName());
        oldMaterial.setEnglishName(newMaterial.getEnglishName());
        oldMaterial.setNickName(newMaterial.getNickName());
        oldMaterial.setEnglishNickName(newMaterial.getEnglishNickName());
        oldMaterial.setRelativeMolecularMass(newMaterial.getRelativeMolecularMass());
        oldMaterial.setStructuralFormula(newMaterial.getStructuralFormula());
        oldMaterial.setCasRegistryNumber(newMaterial.getCasRegistryNumber());
        oldMaterial.setMaterialAppearance(newMaterial.getMaterialAppearance());
        oldMaterial.setMaterialDescription(newMaterial.getMaterialDescription());
        oldMaterial.setMaterialUsage(newMaterial.getMaterialUsage());
        oldMaterial.setMaterialLabels(newMaterial.getMaterialLabels());
        return materialRepository.save(oldMaterial);
    }
}
