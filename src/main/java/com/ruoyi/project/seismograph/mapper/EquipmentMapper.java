package com.ruoyi.project.seismograph.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.project.seismograph.domain.Equipment;

import java.util.List;

/**
 * 设备Mapper接口
 *
 * @author pange
 * @date 2023-04-18
 */
public interface EquipmentMapper {
    /**
     * 查询设备
     *
     * @param equipmentId 设备主键
     * @return 设备
     */
    public Equipment selectEquipmentByEquipmentId(Long equipmentId);

    public Equipment selectByEquipmentIdentity(String equipmentIdentity);

    /**
     * 查询设备列表
     *
     * @param equipment 设备
     * @return 设备集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备
     *
     * @param equipment 设备
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备
     *
     * @param equipment 设备
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    public int updateEquipmentStatus(JSONObject jsonObject);

    /**
     * 删除设备
     *
     * @param equipmentId 设备主键
     * @return 结果
     */
    public int deleteEquipmentByEquipmentId(Long equipmentId);

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds);
}
