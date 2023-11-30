package com.ruoyi.project.seismograph.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.service.ISysDeptService;
import org.apache.commons.lang3.ObjectUtils;
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
import com.ruoyi.project.seismograph.domain.Enterprise;
import com.ruoyi.project.seismograph.service.IEnterpriseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 企业Controller
 * 
 * @author pange
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/seismograph/enterprise")
public class EnterpriseController extends BaseController
{
    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询企业列表
     */
    @PreAuthorize("@ss.hasAnyPermi('seismograph:enterprise:list,seismograph:equipment:seconded')")
    @GetMapping("/list")
    public TableDataInfo list(Enterprise enterprise)
    {
        startPage();
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:enterprise:export')")
    @Log(title = "企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Enterprise enterprise)
    {
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        ExcelUtil<Enterprise> util = new ExcelUtil<Enterprise>(Enterprise.class);
        util.exportExcel(response, list, "企业数据");
    }

    /**
     * 获取企业详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:enterprise:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") Long enterpriseId)
    {
        return success(enterpriseService.selectEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增企业
     */
    @PreAuthorize("@ss.hasPermi('seismograph:enterprise:add')")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Enterprise enterprise)
    {
        int row = enterpriseService.insertEnterprise(enterprise);
        if(row > 0){
            SysDept dept = new SysDept();
            dept.setEnterpriseId(Long.valueOf(enterprise.getEnterpriseId()));
            dept.setDeptName(enterprise.getAbbreviation());
//            dept.setParentId(0l);
            dept.setLeader(enterprise.getContactPerson());
            dept.setPhone(enterprise.getContactWay());
            dept.setCreateBy(getUsername());
//            dept.setStatus("0");
            deptService.insertDept(dept);
        }
        return toAjax(row);
    }

    /**
     * 修改企业
     */
    @PreAuthorize("@ss.hasPermi('seismograph:enterprise:edit')")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Enterprise enterprise)
    {
        return toAjax(enterpriseService.updateEnterprise(enterprise));
    }

    /**
     * 删除企业
     */
    @PreAuthorize("@ss.hasPermi('seismograph:enterprise:remove')")
    @Log(title = "企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIds)
    {
        return toAjax(enterpriseService.deleteEnterpriseByEnterpriseIds(enterpriseIds));
    }
}
