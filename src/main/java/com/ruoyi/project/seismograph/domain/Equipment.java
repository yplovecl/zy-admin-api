package com.ruoyi.project.seismograph.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 设备对象 t_equipment
 *
 * @author pange
 * @date 2023-04-18
 */
public class Equipment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long equipmentId;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号")
    private String equipmentIdentity;

    /**
     * 企业id
     */
    private Long enterpriseId;

    @Excel(name = "所属企业")
    private String enterpriseName;

    /**
     * 第一次使用
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第一次使用", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstUseTime;

    /**
     * 使用时长
     */
    @Excel(name = "使用时长")
    private Long accumulativeUseTime;

    /**
     * 站点号
     */
    @Excel(name = "站点号")
    private String siteNo;

    /**
     * 站点名
     */
    @Excel(name = "站点名")
    private String siteName;

    /**
     * 布设人
     */
    @Excel(name = "布设人")
    private String deployer;

    /**
     * 站点地址
     */
    @Excel(name = "站点地址")
    private String siteLoc;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private String siteLocLon;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private String siteLocLat;

    /**
     * 站点图片
     */
    @Excel(name = "站点图片")
    private String siteImageUri;

    /**
     * 站点视频
     */
    @Excel(name = "站点视频")
    private String siteVideoUri;

    private String online;

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentIdentity(String equipmentIdentity) {
        this.equipmentIdentity = equipmentIdentity;
    }

    public String getEquipmentIdentity() {
        return equipmentIdentity;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setFirstUseTime(Date firstUseTime) {
        this.firstUseTime = firstUseTime;
    }

    public Date getFirstUseTime() {
        return firstUseTime;
    }

    public void setAccumulativeUseTime(Long accumulativeUseTime) {
        this.accumulativeUseTime = accumulativeUseTime;
    }

    public Long getAccumulativeUseTime() {
        return accumulativeUseTime;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setDeployer(String deployer) {
        this.deployer = deployer;
    }

    public String getDeployer() {
        return deployer;
    }

    public void setSiteLoc(String siteLoc) {
        this.siteLoc = siteLoc;
    }

    public String getSiteLoc() {
        return siteLoc;
    }

    public void setSiteLocLon(String siteLocLon) {
        this.siteLocLon = siteLocLon;
    }

    public String getSiteLocLon() {
        return siteLocLon;
    }

    public void setSiteLocLat(String siteLocLat) {
        this.siteLocLat = siteLocLat;
    }

    public String getSiteLocLat() {
        return siteLocLat;
    }

    public void setSiteImageUri(String siteImageUri) {
        this.siteImageUri = siteImageUri;
    }

    public String getSiteImageUri() {
        return siteImageUri;
    }

    public void setSiteVideoUri(String siteVideoUri) {
        this.siteVideoUri = siteVideoUri;
    }

    public String getSiteVideoUri() {
        return siteVideoUri;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("equipmentId", getEquipmentId())
                .append("equipmentIdentity", getEquipmentIdentity())
                .append("enterpriseId", getEnterpriseId())
                .append("firstUseTime", getFirstUseTime())
                .append("accumulativeUseTime", getAccumulativeUseTime())
                .append("siteNo", getSiteNo())
                .append("siteName", getSiteName())
                .append("deployer", getDeployer())
                .append("siteLoc", getSiteLoc())
                .append("siteLocLon", getSiteLocLon())
                .append("siteLocLat", getSiteLocLat())
                .append("siteImageUri", getSiteImageUri())
                .append("siteVideoUri", getSiteVideoUri())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("online", getOnline())
                .append("enterpriseName", getEnterpriseName())
                .toString();
    }
}
