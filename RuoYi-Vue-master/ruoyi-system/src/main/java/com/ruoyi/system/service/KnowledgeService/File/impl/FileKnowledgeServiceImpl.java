package com.ruoyi.system.service.KnowledgeService.File.impl;

import java.util.Arrays;

import com.ruoyi.system.Repository.KnowledgeRepository.File.FileRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.KnowledgeService.File.FileKnowledgeService;

import javax.annotation.Resource;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-05
 */
@Service
public class FileKnowledgeServiceImpl implements FileKnowledgeService
{
    @Resource
    private FileRepository fileRepository;

    @Override
    public Page<FileKnowledge> getAllFileKnowledge(Pageable pageable){
        return fileRepository.findAll(pageable);
    }

    @Override
    public Page<FileKnowledge> getAllFileKnowledgeByType(String label, Pageable pageable){
        return fileRepository.findByFileClassification(label,pageable);
    }

    @Override
    public Page<FileKnowledge> getAllFileKnowledgeByParams(Example<FileKnowledge> example, Pageable pageable){
        return fileRepository.findAll(example,pageable);
    }

    @Override
    public FileKnowledge selectSysFileInfoById(Long fileId) {
        return fileRepository.findById(fileId).get();
    }

    @Override
    public FileKnowledge insertFileKnowledge(FileKnowledge FileInfo) {
        return fileRepository.save(FileInfo);
    }

    @Override
    public FileKnowledge updateSysFileInfo(FileKnowledge FileInfo) {
        return fileRepository.save(FileInfo);
    }

    @Override
    public void deleteFileKnowledgeByIds(Long[] fileIds) {
        fileRepository.deleteAllById(Arrays.asList(fileIds));
    }

    @Override
    public void deleteFileKnowledgeByFileId(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
