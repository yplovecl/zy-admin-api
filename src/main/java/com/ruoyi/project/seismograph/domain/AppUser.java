package com.ruoyi.project.seismograph.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * APP用户对象 t_user
 * 
 * @author pange
 * @date 2023-04-17
 */
public class AppUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long userId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userAccount;

    /** 密码 */
    private String pwd;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 企业id */
    @Excel(name = "企业id")
    private Long enterpriseId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** token */
    @Excel(name = "token")
    private String token;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setPwd(String pwd) 
    {
        this.pwd = pwd;
    }

    public String getPwd() 
    {
        return pwd;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userAccount", getUserAccount())
            .append("pwd", getPwd())
            .append("name", getName())
            .append("enterpriseId", getEnterpriseId())
            .append("status", getStatus())
            .append("token", getToken())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
