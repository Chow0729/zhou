package com.zhou.grad.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.RoomTypeService;

@Controller
@RequestMapping("roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;
    
    @ResponseBody
    @RequestMapping("getAllRoomTypeByPage")
    public Map<String, Object> getAllRoomTypeByPage(@ModelAttribute("params") QueryParamsModal params) {
        System.out.println(params+"     params");
        return roomTypeService.selectAllRoomTypeByPage(params);
    }
}
