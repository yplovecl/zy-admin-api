package com.ruoyi.project.seismograph.service;

import com.ruoyi.project.seismograph.domain.Equipment;

import java.util.List;

/**
 * 设备Service接口
 *
 * @author pange
 * @date 2023-04-18
 */
public interface IEquipmentService {
    /**
     * 查询设备
     *
     * @param equipmentId 设备主键
     * @return 设备
     */
    public Equipment selectEquipmentByEquipmentId(Long equipmentId);

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

    public int updateEquipmentStatus(String online, String equipmentIdentity);

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds);

    /**
     * 删除设备信息
     *
     * @param equipmentId 设备主键
     * @return 结果
     */
    public int deleteEquipmentByEquipmentId(Long equipmentId);
}
