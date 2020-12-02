package com.zf.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhufang
 * @date 2020/11/30 10:06 上午
 */
@Data
public class OrderMaster {
    private Long orderID;
    private String orderKey;
    private BigDecimal goodsAmount;
    private int orderStatus;
    private long userID;
    private long shopID;
    private String deliveryAdress;
    private String deliveryName;
    private String deliveryTel;
    private BigDecimal expressAmount;
    private BigDecimal orderTotalAmount;
    private long creatTime;
    private long actionTime;
}
