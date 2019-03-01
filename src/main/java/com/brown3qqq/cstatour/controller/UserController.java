package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.auxiliary.response;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import com.brown3qqq.cstatour.service.userService;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    userService userService;


    //登录
    @PostMapping(path = "login")
    public JSONObject login(@RequestBody User user,HttpServletResponse httpresponse){
        try {
            Map<String, Object> map = userService.login(user.getRealname(), user.getPasswd());


            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                cookie.setPath("/");
                httpresponse.addCookie(cookie);
//                return "登录成功，下发cookie为："+ map.get("ticket");
                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
//                return "登录失败" + map.get("msg")+ " "+map.get("get")+"　"+map.get("psd");
                return new response(Statecode.FAIL).getJsonObject();

            }
        } catch (Exception e) {
            logger.error("登录异常" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();


        }
    }

    //添加用户
    @PostMapping(path = "add")
    public JSONObject  add(@RequestBody User user, HttpServletResponse httpresponse) {

        try {
            Map<String, String> map = userService.register(user.getRealname(), user.getPasswd(), user.getTelnum());


            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                cookie.setPath("/");
                httpresponse.addCookie(cookie);
//                return "注册成功,下发cookie为："+ map.get("ticket");
                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();
            }
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
           return new response(Statecode.ABNORMAL).getJsonObject();
        }
    }

    //更改密码
    @PostMapping(path = "update")
    public JSONObject  update(@RequestBody JSONObject object, HttpServletResponse httpresponse){
        try {
            Map<String, String> map = userService.update( object.get("username").toString(),object.get("password").toString(), object.get("newpassword").toString());
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                cookie.setPath("/");
                httpresponse.addCookie(cookie);
                return new response(Statecode.SUCCESS).getJsonObject();
            } else {
                return new response(Statecode.FAIL).getJsonObject();
            }
        } catch (Exception e) {
            logger.error("更改密码异常" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();
        }
    }
}
