package com.ruoyi.system.service.KnowledgeService.File;

import java.util.List;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 文件信息Service接口
 * 
 * @author ruoyi
 * @date 2022-12-05
 */
public interface FileKnowledgeService
{
    Page<FileKnowledge> getAllFileKnowledge(Pageable pageable);

    Page<FileKnowledge> getAllFileKnowledgeByType(String label, Pageable pageable);

    Page<FileKnowledge> getAllFileKnowledgeByParams(Example<FileKnowledge> example, Pageable pageable);

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    public FileKnowledge selectSysFileInfoById(Long fileId);


    /**
     * 新增文件信息
     * 
     * @param FileInfo 文件信息
     * @return 结果
     */
    public FileKnowledge insertFileKnowledge(FileKnowledge FileInfo);

    /**
     * 修改文件信息
     * 
     * @param FileInfo 文件信息
     * @return 结果
     */
    public FileKnowledge updateSysFileInfo(FileKnowledge FileInfo);

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键集合
     * @return 结果
     */
    public void deleteFileKnowledgeByIds(Long[] fileIds);

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    public void deleteFileKnowledgeByFileId(Long fileId);

    public void createRelatedRelations(Long relatedId,List<Long> files);

    public List<FileKnowledge> getFilesByRelatedId(Long relatedId);

    List<FileKnowledge> getAllModelFile();

    List<FileKnowledge> getFileOptionsByLabel(String label);

    public FileKnowledge getModelFileByStructureId(Long structure);
}
