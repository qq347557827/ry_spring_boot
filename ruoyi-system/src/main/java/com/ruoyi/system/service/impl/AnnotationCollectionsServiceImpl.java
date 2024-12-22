package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.AnnotationCollections;
import com.ruoyi.system.mapper.AnnotationCollectionsMapper;
import com.ruoyi.system.service.IAnnotationCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批解收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-22
 */
@Service
public class AnnotationCollectionsServiceImpl implements IAnnotationCollectionsService 
{
    @Autowired
    private AnnotationCollectionsMapper annotationCollectionsMapper;

    /**
     * 查询批解收藏
     * 
     * @param id 批解收藏主键
     * @return 批解收藏
     */
    @Override
    public AnnotationCollections selectAnnotationCollectionsById(Long id)
    {
        return annotationCollectionsMapper.selectAnnotationCollectionsById(id);
    }

    /**
     * 查询批解收藏列表
     * 
     * @param annotationCollections 批解收藏
     * @return 批解收藏
     */
    @Override
    public List<AnnotationCollections> selectAnnotationCollectionsList(AnnotationCollections annotationCollections)
    {
        return annotationCollectionsMapper.selectAnnotationCollectionsList(annotationCollections);
    }

    /**
     * 查询批解收藏列表
     *
     *
     * @return 批解收藏
     */
    @Override
    public List<AnnotationCollections> getUserAnnotationCollections(AnnotationCollections annotationCollections) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        annotationCollections.setUserId(loginUser.getUserId());
        Long userId = loginUser.getUserId();
        String param1 = "标签2";
        return annotationCollectionsMapper.selectAnnotationCollectionsByUserId(annotationCollections);
    }

    /**
     * 新增批解收藏
     * 
     * @param annotationCollections 批解收藏
     * @return 结果
     */
    @Override
    public int insertAnnotationCollections(AnnotationCollections annotationCollections)
    {
        return annotationCollectionsMapper.insertAnnotationCollections(annotationCollections);
    }

    /**
     * 修改批解收藏
     * 
     * @param annotationCollections 批解收藏
     * @return 结果
     */
    @Override
    public int updateAnnotationCollections(AnnotationCollections annotationCollections)
    {
        return annotationCollectionsMapper.updateAnnotationCollections(annotationCollections);
    }

    /**
     * 批量删除批解收藏
     * 
     * @param ids 需要删除的批解收藏主键
     * @return 结果
     */
    @Override
    public int deleteAnnotationCollectionsByIds(Long[] ids)
    {
        return annotationCollectionsMapper.deleteAnnotationCollectionsByIds(ids);
    }

    /**
     * 删除批解收藏信息
     * 
     * @param id 批解收藏主键
     * @return 结果
     */
    @Override
    public int deleteAnnotationCollectionsById(Long id)
    {
        return annotationCollectionsMapper.deleteAnnotationCollectionsById(id);
    }
}
