package com.ruoyi.project.seismograph.service.impl;

import com.ruoyi.project.seismograph.domain.EmqxConfig;
import com.ruoyi.project.seismograph.mapper.EmqxConfigMapper;
import com.ruoyi.project.seismograph.service.IEmqxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * emqx配置信息Service业务层处理
 *
 * @author pange
 * @date 2024-07-01
 */
@Service
public class EmqxConfigServiceImpl implements IEmqxConfigService {
    @Autowired
    private EmqxConfigMapper emqxConfigMapper;

    /**
     * 查询emqx配置信息
     *
     * @param id emqx配置信息主键
     * @return emqx配置信息
     */
    @Override
    public EmqxConfig selectEmqxConfigById(Long id) {
        return emqxConfigMapper.selectEmqxConfigById(id);
    }

    /**
     * 查询emqx配置信息列表
     *
     * @param emqxConfig emqx配置信息
     * @return emqx配置信息
     */
    @Override
    public List<EmqxConfig> selectEmqxConfigList(EmqxConfig emqxConfig) {
        return emqxConfigMapper.selectEmqxConfigList(emqxConfig);
    }

    /**
     * 新增emqx配置信息
     *
     * @param emqxConfig emqx配置信息
     * @return 结果
     */
    @Override
    public int insertEmqxConfig(EmqxConfig emqxConfig) {
        return emqxConfigMapper.insertEmqxConfig(emqxConfig);
    }

    /**
     * 修改emqx配置信息
     *
     * @param emqxConfig emqx配置信息
     * @return 结果
     */
    @Override
    public int updateEmqxConfig(EmqxConfig emqxConfig) {
        return emqxConfigMapper.updateEmqxConfig(emqxConfig);
    }

    /**
     * 批量删除emqx配置信息
     *
     * @param ids 需要删除的emqx配置信息主键
     * @return 结果
     */
    @Override
    public int deleteEmqxConfigByIds(Long[] ids) {
        return emqxConfigMapper.deleteEmqxConfigByIds(ids);
    }

    /**
     * 删除emqx配置信息信息
     *
     * @param id emqx配置信息主键
     * @return 结果
     */
    @Override
    public int deleteEmqxConfigById(Long id) {
        return emqxConfigMapper.deleteEmqxConfigById(id);
    }
}
