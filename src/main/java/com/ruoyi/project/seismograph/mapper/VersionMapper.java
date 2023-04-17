package com.ruoyi.project.seismograph.mapper;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Version;

/**
 * APP版本信息Mapper接口
 * 
 * @author pange
 * @date 2023-04-17
 */
public interface VersionMapper 
{
    /**
     * 查询APP版本信息
     * 
     * @param versionId APP版本信息主键
     * @return APP版本信息
     */
    public Version selectVersionByVersionId(Long versionId);

    /**
     * 查询APP版本信息列表
     * 
     * @param version APP版本信息
     * @return APP版本信息集合
     */
    public List<Version> selectVersionList(Version version);

    /**
     * 新增APP版本信息
     * 
     * @param version APP版本信息
     * @return 结果
     */
    public int insertVersion(Version version);

    /**
     * 修改APP版本信息
     * 
     * @param version APP版本信息
     * @return 结果
     */
    public int updateVersion(Version version);

    /**
     * 删除APP版本信息
     * 
     * @param versionId APP版本信息主键
     * @return 结果
     */
    public int deleteVersionByVersionId(Long versionId);

    /**
     * 批量删除APP版本信息
     * 
     * @param versionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVersionByVersionIds(Long[] versionIds);
}
