package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.InspectProjectInterface;

import java.util.Collection;
import java.util.List;

public interface InspectProjectService {

    /**
     * 创建检测项目
     * @param project
     * @return
     */
    InspectProject createInspectProject(InspectProject project);

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
     * 创建单个检测项目节点
     * @param project
     * @return
     */
    InspectProjectInterface createSingleInspectProject(InspectProject project);

    /**
     * 根据id检索完整检测项目
     * @param projectId
     * @return
     */
    InspectProject getInspectProjectById(Long projectId);

    /**
     * 根据id检索单个检测项目节点
     * @param projectId
     * @return
     */
    InspectProjectInterface getInspectProjectInterfaceById(Long projectId);

    /**
     * 根据材料获得关联检测项目
     *
     * @param materialId
     * @return
     */
    Collection<InspectProject> getInspectProjectsByMaterialId(Long materialId);

    /**
     * 删除检测项目
     * @param project
     */
    void deleteInspectProject(InspectProject project);

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
}
