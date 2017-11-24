package com.cz.service.service;

import com.cz.api.service.IOrderService;
import com.cz.common.base.BaseServiceImpl;
import com.cz.mapper.OrderMapper;
import com.cz.model.order.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * Created by jomalone_jia on 2017/11/23.
 */
@Service
@CacheConfig(cacheNames = "order_cache")
public class OrderService extends BaseServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Order> listOrders(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderMapper.listOrders());

    }
}
