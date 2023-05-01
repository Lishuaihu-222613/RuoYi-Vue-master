package com.ruoyi.web.controller.system.TreeBuilder;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.ClassificationTree;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree.vo.LeafForParent;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
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
    @ResponseBody
    @GetMapping("/list")
    public R<List<ClassificationTree>> list(@RequestBody ClassificationTree tree)
    {
        try {
            List<ClassificationTree> list = treeService.selectTreeList(tree);
            System.out.println(list);
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 查询知识树管理列表
     */
    @ResponseBody
    @GetMapping("/getAllRoot")
    public R<List<ClassificationTree>> getAllRoot()
    {
        try {
            List<ClassificationTree> list = treeService.selectAllRoot();
            System.out.println(list);
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/getLeafOptions")
    public R<List<ClassificationTree>> getLeafOptions()
    {
        try {
            List<ClassificationTree> list = treeService.getLeafOptions();
            System.out.println(list);
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 查询知识树管理列表
     */
    @ResponseBody
    @GetMapping("/getSubLeafs/{parentId}")
    public R<List<ClassificationTree>> getSubLeafsByParent(@PathVariable Long parentId)
    {
        try {
            List<ClassificationTree> list = treeService.selectSubLeafs(parentId);
            System.out.println(list);
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 获取知识树管理详细信息
     */
    @ResponseBody
    @GetMapping(value = "/{id}")
    public R<ClassificationTree> getInfo(@PathVariable("id") Long id)
    {
        try {
            ClassificationTree leaf = treeService.selectTreeById(id);
            System.out.println(leaf);
            return R.success(leaf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("getParentLeaf/{id}")
    public R<Long> getParentLeaf(@PathVariable("id") Long id)
    {
        try {
            ClassificationTree leaf = treeService.getParentLeaf(id);
            System.out.println(leaf);
            return R.success(leaf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 新增知识树管理
     */
    @ResponseBody
    @PostMapping
    public R<ClassificationTree> add(@RequestBody ClassificationTree tree)
    {
        try {
            ClassificationTree leaf = treeService.createTree(tree);
            System.out.println(leaf);
            return R.success(leaf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/addSubLeaf")
    public R<ClassificationTree> addSubLeaf(@RequestBody LeafForParent leaf)
    {
        try {
            ClassificationTree subLeaf = treeService.addSubLeafs(leaf);
            System.out.println(subLeaf);
            return R.success(subLeaf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 修改知识树管理
     */
    @ResponseBody
    @PutMapping
    public R<ClassificationTree> edit(@RequestBody LeafForParent leaf)
    {
        try {
            ClassificationTree subLeaf = treeService.updateTree(leaf);
            System.out.println(subLeaf);
            return R.success(subLeaf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 删除知识树管理
     */
    @ResponseBody
    @DeleteMapping("/{ids}")
    public R<String> remove(@PathVariable Long[] ids)
    {
        try {
            treeService.deleteTree(ids);
            System.out.println(ids);
            return R.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
