package com.ruoyi.system.domain.KgBuilderPojo.entity;


import com.ruoyi.common.core.domain.entity.KgBuilder.KgbuilderBaseEntity;
import lombok.Data;

import java.io.Serializable;
/**
 * (MetaDataTable)实体类
 *
 * @author tanc
 * @since 2021-11-21 11:00:20
 */
@Data
public class MetaDataTable extends KgbuilderBaseEntity implements Serializable {

    private static final long serialVersionUID = 146313789945681038L;

    /**
    * 数据表主键
    */
    private Integer dataTableId;

    /**
    * 数据源id
    */
    private Integer datasourceId;

    /**
    * 表名
    */
    private String dataTableCode;

    /**
    * 表别名
    */
    private String dataTableName;

}