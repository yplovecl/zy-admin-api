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
import com.ruoyi.project.seismograph.domain.AppUser;
import com.ruoyi.project.seismograph.service.IAppUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * APP用户Controller
 * 
 * @author pange
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/seismograph/user")
public class AppUserController extends BaseController
{
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询APP用户列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppUser appUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    /**
     * 导出APP用户列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:export')")
    @Log(title = "APP用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppUser appUser)
    {
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        ExcelUtil<AppUser> util = new ExcelUtil<AppUser>(AppUser.class);
        util.exportExcel(response, list, "APP用户数据");
    }

    /**
     * 获取APP用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(appUserService.selectAppUserByUserId(userId));
    }

    /**
     * 新增APP用户
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:add')")
    @Log(title = "APP用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 修改APP用户
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:edit')")
    @Log(title = "APP用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除APP用户
     */
    @PreAuthorize("@ss.hasPermi('seismograph:user:remove')")
    @Log(title = "APP用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(appUserService.deleteAppUserByUserIds(userIds));
    }
}
