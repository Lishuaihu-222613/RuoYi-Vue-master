package com.ruoyi.web.controller.system.MaterialBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageForNeo4j;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo.*;
import com.ruoyi.system.service.KnowledgeService.Material.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Resource
    private MaterialService materialService;

    @ResponseBody
    @PostMapping("/getAllMaterialsByType")
    public R<Page<Material>> getMaterialsByType(@RequestBody MaterialQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize());
            Page<Material> materials = materialService.getMaterialsByType(params.getDynamicLabel(),pageable);
            System.out.println(materials);
            return R.success(materials);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllMaterials")
    public R<Page<Material>> getAllMaterials(@RequestBody MaterialQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize());
            Page<Material> materials = materialService.getAllMaterials(pageable);
            System.out.println(materials);
            return R.success(materials);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getMaterialsByParams")
    public R<Page<Material>> getMaterialsByParams(@RequestBody MaterialQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize());
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("materialName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("nickName",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("casRegistryNumber",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("materialDescription",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<Material> example = Example.of(params.getOriginMaterial(), matcher);
            Page<Material> materials = materialService.getMaterialsByParams(example,pageable);
            System.out.println(materials);
            return R.success(materials);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAllMaterialOptions")
    public R<List<Material>> getAllMaterialOptions() {
        try {
            List<Material> materialOptions = materialService.getAllMaterialOptions();
            System.out.println(materialOptions);
            return R.success(materialOptions);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getMaterialOptionsByLabel/{dynamicLabel}")
    public R<List<Material>> getMaterialOptionsByLabel(@PathVariable String dynamicLabel) {
        try {
            List<Material> materialOptions = materialService.getMaterialOptionsByLabel(dynamicLabel);
            System.out.println(materialOptions);
            return R.success(materialOptions);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getMaterialById/{materialId}")
    public R<MaterialInterface> getMaterialById(@PathVariable Long materialId) {
        try {
            MaterialInterface singleMaterial = materialService.getMaterialById(materialId);
            System.out.println(singleMaterial);
            return R.success(singleMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteMaterial/{materialId}")
    public R<String> deleteMaterial(@PathVariable Long materialId) {
        try {
            materialService.deleteMaterialById(materialId);
            return R.success("删除"+materialId+"的材料");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSingleMaterial")
    public R<Material> createSingleMaterial(Material material) {
        try {
            Material singleMaterial = materialService.createSingleMaterial(material);
            System.out.println(singleMaterial);
            return R.success(singleMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createMaterial")
    public R<Material> createMaterial(@RequestBody Material material) {
        try {
            System.out.println(material);
            Material newMaterial = materialService.createMaterial(material);
            System.out.println(newMaterial);
            return R.success(newMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateMaterial")
    public R<Material> updateMaterial(@RequestBody Material material) {
        try {
            System.out.println(material);
            Material newMaterial = materialService.updateMaterial(material);
            System.out.println(newMaterial);
            return R.success(newMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createAnalysisSpectrogram")
    public R<AnalysisSpectrogram> createAnalysisSpectrogram(@RequestBody AMvo AM) {
        try {
            AnalysisSpectrogram spectrogram = materialService.createAnalysisSpectrogram(AM);
            System.out.println(spectrogram);
            return R.success(spectrogram);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateAnalysisSpectrogram")
    public R<AnalysisSpectrogram> updateAnalysisSpectrogram(@RequestBody AnalysisSpectrogram Spectrogram) {
        try {
            AnalysisSpectrogram newSpectrogram = materialService.updateAnalysisSpectrogram(Spectrogram);
            System.out.println(newSpectrogram);
            return R.success(newSpectrogram);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAnalysisSpectrogramByMaterialId/{materialId}")
    public R<Collection<AnalysisSpectrogram>> getAnalysisSpectrogramByMaterialId(@PathVariable Long materialId) {
        try {
            Collection<AnalysisSpectrogram> spectrograms = materialService.getAnalysisSpectrogramByMaterialId(materialId);
            System.out.println(spectrograms);
            return R.success(spectrograms);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAnalysisSpectrogramById/{spectrogramId}")
    public R<AnalysisSpectrogram> getAnalysisSpectrogramById(@PathVariable Long spectrogramId) {
        try {
            AnalysisSpectrogram spectrogram = materialService.getAnalysisSpectrogramById(spectrogramId);
            System.out.println(spectrogram);
            return R.success(spectrogram);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAnalysisSpectrogramByName/{spectrogramName}")
    public R<Collection<AnalysisSpectrogram>> getAnalysisSpectrogramByName(@PathVariable String spectrogramName) {
        try {
            Collection<AnalysisSpectrogram> spectrograms = materialService.getAnalysisSpectrogramByName(spectrogramName);
            System.out.println(spectrograms);
            return R.success(spectrograms);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteAnalysisSpectrogramById/{spectrogramId}")
    public R<String> deleteAnalysisSpectrogramById(@PathVariable Long spectrogramId) {
        try {
            materialService.deleteAnalysisSpectrogramById(spectrogramId);
            return R.success("删除"+spectrogramId+"的理化分析谱图节点");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createChemicalProperty")
    public R<ChemicalProperty> createChemicalProperty(@RequestBody CMvo CM) {
        try {
            ChemicalProperty property = materialService.createChemicalProperty(CM);
            System.out.println(property);
            return R.success(property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateChemicalProperty")
    public R<ChemicalProperty> updateChemicalProperty(@RequestBody ChemicalProperty property) {
        try {
            ChemicalProperty newProperty = materialService.updateChemicalProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getChemicalPropertyByMaterialId/{materialId}")
    public R<ChemicalProperty> getChemicalPropertyByMaterialId(@PathVariable Long materialId) {
        try {
            ChemicalProperty Property = materialService.getChemicalPropertyByMaterialId(materialId);
            System.out.println(Property);
            return R.success(Property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getChemicalPropertyById/{propertyId}")
    public R<ChemicalProperty> getChemicalPropertyById(@PathVariable Long propertyId) {
        try {
            ChemicalProperty Property = materialService.getChemicalPropertyById(propertyId);
            System.out.println(Property);
            return R.success(Property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteChemicalProperty/{propertyId}")
    public R<String> deleteChemicalProperty(@PathVariable Long propertyId) {
        try {
            materialService.deleteChemicalPropertyById(propertyId);
            return R.success("删除"+propertyId+"的化学性质节点");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPhysicalProperty")
    public R<PhysicalProperty> createPhysicalProperty(@RequestBody PMvo PM) {
        try {
            PhysicalProperty property = materialService.createPhysicalProperty(PM);
            System.out.println(property);
            return R.success(property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updatePhysicalProperty")
    public R<PhysicalProperty> updatePhysicalProperty(@RequestBody PhysicalProperty property) {
        try {
            PhysicalProperty newProperty = materialService.updatePhysicalProperty(property);
            System.out.println(newProperty);
            return R.success(newProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getPhysicalPropertyByMaterialId/{materialId}")
    public R<PhysicalProperty> getPhysicalPropertyByMaterialId(@PathVariable Long materialId) {
        try {
            PhysicalProperty Property = materialService.getPhysicalPropertyByMaterialId(materialId);
            System.out.println(Property);
            return R.success(Property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getPhysicalPropertyByName/{propertyName}")
    public R<Collection<PhysicalProperty>> getPhysicalPropertyByName(@PathVariable String propertyName) {
        try {
            Collection<PhysicalProperty> properties = materialService.getPhysicalPropertyByName(propertyName);
            System.out.println(properties);
            return R.success(properties);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getPhysicalPropertyById/{propertyId}")
    public R<PhysicalProperty> getPhysicalPropertyById(@PathVariable Long propertyId) {
        try {
            PhysicalProperty property = materialService.getPhysicalPropertyById(propertyId);
            System.out.println(property);
            return R.success(property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deletePhysicalProperty/{propertyId}")
    public R<PhysicalProperty> deletePhysicalProperty(@PathVariable Long propertyId) {
        try {
            materialService.deletePhysicalPropertyById(propertyId);
            return R.success("删除"+propertyId+"的物理性质节点");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createDanger")
    public R<Danger> createDanger(@RequestBody DMvo DM) {
        try {
            Danger danger = materialService.createDanger(DM);
            System.out.println(danger);
            return R.success(danger);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateDanger")
    public R<Danger> updateDanger(@RequestBody Danger danger) {
        try {
            Danger newDanger = materialService .updateDanger(danger);
            System.out.println(newDanger);
            return R.success(newDanger);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getDangerById/{dangerId}")
    public R<Danger> getDangerById(@PathVariable Long dangerId) {
        try {
            System.out.println(dangerId);
            Danger danger = materialService.getDangerById(dangerId);
            return R.success(danger);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteDanger/{dangerId}")
    public R<String> deleteDangerById(@PathVariable Long dangerId) {
        try {
            System.out.println(dangerId);
            materialService.deleteDangerById(dangerId);
            return R.success(dangerId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getDangersByMaterialId/{materialId}")
    public R<List<Danger>> getDangersByMaterialId(@PathVariable Long materialId) {
        try {
            List<Danger> dangers = new ArrayList<>(materialService.getDangersByMaterialId(materialId));
            System.out.println(dangers);
            return R.success(dangers);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createProtection")
    public R<Protection> createProtection(@RequestBody ProMVO ProM) {
        try {
            Protection protection = materialService.createProtection(ProM);
            System.out.println(protection);
            return R.success(protection);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProtection")
    public R<Protection> updateProtection(@RequestBody Protection protection) {
        try {
            Protection newProtection = materialService.updateProtection(protection);
            System.out.println(newProtection);
            return R.success(newProtection);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProtectionsByMaterialId/{materialId}")
    public R<List<Protection>> getProtectionsByMaterialId(@PathVariable Long materialId) {
        try {
            List<Protection> protections = new ArrayList<>(materialService.getProtectionsByMaterialId(materialId));
            System.out.println(protections);
            return R.success(protections);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteProtection/{protectionId}")
    public R<String> deleteProtection(@PathVariable Long protectionId) {
        try {
            System.out.println(protectionId);
            materialService.deleteProtectionById(protectionId);
            return R.success(protectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createInspectProjects")
    public R<Set<InspectProject>> createInspectProjects(@RequestBody IMvo IM) {
        try {
            Set<InspectProject> projects = materialService.createInspectProjects(IM);
            System.out.println(projects);
            return R.success(projects);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createInspectProject/{materialId}")
    public R<InspectProject> createInspectProject(@PathVariable Long materialId) {
        try {
            InspectProject project = materialService.createInspectProject(materialId, new InspectProject());
            System.out.println(project);
            return R.success(project);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectProject")
    public R<InspectProject> updateInspectProject(@RequestBody InspectProject project) {
        try {
            InspectProject newProject = materialService.updateInspectProject(project);
            System.out.println(newProject);
            return R.success(newProject);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectProjects")
    public R<List<InspectProject>> updateInspectProjects(@RequestBody List<InspectProject> projects) {
        try {
            List<InspectProject> newProjects = materialService.updateInspectProjects(projects);
            System.out.println(newProjects);
            return R.success(newProjects);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectProjectsByMaterialId/{materialId}")
    public R<List<InspectProject>> getInspectProjectsByMaterialId(@PathVariable Long materialId) {
        try {
            ArrayList<InspectProject> inspectProjects = new ArrayList<>(materialService.getInspectProjectsByMaterialId(materialId));
            System.out.println(inspectProjects);
            return R.success(inspectProjects);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteInspectProject/{projectId}")
    public R<String> deleteInspectProjectById(@PathVariable Long projectId) {
        try {
            System.out.println(projectId);
            materialService.deleteInspectProjectById(projectId);
            return R.success(projectId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getInspectProjectById/{projectId}")
    public R<InspectProject> getInspectProjectById(@PathVariable Long projectId) {
        try {
            System.out.println(projectId);
            InspectProject project = materialService.getInspectProjectById(projectId);
            return R.success(project);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createSingleProduceMethod/{materialId}")
    public R<ProduceMethod> createSingleProduceMethod(@PathVariable Long materialId) {
        try {
            PMMvo mvo = new PMMvo(materialId,new ProduceMethod());
            ProduceMethod produceMethod = materialService.createProduceMethod(mvo);
            System.out.println(produceMethod);
            return R.success(produceMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createProduceMethod")
    public R<ProduceMethod> createProduceMethod(@RequestBody PMMvo PMM) {
        try {
            ProduceMethod produceMethod = materialService.createProduceMethod(PMM);
            System.out.println(produceMethod);
            return R.success(produceMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProduceMethod")
    public R<ProduceMethod> updateProduceMethod(@RequestBody ProduceMethod method) {
        try {
            ProduceMethod produceMethod = materialService.updateProduceMethod(method);
            System.out.println(produceMethod);
            return R.success(produceMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProduceMethodsByMaterialId/{materialId}")
    public R<List<ProduceMethodInterface>> getProduceMethodsByMaterialId(@PathVariable Long materialId) {
        try {
            ArrayList<ProduceMethodInterface> methods = new ArrayList<>(materialService.getProduceMethodsByMaterialId(materialId));
            System.out.println(methods);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteProduceMethodById/{methodId}")
    public R<String> deleteProduceMethodById(@PathVariable Long methodId) {
        try {
            System.out.println(methodId);
            materialService.deleteProduceMethodById(methodId);
            return R.success(methodId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProduceMethodById/{methodId}")
    public R<ProduceMethodInterface> getProduceMethodById(@PathVariable Long methodId) {
        try {
            System.out.println(methodId);
            ProduceMethodInterface produceMethod = materialService.getProduceMethodInterfaceById(methodId);
            return R.success(produceMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProduceMethodByName/{methodName}")
    public R<Collection<ProduceMethodInterface>> getProduceMethodByName(@PathVariable String methodName) {
        try {
            Collection<ProduceMethodInterface> methods = materialService.getProduceMethodsByName(methodName);
            return R.success(methods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createSingleProtection/{materialId}")
    public R<Protection> createSingleProtection(@PathVariable Long materialId) {
        try {
            ProMVO mvo = new ProMVO(materialId,new Protection());
            Protection Protection = materialService.createProtection(mvo);
            System.out.println(Protection);
            return R.success(Protection);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    

    @ResponseBody
    @GetMapping("/deleteProtectionById/{methodId}")
    public R<String> deleteProtectionById(@PathVariable Long methodId) {
        try {
            System.out.println(methodId);
            materialService.deleteProtectionById(methodId);
            return R.success(methodId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProtectionByName/{protectionName}")
    public R<Collection<Protection>> getProtectionByName(@PathVariable String protectionName) {
        try {
            Collection<Protection> protections = materialService.getProtectionsByName(protectionName);
            return R.success(protections);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProtectionById/{protectionId}")
    public R<Protection> getProtectionById(@PathVariable Long  protectionId) {
        try {
            Protection protection = materialService.getProtectionById(protectionId);
            return R.success(protection);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createStorageRequirement")
    public R<StorageRequirement> createStorageRequirement(@RequestBody SMvo SM) {
        try {
            StorageRequirement StorageRequirement = materialService.createStorageRequirement(SM);
            System.out.println(StorageRequirement);
            return R.success(StorageRequirement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateStorageRequirement")
    public R<StorageRequirement> updateStorageRequirement(@RequestBody StorageRequirement StorageRequirement) {
        try {
            StorageRequirement newStorageRequirement = materialService.updateStorageRequirement(StorageRequirement);
            System.out.println(newStorageRequirement);
            return R.success(newStorageRequirement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteStorageRequirement/{storageRequirementId}")
    public R<String> deleteStorageRequirementById(@PathVariable Long storageRequirementId) {
        try {
            System.out.println(storageRequirementId);
            materialService.deleteStorageRequirementById(storageRequirementId);
            return R.success(storageRequirementId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStorageRequirementById/{storageRequirementId}")
    public R<StorageRequirement> getStorageRequirementById(@PathVariable Long storageRequirementId) {
        try {
            StorageRequirement requirement = materialService.getStorageRequirementById(storageRequirementId);
            return R.success(requirement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStorageRequirementByName/{storageRequirementName}")
    public R<Collection<StorageRequirement>> getStorageRequirementByName(@PathVariable String storageRequirementName) {
        try {
            Collection<StorageRequirement> requirements = materialService.getStorageRequirementsByName(storageRequirementName);
            return R.success(requirements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getStorageRequirementsByMaterialId/{materialId}")
    public R<List<StorageRequirement>> getStorageRequirementsByMaterialId(@PathVariable Long materialId) {
        try {
            List<StorageRequirement> StorageRequirements = new ArrayList<>(materialService.getStorageRequirementsByMaterialId(materialId));
            System.out.println(StorageRequirements);
            return R.success(StorageRequirements);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createSingleStorageRequirement/{materialId}")
    public R<StorageRequirement> createSingleStorageRequirement(@PathVariable Long materialId) {
        try {
            SMvo mvo = new SMvo(materialId,new StorageRequirement());
            StorageRequirement StorageRequirement = materialService.createStorageRequirement(mvo);
            System.out.println(StorageRequirement);
            return R.success(StorageRequirement);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
