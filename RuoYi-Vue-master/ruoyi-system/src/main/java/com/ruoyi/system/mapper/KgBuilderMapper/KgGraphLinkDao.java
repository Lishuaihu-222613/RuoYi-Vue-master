package com.ruoyi.system.mapper.KgBuilderMapper;


import com.ruoyi.system.domain.KgBuilderPojo.entity.KgGraphLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KgGraphLinkDao {

    List<KgGraphLink> selectByDomainId(Integer domainId);

    int insert(KgGraphLink record);

    int batchInsert(List<KgGraphLink> records);

    int deleteByDomainId(Integer domainId);

}