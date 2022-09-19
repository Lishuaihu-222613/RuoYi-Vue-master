package com.ruoyi.web.controller.system.KgBuilder;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.KgBuilderService.IKgDomainService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 知识树管理Controller
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
@RestController
@RequestMapping("/knowledgeTree/domainManagement")
public class KgDomainController extends BaseController
{
    @Autowired
    private IKgDomainService kgDomainService;

    /**
     * 查询知识树管理列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:list')")
    @GetMapping("/list")
    public AjaxResult list(KgDomain kgDomain)
    {
        List<KgDomain> list = kgDomainService.selectKgDomainList(kgDomain);
        return AjaxResult.success(list);
    }

    /**
     * 导出知识树管理列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:export')")
    @Log(title = "知识树管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KgDomain kgDomain)
    {
        List<KgDomain> list = kgDomainService.selectKgDomainList(kgDomain);
        ExcelUtil<KgDomain> util = new ExcelUtil<KgDomain>(KgDomain.class);
        util.exportExcel(response, list, "知识树管理数据");
    }

    /**
     * 获取知识树管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(kgDomainService.selectKgDomainById(id));
    }

    /**
     * 新增知识树管理
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:add')")
    @Log(title = "知识树管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KgDomain kgDomain)
    {
        return toAjax(kgDomainService.insertKgDomain(kgDomain));
    }

    /**
     * 修改知识树管理
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:edit')")
    @Log(title = "知识树管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KgDomain kgDomain)
    {
        return toAjax(kgDomainService.updateKgDomain(kgDomain));
    }

    /**
     * 删除知识树管理
     */
    @PreAuthorize("@ss.hasPermi('knowledgeTree:domainManagement:remove')")
    @Log(title = "知识树管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kgDomainService.deleteKgDomainByIds(ids));
    }
}
