package com.brown3qqq.cstatour.controller;



import com.alibaba.fastjson.JSONObject;


import com.brown3qqq.cstatour.service.columnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class ColumnCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnCategoryController.class);
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    columnService columnservice;

    //添加栏目类
    @RequestMapping(value = "/addcolumn", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = columnservice.add(jsonObject.getString("cnname"),jsonObject.getString("enname"),jsonObject.getBoolean("state"),jsonObject.getString("mothercolumn"),jsonObject.getIntValue("sequence"),jsonObject.getBoolean("topnav"),jsonObject.getString("columncontent"));

            if (map.containsKey("state")) {

                return "添加栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "添加栏目失败" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("添加栏目异常" + e.getMessage());
            return "添加栏目异常";
        }

    }

    //更新栏目类内容
    @RequestMapping(value = "/updatecolumn", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = columnservice.update(jsonObject.getString("id"),jsonObject.getString("cnname"),jsonObject.getString("enname"),jsonObject.getBoolean("state"),jsonObject.getString("mothercolumn"),jsonObject.getIntValue("sequence"),jsonObject.getBoolean("topnav"),jsonObject.getString("columncontent"));

            if (map.containsKey("state")) {

                return "更新栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "更新栏目失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "更新栏目异常";
        }

    }

    //删除栏目内容
    @RequestMapping(value = "/deletecolumn", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = columnservice.delete(jsonObject.getString("id"),jsonObject.getBoolean("state"),jsonObject.getString("mothercolumn"));

            if (map.containsKey("state")) {

                return "删除栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "删除栏目失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "删除栏目异常";
        }


    }

    //获取全部栏目
    @RequestMapping(value = "/getcolumn", method = RequestMethod.POST)
    public JSONObject get(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        try {
            return columnservice.getallcolumn();
        }catch (Exception e){
            return columnservice.getallcolumn();
        }

    }
}
