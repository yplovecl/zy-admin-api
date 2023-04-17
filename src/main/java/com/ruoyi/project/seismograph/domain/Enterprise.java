package com.ruoyi.project.seismograph.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 企业对象 t_enterprise
 * 
 * @author pange
 * @date 2023-04-17
 */
public class Enterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long enterpriseId;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 企业简称 */
    @Excel(name = "企业简称")
    private String abbreviation;

    /** 通讯地址 */
    @Excel(name = "通讯地址")
    private String mailingAddress;

    /** 社会信用代码 */
    @Excel(name = "社会信用代码")
    private String uniformSocialCreditCode;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactWay;

    /** 企业等级 */
    @Excel(name = "企业等级")
    private String enterpriseLevel;

    /** 用户数 */
    @Excel(name = "用户数")
    private Long numberOfUsers;

    /** 空间大小 */
    @Excel(name = "空间大小")
    private Long useDiskSize;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAbbreviation(String abbreviation) 
    {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() 
    {
        return abbreviation;
    }
    public void setMailingAddress(String mailingAddress) 
    {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingAddress() 
    {
        return mailingAddress;
    }
    public void setUniformSocialCreditCode(String uniformSocialCreditCode) 
    {
        this.uniformSocialCreditCode = uniformSocialCreditCode;
    }

    public String getUniformSocialCreditCode() 
    {
        return uniformSocialCreditCode;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setContactWay(String contactWay) 
    {
        this.contactWay = contactWay;
    }

    public String getContactWay() 
    {
        return contactWay;
    }
    public void setEnterpriseLevel(String enterpriseLevel) 
    {
        this.enterpriseLevel = enterpriseLevel;
    }

    public String getEnterpriseLevel() 
    {
        return enterpriseLevel;
    }
    public void setNumberOfUsers(Long numberOfUsers) 
    {
        this.numberOfUsers = numberOfUsers;
    }

    public Long getNumberOfUsers() 
    {
        return numberOfUsers;
    }
    public void setUseDiskSize(Long useDiskSize) 
    {
        this.useDiskSize = useDiskSize;
    }

    public Long getUseDiskSize() 
    {
        return useDiskSize;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseId", getEnterpriseId())
            .append("name", getName())
            .append("abbreviation", getAbbreviation())
            .append("mailingAddress", getMailingAddress())
            .append("uniformSocialCreditCode", getUniformSocialCreditCode())
            .append("contactPerson", getContactPerson())
            .append("contactWay", getContactWay())
            .append("enterpriseLevel", getEnterpriseLevel())
            .append("numberOfUsers", getNumberOfUsers())
            .append("useDiskSize", getUseDiskSize())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
