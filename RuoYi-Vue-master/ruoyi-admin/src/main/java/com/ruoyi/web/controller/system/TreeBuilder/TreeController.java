package com.ruoyi.web.controller.system.TreeBuilder;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo.LeafForParent;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import com.ruoyi.system.service.KnowledgeService.ClassificationTree.TreeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ClassificationTree")
public class TreeController  extends BaseController {

    @Resource
    private TreeService treeService;

    /**
     * 查询知识树管理列表
     */
    @GetMapping("/list")
    public AjaxResult list(ClassificationTree tree)
    {
        List<ClassificationTree> list = treeService.selectTreeList(tree);
        return AjaxResult.success(list);
    }

    /**
     * 获取知识树管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(treeService.selectTreeById(id));
    }

    /**
     * 新增知识树管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody ClassificationTree tree)
    {
        return AjaxResult.success(treeService.createTree(tree));
    }

    @PostMapping("/addSubLeafs")
    public AjaxResult addSubLeafs(@RequestBody LeafForParent leafs)
    {
        return AjaxResult.success(treeService.addSubLeafs(leafs));
    }

    /**
     * 修改知识树管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ClassificationTree tree)
    {
        return AjaxResult.success(treeService.updateTree(tree));
    }

    /**
     * 删除知识树管理
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(treeService.deleteTree(ids));
    }
}
