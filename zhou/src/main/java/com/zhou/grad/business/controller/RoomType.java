package com.zhou.grad.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.grad.business.service.RoomService;

@Controller
@RequestMapping("room")
public class RoomType {

    @Autowired
    private RoomService roomService;
    
    @RequestMapping("toRoomManage")
    public String toRoomManage() {
        return "business/room_manage";
    }
}
