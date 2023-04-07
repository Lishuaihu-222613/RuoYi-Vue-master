package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.InspectProjectInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MaterialService {

    /**
     * 获得所有材料
     * @return
     */
    Page<MaterialInterface> getAllMaterials(Pageable pageable);

    /**
     * 根据类型获得材料
     * @param dynamicLabel
     * @return
     */
    Page<MaterialInterface> getMaterialsByType(String dynamicLabel, Pageable pageable);

    /**
     * 根据Id获得某材料完整信息
     * @param materialId
     * @return
     */
    MaterialInterface getMaterialById(Long materialId);

    /**
     * 创建材料
     * @param material
     * @return
     */
    Material createMaterial(Material material);

    /**
     * 创建单个材料节点
     * @param singleMaterial
     * @return
     */
    Material createSingleMaterial(Material singleMaterial);

    /**
     * 根据ID删除材料
     * @param MaterialId
     */
    void deleteMaterialById(Long MaterialId);

    /**
     * 更新材料
     * @param newMaterial
     * @return
     */
    Material updateMaterial(Material newMaterial);

    /**
     * 创建理化分析图谱节点
     * @param AM
     * @return
     */
    AnalysisSpectrogram createAnalysisSpectrogram(AMvo AM);

    /**
     * 根据id检索理化图谱分析节点
     * @param spectrogramId
     * @return
     */
    AnalysisSpectrogram getAnalysisSpectrogramById(Long spectrogramId);

    /**
     * 根据材料Id获得关联理化分析谱图节点
     * @param materialId
     * @return
     */
    Collection<AnalysisSpectrogram> getAnalysisSpectrogramByMaterialId(Long materialId);

    /**
     * 更新理化分析谱图节点
     * @param analysisSpectrogram
     * @return
     */
    AnalysisSpectrogram updateAnalysisSpectrogram(AnalysisSpectrogram analysisSpectrogram);

    /**
     *  依据Id删除节点
     * @param spectrogramId
     */
    void deleteAnalysisSpectrogramById(Long spectrogramId);

    /**
     * 依据名称查询理化分析谱图节点
     * @param spectrogramName
     * @return
     */
    Collection<AnalysisSpectrogram> getAnalysisSpectrogramByName(String spectrogramName);

    /**
     * 创建化学性质节点
     * @param CM
     * @return
     */
    ChemicalProperty createChemicalProperty(CMvo CM);

    /**
     * 根据Id查询
     * @param propertyId
     * @return
     */
    ChemicalProperty getChemicalPropertyById(Long propertyId);

    /**
     * 根据材料查询节点
     * @param materialId
     * @return
     */
    ChemicalProperty getChemicalPropertyByMaterialId(Long materialId);

    /**
     * 更新化学性质节点
     * @param property
     * @return
     */
    ChemicalProperty updateChemicalProperty(ChemicalProperty property);

    /**
     * 根据Id删除节点
     * @param propertyId
     */
    void deleteChemicalPropertyById(Long propertyId);

    /**
     * 创建物理性质节点
     * @param PM
     * @return
     */
    PhysicalProperty createPhysicalProperty(PMvo PM);

    /**
     * 根据材料查询物理性质节点
     * @param materialId
     * @return
     */
    PhysicalProperty getPhysicalPropertyByMaterialId(Long materialId);

    /**
     * 根据名称查询物理性质节点
     * @param propertyName
     * @return
     */
    Collection<PhysicalProperty> getPhysicalPropertyByName(String propertyName);

    /**
     * 查询物理性质节点
     * @param propertyId
     * @return
     */
    PhysicalProperty getPhysicalPropertyById(Long propertyId);

    /**
     * 更新物理性质节点
     * @param property
     * @return
     */
    PhysicalProperty updatePhysicalProperty(PhysicalProperty property);


    /**
     * 根据ID删除节点
     * @param propertyId
     */
    void deletePhysicalPropertyById(Long propertyId);

    /**
     * 根据材料获得关联危险项
     * @param materialId
     * @return
     */
    Collection<Danger> getDangersByMaterialId(Long materialId);

    /**
     * 根据ID获得危险项
     * @param dangerId
     * @return
     */
    Danger getDangerById(Long dangerId);

    /**
     * 创建危险项
     * @param DM
     * @return
     */
    Danger createDanger(DMvo DM);

    /**
     * 更新危险项
     * @param danger
     * @return
     */
    Danger updateDanger(Danger danger);

    /**
     * 根据ID删除危险项
     * @param dangerId
     */
    void deleteDangerById(Long dangerId);

    void createRelationshipForDanger(Long materialId, Long dangerId);

    /**
     * 创建检测项目
     * @param IM
     * @return
     */
    Set<InspectProject> createInspectProjects(IMvo IM);

    InspectProject createInspectProject(Long materialId,InspectProject project);

    /**
     * 更新检测项目
     * @param project
     * @return
     */
    InspectProject updateInspectProject(InspectProject project);

    /**
     * 更新多个检测项目
     * @param projects
     * @return
     */
    List<InspectProject> updateInspectProjects(List<InspectProject> projects);


    /**
     * 根据id检索完整检测项目
     * @param projectId
     * @return
     */
    InspectProject getInspectProjectById(Long projectId);

    /**
     * 根据材料获得关联检测项目
     *
     * @param materialId
     * @return
     */
    Collection<InspectProject> getInspectProjectsByMaterialId(Long materialId);


    /**
     * 根据Id删除检测项目
     * @param projectId
     */
    void deleteInspectProjectById(Long projectId);

    /**
     * 创建联系
     * @param materialId
     * @param projectId
     */
    void createRelationshipForInspectProject(Long materialId, Long projectId);

    /**
     * 创建生产方法节点
     * @param PMM
     * @return
     */
    ProduceMethod createProduceMethod(PMMvo PMM);

    /**
     * 根据Id查询节点
     * @param methodId
     * @return
     */
    ProduceMethodInterface getProduceMethodInterfaceById(Long methodId);

    /**
     * 查询材料关联生产方法
     * @param materialId
     * @return
     */
    Collection<ProduceMethodInterface> getProduceMethodsByMaterialId(Long materialId);

    /**
     * 根据名称获取生产方法
     * @param methodName
     * @return
     */
    Collection<ProduceMethodInterface> getProduceMethodsByName(String methodName);

    /**
     * 更新
     * @param produceMethod
     * @return
     */
    ProduceMethod updateProduceMethod(ProduceMethod produceMethod);

    /**
     * 依据Id删除
     * @param methodId
     */
    void deleteProduceMethodById(Long methodId);

    /**
     * 创建关联
     * @param materialId
     * @param methodId
     */
    void createRelationshipForProduceMethod(Long materialId, Long methodId);

    /**
     * 创建安全要求节点
     * @param PM
     * @return
     */
    Protection createProtection(ProMVO PM);

    /**
     * 检索材料关联安全要求
     * @param materialId
     * @return
     */
    Collection<Protection> getProtectionsByMaterialId(Long materialId);

    /**
     * 根据名称检索
     * @param protectionName
     * @return
     */
    Collection<Protection> getProtectionsByName(String protectionName);

    /**
     * 根据Id检索
     * @param protectionId
     * @return
     */
    Protection getProtectionById(Long protectionId);

    /**
     * 更新
     * @param protection
     * @return
     */
    Protection updateProtection(Protection protection);

    /**
     * 根据Id删除
     * @param protectionId
     */
    void deleteProtectionById(Long protectionId);

    /**
     * 创建联系
     * @param materialId
     * @param protectionId
     */
    void createRelationshipForProtection(Long materialId, Long protectionId);

    /**
     * 创建存储要求
     * @param SM
     * @return
     */
    StorageRequirement createStorageRequirement(SMvo SM);

    /**
     * 获得材料关联存储要求
     * @param materialId
     * @return
     */
    Collection<StorageRequirement> getStorageRequirementsByMaterialId(Long materialId);

    /**
     * 根据名称获得存储要求
     * @param requirementName
     * @return
     */
    Collection<StorageRequirement> getStorageRequirementsByName(String requirementName);

    /**
     * 根据Id获得存储要求
     * @param requirementId
     * @return
     */
    StorageRequirement getStorageRequirementById(Long requirementId);

    /**
     * 更新存储要求
     * @param requirement
     * @return
     */
    StorageRequirement updateStorageRequirement(StorageRequirement requirement);

    /**
     * 根据Id删除存储要求
     * @param requirementId
     */
    void deleteStorageRequirementById(Long requirementId);
}
