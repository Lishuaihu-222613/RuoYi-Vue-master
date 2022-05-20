package com.ruoyi.system.domain.KgBuilderPojo.request;

import lombok.Data;

@Data
public class BatchCreateNodeItem {

    private String domain;

    private String sourceId;

    private String sourceName;

    private Integer entityType;

    private String targetNames ;

    private String[] sourceNames ;

    private String relation;

}
