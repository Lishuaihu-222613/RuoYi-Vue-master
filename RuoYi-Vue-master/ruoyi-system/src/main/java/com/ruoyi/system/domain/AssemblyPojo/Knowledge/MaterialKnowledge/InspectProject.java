package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.fileKnowledge;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Node("InspectProject")
public class InspectProject {

    @Id
    @GeneratedValue
    private Long projectId;

    @Property(name = "label")
    private String projectName;

    @CompositeProperty(prefix = "Indicators")
    private Map<String, String> classificationAndIndicators;

    @Relationship(type = "hasAssociatedFile" , direction = Relationship.Direction.OUTGOING)
    private Set<fileKnowledge> associatedFiles;

    @Relationship(type = "hasInspectMethod" , direction = Relationship.Direction.OUTGOING)
    private Set<InspectionMethod> InspectionMethods;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Map<String, String> getClassificationAndIndicators() {
        return classificationAndIndicators;
    }

    public void setClassificationAndIndicators(Map<String, String> classificationAndIndicators) {
        this.classificationAndIndicators = classificationAndIndicators;
    }

    public Set<InspectionMethod> getInspectionMethods() {
        return InspectionMethods;
    }

    public void setInspectionMethods(Set<InspectionMethod> inspectionMethods) {
        InspectionMethods = inspectionMethods;
    }

    public Set<fileKnowledge> getAssociatedFiles() {
        return associatedFiles;
    }

    public void setAssociatedFiles(Set<fileKnowledge> associatedFiles) {
        this.associatedFiles = associatedFiles;
    }

    public InspectProject() {
    }

    public InspectProject(Long projectId, String projectName, Map<String, String> classificationAndIndicators, Set<fileKnowledge> associatedFiles, Set<InspectionMethod> inspectionMethods) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.classificationAndIndicators = classificationAndIndicators;
        this.associatedFiles = associatedFiles;
        InspectionMethods = inspectionMethods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectProject that = (InspectProject) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(classificationAndIndicators, that.classificationAndIndicators) && Objects.equals(associatedFiles, that.associatedFiles) && Objects.equals(InspectionMethods, that.InspectionMethods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, classificationAndIndicators, associatedFiles, InspectionMethods);
    }

    @Override
    public String toString() {
        return "InspectProject{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", classificationAndIndicators=" + classificationAndIndicators +
                ", associatedFiles=" + associatedFiles +
                ", InspectionMethods=" + InspectionMethods +
                '}';
    }
}
