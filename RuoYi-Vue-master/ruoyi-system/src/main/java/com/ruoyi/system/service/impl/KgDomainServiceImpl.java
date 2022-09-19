package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.KgBuilderMapper.KgDomainMapper;
import com.ruoyi.system.service.KgBuilderService.IKgDomainService;

/**
 * 知识树管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
@Service
public class KgDomainServiceImpl implements IKgDomainService 
{
    @Autowired
    private KgDomainMapper kgDomainMapper;

    /**
     * 查询知识树管理
     * 
     * @param id 知识树管理主键
     * @return 知识树管理
     */
    @Override
    public KgDomain selectKgDomainById(Long id)
    {
        return kgDomainMapper.selectKgDomainById(id);
    }

    /**
     * 查询知识树管理列表
     * 
     * @param kgDomain 知识树管理
     * @return 知识树管理
     */
    @Override
    public List<KgDomain> selectKgDomainList(KgDomain kgDomain)
    {
        return kgDomainMapper.selectKgDomainList(kgDomain);
    }

    /**
     * 新增知识树管理
     * 
     * @param kgDomain 知识树管理
     * @return 结果
     */
    @Override
    public int insertKgDomain(KgDomain kgDomain)
    {
        return kgDomainMapper.insertKgDomain(kgDomain);
    }

    /**
     * 修改知识树管理
     * 
     * @param kgDomain 知识树管理
     * @return 结果
     */
    @Override
    public int updateKgDomain(KgDomain kgDomain)
    {
        return kgDomainMapper.updateKgDomain(kgDomain);
    }

    /**
     * 批量删除知识树管理
     * 
     * @param ids 需要删除的知识树管理主键
     * @return 结果
     */
    @Override
    public int deleteKgDomainByIds(Long[] ids)
    {
        return kgDomainMapper.deleteKgDomainByIds(ids);
    }

    /**
     * 删除知识树管理信息
     * 
     * @param id 知识树管理主键
     * @return 结果
     */
    @Override
    public int deleteKgDomainById(Long id)
    {
        return kgDomainMapper.deleteKgDomainById(id);
    }
}
