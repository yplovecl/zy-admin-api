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
import com.ruoyi.project.seismograph.domain.Document;
import com.ruoyi.project.seismograph.service.IDocumentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 文档管理Controller
 * 
 * @author pange
 * @date 2023-04-18
 */
@RestController
@RequestMapping("/seismograph/document")
public class DocumentController extends BaseController
{
    @Autowired
    private IDocumentService documentService;

    /**
     * 查询文档管理列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:list')")
    @GetMapping("/list")
    public TableDataInfo list(Document document)
    {
        startPage();
        List<Document> list = documentService.selectDocumentList(document);
        return getDataTable(list);
    }

    /**
     * 导出文档管理列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:export')")
    @Log(title = "文档管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Document document)
    {
        List<Document> list = documentService.selectDocumentList(document);
        ExcelUtil<Document> util = new ExcelUtil<Document>(Document.class);
        util.exportExcel(response, list, "文档管理数据");
    }

    /**
     * 获取文档管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:query')")
    @GetMapping(value = "/{documentMgtId}")
    public AjaxResult getInfo(@PathVariable("documentMgtId") Long documentMgtId)
    {
        return success(documentService.selectDocumentByDocumentMgtId(documentMgtId));
    }

    /**
     * 新增文档管理
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:add')")
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Document document)
    {
        return toAjax(documentService.insertDocument(document));
    }

    /**
     * 修改文档管理
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:edit')")
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Document document)
    {
        return toAjax(documentService.updateDocument(document));
    }

    /**
     * 删除文档管理
     */
    @PreAuthorize("@ss.hasPermi('seismograph:document:remove')")
    @Log(title = "文档管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{documentMgtIds}")
    public AjaxResult remove(@PathVariable Long[] documentMgtIds)
    {
        return toAjax(documentService.deleteDocumentByDocumentMgtIds(documentMgtIds));
    }
}
