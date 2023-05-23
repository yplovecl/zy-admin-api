package com.ruoyi.project.seismograph.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.seismograph.domain.EquipmentSeconded;
import com.ruoyi.project.seismograph.service.IEquipmentSecondedService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 设备借调日志Controller
 * 
 * @author pange
 * @date 2023-05-23
 */
@RestController
@RequestMapping("/seismograph/seconded")
public class EquipmentSecondedController extends BaseController
{
    @Autowired
    private IEquipmentSecondedService equipmentSecondedService;

    /**
     * 查询设备借调日志列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentSeconded equipmentSeconded)
    {
        startPage();
        List<EquipmentSeconded> list = equipmentSecondedService.selectEquipmentSecondedList(equipmentSeconded);
        return getDataTable(list);
    }

    /**
     * 导出设备借调日志列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:export')")
    @Log(title = "设备借调日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EquipmentSeconded equipmentSeconded)
    {
        List<EquipmentSeconded> list = equipmentSecondedService.selectEquipmentSecondedList(equipmentSeconded);
        ExcelUtil<EquipmentSeconded> util = new ExcelUtil<EquipmentSeconded>(EquipmentSeconded.class);
        util.exportExcel(response, list, "设备借调日志数据");
    }

    /**
     * 获取设备借调日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:query')")
    @GetMapping(value = "/{equipmentSecondedId}")
    public AjaxResult getInfo(@PathVariable("equipmentSecondedId") Long equipmentSecondedId)
    {
        return success(equipmentSecondedService.selectEquipmentSecondedByEquipmentSecondedId(equipmentSecondedId));
    }

    /**
     * 新增设备借调日志
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:add')")
    @Log(title = "设备借调日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentSeconded equipmentSeconded)
    {
        return toAjax(equipmentSecondedService.insertEquipmentSeconded(equipmentSeconded));
    }

    /**
     * 修改设备借调日志
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:edit')")
    @Log(title = "设备借调日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentSeconded equipmentSeconded)
    {
        return toAjax(equipmentSecondedService.updateEquipmentSeconded(equipmentSeconded));
    }

    /**
     * 删除设备借调日志
     */
    @PreAuthorize("@ss.hasPermi('seismograph:seconded:remove')")
    @Log(title = "设备借调日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equipmentSecondedIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentSecondedIds)
    {
        return toAjax(equipmentSecondedService.deleteEquipmentSecondedByEquipmentSecondedIds(equipmentSecondedIds));
    }
}
