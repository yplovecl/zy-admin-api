package com.ruoyi.project.seismograph.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.seismograph.mapper.EnterpriseMapper;
import com.ruoyi.project.seismograph.domain.Enterprise;
import com.ruoyi.project.seismograph.service.IEnterpriseService;

/**
 * 企业Service业务层处理
 * 
 * @author pange
 * @date 2023-04-17
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService 
{
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询企业
     * 
     * @param enterpriseId 企业主键
     * @return 企业
     */
    @Override
    public Enterprise selectEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return enterpriseMapper.selectEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询企业列表
     * 
     * @param enterprise 企业
     * @return 企业
     */
    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise)
    {
        return enterpriseMapper.selectEnterpriseList(enterprise);
    }

    /**
     * 新增企业
     * 
     * @param enterprise 企业
     * @return 结果
     */
    @Override
    public int insertEnterprise(Enterprise enterprise)
    {
        enterprise.setCreateTime(DateUtils.getNowDate());
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    /**
     * 修改企业
     * 
     * @param enterprise 企业
     * @return 结果
     */
    @Override
    public int updateEnterprise(Enterprise enterprise)
    {
        enterprise.setUpdateTime(DateUtils.getNowDate());
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    /**
     * 批量删除企业
     * 
     * @param enterpriseIds 需要删除的企业主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByEnterpriseIds(Long[] enterpriseIds)
    {
        return enterpriseMapper.deleteEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除企业信息
     * 
     * @param enterpriseId 企业主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return enterpriseMapper.deleteEnterpriseByEnterpriseId(enterpriseId);
    }
}
