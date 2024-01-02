package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OrderAmount;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.domain.SysOrderExport;
import com.ruoyi.system.domain.SysOrderGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
public interface SysOrderMapper
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
     * 查询待发货待导出订单列表
     *
     * @param sysOrder 订单
     * @return 订单集合
     */
    public List<SysOrderExport> selectSysOrderExportList(SysOrder sysOrder);
    public List<SysOrderExport> selectOrdersByOrderIds(ArrayList<String> ids);



    public List<SysOrderGoods> selectSysOrderGoodsListByOrderId(String orderId);
    /**
     * 查询字段业绩
     *
     * @param columnToSum 订单
     * @return 订单集合
     */
    public Float getTotalAmountInThisMonth(String columnToSum);
//    public Float getTotalAmountInThisMonth();

    public OrderAmount selectOrderStatisticsByTimeRange(Map params);



    /**
     * 新增订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
//    public void insertSysOrder(SysOrder sysOrder);
    public int insertSysOrder(SysOrder sysOrder);
    public void insertSysOrderGoods(SysOrder sysOrder);




    /**
     * 修改订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

//    /**
//     * 发货订单
//     *
//     * @param sysOrder 订单
//     * @return 结果
//     */
//    public int shipmentByOrderId(SysOrder sysOrder);
    /**
     * 审核订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int approvalOrderStatusByOrderId(SysOrder sysOrder);


    /**
     * 修改导出次数
     *
     * @param sysOrder 商品
     * @return 结果
     */
    public int updateOrderExportShipmentByOrderId(SysOrderExport sysOrder);


    /**
     * 导入发货
     *
     * @param orderExport 商品
     * @return 结果
     */
    public int updateOrderShipment(SysOrderExport orderExport);

//    删除
    public void deleteSysOrderGoodsByOrderId(String orderId);
    /**
     * 删除订单
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteSysOrderByOrderId(String orderId);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysOrderByOrderIds(String[] orderIds);
}
