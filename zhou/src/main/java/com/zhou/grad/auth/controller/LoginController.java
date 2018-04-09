package com.zhou.grad.auth.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.grad.auth.model.LoginUserModel;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.auth.service.UserService;
import com.zhou.grad.entity.User;

@Controller
public class LoginController {

    @Autowired
    private UserService userService; 
    
    @Autowired
    private ResourceService resourceService;
    
    @ResponseBody
    @RequestMapping("login.do")
    public ModelAndView login(LoginUserModel userModel, HttpSession session) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String error = userService.checkLogin(userModel);
        User user = userService.selectUserByCondition(userModel.getUsername());
        if (user != null) {
            List<String> resUrlList = userService.selectResourceUrlByUserId(user.getUserId());
            List<String> allUrls = userService.selectAllResourceUrls();
            session.setAttribute("allUrls", allUrls);
            session.setAttribute("purview", resUrlList);
        }
        session.setAttribute("User", user);
        session.setAttribute("username", userModel.getUsername());      
        view.getModel().put("userModel", userModel);
        view.getModel().put("username", URLEncoder.encode(userModel.getUsername(), "utf-8"));
        view.getModel().put("error", URLEncoder.encode(error, "utf-8"));
        return view;
    }
    
    /**
     * @description 登出，清除session
     * @param session
     * @return 登录界面
     * @throws Exception
     */
    @GetMapping(value = "/login_out.do")
    public String logout(HttpSession session) throws Exception{  
        //清除Session  
        session.invalidate();
        return "../loginAndRegister";  
    }
    
    /**
     * 跳转到首页
     * @return
     */
    @GetMapping(value = "/index.do")
    public String toIndex() {
        return "index";
    }
}
