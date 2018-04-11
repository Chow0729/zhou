package com.zhou.grad.business.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.Room;
import com.zhou.grad.entity.RoomType;

public interface RoomTypeDao {
    int deleteByPrimaryKey(Integer roomTypeId);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Integer roomTypeId);
    
    /**
     * 分页查询所有的房间类型
     * @param params
     * @return
     */
    List<RoomType> selectByPage(Map<String, Object> map);
    
    /**
     * 统计所有的房间类型数量
     * @param params
     * @return
     */
    int countAllRoomType(Map<String, Object> map);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);
}