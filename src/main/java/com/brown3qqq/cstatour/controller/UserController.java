package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.UserRepository;
import com.brown3qqq.cstatour.pojo.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "add")
    public String  add(@RequestBody User user){
        //写业务逻辑要求过滤，判断权限
        userRepository.save(user);

        return "测试";
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
