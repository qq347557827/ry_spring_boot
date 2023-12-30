package com.ruoyi.web.controller.order;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.domain.SysOrderAndCustomer;
import com.ruoyi.system.domain.SysOrderExport;
import com.ruoyi.system.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
@RestController
@RequestMapping("/system/order")

public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询订单列表
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @PreAuthorize("@ss.hasPermi('system:order:list')")

    @GetMapping("/list")
    @Log(title = "订单查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 导出待发货订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "发货订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export_shipment")
    public void exportShipment(HttpServletResponse response, SysOrderExport order)
    {
        System.out.printf("arrids", order);
        long startTime = System.nanoTime();
        List<SysOrderExport> list = sysOrderService.selectSysOrderExportShipmentList(order);

        ExcelUtil<SysOrderExport> util = new ExcelUtil<SysOrderExport>(SysOrderExport.class);
        util.exportExcel(response, list, "订单数据");
        sysOrderService.updateOrderExportShipmentByOrderId(list);
        // 记录结束时间
        long endTime = System.nanoTime();

        // 计算执行时间（以毫秒为单位）
        long elapsedTimeMillis = (endTime - startTime) / 1000000;

        System.out.println("selectSysOrderExportShipmentList执行时间: " + elapsedTimeMillis + " 毫秒");
    }
    /**
     * 导入发货订单列表
     */
    @Log(title = "导入发货", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysOrderExport> util = new ExcelUtil<>(SysOrderExport.class);
        List<SysOrderExport> orderExports = util.importExcel(file.getInputStream());

        System.out.printf("orderExports`", orderExports);
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        String operName = loginUser.getUsername();
//        String message =
//        return AjaxResult.success(message);
        return sysOrderService.importShipmentOrder(orderExports);
    }

    // 获取导入模版
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysOrderExport> util = new ExcelUtil<SysOrderExport>(SysOrderExport.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(sysOrderService.selectSysOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        Long user_id = SecurityUtils.getUserId();
        System.out.printf("sysOrder.getName:",user_id, sysOrder.getName());
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }


    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/AddOrderAndCustomer")
    public AjaxResult addOrderAndCustomer(@RequestBody SysOrderAndCustomer sysOrderAndCustomer)
    {
        Long user_id = SecurityUtils.getUserId();
//        SysOrder order = objectMapper.convertValue(requestData.get("sysOrder"), SysOrder.class);
//        Customer customer = objectMapper.convertValue(requestData.get("customer"), Customer.class);

        return toAjax(sysOrderService.insertSysOrderAndCustomer(sysOrderAndCustomer));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        String orderStatus = sysOrderService.selectSysOrderStatusByOrderId(sysOrder.getOrderId()).getStatus();
        System.out.printf("orderStatus:", orderStatus);
        AjaxResult ajaxResult;
        if (SecurityUtils.hasRole("manager")) {
            ajaxResult = toAjax(sysOrderService.updateSysOrder(sysOrder));
        } else if (Objects.equals(orderStatus, "0")) {
            ajaxResult = toAjax(sysOrderService.updateSysOrder(sysOrder));

        } else {
            ajaxResult = toAjax(false);
        }

        return ajaxResult;
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "订单删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(sysOrderService.deleteSysOrderByOrderIds(orderIds));
    }

    /**
     * 审核订单
     *
     */
    @PreAuthorize("@ss.hasPermi('system:order:approval')")
    @Log(title = "批量审核订单修改", businessType = BusinessType.UPDATE)
    @PostMapping("/approval")
    public AjaxResult approvalOrderStatusByOrderIds(@RequestBody String[] idsArr)
    {
        return sysOrderService.approvalOrderStatusByOrderIds(idsArr);
    }

    /**
     * 拒绝订单
     *
     */
    @PreAuthorize("@ss.hasPermi('system:order:approval')")
    @Log(title = "批量拒绝订单修改", businessType = BusinessType.UPDATE)
    @PostMapping("/reject")
    public AjaxResult rejectOrderStatusByOrderIds(@RequestBody String[] idsArr)
    {
        return sysOrderService.rejectOrderStatusByOrderIds(idsArr);
    }

    /**
     * 审核或者拒绝订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单审核或者拒绝", businessType = BusinessType.UPDATE)
    @PostMapping("/approvalReject")
    public int approvalRejectOrderStatusByOrderId(@RequestBody SysOrder sysOrder)
    {
        return sysOrderService.approvalRejectOrderStatusByOrderId(sysOrder);
    }

    /**
     * 审核或者拒绝订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单发货", businessType = BusinessType.UPDATE)
    @PostMapping("/shipment")
    public AjaxResult shipmentByOrderId(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.shipmentByOrderId(sysOrder));
    }

    /**
     * 审核或者拒绝订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单发货", businessType = BusinessType.UPDATE)
    @PostMapping("/secondaryRevenue")
    public AjaxResult secondaryRevenueByOrderId(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.secondaryRevenueByOrderId(sysOrder));
    }

}
