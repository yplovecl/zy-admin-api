package com.ruoyi.project.seismograph.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.seismograph.domain.Equipment;
import com.ruoyi.project.seismograph.mapper.EquipmentMapper;
import com.ruoyi.project.seismograph.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备Service业务层处理
 *
 * @author pange
 * @date 2023-04-18
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备
     *
     * @param equipmentId 设备主键
     * @return 设备
     */
    @Override
    public Equipment selectEquipmentByEquipmentId(Long equipmentId) {
        return equipmentMapper.selectEquipmentByEquipmentId(equipmentId);
    }

    @Override
    public Equipment selectByEquipmentIdentity(String equipmentIdentity) {
        return equipmentMapper.selectByEquipmentIdentity(equipmentIdentity);
    }

    /**
     * 查询设备列表
     *
     * @param equipment 设备
     * @return 设备
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment) {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备
     *
     * @param equipment 设备
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment) {
        equipment.setCreateTime(DateUtils.getNowDate());
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备
     *
     * @param equipment 设备
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment) {
        equipment.setUpdateTime(DateUtils.getNowDate());
        return equipmentMapper.updateEquipment(equipment);
    }

    @Override
    public int updateEquipmentStatus(String online, String equipmentIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("online", online);
        jsonObject.put("equipmentIdentity", equipmentIdentity);
        return equipmentMapper.updateEquipmentStatus(jsonObject);
    }

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds) {
        return equipmentMapper.deleteEquipmentByEquipmentIds(equipmentIds);
    }

    /**
     * 删除设备信息
     *
     * @param equipmentId 设备主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentId(Long equipmentId) {
        return equipmentMapper.deleteEquipmentByEquipmentId(equipmentId);
    }
}
