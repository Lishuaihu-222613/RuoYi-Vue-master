package com.ruoyi.system.service.ResourceService.impl;

import com.ruoyi.system.Repository.ResourceRepository.*;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.FixtureTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import com.ruoyi.system.service.ResourceService.ResourceService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceRepository resourceRepository;

    @Resource
    private AuxiliaryRepository auxiliaryRepository;

    @Resource
    private EquipmentRepository equipmentRepository;

    @Resource
    private FixtureToolRepository fixtureToolRepository;

    @Resource
    private MeasuringToolRepository measuringToolRepository;

    @Resource
    private MouldToolRepository mouldToolRepository;

    @Resource
    private SpecialRepository specialRepository;

    @Resource
    private SpreaderToolRepository spreaderToolRepository;

    @Override
    public Page<AssemblyResource> getAllResourcesByType(String type) {
        return null;
    }

    @Override
    public <S extends AssemblyResource> S createResource(S resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public <S extends AssemblyResource> S updateResource(S resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public <S extends MouldTool> S updateMouldTool(S resource) {
        return mouldToolRepository.save(resource);
    }

    @Override
    public void deleteResource(List<Long> resourceIds) {
        resourceRepository.deleteAllById(resourceIds);
    }

    @Override
    public void createRelatedRelations(Long relatedId, List<Long> resources) {
        for (Long resourceId : resources) {
            resourceRepository.createUseRelation(resourceId,relatedId);
        }
    }

    @Override
    public <S extends AssemblyResource> Page<S> getAllByParams(Example<S> example, Pageable pageable) {
        return resourceRepository.findAll(example,pageable);
    }

    @Override
    public Page<AuxiliaryResource> getAuxiliaryResourcesByType(String label, Pageable pageable) {
        return auxiliaryRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<EquipmentResource> getEquipmentResourcesByType(String label, Pageable pageable) {
        return equipmentRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<FixtureTool> getFixtureToolsByType(String label, Pageable pageable) {
        return fixtureToolRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<MeasuringTool> getMeasuringToolsByType(String label, Pageable pageable) {
        return measuringToolRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<MouldTool> getMouldToolsByType(String label, Pageable pageable) {
        return mouldToolRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<SpecialTool> getSpecialToolsByType(String label, Pageable pageable) {
        return specialRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public Page<SpreaderTool> getSpreaderToolsByType(String label, Pageable pageable) {
        return spreaderToolRepository.findResourcesByResourceType(label,pageable);
    }

    @Override
    public List<AssemblyResource> getResourcesByRelatedId(Long relatedId) {
        return resourceRepository.findResourceByAssociatedId(relatedId);
    }

    @Override
    public List<AuxiliaryResource> getAuxiliaryResourcesByType(String label) {
        return null;
    }

    @Override
    public List<EquipmentResource> getEquipmentResourcesByType(String label) {
        return null;
    }

    @Override
    public List<FixtureTool> getFixtureToolsByType(String label) {
        return null;
    }

    @Override
    public List<MeasuringTool> getMeasuringToolsByType(String label) {
        return null;
    }

    @Override
    public List<MouldTool> getMouldToolsByType(String label) {
        return null;
    }

    @Override
    public List<SpecialTool> getSpecialToolsByType(String label) {
        return null;
    }

    @Override
    public List<SpreaderTool> getSpreaderToolsByType(String label) {
        return null;
    }

    @Override
    public List<AuxiliaryResource> getAuxiliaryResources() {
        return null;
    }

    @Override
    public List<EquipmentResource> getEquipmentResources() {
        return null;
    }

    @Override
    public List<FixtureTool> getFixtureTools() {
        return null;
    }

    @Override
    public List<MeasuringTool> getMeasuringTools() {
        return null;
    }

    @Override
    public List<MouldTool> getMouldTools() {
        return null;
    }

    @Override
    public List<SpecialTool> getSpecialTools() {
        return null;
    }

    @Override
    public List<SpreaderTool> getSpreaderTools() {
        return null;
    }

    @Override
    public Page<AuxiliaryResource> getAuxiliaryResources(Pageable pageable) {
        return auxiliaryRepository.findAll(pageable);
    }

    @Override
    public Page<EquipmentResource> getEquipmentResources(Pageable pageable) {
        return equipmentRepository.findAll(pageable);
    }

    @Override
    public Page<FixtureTool> getFixtureTools(Pageable pageable) {
        return fixtureToolRepository.findAll(pageable);
    }

    @Override
    public Page<MeasuringTool> getMeasuringTools(Pageable pageable) {
        return measuringToolRepository.findAll(pageable);
    }

    @Override
    public Page<MouldTool> getMouldTools(Pageable pageable) {
        return mouldToolRepository.findAll(pageable);
    }

    @Override
    public Page<SpecialTool> getSpecialTools(Pageable pageable) {
        return specialRepository.findAll(pageable);
    }

    @Override
    public Page<SpreaderTool> getSpreaderTools(Pageable pageable) {
        return spreaderToolRepository.findAll(pageable);
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
