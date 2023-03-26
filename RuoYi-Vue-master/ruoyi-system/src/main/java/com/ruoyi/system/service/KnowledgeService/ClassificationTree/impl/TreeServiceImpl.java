package com.ruoyi.system.service.KnowledgeService.ClassificationTree.impl;

import com.ruoyi.system.Repository.KnowledgeRepository.ClassificationTree.TreeRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import com.ruoyi.system.service.KnowledgeService.ClassificationTree.TreeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Resource
    private TreeRepository treeRepository;

    @Override
    public ClassificationTree selectTreeById(Long treeId) {
        return treeRepository.findById(treeId).get();
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
    public ClassificationTree updateTree(ClassificationTree tree) {
        return treeRepository.save(tree);
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
