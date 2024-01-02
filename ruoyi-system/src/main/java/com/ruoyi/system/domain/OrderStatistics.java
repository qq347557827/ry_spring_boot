package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class OrderStatistics extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private OrderAmount todayStatistics;
    private OrderAmount yesterdayStatistics;
    private OrderAmount weekStatistics;
    private OrderAmount monthStatistics;
    private OrderAmount lastMonthStatistics;
}