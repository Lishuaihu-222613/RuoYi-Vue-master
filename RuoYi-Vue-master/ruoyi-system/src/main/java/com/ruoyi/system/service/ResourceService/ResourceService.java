package com.ruoyi.system.service.ResourceService;

import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.FixtureTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {

    Page<AssemblyResource> getAllResourcesByType(String type);

    <S extends AssemblyResource> S createResource(S resource);

    <S extends AssemblyResource> S updateResource(S resource);

    void deleteResource(List<Long> resourceId);

    <S extends AssemblyResource> Page<S> getAllByParams(Example<S> example, Pageable pageable);

    Page<AuxiliaryResource> getAuxiliaryResourcesByType(String label,Pageable pageable);

    Page<EquipmentResource> getEquipmentResourcesByType(String label, Pageable pageable);

    Page<FixtureTool> getFixtureToolsByType(String label, Pageable pageable);

    Page<MeasuringTool> getMeasuringToolsByType(String label, Pageable pageable);

    Page<MouldTool> getMouldToolsByType(String label, Pageable pageable);

    Page<SpecialTool> getSpecialToolsByType(String label, Pageable pageable);

    Page<SpreaderTool> getSpreaderToolsByType(String label, Pageable pageable);

    List<AuxiliaryResource> getAuxiliaryResourcesByType(String label);

    List<EquipmentResource> getEquipmentResourcesByType(String label);

    List<FixtureTool> getFixtureToolsByType(String label);

    List<MeasuringTool> getMeasuringToolsByType(String label);

    List<MouldTool> getMouldToolsByType(String label);

    List<SpecialTool> getSpecialToolsByType(String label);

    List<SpreaderTool> getSpreaderToolsByType(String label);

    List<AuxiliaryResource> getAuxiliaryResources();

    List<EquipmentResource> getEquipmentResources();

    List<FixtureTool> getFixtureTools();

    List<MeasuringTool> getMeasuringTools();

    List<MouldTool> getMouldTools();

    List<SpecialTool> getSpecialTools();

    List<SpreaderTool> getSpreaderTools();

    Page<AuxiliaryResource> getAuxiliaryResources(Pageable pageable);

    Page<EquipmentResource> getEquipmentResources(Pageable pageable);

    Page<FixtureTool> getFixtureTools(Pageable pageable);

    Page<MeasuringTool> getMeasuringTools(Pageable pageable);

    Page<MouldTool> getMouldTools(Pageable pageable);

    Page<SpecialTool> getSpecialTools(Pageable pageable);

    Page<SpreaderTool> getSpreaderTools(Pageable pageable);

    AssemblyResource getAssemblyResourceById(Long resourceId);

    List<AssemblyResource> getAssemblyResourceByName(String resourceName);

    List<AssemblyResource> getAssemblyResourceByProcess(Long processId);

    List<AssemblyResource> getAssemblyResourceBySequence(Long sequenceId);

    List<AssemblyResource> getAssemblyResourceByStep(Long stepId);



}
