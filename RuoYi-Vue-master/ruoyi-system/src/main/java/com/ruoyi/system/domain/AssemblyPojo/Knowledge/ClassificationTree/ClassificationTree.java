package com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"分类树","treeNode"})
public class ClassificationTree {

    @Id
    @GeneratedValue
    private Long leafId;

    @Property(name = "label")
    private String leafName;

    @Property(name = "节点描述")
    private String leafDescription;

    @Property(name = "节点值")
    private String leafValue;

    @Property(name = "节点属性")
    private List<String> leafAttributes;

    @Property(name = "节点要求")
    private List<String> leafRequirements;

    @Property(name = "存在子节点")
    private boolean hasSubLeafs;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Relationship(type = "hasSubLeaf" , direction = Relationship.Direction.OUTGOING)
    private Set<ClassificationTree> subLeafs;

    public Long getLeafId() {
        return leafId;
    }

    public void setLeafId(Long leafId) {
        this.leafId = leafId;
    }

    public String getLeafName() {
        return leafName;
    }

    public void setLeafName(String leafName) {
        this.leafName = leafName;
    }

    public String getLeafDescription() {
        return leafDescription;
    }

    public void setLeafDescription(String leafDescription) {
        this.leafDescription = leafDescription;
    }

    public List<String> getLeafAttributes() {
        return leafAttributes;
    }

    public void setLeafAttributes(List<String> leafAttributes) {
        this.leafAttributes = leafAttributes;
    }

    public List<String> getLeafRequirements() {
        return leafRequirements;
    }

    public void setLeafRequirements(List<String> leafRequirements) {
        this.leafRequirements = leafRequirements;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Set<ClassificationTree> getSubLeafs() {
        return subLeafs;
    }

    public void setSubLeafs(Set<ClassificationTree> subLeafs) {
        this.subLeafs = subLeafs;
    }

    public boolean isHasSubLeafs() {
        return hasSubLeafs;
    }

    public void setHasSubLeafs(boolean hasSubLeafs) {
        this.hasSubLeafs = hasSubLeafs;
    }

    public String getLeafValue() {
        return leafValue;
    }

    public void setLeafValue(String leafValue) {
        this.leafValue = leafValue;
    }

    public ClassificationTree() {
    }

    public ClassificationTree(Long leafId, String leafName, String leafDescription, String leafValue, List<String> leafAttributes, List<String> leafRequirements, boolean hasSubLeafs, Set<String> dynamicLabels, Set<ClassificationTree> subLeafs) {
        this.leafId = leafId;
        this.leafName = leafName;
        this.leafDescription = leafDescription;
        this.leafValue = leafValue;
        this.leafAttributes = leafAttributes;
        this.leafRequirements = leafRequirements;
        this.hasSubLeafs = hasSubLeafs;
        this.dynamicLabels = dynamicLabels;
        this.subLeafs = subLeafs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationTree that = (ClassificationTree) o;
        return hasSubLeafs == that.hasSubLeafs && Objects.equals(leafId, that.leafId) && Objects.equals(leafName, that.leafName) && Objects.equals(leafDescription, that.leafDescription) && Objects.equals(leafValue, that.leafValue) && Objects.equals(leafAttributes, that.leafAttributes) && Objects.equals(leafRequirements, that.leafRequirements) && Objects.equals(dynamicLabels, that.dynamicLabels) && Objects.equals(subLeafs, that.subLeafs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafId, leafName, leafDescription, leafValue, leafAttributes, leafRequirements, hasSubLeafs, dynamicLabels, subLeafs);
    }

    @Override
    public String toString() {
        return "ClassificationTree{" +
                "leafId=" + leafId +
                ", leafName='" + leafName + '\'' +
                ", leafDescription='" + leafDescription + '\'' +
                ", leafValue='" + leafValue + '\'' +
                ", leafAttributes=" + leafAttributes +
                ", leafRequirements=" + leafRequirements +
                ", hasSubLeafs=" + hasSubLeafs +
                ", dynamicLabels=" + dynamicLabels +
                ", subLeafs=" + subLeafs +
                '}';
    }
}
