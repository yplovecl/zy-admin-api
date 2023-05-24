package com.ruoyi.project.seismograph.mapper;

import java.util.List;
import com.ruoyi.project.seismograph.domain.EquipmentSeconded;

/**
 * 设备借调日志Mapper接口
 * 
 * @author pange
 * @date 2023-05-23
 */
public interface EquipmentSecondedMapper 
{
    /**
     * 查询设备借调日志
     * 
     * @param equipmentSecondedId 设备借调日志主键
     * @return 设备借调日志
     */
    public EquipmentSeconded selectEquipmentSecondedByEquipmentSecondedId(Long equipmentSecondedId);

    public EquipmentSeconded selectByEquipmentIdentity(String equipmentIdentity);

    /**
     * 查询设备借调日志列表
     * 
     * @param equipmentSeconded 设备借调日志
     * @return 设备借调日志集合
     */
    public List<EquipmentSeconded> selectEquipmentSecondedList(EquipmentSeconded equipmentSeconded);

    /**
     * 新增设备借调日志
     * 
     * @param equipmentSeconded 设备借调日志
     * @return 结果
     */
    public int insertEquipmentSeconded(EquipmentSeconded equipmentSeconded);

    /**
     * 修改设备借调日志
     * 
     * @param equipmentSeconded 设备借调日志
     * @return 结果
     */
    public int updateEquipmentSeconded(EquipmentSeconded equipmentSeconded);

    /**
     * 删除设备借调日志
     * 
     * @param equipmentSecondedId 设备借调日志主键
     * @return 结果
     */
    public int deleteEquipmentSecondedByEquipmentSecondedId(Long equipmentSecondedId);

    /**
     * 批量删除设备借调日志
     * 
     * @param equipmentSecondedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquipmentSecondedByEquipmentSecondedIds(Long[] equipmentSecondedIds);
}
