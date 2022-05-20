package com.ruoyi.web.controller.system.KgBuilder;

import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import com.ruoyi.system.domain.KgBuilderPojo.request.GraphItem;
import com.ruoyi.system.service.KgBuilderService.KgGraphNodeService;
import com.ruoyi.system.service.KgBuilderService.KnowledgeGraphService;
import com.ruoyi.system.service.KgBuilderService.impl.WorkFlowDirectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/er")
public class KGBuilderController extends BaseController {

    @Autowired
    private KnowledgeGraphService kgService;
    @Autowired
    private KgGraphNodeService kgGraphNodeService;
    @Autowired
    WorkFlowDirectorServiceImpl workFlowDirectorService;

    @ResponseBody
    @PostMapping(value = "/saveData")
    public R<Map<String, Object>> saveDataSource(@RequestBody GraphItem submitItem) throws IOException {
        Map<String, Object> result = new HashMap<>();
        KgDomain domainItem = kgService.selectById(submitItem.getDomainId());
        if (domainItem == null) {
            return R.error("领域不存在");
        }
        kgGraphNodeService.createNode(submitItem);
        return R.success(result, "操作成功");
    }

    @ResponseBody
    @GetMapping(value = "/getDomainNode")
    public R<GraphItem> saveDataSource(Integer domainId) throws IOException {
        KgDomain domainItem = kgService.selectById(domainId);
        if (domainItem == null) {
            return R.error("领域不存在");
        }
        GraphItem domainNode = kgGraphNodeService.getDomainNode(domainId);
        return R.success(domainNode, "操作成功");
    }

    @ResponseBody
    @GetMapping(value = "/execute")
    public R<GraphItem> execute(Integer domainId){
        KgDomain domainItem = kgService.selectById(domainId);
        if (domainItem == null) {
            return R.error("领域不存在");
        }
         workFlowDirectorService.direct(domainId);
        return R.success();
    }
}
