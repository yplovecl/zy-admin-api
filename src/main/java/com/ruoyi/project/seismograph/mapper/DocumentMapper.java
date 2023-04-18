package com.ruoyi.project.seismograph.mapper;

import java.util.List;
import com.ruoyi.project.seismograph.domain.Document;

/**
 * 文档管理Mapper接口
 * 
 * @author pange
 * @date 2023-04-18
 */
public interface DocumentMapper 
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
     * 删除文档管理
     * 
     * @param documentMgtId 文档管理主键
     * @return 结果
     */
    public int deleteDocumentByDocumentMgtId(Long documentMgtId);

    /**
     * 批量删除文档管理
     * 
     * @param documentMgtIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDocumentByDocumentMgtIds(Long[] documentMgtIds);
}
