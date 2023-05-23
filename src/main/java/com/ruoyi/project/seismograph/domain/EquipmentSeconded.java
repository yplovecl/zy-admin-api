package com.ruoyi.project.seismograph.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 设备借调日志对象 t_equipment_seconded
 * 
 * @author pange
 * @date 2023-05-23
 */
public class EquipmentSeconded extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long equipmentSecondedId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String equipmentIdentity;

    /** 企业id (外联t_enterprise enterprise_id) */
    @Excel(name = "企业id (外联t_enterprise enterprise_id)")
    private Long fromEnterpriseId;

    /** 企业id (外联t_enterprise enterprise_id) */
    @Excel(name = "企业id (外联t_enterprise enterprise_id)")
    private Long toEnterpriseId;

    /** 归还 */
    @Excel(name = "归还")
    private String isSeconded;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    public void setEquipmentSecondedId(Long equipmentSecondedId) 
    {
        this.equipmentSecondedId = equipmentSecondedId;
    }

    public Long getEquipmentSecondedId() 
    {
        return equipmentSecondedId;
    }
    public void setEquipmentIdentity(String equipmentIdentity) 
    {
        this.equipmentIdentity = equipmentIdentity;
    }

    public String getEquipmentIdentity() 
    {
        return equipmentIdentity;
    }
    public void setFromEnterpriseId(Long fromEnterpriseId) 
    {
        this.fromEnterpriseId = fromEnterpriseId;
    }

    public Long getFromEnterpriseId() 
    {
        return fromEnterpriseId;
    }
    public void setToEnterpriseId(Long toEnterpriseId) 
    {
        this.toEnterpriseId = toEnterpriseId;
    }

    public Long getToEnterpriseId() 
    {
        return toEnterpriseId;
    }
    public void setIsSeconded(String isSeconded) 
    {
        this.isSeconded = isSeconded;
    }

    public String getIsSeconded() 
    {
        return isSeconded;
    }
    public void setReturnTime(Date returnTime) 
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() 
    {
        return returnTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentSecondedId", getEquipmentSecondedId())
            .append("equipmentIdentity", getEquipmentIdentity())
            .append("fromEnterpriseId", getFromEnterpriseId())
            .append("toEnterpriseId", getToEnterpriseId())
            .append("isSeconded", getIsSeconded())
            .append("remark", getRemark())
            .append("returnTime", getReturnTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
