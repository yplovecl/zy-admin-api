package com.ruoyi.project.seismograph.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 固件升级对象 t_firmware
 * 
 * @author pange
 * @date 2023-05-15
 */
public class Firmware extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String firmwareId;

    /** 固件类型 */
    @Excel(name = "固件类型")
    private Integer firmwareType;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 升级包 */
    @Excel(name = "升级包")
    private String filePath;

    /** 文件Hash */
    @Excel(name = "文件Hash")
    private String fileHash;

    public void setFirmwareId(String firmwareId) 
    {
        this.firmwareId = firmwareId;
    }

    public String getFirmwareId() 
    {
        return firmwareId;
    }
    public void setFirmwareType(Integer firmwareType) 
    {
        this.firmwareType = firmwareType;
    }

    public Integer getFirmwareType() 
    {
        return firmwareType;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileHash(String fileHash) 
    {
        this.fileHash = fileHash;
    }

    public String getFileHash() 
    {
        return fileHash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("firmwareId", getFirmwareId())
            .append("firmwareType", getFirmwareType())
            .append("version", getVersion())
            .append("filePath", getFilePath())
            .append("fileHash", getFileHash())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
