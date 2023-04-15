package com.ruoyi.system.service.KnowledgeService.ClassificationTree.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.ClassificationTree.TreeRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo.LeafForParent;
import com.ruoyi.system.service.KnowledgeService.ClassificationTree.TreeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TreeServiceImpl implements TreeService {

    @Resource
    private TreeRepository treeRepository;

    @Override
    public ClassificationTree selectTreeById(Long treeId) {
        return treeRepository.findById(treeId).get();
    }

    @Override
    public List<ClassificationTree> selectAllRoot() {
        List<ClassificationTree> roots = new ArrayList<>();
        for (ClassificationTree rootNode : treeRepository.findAllRoot()) {
            ClassificationTree root = selectTreeById(rootNode.getLeafId());
            roots.add(root);
        }
        return roots;
    }

    @Override
    public List<ClassificationTree> selectSubLeafs(Long parentId){
        List<ClassificationTree> subleafs = treeRepository.findLeafsByParentId(parentId);
        return subleafs;
    }

    @Override
    public List<ClassificationTree> selectTreeList(ClassificationTree tree) {
        Example<ClassificationTree> of = Example.of(tree);
        return treeRepository.findAll(of);
    }

    @Override
    public ClassificationTree createTree(ClassificationTree tree) {
        return treeRepository.save(tree);
    }

    @Override
    public ClassificationTree updateTree(LeafForParent leaf) {
        Optional<ClassificationTree> optional = treeRepository.findById(leaf.getSubLeaf().getLeafId());
        if(optional.isPresent()){
            ClassificationTree oldLeaf = optional.get();
            oldLeaf.setLeafName(leaf.getSubLeaf().getLeafName());
            oldLeaf.setLeafDescription(leaf.getSubLeaf().getLeafDescription());
            oldLeaf.setLeafValue(leaf.getSubLeaf().getLeafValue());
            oldLeaf.setDynamicLabels(leaf.getSubLeaf().getDynamicLabels());
            oldLeaf.setLeafAttributes(leaf.getSubLeaf().getLeafAttributes());
            oldLeaf.setLeafRequirements(leaf.getSubLeaf().getLeafRequirements());
            oldLeaf.setHasSubLeafs(leaf.getSubLeaf().isHasSubLeafs());
            ClassificationTree newLeaf = treeRepository.save(oldLeaf);
            treeRepository.deleteParentRelationShip(leaf.getSubLeaf().getLeafId());
            treeRepository.addParentRelationShip(leaf.getSubLeaf().getLeafId(),leaf.getParentId());
            return newLeaf;
        }
        return null;
    }

    @Override
    public ClassificationTree getParentLeaf(Long leafId) {
        return treeRepository.getParentLeafById(leafId).get();
    }

    @Override
    public ClassificationTree addSubLeafs(LeafForParent leaf) {
        Optional<ClassificationTree> parentLeaf = treeRepository.findById(leaf.getParentId());
        if (parentLeaf.isPresent()){
            ClassificationTree tree = parentLeaf.get();
            ClassificationTree subLeaf = treeRepository.save(leaf.getSubLeaf());
            Set<ClassificationTree> treeSubLeafs = tree.getSubLeafs();
            treeSubLeafs.add(subLeaf);
            tree.setSubLeafs(treeSubLeafs);
            return treeRepository.save(tree);
        }
        return null;
    }

    @Override
    public int deleteTree(Long[] treeIds) {
        treeRepository.deleteAllById(Arrays.asList(treeIds));
        return 0;
    }

    @Override
    public void deleteTreeByTreeId(Long treeId) {
        treeRepository.deleteById(treeId);
    }
}
