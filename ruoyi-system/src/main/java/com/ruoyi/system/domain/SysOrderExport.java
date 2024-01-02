package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysOrderExport {

    private List<SysOrderGoods> orderGoodsList;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号", sort = 1)
    private String orderId;

    @Excel(name = "快递公司", sort = 2)
    private String expressCompany;
    /**
     * 姓名
     */
    @Excel(name = "到件联系人", sort = 3)
    private String name;

    /**
     * 手机
     */
    @Excel(name = "到件手机", sort = 4)
    private String phone;

    /**
     * 地址
     */
    @Excel(name = "到件人地址", sort = 5)
    private String address;

    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;


    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 代收金额
     */
    @Excel(name = "代收货款", sort = 6)
    private Float collectAmount;

    @Excel(name = "原货物名称", sort = 7)
    private String orderGoodsNameQuantity;



    @Excel(name = "备注", sort = 8)
    final String expressRemark = "D794";

    @Excel(name = "货主", sort = 9)
    final String huoZhu = "YAYUN";

    @Excel(name = "保价", sort = 10)
    private String baoJia;

    @Excel(name = "付款方式", sort = 11)
    final String expressPayMethod = "寄方付";

    @Excel(name = "快件产品名称", sort = 12)
    final String expressName = "电商标快";

    @Excel(name = "快递单号", sort = 13)
    private String trackingNumber;

    private ArrayList<String> searchIds;

//    public String getAddress() {
//        updateAddress();
//        return address;
//    }
//
//    public String getProvince() {
//        updateAddress();
//        return province;
//    }
//
//    public String getCity() {
//        updateAddress();
//        return city;
//    }
//
//
//    public String getDistrict() {
//        updateAddress();
//        return district;
//    }
//
//    private void updateAddress() {
//        StringBuilder strBuilder = new StringBuilder();
//        if (province != null) {
//            strBuilder.append(province).append(" ");
//        }
//        if (city != null) {
//            strBuilder.append(city).append(" ");
//        }
//        if (district != null) {
//            strBuilder.append(district).append(" ");
//        }
//        address = strBuilder.append(detailAddress).toString();
//    }
}
