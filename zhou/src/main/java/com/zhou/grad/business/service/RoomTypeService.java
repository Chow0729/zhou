package com.zhou.grad.business.service;

import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;

public interface RoomTypeService {

    /**
     * 分页查询所有的房间类型
     * @param parmas
     * @return
     */
    Map<String, Object> selectAllRoomTypeByPage(QueryParamsModal params);
}
