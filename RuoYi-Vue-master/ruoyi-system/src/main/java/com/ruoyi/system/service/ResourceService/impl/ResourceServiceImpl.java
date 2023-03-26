package com.ruoyi.system.service.ResourceService.impl;

import com.ruoyi.system.Repository.ResourceRepository.ResourceRepository;
import com.ruoyi.system.service.ResourceService.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceRepository resourceRepository;

}
