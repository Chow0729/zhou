package com.zhou.grad.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.business.dao.RoomDao;
import com.zhou.grad.business.dao.RoomTypeDao;
import com.zhou.grad.business.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomDao roomDao;
    
}
