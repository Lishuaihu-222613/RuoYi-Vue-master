package com.ruoyi.system.service.KgBuilderService;



import com.ruoyi.common.core.domain.entity.KgBuilder.PageRecord;
import com.ruoyi.system.domain.KgBuilderPojo.dto.DataTableSubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.entity.MetaDataSource;
import com.ruoyi.system.domain.KgBuilderPojo.entity.MetaDataTable;
import com.ruoyi.system.domain.KgBuilderPojo.query.TableQuery;
import com.ruoyi.system.domain.KgBuilderPojo.vo.DataTableVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (MetaDataTable)表服务接口
 *
 * @author tanc
 * @since 2021-11-21 11:00:21
 */
public interface MetaDataTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param dataTableId 主键
     * @return 实例对象
     */
    MetaDataTable queryById(Integer dataTableId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<MetaDataTable> queryAll();

    List<DataTableVo> queryByDatasourceId(Integer datasourceId);

    /**
     * 新增数据
     *
     * @param metaDataTable 实例对象
     * @return 实例对象
     */
    MetaDataTable insert(MetaDataTable metaDataTable);

    /**
     * 修改数据
     *
     * @param metaDataTable 实例对象
     * @return 实例对象
     */
    MetaDataTable update(MetaDataTable metaDataTable);

    /**
     * 通过主键删除数据
     *
     * @param dataTableId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer dataTableId);

    ArrayList<String> getMetaTables(MetaDataSource metaDataSource) ;

    void saveTables(DataTableSubmitItem submitItem);

    PageRecord<Map<String, Object>> getTableRecords(TableQuery queryItem);
}