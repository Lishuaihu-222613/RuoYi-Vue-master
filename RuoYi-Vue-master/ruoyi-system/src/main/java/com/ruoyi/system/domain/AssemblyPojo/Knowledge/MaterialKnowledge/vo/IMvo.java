package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.InspectProject;

import java.util.Set;

public class IMvo {

    private Long materialId;

    private Set<InspectProject> inspectProjects;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Set<InspectProject> getInspectProjects() {
        return inspectProjects;
    }

    public void setInspectProjects(Set<InspectProject> inspectProjects) {
        this.inspectProjects = inspectProjects;
    }

    public IMvo() {
    }

    public IMvo(Long materialId, Set<InspectProject> inspectProjects) {
        this.materialId = materialId;
        this.inspectProjects = inspectProjects;
    }

    @Override
    public String toString() {
        return "IMvo{" +
                "materialId=" + materialId +
                ", inspectProjects=" + inspectProjects +
                '}';
    }
}
