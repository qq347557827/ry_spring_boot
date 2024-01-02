package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-23
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService {
    @Autowired
    private SysOrderMapper sysOrderMapper;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询订单
     *
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public SysOrder selectSysOrderByOrderId(String orderId) {
        return sysOrderMapper.selectSysOrderByOrderId(orderId);
    }

    public SysOrder selectSysOrderStatusByOrderId(String orderId) {
        return sysOrderMapper.selectSysOrderStatusByOrderId(orderId);
    }

    /**
     * 查询订单列表
     *
     * @param sysOrder 订单
     * @return 订单
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder) {
        System.out.println(sysOrder.getStatus());
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 查询导出列表
     *
     * @param orderExport 商品
     * @return 商品
     */

    public List<SysOrderExport> selectSysOrderExportShipmentList(SysOrderExport orderExport) {
        List<SysOrderExport> orderList;
        if (orderExport.getSearchIds() != null && !orderExport.getSearchIds().isEmpty()) {
            orderList = sysOrderMapper.selectOrdersByOrderIds(orderExport.getSearchIds());
        } else {
            SysOrder sysOrder = new SysOrder();
            sysOrder.setExportShipment(0);
            sysOrder.setStatus("2");
            orderList = sysOrderMapper.selectSysOrderExportList(sysOrder);
        }

        for (SysOrderExport order : orderList) {

            StringBuilder addressStr = new StringBuilder();
            if (order.getProvince() != null) {
                addressStr.append(order.getProvince()).append(" ");
            }
            if (order.getCity() != null) {
                addressStr.append(order.getCity()).append(" ");
            }
            if (order.getDistrict() != null) {
                addressStr.append(order.getDistrict()).append(" ");
            }
            String address = addressStr.append(order.getDetailAddress()).toString();
            order.setAddress(address);
            StringBuilder strBuilder = new StringBuilder();
            if (order.getOrderGoodsList() != null && !order.getOrderGoodsList().isEmpty()) {
                for (SysOrderGoods orderGoods : order.getOrderGoodsList()) {
                    strBuilder.append(orderGoods.getGoodsName())
                            .append("*")
                            .append(orderGoods.getQuantity())
                            .append(" + ");

                }
                // 移除末尾多余的"、"
                System.out.printf("strBuilder", strBuilder);
                order.setOrderGoodsNameQuantity(strBuilder.substring(0, strBuilder.length() - 2));
            }
        }
        return orderList;
    }


    /**
     * 导入发货
     */

    public AjaxResult importShipmentOrder(List<SysOrderExport> orderExportList) {

        if (StringUtils.isNull(orderExportList) || orderExportList.isEmpty()) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<SysOrderExport> failureOrderExportList = new ArrayList<>();
        for (SysOrderExport orderExport : orderExportList) {
            try {
                int res = sysOrderMapper.updateOrderShipment(orderExport);
                if (res > 0) {
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、订单号 " + orderExport.getOrderId() + " 导入发货成功");
                } else {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、订单号 " + orderExport.getOrderId() + " 导入发货失败：";
                    failureOrderExportList.add(orderExport);
                }

            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、订单号 " + orderExport.getOrderId() + " 导入发货失败：";
                failureMsg.append(msg + e.getMessage());
                failureOrderExportList.add(orderExport);
//                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, successNum + "条数据，导入发货成功 " + failureNum + " 条数据导入发货失败，错误如下：");
//            throw new ServiceException(failureMsg.toString());
            return AjaxResult.error(String.valueOf(failureMsg), failureOrderExportList);
        } else {
            successMsg.insert(0, "全部导入发货成功！共 " + successNum + " 条，数据如下：");
        }
        return AjaxResult.success(String.valueOf(successMsg), orderExportList);
    }

    public SysOrder initOrder(SysOrder sysOrder) {
        if (sysOrder.getCustomerId() == null) {
            throw new ServiceException("用户id为空");
        }
        Customer customer = customerMapper.selectCustomerById(sysOrder.getCustomerId());
        LoginUser loginUser = SecurityUtils.getLoginUser();

        Long deptId = loginUser.getDeptId();
        Long userId = loginUser.getUserId();
        String orderId = DateUtils.dateTimeNow();
        if (sysOrder.getOrderType() == null) {
            sysOrder.setOrderType("1");
        }
        sysOrder.setDeptId(deptId);
        sysOrder.setUserId(userId);
        sysOrder.setOrderId(orderId);
        sysOrder.setStatus("1");
        String wechat = customer.getWechat();
        if ( wechat != null && wechat.isEmpty()) {
            sysOrder.setWechat(customer.getWechat());
        }
        sysOrder.setSourceChannel(customer.getSourceChannel());
        return sysOrder;
    }

    /**
     * 新增订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysOrder(SysOrder sysOrder) {
//        String wechat = selectSysOrderByOrderId(sysOrder.getOrderId()).getWechat();

//        sysOrder.setOrderType("1");
        SysOrder initOrder = initOrder(sysOrder);
        System.out.println("insertSysOrder--sysOrder:" + initOrder);
        int i = sysOrderMapper.insertSysOrder(initOrder);
        sysOrderMapper.insertSysOrderGoods(sysOrder);

        return i;
    }

    @Override
    @Transactional
    public int insertSysOrderAndCustomer(SysOrderAndCustomer orderAndCustomer) {
        Customer customer = orderAndCustomer.getCustomer();
        SysOrder order = orderAndCustomer.getSysOrder();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long deptId = loginUser.getDeptId();
        Long userId = loginUser.getUserId();
        customer.setUserId(userId);
        customer.setDeptId(deptId);
//        customer.setName(order.getName());
//        customer.setPhone(order.getPhone());
//        customer.setAddress(order.getAddress());

        int customerRes = customerMapper.insertCustomer(customer);
        if (customerRes == 1) {
            order.setCustomerId(customer.getId());
            return insertSysOrder(order);
        }

        return 0;
    }

    /**
     * 修改订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    @Transactional
    public int updateSysOrder(SysOrder sysOrder) {
        sysOrderMapper.deleteSysOrderGoodsByOrderId(sysOrder.getOrderId());
        sysOrderMapper.insertSysOrderGoods(sysOrder);
        sysOrder.setStatus("1");
        return sysOrderMapper.updateSysOrder(sysOrder);
    }
    private SysOrder validateOrderId(SysOrder sysOrder, String str) {
        if (sysOrder.getOrderId() == null || sysOrder.getOrderId().isEmpty()) {
            throw new ServiceException("没有提交订单编号！");
        }
        SysOrder initOrder = sysOrderMapper.selectSysOrderByOrderId(sysOrder.getOrderId());
        if (str.equals(initOrder.getStatus())) {
            return initOrder;
        } else {
            throw new ServiceException("该订单状态下 不能进行这样的操作，请检查！");
        }
    }
    /**
     * 发货订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int shipmentByOrderId(SysOrder sysOrder) {
        SysOrder initOrder =  validateOrderId(sysOrder, "2");
        initOrder.setOrderId(sysOrder.getOrderId());
        initOrder.setExpressCompany(sysOrder.getExpressCompany());
        initOrder.setTrackingNumber(sysOrder.getTrackingNumber());
        initOrder.setStatus("3");
        return sysOrderMapper.updateSysOrder(initOrder);
    }
    /**
     * 二次收款
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int secondaryRevenueByOrderId(SysOrder sysOrder) {
        SysOrder initOrder =  validateOrderId(sysOrder, "3");
        initOrder.setOrderId(sysOrder.getOrderId());

        initOrder.setSecondaryRevenue(sysOrder.getSecondaryRevenue());
        initOrder.setSecondaryRevenueMethod(sysOrder.getSecondaryRevenueMethod());
        float collectAmount = initOrder.getTotalAmount() - initOrder.getDeposit() - sysOrder.getSecondaryRevenue();
        initOrder.setCollectAmount(collectAmount);

        return sysOrderMapper.updateSysOrder(initOrder);
    }

    public AjaxResult approvalUtils(String[] idsArr, String setStatus, String str) {
        if (idsArr != null && idsArr.length < 1) {
            throw new ServiceException("没有提交订单审核！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<String> failureIdsArr = new ArrayList<String>();
        for (String id : idsArr) {
            try {
                SysOrder order = selectSysOrderByOrderId(id);
                // 判断当前订单是否为审核状态
                if (!"1".equals(order.getStatus())) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、订单号 " + id + " 不是待审核状态";
                    failureIdsArr.add(id);
                    failureMsg.append(msg);
                    continue;  // 如果非审核状态就结束循环
                }
                int res;
                // 如果订单是预定单，且为审核状态，审核到 8 预定状态
                if ("0".equals(order.getOrderType()) && "2".equals(setStatus)) {
                    order.setStatus("8");
                } else {
                    order.setStatus(setStatus);
                }
                res = sysOrderMapper.approvalOrderStatusByOrderId(order);

//                如果修改状态返回成功
                if (res > 0) {
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、订单号 " + id + str + " 成功");
                } else {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、订单号 " + id + str + " 失败，请重试";
                    failureMsg.append(msg);
                    failureIdsArr.add(id);
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、订单号 " + id + str + " 失败，请重试：";
                failureMsg.append(msg).append(e.getMessage());
                failureIdsArr.add(id);
            }
        }
        if (failureNum > 0 && successNum < 1) {
            failureMsg.insert(0, failureNum + " 条" + str + "失败，错误如下：");
//            throw new ServiceException(failureMsg.toString());
            return AjaxResult.error(String.valueOf(failureMsg), failureIdsArr);
        } else if (failureNum > 0) {
            failureMsg.insert(0, successNum + "条订单" + str + "成功 " + failureNum + " 条" + str + "失败，错误如下：");
//            throw new ServiceException(failureMsg.toString());
            return AjaxResult.error(String.valueOf(failureMsg), failureIdsArr);
        } else {
            successMsg.insert(0, "全部" + str + "成功！共 " + successNum + " 条，数据如下：");
        }
        return AjaxResult.success(String.valueOf(successMsg));
    }

    /**
     * 审核订单
     *
     * @param idsArr 订单
     * @return 结果
     */
    public AjaxResult approvalOrderStatusByOrderIds(String[] idsArr) {
        return approvalUtils(idsArr, "2", "审核");

    }

    /**
     * 拒绝订单
     *
     * @param idsArr 订单
     * @return 结果
     */
    public AjaxResult rejectOrderStatusByOrderIds(String[] idsArr) {
        return approvalUtils(idsArr, "0", "拒绝");
    }

    /**
     * 审核或者拒绝订单
     *
     * @param order 订单
     * @return 结果
     */
    public int approvalRejectOrderStatusByOrderId(SysOrder order) {
        String setStatus = order.getStatus();
        String id = order.getOrderId();
        if (id == null || id.isEmpty()) {
            throw new ServiceException("订单编号为空");
        }

        SysOrder initOrder = sysOrderMapper.selectSysOrderByOrderId(order.getOrderId());
        String getStatus = initOrder.getStatus();
        String getType = initOrder.getOrderType();

        if (!"2".equals(setStatus) && !"0".equals(setStatus)) {
            throw new ServiceException("提交状态不对，只能为0 或者 2！");
        } else if ("2".equals(setStatus)) {
            if (!"1".equals(getStatus)) {
                throw new ServiceException("只有待审核 才能审核！");
            }

            if ("0".equals(getType)) {
                initOrder.setStatus("8");
            } else {
                initOrder.setStatus("2");
            }
        } else {
            if ("1".equals(getStatus) || "2".equals(getStatus) || "8".equals(getStatus)) {
                initOrder.setStatus("0");
            } else {
                throw new ServiceException("只有待审核 待发货  预定状态的订单才能被踢回！");
            }
        }

        return sysOrderMapper.approvalOrderStatusByOrderId(initOrder);

    }

    public void updateOrderExportShipmentByOrderId(List<SysOrderExport> orderList) {
        for (SysOrderExport order : orderList) {
            sysOrderMapper.updateOrderExportShipmentByOrderId(order);
        }
    }


    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderIds(String[] orderIds) {
        return sysOrderMapper.deleteSysOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderId(String orderId) {
        return sysOrderMapper.deleteSysOrderByOrderId(orderId);
    }

    /*
      查询本月总业绩

      @param orderId 订单主键
     * @return 结果
     */


//     public OrderAmount selectOrderAllAmount() {
//         LocalDate today = LocalDate.now();
//         // 今天
//         LocalDateTime todayStart = today.atStartOfDay();
//         LocalDateTime todayEnd = today.atTime(LocalTime.MAX);
//         Map<String, Object> params = new HashMap<>();
//
//         params.put("startTime", todayStart);
//         params.put("endTime", todayEnd);
//         //         SysOrder order = new SysOrder();
////         OrderStatistics statistics = new OrderStatistics();
////         Float totalAmount = sysOrderMapper.getTotalAmountInThisMonth("total_amount");
////         Float deposit = sysOrderMapper.getTotalAmountInThisMonth("deposit");
////         Float collectAmount = sysOrderMapper.getTotalAmountInThisMonth("collect_amount");
////         order.setTotalAmount(totalAmount);
////         order.setDeposit(deposit);
////         order.setCollectAmount(collectAmount);
//         return SysOrderMapper.selectOrderStatisticsByTimeRange(params);
//     };

    public OrderAmount selectOrderAllAmount(LocalDateTime startTime,LocalDateTime endTime, Map<String, Object> params) {


        params.put("startTime", startTime);
        params.put("endTime", endTime);

        // 使用 @Autowired 或其他方式注入 SysOrderMapper 的实例

        // 处理返回结果
        // ...

        return sysOrderMapper.selectOrderStatisticsByTimeRange(params);
    }

    public OrderAmount selectOrderAllAmountByLocalDate (LocalDate startTime,LocalDateTime endTime, Map<String, Object> params) {



        params.put("startTime", startTime);
        params.put("endTime", endTime);

        // 使用 @Autowired 或其他方式注入 SysOrderMapper 的实例

        // 处理返回结果
        // ...


        return sysOrderMapper.selectOrderStatisticsByTimeRange(params);
    }

    public OrderStatistics selectOrderStatistics(OrderStatistics orderStatistics) {
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        System.out.printf("____dataspoce____loginUser ------" + String.valueOf(loginUser.getUser()) + "!!!!!!!!!!!!!!!!");
//        if (StringUtils.isNotNull(loginUser))
//        {
//            SysUser currentUser = loginUser.getUser();
//            // 如果是超级管理员，则不过滤数据
//            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin())
//            {
//                SysRoleMapper roleMapper = sysRoleMapper.selectRoleListByUserId(currentUser.getUserId());
//
//            }
//        }


        LocalDate today = LocalDate.now();
        // 今天
        LocalDateTime todayStart = today.atStartOfDay();
        LocalDateTime todayEnd = today.atTime(LocalTime.MAX);
//        Map<String, Object> params = orderStatistics.getParams();
//        params.put("startTime", todayStart);
//        params.put("endTime", todayEnd);
        orderStatistics.setTodayStatistics(selectOrderAllAmount(todayStart, todayEnd, orderStatistics.getParams()));
//        orderStatistics.setTodayStatistics(sysOrderMapper.selectOrderStatisticsByTimeRange(params));

        // 昨天
        LocalDate yesterday = today.minusDays(1);
        LocalDateTime yesterdayStart = yesterday.atStartOfDay();
        LocalDateTime yesterdayEnd = yesterday.atTime(LocalTime.MAX);
        orderStatistics.setYesterdayStatistics(selectOrderAllAmount(yesterdayStart, yesterdayEnd, orderStatistics.getParams()));

        // 本周
        LocalDate thisWeekStart = today.with(java.time.DayOfWeek.MONDAY);
        LocalDateTime thisWeekEnd = today.plusDays(6).atTime(LocalTime.MAX);
        orderStatistics.setWeekStatistics(selectOrderAllAmountByLocalDate(thisWeekStart, thisWeekEnd, orderStatistics.getParams()));

        // 本月
        LocalDate thisMonthStart = today.withDayOfMonth(1);
        LocalDateTime thisMonthEnd = today.withDayOfMonth(today.lengthOfMonth()).atTime(LocalTime.MAX);
        orderStatistics.setMonthStatistics(selectOrderAllAmountByLocalDate(thisMonthStart, thisMonthEnd, orderStatistics.getParams()));

        // 上月
        LocalDate lastMonthStart = today.minusMonths(1).withDayOfMonth(1);
        LocalDateTime lastMonthEnd = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth()).atTime(LocalTime.MAX);
        orderStatistics.setLastMonthStatistics(selectOrderAllAmountByLocalDate(lastMonthStart, lastMonthEnd, orderStatistics.getParams()));





        return  orderStatistics;
    };
     /**
     * 查询本月总定金
     *
     * @param orderId 订单主键
     * @return 结果
     */

    /**
     * 查询本月总代收
     *
     * @param orderId 订单主键
     * @return 结果
     */


    /**
     * 查询部门本月业绩
     *
     * @param orderId 订单主键
     * @return 结果
     */


    /**
     * 个人业绩排名
     *
     * @param orderId 订单主键
     * @return 结果
     */


    /**
     * 个人单数排名
     *
     * @param orderId 订单主键
     * @return 结果
     */
//    public List ExcelShipmentOrderList(SysOrder sysOrder)
//    {
//        List sysOder = sysOrderMapper.selectSysOrderList(sysOrder);
//        for (item: sysOder;
//             ) {
//
//        }
//        return sysOder;
//
//    }
}
