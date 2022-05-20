package com.ruoyi.system.service.KgBuilderService;


import com.ruoyi.system.domain.KgBuilderPojo.request.GraphItem;

import java.io.IOException;

/**
 * (KgGraphNode)表服务接口
 *
 * @author tanc
 * @since 2021-12-24 12:14:23
 */
public interface KgGraphNodeService {

    void createNode(GraphItem submitItem) throws IOException;

    GraphItem getDomainNode(Integer domainId) throws IOException;

}