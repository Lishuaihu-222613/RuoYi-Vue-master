package com.ruoyi.web.controller.system;

import javax.annotation.Resource;

import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.service.KnowledgeService.File.FileKnowledgeService;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;



/**
 * 文件信息Controller
 * 
 * @author ruoyi
 * @date 2022-12-05
 */
@RestController
@RequestMapping("/system/info")
public class SysFileInfoController extends BaseController
{
    @Resource
    private FileKnowledgeService fileKnowledgeService;

    /**
     * 获取文件信息详细信息
     */
    @ResponseBody
    @GetMapping(value = "/{fileId}")
    public R<FileKnowledge> getInfo(@PathVariable("fileId") Long fileId)
    {
        try{
            FileKnowledge fileInfo = fileKnowledgeService.selectSysFileInfoById(fileId);
            return R.success(fileInfo);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 新增文件信息
     */
    @ResponseBody
    @PostMapping
    public R<FileKnowledge> insertFile(@RequestBody FileKnowledge fileInfo)
    {
        try{
            FileKnowledge newFile = fileKnowledgeService.insertFileKnowledge(fileInfo);
            return R.success(newFile);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }


    /**
     * 删除文件信息
     */

}
