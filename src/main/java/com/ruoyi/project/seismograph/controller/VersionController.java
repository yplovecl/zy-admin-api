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
import com.ruoyi.project.seismograph.domain.Version;
import com.ruoyi.project.seismograph.service.IVersionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * APP版本信息Controller
 * 
 * @author pange
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/seismograph/version")
public class VersionController extends BaseController
{
    @Autowired
    private IVersionService versionService;

    /**
     * 查询APP版本信息列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:list')")
    @GetMapping("/list")
    public TableDataInfo list(Version version)
    {
        startPage();
        List<Version> list = versionService.selectVersionList(version);
        return getDataTable(list);
    }

    /**
     * 导出APP版本信息列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:export')")
    @Log(title = "APP版本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Version version)
    {
        List<Version> list = versionService.selectVersionList(version);
        ExcelUtil<Version> util = new ExcelUtil<Version>(Version.class);
        util.exportExcel(response, list, "APP版本信息数据");
    }

    /**
     * 获取APP版本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:query')")
    @GetMapping(value = "/{versionId}")
    public AjaxResult getInfo(@PathVariable("versionId") Long versionId)
    {
        return success(versionService.selectVersionByVersionId(versionId));
    }

    /**
     * 新增APP版本信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:add')")
    @Log(title = "APP版本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Version version)
    {
        return toAjax(versionService.insertVersion(version));
    }

    /**
     * 修改APP版本信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:edit')")
    @Log(title = "APP版本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Version version)
    {
        return toAjax(versionService.updateVersion(version));
    }

    /**
     * 删除APP版本信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:version:remove')")
    @Log(title = "APP版本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{versionIds}")
    public AjaxResult remove(@PathVariable Long[] versionIds)
    {
        return toAjax(versionService.deleteVersionByVersionIds(versionIds));
    }
}
