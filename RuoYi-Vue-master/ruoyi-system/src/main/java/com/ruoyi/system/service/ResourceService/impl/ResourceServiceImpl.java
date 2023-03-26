package com.ruoyi.system.service.ResourceService.impl;

import com.ruoyi.system.Repository.ResourceRepository.ResourceRepository;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.service.ResourceService.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceRepository resourceRepository;

    @Override
    public Page<AssemblyResource> getAllResourcesByType(String type) {
        return null;
    }

    @Override
    public <S extends AssemblyResource> S createResource(S resource) {
        return null;
    }

    @Override
    public <S extends AssemblyResource> S updateResource(S resource) {
        return null;
    }

    @Override
    public void deleteResource(Long resourceId) {

    }

    @Override
    public AssemblyResource getAssemblyResourceById(Long resourceId) {
        return null;
    }

    @Override
    public List<AssemblyResource> getAssemblyResourceByName(String resourceName) {
        return null;
    }

    @Override
    public List<AssemblyResource> getAssemblyResourceByProcess(Long processId) {
        return null;
    }

    @Override
    public List<AssemblyResource> getAssemblyResourceBySequence(Long sequenceId) {
        return null;
    }

    @Override
    public List<AssemblyResource> getAssemblyResourceByStep(Long stepId) {
        return null;
    }
}
