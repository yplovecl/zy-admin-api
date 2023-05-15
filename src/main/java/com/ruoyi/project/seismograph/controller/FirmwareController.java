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
import com.ruoyi.project.seismograph.domain.Firmware;
import com.ruoyi.project.seismograph.service.IFirmwareService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 固件升级Controller
 * 
 * @author pange
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/seismograph/firmware")
public class FirmwareController extends BaseController
{
    @Autowired
    private IFirmwareService firmwareService;

    /**
     * 查询固件升级列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:list')")
    @GetMapping("/list")
    public TableDataInfo list(Firmware firmware)
    {
        startPage();
        List<Firmware> list = firmwareService.selectFirmwareList(firmware);
        return getDataTable(list);
    }

    /**
     * 导出固件升级列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:export')")
    @Log(title = "固件升级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Firmware firmware)
    {
        List<Firmware> list = firmwareService.selectFirmwareList(firmware);
        ExcelUtil<Firmware> util = new ExcelUtil<Firmware>(Firmware.class);
        util.exportExcel(response, list, "固件升级数据");
    }

    /**
     * 获取固件升级详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:query')")
    @GetMapping(value = "/{firmwareId}")
    public AjaxResult getInfo(@PathVariable("firmwareId") String firmwareId)
    {
        return success(firmwareService.selectFirmwareByFirmwareId(firmwareId));
    }

    /**
     * 新增固件升级
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:add')")
    @Log(title = "固件升级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Firmware firmware)
    {
        return toAjax(firmwareService.insertFirmware(firmware));
    }

    /**
     * 修改固件升级
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:edit')")
    @Log(title = "固件升级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Firmware firmware)
    {
        return toAjax(firmwareService.updateFirmware(firmware));
    }

    /**
     * 删除固件升级
     */
    @PreAuthorize("@ss.hasPermi('seismograph:firmware:remove')")
    @Log(title = "固件升级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{firmwareIds}")
    public AjaxResult remove(@PathVariable String[] firmwareIds)
    {
        return toAjax(firmwareService.deleteFirmwareByFirmwareIds(firmwareIds));
    }
}
