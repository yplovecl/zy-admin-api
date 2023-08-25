package com.ruoyi.project.seismograph.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.seismograph.domain.Equipment;
import com.ruoyi.project.seismograph.domain.EquipmentSeconded;
import com.ruoyi.project.seismograph.service.IEquipmentSecondedService;
import com.ruoyi.project.seismograph.service.IEquipmentService;
import com.ruoyi.project.seismograph.utils.ApiRequestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * 设备Controller
 *
 * @author pange
 * @date 2023-04-18
 */
@RestController
@RequestMapping("/seismograph/equipment")
public class EquipmentController extends BaseController {
    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IEquipmentSecondedService equipmentSecondedService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Equipment equipment) {
        startPage();
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && enterpriseId.intValue() > 0) {
            equipment.setEnterpriseId(enterpriseId);
        }
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Equipment equipment) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && enterpriseId.intValue() > 0) {
            equipment.setEnterpriseId(enterpriseId);
        }
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        ExcelUtil<Equipment> util = new ExcelUtil<Equipment>(Equipment.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId) {
        Equipment equipment = equipmentService.selectEquipmentByEquipmentId(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && !enterpriseId.equals(equipment.getEnterpriseId())) {
            return error("设备不存在");
        }
        JSONObject jsonObject = JSONObject.from(equipment);
        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipment.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(seconded)) {
            jsonObject.put("isSeconded", seconded.getIsSeconded());
            jsonObject.put("returnTime", new SimpleDateFormat("yyyy-MM-dd").format(seconded.getReturnTime()));
        }
        JSONObject result = ApiRequestUtils.get5gPayload(equipment.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(result)) {
            jsonObject.put("payload", result);
        }
        return success(jsonObject);
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Equipment equipment) {
        return toAjax(equipmentService.insertEquipment(equipment));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Equipment equipment) {
        return toAjax(equipmentService.updateEquipment(equipment));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds) {
        return toAjax(equipmentService.deleteEquipmentByEquipmentIds(equipmentIds));
    }


    @PreAuthorize("@ss.hasPermi('seismograph:equipment:seconded')")
    @Log(title = "设备借调", businessType = BusinessType.OTHER)
    @PostMapping("/seconded")
    public AjaxResult seconded(@RequestBody EquipmentSeconded equipmentSeconded) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        Equipment equipment = equipmentService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(equipment) || !Objects.equals(equipment.getEnterpriseId(), enterpriseId))
            return error("设备不存在");

        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(seconded) && "N".equals(seconded.getIsSeconded())) return error("设备未归还");

        equipmentSeconded.setFromEnterpriseId(equipment.getEnterpriseId());
        equipmentSeconded.setIsSeconded("N");
        int size = equipmentSecondedService.insertEquipmentSeconded(equipmentSeconded);
        if (size > 0) {
            equipment.setEnterpriseId(equipmentSeconded.getToEnterpriseId());
            equipmentService.updateEquipment(equipment);
            return success("设备借调成功");
        }
        return error("设备借调失败，请稍后再试。");
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:seconded')")
    @Log(title = "设备借调", businessType = BusinessType.OTHER)
    @PostMapping("/return")
    public AjaxResult returnEquipment(@RequestBody EquipmentSeconded equipmentSeconded) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        Equipment equipment = equipmentService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(equipment) || !Objects.equals(equipment.getEnterpriseId(), enterpriseId))
            return error("设备不存在");

        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(seconded) || "Y".equals(seconded.getIsSeconded())) return error("该设备无需归还");

        seconded.setIsSeconded("Y");
        int size = equipmentSecondedService.updateEquipmentSeconded(seconded);
        if (size > 0) {
            equipment.setEnterpriseId(seconded.getFromEnterpriseId());
            equipmentService.updateEquipment(equipment);
            return success("设备归还成功");
        }
        return error("设备归还失败，请稍后再试。");
    }
}
