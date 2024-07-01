package com.ruoyi.project.seismograph.service;

import com.ruoyi.project.seismograph.domain.EmqxConfig;

import java.util.List;

/**
 * emqx配置信息Service接口
 *
 * @author pange
 * @date 2024-07-01
 */
public interface IEmqxConfigService {
    /**
     * 查询emqx配置信息
     *
     * @param id emqx配置信息主键
     * @return emqx配置信息
     */
    public EmqxConfig selectEmqxConfigById(Long id);

    /**
     * 查询emqx配置信息列表
     *
     * @param emqxConfig emqx配置信息
     * @return emqx配置信息集合
     */
    public List<EmqxConfig> selectEmqxConfigList(EmqxConfig emqxConfig);

    /**
     * 新增emqx配置信息
     *
     * @param emqxConfig emqx配置信息
     * @return 结果
     */
    public int insertEmqxConfig(EmqxConfig emqxConfig);

    /**
     * 修改emqx配置信息
     *
     * @param emqxConfig emqx配置信息
     * @return 结果
     */
    public int updateEmqxConfig(EmqxConfig emqxConfig);

    /**
     * 批量删除emqx配置信息
     *
     * @param ids 需要删除的emqx配置信息主键集合
     * @return 结果
     */
    public int deleteEmqxConfigByIds(Long[] ids);

    /**
     * 删除emqx配置信息信息
     *
     * @param id emqx配置信息主键
     * @return 结果
     */
    public int deleteEmqxConfigById(Long id);
}
