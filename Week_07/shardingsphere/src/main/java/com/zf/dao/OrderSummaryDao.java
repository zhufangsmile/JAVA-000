package com.zf.dao;

import com.zf.mapper.OrderMasterMapper;
import com.zf.model.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhufang
 * @date 2020/11/30 4:25 下午
 */
@Repository
public class OrderSummaryDao {

    @Autowired
    private OrderMasterMapper orderMasterMapper;


    public List<OrderMaster> queryList() {
        return orderMasterMapper.queryList();
    }
}
