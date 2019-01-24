package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import com.brown3qqq.cstatour.service.userService;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    userService userService;
    @PostMapping(path = "login")
    public String login(@RequestBody User user){
            return "ok";
    }
    @PostMapping(path = "add")
    public String  add(@RequestBody User user, HttpServletResponse response) {
        //写业务逻辑要求过滤，判断权限
        try {
            Map<String, String> map = userService.register(user.getRealname(), user.getPasswd(), user.getTelnum());
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "DDDlogin";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "232login";
            }
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return "FFFlogin";

        }

    }
    @PostMapping(path = "update")
    public String  update(@RequestBody User user){
        //业务逻辑，判断
        Iterable<User> userIterable =  userRepository.findAll();
        Iterator<User> it = userIterable.iterator();

        while (it.hasNext()){
            System.out.println(it.next().getId());
            System.out.println(it.next().getRealname());
        }

        return "测试";
    }

}
