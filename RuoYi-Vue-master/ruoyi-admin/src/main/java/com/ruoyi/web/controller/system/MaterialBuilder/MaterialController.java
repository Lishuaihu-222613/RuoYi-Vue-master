package com.ruoyi.web.controller.system.MaterialBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.service.KnowledgeService.Material.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Resource
    private MaterialService materialService;

    @Resource
    private AnalysisSpectrogramService analysisSpectrogramService;

    @Resource
    private ChemicalPropertyService chemicalPropertyService;

    @Resource
    private DangerService dangerService;

    @Resource
    private InspectProjectService inspectProjectService;

    @Resource
    private PhysicalPropertyService physicalPropertyService;

    @Resource
    private ProduceMethodService produceMethodService;

    @Resource
    private ProtectionService protectionService;

    @Resource
    private StorageRequirementService storageRequirementService;

    @ResponseBody
    @GetMapping("/getMaterialsByType/{dynamicLabel}")
    public R<List<MaterialInterface>> getMaterialsByType(@PathVariable String dynamicLabel) {
        try {
            List<MaterialInterface> materials = materialService.getMaterialsByType(dynamicLabel);
            System.out.println(materials);
            return R.success(materials);
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
    @PostMapping("/createSingleMaterial")
    public R<MaterialInterface> createSingleMaterial(Material material) {
        try {
            MaterialInterface singleMaterial = (MaterialInterface) materialService.createSingleMaterial(material);
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
    @PostMapping("/updateAnalysisSpectrogram")
    public R<AnalysisSpectrogram> updateAnalysisSpectrogram(@RequestBody AnalysisSpectrogram Spectrogram) {
        try {
            AnalysisSpectrogram newSpectrogram = analysisSpectrogramService.updateAnalysisSpectrogram(Spectrogram);
            System.out.println(newSpectrogram);
            return R.success(newSpectrogram);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAnalysisSpectrogramByMaterialId/{materialId}")
    public R<AnalysisSpectrogram> getAnalysisSpectrogramByMaterialId(@PathVariable Long materialId) {
        try {
            AnalysisSpectrogram newSpectrogram = analysisSpectrogramService.getAnalysisSpectrogramByMaterialId(materialId).stream().findFirst().get();
            System.out.println(newSpectrogram);
            return R.success(newSpectrogram);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateChemicalProperty")
    public R<ChemicalProperty> updateChemicalProperty(@RequestBody ChemicalProperty property) {
        try {
            ChemicalProperty newProperty = chemicalPropertyService.updateChemicalProperty(property);
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
            ChemicalProperty Property = chemicalPropertyService.getChemicalPropertyByMaterialId(materialId).stream().findFirst().get();
            System.out.println(Property);
            return R.success(Property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updatePhysicalProperty")
    public R<PhysicalProperty> updatePhysicalProperty(@RequestBody PhysicalProperty property) {
        try {
            PhysicalProperty newProperty = physicalPropertyService.updatePhysicalProperty(property);
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
            PhysicalProperty Property = physicalPropertyService.getPhysicalPropertyByMaterialId(materialId).stream().findFirst().get();
            System.out.println(Property);
            return R.success(Property);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createDanger/{materialId}")
    public R<Danger> createDanger(@PathVariable Long materialId) {
        try {
            Danger newDanger = dangerService.createDanger(new Danger());
            dangerService.createRelationshipForDanger(materialId, newDanger.getDangerId());
            System.out.println(newDanger);
            return R.success(newDanger);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateDanger")
    public R<Danger> updateDanger(@RequestBody Danger danger) {
        try {
            Danger newDanger = dangerService.updateDanger(danger);
            System.out.println(newDanger);
            return R.success(newDanger);
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
            dangerService.deleteDangerById(dangerId);
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
            List<Danger> dangers = new ArrayList<>(dangerService.getDangersByMaterialId(materialId));
            System.out.println(dangers);
            return R.success(dangers);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createProtection/{materialId}")
    public R<Protection> createProtection(@PathVariable Long materialId) {
        try {
            Protection newProtection = protectionService.createProtection(new Protection());
            protectionService.createRelationshipForProtection(materialId, newProtection.getProtectionId());
            System.out.println(newProtection);
            return R.success(newProtection);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProtection")
    public R<Protection> updateProtection(@RequestBody Protection protection) {
        try {
            Protection newProtection = protectionService.updateProtection(protection);
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
            List<Protection> protections = new ArrayList<>(protectionService.getProtectionsByMaterialId(materialId));
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
            protectionService.deleteProtectionById(protectionId);
            return R.success(protectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createInspectProject/{materialId}")
    public R<InspectProject> createInspectProject(@PathVariable Long materialId) {
        try {
            InspectProject newProject = inspectProjectService.updateInspectProject(new InspectProject());
            inspectProjectService.createRelationshipForInspectProject(materialId, newProject.getProjectId());
            System.out.println(newProject);
            return R.success(newProject);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectProject")
    public R<InspectProject> updateInspectProject(@RequestBody InspectProject project) {
        try {
            InspectProject newProject = inspectProjectService.updateInspectProject(project);
            System.out.println(newProject);
            return R.success(newProject);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateInspectProjects")
    public R<List<InspectProject>> updateInspectProject(@RequestBody List<InspectProject> projects) {
        try {
            List<InspectProject> newProjects = inspectProjectService.updateInspectProjects(projects);
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
            ArrayList<InspectProject> inspectProjects = new ArrayList<>(inspectProjectService.getInspectProjectsByMaterialId(materialId));
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
            inspectProjectService.deleteInspectProjectById(projectId);
            return R.success(projectId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/createProduceMethod/{materialId}")
    public R<ProduceMethod> createProduceMethod(@PathVariable Long materialId) {
        try {
            ProduceMethod newMethod = produceMethodService.createProduceMethod(new ProduceMethod());
            produceMethodService.createRelationshipForProduceMethod(materialId, newMethod.getMethodId());
            System.out.println(newMethod);
            return R.success(newMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateProduceMethod")
    public R<ProduceMethodInterface> updateProduceMethod(@RequestBody ProduceMethod method) {
        try {
            ProduceMethodInterface newMethod = (ProduceMethodInterface) produceMethodService.updateProduceMethod(method);
            System.out.println(newMethod);
            return R.success(newMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getProduceMethodsByMaterialId/{materialId}")
    public R<List<ProduceMethodInterface>> getProduceMethodsByMaterialId(@PathVariable Long materialId) {
        try {
            ArrayList<ProduceMethodInterface> methods = new ArrayList<>(produceMethodService.getProduceMethodsByMaterialId(materialId));
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
            produceMethodService.deleteProduceMethodById(methodId);
            return R.success(methodId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
