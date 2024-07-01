package com.ruoyi.project.seismograph.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * emqx配置信息对象 t_emqx_config
 *
 * @author pange
 * @date 2024-07-01
 */
public class EmqxConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 接口公共url地址
     */
    @Excel(name = "接口公共url地址")
    private String emqxBaseUrl;

    /**
     * 接口用户名
     */
    @Excel(name = "接口用户名")
    private String emqxUser;

    /**
     * 接口密码
     */
    @Excel(name = "接口密码")
    private String emqxPassword;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmqxBaseUrl(String emqxBaseUrl) {
        this.emqxBaseUrl = emqxBaseUrl;
    }

    public String getEmqxBaseUrl() {
        return emqxBaseUrl;
    }

    public void setEmqxUser(String emqxUser) {
        this.emqxUser = emqxUser;
    }

    public String getEmqxUser() {
        return emqxUser;
    }

    public void setEmqxPassword(String emqxPassword) {
        this.emqxPassword = emqxPassword;
    }

    public String getEmqxPassword() {
        return emqxPassword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("emqxBaseUrl", getEmqxBaseUrl())
                .append("emqxUser", getEmqxUser())
                .append("emqxPassword", getEmqxPassword())
                .toString();
    }
}
