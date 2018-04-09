package com.zhou.grad.wechat.service.impl;

import java.util.Date;
import java.util.List;

import com.zhou.grad.entity.Order;
import com.zhou.grad.entity.OrderFood;
import com.zhou.grad.wechat.service.OrderService;

public class OrderServiceImpl implements OrderService{

    @Override
    public void addOrder(Order order, List<OrderFood> foods) {
        //生成一个随机数用作订单号
        
        
        order.setOrderTime(new Date());
        
    }

}
