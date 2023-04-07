package com.ruoyi.web.controller.system.KgBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.ruoyi.common.config.KgBuilderConfig;
import com.ruoyi.common.enums.ReturnStatus;
import com.ruoyi.common.utils.Neo4j.*;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgFeedBack;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgNodeDetail;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgNodeDetailFile;
import com.ruoyi.system.domain.KgBuilderPojo.model.NodeItem;
import com.ruoyi.system.domain.KgBuilderPojo.request.*;
import com.ruoyi.system.service.KgBuilderService.FeedBackService;
import com.ruoyi.system.service.KgBuilderService.KgGraphService;
import com.ruoyi.system.service.KgBuilderService.KnowledgeGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping(value = "/kg")
public class KGManagerController extends BaseController {

    @Autowired
    private KgBuilderConfig config;
    @Autowired
    private KgGraphService kgGraphService;
    @Autowired
    private KnowledgeGraphService kgService;
    @Autowired
    FeedBackService feedBackService;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @ResponseBody
    @PostMapping(value = "/getGraph") // call db.labels
    public R<GraphPageRecord<KgDomain>> getGraph(@RequestBody GraphQuery queryItem) {
        GraphPageRecord<KgDomain> resultRecord = new GraphPageRecord<KgDomain>();
        try {
            PageHelper.startPage(queryItem.getPageIndex(), queryItem.getPageSize(), true);
            List<KgDomain> domainList = kgService.getDomainList(queryItem.getDomain(), queryItem.getType(), queryItem.getCommend());
            PageInfo<KgDomain> pageInfo = new PageInfo<KgDomain>(domainList);
            long total = pageInfo.getTotal();
            resultRecord.setPageIndex(queryItem.getPageIndex());
            resultRecord.setPageSize(queryItem.getPageSize());
            resultRecord.setTotalCount(new Long(total).intValue());
            resultRecord.setNodeList(pageInfo.getList());
            return R.success(resultRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getDomainGraph")
    public R<HashMap<String, Object>> getDomainGraph(@RequestBody GraphQuery query) {
        try {
            HashMap<String, Object> graphData = kgGraphService.getDomainGraph(query);
            System.out.println(graphData);
            return R.success(graphData);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getDomainGraphforG6")
    public R<HashMap<String, Object>> getDomainGraphforG6(@RequestBody GraphQuery query) {
        try {
            HashMap<String, Object> graphData = kgGraphService.getDomainGraph(query);
            return R.success(graphData);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getCypherResult")
    public R<KgDomain> getCypherResult(String cypher) {
        try {
            HashMap<String, Object> graphData = Neo4jUtil.getGraphNodeAndShip(cypher);
            return R.success(graphData);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getRelationNodeCount")
    public R<String> getRelationNodeCount(String domain, long nodeId) {
        try {
            long totalCount = 0;
            if (!StringUtil.isBlank(domain)) {
                totalCount = kgGraphService.getRelationNodeCount(domain, nodeId);
                return R.success(totalCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/createDomain")
    public R<String> createDomain(String domain, Integer type) {
        try {
            if (!StringUtil.isBlank(domain)) {
                List<KgDomain> domainItem = kgService.getDomainByName(domain);
                if (domainItem.size() > 0) {
                    return R.create(ReturnStatus.Error, "领域已存在");
                } else {
                    int domainId = kgService.quickCreateDomain(domain, type);// 保存到mysql
                    kgGraphService.createDomain(domain);// 保存到图数据
                    return R.success(domainId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getMoreRelationNode")
    public R<HashMap<String, Object>> getMoreRelationNode(String domain, String nodeId) {
        try {
            if (!StringUtil.isBlank(domain)) {
                HashMap<String, Object> graphModel = kgGraphService.getMoreRelationNode(domain, nodeId);
                if (graphModel != null) {
                    return R.success(graphModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("没有更多数据了");
    }

    @ResponseBody
    @RequestMapping(value = "/getMoreRelatedNode")
    public R<HashMap<String, Object>> getMoreRelatedNode(String domain, String nodeId) {
        try {
            HashMap<String, Object> graphModel = kgGraphService.getMoreRelationNode(domain, nodeId);
            if (graphModel != null) {

                return R.success(graphModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("没有更多数据了");
    }

    @ResponseBody
    @RequestMapping(value = "/updateNodeName")
    public R<HashMap<String, Object>> updateNodeName(@RequestBody KgNodeItem request) {

        HashMap<String, Object> graphNodeList = new HashMap<String, Object>();
        try {
            if (!StringUtil.isBlank(request.getDomain())) {
                graphNodeList = kgGraphService.updateNodeName(request.getDomain(), request.getNodeId(), request.getNodeName());
                if (graphNodeList.size() > 0) {
                    return R.success(graphNodeList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/updateCoordinateOfNode")
    public R<String> updateCoordinateOfNode(@RequestBody NodeCoordinateItem request) {
        try {
            kgGraphService.updateCoordinateOfNode(request.getDomain(), request.getUuid(), request.getFx(), request.getFy());
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/createNode")
    public R<HashMap<String, Object>> createNode(@RequestBody NodeItem entity) {
        HashMap<String, Object> graphNode = new HashMap<String, Object>();
        try {
//            System.out.println(entity);
//            System.out.println(Arrays.toString(entity.getSize()));
            graphNode = kgGraphService.createNode(entity.getDomain(), entity);
            if (graphNode != null && graphNode.size() > 0) {
                System.out.println(graphNode);
                return R.success(graphNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");

    }

    @ResponseBody
    @RequestMapping(value = "/batchCreateNode")
    public R<HashMap<String, Object>> batchCreateNode(@RequestBody BatchCreateNodeItem request) {

        HashMap<String, Object> rss = new HashMap<String, Object>();
        try {
            String[] tNames = request.getTargetNames().split(",");
            rss = kgGraphService.batchCreateNode(request.getDomain(), request.getSourceName(), request.getRelation(), tNames);
            return R.success(rss);
        } catch (Exception e) {
            e.printStackTrace();

            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/batchCreateChildNode")
    public R<HashMap<String, Object>> batchCreateChildNode(@RequestBody BatchCreateNodeItem request) {

        HashMap<String, Object> rss = new HashMap<String, Object>();
        try {
            String[] tNames = request.getTargetNames().split(",");
            rss = kgGraphService.batchCreateChildNode(request.getDomain(), request.getSourceId(), request.getEntityType(), tNames, request.getRelation());
            return R.success(rss);
        } catch (Exception e) {
            e.printStackTrace();

            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/batchCreateSameNode")
    public R<List<HashMap<String, Object>>> batchCreateSameNode(@RequestBody BatchCreateNodeItem request) {
        List<HashMap<String, Object>> rss = new ArrayList<HashMap<String, Object>>();
        try {
            rss = kgGraphService.batchCreateSameNode(request.getDomain(), request.getEntityType(), request.getSourceNames());
            return R.success(rss);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/createEdge")
    public R<HashMap<String, Object>> createLink(@RequestBody EdgeItem edge) {
        try {
            HashMap<String, Object> Result = kgGraphService.createEdge(edge.getDomain(), edge.getSource(), edge.getTarget(), edge.getLabel());
            return R.success(Result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateEdge")
    public R<HashMap<String, Object>> updateEdge(@RequestBody EdgeItem edge) {
        try {
            System.out.println(edge);
            HashMap<String, Object> Result = kgGraphService.updateEdge(edge.getDomain(), edge);
            if (Result != null && Result.size() > 0) {
                return R.success(Result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteNode")
    public R<List<HashMap<String, Object>>> deleteNode(String domain, String nodeId) {
        try {
            System.out.println(domain + nodeId);
            List<HashMap<String, Object>> rList = kgGraphService.deleteNode(domain, nodeId);
            return R.success(rList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteEdge")
    public R<HashMap<String, Object>> deleteEdge(String domain, String edgeId) {
        try {
            System.out.println(domain + edgeId);
            kgGraphService.deleteEdge(domain, edgeId);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDomain")
    public R<List<HashMap<String, Object>>> deleteDomain(@RequestParam("domainId") Integer domainId, @RequestParam("domain") String domain) {
        try {
            kgService.deleteDomain(domainId);
            kgGraphService.deleteKGDomain(domain);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importGraph")
    public R<String> importGraph(@RequestParam(value = "file", required = true)
                                 @Validated @NotNull(message = "请上传有效的excel的文件") @Pattern(regexp = "^(?:\\w+\\.xlsx|\\w+\\.xls)$", message = "请上传有效的excel的文件")
                                 MultipartFile file,
                                 HttpServletRequest request) throws Exception {
        try {
            String label = request.getParameter("domain");
            label = StringUtil.isBlank(label) ? UuidUtil.getUUID() : label;
            String type = request.getParameter("type");
            if (type.equals("0")) {//三元组导入
                kgService.quickCreateDomain(label, 2);// 三元组
                kgGraphService.importBySyz(file, request, label);
            } else {
                kgService.quickCreateDomain(label, 3);//excel分类
                kgGraphService.importByCategory(file, request, label);
            }
            return R.success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.error("处理失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/exportGraph")
    public Map<String, Object> exportGraph(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>();
        String label = request.getParameter("domain");
        String filePath = config.getLocation();
        String fileName = UUID.randomUUID() + ".csv";
        String fileUrl = filePath + fileName;
        String cypher = String.format(
                "MATCH (n:`%s`) -[r]-(m:`%s`) return n.name as source,m.name as target,r.name as relation", label, label);
        List<HashMap<String, Object>> list = Neo4jUtil.getGraphTable(cypher);
        if (list.size() == 0) {
            res.put("code", -1);
            res.put("message", "该领域没有任何有关系的实体!");
            return res;
        }
        try {
            FileUtil.createFile(fileUrl);
            CsvWriter csvWriter = new CsvWriter(fileUrl, ',', StandardCharsets.UTF_8);
            String[] header = {"source", "target", "relation"};
            csvWriter.writeRecord(header);
            for (HashMap<String, Object> hashMap : list) {
                int colSize = hashMap.size();
                String[] cntArr = new String[colSize];
                cntArr[0] = hashMap.get("source").toString().replace("\"", "");
                cntArr[1] = hashMap.get("target").toString().replace("\"", "");
                cntArr[2] = hashMap.get("relation").toString().replace("\"", "");
                try {
                    csvWriter.writeRecord(cntArr);
                } catch (IOException e) {
                    log.error("CSVUtil->createFile: 文件输出异常" + e.getMessage());
                }
            }
            csvWriter.close();
            String serverUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String csvUrl = "http://" + serverUrl + "/download/" + fileName;

            res.put("code", 200);
            res.put("fileName", csvUrl);
            res.put("message", "success!");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }

    // 文件下载相关代码
    @GetMapping(value = "/download/{filename}")
    public String download(@PathVariable("filename") String filename, HttpServletRequest request,
                           HttpServletResponse response) {
        String filePath = config.getLocation();
        String fileUrl = filePath + filename;
        File file = new File(fileUrl);
        if (file.exists()) {
            //response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                log.info("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getNodeImage")
    public R<List<Map<String, Object>>> getNodeImagelist(int domainId, int nodeId) {
        try {
            List<KgNodeDetailFile> images = kgService.getNodeImageList(domainId, nodeId);
            return R.success(images);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getNodeContent")
    public R<Map<String, Object>> getNodeContent(int domainId, int nodeId) {
        try {
            List<KgNodeDetail> contents = kgService.getNodeContent(domainId, nodeId);
            if (contents != null && contents.size() > 0) {
                return R.success(contents.get(0));
            }
            return R.success(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getNodeDetail")
    public R<Map<String, Object>> getNodeDetail(Integer domainId, Integer nodeId) {

        try {
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("content", "");
            res.put("imageList", new String[]{});
            List<KgNodeDetail> contents = kgService.getNodeContent(domainId, nodeId);
            if (contents != null && contents.size() > 0) {
                res.replace("content", contents.get(0).getContent());
            }
            List<KgNodeDetailFile> images = kgService.getNodeImageList(domainId, nodeId);
            if (images != null && images.size() > 0) {
                res.replace("imageList", images);
            }
            return R.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/feedBack")
    public R<Map<String, Object>> feedBack(KgFeedBack submitItem) {
        try {
            feedBackService.insert(submitItem);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveNodeImage")
    public R<String> saveNodeImage(@RequestBody Map<String, Object> params) {
        try {
            String username = "tc";
            int domainId = (int) params.get("domainId");
            String nodeId = params.get("nodeId").toString();
            String imageList = params.get("imageList").toString();
            List<KgDomain> domainList = kgService.getDomainById(domainId);
            if (domainList != null && domainList.size() > 0) {
                String domainName = domainList.get(0).getName();
                kgService.deleteNodeImage(domainId, Integer.parseInt(nodeId));
                List<Map<String, Object>> imageItems = JsonHelper.parseObject(imageList, new TypeReference<List<Map<String, Object>>>() {
                });
                List<Map<String, Object>> submitItemList = new ArrayList<Map<String, Object>>();
                if (!imageItems.isEmpty()) {
                    for (Map<String, Object> item : imageItems) {
                        String file = item.get("file").toString();
                        int sourceType = 0;
                        Map<String, Object> sb = new HashMap<String, Object>();
                        sb.put("file", file);
                        sb.put("imageType", sourceType);
                        sb.put("domainId", domainId);
                        sb.put("nodeId", nodeId);
                        sb.put("status", 1);
                        sb.put("createUser", username);
                        sb.put("createTime", DateUtil.getDateNow());
                        submitItemList.add(sb);
                    }
                }
                if (submitItemList.size() > 0) {
                    kgService.saveNodeImage(submitItemList);
                    // 更新到图数据库,表明该节点有附件,加个标识,0=没有,1=有
                    kgGraphService.updateNodeFileStatus(domainName, Long.parseLong(nodeId), 1);
                    return R.success("操作成功");
                } else {
                    kgGraphService.updateNodeFileStatus(domainName, Long.parseLong(nodeId), 0);
                    return R.success("操作成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/saveNodeContent")
    public R<String> saveNodeContent(@RequestBody Map<String, Object> params) {
        try {
            String username = "tc";
            int domainId = (int) params.get("domainId");
            String nodeId = params.get("nodeId").toString();
            String content = params.get("content").toString();
            List<KgDomain> domainList = kgService.getDomainById(domainId);
            if (domainList != null && domainList.size() > 0) {
                String domainName = domainList.get(0).getName();
                // 检查是否存在
                List<KgNodeDetail> items = kgService.getNodeContent(domainId, Integer.parseInt(nodeId));
                if (items != null && items.size() > 0) {
                    KgNodeDetail oldItem = items.get(0);
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("domainId", oldItem.getDomainId());
                    item.put("nodeId", oldItem.getNodeId());
                    item.put("content", content);
                    item.put("modifyUser", username);
                    item.put("modifyTime", DateUtil.getDateNow());
                    kgService.updateNodeContent(item);
                    return R.success("更新成功");
                } else {
                    Map<String, Object> sb = new HashMap<String, Object>();
                    sb.put("content", content);
                    sb.put("domainId", domainId);
                    sb.put("nodeId", nodeId);
                    sb.put("status", 1);
                    sb.put("createUser", username);
                    sb.put("createTime", DateUtil.getDateNow());
                    if (sb.size() > 0) {
                        kgService.saveNodeContent(sb);
                        return R.success("保存成功");
                    }
                }
                // 更新到图数据库,表明该节点有附件,加个标识,0=没有,1=有
                kgGraphService.updateNodeFileStatus(domainName, Long.parseLong(nodeId), 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.error("操作失败");
    }

}
