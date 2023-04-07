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
    ClassificationTree updateTree(ClassificationTree tree);

    ClassificationTree addSubLeafs(LeafForParent leafs);

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
