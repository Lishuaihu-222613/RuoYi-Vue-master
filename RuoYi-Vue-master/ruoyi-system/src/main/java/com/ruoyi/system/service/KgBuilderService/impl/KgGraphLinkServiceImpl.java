package com.ruoyi.system.service.KgBuilderService.impl;


import com.ruoyi.system.domain.KgBuilderPojo.entity.KgGraphLink;
import com.ruoyi.system.mapper.KgBuilderMapper.KgGraphLinkDao;
import com.ruoyi.system.service.KgBuilderService.KgGraphLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (KgGraphLink)表服务接口
 *
 * @author tanc
 * @since 2021-12-24 15:53:53
 */
@Service
public class KgGraphLinkServiceImpl implements KgGraphLinkService {

    @Autowired
    KgGraphLinkDao GraphLinkDao;

    @Override
    public List<KgGraphLink> queryById(Integer domainId) {
        return GraphLinkDao.selectByDomainId(domainId);
    }

    @Override
    public Integer insert(KgGraphLink kgGraphLink) {
        return GraphLinkDao.insert(kgGraphLink);
    }

}