package com.ruoyi.project.seismograph.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * APP版本信息对象 t_version
 * 
 * @author pange
 * @date 2023-04-17
 */
public class Version extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long versionId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String no;

    /** 版本日志 */
    @Excel(name = "版本日志")
    private String log;

    /** APP下载地址 */
    @Excel(name = "APP下载地址")
    private String uri;

    /** 平台 */
    @Excel(name = "平台")
    private String type;

    /** 强制更新 */
    @Excel(name = "强制更新")
    private String enforce;

    public void setVersionId(Long versionId) 
    {
        this.versionId = versionId;
    }

    public Long getVersionId() 
    {
        return versionId;
    }
    public void setNo(String no) 
    {
        this.no = no;
    }

    public String getNo() 
    {
        return no;
    }
    public void setLog(String log) 
    {
        this.log = log;
    }

    public String getLog() 
    {
        return log;
    }
    public void setUri(String uri) 
    {
        this.uri = uri;
    }

    public String getUri() 
    {
        return uri;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setEnforce(String enforce) 
    {
        this.enforce = enforce;
    }

    public String getEnforce() 
    {
        return enforce;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("versionId", getVersionId())
            .append("no", getNo())
            .append("log", getLog())
            .append("uri", getUri())
            .append("type", getType())
            .append("enforce", getEnforce())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
