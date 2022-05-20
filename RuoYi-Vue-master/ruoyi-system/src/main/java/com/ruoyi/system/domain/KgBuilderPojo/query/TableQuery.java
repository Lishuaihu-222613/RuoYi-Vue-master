package com.ruoyi.system.domain.KgBuilderPojo.query;


import com.ruoyi.common.core.domain.entity.KgBuilder.FieldQueryItem;
import com.ruoyi.common.core.domain.entity.KgBuilder.PageQuery;
import lombok.Data;

import java.util.List;

@Data
public class TableQuery extends PageQuery {

    private Integer dataSourceId;

    private Integer dataTableId;

    private String  dataTableName;

    private List<FieldQueryItem> filterItems;
}
