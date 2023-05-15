package com.ruoyi.project.seismograph.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.seismograph.mapper.FirmwareMapper;
import com.ruoyi.project.seismograph.domain.Firmware;
import com.ruoyi.project.seismograph.service.IFirmwareService;

/**
 * 固件升级Service业务层处理
 * 
 * @author pange
 * @date 2023-05-15
 */
@Service
public class FirmwareServiceImpl implements IFirmwareService 
{
    @Autowired
    private FirmwareMapper firmwareMapper;

    /**
     * 查询固件升级
     * 
     * @param firmwareId 固件升级主键
     * @return 固件升级
     */
    @Override
    public Firmware selectFirmwareByFirmwareId(String firmwareId)
    {
        return firmwareMapper.selectFirmwareByFirmwareId(firmwareId);
    }

    /**
     * 查询固件升级列表
     * 
     * @param firmware 固件升级
     * @return 固件升级
     */
    @Override
    public List<Firmware> selectFirmwareList(Firmware firmware)
    {
        return firmwareMapper.selectFirmwareList(firmware);
    }

    /**
     * 新增固件升级
     * 
     * @param firmware 固件升级
     * @return 结果
     */
    @Override
    public int insertFirmware(Firmware firmware)
    {
        firmware.setCreateTime(DateUtils.getNowDate());
        return firmwareMapper.insertFirmware(firmware);
    }

    /**
     * 修改固件升级
     * 
     * @param firmware 固件升级
     * @return 结果
     */
    @Override
    public int updateFirmware(Firmware firmware)
    {
        firmware.setUpdateTime(DateUtils.getNowDate());
        return firmwareMapper.updateFirmware(firmware);
    }

    /**
     * 批量删除固件升级
     * 
     * @param firmwareIds 需要删除的固件升级主键
     * @return 结果
     */
    @Override
    public int deleteFirmwareByFirmwareIds(String[] firmwareIds)
    {
        return firmwareMapper.deleteFirmwareByFirmwareIds(firmwareIds);
    }

    /**
     * 删除固件升级信息
     * 
     * @param firmwareId 固件升级主键
     * @return 结果
     */
    @Override
    public int deleteFirmwareByFirmwareId(String firmwareId)
    {
        return firmwareMapper.deleteFirmwareByFirmwareId(firmwareId);
    }
}
