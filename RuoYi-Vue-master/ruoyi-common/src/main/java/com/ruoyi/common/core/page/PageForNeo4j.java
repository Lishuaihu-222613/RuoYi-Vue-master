package com.ruoyi.common.core.page;

import com.ruoyi.common.utils.StringUtils;

public class PageForNeo4j {

    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String sortableField;

    /** 排序的方向desc或者asc */
    private String sortType;

    /** 查询标签 */
    private String dynamicLabel;

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortableField() {
        return sortableField;
    }

    public void setSortableField(String sortableField) {
        this.sortableField = sortableField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getDynamicLabel() {
        return dynamicLabel;
    }

    public void setDynamicLabel(String dynamicLabel) {
        this.dynamicLabel = dynamicLabel;
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
}
