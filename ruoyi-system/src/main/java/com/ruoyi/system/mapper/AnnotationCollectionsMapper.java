package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AnnotationCollections;

import java.util.List;

/**
 * 批解收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-22
 */
public interface AnnotationCollectionsMapper 
{
    /**
     * 查询批解收藏
     * 
     * @param id 批解收藏主键
     * @return 批解收藏
     */
    public AnnotationCollections selectAnnotationCollectionsById(Long id);

    /**
     * 查询批解收藏列表
     * 
     * @param annotationCollections 批解收藏
     * @return 批解收藏集合
     */
    public List<AnnotationCollections> selectAnnotationCollectionsList(AnnotationCollections annotationCollections);

    /**
     * 查询批解收藏列表
     *
     * @param userId  通过userid查询批解收藏立
     * @return 批解收藏集合
     */
    public List<AnnotationCollections> selectAnnotationCollectionsByUserId(AnnotationCollections annotationCollections);
//    public List<AnnotationCollections> selectAnnotationCollectionsByUserId(@Param("userId") Long userId);

    /**
     * 新增批解收藏
     * 
     * @param annotationCollections 批解收藏
     * @return 结果
     */
    public int insertAnnotationCollections(AnnotationCollections annotationCollections);

    /**
     * 修改批解收藏
     * 
     * @param annotationCollections 批解收藏
     * @return 结果
     */
    public int updateAnnotationCollections(AnnotationCollections annotationCollections);

    /**
     * 删除批解收藏
     * 
     * @param id 批解收藏主键
     * @return 结果
     */
    public int deleteAnnotationCollectionsById(Long id);

    /**
     * 批量删除批解收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnnotationCollectionsByIds(Long[] ids);
}
