package com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;

import java.util.Set;

public class LeafForParent {

    private Long parentId;

    private Set<ClassificationTree> subLeafs;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<ClassificationTree> getSubLeafs() {
        return subLeafs;
    }

    public void setSubLeafs(Set<ClassificationTree> subLeafs) {
        this.subLeafs = subLeafs;
    }

    public LeafForParent() {
    }

    public LeafForParent(Long parentId, Set<ClassificationTree> subLeafs) {
        this.parentId = parentId;
        this.subLeafs = subLeafs;
    }

    @Override
    public String toString() {
        return "leafForParent{" +
                "parentId=" + parentId +
                ", subLeafs=" + subLeafs +
                '}';
    }
}
