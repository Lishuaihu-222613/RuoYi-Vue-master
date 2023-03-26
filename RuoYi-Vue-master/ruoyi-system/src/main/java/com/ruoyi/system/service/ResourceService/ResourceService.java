package com.ruoyi.system.service.ResourceService;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceService {

    Page<AssemblyResource> getAllResourcesByType(String type);

    <S extends AssemblyResource> S createResource(S resource);

    <S extends AssemblyResource> S updateResource(S resource);

    void deleteResource(Long resourceId);

    AssemblyResource getAssemblyResourceById(Long resourceId);

    List<AssemblyResource> getAssemblyResourceByName(String resourceName);

    List<AssemblyResource> getAssemblyResourceByProcess(Long processId);

    List<AssemblyResource> getAssemblyResourceBySequence(Long sequenceId);

    List<AssemblyResource> getAssemblyResourceByStep(Long stepId);



}
