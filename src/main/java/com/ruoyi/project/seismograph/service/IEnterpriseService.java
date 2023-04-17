package com.ruoyi.project.seismograph.service;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Enterprise;

/**
 * 企业Service接口
 * 
 * @author pange
 * @date 2023-04-17
 */
public interface IEnterpriseService 
{
    /**
     * 查询企业
     * 
     * @param enterpriseId 企业主键
     * @return 企业
     */
    public Enterprise selectEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询企业列表
     * 
     * @param enterprise 企业
     * @return 企业集合
     */
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    /**
     * 新增企业
     * 
     * @param enterprise 企业
     * @return 结果
     */
    public int insertEnterprise(Enterprise enterprise);

    /**
     * 修改企业
     * 
     * @param enterprise 企业
     * @return 结果
     */
    public int updateEnterprise(Enterprise enterprise);

    /**
     * 批量删除企业
     * 
     * @param enterpriseIds 需要删除的企业主键集合
     * @return 结果
     */
    public int deleteEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    /**
     * 删除企业信息
     * 
     * @param enterpriseId 企业主键
     * @return 结果
     */
    public int deleteEnterpriseByEnterpriseId(Long enterpriseId);
}
