package com.ruoyi.system.service.KnowledgeService.ClassificationTree;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo.LeafForParent;

import java.util.List;

public interface TreeService {

    /**
     *
     * @param treeId
     * @return
     */
    ClassificationTree selectTreeById(Long treeId);

    List<ClassificationTree> selectAllRoot();

    List<ClassificationTree> selectSubLeafs(Long parentId);

    /**
     *
     * @param tree
     * @return
     */
    List<ClassificationTree> selectTreeList(ClassificationTree tree);

    /**
     *
     * @param tree
     * @return
     */
    ClassificationTree createTree(ClassificationTree tree);

    /**
     *
     * @param tree
     * @return
     */
    ClassificationTree updateTree(LeafForParent leaf);

    ClassificationTree getParentLeaf(Long leafId);

    ClassificationTree addSubLeafs(LeafForParent leaf);

    /**
     * @param treeIds
     * @return
     */
    int deleteTree(Long[] treeIds);

    /**
     *
     * @param treeId
     */
    void deleteTreeByTreeId(Long treeId);
}
