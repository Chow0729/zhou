package com.zhou.grad.wechat.dao;

import java.util.List;

import com.zhou.grad.entity.Food;
import com.zhou.grad.wechat.model.FoodReturnModel;

public interface FoodDao {
    int deleteByPrimaryKey(Integer foodId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer foodId);
    
    /**
     * 按种类查询所有的食品
     * @return
     */
    List<FoodReturnModel> selectFoodsOrderByCatgid();

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}