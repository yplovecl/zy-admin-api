package com.ruoyi.project.seismograph.mapper;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Firmware;

/**
 * 固件升级Mapper接口
 * 
 * @author pange
 * @date 2023-05-15
 */
public interface FirmwareMapper 
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
     * 删除固件升级
     * 
     * @param firmwareId 固件升级主键
     * @return 结果
     */
    public int deleteFirmwareByFirmwareId(String firmwareId);

    /**
     * 批量删除固件升级
     * 
     * @param firmwareIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFirmwareByFirmwareIds(String[] firmwareIds);
}
