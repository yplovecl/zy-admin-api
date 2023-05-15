package com.ruoyi.project.seismograph.service;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Firmware;

/**
 * 固件升级Service接口
 * 
 * @author pange
 * @date 2023-05-15
 */
public interface IFirmwareService 
{
    /**
     * 查询固件升级
     * 
     * @param firmwareId 固件升级主键
     * @return 固件升级
     */
    public Firmware selectFirmwareByFirmwareId(String firmwareId);

    /**
     * 查询固件升级列表
     * 
     * @param firmware 固件升级
     * @return 固件升级集合
     */
    public List<Firmware> selectFirmwareList(Firmware firmware);

    /**
     * 新增固件升级
     * 
     * @param firmware 固件升级
     * @return 结果
     */
    public int insertFirmware(Firmware firmware);

    /**
     * 修改固件升级
     * 
     * @param firmware 固件升级
     * @return 结果
     */
    public int updateFirmware(Firmware firmware);

    /**
     * 批量删除固件升级
     * 
     * @param firmwareIds 需要删除的固件升级主键集合
     * @return 结果
     */
    public int deleteFirmwareByFirmwareIds(String[] firmwareIds);

    /**
     * 删除固件升级信息
     * 
     * @param firmwareId 固件升级主键
     * @return 结果
     */
    public int deleteFirmwareByFirmwareId(String firmwareId);
}
