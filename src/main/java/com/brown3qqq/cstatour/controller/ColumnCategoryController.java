package com.brown3qqq.cstatour.controller;


import com.brown3qqq.cstatour.pojo.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColumnCategoryController {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){

        example e = new example("10001","sad");

//        mongoTemplate.save(e,"user");
//        System.out.println("mongoDB插入数据成功,集合为user,文档为："+mongoTemplate.getCollection("user"));
       return "hello world!　烦人的世界, 热更新测试";
    }
}
