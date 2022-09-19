package com.ruoyi.system.service.KgBuilderService;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;

import java.util.List;

/**
 * 知识树管理Service接口
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public interface IKgDomainService 
{
    /**
     * 查询知识树管理
     * 
     * @param id 知识树管理主键
     * @return 知识树管理
     */
    public KgDomain selectKgDomainById(Long id);

    /**
     * 查询知识树管理列表
     * 
     * @param kgDomain 知识树管理
     * @return 知识树管理集合
     */
    public List<KgDomain> selectKgDomainList(KgDomain kgDomain);

    /**
     * 新增知识树管理
     * 
     * @param kgDomain 知识树管理
     * @return 结果
     */
    public int insertKgDomain(KgDomain kgDomain);

    /**
     * 修改知识树管理
     * 
     * @param kgDomain 知识树管理
     * @return 结果
     */
    public int updateKgDomain(KgDomain kgDomain);

    /**
     * 批量删除知识树管理
     * 
     * @param ids 需要删除的知识树管理主键集合
     * @return 结果
     */
    public int deleteKgDomainByIds(Long[] ids);

    /**
     * 删除知识树管理信息
     * 
     * @param id 知识树管理主键
     * @return 结果
     */
    public int deleteKgDomainById(Long id);
}
