package com.ruoyi.system.service.KgBuilderService.impl;


import com.ruoyi.system.domain.KgBuilderPojo.entity.KgFeedBack;
import com.ruoyi.system.mapper.KgBuilderMapper.FeedBackDao;
import com.ruoyi.system.service.KgBuilderService.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackDao feedBackDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return feedBackDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(KgFeedBack record) {
        return feedBackDao.insert(record);
    }

    @Override
    public KgFeedBack selectByPrimaryKey(Integer id) {
        return feedBackDao.selectByPrimaryKey(id);
    }

    @Override
    public List<KgFeedBack> queryForList(KgFeedBack queryItem) {
        return feedBackDao.queryForList(queryItem);
    }
}