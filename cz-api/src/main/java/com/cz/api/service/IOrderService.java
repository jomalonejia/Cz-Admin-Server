package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.order.Order;
import com.github.pagehelper.PageInfo;

/**
 * Created by jomalone_jia on 2017/11/24.
 */
public interface IOrderService extends IService<Order> {
    PageInfo<Order> listOrders(int pageNum,int pageSize);
}
