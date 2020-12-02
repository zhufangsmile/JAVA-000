package com.zf.service;

import com.zf.annotaion.DataSourceSelector;
import com.zf.mapper.OrderMasterMapper;
import com.zf.model.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhufang
 * @date 2020/11/30 4:25 下午
 */
@DataSourceSelector(value = DataSourceSelector.SLAVE)
@Service
public class OrderSummaryService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;


    public List<OrderMaster> queryList() {
        return orderMasterMapper.queryList();
    }
}
