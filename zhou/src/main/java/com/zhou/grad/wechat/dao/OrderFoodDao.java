package com.zhou.grad.wechat.dao;

import com.zhou.grad.entity.OrderFood;

public interface OrderFoodDao {
    int deleteByPrimaryKey(Integer orderFoodId);

    int insert(OrderFood record);

    int insertSelective(OrderFood record);

    OrderFood selectByPrimaryKey(Integer orderFoodId);

    int updateByPrimaryKeySelective(OrderFood record);

    int updateByPrimaryKey(OrderFood record);
}