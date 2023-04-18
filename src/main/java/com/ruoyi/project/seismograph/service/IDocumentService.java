package com.ruoyi.project.seismograph.service;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Document;

/**
 * 文档管理Service接口
 * 
 * @author pange
 * @date 2023-04-18
 */
public interface IDocumentService 
{
    /**
     * 查询文档管理
     * 
     * @param documentMgtId 文档管理主键
     * @return 文档管理
     */
    public Document selectDocumentByDocumentMgtId(Long documentMgtId);

    /**
     * 查询文档管理列表
     * 
     * @param document 文档管理
     * @return 文档管理集合
     */
    public List<Document> selectDocumentList(Document document);

    /**
     * 新增文档管理
     * 
     * @param document 文档管理
     * @return 结果
     */
    public int insertDocument(Document document);

    /**
     * 修改文档管理
     * 
     * @param document 文档管理
     * @return 结果
     */
    public int updateDocument(Document document);

    /**
     * 批量删除文档管理
     * 
     * @param documentMgtIds 需要删除的文档管理主键集合
     * @return 结果
     */
    public int deleteDocumentByDocumentMgtIds(Long[] documentMgtIds);

    /**
     * 删除文档管理信息
     * 
     * @param documentMgtId 文档管理主键
     * @return 结果
     */
    public int deleteDocumentByDocumentMgtId(Long documentMgtId);
}
