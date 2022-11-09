package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.InspectProjectRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.InspectProjectInterface;
import com.ruoyi.system.service.KnowledgeService.Material.InspectProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InspectProjectServiceImpl implements InspectProjectService {

    @Autowired
    private InspectProjectRepository inspectProjectRepository;

    @Override
    public InspectProject createInspectProject(InspectProject project) {
        return inspectProjectRepository.save(project);
    }

    @Override
    public InspectProjectInterface createSingleInspectProject(InspectProject project) {
        project.setInspectionMethods(null);
        project.setAssociatedFiles(null);
        return (InspectProjectInterface) inspectProjectRepository.save(project);
    }

    @Override
    public InspectProject getInspectProjectById(Long projectId) {
        return inspectProjectRepository.findById(projectId).get();
    }

    @Override
    public InspectProjectInterface getInspectProjectInterfaceById(Long projectId) {
        return (InspectProjectInterface) inspectProjectRepository.findById(projectId).get();
    }

    @Override
    public Collection<InspectProject> getInspectProjectsByMaterialId(Long materialId) {
        return inspectProjectRepository.findInspectProjectsByMaterialId(materialId);
    }

    @Override
    public void deleteInspectProject(InspectProject project) {
        inspectProjectRepository.delete(project);
    }

    @Override
    public void deleteInspectProjectById(Long projectId) {
        inspectProjectRepository.deleteById(projectId);
    }
}
