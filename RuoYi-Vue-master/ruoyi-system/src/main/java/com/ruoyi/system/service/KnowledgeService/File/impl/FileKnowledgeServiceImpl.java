package com.ruoyi.system.service.KnowledgeService.File.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.system.Repository.KnowledgeRepository.File.FileRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
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
