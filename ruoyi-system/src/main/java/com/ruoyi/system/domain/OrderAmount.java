package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class OrderAmount {

    private Float totalAmountSum;

    /**
     * 定金
     */

    private Float depositSum;


    private Long orderCount;
    /**
     * 代收金额
     */

    private Float collectAmountSum;

    private Float refundSum;

    /**
     * 实际金额
     */

    private Float actualAmountSum;
}
