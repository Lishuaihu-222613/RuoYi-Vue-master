package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.MaterialRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.service.KnowledgeService.Material.MaterialService;
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

    @Override
    public List<MaterialInterface> getMaterialsByMkId(Long mkId) {
        return new ArrayList<>(materialRepository.findMaterialsByMKId(mkId));
    }

    @Override
    public List<MaterialInterface> getAllMaterials() {
        return new ArrayList<>( materialRepository.findSingleMaterials());
    }

    @Override
    public List<MaterialInterface> getMaterialsByType(String dynamicLabel) {
        return new ArrayList<>(materialRepository.findMaterialsByLabel(dynamicLabel));
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
