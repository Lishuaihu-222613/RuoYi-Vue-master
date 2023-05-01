package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.InspectProjectInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo.*;
import com.ruoyi.system.service.KnowledgeService.Material.MaterialService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

    @Override
    public Page<Material> getAllMaterials(Pageable pageable) {
        return materialRepository.findSingleMaterials(pageable);
    }

    @Override
    public Page<Material> getMaterialsByParams(Example<Material> example, Pageable pageable) {
        return materialRepository.findAll(example,pageable);
    }

    @Override
    public List<Material> getAllMaterialOptions() {
        return materialRepository.findAllMaterialOptions();
    }

    @Override
    public List<Material> getMaterialOptionsByLabel(String dynamicLabel) {
        return materialRepository.findMaterialOptionsByLabel(dynamicLabel);
    }

    @Override
    public Page<Material> getMaterialsByType(String dynamicLabel,Pageable pageable) {
        return materialRepository.findMaterialsByLabel(dynamicLabel,pageable);
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
        AnalysisSpectrogram analysisSpectrogram = analysisSpectrogramRepository.save(new AnalysisSpectrogram());
        Set<AnalysisSpectrogram> spectrograms = singleMaterial.getAnalysisSpectrogram();
        spectrograms.add(analysisSpectrogram);
        singleMaterial.setAnalysisSpectrogram(spectrograms);
        ChemicalProperty chemicalProperty = chemicalPropertyRepository.save(new ChemicalProperty());
        Set<ChemicalProperty> chemicalPropertys = singleMaterial.getChemicalProperty();
        chemicalPropertys.add(chemicalProperty);
        singleMaterial.setChemicalProperty(chemicalPropertys);
        singleMaterial.setAssociatedFiles(null);
        Set<Danger> dangers = singleMaterial.getDangers();
        Danger danger = dangerRepository.save(new Danger());
        dangers.add(danger);
        singleMaterial.setDangers(dangers);
        Set<InspectProject> inspectProjects = singleMaterial.getInspectProjects();
        InspectProject project = inspectProjectRepository.save(new InspectProject());
        inspectProjects.add(project);
        singleMaterial.setInspectProjects(inspectProjects);
        PhysicalProperty physicalProperty = physicalPropertyRepository.save(new PhysicalProperty());
        Set<PhysicalProperty> physicalPropertys = singleMaterial.getPhysicalProperty();
        physicalPropertys.add(physicalProperty);
        singleMaterial.setPhysicalProperty(physicalPropertys);
        Set<ProduceMethod> productMethods = singleMaterial.getProductMethods();
        ProduceMethod produceMethod = produceMethodRepository.save(new ProduceMethod());
        productMethods.add(produceMethod);
        singleMaterial.setProductMethods(productMethods);
        Protection protection = protectionRepository.save(new Protection());
        Set<Protection> protections = singleMaterial.getProtections();
        protections.add(protection);
        singleMaterial.setProtections(protections);
        StorageRequirement storageRequirement = storageRequirementRepository.save(new StorageRequirement());
        Set<StorageRequirement> storageRequirements = singleMaterial.getStorageRequirements();
        storageRequirements.add(storageRequirement);
        singleMaterial.setStorageRequirements(storageRequirements);
        return materialRepository.save(singleMaterial);
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

    @Override
    public AnalysisSpectrogram createAnalysisSpectrogram(AMvo AM) {
        Optional<Material> optionalMaterial = materialRepository.findById(AM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            AnalysisSpectrogram spectrogram = analysisSpectrogramRepository.save(AM.getAnalysisSpectrogram());
            Set<AnalysisSpectrogram> spectrograms = material.getAnalysisSpectrogram();
            spectrograms.add(spectrogram);
            material.setAnalysisSpectrogram(spectrograms);
            materialRepository.save(material);
            return spectrogram;
        }
        return null;
    }

    @Override
    public AnalysisSpectrogram getAnalysisSpectrogramById(Long spectrogramId) {
        return analysisSpectrogramRepository.findById(spectrogramId).get();
    }

    @Override
    public Collection<AnalysisSpectrogram> getAnalysisSpectrogramByMaterialId(Long materialId) {
        return analysisSpectrogramRepository.findAnalysisSpectrogramByMaterialId(materialId);
    }

    @Override
    public AnalysisSpectrogram updateAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram) {
        return analysisSpectrogramRepository.save(analysisSpectrogram);
    }


    @Override
    public void deleteAnalysisSpectrogramById(Long spectrogramId) {
        analysisSpectrogramRepository.deleteById(spectrogramId);
    }

    @Override
    public Collection<AnalysisSpectrogram> getAnalysisSpectrogramByName(String spectrogramName) {
        return analysisSpectrogramRepository.findAnalysisSpectrogramBySpectrogramName(spectrogramName);
    }

    @Override
    public ChemicalProperty createChemicalProperty(CMvo CM) {
        Optional<Material> optionalMaterial = materialRepository.findById(CM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            ChemicalProperty property = chemicalPropertyRepository.save(CM.getChemicalProperty());
            Set<ChemicalProperty> properties = material.getChemicalProperty();
            properties.add(property);
            material.setChemicalProperty(properties);
            materialRepository.save(material);
            return property;
        }
        return null;
    }

    @Override
    public ChemicalProperty getChemicalPropertyById(Long propertyId) {
        return chemicalPropertyRepository.findById(propertyId).get();
    }

    @Override
    public ChemicalProperty getChemicalPropertyByMaterialId(Long materialId) {
        return chemicalPropertyRepository.findChemicalPropertiesByMaterialId(materialId);
    }

    @Override
    public ChemicalProperty updateChemicalProperty(ChemicalProperty property) {
        return chemicalPropertyRepository.save(property);
    }

    @Override
    public void deleteChemicalPropertyById(Long propertyId) {
        chemicalPropertyRepository.deleteById(propertyId);
    }

    @Override
    public PhysicalProperty createPhysicalProperty(PMvo PM) {
        Optional<Material> optionalMaterial = materialRepository.findById(PM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            PhysicalProperty property = physicalPropertyRepository.save(PM.getPhysicalProperty());
            Set<PhysicalProperty> properties = material.getPhysicalProperty();
            properties.add(property);
            material.setPhysicalProperty(properties);
            materialRepository.save(material);
            return property;
        }
        return null;
    }

    @Override
    public PhysicalProperty getPhysicalPropertyByMaterialId(Long materialId) {
        return physicalPropertyRepository.findPhysicalPropertiesByMaterialId(materialId);
    }

    @Override
    public Collection<PhysicalProperty> getPhysicalPropertyByName(String propertyName) {
        return physicalPropertyRepository.findPhysicalPropertiesByPropertyName(propertyName);
    }

    @Override
    public PhysicalProperty getPhysicalPropertyById(Long propertyId) {
        return physicalPropertyRepository.findById(propertyId).get();
    }

    @Override
    public PhysicalProperty updatePhysicalProperty(PhysicalProperty property) {
        return physicalPropertyRepository.save(property);
    }

    @Override
    public void deletePhysicalPropertyById(Long propertyId) {
        physicalPropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<Danger> getDangersByMaterialId(Long materialId) {
        return dangerRepository.findDangersByMaterialId(materialId);
    }

    @Override
    public Danger getDangerById(Long dangerId) {
        return dangerRepository.findById(dangerId).get();
    }

    @Override
    public Danger createDanger(DMvo DM) {
        Optional<Material> optionalMaterial = materialRepository.findById(DM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Danger danger = dangerRepository.save(DM.getDanger());
            Set<Danger> dangers = material.getDangers();
            dangers.add(danger);
            material.setDangers(dangers);
            materialRepository.save(material);
            return danger;
        }
        return null;
    }

    @Override
    public Danger updateDanger(Danger danger) {
        return dangerRepository.save(danger);
    }


    @Override
    public void deleteDangerById(Long dangerId) {
        dangerRepository.deleteById(dangerId);
    }

    @Override
    public void createRelationshipForDanger(Long materialId, Long dangerId) {
        dangerRepository.createRelationshipForDanger(materialId, dangerId);
    }

    @Override
    public Set<InspectProject> createInspectProjects(IMvo IM) {
        Optional<Material> optionalMaterial = materialRepository.findById(IM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Set<InspectProject> projects = material.getInspectProjects();
            for (InspectProject project : IM.getInspectProjects()) {
                InspectProject newProject = inspectProjectRepository.save(project);
                projects.add(newProject);
            }
            material.setInspectProjects(projects);
            materialRepository.save(material);
            return projects;
        }
        return null;
    }

    @Override
    public InspectProject createInspectProject(Long materialId, InspectProject project) {
        Optional<Material> optionalMaterial = materialRepository.findById(materialId);
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Set<InspectProject> projects = material.getInspectProjects();
            InspectProject newProject = inspectProjectRepository.save(project);
            projects.add(newProject);
            material.setInspectProjects(projects);
            materialRepository.save(material);
            return newProject;
        }
        return null;
    }

    @Override
    public InspectProject updateInspectProject(InspectProject project) {
        Optional<InspectProject> optionalInspectProject = inspectProjectRepository.findById(project.getProjectId());
        if (optionalInspectProject.isPresent()) {
            InspectProject inspectProject = optionalInspectProject.get();
            inspectProject.setProjectName(project.getProjectName());
            inspectProject.setClassificationAndIndicators(project.getClassificationAndIndicators());
            InspectProject newProject = inspectProjectRepository.save(inspectProject);
            return newProject;
        }
        return null;
    }

    @Override
    public List<InspectProject> updateInspectProjects(List<InspectProject> projects) {
        ArrayList<InspectProject> inspectProjects = new ArrayList<>();
        for (InspectProject project : projects) {
            Optional<InspectProject> optionalInspectProject = inspectProjectRepository.findById(project.getProjectId());
            if (optionalInspectProject.isPresent()) {
                InspectProject inspectProject = optionalInspectProject.get();
                inspectProject.setProjectName(project.getProjectName());
                inspectProject.setClassificationAndIndicators(project.getClassificationAndIndicators());
                InspectProject newProject = inspectProjectRepository.save(inspectProject);
                inspectProjects.add(newProject);
            }
        }
        return inspectProjects;
    }

    @Override
    public InspectProject getInspectProjectById(Long projectId) {
        return inspectProjectRepository.findById(projectId).get();
    }

    @Override
    public Collection<InspectProject> getInspectProjectsByMaterialId(Long materialId) {
        return inspectProjectRepository.findInspectProjectsByMaterialId(materialId);
    }

    @Override
    public void deleteInspectProjectById(Long projectId) {
        inspectProjectRepository.deleteById(projectId);
    }

    @Override
    public void createRelationshipForInspectProject(Long materialId, Long projectId) {
        inspectProjectRepository.createRelationshipForInspectProject(materialId,projectId);
    }

    @Override
    public ProduceMethod createProduceMethod(PMMvo PMM) {
        Optional<Material> optionalMaterial = materialRepository.findById(PMM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Set<ProduceMethod> productMethods = material.getProductMethods();
            ProduceMethod produceMethod = produceMethodRepository.save(PMM.getProduceMethod());
            productMethods.add(produceMethod);
            material.setProductMethods(productMethods);
            materialRepository.save(material);
            return produceMethod;
        }
        return null;
    }

    @Override
    public ProduceMethodInterface getProduceMethodInterfaceById(Long methodId) {
        return produceMethodRepository.findProduceMethodInterfaceById(methodId).get();
    }

    @Override
    public Collection<ProduceMethodInterface> getProduceMethodsByMaterialId(Long materialId) {
        return produceMethodRepository.findProduceMethodByMaterialId(materialId);
    }

    @Override
    public Collection<ProduceMethodInterface> getProduceMethodsByName(String methodName) {
        return produceMethodRepository.findByMethodName(methodName);
    }

    @Override
    public ProduceMethod updateProduceMethod(ProduceMethod produceMethod) {
        return produceMethodRepository.save(produceMethod);
    }

    @Override
    public void deleteProduceMethodById(Long methodId) {
        produceMethodRepository.deleteById(methodId);
    }

    @Override
    public void createRelationshipForProduceMethod(Long materialId, Long methodId) {
        produceMethodRepository.createRelationshipForProduceMethod(materialId,methodId);
    }

    @Override
    public Protection createProtection(ProMVO PM) {
        Optional<Material> optionalMaterial = materialRepository.findById(PM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Set<Protection> protections = material.getProtections();
            Protection protection = protectionRepository.save(PM.getProtection());
            protections.add(protection);
            material.setProtections(protections);
            materialRepository.save(material);
            return protection;
        }
        return null;
    }

    @Override
    public Collection<Protection> getProtectionsByMaterialId(Long materialId) {
        return protectionRepository.findProtectionsByMaterialId(materialId);
    }

    @Override
    public Collection<Protection> getProtectionsByName(String protectionName) {
        return protectionRepository.findByProtectionName(protectionName);
    }

    @Override
    public Protection getProtectionById(Long protectionId) {
        return protectionRepository.findById(protectionId).get();
    }

    @Override
    public Protection updateProtection(Protection protection) {
        return protectionRepository.save(protection);
    }

    @Override
    public void deleteProtectionById(Long protectionId) {
        protectionRepository.deleteById(protectionId);
    }

    @Override
    public void createRelationshipForProtection(Long materialId, Long protectionId) {
        protectionRepository.createRelationshipForProtection(materialId, protectionId);
    }

    @Override
    public StorageRequirement createStorageRequirement(SMvo SM) {
        Optional<Material> optionalMaterial = materialRepository.findById(SM.getMaterialId());
        if (optionalMaterial.isPresent()) {
            Material material = optionalMaterial.get();
            Set<StorageRequirement> storageRequirements = material.getStorageRequirements();
            StorageRequirement storageRequirement = storageRequirementRepository.save(SM.getStorageRequirement());
            storageRequirements.add(storageRequirement);
            material.setStorageRequirements(storageRequirements);
            materialRepository.save(material);
            return storageRequirement;
        }
        return null;
    }

    @Override
    public Collection<StorageRequirement> getStorageRequirementsByMaterialId(Long materialId) {
        return storageRequirementRepository.findStorageRequirementsByMaterialId(materialId);
    }

    @Override
    public Collection<StorageRequirement> getStorageRequirementsByName(String requirementName) {
        return storageRequirementRepository.findByRequirementName(requirementName);
    }

    @Override
    public StorageRequirement getStorageRequirementById(Long requirementId) {
        return storageRequirementRepository.findById(requirementId).get();
    }

    @Override
    public StorageRequirement updateStorageRequirement(StorageRequirement requirement) {
        return storageRequirementRepository.save(requirement);
    }

    @Override
    public void deleteStorageRequirementById(Long requirementId) {
        storageRequirementRepository.deleteById(requirementId);
    }
}
