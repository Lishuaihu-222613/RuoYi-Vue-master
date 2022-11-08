package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.InspectProjectInterface;

import java.util.List;

public interface InspectProjectService {

    /**
     * 创建检测项目
     * @param project
     * @return
     */
    InspectProject createInspectProject(InspectProject project);

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
     * @param materialId
     * @return
     */
    List<InspectProjectInterface> getInspectProjectsByMaterialId(Long materialId);

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
}
