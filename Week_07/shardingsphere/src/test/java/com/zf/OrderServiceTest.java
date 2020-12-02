package com.zf;

import com.zf.dao.OrderDao;
import com.zf.dao.OrderSummaryDao;
import com.zf.model.OrderMaster;
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
    private OrderDao orderDao;

    @Autowired
    private OrderSummaryDao orderSummaryDao;

    @Test
    public void testCreateOrder() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderKey("20201130000000");
        orderMaster.setShopID(1);
        orderMaster.setUserID(1);
        orderMaster.setDeliveryAdress("北京");
        orderMaster.setDeliveryName("zf");
        orderMaster.setOrderTotalAmount(BigDecimal.TEN);
        orderDao.createOrder(orderMaster);
    }

    @Test
    public void testQueryList() {
        orderSummaryDao.queryList();
    }

}
