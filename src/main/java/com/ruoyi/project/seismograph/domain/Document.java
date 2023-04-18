package com.ruoyi.project.seismograph.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 文档管理对象 t_document_mgt
 * 
 * @author pange
 * @date 2023-04-18
 */
public class Document extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long documentMgtId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 设备系列 */
    @Excel(name = "设备系列")
    private String equipmentSeries;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String equipmentIdentity;

    /** 文档 */
    @Excel(name = "文档")
    private String documentName;

    /** 文档URL */
    private String documentUri;

    public void setDocumentMgtId(Long documentMgtId) 
    {
        this.documentMgtId = documentMgtId;
    }

    public Long getDocumentMgtId() 
    {
        return documentMgtId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setEquipmentSeries(String equipmentSeries) 
    {
        this.equipmentSeries = equipmentSeries;
    }

    public String getEquipmentSeries() 
    {
        return equipmentSeries;
    }
    public void setEquipmentIdentity(String equipmentIdentity) 
    {
        this.equipmentIdentity = equipmentIdentity;
    }

    public String getEquipmentIdentity() 
    {
        return equipmentIdentity;
    }
    public void setDocumentName(String documentName) 
    {
        this.documentName = documentName;
    }

    public String getDocumentName() 
    {
        return documentName;
    }
    public void setDocumentUri(String documentUri) 
    {
        this.documentUri = documentUri;
    }

    public String getDocumentUri() 
    {
        return documentUri;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("documentMgtId", getDocumentMgtId())
            .append("type", getType())
            .append("equipmentSeries", getEquipmentSeries())
            .append("equipmentIdentity", getEquipmentIdentity())
            .append("documentName", getDocumentName())
            .append("documentUri", getDocumentUri())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
