package com.ruoyi.system.domain.KgBuilderPojo.entity;


import com.ruoyi.common.core.domain.entity.KgBuilder.KgbuilderBaseEntity;
import lombok.Data;

import java.io.Serializable;
/**
 * (MetaDataColumn)实体类
 *
 * @author tanc
 * @since 2021-11-21 11:00:13
 */
@Data
public class MetaDataColumn extends KgbuilderBaseEntity implements Serializable {

    private static final long serialVersionUID = 728550695358682173L;

    /**
    * 数据列id
    */
    private Integer dataColumnId;

    /**
    * 数据表id
    */
    private Integer dataTableId;

    /**
    * 数据列名称
    */
    private String dataColumnCode;

    /**
    * 数据列别名
    */
    private String dataColumnName;

    /**
    * 数据列描述
    */
    private String dataColumnComment;

    /**
    * 数据类型
    */
    private String dataColumnType;

    /**
     * 是否主键
     */
    private Integer isPrimary;


}