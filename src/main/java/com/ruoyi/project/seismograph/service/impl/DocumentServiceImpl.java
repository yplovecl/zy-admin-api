package com.ruoyi.project.seismograph.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.seismograph.mapper.DocumentMapper;
import com.ruoyi.project.seismograph.domain.Document;
import com.ruoyi.project.seismograph.service.IDocumentService;

/**
 * 文档管理Service业务层处理
 * 
 * @author pange
 * @date 2023-04-18
 */
@Service
public class DocumentServiceImpl implements IDocumentService 
{
    @Autowired
    private DocumentMapper documentMapper;

    /**
     * 查询文档管理
     * 
     * @param documentMgtId 文档管理主键
     * @return 文档管理
     */
    @Override
    public Document selectDocumentByDocumentMgtId(Long documentMgtId)
    {
        return documentMapper.selectDocumentByDocumentMgtId(documentMgtId);
    }

    /**
     * 查询文档管理列表
     * 
     * @param document 文档管理
     * @return 文档管理
     */
    @Override
    public List<Document> selectDocumentList(Document document)
    {
        return documentMapper.selectDocumentList(document);
    }

    /**
     * 新增文档管理
     * 
     * @param document 文档管理
     * @return 结果
     */
    @Override
    public int insertDocument(Document document)
    {
        document.setCreateTime(DateUtils.getNowDate());
        return documentMapper.insertDocument(document);
    }

    /**
     * 修改文档管理
     * 
     * @param document 文档管理
     * @return 结果
     */
    @Override
    public int updateDocument(Document document)
    {
        document.setUpdateTime(DateUtils.getNowDate());
        return documentMapper.updateDocument(document);
    }

    /**
     * 批量删除文档管理
     * 
     * @param documentMgtIds 需要删除的文档管理主键
     * @return 结果
     */
    @Override
    public int deleteDocumentByDocumentMgtIds(Long[] documentMgtIds)
    {
        return documentMapper.deleteDocumentByDocumentMgtIds(documentMgtIds);
    }

    /**
     * 删除文档管理信息
     * 
     * @param documentMgtId 文档管理主键
     * @return 结果
     */
    @Override
    public int deleteDocumentByDocumentMgtId(Long documentMgtId)
    {
        return documentMapper.deleteDocumentByDocumentMgtId(documentMgtId);
    }
}
