package com.zf.service;

import com.zf.annotaion.DataSourceSelector;
import com.zf.mapper.OrderMasterMapper;
import com.zf.model.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhufang
 * @date 2020/11/30 1:06 下午
 */
@Service
@DataSourceSelector
public class OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;


    public void createOrder(OrderMaster orderMaster) {
        orderMasterMapper.insert(orderMaster);
    }

}
