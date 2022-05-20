package com.ruoyi.system.domain.KgBuilderPojo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 知识树管理对象 kg_domain
 * 
 * @author Jarvis
 * @date 2022-05-17
 */
public class KgDomain extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** 0=手动创建，1=三元组导入，2=excel导入，3=er图构建 */
    @Excel(name = "0=手动创建，1=三元组导入，2=excel导入，3=er图构建")
    private Integer type;

    /** 概念描述 */
    @Excel(name = "概念描述")
    private String description;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer nodecount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer shipcount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createuser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String modifyuser;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifytime;

    /** 推荐 */
    @Excel(name = "推荐")
    private Integer commend;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setNodecount(Integer nodecount) 
    {
        this.nodecount = nodecount;
    }

    public Integer getNodecount() 
    {
        return nodecount;
    }
    public void setShipcount(Integer shipcount) 
    {
        this.shipcount = shipcount;
    }

    public Integer getShipcount() 
    {
        return shipcount;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCreateuser(String createuser) 
    {
        this.createuser = createuser;
    }

    public String getCreateuser() 
    {
        return createuser;
    }
    public Date getCreatetime() {return createtime;}

    public void setCreatetime(Date createtime) {this.createtime = createtime;}
    public void setModifyuser(String modifyuser)
    {
        this.modifyuser = modifyuser;
    }

    public String getModifyuser() 
    {
        return modifyuser;
    }
    public void setModifytime(Date modifytime) 
    {
        this.modifytime = modifytime;
    }

    public Date getModifytime() 
    {
        return modifytime;
    }
    public void setCommend(Integer commend) 
    {
        this.commend = commend;
    }

    public Integer getCommend() 
    {
        return commend;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("description", getDescription())
            .append("parentId", getParentId())
            .append("nodecount", getNodecount())
            .append("shipcount", getShipcount())
            .append("status", getStatus())
            .append("createuser", getCreateuser())
            .append("createtime", getCreatetime())
            .append("modifyuser", getModifyuser())
            .append("modifytime", getModifytime())
            .append("commend", getCommend())
            .toString();
    }
}
