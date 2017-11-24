package com.cz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.order.Order;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/11/23.
 */
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> listOrders();
}
