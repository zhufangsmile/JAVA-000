package com.zf;

import com.zf.model.OrderMaster;
import com.zf.service.OrderService;
import com.zf.service.OrderSummaryService;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @author zhufang
 * @date 2020/11/30 2:34 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderSummaryService orderSummaryService;

    @Test
    public void testCreateOrder() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderKey("20201130000000");
        orderMaster.setShopID(1);
        orderMaster.setUserID(1);
        orderMaster.setDeliveryAdress("北京");
        orderMaster.setDeliveryName("zf");
        orderMaster.setOrderTotalAmount(BigDecimal.TEN);
        orderService.createOrder(orderMaster);
    }

    @Test
    public void testQueryList() {
        orderSummaryService.queryList();
    }

}
