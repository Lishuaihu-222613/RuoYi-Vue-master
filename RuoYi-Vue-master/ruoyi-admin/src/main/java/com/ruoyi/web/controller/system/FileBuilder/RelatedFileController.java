package com.ruoyi.web.controller.system.FileBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.FileKnowledge;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge.vo.FileQueryVo;
import com.ruoyi.system.service.KnowledgeService.File.FileKnowledgeService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/fileManagement")
public class RelatedFileController extends BaseController {

    @Resource
    private FileKnowledgeService fileKnowledgeService;

    @ResponseBody
    @PostMapping("/getAllFiles")
    public R<Page<FileKnowledge>> getAllFiles(@RequestBody FileQueryVo params){
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<FileKnowledge> knowledges = fileKnowledgeService.getAllFileKnowledge(pageable);
            System.out.println(knowledges);
            return R.success(knowledges);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllFilesByLabel")
    public R<Page<FileKnowledge>> getAllFilesByLabel(@RequestBody FileQueryVo params){
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<FileKnowledge> knowledges = fileKnowledgeService.getAllFileKnowledgeByType(params.getDynamicLabel(), pageable);
            System.out.println(knowledges);
            return R.success(knowledges);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getFilesByParams")
    public R<Page<FileKnowledge>> getFilesByParams(@RequestBody FileQueryVo params){
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("fileName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("fileDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("manager",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("fileType",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<FileKnowledge> example = Example.of(params.getOriginFile(), matcher);
            Page<FileKnowledge> knowledges = fileKnowledgeService.getAllFileKnowledgeByParams(example, pageable);
            System.out.println(knowledges);
            return R.success(knowledges);
        } catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getFileInfoById/{fileId}")
    public R<FileKnowledge> getFileInfoById(@PathVariable Long fileId){
        try {
            FileKnowledge fileKnowledge = fileKnowledgeService.selectSysFileInfoById(fileId);
            System.out.println(fileKnowledge);
            return R.success(fileKnowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getFilesByRelatedId/{relatedId}")
    public R<List<FileKnowledge>> getFilesByRelatedId(@PathVariable Long relatedId){
        try {
            List<FileKnowledge> files = fileKnowledgeService.getFilesByRelatedId(relatedId);
            System.out.println(files);
            return R.success(files);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getModelFileByStructure/{relatedId}")
    public R<FileKnowledge> getModelFileByStructure(@PathVariable Long relatedId){
        try {
            FileKnowledge model = fileKnowledgeService.getModelFileByStructureId(relatedId);
            System.out.println(model);
            return R.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getFileOptionsByLabel/{label}")
    public R<List<FileKnowledge>> getFileOptionsByLabel(@PathVariable String label){
        try {
            List<FileKnowledge> options = fileKnowledgeService.getFileOptionsByLabel(label);
            System.out.println(options);
            return R.success(options);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAllModelFile")
    public R<List<FileKnowledge>> getAllModelFile(){
        try {
            List<FileKnowledge> files = fileKnowledgeService.getAllModelFile();
            System.out.println(files);
            return R.success(files);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };


    @ResponseBody
    @PostMapping("/updateFile")
    public R<FileKnowledge> updateFile(@RequestBody FileKnowledge file) {
        try {
            FileKnowledge fileKnowledge = fileKnowledgeService.updateSysFileInfo(file);
            return R.success(fileKnowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createFile")
    public R<FileKnowledge> createFile(@RequestBody FileKnowledge file) {
        try {
            FileKnowledge fileKnowledge = fileKnowledgeService.insertFileKnowledge(file);
            return R.success(fileKnowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    @ResponseBody
    @PostMapping("/deleteFile")
    public R<String> deleteFile(@RequestBody Long[] fileIds ) {
        try {
            fileKnowledgeService.deleteFileKnowledgeByIds(fileIds);
            return R.success("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

}
