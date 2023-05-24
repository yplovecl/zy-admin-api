package com.ruoyi.project.seismograph.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.seismograph.domain.EquipmentSeconded;
import com.ruoyi.project.seismograph.mapper.EquipmentSecondedMapper;
import com.ruoyi.project.seismograph.service.IEquipmentSecondedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备借调日志Service业务层处理
 *
 * @author pange
 * @date 2023-05-23
 */
@Service
public class EquipmentSecondedServiceImpl implements IEquipmentSecondedService {
    @Autowired
    private EquipmentSecondedMapper equipmentSecondedMapper;

    /**
     * 查询设备借调日志
     *
     * @param equipmentSecondedId 设备借调日志主键
     * @return 设备借调日志
     */
    @Override
    public EquipmentSeconded selectEquipmentSecondedByEquipmentSecondedId(Long equipmentSecondedId) {
        return equipmentSecondedMapper.selectEquipmentSecondedByEquipmentSecondedId(equipmentSecondedId);
    }

    @Override
    public EquipmentSeconded selectByEquipmentIdentity(String equipmentIdentity) {
        return equipmentSecondedMapper.selectByEquipmentIdentity(equipmentIdentity);
    }

    /**
     * 查询设备借调日志列表
     *
     * @param equipmentSeconded 设备借调日志
     * @return 设备借调日志
     */
    @Override
    public List<EquipmentSeconded> selectEquipmentSecondedList(EquipmentSeconded equipmentSeconded) {
        return equipmentSecondedMapper.selectEquipmentSecondedList(equipmentSeconded);
    }

    /**
     * 新增设备借调日志
     *
     * @param equipmentSeconded 设备借调日志
     * @return 结果
     */
    @Override
    public int insertEquipmentSeconded(EquipmentSeconded equipmentSeconded) {
        equipmentSeconded.setCreateTime(DateUtils.getNowDate());
        return equipmentSecondedMapper.insertEquipmentSeconded(equipmentSeconded);
    }

    /**
     * 修改设备借调日志
     *
     * @param equipmentSeconded 设备借调日志
     * @return 结果
     */
    @Override
    public int updateEquipmentSeconded(EquipmentSeconded equipmentSeconded) {
        equipmentSeconded.setUpdateTime(DateUtils.getNowDate());
        return equipmentSecondedMapper.updateEquipmentSeconded(equipmentSeconded);
    }

    /**
     * 批量删除设备借调日志
     *
     * @param equipmentSecondedIds 需要删除的设备借调日志主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentSecondedByEquipmentSecondedIds(Long[] equipmentSecondedIds) {
        return equipmentSecondedMapper.deleteEquipmentSecondedByEquipmentSecondedIds(equipmentSecondedIds);
    }

    /**
     * 删除设备借调日志信息
     *
     * @param equipmentSecondedId 设备借调日志主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentSecondedByEquipmentSecondedId(Long equipmentSecondedId) {
        return equipmentSecondedMapper.deleteEquipmentSecondedByEquipmentSecondedId(equipmentSecondedId);
    }
}
