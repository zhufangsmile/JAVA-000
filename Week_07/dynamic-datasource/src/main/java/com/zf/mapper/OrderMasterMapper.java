package com.zf.mapper;

import com.zf.model.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhufang
 * @date 2020/11/30 10:15 上午
 */
@Mapper
public interface OrderMasterMapper {

    int insert(OrderMaster orderMaster);

    List<OrderMaster> queryList();
}
