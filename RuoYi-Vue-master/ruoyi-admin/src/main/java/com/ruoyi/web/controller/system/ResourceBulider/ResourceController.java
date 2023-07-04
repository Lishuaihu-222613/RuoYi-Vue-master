package com.ruoyi.web.controller.system.ResourceBulider;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AuxiliaryResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.FixtureTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.vo.ResourceQueryVo;
import com.ruoyi.system.service.ResourceService.ResourceService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.by;


@RestController
@RequestMapping("/Resource")
public class ResourceController extends BaseController {

    @Resource
    private ResourceService resourceService;

    @ResponseBody
    @PostMapping("/createAuxiliaryResource")
    public R<AuxiliaryResource> createResource(@RequestBody AuxiliaryResource resource) {
        try {
            AuxiliaryResource newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createEquipmentResource")
    public R<EquipmentResource> createResource(@RequestBody EquipmentResource resource) {
        try {
            EquipmentResource newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createFixtureTool")
    public R<FixtureTool> createResource(@RequestBody FixtureTool resource) {
        try {
            FixtureTool newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createMeasuringTool")
    public R<MeasuringTool> createResource(@RequestBody MeasuringTool resource) {
        try {
            MeasuringTool newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSpreaderTool")
    public R<SpreaderTool> createResource(@RequestBody SpreaderTool resource) {
        try {
            SpreaderTool newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSpecialTool")
    public R<SpecialTool> createResource(@RequestBody SpecialTool resource) {
        try {
            SpecialTool newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createMouldTool")
    public R<MouldTool> createResource(@RequestBody MouldTool resource) {
        try {
            MouldTool newResource = resourceService.createResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };


    @ResponseBody
    @PostMapping("/updateAuxiliaryResource")
    public R<AuxiliaryResource> updateResource(@RequestBody AuxiliaryResource resource) {
        try {
            AuxiliaryResource newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateEquipmentResource")
    public R<EquipmentResource> updateResource(@RequestBody EquipmentResource resource) {
        try {
            EquipmentResource newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateFixtureTool")
    public R<FixtureTool> updateResource(@RequestBody FixtureTool resource) {
        try {
            FixtureTool newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateMeasuringTool")
    public R<MeasuringTool> updateResource(@RequestBody MeasuringTool resource) {
        try {
            MeasuringTool newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateSpreaderTool")
    public R<SpreaderTool> updateResource(@RequestBody SpreaderTool resource) {
        try {
            SpreaderTool newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateSpecialTool")
    public R<SpecialTool> updateResource(@RequestBody SpecialTool resource) {
        try {
            SpecialTool newResource = resourceService.updateResource(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/updateMouldTool")
    public R<MouldTool> updateResource(@RequestBody MouldTool resource) {
        try {
            MouldTool newResource = resourceService.updateMouldTool(resource);
            System.out.println(newResource);
            return R.success(newResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/deleteResource/{resourceIds}")
    public R<String> deletePrinciple(@PathVariable Long[] resourceIds) {
        try {
            resourceService.deleteResource(resourceIds);
            return R.success("删除" + resourceIds + "的资源");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAuxiliaryResourcesByParams")
    public R<Page<AuxiliaryResource>> getAuxiliaryResourceByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<AuxiliaryResource> example = Example.of(params.getOriginAuxiliaryResource(),matcher);
            Page<AuxiliaryResource> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getEquipmentResourcesByParams")
    public R<Page<EquipmentResource>> getEquipmentResourceByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<EquipmentResource> example = Example.of(params.getOriginEquipmentResource(),matcher);
            Page<EquipmentResource> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getFixtureToolsByParams")
    public R<Page<FixtureTool>> getFixtureToolByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<FixtureTool> example = Example.of(params.getOriginFixtureTool(),matcher);
            Page<FixtureTool> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getMeasuringToolsByParams")
    public R<Page<MeasuringTool>> getMeasuringToolByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<MeasuringTool> example = Example.of(params.getOriginMeasuringTool(),matcher);
            Page<MeasuringTool> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getSpreaderToolsByParams")
    public R<Page<SpreaderTool>> getSpreaderToolByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<SpreaderTool> example = Example.of(params.getOriginSpreaderTool(),matcher);
            Page<SpreaderTool> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getSpecialToolsByParams")
    public R<Page<SpecialTool>> getSpecialToolByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<SpecialTool> example = Example.of(params.getOriginSpecialTool(),matcher);
            Page<SpecialTool> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getMouldToolsByParams")
    public R<Page<MouldTool>> getMouldToolByParams(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("resourceDescription",ExampleMatcher.GenericPropertyMatcher::contains)
                    .withMatcher("site",ExampleMatcher.GenericPropertyMatcher::contains);
            Example<MouldTool> example = Example.of(params.getOriginMouldTool(),matcher);
            Page<MouldTool> allByParams = resourceService.getAllByParams(example, pageable);
            System.out.println(allByParams);
            return R.success(allByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAuxiliaryResourcesByLabel")
    public R<Page<AuxiliaryResource>> getAuxiliaryResourceByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<AuxiliaryResource> resources = resourceService.getAuxiliaryResourcesByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getEquipmentResourcesByLabel")
    public R<Page<EquipmentResource>> getEquipmentResourceByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<EquipmentResource> resources = resourceService.getEquipmentResourcesByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getFixtureToolsByLabel")
    public R<Page<FixtureTool>> getFixtureToolByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<FixtureTool> resources = resourceService.getFixtureToolsByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getMeasuringToolsByLabel")
    public R<Page<MeasuringTool>> getMeasuringToolByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<MeasuringTool> resources = resourceService.getMeasuringToolsByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getSpreaderToolsByLabel")
    public R<Page<SpreaderTool>> getSpreaderToolByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<SpreaderTool> resources = resourceService.getSpreaderToolsByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getSpecialToolsByLabel")
    public R<Page<SpecialTool>> getSpecialToolByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<SpecialTool> resources = resourceService.getSpecialToolsByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getMouldToolsByLabel")
    public R<Page<MouldTool>> getMouldToolByLabel(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<MouldTool> resources = resourceService.getMouldToolsByType(params.getDynamicLabel(), pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllAuxiliaryResources")
    public R<Page<AuxiliaryResource>> getAllAuxiliaryResources(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<AuxiliaryResource> resources = resourceService.getAuxiliaryResources(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllEquipmentResources")
    public R<Page<EquipmentResource>> getAllEquipmentResources(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<EquipmentResource> resources = resourceService.getEquipmentResources(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllFixtureTools")
    public R<Page<FixtureTool>> getAllFixtureTools(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<FixtureTool> resources = resourceService.getFixtureTools(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllMeasuringTools")
    public R<Page<MeasuringTool>> getAllMeasuringTools(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<MeasuringTool> resources = resourceService.getMeasuringTools(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllSpreaderTools")
    public R<Page<SpreaderTool>> getAllSpreaderTools(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<SpreaderTool> resources = resourceService.getSpreaderTools(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllSpecialTools")
    public R<Page<SpecialTool>> getAllSpecialTools(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<SpecialTool> resources = resourceService.getSpecialTools(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/getAllMouldTools")
    public R<Page<MouldTool>> getAllMouldTools(@RequestBody ResourceQueryVo params) {
        try {
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(params.getSortType()) ? by(Sort.Direction.ASC, params.getSortableField()) : by(Sort.Direction.DESC, params.getSortableField());
            //获取pageable
            Pageable pageable = PageRequest.of(params.getPageNum()-1,params.getPageSize(),sort);
            Page<MouldTool> resources = resourceService.getMouldTools(pageable);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getResourceOptionsByLabel/{label}")
    public R<List<AssemblyResource>> getResourceOptionsByLabel(@PathVariable String label) {
        try {
            List<AssemblyResource> options = resourceService.getResourceOptionsByLabel(label);
            System.out.println(options);
            return R.success(options);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getAuxiliaryResources")
    public R<List<AuxiliaryResource>> getAuxiliaryResources() {
        try {
            List<AuxiliaryResource> resources = resourceService.getAuxiliaryResources();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getEquipmentResources")
    public R<List<EquipmentResource>> getEquipmentResources() {
        try {
            List<EquipmentResource> resources = resourceService.getEquipmentResources();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getFixtureTools")
    public R<List<FixtureTool>> getFixtureTools() {
        try {
            List<FixtureTool> resources = resourceService.getFixtureTools();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getMeasuringTools")
    public R<List<MeasuringTool>> getMeasuringTools() {
        try {
            List<MeasuringTool> resources = resourceService.getMeasuringTools();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSpreaderTools")
    public R<List<SpreaderTool>> getSpreaderTools() {
        try {
            List<SpreaderTool> resources = resourceService.getSpreaderTools();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getSpecialTools")
    public R<List<SpecialTool>> getSpecialTools() {
        try {
            List<SpecialTool> resources = resourceService.getSpecialTools();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getMouldTools")
    public R<List<MouldTool>> getMouldTools() {
        try {
            List<MouldTool> resources = resourceService.getMouldTools();
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @GetMapping("/getResourcesByRelatedId/{relatedId}")
    public R<List<AssemblyResource>> getResourcesByRelatedId(@PathVariable Long relatedId) {
        try {
            List<AssemblyResource> resources = resourceService.getResourcesByRelatedId(relatedId);
            System.out.println(resources);
            return R.success(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
    
}
