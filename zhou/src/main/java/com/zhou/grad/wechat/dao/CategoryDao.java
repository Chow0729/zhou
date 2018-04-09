package com.zhou.grad.wechat.dao;

import com.zhou.grad.entity.Category;

public interface CategoryDao {
    int deleteByPrimaryKey(Long categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}