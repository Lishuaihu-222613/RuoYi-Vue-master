package com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;

import java.util.Objects;
import java.util.Set;

public class LeafForParent {

    private Long parentId;

    private ClassificationTree subLeaf;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public ClassificationTree getSubLeaf() {
        return subLeaf;
    }

    public void setSubLeaf(ClassificationTree subLeaf) {
        this.subLeaf = subLeaf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeafForParent that = (LeafForParent) o;
        return Objects.equals(parentId, that.parentId) && Objects.equals(subLeaf, that.subLeaf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, subLeaf);
    }

    @Override
    public String toString() {
        return "LeafForParent{" +
                "parentId=" + parentId +
                ", subLeaf=" + subLeaf +
                '}';
    }
}
