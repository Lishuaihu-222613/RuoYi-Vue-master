package com.ruoyi.system.domain.KgBuilderPojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class KgGraphLink implements Serializable {

    private Long id;

    private String from;

    private String to;

    private String label;

    /**
     * 领域id
     */
    private Integer domainId;

    private static final long serialVersionUID = 1L;
}