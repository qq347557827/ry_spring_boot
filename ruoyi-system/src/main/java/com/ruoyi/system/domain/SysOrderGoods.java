package com.ruoyi.system.domain;


import lombok.Data;

@Data
public class SysOrderGoods {
    private Long goodsId;
    private String orderId;
    private String goodsName;
    private Long quantity;
    private String goodsNameQuantity;
    // 省略其他属性和方法
    public void updateGoodsNameQuantity() {
        if (goodsName != null && quantity != null) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(goodsName)
                    .append("*")
                    .append(quantity);
            goodsNameQuantity = strBuilder.toString();
        }
    }

    public String getGoodsName() {
        updateGoodsNameQuantity();
        return goodsName;
    }

    public Long getQuantity() {
        updateGoodsNameQuantity();
        return quantity;
    }
}
