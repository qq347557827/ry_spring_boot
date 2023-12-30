package com.ruoyi.system.domain;

import com.ruoyi.common.utils.poi.ExcelHandlerAdapter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class MyDataHandler implements ExcelHandlerAdapter
{
    @Override
    public Object format(Object value, String[] args, Cell cell, Workbook wb)
    {
        // value 为返回单元格显示内容值
        // args 为excel注解传递的args数组值
        // cell 为单元格对象
        // wb 为工作簿对象
        StringBuilder stringBuilder = new StringBuilder();
        List<SysOrderGoods> orderGoodsList = (List<SysOrderGoods>) value;
        for (SysOrderGoods sysOrderGoods: orderGoodsList) {
            String goodsNameQuantity = sysOrderGoods.getGoodsNameQuantity();

            stringBuilder.append(sysOrderGoods.getGoodsName())
                    .append("*")
                    .append(sysOrderGoods.getQuantity())
                    .append("、");
        }
        String s  = stringBuilder.substring(0, stringBuilder.length() - 1);
        System.out.printf("MyDataHandler value" + value);


        return s;
    }
}