package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge.InspectionMethod;
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

    @Property(name = "ClassificationAndIndicators")
    private Map<String, String> ClassificationAndIndicators;

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
        return ClassificationAndIndicators;
    }

    public void setClassificationAndIndicators(Map<String, String> classificationAndIndicators) {
        ClassificationAndIndicators = classificationAndIndicators;
    }

    public Set<InspectionMethod> getInspectionMethods() {
        return InspectionMethods;
    }

    public void setInspectionMethods(Set<InspectionMethod> inspectionMethods) {
        InspectionMethods = inspectionMethods;
    }

    public InspectProject() {
    }

    public InspectProject(Long projectId, String projectName, Map<String, String> classificationAndIndicators, Set<InspectionMethod> inspectionMethods) {
        this.projectId = projectId;
        this.projectName = projectName;
        ClassificationAndIndicators = classificationAndIndicators;
        InspectionMethods = inspectionMethods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectProject that = (InspectProject) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(ClassificationAndIndicators, that.ClassificationAndIndicators) && Objects.equals(InspectionMethods, that.InspectionMethods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, ClassificationAndIndicators, InspectionMethods);
    }

    @Override
    public String toString() {
        return "InspectProject{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", ClassificationAndIndicators=" + ClassificationAndIndicators +
                ", InspectionMethods=" + InspectionMethods +
                '}';
    }
}
