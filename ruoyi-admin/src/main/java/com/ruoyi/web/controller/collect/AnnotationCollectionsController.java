package com.ruoyi.web.controller.collect;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AnnotationCollections;
import com.ruoyi.system.service.IAnnotationCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 批解收藏Controller
 * 
 * @author ruoyi
 * @date 2024-12-22
 */
@RestController
@RequestMapping("/system/collections")
public class AnnotationCollectionsController extends BaseController
{
    @Autowired
    private IAnnotationCollectionsService annotationCollectionsService;

    /**
     * 查询批解收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:collections:list')")
    @GetMapping("/list/all")
    public TableDataInfo list(AnnotationCollections annotationCollections)
    {
        startPage();
        List<AnnotationCollections> list = annotationCollectionsService.selectAnnotationCollectionsList(annotationCollections);
        return getDataTable(list);
    }
    /**
     * 根据userid查询批解收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:collections:list')")
    @GetMapping("/list")
    public TableDataInfo byUserIdList(AnnotationCollections annotationCollections)
    {
        startPage();
        List<AnnotationCollections> list = annotationCollectionsService.getUserAnnotationCollections(annotationCollections);
        return getDataTable(list);
    }

    /**
     * 导出批解收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:collections:export')")
    @Log(title = "批解收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnnotationCollections annotationCollections)
    {
        List<AnnotationCollections> list = annotationCollectionsService.selectAnnotationCollectionsList(annotationCollections);
        ExcelUtil<AnnotationCollections> util = new ExcelUtil<AnnotationCollections>(AnnotationCollections.class);
        util.exportExcel(response, list, "批解收藏数据");
    }

    /**
     * 获取批解收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:collections:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(annotationCollectionsService.selectAnnotationCollectionsById(id));
    }

    /**
     * 新增批解收藏
     */
    @PreAuthorize("@ss.hasPermi('system:collections:add')")
    @Log(title = "批解收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnnotationCollections annotationCollections)
    {
        return toAjax(annotationCollectionsService.insertAnnotationCollections(annotationCollections));
    }

    /**
     * 修改批解收藏
     */
    @PreAuthorize("@ss.hasPermi('system:collections:edit')")
    @Log(title = "批解收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnnotationCollections annotationCollections)
    {
        return toAjax(annotationCollectionsService.updateAnnotationCollections(annotationCollections));
    }

    /**
     * 删除批解收藏
     */
    @PreAuthorize("@ss.hasPermi('system:collections:remove')")
    @Log(title = "批解收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(annotationCollectionsService.deleteAnnotationCollectionsByIds(ids));
    }
}
