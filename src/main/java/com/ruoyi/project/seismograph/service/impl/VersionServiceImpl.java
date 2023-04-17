package com.ruoyi.project.seismograph.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.seismograph.mapper.VersionMapper;
import com.ruoyi.project.seismograph.domain.Version;
import com.ruoyi.project.seismograph.service.IVersionService;

/**
 * APP版本信息Service业务层处理
 * 
 * @author pange
 * @date 2023-04-17
 */
@Service
public class VersionServiceImpl implements IVersionService 
{
    @Autowired
    private VersionMapper versionMapper;

    /**
     * 查询APP版本信息
     * 
     * @param versionId APP版本信息主键
     * @return APP版本信息
     */
    @Override
    public Version selectVersionByVersionId(Long versionId)
    {
        return versionMapper.selectVersionByVersionId(versionId);
    }

    /**
     * 查询APP版本信息列表
     * 
     * @param version APP版本信息
     * @return APP版本信息
     */
    @Override
    public List<Version> selectVersionList(Version version)
    {
        return versionMapper.selectVersionList(version);
    }

    /**
     * 新增APP版本信息
     * 
     * @param version APP版本信息
     * @return 结果
     */
    @Override
    public int insertVersion(Version version)
    {
        version.setCreateTime(DateUtils.getNowDate());
        return versionMapper.insertVersion(version);
    }

    /**
     * 修改APP版本信息
     * 
     * @param version APP版本信息
     * @return 结果
     */
    @Override
    public int updateVersion(Version version)
    {
        version.setUpdateTime(DateUtils.getNowDate());
        return versionMapper.updateVersion(version);
    }

    /**
     * 批量删除APP版本信息
     * 
     * @param versionIds 需要删除的APP版本信息主键
     * @return 结果
     */
    @Override
    public int deleteVersionByVersionIds(Long[] versionIds)
    {
        return versionMapper.deleteVersionByVersionIds(versionIds);
    }

    /**
     * 删除APP版本信息信息
     * 
     * @param versionId APP版本信息主键
     * @return 结果
     */
    @Override
    public int deleteVersionByVersionId(Long versionId)
    {
        return versionMapper.deleteVersionByVersionId(versionId);
    }
}
