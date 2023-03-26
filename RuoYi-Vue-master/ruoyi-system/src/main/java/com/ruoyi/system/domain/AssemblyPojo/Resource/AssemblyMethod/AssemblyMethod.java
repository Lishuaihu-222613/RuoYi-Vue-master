package com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyMethod;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement.GeometricElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"装配方法","AssemblyMethod"})
public class AssemblyMethod {

    @Id
    @GeneratedValue
    private Long methodId;

    @Property(name = "label")
    private String methodName;

    @Property(name = "方法描述")
    private String methodDescription;

    @Property(name = "可达精度")
    private String reachableDimensionalAccuracy;

    @Property(name = "可达公差")
    private String reachableGeometricTolerance;

    @DynamicLabels
    private Set<String> methodType;

    @Relationship(type = "适用元素",direction = Relationship.Direction.OUTGOING)
    private Set<GeometricElement> suitableElements;

    @Relationship(type = "适合资源",direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> suitableResources;

    @Relationship(type = "适用材料",direction = Relationship.Direction.OUTGOING)
    private Set<Material> suitableMaterials;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String getReachableDimensionalAccuracy() {
        return reachableDimensionalAccuracy;
    }

    public void setReachableDimensionalAccuracy(String reachableDimensionalAccuracy) {
        this.reachableDimensionalAccuracy = reachableDimensionalAccuracy;
    }

    public String getReachableGeometricTolerance() {
        return reachableGeometricTolerance;
    }

    public void setReachableGeometricTolerance(String reachableGeometricTolerance) {
        this.reachableGeometricTolerance = reachableGeometricTolerance;
    }

    public Set<String> getMethodType() {
        return methodType;
    }

    public void setMethodType(Set<String> methodType) {
        this.methodType = methodType;
    }

    public Set<GeometricElement> getSuitableElements() {
        return suitableElements;
    }

    public void setSuitableElements(Set<GeometricElement> suitableElements) {
        this.suitableElements = suitableElements;
    }

    public Set<AssemblyResource> getSuitableResources() {
        return suitableResources;
    }

    public void setSuitableResources(Set<AssemblyResource> suitableResources) {
        this.suitableResources = suitableResources;
    }

    public Set<Material> getSuitableMaterials() {
        return suitableMaterials;
    }

    public void setSuitableMaterials(Set<Material> suitableMaterials) {
        this.suitableMaterials = suitableMaterials;
    }

    public AssemblyMethod() {
    }

    public AssemblyMethod(Long methodId, String methodName, String methodDescription, String reachableDimensionalAccuracy, String reachableGeometricTolerance, Set<String> methodType, Set<GeometricElement> suitableElements, Set<AssemblyResource> suitableResources, Set<Material> suitableMaterials) {
        this.methodId = methodId;
        this.methodName = methodName;
        this.methodDescription = methodDescription;
        this.reachableDimensionalAccuracy = reachableDimensionalAccuracy;
        this.reachableGeometricTolerance = reachableGeometricTolerance;
        this.methodType = methodType;
        this.suitableElements = suitableElements;
        this.suitableResources = suitableResources;
        this.suitableMaterials = suitableMaterials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyMethod that = (AssemblyMethod) o;
        return Objects.equals(methodId, that.methodId) && Objects.equals(methodName, that.methodName) && Objects.equals(methodDescription, that.methodDescription) && Objects.equals(reachableDimensionalAccuracy, that.reachableDimensionalAccuracy) && Objects.equals(reachableGeometricTolerance, that.reachableGeometricTolerance) && Objects.equals(methodType, that.methodType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, methodName, methodDescription, reachableDimensionalAccuracy, reachableGeometricTolerance, methodType);
    }

    @Override
    public String toString() {
        return "AssemblyMethod{" +
                "methodId=" + methodId +
                ", methodName='" + methodName + '\'' +
                ", methodDescription='" + methodDescription + '\'' +
                ", reachableDimensionalAccuracy='" + reachableDimensionalAccuracy + '\'' +
                ", reachableGeometricTolerance='" + reachableGeometricTolerance + '\'' +
                ", methodType=" + methodType +
                ", suitableElements=" + suitableElements +
                ", suitableResources=" + suitableResources +
                ", suitableMaterials=" + suitableMaterials +
                '}';
    }
}
