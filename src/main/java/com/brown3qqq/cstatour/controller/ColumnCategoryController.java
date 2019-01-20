package com.brown3qqq.cstatour.controller;


<<<<<<< HEAD
import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.pojo.example;
=======
>>>>>>> temp
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

=======
>>>>>>> temp
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

    public static void main(String[] args) {
//
//            JSONObject object = new JSONObject();
//
//            object.put("姓名","胡一博");
//        object.put("boolean",true);
//        object.put("数字","0");
//
//        object.

        //System.out.println(object);
//            //string
//            object.put("string","string");
//            //int
//            object.put("int",2);
//            //boolean
//            object.put("boolean",true);
//            //array
//            List<Integer> integers = Arrays.asList(1,2,3);
//            object.put("list",integers);
//            //null
// object.put("null",null);​
//System.out.println(object);

    }
}
