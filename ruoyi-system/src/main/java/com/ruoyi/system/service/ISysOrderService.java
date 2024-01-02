package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.*;

import java.util.List;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
public interface ISysOrderService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public SysOrder selectSysOrderByOrderId(String orderId);


    public SysOrder selectSysOrderStatusByOrderId(String orderId);

    /**
     * 查询订单列表
     * 
     * @param sysOrder 订单
     * @return 订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 查询导出列表
     *
     * @param order 订单
     * @return 订单集合
     */
    public List<SysOrderExport> selectSysOrderExportShipmentList(SysOrderExport order);


    /**
     * 导入发货订单
     *
     * @param orderExportList 订单
     * @return 订单集合
     */
    public AjaxResult importShipmentOrder(List<SysOrderExport> orderExportList);

    /**
     * 新增订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    public int insertSysOrderAndCustomer(SysOrderAndCustomer sysOrderAndCustomer);

    /**
     * 修改订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);
    /**
     * 发货订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int shipmentByOrderId(SysOrder sysOrder);

    /**
     * 发货订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int secondaryRevenueByOrderId(SysOrder sysOrder);

    // 审核订单
    public AjaxResult approvalOrderStatusByOrderIds(String[] ids);


    // 拒绝订单
    public AjaxResult rejectOrderStatusByOrderIds(String[] ids);
    /**
     * 审核或者拒绝订单
     *
     * @param order 订单
     * @return 结果
     */
    public int approvalRejectOrderStatusByOrderId(SysOrder order);

    // 导出后更新导出发货次数
    public void updateOrderExportShipmentByOrderId(List<SysOrderExport> orderList);


    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteSysOrderByOrderIds(String[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteSysOrderByOrderId(String orderId);

//    public List ExcelShipmentOrderList(SysOrder sysOrder);
    public OrderStatistics selectOrderStatistics(OrderStatistics orderStatistics);
}
