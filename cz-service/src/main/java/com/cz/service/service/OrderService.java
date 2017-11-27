package com.cz.service.service;

import com.cz.api.service.IOrderService;
import com.cz.common.base.BaseServiceImpl;
import com.cz.mapper.OrderMapper;
import com.cz.mapper.OrderTrackMapper;
import com.cz.model.order.Order;
import com.cz.model.order.OrderTrack;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jomalone_jia on 2017/11/23.
 */
@Service
@CacheConfig(cacheNames = "order_cache")
public class OrderService extends BaseServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderTrackMapper orderTrackMapper;

    @Override
    public PageInfo<Order> listOrders(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderMapper.listOrders());
    }

    @Override
    @Transactional
    public void updateStatus(String orderId,String trackInformation) {
        Order order = orderMapper.selectById(orderId);
        order.setStatus(order.getStatus().next());
        orderMapper.updateById(order);
        orderTrackMapper.insert(new OrderTrack(order.getId(),trackInformation));
    }
}
