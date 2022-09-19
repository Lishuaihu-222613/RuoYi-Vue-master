package com.ruoyi.system.mapper.KgBuilderMapper;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;

import java.util.List;

/**
 * 知识树管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public interface KgDomainMapper 
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
     * 删除知识树管理
     * 
     * @param id 知识树管理主键
     * @return 结果
     */
    public int deleteKgDomainById(Long id);

    /**
     * 批量删除知识树管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKgDomainByIds(Long[] ids);
}
