package com.ruoyi.system.service.KnowledgeService.ClassificationTree;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;

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

    /**
     *
     * @param treeIds
     */
    void deleteTree(Long[] treeIds);

    /**
     *
     * @param treeId
     */
    void deleteTreeByTreeId(Long treeId);
}
