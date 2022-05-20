package com.ruoyi.system.service.KgBuilderService;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgFeedBack;

import java.util.List;

public interface FeedBackService {

    int deleteByPrimaryKey(Integer id);

    int insert(KgFeedBack record);

    KgFeedBack selectByPrimaryKey(Integer id);

    List<KgFeedBack> queryForList(KgFeedBack queryItem);

}
