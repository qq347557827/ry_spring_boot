package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单对象 sys_order
 *
 * @author ruoyi
 * @date 2023-11-23
 */
public class SysOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //    @Excel(name = "商品名称", handler = MyDataHandler.class)


    private List<SysOrderGoods> orderGoodsList;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号", sort = 1)
    private String orderId;

    /**
     * 订单类型（0预定单 1正常订单 2从发订单 3补发订单 4退款单)
     */
    @Excel(name = "订单类型", readConverterExp = "0=预定单,1=正常订单,2=从发订单,3=补发订单,4=退款单")
    private String orderType;

    /**
     * 订单状态（0待修改 1待审核 2待发货 3已发货待签收 4已签收 5拒收 6退款 8预定 )
     */
    private String status;


    @Excel(name = "订单状态")
    private String statusName;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private Long customerId;

    /**
     * 姓名
     */
    @Excel(name = "到件联系人", sort = 2)
    private String name;

    /**
     * 手机
     */
    @Excel(name = "到件手机", sort = 3)
    private String phone;
    /**
     * 省份
     */
    @Excel(name = "省份")
    private String province;

    /**
     * 城市
     */
    @Excel(name = "城市")
    private String city;

    /**
     * 区县
     */
    @Excel(name = "区县")
    private String district;

    /**
     * 区县
     */
    @Excel(name = "区域编码")
    private String areaCode;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String detailAddress;


    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;


    /**
     * 微信
     */
    @Excel(name = "微信")
    private String wechat;

    /**
     * 来源渠道
     */
    @Excel(name = "来源渠道")
    private String sourceChannel;

    /**
     * 总金额
     */
    @Excel(name = "总金额")
    private Float totalAmount;

    /**
     * 定金
     */
    @Excel(name = "定金")
    private Float deposit;

    /**
     * 定金
     */
    @Excel(name = "定金支付方式")
    private String payMethod;

    /**
     * 定金
     */
    @Excel(name = "二次收款")
    private Float secondaryRevenue;

    /**
     * 定金
     */
    @Excel(name = "二次收款支付方式")
    private String secondaryRevenueMethod;

    /**
     * 代收金额
     */
    @Excel(name = "代收货款", sort = 5)
    private Float collectAmount;

    /**
     * 退款金额
     */
    @Excel(name = "退款金额")
    private Float refund;

    /**
     * 实际金额
     */
    @Excel(name = "实际金额")
    private Float actualAmount;


    /**
     * 商品名称数量
     */
    @Excel(name = "原货物名称")
    private String orderGoodsNameQuantity;


    /**
     * 导出发货（0未导出 1已导出)
     */
    @Excel(name = "导出发货", readConverterExp = "导出发货（0未导出 1已导出)")
    private Integer exportShipment;

    /**
     * 导出发时间
     */
    private Date exportTime;


    /**
     * 下单客服ID
     */
    private Long userId;

    /**
     * 下单客服
     */
    @Excel(name = "下单客服")
    private String userName;

    /**
     * 下单部门ID
     */
    private Long deptId;

    /**
     * 下单客服ID
     */
    @Excel(name = "下单部门")
    private String deptName;

    /**
     * 原订单号
     */
    @Excel(name = "原订单号")
    private String oldId;

    @Excel(name = "快递公司")
    private String expressCompany;

    @Excel(name = "快递单号")
    private String trackingNumber;


    public List<SysOrderGoods> getOrderGoodsList() {
//        updateOrderGoodsNameQuantity();
        return orderGoodsList;
    }


    public void setOrderGoodsList(List<SysOrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setStatus(String status) {
        this.status = status;
        updateStatusName();
    }

    private void updateStatusName() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("0", "待修改");
        map.put("1", "待审核");
        map.put("2", "待发货");
        map.put("3", "已发货");
        map.put("4", "已签收");
        map.put("5", "拒收");
        map.put("6", "退款");
        map.put("8", "预定");

//        String[] statusArr = {"待修改", "待审核", "待发货", "已发货待签收", "已签收", "拒收", "退款"};
//        int statusInt = Integer.valueOf(status);
        statusName = map.get(status);
    }

    public String getStatus() {
        updateStatusName();
        return status;
    }


    public String getStatusName() {
        return statusName;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

//    public void setAddress(String address)
//    {
//        this.address = address;
//    }


    public String getAddress() {
        StringBuilder strBuilder = new StringBuilder();
        if (province != null) {
            strBuilder.append(province).append(" ");
        }
        if (city != null) {
            strBuilder.append(city).append(" ");
        }
        if (district != null) {
            strBuilder.append(district).append(" ");
        }
        address = strBuilder.append(detailAddress).toString();
        return address;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getOrderGoodsNameQuantity() {
//        updateOrderGoodsNameQuantity();
        return orderGoodsNameQuantity;
    }

    public void setOrderGoodsNameQuantity(String orderGoodsNameQuantity) {
        this.orderGoodsNameQuantity = orderGoodsNameQuantity;
    }

    private void updateOrderGoodsNameQuantity() {
        StringBuilder strBuilder = new StringBuilder();
        if (orderGoodsList != null && !orderGoodsList.isEmpty()) {
            for (SysOrderGoods orderGoods : orderGoodsList) {
                strBuilder.append(orderGoods.getGoodsNameQuantity()).append("、");
            }
            // 移除末尾多余的"、"
            orderGoodsNameQuantity = strBuilder.substring(0, strBuilder.length() - 1);
        }
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }

    public Float getDeposit() {
        return deposit;
    }

    public void setSecondaryRevenue(Float secondaryRevenue) {
        this.secondaryRevenue = secondaryRevenue;
    }

    public Float getSecondaryRevenue() {
        return secondaryRevenue;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getSecondaryRevenueMethod() {
        return secondaryRevenueMethod;
    }

    public void setSecondaryRevenueMethod(String secondaryRevenueMethod) {
        this.secondaryRevenueMethod = secondaryRevenueMethod;
    }

    public void setCollectAmount(Float collectAmount) {
        this.collectAmount = collectAmount;
    }

    public Float getCollectAmount() {
        return collectAmount;
    }

    public void setRefund(Float refund) {
        this.refund = refund;
    }

    public Float getRefund() {
        return refund;
    }

    public void setActualAmount(Float actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Float getActualAmount() {
        return actualAmount;
    }

    public void setExportShipment(Integer exportShipment) {
        this.exportShipment = exportShipment;
    }

    public Integer getExportShipment() {
        return exportShipment;
    }

    public void setExportTime(Integer exportShipment) {
        this.exportTime = exportTime;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public String getOldId() {
        return oldId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("OrderGoodsList", getOrderGoodsList()).append("goodsNameQuantity", getOrderGoodsNameQuantity()).append("orderId", getOrderId()).append("userId", getUserId()).append("deptId", getDeptId()).append("status", getStatus()).append("customerId", getCustomerId()).append("name", getName()).append("phone", getPhone()).append("province", getProvince()).append("city", getCity()).append("district", getDistrict()).append("areaCode", getAreaCode()).append("detailAddress", getDetailAddress()).append("address", getAddress()).append("wechat", getWechat()).append("sourceChannel", getSourceChannel()).append("createTime", getCreateTime()).append("totalAmount", getTotalAmount()).append("deposit", getDeposit()).append("payMethod", getPayMethod()).append("secondaryRevenue", getSecondaryRevenue()).append("secondaryRevenueMethod", getSecondaryRevenueMethod()).append("collectAmount", getCollectAmount()).append("refund", getRefund()).append("actualAmount", getActualAmount()).append("exportShipment", getExportShipment()).append("exportTime", getExportTime()).append("orderType", getOrderType()).append("oldId", getOldId()).toString();
    }


}

