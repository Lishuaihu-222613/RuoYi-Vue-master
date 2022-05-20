package com.ruoyi.system.service.KgBuilderService.impl;


import com.ruoyi.common.core.domain.entity.KgBuilder.PageRecord;
import com.ruoyi.common.utils.Neo4j.DbUtils;
import com.ruoyi.system.domain.KgBuilderPojo.dto.DataTableSubmitItem;
import com.ruoyi.system.domain.KgBuilderPojo.entity.MetaDataColumn;
import com.ruoyi.system.domain.KgBuilderPojo.entity.MetaDataSource;
import com.ruoyi.system.domain.KgBuilderPojo.entity.MetaDataTable;
import com.ruoyi.system.domain.KgBuilderPojo.query.TableQuery;
import com.ruoyi.system.domain.KgBuilderPojo.vo.DataColumnVo;
import com.ruoyi.system.domain.KgBuilderPojo.vo.DataTableVo;
import com.ruoyi.system.mapper.KgBuilderMapper.MetaDataSourceDao;
import com.ruoyi.system.mapper.KgBuilderMapper.MetaDataTableDao;
import com.ruoyi.system.service.KgBuilderService.MetaDataColumnService;
import com.ruoyi.system.service.KgBuilderService.MetaDataTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (MetaDataTable)表服务实现类
 *
 * @author tanc
 * @since 2021-11-21 11:00:22
 */
@Service("metaDataTableService")
public class MetaDataTableServiceImpl implements MetaDataTableService {

    @Resource
    private MetaDataSourceDao metaDataSourceDao;
    @Resource
    private MetaDataTableDao metaDataTableDao;
    @Resource
    private MetaDataColumnService metaDataColumnService;

    /**
     * 通过ID查询单条数据
     *
     * @param dataTableId 主键
     * @return 实例对象
     */
    @Override
    public MetaDataTable queryById(Integer dataTableId) {
        return this.metaDataTableDao.queryById(dataTableId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<MetaDataTable> queryAll() {
        return this.metaDataTableDao.queryAll();
    }

    @Override
    public List<DataTableVo> queryByDatasourceId(Integer datasourceId) {
        List<MetaDataTable> dataTable = this.metaDataTableDao.queryByDatasourceId(datasourceId);
        List<DataTableVo> voList = dataTable.stream().map(n -> {
            DataTableVo item = new DataTableVo();
            item.setDataTableId(n.getDataTableId());
            item.setDatasourceId(n.getDatasourceId());
            item.setDataTableAlia(n.getDataTableName());
            item.setDataTableName(n.getDataTableCode());
            return item;
        }).collect(Collectors.toList());
        return voList;
    }

    /**
     * 新增数据
     *
     * @param metaDataTable 实例对象
     * @return 实例对象
     */
    @Override
    public MetaDataTable insert(MetaDataTable metaDataTable) {
        this.metaDataTableDao.insert(metaDataTable);
        return metaDataTable;
    }

    /**
     * 修改数据
     *
     * @param metaDataTable 实例对象
     * @return 实例对象
     */
    @Override
    public MetaDataTable update(MetaDataTable metaDataTable) {
        this.metaDataTableDao.update(metaDataTable);
        return this.queryById(metaDataTable.getDataTableId());
    }

    /**
     * 通过主键删除数据
     *
     * @param dataTableId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer dataTableId) {
        return this.metaDataTableDao.deleteById(dataTableId) > 0;
    }

    @Override
    public ArrayList<String> getMetaTables(MetaDataSource metaDataSource) {

        String dbType = metaDataSource.getDbType();
        String userName = metaDataSource.getDbUserName();
        String password = metaDataSource.getDbPassWord();
        String driverClassName = metaDataSource.getDriverName();
        ArrayList<String> tables = new ArrayList<>();
        try {
            tables = DbUtils.getTables(dbType, metaDataSource.getDbCode(), userName, password,
                    driverClassName, metaDataSource.getConnectUrl());
        } catch (Exception e) {
            throw e;
        }
        return tables;
    }

    @Override
    public void saveTables(DataTableSubmitItem submitItem) {
        MetaDataSource dataSource = metaDataSourceDao.queryById(submitItem.getDataSourceId());
        String dbType = dataSource.getDbType();
        String userName = dataSource.getDbUserName();
        String password = dataSource.getDbPassWord();
        String driverClassName = dataSource.getDriverName();
        HashMap<String, ArrayList<HashMap<String, String>>> metaData = DbUtils.getMetaData(dbType, dataSource.getDbCode(), dataSource.getConnectUrl(), userName, password,
                driverClassName, submitItem.getDataTables());
        for (String table : metaData.keySet()) {
            MetaDataTable dataTable = new MetaDataTable();
            dataTable.setDataTableName(table);
            dataTable.setDataTableCode(table);
            dataTable.setDatasourceId(dataSource.getDataSourceId());
            insert(dataTable);
            Integer tableId = dataTable.getDataTableId();
            ArrayList<HashMap<String, String>> columns = metaData.get(table);
            for (HashMap<String, String> column : columns) {
                String columnName = column.get("ColumnName");
                String columnAlia = column.get("ColumnAlia");
                String columnType = column.get("ColumnType");
                String isPrimary = column.get("IsPrimary");
                MetaDataColumn columnItem = new MetaDataColumn();
                columnItem.setDataColumnCode(columnName);
                columnItem.setDataColumnName(columnAlia);
                columnItem.setDataColumnComment(columnAlia);
                columnItem.setDataColumnType(columnType);
                columnItem.setDataTableId(tableId);
                columnItem.setIsPrimary(isPrimary.equals("1") ? 1 : 0);
                metaDataColumnService.insert(columnItem);
            }
        }
    }

    public PageRecord<Map<String, Object>> getTableRecords(TableQuery queryItem) {

        MetaDataSource metaDataSource = metaDataSourceDao.queryById(queryItem.getDataSourceId());
        String dbType = metaDataSource.getDbType();
        String userName = metaDataSource.getDbUserName();
        String password = metaDataSource.getDbPassWord();
        String driverClassName = metaDataSource.getDriverName();
        PageRecord<Map<String, Object>> records = new PageRecord<>();
        MetaDataTable metaDataTable = metaDataTableDao.queryByDatasourceIdAndTableCode(queryItem.getDataSourceId(), queryItem.getDataTableName());
        List<DataColumnVo> dataColumnVos = metaDataColumnService.queryByTableId(metaDataTable.getDataTableId());
        try {
            records = DbUtils.getTableInfoByPage(queryItem.getCurrentPage(), queryItem.getPageSize(), dbType, metaDataSource.getDbCode(), metaDataSource.getConnectUrl(), queryItem.getDataTableName(), userName, password,
                    driverClassName, 10, queryItem.getFilterItems());
            records.setHeads(dataColumnVos);
        } catch (Exception e) {
            throw e;
        }
        return records;
    }
}