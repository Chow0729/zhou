package com.zhou.grad.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.entity.Order;
import com.zhou.grad.entity.OrderFood;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("wechat/order")
public class OrderController {

    @ResponseBody
    @RequestMapping("addOrder")
    public void addOrder( Order order, String cartList) throws UnsupportedEncodingException {
        //转码
        order.setNickName(new String(order.getNickName().getBytes("ISO-8859-1"), "UTF-8"));
        order.setRemark(new String(order.getRemark().getBytes("ISO-8859-1"), "UTF-8"));
        cartList=new String(cartList.getBytes("ISO-8859-1"), "UTF-8");
        
        //将json字符串转化为list
        List<OrderFood> foods=(List<OrderFood>)JSONArray.toList(JSONArray.fromObject(cartList), OrderFood.class);
        
    }
    
}
