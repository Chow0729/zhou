package com.zhou.grad.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.dao.ResourceDao;
import com.zhou.grad.auth.dao.RoleDao;
import com.zhou.grad.auth.dao.UserDao;
import com.zhou.grad.auth.dao.UserRoleDao;
import com.zhou.grad.auth.model.LoginUserModel;
import com.zhou.grad.auth.model.UserInfoModel;
import com.zhou.grad.auth.service.UserService;
import com.zhou.grad.company.dao.UserCompanyDao;
import com.zhou.grad.entity.Role;
import com.zhou.grad.entity.User;
import com.zhou.grad.entity.UserCompany;
import com.zhou.grad.entity.UserRole;
import com.zhou.grad.util.CookieTool;
import com.zhou.grad.util.MD5Util;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserCompanyDao ucDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private UserRoleDao urDao;
    
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Map<String, Object> selectAllUsersByPage(String selectedFiled, String condition, String sortOrder,
            Integer isenable, int start, int pageSize) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        List<UserInfoModel> list = null;
        int total = 0;
        paramsMap.put("condition", condition);
        paramsMap.put("isenable", isenable);
        paramsMap.put("start", start);
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("sortOrder",sortOrder);
        switch (selectedFiled) {
        case "员工名称":
            paramsMap.put("selectedFiled", "user_name");
            break;
        case "部门名称":
            paramsMap.put("selectedFiled", "dep_name");
            break;
        case "真实姓名":
            paramsMap.put("selectedFiled", "real_name");
            break;
        case "职位":
            paramsMap.put("selectedFiled", "position");
            break;
        case "电话":
            paramsMap.put("selectedFiled", "phone");
            break;
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        // 获取查询到的数据
        list = userDao.selectAllUsersByPage(paramsMap);
        // 获取查询到的记录总数
        total = userDao.countAllUsers(paramsMap);
        resultMap.put("rows", list);
        resultMap.put("total", total);
        return resultMap;
    }

    @Override
    public int addUser(User user, UserCompany uCompany) {
        int result = 0;
        if(user!=null) {
        //判断用户名是否重复
        User nameUsers = userDao.selectByUserName(user.getUserName());
        //判断邮箱是否重复
        User mailUsers = userDao.selectByMail(user.getMail());
        
        if ((nameUsers != null) || (mailUsers != null)) {
            result = -1;  //返回-1表示重名或邮箱重复
        } else {
            try {
                    user.setPassword(MD5Util.encode2hex(user.getPassword()));
                    result = userDao.insert(user);
                if (result > 0) {
                    Role role = null;
                    role = roleDao.selectByRoleCode("0002");
                    if (role != null) {
                        UserRole ur = new UserRole();
                        ur.setRoleId(role.getRoleId());
                        ur.setUserId(user.getUserId());
                        ur.setCreatedTime(new Date());
                        result=urDao.insert(ur);
                    }
                    //为该用户添加部门信息
                    result=insertUCompany(uCompany,user);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        }
        return result;
    }
    
    @Override
    public Map<String, Object> editUser(User user, UserCompany uCompany) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        int result1 = 0;
        int result2 = 0;
        //修改用户
        result1 = userDao.updateByPrimaryKey(user);
        //修改用户部门信息
        if (uCompany.getDepId() == 0) {
            uCompany.setDepId(null);
        }
        result2 = ucDao.updateByPrimaryKey(uCompany);
        if (result1>0 && result2>0) {
            returnMap.put("data", 1);
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("data", 0);
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }
    
    private int insertUCompany(UserCompany uCompany,User user) {
        uCompany.setUserId(user.getUserId());
        uCompany.setCreatedTime(new Date());
        if (uCompany.getDepId() == 0) {
            uCompany.setDepId(null);
        }
        System.out.println(uCompany+ "000000");
        int result=0;
        if(user!=null&&uCompany!=null) {
            result=ucDao.insert(uCompany);
        }
        return result;
    }

    @Override
    public Map<String, Object> selectUserWithDeptByUserId(int userId) {
        Map<String, Object> map = userDao.selectUserWithDeptByUserId(userId);
        return map;
    }

    @Override
    public Map<String, Object> updateUserStatus(Integer[] ids, int status) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("ids", ids);
        paramsMap.put("status", status);
        int result = userDao.updateUserStatus(paramsMap);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> deleteUsers(Integer[] ids) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        int result = userDao.deleteBatch(ids);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "删除成功");
        } else {
            returnMap.put("message", "删除失败");
        }
        return returnMap;
    }

    @Override
    public void remember(LoginUserModel user, HttpServletRequest request, HttpServletResponse response) {
        if (user.getRemember() != null) {
            System.out.println("添加cookie");
            CookieTool.addCookie(response, "username", user.getUsername(), 3600 * 1000);
            CookieTool.addCookie(response, "password", user.getPassword(), 3600 * 1000);
            CookieTool.addCookie(response, "remember", user.getRemember(), 3600 * 1000);
        } else {
            System.out.println("删除cookie");
            CookieTool.deleteCookie(response, request, "username");
            CookieTool.deleteCookie(response, request, "password");
            CookieTool.deleteCookie(response, request, "remember");
        }
    }

    @Override
    public String checkLogin(LoginUserModel loginUser) {
        String error = "";
        User userByName = null;
        User userByMail = null;
        try {
            userByName = userDao.selectByUserName(loginUser.getUsername());
            userByMail = userDao.selectByMail(loginUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return "登录失败，请联系管理员！";
        }
        if (userByName != null) {
            if (MD5Util.validate(loginUser.getPassword(), userByName.getPassword())) {
                return error;
            } else {
                error = "密码错误";
            }
        } else if (userByMail != null) {
            if (MD5Util.validate(loginUser.getPassword(), userByMail.getPassword())) {
                return error;
            } else {
                error = "密码错误";
            }
        } else {
            error = "用户名或邮箱不存在，请前往注册";
        }
        return error;
    }

    @Override
    public User selectUserByCondition(String condition) {
        return userDao.selectUserByCondition(condition);
    }

    @Override
    public List<String> selectResourceUrlByUserId(int userId) {
        
        return resourceDao.selectResourceUrlByUserId(userId);
    }

    @Override
    public List<String> selectAllResourceUrls() {
        
        return resourceDao.selectAllResourceUrls();
    }

    
}
